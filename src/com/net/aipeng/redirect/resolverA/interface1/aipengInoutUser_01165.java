package com.net.aipeng.redirect.resolverA.interface1;

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
import com.net.aipeng.redirect.resolverA.interface2.aipengSqlUser_01165;

public class aipengInoutUser_01165 extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	SqlManagerFace sqlmface = new aipengSqlUser_01165();

	public aipengInoutUser_01165(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 用户注册
		// case "user_register"://180910创建
		// user_register(arg);
		// break;
		case "wx_login":
			wx_login(arg);
			break;

		case "qq_login":
			qq_login(arg);
			break;

		case "bind_wx":
			String sql = "select wx_id from user_data where id=" + arg[2];
			list = sqlUtil.get_list(sql);
			if (list.size() > 0) {
				String wxid = "";
				if (list.get(0).get("wx_id") == null) {// || list.get(0).get("wx_id").equals("null")
					wxid = "";
				} else {
					wxid = list.get(0).get("wx_id").toString();
				}
				logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--phone_id:", wxid);
				if (wxid.equals(arg[3])) {
					// 本身绑定的，不需要更换
					String jsonadd = "{\"result\":\"0\"}";
					inOutUtil.return_ajax(jsonadd);
				} else {
					sql = "select count(*) from user_data where wx_id='" + arg[3] + "'";
					int cont = sqlUtil.get_countint(sql);
					if (cont > 0) {
						// 别的账号绑定了
						String jsonadd = "{\"result\":\"2\"}";
						inOutUtil.return_ajax(jsonadd);
					} else {
						sql = "update user_data set wx_id='" + arg[3] + "', wechat_name='" + arg[4] + "' where id="
								+ arg[2];
						sqlUtil.sql_exec(sql);
						// 绑定成功
						String jsonadd = "{\"result\":\"1\"}";
						inOutUtil.return_ajax(jsonadd);
					}
				}
			}
			break;
		case "bind_phone":
			String sql1 = "select phone from user_data where id=" + arg[2];
			list = sqlUtil.get_list(sql1);
			if (list.size() > 0) {
				String phone = "";
				if (list.get(0).get("phone") == null) {
					phone = "";
				} else {
					phone = list.get(0).get("phone").toString();
				}
				if (phone.equals(arg[3])) {
					// 本身绑定的，不需要更换
					String jsonadd = "{\"result\":\"0\"}";
					inOutUtil.return_ajax(jsonadd);
				} else {
					sql1 = "select count(*) from user_data where phone='" + arg[3] + "'";
					int cont = sqlUtil.get_countint(sql1);
					if (cont > 0) {
						// 别的账号绑定了
						String jsonadd = "{\"result\":\"2\"}";
						inOutUtil.return_ajax(jsonadd);
					} else {
						// 绑定成功
						String jsonadd = "{\"result\":\"1\"}";
						inOutUtil.return_ajax(jsonadd);
					}
				}
			}
			break;
		}
	}

	/**
	 * arg[2] wx_id arg[3] nickname arg[4] photo
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */

	private void wx_login(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.addSqlface(3, arg);
		logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--phone_id:", list);

		String yqcode = arg[6];
		String channelcode = arg[7];
		String tmppath = request.getSession().getServletContext().getRealPath("/");

		if (list == null || "null".equals(list) || list.size() == 0) {
			sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01198", "微信 注册-sql:", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01198", "微信 注册-success:", success);

			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取id--sql:", sql);
			String u_id = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取id--phone_id:", u_id);

			share_agentinfo.registerinfo(1, u_id, yqcode, channelcode, "0.5", tmppath, "app.quyuanapp.com");

			// 查询微信手机号sql = sqlmface.addSqlface(3, arg);

			sql = sqlmface.addSqlface(3, arg);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--sql:", sql);
			list = sqlUtil.get_list(sql);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--phone_id:", list);
			list.get(0).put("is_new", "1");

			inOutUtil.return_ajax(JsonUtil.listToJson(list));
		} else {
			list.get(0).put("is_new", "0");
			inOutUtil.return_ajax(JsonUtil.listToJson(list));
		}

	}

	private void qq_login(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.addSqlface(3, arg);
		logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--phone_id:", list);

		String yqcode = arg[6];
		String channelcode = arg[7];
		String tmppath = request.getSession().getServletContext().getRealPath("/");

		if (list == null || "null".equals(list) || list.size() == 0) {
			sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01198", "微信 注册-sql:", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01198", "微信 注册-success:", success);

			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取id--sql:", sql);
			String u_id = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取id--phone_id:", u_id);

			share_agentinfo.registerinfo(1, u_id, yqcode, channelcode, "0.5", tmppath, "app.quyuanapp.com");

			// 查询微信手机号sql = sqlmface.addSqlface(3, arg);

			sql = sqlmface.addSqlface(3, arg);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--sql:", sql);
			list = sqlUtil.get_list(sql);
			logDetector.send(DataType.basicType, "01198", "微信 登陆-获取信息--phone_id:", list);
			list.get(0).put("is_new", "1");

			inOutUtil.return_ajax(JsonUtil.listToJson(list));
		} else {
			list.get(0).put("is_new", "0");
			inOutUtil.return_ajax(JsonUtil.listToJson(list));
		}

	}

	/**
	 * 用户注册------01198 arg[2] phone arg[3] user_photo arg[4] nickname arg[5] age
	 * arg[6] address arg[7] gender
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void user_register(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "用户注册-sql:", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01198", "用户注册-success:", success);
		String yqcode = arg[8];
		String channelcode = arg[9];
		String tmppath = request.getSession().getServletContext().getRealPath("/");
		if (success == 1) {
			String sql1 = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "用户id-sql:", sql);
			list = sqlUtil.get_list(sql1);
			logDetector.send(DataType.basicType, "01198", "用户id-success:", success);
			String user_id = list.get(0).get("id").toString();
			share_agentinfo.registerinfo(1, user_id, yqcode, channelcode, "5", tmppath, "app.quyuanapp.com");
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
		// 查询用户基本信息
		case "search_basic_personal_details":// 180902创建
			search_basic_personal_details(arg);
			break;
		// 查询是否关注了
		case "search_is_attention":// 180907创建
			search_is_attention(arg);
			break;
		// 查询他人详情页聊天价格信息
		case "search_chat_price_details":// 180909创建
			search_chat_price_details(arg);
			break;
		// 根据手机号查询用户信息
		case "search_info_by_phone":// 180910创建
			search_info_by_phone(arg);
			break;
		// 获取短信验证码
		case "get_message_info":// 180910创建
			get_message_info(arg);
			break;
		}
	}

	private void get_message_info(String[] arg) throws SQLException, ServletException, IOException {
		// 取随机数1000-9999
		int num = (int) ((Math.random() * 9000) + 1000);
		String jsonadd = "{\"success\":\"" + num + "\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	/**
	 * 根据手机号查询用户信息------01198 arg[2] phone 手机号
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_info_by_phone(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "根据手机号查询用户信息sql", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "根据手机号查询用户信息-list", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	/**
	 * 查询他人详情页聊天价格信息------01198 arg[2] user_id 用户id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_chat_price_details(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询他人详情页聊天价格信息sql", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "查询他人详情页聊天价格信息-list", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	/**
	 * 查询是否关注了------01198
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_is_attention(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询是否关注了---sql", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01198", "查询是否关注了---count", count);
		if (count == 1) {
			String sql1 = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "查询是否关注了---sql1", sql1);
			list = sqlUtil.get_list(sql1);
			logDetector.send(DataType.basicType, "01198", "查询是否关注了---list", list);
			if ("".equals(list.get(0).get("user_follow_time"))) {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		} else {
			String sql2 = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01198", "查询是否关注了---sq2", sql2);
			int count1 = sqlUtil.get_int(sql2);
			logDetector.send(DataType.basicType, "01198", "查询是否关注了---count2", count1);
			if (count1 == 1) {
				String sql3 = sqlmface.searchSqlface(3, arg);
				logDetector.send(DataType.basicType, "01198", "查询是否关注了---sql3", sql3);
				list1 = sqlUtil.get_list(sql3);
				logDetector.send(DataType.basicType, "01198", "查询是否关注了---list1", list1);
				if ("".equals(list1.get(0).get("target_follow_time"))) {
					String jsonadd = "{\"success\":\"0\"}";
					inOutUtil.return_ajax(jsonadd);
				} else {
					String jsonadd = "{\"success\":\"1\"}";
					inOutUtil.return_ajax(jsonadd);
				}
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		}

	}

	/**
	 * 查询用户基本信息------01198 arg[2] id 用户id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_basic_personal_details(String[] arg) throws SQLException, ServletException, IOException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询用户基本信息sql", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "查询用户基本信息-list", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

}
