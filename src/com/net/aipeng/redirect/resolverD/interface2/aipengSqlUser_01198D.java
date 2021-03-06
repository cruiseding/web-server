package com.net.aipeng.redirect.resolverD.interface2;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;

public class aipengSqlUser_01198D extends SqlManager implements SqlManagerFace {

	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		// 申请提现
		case "cash_withdrawal":
			if (current == 0) {
				ressql = "insert into tgcash_table (user_id,out_biz_no,cash,status,time,c_type) " + "values (" + arg[2]
						+ ",'" + arg[0] + "'," + arg[3] + ",'申请中',now(),'1')";
			} else if (current == 1) {
				ressql = "update user_data set ableinvite_price=ableinvite_price-'" + arg[3] + "' where id='" + arg[2]
						+ "'";
			}
			break;
		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {

		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
		switch (arg[1]) {

		}
		return ressql;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		// 查询分享提现记录
		case "search_withdrawal_record":
			if (current == 0) {
				ressql = "select * from tgcash_table where user_id='" + arg[2] + "' ORDER BY time desc limit 0,100";
			}
			break;
		// 查询奖励明细
		case "search_reward_details":
			if (current == 0) {
				ressql = "select c.*,u.nickname from tuiguang_detail as c join user_data as u on c.downuser_id=u.id "
						+ "and c.upuser_id='" + arg[2] + "' ORDER BY uptime desc limit 0,100";
			}
			break;
		// 查询推广信息
		case "search_popularize":
			if (current == 0) {
				ressql = "select invite_price,ableinvite_price from user_data where id='" + arg[2] + "'";
			} else if (current == 1) {
				ressql = "select count(*) as count1 from user_data where promoter_id='" + arg[2] + "'";
			} else if (current == 2) {
				ressql = "select count(*) as count2 from user_data where promoter_id='" + arg[2] + "' and gender='男'";
			} else if (current == 3) {
				ressql = "select count(*) as count3 from user_data where promoter_id='" + arg[2] + "' and gender='女'";
			} else if (current == 4) {
				ressql = "select sum(able_money) as count4 from tuiguang_detail where upuser_id='" + arg[2]
						+ "' and uptime like '%" + arg[3] + "%'";
			}
			break;
		// 查询邀请的男/女用户信息
		case "search_invited":
			if (current == 0) {
				ressql = "select user_id,nickname,registered_time from user_data  where promoter_id='" + arg[2]
						+ "' and gender='" + arg[3] + "' " + "ORDER BY registered_time desc limit 0,100";
			}
			break;
		}
		return ressql;
	}

}
