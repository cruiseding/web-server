package com.net.aipeng.redirect.resolverC.interface1.mA;

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
import com.net.aipeng.redirect.resolverC.interface2.mA.aipengSqlUser_01198C;

public class aipengInoutUser_01198C extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	SqlManagerFace sqlmface = new aipengSqlUser_01198C();

	public aipengInoutUser_01198C(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		}
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 修改未接来电为已查看
		case "mod_miss_call":// 180926
			mod_miss_call(arg);
			break;
		// 删除通话记录
		case "delete_call_record":// 180927创建
			delete_call_record(arg);
			break;
		}
	}

	private void delete_call_record(String[] arg) throws SQLException, ServletException, IOException {
		if ("男".equals(arg[3])) {
			String sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01198", "删除通话记录-sql", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01198", "删除通话记录-success", success);
			if (success >= 0) {
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		} else {
			String sql = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "删除通话记录-sql", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01198", "删除通话记录-success", success);
			if (success >= 0) {
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		}

	}

	/**
	 * 修改未接来电为已查看------01198
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void mod_miss_call(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "修改未接来电为已查看sql", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01198", "修改未接来电为已查看-success", success);
		if (success == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		}
	}

	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 查询未接来电
		case "miss_call":// 180926创建
			miss_call(arg);
			break;
		// 查询未接来电未查看数量
		case "miss_call_num":// 180926
			miss_call_num(arg);
			break;
		}
	}

	/**
	 * 查询未接来电未查看数量----01198
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void miss_call_num(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询未接来电未查看数量sql", sql);
		int num = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01198", "查询未接来电未查看数量-list", list);

		String jsonadd = "{\"success\":\"" + num + "\"}";
		inOutUtil.return_ajax(jsonadd);

	}

	/**
	 * 查询未接来电------01198 arg[2] target_id 接听者id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void miss_call(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询未接来电sql", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "查询未接来电-list", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

}
