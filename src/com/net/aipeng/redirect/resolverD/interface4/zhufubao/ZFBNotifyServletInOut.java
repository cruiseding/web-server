package com.net.aipeng.redirect.resolverD.interface4.zhufubao;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.SqlUtil;
import com.net.aipeng.redirect.resolverD.interface1.share_agentinfo;

//import com.net.feimiaoquan.classroot.interface4.JyGlobalConstant;
//import com.net.feimiaoquan.classroot.interface4.JyLogDetect;
//import com.net.feimiaoquan.classroot.interface4.SqlUtil;

@WebServlet("/zfbpay/notify")
public class ZFBNotifyServletInOut extends HttpServlet {

	JyLogDetect log = null;

	public ZFBNotifyServletInOut() {
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
		// 获取到返回的所有参数 先判断是否交易成功trade_status 再做签名校验
		// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		// 3、校验通知中的seller_id（或者seller_email)
		// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
		// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
		log.send("01115", "trade_status：", request.getParameter("trade_status"));
		if ("TRADE_SUCCESS".equals(request.getParameter("trade_status"))) {
//            Enumeration<?> pNames = request.getParameterNames();
//            Map<String, String> param = new HashMap<String, String>();
//            try {
//                while (pNames.hasMoreElements()) {
//                    String pName = (String) pNames.nextElement();
//                    if(pName.equals("sign") || pName.equals("sign_type")){
//                    	
//                    }else{
//                    	param.put(pName, request.getParameter(pName));
//                    }
//                }
//                log.send("01115", "param：", param);
//                boolean signVerified = AlipaySignature.rsaCheckV1(param, Ordercreat.ALIPAY_PUBLIC_KEY,
//                        AlipayConstants.CHARSET_UTF8); // 校验签名是否正确
//                log.send("01115", "signVerified：", signVerified);
//                if (signVerified) {
//                    // TODO 验签成功后
//                    // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
//                    //LOG.info("订单支付成功：" + JSON.toJSONString(param)); 
//                } else {
//                    // TODO 验签失败则记录异常日志，并在response中返回failure.
//                }
//            } catch (Exception e) {
//            	log.send("01115", "signVerified：", e);
//                e.printStackTrace();
//            }

			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			log.send("01115", "param：", params);
			// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
			// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
			// publicKey, String charset, String sign_type)
			try {
				boolean flag = AlipaySignature.rsaCheckV1(params, Ordercreat.ALIPAY_PUBLIC_KEY,
						AlipayConstants.CHARSET_UTF8, "RSA2");
				log.send("01115", "flag：", flag);

				response.getWriter().write("success");
				flag = true;
				if (flag) {

					if (!Ordercreat.APPID.equals(request.getParameter("app_id").toString())) {
						return;
					}

					String out_trade_no = request.getParameter("out_trade_no").toString();
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
							sql = "update order_management set pay_status='已付款',payorder_id='"
									+ request.getParameter("trade_no").toString() + "' where order_num='" + out_trade_no
									+ "'";
							log.send("01115", "支付成功的回调：", sql);
							sqlUtil.sql_exec(sql);

							String type = list.get(0).get("pay_what").toString();
							String typename = "";
							if (type.startsWith("充值")) {
								sql = "update user_data set balance=balance+" + list.get(0).get("pay_value").toString()
										+ ",is_paymember=1 where id='" + userid + "'";
								log.send("01115", "支付成功的回调：", sql);
								sqlUtil.sql_exec(sql);
//								sql="insert into income_details (user_id,time,type,money,operation) values ('"+userid+"',now(),'充值',"+list.get(0).get("pay_value").toString()+",'已到账')";
//								sqlUtil.sql_exec(sql);
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

								sql = "update user_data set is_member=1,member_end_time='" + reStr + "' where id='"
										+ userid + "'";
								log.send("01115", "支付成功的回调：", sql);
								sqlUtil.sql_exec(sql);
							}
							share_agentinfo.pay_info(userid, realmoney, typename);
						}
					}

				} else {
					// TODO 验签失败则记录异常日志，并在response中返回failure.
				}
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Map parseXmlToList(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
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

}
