package com.net.aipeng.redirect.resolverC.interface1.mA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface1.mA.InOutManager;
import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.redirect.resolverC.interface2.mA.aiPengSqlUser_C_192;

public class aiPengInoutUser_192_C extends InOutManager implements InOutFace {
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
	SqlManagerFace sqlmface = new aiPengSqlUser_C_192();

	public aiPengInoutUser_192_C(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
			default:
				break;
		}
	}

	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case "update_Vprice":
			update_Vprice(arg);
			break;
		case "update_Iprice":
			update_Iprice(arg);
			break;
		case "update_Mprice":
			update_Mprice(arg);
			break;
		case "mod_is_type":
			mod_is_type(arg);
			break;
		default:
			break;
		}

	}

	/**
	 * mod_is_type 修改是否接听 arg[2] user_id arg[3] type arg[4] is_type
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	private void mod_is_type(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String jsonadd = "";
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "修改是否接听:", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01192", "修改是否接听---success:", success);
		if (success == 0) {
			jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	/**
	 * 修改视频聊天价格 arg[2] user_id arg[3] price
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void update_Vprice(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		int success = 0;
		// 查询用户是否已提交
		sql = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01192", "查询用户是否已提交视频价格:", sql);
		int is_submit = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01192", "查询用户是否已提交视频价格----is_submit:", is_submit);
		if (is_submit == 1) {
			sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01192", "修改用户视频价格:", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01192", "修改用户视频价格----success", success);
		} else {
			sql = sqlmface.modSqlface(2, arg);
			logDetector.send(DataType.basicType, "01192", "创建用户视频价格:", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01192", "创建用户视频价格----success", success);
		}
		if (success == 0) {
			jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	/**
	 * 修改语音聊天价格 arg[2] user_id arg[3] price
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void update_Iprice(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		int success = 0;
		// 查询用户是否已提交
		sql = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01192", "查询用户是否已提交视频价格:", sql);
		int is_submit = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01192", "查询用户是否已提交视频价格----is_submit:", is_submit);
		if (is_submit == 1) {
			sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01192", "修改用户视频价格:", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01192", "修改用户视频价格----success", success);
		} else {
			sql = sqlmface.modSqlface(2, arg);
			logDetector.send(DataType.basicType, "01192", "创建用户视频价格:", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01192", "创建用户视频价格----success", success);
		}
		if (success == 0) {
			jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	/**
	 * 修改私信聊天价格 arg[2] user_id arg[3] price
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void update_Mprice(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		int success = 0;
		// 查询用户是否已提交
		sql = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01192", "查询用户是否已提交视频价格:", sql);
		int is_submit = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01192", "查询用户是否已提交视频价格----is_submit:", is_submit);
		if (is_submit == 1) {
			sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01192", "修改用户视频价格:", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01192", "修改用户视频价格----success", success);
		} else {
			sql = sqlmface.modSqlface(2, arg);
			logDetector.send(DataType.basicType, "01192", "创建用户视频价格:", sql);
			success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01192", "创建用户视频价格----success", success);
		}
		if (success == 0) {
			jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			jsonadd = "{\"success\":\"1\"}";
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
		case "search_msg_list":
			search_msg_list(arg);
			break;
		case "search_call_list":
			search_call_list(arg);
			break;
		case "search_income_list":
			search_income_list(arg);
			break;
		case "sum_income":
			sum_income(arg);
			break;
		case "search_income_today":
			search_income_today(arg);
			break;
		case "search_balance":
			search_balance(arg);
			break;
		case "search_info":
			search_info(arg);
			break;
		case "search_price":
			search_price(arg);
			break;
		default:
			break;
		}
	}

	// 查询 用户 视频价格
	private void search_price(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询用户价格sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询用户价格信息:", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	/**
	 * 查询用户信息
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void search_info(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询用户信息sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询用户信息:", list);
		// 获取今日时间
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01192", "获取今日时间--sql:", sql);
		arg[0] = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01192", "获取今日时间--arg[0]:", arg[0]);
		// 查询今日收入
		sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01192", "获取今日收入--sql:", sql);
		String today_income = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01192", "获取今日收入--today_income:", today_income);
		// 获取聊天价格
		sql = sqlmface.searchSqlface(3, arg);
		list1 = sqlUtil.get_list(sql);
		if (list1 == null || list1.size() == 0) {
			logDetector.send(DataType.basicType, "01192", "没有各项价格--list1:", list1);
			// b.videochat_price,b.voicechat_price,b.wordchat_price,today_income
			list.get(0).put("videochat_price", "0");
			list.get(0).put("voicechat_price", "0");
			list.get(0).put("wordchat_price", "0");
		} else {
			// [[(voicechat_price,10),(videochat_price,15),(wordchat_price,2)]]
			logDetector.send(DataType.basicType, "01192", "至少含有一项价格--list1:", list1);
			list.get(0).put("videochat_price", list1.get(0).get("videochat_price"));
			list.get(0).put("voicechat_price", list1.get(0).get("voicechat_price"));
			list.get(0).put("wordchat_price", list1.get(0).get("wordchat_price"));
		}
		if (today_income == null || today_income.equals("null")) {
			list.get(0).put("today_income", "0.0");
		} else {
			list.get(0).put("today_income", today_income);
		}

		if (list.size() > 0) {
			int a = Integer.parseInt(list.get(0).get("balance").toString());
			int b = Integer.parseInt(list.get(0).get("total_revenue").toString());
			int c = Integer.parseInt(list.get(0).get("total_withdrawals").toString());
			list.get(0).put("balance", a + b - c);
		}

//		//互相关注的
//		sql = sqlmface.searchSqlface(4, arg);
//		String two_like = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01192", "最终的查询用户信息:", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	private void search_balance(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询余额sql:", sql);
		jsonadd = sqlUtil.get_string(sql);
//		list=sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询余额信息:", jsonadd);
		inOutUtil.return_ajax(jsonadd);

	}

	private void search_income_today(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询今日收益总和sql:", sql);
		jsonadd = sqlUtil.get_string(sql);
//		list=sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询今日收益信息:", jsonadd);
		inOutUtil.return_ajax(jsonadd);

	}

	private void sum_income(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询收入明细总和sql:", sql);
		jsonadd = sqlUtil.get_string(sql);
//		list=sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询收入明细列表信息:", jsonadd);
		inOutUtil.return_ajax(jsonadd);
	}

	private void search_income_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询收入明细列表sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询收入明细列表信息:", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	private void search_call_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		if ("男".equals(arg[3])) {
			sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01192", "查询视频列表sql:", sql);
			list = sqlUtil.get_list(sql);
			logDetector.send(DataType.basicType, "01192", "查询视频列表信息:", list);
		} else {
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01192", "查询视频列表sql:", sql);
			list = sqlUtil.get_list(sql);
			logDetector.send(DataType.basicType, "01192", "查询视频列表信息:", list);
		}
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	private void search_msg_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String jsonadd = "";
		sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01192", "查询聊天列表sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01192", "查询聊天列表信息:", list);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	static class MapComparatorAsc implements Comparator<Map<String, Object>> {
		@Override
		public int compare(Map<String, Object> m1, Map<String, Object> m2) {
			String v1 = m1.get("juli").toString().trim();
			String v2 = m2.get("juli").toString().trim();
			// (0, 4)(5, 7)(8, 10)(10, 12)(13, 15);
			/*
			 * String s1 = v1.subSequence(0, 4) + "" + v1.subSequence(5, 7) +
			 * v1.subSequence(8, 10) + v1.subSequence(11, 13) + v1.subSequence(14, 16);
			 * String s2 = v2.subSequence(0, 4) + "" + v2.subSequence(5, 7) +
			 * v2.subSequence(8, 10) + v2.subSequence(11, 13) + v2.subSequence(14, 16); Long
			 * s11 = Long.parseLong(s1); Long s22 = Long.parseLong(s2);
			 */
			if (v1 != null) {
				return v1.compareTo(v2);
			}
			return 0;
		}

	}

	static class MapComparatorDesc implements Comparator<Map<String, Object>> {
		@Override
		public int compare(Map<String, Object> m1, Map<String, Object> m2) {
			String v1 = m1.get("juli").toString().trim();
			String v2 = m2.get("juli").toString().trim();
			// (0, 4)(5, 7)(8, 10)(10, 12)(13, 15);
			/*
			 * String s1 = v1.subSequence(0, 4) + "" + v1.subSequence(5, 7) +
			 * v1.subSequence(8, 10) + v1.subSequence(11, 13) + v1.subSequence(14, 16);
			 * String s2 = v2.subSequence(0, 4) + "" + v2.subSequence(5, 7) +
			 * v2.subSequence(8, 10) + v2.subSequence(11, 13) + v2.subSequence(14, 16); Long
			 * s11 = Long.parseLong(s1); Long s22 = Long.parseLong(s2);
			 */
			if (v2 != null) {
				return v2.compareTo(v1);
			}
			return 0;
		}
	}

	public double computeDistance(double lat1, double lon1, double lat2, double lon2) {
		// TODO Auto-generated method stub
		int MAXITERS = 20;
		// Convert lat/long to radians
		lat1 *= Math.PI / 180.0;
		lat2 *= Math.PI / 180.0;
		lon1 *= Math.PI / 180.0;
		lon2 *= Math.PI / 180.0;

		double a = 6378137.0; // WGS84 major axis
		double b = 6356752.3142; // WGS84 semi-major axis
		double f = (a - b) / a;
		double aSqMinusBSqOverBSq = (a * a - b * b) / (b * b);

		double L = lon2 - lon1;
		double A = 0.0;
		double U1 = Math.atan((1.0 - f) * Math.tan(lat1));
		double U2 = Math.atan((1.0 - f) * Math.tan(lat2));

		double cosU1 = Math.cos(U1);
		double cosU2 = Math.cos(U2);
		double sinU1 = Math.sin(U1);
		double sinU2 = Math.sin(U2);
		double cosU1cosU2 = cosU1 * cosU2;
		double sinU1sinU2 = sinU1 * sinU2;

		double sigma = 0.0;
		double deltaSigma = 0.0;
		double cosSqAlpha = 0.0;
		double cos2SM = 0.0;
		double cosSigma = 0.0;
		double sinSigma = 0.0;
		double cosLambda = 0.0;
		double sinLambda = 0.0;

		double lambda = L; // initial guess
		for (int iter = 0; iter < MAXITERS; iter++) {
			double lambdaOrig = lambda;
			cosLambda = Math.cos(lambda);
			sinLambda = Math.sin(lambda);
			double t1 = cosU2 * sinLambda;
			double t2 = cosU1 * sinU2 - sinU1 * cosU2 * cosLambda;
			double sinSqSigma = t1 * t1 + t2 * t2; // (14)
			sinSigma = Math.sqrt(sinSqSigma);
			cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda; // (15)
			sigma = Math.atan2(sinSigma, cosSigma); // (16)
			double sinAlpha = (sinSigma == 0) ? 0.0 : cosU1cosU2 * sinLambda / sinSigma; // (17)
			cosSqAlpha = 1.0 - sinAlpha * sinAlpha;
			cos2SM = (cosSqAlpha == 0) ? 0.0 : cosSigma - 2.0 * sinU1sinU2 / cosSqAlpha; // (18)

			double uSquared = cosSqAlpha * aSqMinusBSqOverBSq; // defn
			A = 1 + (uSquared / 16384.0) * // (3)
					(4096.0 + uSquared * (-768 + uSquared * (320.0 - 175.0 * uSquared)));
			double B = (uSquared / 1024.0) * // (4)
					(256.0 + uSquared * (-128.0 + uSquared * (74.0 - 47.0 * uSquared)));
			double C = (f / 16.0) * cosSqAlpha * (4.0 + f * (4.0 - 3.0 * cosSqAlpha)); // (10)
			double cos2SMSq = cos2SM * cos2SM;
			deltaSigma = B * sinSigma * // (6)
					(cos2SM + (B / 4.0) * (cosSigma * (-1.0 + 2.0 * cos2SMSq)
							- (B / 6.0) * cos2SM * (-3.0 + 4.0 * sinSigma * sinSigma) * (-3.0 + 4.0 * cos2SMSq)));

			lambda = L + (1.0 - C) * f * sinAlpha
					* (sigma + C * sinSigma * (cos2SM + C * cosSigma * (-1.0 + 2.0 * cos2SM * cos2SM))); // (11)

			double delta = (lambda - lambdaOrig) / lambda;
			if (Math.abs(delta) < 1.0e-12) {
				break;
			}
		}
		return b * A * (sigma - deltaSigma) / 1000;
	}
}
