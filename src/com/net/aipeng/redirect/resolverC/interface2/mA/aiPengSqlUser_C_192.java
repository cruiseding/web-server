package com.net.aipeng.redirect.resolverC.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;

public class aiPengSqlUser_C_192 extends SqlManager implements SqlManagerFace {

	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		case "update_Vprice":
			if (current == 0) {
				ressql = "update  roles_table set videochat_price='" + arg[3] + "'  where user_id=" + arg[2] + "";
			} else if (current == 1) {
				ressql = "SELECT COUNT(*) FROM roles_table WHERE user_id=" + arg[2];
			} else if (current == 2) {
				ressql = "INSERT INTO roles_table (user_id,videochat_price) VALUES (" + arg[2] + "," + arg[3] + ")";
			}
			break;
		case "update_Iprice":
			if (current == 0) {
				ressql = "update  roles_table set voicechat_price='" + arg[3] + "' where user_id=" + arg[2] + "";
			} else if (current == 1) {
				ressql = "SELECT COUNT(*) FROM roles_table WHERE user_id=" + arg[2];
			} else if (current == 2) {
				ressql = "INSERT INTO roles_table (user_id,voicechat_price) VALUES (" + arg[2] + "," + arg[3] + ")";
			}
			break;
		case "update_Mprice":
			if (current == 0) {
				ressql = "update  roles_table set wordchat_price='" + arg[3] + "'  where user_id=" + arg[2] + "";
			} else if (current == 1) {
				ressql = "SELECT COUNT(*) FROM roles_table WHERE user_id=" + arg[2];
			} else if (current == 2) {
				ressql = "INSERT INTO roles_table (user_id,wordchat_price) VALUES (" + arg[2] + "," + arg[3] + ")";
			}
			break;
		case "mod_is_type":
			if (current == 0) {
				ressql = "UPDATE user_data set " + arg[3] + "= " + arg[4] + " WHERE id=" + arg[2];
			}
			break;

		default:
			break;
		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		case "search_msg_list":
			if (current == 0) {
				ressql = "SELECT   m.message,m.time,u.user_photo,u.nickname,u.id  from message_detail_table m left JOIN user_data u on m.send_id=u.id where target_id="
						+ arg[2] + "  GROUP BY m.send_id order by (m.time) desc";
			}
			break;
		case "search_call_list":
			if (current == 0) {
				ressql = "SELECT c.minutes ,c.is_call,c.type,c.time,u.user_photo,u.nickname,u.id from call_detail_table c left JOIN user_data u on c.target_id=u.id where send_id="
						+ arg[2] + " and is_delete_man='0' order by c.time desc ";
			} else if (current == 1) {
				ressql = "SELECT c.minutes ,c.is_call,c.type,c.time,u.user_photo,u.nickname,u.id from call_detail_table c left JOIN user_data u on c.send_id=u.id where target_id="
						+ arg[2] + " and is_delete='0' order by c.time desc ";
			}
			break;
		case "search_income_list":
			if (current == 0) {
				ressql = "SELECT  i.type,i.money,i.time,u.nickname from Income_details_table i,user_data u "
						+ "WHERE i.pay_id=u.id and i.user_id='" + arg[2] + "'" + "and i.time like '%" + arg[3]
						+ "%' order by i.time desc";
			}
			break;

		case "sum_income":
			if (current == 0) {
				ressql = "select sum(money)  as money from Income_details_table where user_id='" + arg[2]
						+ "' and  time LIKE '%" + arg[3] + "%'";
			}
			break;
		case "search_income_today":
			if (current == 0) {
				ressql = "SELECT income_today FROM roles_table  where  user_id=" + arg[2] + "";
			}
			break;
		case "search_balance":
			if (current == 0) {
				ressql = "select sum(money)  as money from Income_details_table where user_id=" + arg[2] + "";
			}
			break;
		case "search_info":
			if (current == 0) {
				ressql = "SELECT * from  user_data  where id=" + arg[2];
			} else if (current == 1) {// 查看进日收益
				ressql = "SELECT CURDATE()";
			} else if (current == 2) {
				ressql = "SELECT SUM(money) FROM Income_details_table WHERE user_id= " + arg[2] + " AND "
						+ "time LIKE '%" + arg[0] + "%'";
			} else if (current == 3) {
				ressql = "SELECT  b.videochat_price,b.voicechat_price,b.wordchat_price "
						+ "FROM user_data as a,roles_table as b WHERE a.id = b.user_id AND a.id=" + arg[2];
			} else if (current == 4) {
				ressql = "";
			}
			break;
		case "search_price":
			if (current == 0) {
				ressql = "SELECT videochat_price,voicechat_price,wordchat_price from  roles_table  where user_id="
						+ arg[2] + "";
			}
			break;

		default:
			break;

		}
		return ressql;
	}

}
