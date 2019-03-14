package com.net.aipeng.redirect.resolverB.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;

public class aipengSqlUser_01198B extends SqlManager implements SqlManagerFace {

	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		// 用户提现
		case "add_withdrawals":
			if (current == 0) {
				ressql = "insert into cash_table (user_id,cash,content,time) " + "values(" + arg[2] + ",'" + arg[3]
						+ "','" + arg[4] + "',Now())";
			}
			break;
		case "pushp2pvideo":
			if (current == 0) {
				ressql = "delete from videorequest where zhuboid='" + arg[6] + "' and gukeid='" + arg[3] + "'";
			} else if (current == 1) {
				ressql = "insert into videorequest (zhuboid,gukeid,gukename,gukepic,roomid,calltype) values ('" + arg[6]
						+ "','" + arg[3] + "','" + arg[4] + "','" + arg[5] + "','" + arg[7] + "','" + arg[9] + "')";
			}
			break;

		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		// 修改女神状态(通话状态)
		case "updateConversationStatus":
			if (current == 0) {
				ressql = "update user_data set is_online='4' where id='" + arg[2] + "'";
			} else if (current == 1) {
				ressql = "update user_data set is_online='1' where id='" + arg[2] + "'";
			}
			break;
		// 发信息按条扣费
		case "message_one_pay":
			if (current == 0) {
				ressql = "select * from roles_table where user_id='" + arg[3] + "'";
			} else if (current == 1) {
				ressql = "update user_data set balance=balance-'" + arg[0] + "' where id='" + arg[2] + "'";
			} else if (current == 2) {
				ressql = "update user_data set total_revenue=total_revenue+'" + arg[0] + "' where id='" + arg[3] + "'";
			} else if (current == 3) {
				ressql = "insert into Income_details_table (user_id,pay_id,type,money,time,operation) " + "values('"
						+ arg[3] + "','" + arg[2] + "','发送消息','" + arg[0] + "',now(),'已到账')";
			} else if (current == 4) {
				ressql = "insert into pay_table (user_id,target_id,type,num,time) " + "values('" + arg[2] + "','"
						+ arg[3] + "','发送消息','" + arg[0] + "',now())";
			}
			break;
		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
		switch (arg[1]) {
		case "removep2pvideo":
			// ressql = "delete from videorequest where zhuboid='"+arg[4]+"' and
			// gukeid='"+arg[3]+"'";
			ressql = "delete from videorequest where roomid='" + arg[3] + "'";
			break;
		}
		return ressql;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		// 查询充值爱豆数量和价格等详情
		case "search_love_bean_details":
			if (current == 0) {
				ressql = "select * from love_bean_table";
			}
			break;
		// 查询充值记录
		case "search_recharge_record":
			if (current == 0) {
				ressql = "select * from order_management where user_id='" + arg[2] + "' and pay_status='已付款'";
			}
			break;
		case "checkroominfo":
			ressql = "select * from videorequest where zhuboid='" + arg[3] + "' and roomid='" + arg[4] + "'";
			break;
		}
		return ressql;
	}

}
