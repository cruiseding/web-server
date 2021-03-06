package com.net.aipeng.redirect.resolverD.interface4.pay;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.SqlUtil;
import com.net.aipeng.redirect.resolverD.interface1.share_agentinfo;
import com.net.aipeng.redirect.resolverD.interface4.pay.util.RequestHandler;

@WebServlet("/wxpay/notify")
public class WXNotifyServletInOut extends HttpServlet {

	JyLogDetect log = null;

	public WXNotifyServletInOut() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		log = new JyLogDetect();
		log.send("01115", "支付成功的回调：", "支付成功的回调");
		String out_trade_no = null;
		String return_code = null;
		String transaction_id = "";
		String attach = null;
		try {
			InputStream inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			String resultStr = new String(outSteam.toByteArray(), "utf-8");
			log.send("01115", "", "支付成功的回调：" + resultStr);
			// logger.info("支付成功的回调："+resultStr);
			Map<String, Object> resultMap = parseXmlToList(resultStr);
			String result_code = (String) resultMap.get("result_code");
			String is_subscribe = (String) resultMap.get("is_subscribe");
			transaction_id = (String) resultMap.get("transaction_id");
			String sign = (String) resultMap.get("sign");
			String time_end = (String) resultMap.get("time_end");
			String bank_type = (String) resultMap.get("bank_type");
			out_trade_no = (String) resultMap.get("out_trade_no");
			return_code = (String) resultMap.get("return_code");
			// attach =(String) resultMap.get("attach");
			request.setAttribute("out_trade_no", out_trade_no);
			log.send("01115", "支付成功的回调：", "" + out_trade_no);
			// log.send("01115", "支付成功的回调：", ""+attach);
			log.send("01115", "支付成功的回调：", "" + return_code);
			// 通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.
			response.getWriter().write(RequestHandler.setXML("SUCCESS", ""));
		} catch (Exception e) {
			// logger.error("微信回调接口出现错误：",e);
			try {
				response.getWriter().write(RequestHandler.setXML("FAIL", "error"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		log.send("01115", "支付成功的回调：", "处理订单" + return_code);
		if (return_code.equals("SUCCESS")) {
			try {
				log.send("01115", "支付成功的业务逻辑：", "处理订单" + return_code);
				// String out_trade_no=request.getParameter("out_trade_no").toString();
				log.send("01115", "支付成功的回调out_trade_no：", out_trade_no);
				SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
				String sql = "select * from order_management where order_num='" + out_trade_no + "'";
				log.send("01115", "支付成功的回调out_trade_no：", sql);
				ArrayList<Map<String, Object>> list = null;
				list = sqlUtil.get_list(sql);
				log.send("01115", "支付成功的回调：", list);
				if (list.size() == 1) {
					if (list.get(0).get("pay_status").toString().equals("未付款")) {
						String userid = list.get(0).get("user_id").toString();
						log.send("01115", "支付成功的回调：", "下一步");
						log.send("01115", "支付成功的回调：", list.get(0).get("pay_value").toString());
						double realmoney = Double.parseDouble(list.get(0).get("pay_price").toString());
						log.send("01115", "支付成功的回调：", realmoney);
						sql = "update order_management set pay_status='已付款',payorder_id='" + transaction_id
								+ "' where order_num='" + out_trade_no + "'";
						log.send("01115", "支付成功的回调：", sql);
						sqlUtil.sql_exec(sql);

						String type = list.get(0).get("pay_what").toString();
						String typename = "";
						if (type.startsWith("充值")) {
							sql = "update user_data set balance=balance+" + list.get(0).get("pay_value").toString()
									+ ",is_paymember=1 where id='" + userid + "'";
							log.send("01115", "支付成功的回调：", sql);
							sqlUtil.sql_exec(sql);
//							sql="insert into income_details (user_id,time,type,money,operation) values ('"+userid+"',now(),'充值',"+list.get(0).get("pay_value").toString()+",'已到账')";
//							sqlUtil.sql_exec(sql);
							typename = type;
						} else {

							if (Integer.parseInt(type) < 100) {
								String sqlnum = "select name from vip_price_table where id=" + type;
								log.send(DataType.basicType, "01162", "pay", sql);
								typename = sqlUtil.get_string(sqlnum);
							} else {
								String sqlnum = "select * from supvip_table where id=" + type;
								log.send(DataType.basicType, "01162", "pay", sql);
								typename = sqlUtil.get_string(sqlnum);
							}

							String reStr = "";
							sql = "select member_end_time from user_data where id=" + userid;
							String date = sqlUtil.get_string(sql);
							if (date.equals("")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								date = sdf.format(new Date());
							}
							if (type.equals("1")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date dt = sdf.parse(date);
								Calendar rightNow = Calendar.getInstance();
								rightNow.setTime(dt);
								rightNow.add(Calendar.MONTH, 12);
								Date dt1 = rightNow.getTime();
								reStr = sdf.format(dt1);
							} else if (type.equals("2")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date dt = sdf.parse(date);
								Calendar rightNow = Calendar.getInstance();
								rightNow.setTime(dt);
								rightNow.add(Calendar.MONTH, 3);
								Date dt1 = rightNow.getTime();
								reStr = sdf.format(dt1);
							} else if (type.equals("3")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date dt = sdf.parse(date);
								Calendar rightNow = Calendar.getInstance();
								rightNow.setTime(dt);
								rightNow.add(Calendar.MONTH, 1);
								Date dt1 = rightNow.getTime();
								reStr = sdf.format(dt1);
							} /*
								 * else if(type.equals("4") || type.equals("100")){ SimpleDateFormat sdf = new
								 * SimpleDateFormat("yyyy-MM-dd"); Date dt = sdf.parse(date); Calendar rightNow
								 * = Calendar.getInstance(); rightNow.setTime(dt); rightNow.add(Calendar.MONTH,
								 * 12); Date dt1 = rightNow.getTime(); reStr = sdf.format(dt1); }else{
								 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date dt =
								 * sdf.parse(date); Calendar rightNow = Calendar.getInstance();
								 * rightNow.setTime(dt); rightNow.add(Calendar.YEAR, 1000); Date dt1 =
								 * rightNow.getTime(); reStr = sdf.format(dt1); }
								 */

							sql = "update user_data set is_member=1,member_end_time='" + reStr + "' where id='" + userid
									+ "'";
							log.send("01115", "支付成功的回调：", sql);
							sqlUtil.sql_exec(sql);
						}
						share_agentinfo.pay_info(userid, realmoney, typename);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.send("01115", "支付失败的业务逻辑：", e);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			log.send("01115", "支付失败的业务逻辑：", "处理订单" + return_code);
			// 支付失败的业务逻辑
		}
	}

	/*
	 * public void sendAndroidPush(String sellerid,String orderid) {
	 * log.send("01115", "支付成功的回调：", sellerid); final String appKey
	 * ="05aa32a7f711e77ad2e1b80e"; final String masterSecret =
	 * "0623578f51c9b2fa1f4b207d"; ClientConfig config = ClientConfig.getInstance();
	 * // Setup the custom hostname config.setPushHostName("https://api.jpush.cn");
	 * JPushClient jpushClient = new JPushClient(masterSecret, appKey, null,
	 * config); //JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	 * // 解析消息类型 String msgType = "msg_type_img"; String message = "新订单："+orderid;
	 * log.send("01115", "支付成功的回调：", message); //com.tencent.xinge.Message message1
	 * = new com.tencent.xinge.Message(); PushPayload payload = null;
	 * switch(msgType) { case "msg_type_img":
	 * //message1.setType(com.tencent.xinge.Message.TYPE_MESSAGE); payload =
	 * PushPayload.newBuilder() .setPlatform(Platform.android())
	 * //.setAudience(Audience.tag("seller")) .setAudience(Audience.tag(sellerid))
	 * .setNotification(Notification.newBuilder() .setAlert(message)
	 * .addPlatformNotification(AndroidNotification.newBuilder()
	 * .setTitle("订单消息").build())
	 * .addPlatformNotification(IosNotification.newBuilder() .setBadge(1)
	 * .setSound("happy") .addExtra("from", "JPush") .build()) .build())
	 * .setMessage(cn.jpush.api.push.model.Message.content(message))
	 * .setOptions(Options.newBuilder() .setApnsProduction(true) .build()) .build();
	 * break; } // 开始极光推送 try { if(payload != null) { PushResult result =
	 * jpushClient.sendPush(payload); log.send("01115", "支付成功的回调：",
	 * "message: "+message+", result: "+result); } } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			sb.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			sb.setFeature("http://xml.org/sax/features/external-general-entities", false);
			sb.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			sb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map parseXmlToList(String strXML) throws Exception {
		Map data = new HashMap<>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		String FEATURE = null;
		try {

			FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
			documentBuilderFactory.setFeature(FEATURE, true);

			FEATURE = "http://xml.org/sax/features/external-general-entities";
			documentBuilderFactory.setFeature(FEATURE, false);

			FEATURE = "http://xml.org/sax/features/external-parameter-entities";
			documentBuilderFactory.setFeature(FEATURE, false);

			FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
			documentBuilderFactory.setFeature(FEATURE, false);

			documentBuilderFactory.setXIncludeAware(false);
			documentBuilderFactory.setExpandEntityReferences(false);

		} catch (ParserConfigurationException e) {
			// log.error("ParserConfigurationException was thrown. The feature '" +
			// FEATURE + "' is probably not supported by your XML processor.");

		}
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
		org.w3c.dom.Document doc = documentBuilder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getDocumentElement().getChildNodes();
		for (int idx = 0; idx < nodeList.getLength(); ++idx) {
			Node node = nodeList.item(idx);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				org.w3c.dom.Element element = (org.w3c.dom.Element) node;
				data.put(element.getNodeName(), element.getTextContent());
			}
		}
		try {
			stream.close();
		} catch (Exception ex) {

		}
		return data;
	}

}
