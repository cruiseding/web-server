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
import com.net.aipeng.redirect.resolverC.interface2.mA.aipengSqlUser_01201;

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

		}
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

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
		// 查询跑团活动
		case "search_group_activities":// 180629创建
			search_group_activities(arg);
			break;
		// 查询用户当天的跑步总里程
		case "search_today_mileage":// 170704创建
			search_today_mileage(arg);
			break;
		// 查询不看其动态的好友
		case "search_not_look_dynamics":// 180709创建
			search_not_look_dynamics(arg);
			break;
		// 爱碰周榜查询
		case "week_list1_search":// 180905创建
			week_list1_search(arg);
			break;
		case "week_list2_search":// 180905创建
			week_list2_search(arg);
			break;
		case "pay_details_search":// 180907创建
			pay_details_search(arg);
			break;
		case "cash_details_search":// 180909创建
			cash_details_search(arg);
			break;

		}
	}

	/*
	 * 爱碰提现明细查询---01201 180909创建 arg[2] user_id 用户id arg[3] time 提现时间 arg[4] status
	 * 提现状态
	 */
	private void cash_details_search(String[] arg) throws SQLException, IOException, ServletException {
		if ("全部".equals(arg[4])) {
			String sql = sqlmface.searchSqlface(-1, arg);
			logDetector.send(DataType.basicType, "01201", " 提现全部明细查询---sql:", sql);
			list1 = sqlUtil.get_list(sql);
			logDetector.send(DataType.basicType, "01201", " 提现全部明细查询---list1:", list1);
		} else {
			String sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01201", " 提现明细查询---sql:", sql);
			list1 = sqlUtil.get_list(sql);
			logDetector.send(DataType.basicType, "01201", " 提现明细查询---list1:", list1);
		}

		String sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01201", " 提现成功总数---sql:", sql);
		int sum = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01201", " 提现成功总数---sum:", sum);
		for (int i = 0; i < list1.size(); i++) {
			list1.get(i).put("sum", sum);
		}
		inOutUtil.return_ajax(JsonUtil.listToJson(list1));
	}

	/*
	 * 爱碰支出明细查询---01201 180907创建 arg[2] 用户id arg[3] 时间字符串
	 */
	private void pay_details_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", " 支出明细查询---sql:", sql);
		list1 = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01201", " 支出明细查询---list1:", list1);

		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01201", " 支出总数---sql:", sql);
		int sum = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01201", " 支出总数---sum:", sum);
		for (int i = 0; i < list1.size(); i++) {
			list1.get(i).put("sum", sum);
		}
		inOutUtil.return_ajax(JsonUtil.listToJson(list1));
	}

	/*
	 * 爱碰周榜富豪榜---01201 18/09/05创建
	 */
	private void week_list2_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01201", " 爱碰富豪榜查询---sql:", sql);
		list1 = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01201", " 爱碰富豪榜查询---list1:", list1);
		inOutUtil.return_ajax(JsonUtil.listToJson(list1));

	}

	/*
	 * 爱碰周榜女神榜---01201 18/09/05创建
	 */
	private void week_list1_search(String[] arg) throws SQLException, IOException,

			ServletException {
		String sql = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01201", "爱碰女神周榜查询---sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01201", " 爱碰女神周榜查询---list:", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	/**
	 * 查询不看其动态的好友------01198 arg[2] 用户id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_not_look_dynamics(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", " 查询不看其动态的好友look_friends---sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", " 查询不看其动态的好友look_friends---list:", list);

		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01198", " 查询不看其动态的好友look_user---sql:", sql);
		list1 = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", " 查询不看其动态的好友look_user---list:", list1);

		list.addAll(list1);
		logDetector.send(DataType.basicType, "01198", " 查询不看其动态的好友---list:", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	/**
	 * 查询用户当天的跑步总里程 arg[2] user_id 用户id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_today_mileage(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", " 查询用户当天的跑步总里程---sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", " 查询用户当天的跑步总里程---list:", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	/**
	 * 查询跑团活动 arg[2] team_id 跑团id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_group_activities(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询跑团活动---sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "查询跑团活动---list:", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

}
