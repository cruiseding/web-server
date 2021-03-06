package com.net.aipeng.redirect.resolverD.interface2;

import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;

//import com.net.feimiaoquan.classroot.interface2.mA.fmqSqlMFace;
//import com.net.feimiaoquan.classroot.interface2.mA.fmqSqlManager;
//import com.net.feimiaoquan.classroot.interface4.JyLogDetect.DataType;

public class PaySqlUser extends SqlManager implements SqlManagerFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {
		case "zfbpay":
			if (current == 0) {
				ressql = "insert into order_management (user_id,order_num,pay_time,pay_type,pay_price,pay_what,pay_value,pay_status) values("
						+ arg[2] + ",'" + arg[0] + "',now(),'" + arg[5] + "','" + arg[4] + "','" + arg[3] + "','"
						+ arg[6] + "','未付款') ";
			} else if (current == 1) {
				ressql = "select * from order_management where order_num='" + arg[0] + "'";
			}
			break;
		case "vip_zfbpay":
			if (current == 0) {
				ressql = "insert into order_management (user_id,order_num,pay_time,pay_type,pay_price,pay_what,pay_value,pay_status) values("
						+ arg[2] + ",'" + arg[0] + "',now(),'" + arg[5] + "','" + arg[4] + "','" + arg[3] + "','"
						+ arg[6] + "','未付款') ";
			} else if (current == 1) {
				ressql = "select * from order_management where order_num='" + arg[0] + "'";
			}

		}
		log.send(DataType.specialType, "01162", arg[1], ressql);
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {

		switch (arg[1]) {

		}

		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {

		}
		log.send(DataType.specialType, "01162", arg[1], ressql);
		return ressql;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {

		case "myself":
			break;

		}

		log.send(DataType.specialType, "01162", arg[1], ressql);
		return ressql;
	}

}
