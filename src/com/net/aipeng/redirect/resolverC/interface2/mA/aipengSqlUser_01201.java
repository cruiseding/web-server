package com.net.aipeng.redirect.resolverC.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;

public class aipengSqlUser_01201 extends SqlManager implements SqlManagerFace {

	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {

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
		// 查询跑团活动
		case "search_group_activities":
			if (current == 0) {
				ressql = "select te.*,ru.runteam_name from team_activity_table te join runteam_table ru on te.team_id=ru.id "
						+ " where team_id='" + arg[2] + "'";
			}
			break;
		// 查询用户当天的跑步总里程
		case "search_today_mileage":
			if (current == 0) {
				ressql = "select *,sum(mileage) as today_mileage from run_table where user_id='" + arg[2] + "' "
						+ "and  end_time BETWEEN CAST(NOW() AS DATE) "
						+ "and (date_add(CAST(NOW() AS DATE), interval 1 day))";
			}
			break;
		// 查询不看其动态的好友
		case "search_not_look_dynamics":
			if (current == 0) {
				ressql = "SELECT u.id,u.user_photo,u.nickname from friends_table as f join "
						+ " user_data as u on f.friends_id=u.id "
						+ " where u.id=(SELECT f.friends_id FROM friends_table " + " where f.user_id='" + arg[2]
						+ "' AND f.look_friends='0' GROUP BY f.friends_id)";
			} else if (current == 1) {
				ressql = "SELECT u.id,u.user_photo,u.nickname from friends_table as f join "
						+ " user_data as u on f.user_id=u.id " + " where u.id=(SELECT f.user_id FROM friends_table "
						+ "where f.friends_id='" + arg[2] + "' AND f.look_user='0'  GROUP BY f.user_id)";
			}
			break;
		// 爱碰周榜女神榜查询
		case "week_list1_search":
			if (current == -1) {
//				ressql = "SELECT l.user_id,u.nickname,u.user_photo,l.week_inout from list_table l,user_data u "
//						+ "where u.id=l.user_id and l.user_type=0 order by "
//						+ "l.week_inout desc";
				ressql = "SELECT id as user_id,nickname,user_photo,week_inout from user_data  "
						+ "where is_goddess=1 order by " + "week_inout desc limit 0,10";
			}
			break;
		// 爱碰周榜富豪榜查询
		case "week_list2_search":
			if (current == 0) {
//				ressql = "SELECT l.user_id,u.nickname,u.user_photo,l.week_inout from list_table l,user_data u "
//						+ "where u.id=l.user_id and l.user_type=1 order by "
//						+ "l.week_inout desc";
				ressql = "SELECT id as user_id,nickname,user_photo,week_inout from user_data  "
						+ "where is_goddess=0 and gender='男' order by " + "week_inout desc limit 0,10";
			}
			break;
		// 爱碰支出明细查询
		case "pay_details_search":
			if (current == 0) {
				ressql = "SELECT p.*,u.nickname from pay_table p,user_data u "
						+ "where p.target_id=u.id and date_format('" + arg[3]
						+ "', '%Y%m')=date_format(p.time, '%Y%m') " + "and p.user_id='" + arg[2]
						+ "' order by p.time desc";
				// arg[3] 形如2018-09-00格式
			} else if (current == 1) {
				ressql = "SELECT sum(num) from pay_table " + "where date_format('" + arg[3]
						+ "', '%Y%m')=date_format(time, '%Y%m') " + "and user_id='" + arg[2] + "'";
			}
			break;
		// 爱碰支出明细查询
		case "cash_details_search":
			if (current == -1) {
				ressql = "SELECT * from cash_table " + "where date_format('" + arg[3]
						+ "', '%Y%m')=date_format(time, '%Y%m') " + "and user_id='" + arg[2] + "' order by time desc";
			} else if (current == 0) {
				ressql = "SELECT * from cash_table " + "where date_format('" + arg[3]
						+ "', '%Y%m')=date_format(time, '%Y%m') " + "and user_id='" + arg[2] + "' and status='" + arg[4]
						+ "' " + "order by time desc";
				// arg[3] 形如2018-09-00格式
			} else if (current == 1) {
				ressql = "SELECT sum(cash) from cash_table " + "where date_format('" + arg[3]
						+ "', '%Y%m')=date_format(time, '%Y%m') " + "and user_id='" + arg[2] + "' and status='提现成功' ";
			}
			break;
		}
		return ressql;
	}
}
