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
import com.net.aipeng.redirect.resolverC.interface2.mA.aipengSqlUser_C_01215;

public class aipengInoutUser_C_01215 extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected ArrayList<Map<String, Object>> list3;
	protected ArrayList<Map<String, Object>> list4;
	protected ArrayList<Map<String, Object>> list5;
	protected ArrayList<Map<String, Object>> list6;
	protected ArrayList<Map<String, Object>> list7;
	protected ArrayList<Map<String, Object>> listzong;
	protected String json = "";
	SqlManagerFace sqlmface = new aipengSqlUser_C_01215();

	public aipengInoutUser_C_01215(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "add_yijianfankui":
			add_yijianfankui(arg);
			break;

		default:
			break;
		}
	}

	private void add_yijianfankui(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		logDetector.send(DataType.basicType, "01215", "女神认证--arg[]:", arg);
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01215", "女神认证--sql:", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01215", "女神认证--success:", success);
		if (success == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case "shenhe_gerenxinxi":
			shenhe_gerenxinxi(arg);
			break;

		default:
			break;
		}

	}

	private void shenhe_gerenxinxi(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01215", "账号绑定---sql:", sql);
		int success = sqlUtil.sql_exec(sql);
		if (success == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	// arg[2]用户需求id
	private void delete_need(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "账号绑定---sql:", sql);
		int success = sqlUtil.sql_exec(sql);
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
		default:
			break;
		}
	}

	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case "gerenxinxi":
			gerenxinxi(arg);
			break;
		case "se_kefuweixin":
			se_kefuweixin(arg);
			break;
		case "se_namestate":
			se_namestate(arg);
			break;
		default:
			break;
		}
	}

	private void se_namestate(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send("01215", "实名认证", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send("01215", "实名认证", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	private void se_kefuweixin(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send("01215", "用户个人信息", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send("01215", "用户个人信息", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	private void gerenxinxi(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		// arg=Arrays.copyOf(arg, arg.length+8);
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send("01215", "用户个人信息", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send("01215", "用户个人信息", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

}
