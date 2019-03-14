package com.net.aipeng.redirect.resolverC.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;

public class aipengSqlUser_01198C extends SqlManager implements SqlManagerFace {

	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {

		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		// 修改未接来电为已查看
		case "mod_miss_call":
			if (current == 0) {
				ressql = "update call_detail_table set is_see='1' where target_id='" + arg[2] + "'";
			}
			break;
		// 删除通话记录
		case "delete_call_record":
			if (current == 0) {
				ressql = "update call_detail_table set is_delete_man='1' where send_id='" + arg[2] + "'";
			} else if (current == 1) {
				ressql = "update call_detail_table set is_delete='1' where target_id='" + arg[2] + "'";
			}
			break;
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
		// 查询未接来电
		case "miss_call":
			if (current == 0) {
				ressql = "select u.user_photo,u.nickname,c.* from call_detail_table as c Join user_data as u "
						+ "on c.send_id=u.id  where target_id='" + arg[2] + "' and is_call='0' ORDER BY c.time desc";
			}
			break;
		// 查询未接来电未查看数量
		case "miss_call_num":
			if (current == 0) {
				ressql = "select count(*) from call_detail_table where target_id='" + arg[2]
						+ "' and is_call='0' and is_see='0'";
			}
			break;
		}
		return ressql;
	}

}
