package com.net.aipeng.redirect.resolverE.interface1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface1.mA.InOutManager;
import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyHelpManager;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.redirect.resolverE.interface2.agentSqlBoss_01066;
import com.net.aipeng.redirect.resolverE.interface4.JyHelpManager_boss;

public class agentInoutBoss_01066 extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	SqlManagerFace sqlmface = new agentSqlBoss_01066();

	// protected Main main;
	// OkHttp okhttp=new OkHttp();
	// OkHttp okhttp = new OkHttp();
	public agentInoutBoss_01066(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);

	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "bossand":
			bossand(arg);
			break;

		}
	}

	/**
	 * 增加用户 arg[0]:A-boss-add arg[1]:bossand
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 * 
	 */
	private void bossand(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(1, arg);
		logDetector.send("01152", "sql", sql);
		int a = sqlUtil.get_int(sql);
		logDetector.send("01152", "a", a);
		String jsonadd = "{\"1\":\"添加成功\"}";
		inOutUtil.return_ajax(jsonadd);

	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {

		// 1
		case "agent_add1":
			agent_add(arg);
			break;
		case "agent_mod1":
			agent_mod1(arg);
			break;
		case "agentfencheng_mod":
			agentfencheng_mod(arg);
			break;
		case "agent_tixian":
			agent_tixian(arg);
			break;

		}

	}

	private void agent_tixian(String[] arg) throws SQLException, IOException, ServletException {
		arg[2] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = "";
		String mon = "";
		int fenxiao = 0;
		String order_num = "";
		String sql0 = sqlmface.modSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01156", "searchinsearch()-sql0: ", sql0);
		int vbi = sqlUtil.get_int(sql0);
		logDetector.send(DataType.basicType, "01156", "searchinsearch()-account_num: ", vbi);
		if (vbi < Integer.parseInt(arg[3])) {
			String jsonadd = "fail";
			inOutUtil.return_ajax(jsonadd);
		} else {
			logDetector.send(DataType.basicType, "01156", "jy的值: ", new JyHelpManager_boss().order_create());
			order_num = new JyHelpManager_boss().order_create() + "" + JyHelpManager_boss.sb();
			logDetector.send(DataType.basicType, "01156", "推广提现订单号: ", order_num);
			arg[0] = order_num;
			sql0 = sqlmface.modSqlface(0, arg);
			sqlUtil.sql_exec(sql0);
			sql0 = sqlmface.modSqlface(1, arg);
			sqlUtil.sql_exec(sql0);
			String jsonadd = "success";
			// String jsonadd =
			// "{\"success\":\""+(vbi-Integer.parseInt(arg[3]))+"\",\"vbi\":\""+(vbi-Integer.parseInt(arg[3]))+"\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	private void agentfencheng_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "轮播图更改", sql);
		int a = sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("1");
	}

	private void agentfencheng_set(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fencheng_set:", sql);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "fencheng_set:", list);
		inOutUtil.return_list(list, "/uiface/boss/agent_fencheng_set.jsp");
	}

	private void agent_mod1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "代理对应等级修改", sql);
		int a = sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("1");
	}

	// 1
	private void agent_add(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "代理对应等级修改", sql);
		int a = sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("1");
	}

	/**
	 * arg[0] A-boss-mod arg[1] response_money arg[2] id
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 */
	/*
	 * private void response_money(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.modSqlface(0, arg); list =
	 * sqlUtil.get_list(sql); if (list.size() == 1) { log.send(DataType.basicType,
	 * "01158", "response_money()-list: ", list);
	 * 
	 * double realmoney = Double.parseDouble(list.get(0).get("cash") .toString());
	 * 
	 * AlipayClient alipayClient = new DefaultAlipayClient(
	 * "https://openapi.alipay.com/gateway.do", Ordercreat.APPID,
	 * Ordercreat.RSA2_PRIVATE, "json", "utf-8", Ordercreat.ALIPAY_PUBLIC_KEY,
	 * "RSA2"); AlipayFundTransToaccountTransferRequest request = new
	 * AlipayFundTransToaccountTransferRequest(); request.setBizContent("{" +
	 * "\"out_biz_no\":\"" + list.get(0).get("out_biz_no").toString() + "\"," +
	 * "\"payee_type\":\"ALIPAY_LOGONID\"," + "\"payee_account\":\"" +
	 * list.get(0).get("tixian_account").toString() + "\"," + "\"amount\":\"" +
	 * list.get(0).get("cash").toString() + "\"," + "\"payer_show_name\":\"悦币\"," +
	 * "\"payee_real_name\":\"\"," + "\"remark\":\"悦币提现\"" // + //
	 * "\"payee_real_name\":\"张三\"," + "}");
	 * AlipayFundTransToaccountTransferResponse response = null;
	 * log.send(DataType.basicType, "01158", "response_money()-response: ", 1); try
	 * { response = alipayClient.execute(request); } catch (AlipayApiException e) {
	 * // TODO Auto-generated catch block e.printStackTrace();
	 * log.send(DataType.basicType, "01158", "response_money()-response: ",
	 * e.toString()); } log.send(DataType.basicType, "01158",
	 * "response_money()-response: ", response.getParams());
	 * log.send(DataType.basicType, "01158", "response_money()-response: ",
	 * response.getBody() + response.getErrorCode() + response.getParams()); if
	 * (response.isSuccess()) { System.out.println("调用成功");
	 * 
	 * String code = response.getCode(); if (code.equals("10000")) { String[] newarg
	 * = new String[5]; newarg[0] = response.getOutBizNo(); newarg[1] = arg[1];
	 * newarg[2] = response.getOrderId(); newarg[3] = response.getPayDate();
	 * newarg[4] = response.getMsg(); sql = sqlmface.modSqlface(1, newarg);
	 * log.send(DataType.basicType, "01158", "response_money()-sql: ", sql); int i =
	 * sqlUtil.sql_exec(sql); log.send(DataType.basicType, "01158",
	 * "response_money()-i: ", i); String jsonadd = "提现成功！";
	 * log.send(DataType.basicType, "01158", "response_money()-jsonadd: ", jsonadd);
	 * 
	 * if (list.get(0).get("c_type").toString().equals("0")) {
	 * 
	 * ArrayList<Map<String, Object>> list2 = null; sql = "select * from cash_set ";
	 * log.send("01158", "sql", sql); list2 = sqlUtil.get_list(sql);
	 * 
	 * ArrayList<Map<String, Object>> list1 = null; // 一级分销 sql =
	 * "select promoter_id,twopromoter_id,up_agentid from user_data where id=" +
	 * list.get(0).get("user_id").toString(); log.send("01158", "sql", sql); list1 =
	 * sqlUtil.get_list(sql); if (list1.size() == 0) {
	 * 
	 * } else { String oneid = list1.get(0).get("promoter_id") .toString(); if
	 * (!oneid.equals("0")) { String isv = "0"; String sacleone = list2.get(0)
	 * .get("cash_onefee").toString(); Double oneable_money = Double
	 * .parseDouble(sacleone) * realmoney; sql =
	 * "insert into tuiguang_detail (upuser_id,downuser_id,is_dv,levle,money_type,money_num,scale_num,able_money,uptime) values ('"
	 * + oneid + "','" + list.get(0).get("user_id").toString() + "','" + isv +
	 * "',1,'提现','" + realmoney + "','" + sacleone + "','" + oneable_money +
	 * "',now())"; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); sql =
	 * "update user_data set invite_price = invite_price+" + oneable_money +
	 * ",ableinvite_price=ableinvite_price+" + oneable_money + " where id=" + oneid
	 * + " "; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); }
	 * 
	 * String twoid = list1.get(0).get("twopromoter_id") .toString(); if
	 * (!twoid.equals("0")) { String isv = "0"; String sacletwo = list2.get(0)
	 * .get("cash_twofee").toString(); Double twoable_money = Double
	 * .parseDouble(sacletwo) * realmoney; sql =
	 * "insert into tuiguang_detail (upuser_id,downuser_id,is_dv,levle,money_type,money_num,scale_num,able_money,uptime) values ('"
	 * + twoid + "','" + list.get(0).get("user_id").toString() + "','" + isv +
	 * "',1,'提现','" + realmoney + "','" + sacletwo + "','" + twoable_money +
	 * "',now())"; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); sql =
	 * "update user_data set invite_price = invite_price+" + twoable_money +
	 * ",ableinvite_price=ableinvite_price+" + twoable_money + " where id=" + twoid
	 * + " "; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); }
	 * 
	 * String up_agentid=list1.get(0).get("up_agentid").toString();
	 * if(!up_agentid.equals("0")){ //
	 * sql="select b.agent_percent from agentlist as a ,agent_set as b where a.agent_channelcode="
	 * +up_agentid+" and a.agent_level=b.agent_level";
	 * sql="select fencheng_v from agentlist   where agent_channelcode='"+up_agentid
	 * +"'"; String agent_fee=sqlUtil.get_string(sql);
	 * 
	 * //String agent_fee=listcast.get(0).get("agent_fee").toString(); String
	 * isv="0"; Double agentable_money=Double.parseDouble(agent_fee)*realmoney;
	 * sql="insert into agenttuiguang_detail (upuser_id,downuser_id,is_dv,levle,money_type,money_num,scale_num,able_money,uptime) values ('"
	 * +up_agentid+"','"+list.get(0).get("user_id").toString()+"','"+isv+
	 * "',1,'提现','"+realmoney+"','"+agent_fee+"','"+agentable_money+"',now())";
	 * log.send("01158", "sql", sql); sqlUtil.sql_exec(sql);
	 * sql="update agentlist set totle_money = totle_money+"+agentable_money+
	 * ",ablew_money=ablew_money+"+agentable_money+" where agent_channelcode='"
	 * +up_agentid+"' "; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); } } }
	 * 
	 * 
	 * //BizRenderTask send = new
	 * BizRenderTask(list.get(0).get("user_id").toString() +
	 * "卍您于"+list.get(0).get("time").toString()+"提交的 "+list.get(0).get("cash").
	 * toString()+" 元的提现申请已经通过，请注意查看！"); //send.run();
	 * 
	 * 
	 * inOutUtil.return_ajax(jsonadd); } else { String[] newarg = new String[3];
	 * newarg[0] = arg[2]; newarg[1] = arg[1]; newarg[2] = response.getSubMsg(); sql
	 * = sqlmface.modSqlface(2, newarg); log.send(DataType.basicType, "01158",
	 * "response_money()-sql: ", sql); int i = sqlUtil.sql_exec(sql);
	 * log.send(DataType.basicType, "01158", "response_money()-i: ", i); String
	 * jsonadd = newarg[2]; log.send(DataType.basicType, "01158",
	 * "response_money()-jsonadd: ", jsonadd); inOutUtil.return_ajax(jsonadd); }
	 * 
	 * } else { String[] newarg = new String[3]; newarg[0] = arg[2]; newarg[1] =
	 * arg[1]; newarg[2] = response.getSubMsg(); sql = sqlmface.modSqlface(2,
	 * newarg); log.send(DataType.basicType, "01158", "response_money()-sql: ",
	 * sql); int i = sqlUtil.sql_exec(sql); log.send(DataType.basicType, "01158",
	 * "response_money()-i: ", i); String jsonadd = newarg[2];
	 * log.send(DataType.basicType, "01158", "response_money()-jsonadd: ", jsonadd);
	 * inOutUtil.return_ajax(jsonadd); }
	 * 
	 * } else { String jsonadd = "没有此条提现明细或已提现！"; log.send(DataType.basicType,
	 * "01158", "response_money()-jsonadd: ", jsonadd);
	 * inOutUtil.return_ajax(jsonadd); }
	 * 
	 * }
	 */

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		case "admin_del":
			admin_del(arg);
			break;

		case "agent_delete":
			agent_delete(arg);
			break;

		}
	}

	private void admin_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void agent_delete(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {

		// 1
		case "acash_withdrawal":
			acash_withdrawal(arg);
			break;

		case "agentfencheng_set":
			agentfencheng_set(arg);
			break;

		// 1
		case "amemberbackstage":// 会员后台
			amemberbackstage(arg);
			break;
		// 1
		case "aanchor_search":// 会员后台
			aanchor_search(arg);
			break;

		// 1
		case "pay_search":
			pay_search(arg);
			break;
		// 1
		case "agentpay_table_search":
			agentpay_table_search(arg);
			break;
		// 1
		case "agentcash_withdrawl":
			agentcash_withdrawl(arg);
			break;

		// 1
		case "agentist1"://
			agentist1(arg);
			break;

		case "agent_modbf1"://
			agent_modbf1(arg);
			break;

		case "mymoney"://
			mymoney(arg);
			break;
		case "agent_analyse"://
			agent_analyse(arg);
			break;
		// 渠道商信息查询
		case "qds_search":
			qds_search(arg);
			break;
		}
	}

	public void qds_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String sum = "";
		String qds_name = "";
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-total: ", total);
		pages = JyHelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[0] = pages[2] + "";
		// arg[arg.length - 1] = JyHelpManager.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01170", "pay_table_search-sql2: ", list);
		if (list.size() > 0) {
			// 计算渠道商总数
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01170", "计算渠道商总数-sql: ", sql);
			sum = sqlUtil.get_string(sql);
			list.get(0).put("sum", sum);
		}
		/*
		 * for(int i=0;i<list.size();i++){ arg[0] = list.get(i).get("").toString();
		 * 
		 * 
		 * }
		 */

		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/qds_search.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	public void agent_analyse(String[] arg) throws SQLException, IOException, ServletException {

		arg[7] = request.getSession().getAttribute("channelcode").toString();
		if (arg[6].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			logDetector.send(DataType.basicType, "01162", "提现明细", pages);
			arg[2] = pages[2] + "";
			logDetector.send(DataType.basicType, "01162", "提现明细", arg);
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_analyse.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(5, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_analyse.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}

	}

	public void mymoney(String[] arg) throws SQLException, IOException, ServletException {
		arg[0] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = sqlmface.searchSqlface(0, arg);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql1);
		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	private void agentpay_table_search(String[] arg) throws SQLException, IOException, ServletException {
		arg[0] = request.getSession().getAttribute("channelcode").toString();
		if (arg[6].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			logDetector.send(DataType.basicType, "01162", "提现明细", pages);
			arg[2] = pages[2] + "";
			logDetector.send(DataType.basicType, "01162", "提现明细", arg);
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			String sum = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);
			if (list.size() > 0) {
				list.get(0).put("sum", sum);
			}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agentincome_table1.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(5, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			String sum = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);
			if (list.size() > 0) {
				list.get(0).put("sum", sum);
			}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agentincome_table1.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
	}

	public void agent_modbf1(String[] arg) throws SQLException, IOException, ServletException {

		String sql1 = sqlmface.searchSqlface(0, arg);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql1);
		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		inOutUtil.return_list(list, "/uiface/boss/agent_mod1.jsp");

	}

	// 1
	public void agentist1(String[] arg) throws SQLException, IOException, ServletException {
		if (arg[3].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "相册图片审核-无条件搜索", sql);
			list = sqlUtil.get_list(sql);
			if (arg[4].endsWith("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_list1.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(2, arg);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", sql);
			list = sqlUtil.get_list(sql);
			if (arg[4].endsWith("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_list1.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
	}

	// 1
	public void amemberbackstage(String[] arg) throws SQLException, IOException, ServletException {

		arg[0] = request.getSession().getAttribute("channelcode").toString();
		logDetector.send(DataType.basicType, "01162", "amemberbackstage中arg[0]的值", arg[0]);
		logDetector.send(DataType.basicType, "01162", "amemberbackstage中arg的值", arg);
		if (arg[6].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			logDetector.send(DataType.basicType, "01162", "提现明细", pages);
			arg[2] = pages[2] + "";
			logDetector.send(DataType.basicType, "01162", "提现明细", arg);
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_meberbackstage.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(5, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_meberbackstage.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}

//			String sql1 = sqlmface.searchSqlface(0, arg);
//			log.send(DataType.basicType, "01158", "求总条数SQL", sql1);
//			int total = sqlUtil.get_int(sql1);
//			log.send(DataType.basicType, "01158", "总条数Int", total);
//			pages = JyHelpManager_boss.pages(arg[2], total);
//			arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
//			arg[arg.length - 2] = pages[2] + "";
//			arg[arg.length - 1] = JyHelpManager_boss.item + "";
//			String sql2 = sqlmface.searchSqlface(1, arg);
//			log.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
//			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
//			// log.send(DataType.basicType, "01178", "获取到会员列表", list);
//			if ("tojsp".equals(arg[3])) {
//				inOutUtil.return_listpage(list, pages,
//						"/uiface/boss/agenttcqo_meberbackstage.jsp");
//			} else {
//				json = JsonUtil.listPageToJson(list, pages);
//				inOutUtil.return_ajax(json);
//			}

	}

	// 1
	public void aanchor_search(String[] arg) throws SQLException, IOException, ServletException {

//		    arg[0]=request.getSession().getAttribute("channelcode").toString();
//			String sql1 = sqlmface.searchSqlface(0, arg);
//			log.send(DataType.basicType, "01158", "求总条数SQL", sql1);
//			int total = sqlUtil.get_int(sql1);
//			log.send(DataType.basicType, "01158", "总条数Int", total);
//			pages = JyHelpManager_boss.pages(arg[2], total);
//			arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
//			arg[arg.length - 2] = pages[2] + "";
//			arg[arg.length - 1] = JyHelpManager_boss.item + "";
//			String sql2 = sqlmface.searchSqlface(1, arg);
//			log.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
//			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
//			// log.send(DataType.basicType, "01178", "获取到会员列表", list);
//			if ("tojsp".equals(arg[3])) {
//				inOutUtil.return_listpage(list, pages,
//						"/uiface/boss/agenttcqo_meberbackstage.jsp");
//			} else {
//				json = JsonUtil.listPageToJson(list, pages);
//				inOutUtil.return_ajax(json);
//			}

		arg[0] = request.getSession().getAttribute("channelcode").toString();

		if (arg[6].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			logDetector.send(DataType.basicType, "01162", "提现明细", pages);
			arg[2] = pages[2] + "";
			logDetector.send(DataType.basicType, "01162", "提现明细", arg);
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_zhubobackstage.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(5, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_zhubobackstage.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}

	}

	// 1
	public void pay_search(String[] arg) throws SQLException, IOException, ServletException {
		arg[0] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01158", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01158", "总条数Int", total);
		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agentpay_search.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	// 1
	public void agentcash_withdrawl(String[] arg) throws SQLException, IOException, ServletException {
		arg[0] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01178", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01178", "求总条数Int", total);

		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到cash_withdrawal表的SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01178", "获取到cash_withdrawal表的集合",
		// list);

		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_cashwithdrawal.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	// 1
	public void acash_withdrawal(String[] arg) throws SQLException, IOException, ServletException {

		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01178", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01178", "求总条数Int", total);

		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到cash_withdrawal表的SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01178", "获取到cash_withdrawal表的集合",
		// list);

		if ("tojsp".equals(arg[4])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agentcash_withdrawal1.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	/*
	 * //1 public void agentpay_table_search(String[] arg) throws SQLException,
	 * IOException, ServletException {
	 * arg[0]=request.getSession().getAttribute("channelcode").toString(); String
	 * sql1 = sqlmface.searchSqlface(0, arg); log.send(DataType.basicType, "01158",
	 * "求总条数SQL", sql1); int total = sqlUtil.get_int(sql1);
	 * log.send(DataType.basicType, "01158", "总条数Int", total); pages =
	 * JyHelpManager_boss.pages(arg[2], total); arg = Arrays.copyOfRange(arg, 0,
	 * arg.length + 2); arg[arg.length - 2] = pages[2] + ""; arg[arg.length - 1] =
	 * JyHelpManager_boss.item + ""; String sql2 = sqlmface.searchSqlface(1, arg);
	 * log.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
	 * ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2); //
	 * log.send(DataType.basicType, "01178", "获取到会员列表", list); if
	 * ("tojsp".equals(arg[3])) { inOutUtil.return_listpage(list, pages,
	 * "/uiface/boss/agentallpay_search.jsp"); } else { json =
	 * JsonUtil.listPageToJson(list, pages); inOutUtil.return_ajax(json); }
	 * 
	 * }
	 */

}