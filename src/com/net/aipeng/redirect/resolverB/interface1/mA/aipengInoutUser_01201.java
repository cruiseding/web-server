package com.net.aipeng.redirect.resolverB.interface1.mA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface1.mA.InOutManager;
import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.redirect.resolverB.interface2.mA.aipengSqlUser_01201;

public class aipengInoutUser_01201 extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	SqlManagerFace sqlmface = new aipengSqlUser_01201();

	public aipengInoutUser_01201(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 爱碰添加举报信息记录
		case "report_record_add":// 180911创建
			report_record_add(arg);
			break;
		// 爱碰添加举报信息记录
		case "blacklist_add":// 180912创建
			blacklist_add(arg);
			break;
		// 爱碰视频一对一收支明细统计
		case "video_to_one_pay_add":// 180912创建
			video_to_one_pay_add(arg);
			break;
		case "man_price_add":
			man_price_add(arg);
			break;
		// 按分统计通话记录明细
		case "call_record_per_minute":
			call_record_per_minute(arg);
			break;
		// 添加通话明细记录
		case "call_detail_record_add":
			call_detail_record_add(arg);
			break;
		}
	}

	/**
	 * 添加通话记录明细---01201 18/09/26创建 arg[2] 拨打者id arg[3] 接听者id arg[4] 通话类型 arg[5] 通话时间
	 * arg[6] 是否接通
	 *
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void call_detail_record_add(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", "添加通话明细记录---sql:", sql);
		int count = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01201", " 添加通话明细记录---int:", count);
		if (count != 0) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/**
	 * 按分统计通话记录明细---01201 18/09/26创建 arg[2] 拨打者id arg[3] 接听者id arg[4] 计费标准 arg[5]
	 * 通话类型
	 */
	private void call_record_per_minute(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", "按分添加通话记录---sql:", sql);
		int count = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01201", " 按分添加通话记录---int:", count);
		if (count != 0) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/*
	 * 男号价格添加---01201 18/09/16创建 arg[2] 男号id arg[3] 男号视频价格 arg[4] 男号语音价格 arg[5]
	 * 男号私信价格
	 */
	private void man_price_add(String[] arg) throws SQLException, IOException, ServletException {
		int count1 = 0, count2 = 0;
		String sql = sqlmface.addSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01201", "查询男号在角色表有无记录---sql:", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01201", " 查询男号在角色表有无记录---list1:", count);
		if (count != 0) {
			sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01201", "修改记录---sql:", sql);
			count1 = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "修改记录---count1:", count1);
		} else {
			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01201", " 添加记录---sql:", sql);
			count2 = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", " 添加记录---count2:", count2);

		}

		if (count1 != 0 || count2 != 0) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/*
	 * 一对一视频结束时，统计通话记录及收支明细 爱碰视频一对一收支明细统计---01201 18/09/16创建 arg[2] 男号id arg[3] 女号id
	 * arg[4] 通话时间 arg[5] 通话整分钟时间 arg[6] 通话总费用 arg[7] 女号总收入
	 */
	private void video_to_one_pay_add(String[] arg) throws SQLException, IOException, ServletException {

		if ("视频通话".equals(arg[8])) {
			String sql = sqlmface.addSqlface(-1, arg);
			logDetector.send(DataType.basicType, "01201", " 查询女号每分钟视频资费---sql:", sql);
			int videochat_price = 0;
			if (arg.length > 9) {
				videochat_price = Integer.parseInt(arg[9]);
			} else {
				videochat_price = sqlUtil.get_int(sql);
			}
			// int videochat_price = sqlUtil.get_int(sql);

			logDetector.send(DataType.basicType, "01201", " 查询女号每分钟视频资费---list1:", videochat_price);
			int time = Integer.parseInt(arg[5]);
			int sum = time * videochat_price;
			arg[6] = sum + "";

			sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01201", "添加通话记录---sql:", sql);
			int a = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加通话记录---a:", a);

			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01201", "添加收入明细记录---sql:", sql);
			int b = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加收入明细记录---b:", b);

//				sql = sqlmface.addSqlface(2, arg);
//				log.send(DataType.basicType, "01201", "查询女号总收入---sql:", sql);
//				int c = sqlUtil.get_int(sql);
//				log.send(DataType.basicType, "01201", "查询女号总收入---c:",
//						c);
//				arg[7]=c+sum+"";
//				sql = sqlmface.addSqlface(3, arg);
//				log.send(DataType.basicType, "01201", "修改女号总收入---sql:", sql);
//				int d = sqlUtil.sql_exec(sql);
//				log.send(DataType.basicType, "01201", "修改女号总收入---d:",
//						d);

			sql = sqlmface.addSqlface(4, arg);
			logDetector.send(DataType.basicType, "01201", "添加支出明细记录---sql:", sql);
			int e = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加支出明细记录---c:", e);

			if (a != 0 && b != 0 && e != 0) {// &&c!=0 && d!=0
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		} else {// 语音通话
			String sql = sqlmface.addSqlface(5, arg);
			logDetector.send(DataType.basicType, "01201", " 查询女号每分钟语音资费---sql:", sql);
			int videochat_price = 0;
			if (arg.length > 9) {
				videochat_price = Integer.parseInt(arg[9]);
			} else {
				videochat_price = sqlUtil.get_int(sql);
			}
			// int videochat_price = sqlUtil.get_int(sql);
			logDetector.send(DataType.basicType, "01201", " 查询女号每分钟语音资费---list1:", videochat_price);
			int time = Integer.parseInt(arg[5]);
			int sum = time * videochat_price;
			arg[6] = sum + "";

			sql = sqlmface.addSqlface(6, arg);
			logDetector.send(DataType.basicType, "01201", "添加通话记录---sql:", sql);
			int a = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加通话记录---a:", a);

			sql = sqlmface.addSqlface(7, arg);
			logDetector.send(DataType.basicType, "01201", "添加收入明细记录---sql:", sql);
			int b = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加收入明细记录---b:", b);

//				sql = sqlmface.addSqlface(8, arg);
//				log.send(DataType.basicType, "01201", "查询女号总收入---sql:", sql);
//				int c = sqlUtil.get_int(sql);
//				log.send(DataType.basicType, "01201", "查询女号总收入---c:",
//						c);
//				arg[7]=c+sum+"";
//				sql = sqlmface.addSqlface(9, arg);
//				log.send(DataType.basicType, "01201", "修改女号总收入---sql:", sql);
//				int d = sqlUtil.sql_exec(sql);
//				log.send(DataType.basicType, "01201", "修改女号总收入---d:",
//						d);

			sql = sqlmface.addSqlface(10, arg);
			logDetector.send(DataType.basicType, "01201", "添加支出明细记录---sql:", sql);
			int e = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加支出明细记录---c:", e);

			if (a != 0 && b != 0 && e != 0) {// &&c!=0 && d!=0
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		}

	}

	/*
	 * 黑名单添加---01201 180912创建 arg[2] user_id arg[3] black_id
	 */
	private void blacklist_add(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录或状态查询-sql", sql);
		String status = sqlUtil.get_string(sql);
		logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录或状态查询-status", status);
		int success = 0;
		if ("".equals(status)) {
			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录插入-sql", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录插入-success", success);
		} else if ("0".equals(status)) {
			sql = sqlmface.addSqlface(2, arg);
			logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录状态修改1-sql", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录状态修改1-success", success);
		} else if ("1".equals(status)) {
			sql = sqlmface.addSqlface(3, arg);
			logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录状态修改0-sql", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.specialType, "01201", "爱碰黑名单记录状态修改0-success", success);
		}

		if (success == 1) {
			// 修改成功
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			// 修改失败
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/*
	 * 爱碰添加举报信息记录---01201 180911创建 arg[2] 举报者id arg[3] 被举报者id arg[4] 举报原因 arg[5]
	 * 其他举报原因
	 */
	private void report_record_add(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.addSqlface(1, arg);
		logDetector.send(DataType.specialType, "01201", "爱碰添加举报信息记录-sql", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.specialType, "01201", "爱碰添加举报信息记录-success", success);

		// if (count == 0) {
		// String sql1 = sqlmface.addSqlface(2, arg);
		// log.send(DataType.specialType, "01201", "一键报名信息(添加)-sql1", sql1);
		// success1 = sqlUtil.sql_exec(sql1);
		// log.send(DataType.specialType, "01201", "一键报名信息-success1", success1);
		// } else {
		// String sql2 = sqlmface.addSqlface(3, arg);
		// log.send(DataType.specialType, "01201", "一键报名信息(修改)-sql2", sql2);
		// success2 = sqlUtil.sql_exec(sql2);
		// log.send(DataType.specialType, "01201", "一键报名信息-success2", success2);
		// }

		if (success == 1) {
			// 修改成功
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			// 修改失败
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 爱碰视频一对一按分钟扣费
		case "video_to_one_pay":// 180912创建
			video_to_one_pay(arg);
			break;
		// 发信息按条扣费
		case "message_one_pay":// 180922创建
			message_one_pay(arg);
			break;

		case "sximgvideopay":// 180922创建
			sximgvideopay(arg);
			break;

		}
	}

	/**
	 * 私信扣费---198 arg[2] id 发送者id arg[3] user_id 接收者id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sximgvideopay(String[] arg) throws SQLException, ServletException, IOException {

//		String sql = sqlmface.modSqlface(0, arg);
//		log.send(DataType.basicType, "01198", "对方设置的价格sql",sql);
//		list = sqlUtil.get_list(sql);
//		log.send(DataType.basicType, "01198", "对方设置的价格-list",list);
//
//		if(list.size()==0 || list==null){
//			arg[0]="0";
//		}else{
//			arg[0]=list.get(0).get("wordchat_price").toString();
//		}
		arg[0] = arg[4];

		String sql = "select balance,total_revenue,total_withdrawals from user_data where id=" + arg[2];
		list = sqlUtil.get_list(sql);
		int balance = Integer.parseInt(list.get(0).get("balance").toString());
		int total_revenue = Integer.parseInt(list.get(0).get("total_revenue").toString());
		int total_withdrawals = Integer.parseInt(list.get(0).get("total_withdrawals").toString());

		if ((balance + total_revenue - total_withdrawals) < Integer.parseInt(arg[0])) {
			String jsonadd = "{\"result\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
			return;
		}
		int success1 = 0;
		if (balance >= Integer.parseInt(arg[0])) {
			String sql1 = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "发送者减趣豆sql", sql1);
			success1 = sqlUtil.sql_exec(sql1);
			logDetector.send(DataType.basicType, "01198", "发送者减趣豆-success1", success1);
		} else {

			String b = arg[0];

			int a = Integer.parseInt(arg[0]) - balance;
			arg[0] = balance + "";
			String sql1 = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "发送者减趣豆sql", sql1);
			success1 = sqlUtil.sql_exec(sql1);
			logDetector.send(DataType.basicType, "01198", "发送者减趣豆-success1", success1);

			sql1 = "update user_data set total_revenue=total_revenue-'" + a + "' where id='" + arg[2] + "'";
			success1 = sqlUtil.sql_exec(sql1);
			logDetector.send(DataType.basicType, "01198", "发送者减趣豆-success1", success1);
			arg[0] = b;
		}

		String weeksql = "update user_data set week_inout=week_inout+" + arg[0] + " where id ='" + arg[2] + "' or id='"
				+ arg[3] + "'";
		sqlUtil.sql_exec(weeksql);

		String sql2 = sqlmface.modSqlface(2, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql2", sql2);
		int success2 = sqlUtil.sql_exec(sql2);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success2", success2);

		String sql3 = sqlmface.modSqlface(3, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql3", sql3);
		int success3 = sqlUtil.sql_exec(sql3);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success3", success3);

		String sql4 = sqlmface.modSqlface(4, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql4", sql4);
		int success4 = sqlUtil.sql_exec(sql4);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success4", success4);

		if (success1 == 1 && success2 == 1 && success3 == 1 && success4 == 1) {
			String jsonadd = "{\"result\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"result\":\"2\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/**
	 * 私信扣费---198 arg[2] id 发送者id arg[3] user_id 接收者id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void message_one_pay(String[] arg) throws SQLException, ServletException, IOException {

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "对方设置的价格sql", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "对方设置的价格-list", list);

		if (list.size() == 0 || list == null) {
			arg[0] = "0";
		} else {
			arg[0] = list.get(0).get("wordchat_price").toString();
		}

		sql = "select balance from user_data where id=" + arg[2];
		int balance = sqlUtil.get_int(sql);
		if (balance < Integer.parseInt(arg[0])) {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
			return;
		}

		String blacksql = "select count(*) from blacklist_table where user_id=" + arg[3] + " and black_id=" + arg[2]
				+ " and status=1";
		int a = sqlUtil.get_countint(blacksql);
		if (a > 0) {
			String jsonadd = "{\"success\":\"-1\"}";
			inOutUtil.return_ajax(jsonadd);
			return;
		}

		String weeksql = "update user_data set week_inout=week_inout+" + arg[0] + " where id ='" + arg[2] + "' or id='"
				+ arg[3] + "'";
		sqlUtil.sql_exec(weeksql);

		String sql1 = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01198", "发送者减趣豆sql", sql1);
		int success1 = sqlUtil.sql_exec(sql1);
		logDetector.send(DataType.basicType, "01198", "发送者减趣豆-success1", success1);

		String sql2 = sqlmface.modSqlface(2, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql2", sql2);
		int success2 = sqlUtil.sql_exec(sql2);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success2", success2);

		String sql3 = sqlmface.modSqlface(3, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql3", sql3);
		int success3 = sqlUtil.sql_exec(sql3);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success3", success3);

		String sql4 = sqlmface.modSqlface(4, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql4", sql4);
		int success4 = sqlUtil.sql_exec(sql4);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success4", success4);

		if (success1 == 1 && success2 == 1 && success3 == 1 && success4 == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/*
	 * 爱碰视频一对一按分钟扣费---01201 18/09/16创建 arg[2] 男号id arg[3] 主播id arg[4] 男号余额 arg[5]
	 * 女号视频价格/分
	 */
	private void video_to_one_pay(String[] arg) throws SQLException, IOException, ServletException {
		int sum;
		String sql = sqlmface.modSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01201", " 查询男号充值可用余额---sql:", sql);
		list1 = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01201", " 查询男号充值可用余额---list1:", list1);
		int balance = Integer.parseInt(list1.get(0).get("balance").toString());
		int videochat_price = Integer.parseInt(arg[5]);

		if (balance >= videochat_price) {
			sum = balance - videochat_price;
			arg[4] = sum + "";
			sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01201", " 修改男号可用余额---sql:", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", " 修改男号可用余额---success:", success);

			sql = "update user_data set total_revenue=total_revenue+" + arg[5] + " where id ='" + arg[3] + "'";
			success = sqlUtil.sql_exec(sql);

			String weeksql = "update user_data set week_inout=week_inout+" + arg[5] + " where id ='" + arg[2]
					+ "' or id='" + arg[3] + "'";
			sqlUtil.sql_exec(weeksql);

			String totle_minsql = "update user_data set totle_min=totle_min+1 where  id='" + arg[3] + "'";
			sqlUtil.sql_exec(totle_minsql);

			if (sum >= videochat_price) {
				String jsonadd = "{\"success\":\"1\"}";// 下一分钟够扣费
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"2\"}";// 下一分钟不够扣费
				inOutUtil.return_ajax(jsonadd);
			}

//			if(success==1){
//				list.get(0).put("status", "1");
//				list.get(0).put("success", "1");
//				inOutUtil.return_ajax(JsonUtil.listToJson(list));
//			}else{
//				list.get(0).put("status", "1");
//				list.get(0).put("success", "0");
//				inOutUtil.return_ajax(JsonUtil.listToJson(list));
//			}
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
//			list.get(0).put("status", "0");
//			list.get(0).put("success", "1");
//			inOutUtil.return_ajax(JsonUtil.listToJson(list));
		}

//		sql = sqlmface.searchSqlface(0, arg);
//		log.send(DataType.basicType, "01201", " 提现明细查询---sql:", sql);
//		list1 = sqlUtil.get_list(sql);
//		log.send(DataType.basicType, "01201", " 提现明细查询---list1:", list1);
//
//		sql = sqlmface.searchSqlface(1, arg);
//		log.send(DataType.basicType, "01201", " 提现成功总数---sql:", sql);
//		int sum = sqlUtil.get_int(sql);
//		log.send(DataType.basicType, "01201", " 提现成功总数---sum:", sum);
//		for (int i = 0; i < list1.size(); i++) {
//			list1.get(i).put("sum", sum);
//		}
//		inOutUtil.return_ajax(JsonUtil.listToJson(list1));

	}

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		}
	}

	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 查看女号手机
		case "look_cost_search":// 180918创建
			look_cost_search(arg);
			break;
		// 查询黑名单
		case "blacklist_search":// 180923创建
			blacklist_search(arg);
			break;
		case "msg_search":
			msg_search(arg);
			break;
		}
	}

	/**
	 * 查询私信页面信息 arg[2] 用户id arg[3] 他人id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void msg_search(String[] arg) throws SQLException, IOException, ServletException {
		list1 = new ArrayList<Map<String, Object>>();
		// 查询男号余额
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", " 黑名单查询---sql:", sql);
		String status = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01201", " 黑名单查询---list1:", status);

		if (status.length() > 0) {
			if ("0".equals(status)) {
				String jsonadd = "{\"status\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"status\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		} else {
			String jsonadd = "{\"status\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/*
	 * 黑名单查询---01201 18/9/23创建 arg[2] 用户id arg[3] 加入黑名单id
	 */
	private void blacklist_search(String[] arg) throws SQLException, IOException, ServletException {
		list1 = new ArrayList<Map<String, Object>>();
		// 查询男号余额
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", " 黑名单查询---sql:", sql);
		String status = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01201", " 黑名单查询---list1:", status);

		if (status.length() > 0) {
			if ("0".equals(status)) {
				String jsonadd = "{\"status\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"status\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		} else {
			String jsonadd = "{\"status\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	/*
	 * 查看女号手机---01201 180918创建 arg[2] 男号id arg[3] 女号id arg[4] 查看某某 arg[5] 男号余额(扣除后的)
	 * arg[6] 女号总收入
	 */
	private void look_cost_search(String[] arg) throws SQLException, IOException, ServletException {
		list1 = new ArrayList<Map<String, Object>>();
		// 查询男号余额
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", " 查询男号余额---sql:", sql);
		String yue = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01201", " 查询男号余额---list1:", yue);

		int balance = Integer.parseInt(yue) - 250;
//	    double d = Double.valueOf(yue);
//	    double dd = Math.round(d*100);
//	    double balance =(dd-25000)/100;
		arg[5] = balance + "";
		// 扣除男号余额
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01201", " 扣除男号余额---sql:", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01201", " 扣除男号余额---success:", success);

		// 查询女号总收入
		sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01201", "查询女号总收入---sql:", sql);
		String c = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01201", "查询女号总收入---c:", c);
		int ba = Integer.parseInt(c) + 250;
//	    double dc = Double.valueOf(c);
//	    double ddc = Math.round(dc*100);
//	    double balancec =(ddc+25000)/100;
		arg[6] = ba + "";

		sql = sqlmface.searchSqlface(3, arg);
		logDetector.send(DataType.basicType, "01201", "增加女号收入---sql:", sql);
		int suc = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01201", "增加女号收入---c:", c);

		if (success == 1 && suc == 1) {

			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01201", "添加支出记录---sql:", sql);
			int a = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加支出记录--ab:", a);

			sql = sqlmface.searchSqlface(5, arg);
			logDetector.send(DataType.basicType, "01201", "添加收益记录---sql:", sql);
			int b = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01201", "添加收益记录---b:", b);

			if ("查看手机".equals(arg[4])) {
				sql = sqlmface.searchSqlface(6, arg);
				logDetector.send(DataType.basicType, "01201", " 查询女号手机---sql:", sql);
				list = sqlUtil.get_list(sql);
				logDetector.send(DataType.basicType, "01201", " 查询女号手机---list:", list);
				if (a == 1 && b == 1 && list.size() != 0) {
					if (a == 1 && b == 1 && list.size() != 0) {
						list.get(0).put("qq", "");
						list.get(0).put("wechat", "");
						list.get(0).put("wechat_name", "");
						inOutUtil.return_ajax(JsonUtil.listToJson(list));
					}
				}
			} else if ("查看微信".equals(arg[4])) {
				sql = sqlmface.searchSqlface(7, arg);
				logDetector.send(DataType.basicType, "01201", " 查询女号微信---sql:", sql);
				list = sqlUtil.get_list(sql);
				logDetector.send(DataType.basicType, "01201", " 查询女号微信---list:", list);
				if (a == 1 && b == 1 && list.size() != 0) {
					list.get(0).put("qq", "");
					list.get(0).put("phone", "");
					inOutUtil.return_ajax(JsonUtil.listToJson(list));
				}
			} else if ("查看qq".equals(arg[4])) {
				sql = sqlmface.searchSqlface(8, arg);
				logDetector.send(DataType.basicType, "01201", " 查询女号qq---sql:", sql);
				list = sqlUtil.get_list(sql);
				logDetector.send(DataType.basicType, "01201", " 查询女号qq---list:", list);
				if (a == 1 && b == 1 && list.size() != 0) {
					list.get(0).put("phone", "");
					list.get(0).put("wechat", "");
					list.get(0).put("wechat_name", "");
					inOutUtil.return_ajax(JsonUtil.listToJson(list));
				}
			}

		}
	}
}
