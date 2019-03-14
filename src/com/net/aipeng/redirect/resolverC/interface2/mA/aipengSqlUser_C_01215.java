package com.net.aipeng.redirect.resolverC.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;

public class aipengSqlUser_C_01215 extends SqlManager implements SqlManagerFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "add_yijianfankui":
			if (current == 0) {
				ressql = "insert into feedback_table (user_id,opinion_content,time) values ('" + arg[2] + "','" + arg[3]
						+ "',now())";
			}
			break;
		default:
			break;
		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "shenhe_gerenxinxi":
			if (current == 0) {
				ressql = "update check_list set photo='" + arg[4] + "',nickname='" + arg[5] + "',age='" + arg[6] + "',"
						+ "city='" + arg[7] + "',photo_album='" + arg[8] + "',signature='" + arg[9] + "'  where id='"
						+ arg[2] + "'";
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
		case "gerenxinxi":
			if (current == 0) {
				ressql = "select nickname,user_photo,gender,age,address,signature from user_data where id='" + arg[2]
						+ "'";
			}
			break;
		case "se_kefuweixin":
			if (current == 0) {
				ressql = "select weixin,tel from contact_customer_service where user_gender=(select gender from user_data where id='"
						+ arg[2] + "')";
			}
			break;
		case "se_namestate":
			if (current == 0) {
				ressql = "select stat from card_table where user_id='" + arg[2] + "' order by id desc limit 0,1";
			}
			break;
		}
		return ressql;
		// seCarousel search type(聊场）
		// http://120.27.98.128:9810/uiface/memberC01215?mode0=A-user-search&mode1=seCarousel&mode2=聊场

	}

}
