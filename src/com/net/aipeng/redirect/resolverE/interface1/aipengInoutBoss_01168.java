package com.net.aipeng.redirect.resolverE.interface1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import com.hz.vliao1.mA.vliaoInoutBoss_01150.BizRenderTask;*/
import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface1.mA.InOutManager;
import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyHelpManager;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.redirect.resolverE.interface2.aipengSqlBoss_01168;
import com.net.aipeng.redirect.resolverE.interface4.JyHelpManager_boss;

///uiface/ar?p0=A-boss-search&p1=memberbackstage&p2=1&p3=&p4=&p5=tojson&p6=50

public class aipengInoutBoss_01168 extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	SqlManagerFace sqlmface = new aipengSqlBoss_01168();

	// Fmq_Other_RewardInOutUser_B other_reward = new
	// Fmq_Other_RewardInOutUser_B(dbName);
	public aipengInoutBoss_01168(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	/***********************************
	 * addface
	 ******************************************/
	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 管理员添加
		case "admin_add":
			admin_add(arg);
			break;
		case "version_update":
			version_update(arg);
			break;
		// 轮播图添加
		case "photo_add":
			photo_add(arg);
			break;
		// 系统通知(发送)
		/*
		 * case "notification_add": notification_add(arg); break;
		 */
		case "notification_add_all":
			notification_add_all(arg);
			break;
		case "notification_add_god":
			notification_add_god(arg);
			break;
		case "notification_add_male":
			notification_add_male(arg);
			break;
		case "notification_add_female":
			notification_add_female(arg);
			break;
		// 礼物添加
		case "dashang_add":
			dashang_add(arg);
			break;
		// 私信屏蔽关键词添加
		case "addchatfilter":
			addchatfilter(arg);
			break;
		// vip充值增加
		case "vip_add":
			vip_add(arg);
			break;
		// 趣豆充值增加
		case "recharge_add":
			recharge_add(arg);
			break;
		// 渠道商增加
		case "agent_add1":
			agent_add1(arg);
			break;
		}
	}

	private void agent_add1(String[] arg) throws SQLException, IOException, ServletException {
		logDetector.send(DataType.basicType, "01162", "进入渠道商agent_add1", arg);
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "代理对应等级修改", sql);
		int a = sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("1");
	}

	private void recharge_add(String[] arg) throws SQLException, IOException, ServletException {
		if ("".equals(arg[2]) && "".equals(arg[3]) && "".equals(arg[4])) {
			String jsonadd = "{\"success\":\"添加失败\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "后台——礼物添加", sql);
			int a = sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"添加成功\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	private void vip_add(String[] arg) throws SQLException, IOException, ServletException {
		if ("".equals(arg[2]) && "".equals(arg[3]) && "".equals(arg[4])) {
			String jsonadd = "{\"success\":\"添加失败\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "后台——礼物添加", sql);
			int a = sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"添加成功\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	// A-boss-add,addchatfilter,测试关键词
	private void addchatfilter(String[] arg) throws SQLException, IOException, ServletException {
		// arg[2] = arg[2].trim();
		arg[2] = arg[2].replaceAll(" ", "");
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "检测关键词是否有重复()-sql: ", sql);
		String str = sqlUtil.get_string(sql);
		if ("0".equals(str)) {
			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "插入屏蔽关键词-sql: ", sql);
			int result = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01162", "addchatfilter()-result: ", result);
			inOutUtil.return_ajax("{\"result\":\"" + result + "\"}");
		} else {
			inOutUtil.return_ajax("{\"result\":\"" + 0 + "\"}");
		}
	}

	private void dashang_add(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "后台——礼物添加", sql);
		int a = sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	private void notification_add_all(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "发布全部用户的通知", sql);
		int a = sqlUtil.sql_exec(sql);
		sql = sqlmface.addSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		String user_id = "";
		String content = arg[2];
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				user_id = list.get(i).get("id").toString();
			} else {
				user_id = user_id + "," + list.get(i).get("id").toString();
			}
		}
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "用户ID" + user_id + "内容:----" + content);
		BizRenderTask send = new BizRenderTask(user_id + "卍" + content);

		send.run();
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "通过并返回");
		inOutUtil.return_ajax("修改成功");
	}

	private void notification_add_god(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "发布给女神的通知", sql);
		int a = sqlUtil.sql_exec(sql);
		sql = sqlmface.addSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		String user_id = "";
		String content = arg[2];
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				user_id = list.get(i).get("id").toString();
			} else {
				user_id = user_id + "," + list.get(i).get("id").toString();
			}
		}
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "用户ID" + user_id + "内容:----" + content);
		BizRenderTask send = new BizRenderTask(user_id + "卍" + content);
		send.run();
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "通过并返回");
		inOutUtil.return_ajax("修改成功");
	}

	private void notification_add_male(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "发布给男用户的通知", sql);
		int a = sqlUtil.sql_exec(sql);
		sql = sqlmface.addSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		String user_id = "";
		String content = arg[2];
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				user_id = list.get(i).get("id").toString();
			} else {
				user_id = user_id + "," + list.get(i).get("id").toString();
			}
		}
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "用户ID" + user_id + "内容:----" + content);
		BizRenderTask send = new BizRenderTask(user_id + "卍" + content);
		send.run();
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "通过并返回");
		inOutUtil.return_ajax("修改成功");
	}

	private void notification_add_female(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "发布给女用户的通知", sql);
		int a = sqlUtil.sql_exec(sql);
		sql = sqlmface.addSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		String user_id = "";
		String content = arg[2];
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				user_id = list.get(i).get("id").toString();
			} else {
				user_id = user_id + "," + list.get(i).get("id").toString();
			}
		}
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "用户ID" + user_id + "内容:----" + content);
		BizRenderTask send = new BizRenderTask(user_id + "卍" + content);
		send.run();
		logDetector.send(DataType.basicType, "01160", "最新一条内容 ", "通过并返回");
		inOutUtil.return_ajax("修改成功");
	}

	/*
	 * private void notification_add(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.addSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "发布通知", sql); int a =
	 * sqlUtil.sql_exec(sql); sql = sqlmface.addSqlface(1, arg); list =
	 * sqlUtil.get_list(sql); String user_id = ""; String content = arg[2]; for (int
	 * i = 0; i < list.size(); i++) { if (i == 0) { user_id =
	 * list.get(i).get("id").toString(); } else { user_id = user_id + "," +
	 * list.get(i).get("id").toString(); } } log.send(DataType.basicType, "01160",
	 * "最新一条内容 ", "用户ID" + user_id + "内容:----" + content); BizRenderTask send = new
	 * BizRenderTask(user_id + "卍" + content); send.run();
	 * log.send(DataType.basicType, "01160", "最新一条内容 ", "通过并返回");
	 * inOutUtil.return_ajax("修改成功"); }
	 */

	// A-boss-add,photo_add,2,20180920163956.png,www.baidu.com,消息,隐藏
	/**
	 * arg[2]:排序序号 arg[3]:图片名称 arg[4]:跳转地址 arg[5]:分属页面 arg[6]:是否隐藏
	 */
	private void photo_add(String[] arg) throws SQLException, IOException, ServletException {
		// 判断用户是否上传图片
		String sql = "";
		if ("".equals(arg[4])) {
			sql = sqlmface.addSqlface(0, arg);
			logDetector.send(DataType.basicType, "01168", "后台——轮播图添加", sql);
			int a = sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			sql = sqlmface.addSqlface(1, arg);
			logDetector.send(DataType.basicType, "01168", "后台——轮播图添加", sql);
			int a = sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}
	/*
	 * String sql = sqlmface.addSqlface(0, arg); log.send(DataType.basicType,
	 * "01162", "后台——轮播图添加", sql); int a = sqlUtil.sql_exec(sql); String jsonadd =
	 * "{\"success\":\"1\"}"; inOutUtil.return_ajax(jsonadd);
	 */

	private void version_update(String[] arg) throws SQLException, IOException, ServletException {
		// 判断用户是否上传图片
		String sql = "";
		sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "版本更新内容  - sql ", sql);
		int a = sqlUtil.sql_exec(sql);

	}

	private void admin_add(String[] arg) throws SQLException, IOException, ServletException {
		String user = "";
		int a = 0;
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01168", "查询要添加的管理员用户是否重复", sql);
		user = sqlUtil.get_string(sql);
		if (user.equals("0")) {
			// input.matches("[0-9A-Za-z_]*")
			if (arg[2].length() > 5 && arg[2].matches("[0-9A-Za-z_]*") && arg[3].matches("[0-9A-Za-z_]*")) {
				sql = sqlmface.addSqlface(1, arg);
				logDetector.send(DataType.basicType, "01162", "添加管理员用户", sql);
				a = sqlUtil.sql_exec(sql);
			} else {
				a = 0;
			}

		}
		if (a == 1) {
			inOutUtil.return_ajax("添加成功");
		} else {
			inOutUtil.return_ajax("添加失败");
		}
	}

	/*
	 * private void admin_add(String[] arg) throws SQLException, IOException,
	 * ServletException {
	 * 
	 * String sql = sqlmface.addSqlface(0, arg); int a = sqlUtil.sql_exec(sql); if
	 * (a == 1) { inOutUtil.return_ajax("添加成功"); } }
	 */
	/***********************************
	 * modface
	 ******************************************/
	@Override
	public void modface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		// 保存代理商信息
		/*
		 * case "save_t_agent_power": save_t_agent_power(arg); break;
		 */
		// 管理员权限修改
		case "logins_mod":
			logins_mod(arg);
			break;

		// 管理员修改(查询)
		case "admin_mod":
			admin_mod(arg);
			break;
		// 管理员修改(修改)
		case "admin_mod1":
			admin_mod1(arg);
			break;
		// 封禁
		case "recycle_hide":
			recycle_hide(arg);
			break;
		case "recycle_appear":
			recycle_appear(arg);
			break;
		case "anchor_banned":
			anchor_banned(arg);
			break;
		// 解封
		case "banned_cancel":
			banned_cancel(arg);
			break;
		// 删除相册图片
		case "picture_del":
			picture_del(arg);
			break;
		// 拒绝提现
		case "jujue_money":
			jujue_money(arg);
			break;
		// 提现
		/*
		 * case "response_money": response_money(arg); break;
		 */
		// 充值爱豆设置(查询)
		case "recharge_mod":
			recharge_mod(arg);
			break;
		// 充值爱豆设置(修改)
		case "recharge_mod1":
			recharge_mod1(arg);
			break;
		// 充值vip设置(查询)
		case "vip_mod":
			vip_mod(arg);
			break;
		// 充值vip设置(修改)
		case "vip_mod1":
			vip_mod1(arg);
			break;

		// 轮播图修改(查询)
		case "photo2":
			photo2(arg);
			break;
		// 轮播图修改(修改)
		case "photo_mod":
			photo_mod(arg);
			break;
		// 提现修改(查询)
		case "cash_mod1":
			cash_mod1(arg);
			break;
		// 提现修改(修改)
		case "cash_mod":
			cash_mod(arg);
			break;
		// 女神认证(通过)
		case "renzheng_checkpass":
			renzheng_checkpass(arg);
			break;
		// 女神认证(拒绝)
		case "renzheng_checknopass":
			renzheng_checknopass(arg);
			break;
		// 用户信息审核(通过)
		case "album_checkpass":
			album_checkpass(arg);
			break;
		// 用户信息审核(不通过)
		case "album_checknopass":
			album_checknopass(arg);
			break;
		// 设为推荐/取消推荐
		case "anchor_tuijian":
			anchor_tuijian(arg);
			break;
		// 分销设置(修改)
		case "mod_fenxiao":
			mod_fenxiao(arg);
			break;
		// 礼物打赏设置(查询)
		case "dashang_search_mod":
			dashang_search_mod(arg);
			break;
		// 礼物打赏设置(编辑)
		case "dashang_search_bianji":
			dashang_search_bianji(arg);
			break;
		// 趣豆增加查询
		case "qd_search_a":
			qd_search_a(arg);
			break;
		// 趣豆减少查询
		case "qd_search_b":
			qd_search_b(arg);
			break;
		// 趣豆增加
		case "qd_mod_a":
			qd_mod_a(arg);
			break;
		// 趣豆减少
		case "qd_mod_b":
			qd_mod_b(arg);
			break;
		// 修改举报信息(标记处理)
		case "bl_mod":
			bl_mod(arg);
			break;
		case "c_mod1":
			c_mod1(arg);
			break;
		case "c_mod2":
			c_mod2(arg);
			break;
		// 修改反馈信息(标记处理)
		case "com_mod":
			com_mod(arg);
			break;
		// 客服信息修改(查询)
		case "service_mod":
			service_mod(arg);
			break;
		// 客服信息修改(修改)
		case "service_xiugai":
			service_xiugai(arg);
			break;
		// 鉴黄时间间隔修改(查询)
		case "jianhuang_mod":
			jianhuang_mod(arg);
			break;
		// 鉴黄时间间隔修改(查询)
		case "jianhuang_xiugai":
			jianhuang_xiugai(arg);
			break;
		// 渠道商修改(查询)
		case "agent_modbf1"://
			agent_modbf1(arg);
			break;
		// 渠道商修改(修改)
		case "agent_mod1":
			agent_mod1(arg);
			break;
		// 管理员删除
		case "admin_del":
			admin_del(arg);
			break;
		case "jianhuang_cl":
			jianhuang_cl(arg);
			break;
		case "anchor_banned_jh":
			anchor_banned_jh(arg);
			break;
		// 解封
		case "banned_cancel_jh":
			banned_cancel_jh(arg);
			break;

		}
	}

	private void banned_cancel_jh(String[] arg) throws SQLException, IOException, ServletException {
		// p2:鉴黄表id p3:用户user_id
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		sql = sqlmface.modSqlface(1, arg);
		sqlUtil.sql_exec(sql);
		BizRenderTask send = new BizRenderTask(arg[2] + "卍" + "解封");
		send.run();
	}

	private void anchor_banned_jh(String[] arg) throws SQLException, IOException, ServletException {
		if (!arg[2].equals("")) {
			String sql = sqlmface.modSqlface(0, arg);
			sqlUtil.sql_exec(sql);
			sql = sqlmface.modSqlface(1, arg);
			sqlUtil.sql_exec(sql);

			BizRenderTask send = new BizRenderTask(arg[2] + "卍" + "封禁");
			send.run();
		}
	}

	private void jianhuang_cl(String[] arg) throws SQLException, IOException, ServletException {
		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[3] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[4] = ip;

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "鉴黄管理标记处理-sql", sql);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql);

		String sql1 = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01208", "鉴黄管理标记处理-sql - log : ", sql1);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql1);
	}

	private void admin_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "删除管理员表中信息", sql);
		int a = sqlUtil.sql_exec(sql);
		sql = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "删除管理员权限表中信息", sql);
		sqlUtil.sql_exec(sql);
	}

	private void agent_mod1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "代理对应等级修改", sql);
		int a = sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("1");
	}

	public void agent_modbf1(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.modSqlface(0, arg);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql1);
		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		inOutUtil.return_list(list, "/uiface/boss/agent_mod1.jsp");
	}

	private void jianhuang_xiugai(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "修改分销提成比例-sql", sql);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	private void jianhuang_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fenxiao_search_mod:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/jianhuang_mod.jsp");
	}

	private void service_xiugai(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "修改分销提成比例-sql", sql);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	private void service_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fenxiao_search_mod:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/service_set.jsp");
	}

	private void logins_mod(String[] arg) throws SQLException, IOException, ServletException {
		try {
			logDetector.send(DataType.basicType, "01202", "权限修改-01168: ", arg);
			// 删除原来的菜单权限
			String sql = sqlmface.modSqlface(-1, arg);
			logDetector.send(DataType.basicType, "01202", "save_t_agent_power-sql: 删除原来的权限 ", sql);
			int set = sqlUtil.sql_exec(sql);
			// 插入新的菜单权限
			String sql1 = sqlmface.modSqlface(-2, arg);
			ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
			if (arg[3] != null) {
				String[] argss = arg[3].split("\\|");
				for (int i = 0; i < argss.length; i++) {
					if (!"".equals(argss[i])) {
						ArrayList<String> value = new ArrayList<String>();
						value.add(arg[2]);
						value.add(argss[i]);
						values.add(value);
					}
				}
			}
			logDetector.send(DataType.basicType, "01202", "save_t_agent_power-sql1: ", sql1);
			logDetector.send(DataType.basicType, "01202", "save_t_agent_power-values: ", values);
			int[] set1 = sqlUtil.sql_exec_batch(sql1, values);
			logDetector.send(DataType.basicType, "01202", "save_t_agent_power-set1: 增加权限 ", set1.length);
			inOutUtil.return_only("agent?p0=A-boss-search&p1=agent_power_edit&uid=" + arg[2]);

		} catch (Exception e) {
			logDetector.send(DataType.basicType, "01202", "changeUserPid-res: ", e);
			json = "{\"res\":\"ERROR_SERVICE\",\"msg\":\"服务器内部错误\",\"list\":[]}";
			try {
				inOutUtil.return_ajax(json);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/*
	 * private void save_t_agent_power(String[] arg) { try {
	 * log.send(DataType.basicType, "01202", "save_t_agent_power-arg: ", arg);
	 * //更新代理商的账号，密码，总代分成比例，区域代理分成比例 String sql3 = sqlmface.modSqlface(-3, arg);
	 * log.send(DataType.basicType, "01202",
	 * "save_t_agent_power-sql: 更新代理商的账号，密码，总代分成比例，区域代理分成比例 ", sql3); int set3 =
	 * sqlUtil.sql_exec(sql3); //删除原来的菜单权限 String sql = sqlmface.modSqlface(-1,
	 * arg); log.send(DataType.basicType, "01202",
	 * "save_t_agent_power-sql: 删除原来的权限 ", sql); int set = sqlUtil.sql_exec(sql);
	 * //插入新的菜单权限 String sql1 = sqlmface.modSqlface(-2, arg);
	 * ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>(); if
	 * (arg[8] != null) { String[] argss = arg[8].split("\\|"); for (int i = 0; i <
	 * argss.length; i++) { if (!"".equals(argss[i])) { ArrayList<String> value =
	 * new ArrayList<String>(); value.add(arg[2]); value.add(argss[i]);
	 * values.add(value); } } } log.send(DataType.basicType, "01202",
	 * "save_t_agent_power-sql1: ", sql1); log.send(DataType.basicType, "01202",
	 * "save_t_agent_power-values: ", values); int[] set1 =
	 * sqlUtil.sql_exec_batch(sql1, values); log.send(DataType.basicType, "01202",
	 * "save_t_agent_power-set1: 增加权限 ", set1.length);
	 * inOutUtil.return_only("agent?p0=A-boss-search&p1=agent_power_edit&uid=" +
	 * arg[2]); } catch (Exception e) { log.send(DataType.basicType, "01202",
	 * "changeUserPid-res: ", e); json =
	 * "{\"res\":\"ERROR_SERVICE\",\"msg\":\"服务器内部错误\",\"list\":[]}"; try {
	 * inOutUtil.return_ajax(json); } catch (ServletException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } } }
	 */

	/*
	 * private void c_mod1(String[] arg) throws SQLException, IOException,
	 * ServletException { String[] sql = sqlmface.modSqlface(0, arg).split(";");
	 * log.send(DataType.basicType, "01208", "实名认证审核通过-sql", sql); list =
	 * sqlUtil.get_list(sql); sqlUtil.sql_exec(sql[0]);
	 * 
	 * sqlUtil.sql_exec(sql[1]); }
	 */
	private void c_mod1(String[] arg) throws SQLException, IOException, ServletException {
		String ip = "";
		ip = getIpAddr(request);
		arg[4] = ip;

		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[5] = admin_id;

		String[] sql = sqlmface.modSqlface(0, arg).split(";");
		logDetector.send(DataType.basicType, "01208", "实名认证审核通过-sql", sql);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql[0]);

		sqlUtil.sql_exec(sql[1]);
		sqlUtil.sql_exec(sql[2]);
	}

	/*
	 * private void c_mod2(String[] arg) throws SQLException, IOException,
	 * ServletException { String[] sql = sqlmface.modSqlface(0, arg).split(";");
	 * log.send(DataType.basicType, "01208", "实名认证审核不通过-sql", sql); list =
	 * sqlUtil.get_list(sql); sqlUtil.sql_exec(sql[0]);
	 * 
	 * sqlUtil.sql_exec(sql[1]); }
	 */

	private void c_mod2(String[] arg) throws SQLException, IOException, ServletException {
		String ip = "";
		ip = getIpAddr(request);
		arg[5] = ip;

		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[6] = admin_id;

		String[] sql = sqlmface.modSqlface(0, arg).split(";");
		logDetector.send(DataType.basicType, "01208", "实名认证审核不通过-sql", sql);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql[0]);

		sqlUtil.sql_exec(sql[1]);
		sqlUtil.sql_exec(sql[2]);
	}

	/*
	 * private void bl_mod(String[] arg) throws SQLException, IOException,
	 * ServletException {
	 * 
	 * 
	 * String sql = sqlmface.modSqlface(0, arg); log.send(DataType.basicType,
	 * "01208", "举报管理标记处理-sql", sql); list = sqlUtil.get_list(sql);
	 * sqlUtil.sql_exec(sql); }
	 */

	private void bl_mod(String[] arg) throws SQLException, IOException, ServletException {
		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[3] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[4] = ip;

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "举报管理标记处理-sql", sql);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql);

		String sql1 = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01208", "举报管理标记处理-sql - log : ", sql1);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql1);
	}

	private void com_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "意见反馈标记处理-sql", sql);
		/* list = sqlUtil.get_list(sql); */
		sqlUtil.sql_exec(sql);
	}

	/*
	 * private void bl_mod(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.modSqlface(0, arg);
	 * log.send(DataType.basicType, "01208", "举报管理标记处理-sql", sql); list =
	 * sqlUtil.get_list(sql); } private void com_mod(String[] arg) throws
	 * SQLException, IOException, ServletException { String sql =
	 * sqlmface.modSqlface(0, arg); log.send(DataType.basicType, "01208",
	 * "意见反馈标记处理-sql", sql); list = sqlUtil.get_list(sql); }
	 */

	/*
	 * private void bl_mod(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.modSqlface(0, arg); list =
	 * sqlUtil.get_list(sql); log.send(DataType.basicType, "01208", "举报管理标记处理",
	 * "/uiface/boss/blacklist.jsp"); }
	 */
	/*
	 * private void com_mod(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.modSqlface(0, arg); list =
	 * sqlUtil.get_list(sql); log.send(DataType.basicType, "01208", "意见反馈标记处理",
	 * "/uiface/boss/complaint.jsp"); }
	 */

	private void qd_search_a(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fenxiao_search_mod:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/qd_mod_a.jsp");
	}

	private void qd_search_b(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fenxiao_search_mod:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/qd_mod_b.jsp");
	}

	// 判断字符串中是否为数字
	public static boolean isNumeric(String s) {
		if (s != null && !"".equals(s.trim()))
			return s.matches("^[0-9]*$");
		else
			return false;
	}

	// 获取当前年月日时分秒毫秒
	private static String yymmdd() {
		Calendar nowtime = new GregorianCalendar();
		String strDateTime = "[" + String.format("%04d", nowtime.get(Calendar.YEAR)) + "/"
				+ String.format("%02d", nowtime.get(Calendar.MONTH)) + "/"
				+ String.format("%02d", nowtime.get(Calendar.DATE)) + " "
				+ String.format("%02d", nowtime.get(Calendar.HOUR)) + ":"
				+ String.format("%02d", nowtime.get(Calendar.MINUTE)) + ":"
				+ String.format("%02d", nowtime.get(Calendar.SECOND)) + "."
				+ String.format("%03d", nowtime.get(Calendar.MILLISECOND)) + "]";
		System.out.println(strDateTime);

		// method 2
		String msg = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");
		msg += "" + sdf.format(date) + "";

		System.out.println(msg);
		msg = msg.replace("/", "");
		msg = msg.replace(":", "");
		msg = msg.replace(".", "");
		msg = msg.replace(" ", "");
		System.out.println(msg);
		return msg;
	}

	/*
	 * private void qd_mod_a(String[] arg) throws SQLException, IOException,
	 * ServletException {
	 * 
	 * if(isNumeric(arg[3])){ String sql = sqlmface.modSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "后台趣豆增加-sql", sql);
	 * sqlUtil.sql_exec(sql); sql = sqlmface.modSqlface(1, arg);
	 * log.send(DataType.basicType, "01162", "充值表添加记录-sql", sql);
	 * sqlUtil.sql_exec(sql); String jsonadd = "{\"success\":\"充值成功\"}";
	 * inOutUtil.return_ajax(jsonadd); }else{ String jsonadd =
	 * "{\"success\":\"充值失败\"}"; inOutUtil.return_ajax(jsonadd); }
	 * 
	 * }
	 */

	private void qd_mod_a(String[] arg) throws SQLException, IOException, ServletException {
		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[5] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[6] = ip;

		if (isNumeric(arg[3])) {
			String sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "后台趣豆增加-sql", sql);
			sqlUtil.sql_exec(sql);
			sql = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "充值表添加记录-sql1", sql);
			sqlUtil.sql_exec(sql);

			String sql2 = sqlmface.modSqlface(2, arg);
			logDetector.send(DataType.basicType, "01208", "充值表添加记录-sql2 - log : ", sql2);
			sqlUtil.sql_exec(sql2);

			String jsonadd = "{\"success\":\"充值成功\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String sql3 = sqlmface.modSqlface(3, arg);
			logDetector.send(DataType.basicType, "01208", "充值表添加记录-sql3 - log : ", sql3);
			sqlUtil.sql_exec(sql3);

			String jsonadd = "{\"success\":\"充值失败\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	/*
	 * private void qd_mod_b(String[] arg) throws SQLException, IOException,
	 * ServletException {
	 * 
	 * if(isNumeric(arg[3])){ String sql1 = sqlmface.modSqlface(2, arg);
	 * log.send(DataType.basicType, "01162", "查询用户余额-sql", sql1); String str =
	 * sqlUtil.get_string(sql1); //原有金额 int a = sqlUtil.get_int(sql1); //减去的金额 int b
	 * = Integer.parseInt(arg[3]); //减去的金额大于原有金额,不执行减金额操作 if(a<b){ String jsonadd =
	 * "{\"success\":\"余额不足,无法扣除\"}"; inOutUtil.return_ajax(jsonadd); }else{ String
	 * sql = sqlmface.modSqlface(0, arg); log.send(DataType.basicType, "01162",
	 * "后台趣豆增加-sql", sql); sqlUtil.sql_exec(sql); sql = sqlmface.modSqlface(1, arg);
	 * log.send(DataType.basicType, "01162", "充值表添加记录-sql", sql);
	 * sqlUtil.sql_exec(sql); String jsonadd = "{\"success\":\"充值成功\"}";
	 * inOutUtil.return_ajax(jsonadd); }
	 * 
	 * }else{ String jsonadd = "{\"success\":\"充值失败\"}";
	 * inOutUtil.return_ajax(jsonadd); } }
	 */

	private void qd_mod_b(String[] arg) throws SQLException, IOException, ServletException {
		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[5] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[6] = ip;

		if (isNumeric(arg[3])) {
			String sql1 = sqlmface.modSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "查询用户余额-sql", sql1);
			/* String str = sqlUtil.get_string(sql1); */
			// 原有金额
			int a = sqlUtil.get_int(sql1);
			// 减去的金额
			int b = Integer.parseInt(arg[3]);
			// 减去的金额大于原有金额,不执行减金额操作
			if (a < b) {
				String sql5 = sqlmface.modSqlface(4, arg);
				logDetector.send(DataType.basicType, "01162", "充值表添加记录-sql5- log", sql5);
				sqlUtil.sql_exec(sql5);

				String jsonadd = "{\"success\":\"余额不足,无法扣除\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String sql = sqlmface.modSqlface(0, arg);
				logDetector.send(DataType.basicType, "01162", "后台趣豆增加-sql", sql);
				sqlUtil.sql_exec(sql);
				sql = sqlmface.modSqlface(1, arg);
				logDetector.send(DataType.basicType, "01162", "充值表添加记录-sql", sql);
				sqlUtil.sql_exec(sql);

				String sql3 = sqlmface.modSqlface(3, arg);
				logDetector.send(DataType.basicType, "01162", "充值表添加记录-sql3", sql3);
				sqlUtil.sql_exec(sql3);

				String jsonadd = "{\"success\":\"充值成功\"}";
				inOutUtil.return_ajax(jsonadd);
			}

		} else {
			String sql4 = sqlmface.modSqlface(4, arg);
			logDetector.send(DataType.basicType, "01162", "充值表添加记录-sql4- log", sql4);
			sqlUtil.sql_exec(sql4);

			String jsonadd = "{\"success\":\"充值失败\"}";
			inOutUtil.return_ajax(jsonadd);
		}
	}

	private void dashang_search_bianji(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "修改分销提成比例-sql", sql);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	private void dashang_search_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fenxiao_search_mod:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/dashang_search_set.jsp");
	}

	private void mod_fenxiao(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "修改分销提成比例-sql", sql);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	private void anchor_tuijian(String[] arg) throws SQLException, IOException, ServletException {
		logDetector.send(DataType.basicType, "01150", "设为推荐/取消推荐：", "进入anchor_tuijian");
		String sql = "select is_recommand from user_data where id=" + arg[2];
		int ist = sqlUtil.get_int(sql);
		if (ist == 0) {
			sql = "update user_data set is_recommand=1 where id=" + arg[2];
			sqlUtil.sql_exec(sql);
			inOutUtil.return_ajax("1");
		} else {
			sql = "update user_data set is_recommand=0 where id=" + arg[2];
			sqlUtil.sql_exec(sql);
			inOutUtil.return_ajax("0");
		}

	}

	// arg[4]:拒绝理由
	/*
	 * private void album_checknopass(String[] arg) throws SQLException,
	 * IOException, ServletException { String sql = sqlmface.modSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "用户信息审核-不通过", sql);
	 * sqlUtil.sql_exec(sql); String jsonadd = "{\"success\":\"1\"}";
	 * inOutUtil.return_ajax(jsonadd); }
	 */

	private void album_checknopass(String[] arg) throws SQLException, IOException, ServletException {

		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[5] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[6] = ip;

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "用户信息审核-不通过", sql);
		sqlUtil.sql_exec(sql);

		String sql1 = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "用户信息审核-不通过-log ; ", sql1);
		sqlUtil.sql_exec(sql1);

		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	/*
	 * private void album_checkpass(String[] arg) throws SQLException, IOException,
	 * ServletException { arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
	 * //改变用户信息管理表中状态 String sql0 = sqlmface.modSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "相册审核通过-sql0", sql0);
	 * sqlUtil.sql_exec(sql0); //查询用户信息管理表中相册信息 String sql1 = sqlmface.modSqlface(1,
	 * arg); log.send(DataType.basicType, "01150", "相册审核通过-sql1:", sql1); String pic
	 * = sqlUtil.get_string(sql1); log.send(DataType.basicType, "01150",
	 * "审核通过的上传图片：", pic); //获得到的相册信息,改变用户表中相册信息 arg[4] = pic + ""; String sql3 =
	 * sqlmface.modSqlface(3, arg); log.send(DataType.basicType, "01162",
	 * "相册审核通过-sql3", sql3); sqlUtil.sql_exec(sql3);
	 * 
	 * //--------------------------------------------------- //查询用户信息管理表中信息 String
	 * sql4 = sqlmface.modSqlface(4, arg); log.send(DataType.basicType, "01162",
	 * "album_checkpass()-sql4", sql4); ArrayList<Map<String,Object>> list4 =
	 * sqlUtil.get_list(sql4); log.send(DataType.basicType, "01162",
	 * "album_checkpass()-list4", list4);
	 * 
	 * arg = Arrays.copyOfRange(arg, 0, arg.length+10); arg[4] =
	 * list4.get(0).get("nickname")+""; arg[5] = list4.get(0).get("photo")+"";
	 * arg[6] = list4.get(0).get("age")+""; //arg[7] =
	 * list4.get(0).get("gender")+""; arg[7] = list4.get(0).get("city")+""; arg[8] =
	 * list4.get(0).get("signature")+""; //改变用户表中信息 String sql5 =
	 * sqlmface.modSqlface(5, arg); log.send(DataType.basicType, "01162",
	 * "album_checkpass()-sql5", sql5); int result = sqlUtil.sql_exec(sql5);
	 * log.send(DataType.basicType, "01162", "album_checkpass()-result", result);
	 * 
	 * String jsonadd = "{\"success\":\"1\"}"; inOutUtil.return_ajax(jsonadd);
	 * 
	 * }
	 */

	private void album_checkpass(String[] arg) throws SQLException, IOException, ServletException {
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);

		// 改变用户信息管理表中状态
		String sql0 = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "相册审核通过-sql0", sql0);
		sqlUtil.sql_exec(sql0);
		// 查询用户信息管理表中相册信息
		String sql1 = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01150", "相册审核通过-sql1:", sql1);
		String pic = sqlUtil.get_string(sql1);
		logDetector.send(DataType.basicType, "01150", "审核通过的上传图片：", pic);
		// 获得到的相册信息,改变用户表中相册信息
		arg[4] = pic + "";
		String sql3 = sqlmface.modSqlface(3, arg);
		logDetector.send(DataType.basicType, "01162", "相册审核通过-sql3", sql3);
		sqlUtil.sql_exec(sql3);

		// ---------------------------------------------------
		// 查询用户信息管理表中信息
		String sql4 = sqlmface.modSqlface(4, arg);
		logDetector.send(DataType.basicType, "01162", "album_checkpass()-sql4", sql4);
		ArrayList<Map<String, Object>> list4 = sqlUtil.get_list(sql4);
		logDetector.send(DataType.basicType, "01162", "album_checkpass()-list4", list4);

		arg = Arrays.copyOfRange(arg, 0, arg.length + 10);
		arg[4] = list4.get(0).get("nickname") + "";
		arg[5] = list4.get(0).get("photo") + "";
		arg[6] = list4.get(0).get("age") + "";
		// arg[7] = list4.get(0).get("gender")+"";
		arg[7] = list4.get(0).get("city") + "";
		arg[8] = list4.get(0).get("signature") + "";

		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[11] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[12] = ip;

		// 改变用户表中信息
		String sql5 = sqlmface.modSqlface(5, arg);
		logDetector.send(DataType.basicType, "01162", "album_checkpass()-sql5", sql5);
		int result = sqlUtil.sql_exec(sql5);
		logDetector.send(DataType.basicType, "01162", "album_checkpass()-result", result);

		String sql6 = sqlmface.modSqlface(6, arg);
		int a = sqlUtil.sql_exec(sql6);

		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);

	}

	/*
	 * private void renzheng_checknopass(String[] arg) throws SQLException,
	 * IOException, ServletException { String sql = sqlmface.modSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "认证审核表信息改变-审核状态字段-sql", sql);
	 * sqlUtil.sql_exec(sql); sql = sqlmface.modSqlface(1, arg);
	 * log.send(DataType.basicType, "01162", "用户表中字段信息改变-是否女神字段-sql", sql);
	 * sqlUtil.sql_exec(sql);
	 * //sql="select user_id from renzheng_list where id="+arg[2]; //String
	 * userid=sqlUtil.get_string(sql);
	 * 
	 * //BizRenderTask send = new BizRenderTask(userid + "卍" +
	 * "很抱歉，您提交的认证资料不符合平台要求，具体原因请联系客服微信:yuese0829"); //send.run(); String jsonadd =
	 * "{\"success\":\"1\"}"; inOutUtil.return_ajax(jsonadd); }
	 */

	private void renzheng_checknopass(String[] arg) throws SQLException, IOException, ServletException {
		String admin_id = "";
		admin_id = request.getSession().getAttribute("id").toString();
		logDetector.send(DataType.basicType, "01208", "获取登录id : ", admin_id);
		arg[4] = admin_id;

		String ip = "";
		ip = getIpAddr(request);
		arg[5] = ip;

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "认证审核表信息改变-审核状态字段-sql", sql);
		sqlUtil.sql_exec(sql);
		sql = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "用户表中字段信息改变-是否女神字段-sql", sql);
		sqlUtil.sql_exec(sql);
		sql = sqlmface.modSqlface(2, arg);
		sqlUtil.sql_exec(sql);

		// sql="select user_id from renzheng_list where id="+arg[2];
		// String userid=sqlUtil.get_string(sql);

		// BizRenderTask send = new BizRenderTask(userid + "卍" +
		// "很抱歉，您提交的认证资料不符合平台要求，具体原因请联系客服微信:yuese0829");
		// send.run();
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);
	}

	private void cash_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("修改");
	}

	private void cash_mod1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/cash_modify_persent.jsp");
	}

	// A-boss-mod,photo_mod,5,1,
	// http://47.99.110.97:8090/img/gongyue.png,
	// http://47.99.110.97:8090/img/lunbo.jpg,消息,隐藏
	/**
	 * arg[2]:id arg[3]:排序 arg[4]:轮播图片 arg[5]:跳转地址 arg[6]:分属页面 arg[7]:显隐
	 */
	private void photo_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		// 如果上传图片为空,可能是用户忘记上传
		if ("".equals(arg[5])) {
			sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01168", "轮播图更改", sql);
			int a = sqlUtil.sql_exec(sql);
			inOutUtil.return_ajax("1");
		} else {
			sql = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01168", "轮播图更改", sql);
			int a = sqlUtil.sql_exec(sql);
			inOutUtil.return_ajax("1");
		}
	}

	/*
	 * String sql = sqlmface.modSqlface(0, arg); log.send(DataType.basicType,
	 * "01162", "轮播图更改", sql); int a = sqlUtil.sql_exec(sql);
	 * inOutUtil.return_ajax("1");
	 */
	// }

	private void photo2(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "轮播图修改1", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/photo_mod.jsp");
	}

	private void renzheng_checkpass(String[] arg) throws SQLException, IOException, ServletException {

		arg = Arrays.copyOfRange(arg, 0, arg.length + 8);
		arg[0] = arg[3];
		// 查询女神表中信息
		String sql3 = sqlmface.modSqlface(3, arg);
		logDetector.send(DataType.basicType, "01150", "查询女神表中信息是否有该用户数据-sql2:", sql3);
		String str = sqlUtil.get_string(sql3);
		// 女神表中信息有该用户数据
		if ("0".equals(str)) {

			// 审核状态改变
			String sql0 = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "认证审核通过-sql0", sql0);
			sqlUtil.sql_exec(sql0);
			// 查询认证表信息
			String sql1 = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01150", "查询认证表信息-sql1:", sql1);
			// sqlUtil.sql_exec(sql1);
			list = sqlUtil.get_list(sql1);
			// arg[0] = list.get(0).get("user_id").toString();

			arg[3] = list.get(0).get("nickname").toString();
			arg[4] = list.get(0).get("photo").toString();
			arg[5] = list.get(0).get("age").toString();
			arg[6] = list.get(0).get("city").toString();
			arg[7] = list.get(0).get("signature").toString();
			arg[8] = list.get(0).get("photo_album").toString();
			// arg[9] = list.get(0).get("selfie").toString();
			// arg[10] = list.get(0).get("signature").toString();

			// 修改用户表中信息
			String sql2 = sqlmface.modSqlface(2, arg);
			logDetector.send(DataType.basicType, "01150", "修改用户表中信息-sql2:", sql2);
			sqlUtil.sql_exec(sql2);

			// 插入角色表中信息
			String sql4 = sqlmface.modSqlface(4, arg);
			logDetector.send(DataType.basicType, "01150", "插入角色表中信息-sql4:", sql4);
			sqlUtil.sql_exec(sql4);
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			// 女神表中信息没有该用户数据
			// 删除该条审核信息
			String sql = sqlmface.modSqlface(5, arg);
			logDetector.send(DataType.basicType, "01150", "删除该条审核信息-sql5:", sql);
			sqlUtil.sql_exec(sql);
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

		// list = sqlUtil.get_list(sql2);

		// arg = Arrays.copyOfRange(arg, 0, arg.length + 11);

		// BizRenderTask send = new BizRenderTask(arg[4] + "卍" +
		// "欢迎入驻悦色平台！您的认证已经通过平台初步审核，请加客服微信:qumao518, 继续完成审核！");
		// send.run();

	}

	private void vip_mod1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("修改成功");
	}

	private void vip_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/vip_mod.jsp");
	}

	private void recharge_mod1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		inOutUtil.return_ajax("修改成功");
	}

	private void recharge_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/recharge_mod.jsp");
	}

	private void jujue_money(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "修改分销提成比例-sql", sql);
		sqlUtil.sql_exec(sql);
		String jsonadd = "{\"success\":\"1\"}";
		inOutUtil.return_ajax(jsonadd);

	}

	// A-boss-mod,picture_del,0,16,3
	private void picture_del(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "女神中的图片sql:", sql);
		String picture = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "女神中的图片String:", picture);
		List<String> list = Arrays.asList(picture.split(","));// 将数组转换为list集合
		List<String> arrayList = new ArrayList<String>(list);// 转换为ArrayLsit调用相关的remove方法
		arrayList.remove(Integer.parseInt(arg[4]) - 1);
		String pic = "";
		for (int i = 0; i < arrayList.size(); i++) {
			if (i == 0) {
				pic = arrayList.get(i);
			} else {
				pic = pic + "," + arrayList.get(i);
			}
		}
		arg[0] = pic;
		sql = sqlmface.modSqlface(1, arg);
		int a = sqlUtil.sql_exec(sql);
		if (a == 1) {
			inOutUtil.return_ajax("1");
		}
	}

	private void banned_cancel(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		sqlUtil.sql_exec(sql);
		BizRenderTask send = new BizRenderTask(arg[2] + "卍" + "解封");
		send.run();
	}

	private void anchor_banned(String[] arg) throws SQLException, IOException, ServletException {
		if (!arg[2].equals("")) {
			String sql = sqlmface.modSqlface(0, arg);
			sqlUtil.sql_exec(sql);
			BizRenderTask send = new BizRenderTask(arg[2] + "卍" + "封禁");
			send.run();
		}
	}

	private void recycle_hide(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "轮播图隐藏-sql : ", sql);
		int a = sqlUtil.sql_exec(sql);
	}

	private void recycle_appear(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "轮播图显示-sql : ", sql);
		int a = sqlUtil.sql_exec(sql);
	}

	private void admin_mod1(String[] arg) throws SQLException, IOException, ServletException {
		// 定义开关
		String str = "";
		String sql = "";
		String user = "";
		// 输入的账号和本身的账号一致
		if (arg[3].equals(arg[6])) {
			str = "t";
		} else {
			// 输入的账号和本身的账号不一致
			sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01168", "查询要添加的二级用户是否重复", sql);
			user = sqlUtil.get_string(sql);
			if (user.equals("0")) {
				// 查找到和其它账号不一致
				str = "t";
			} else {
				// 查找到和其它账号一致
				str = "f";
			}
		}

		int a = 0;
		if (str.equals("t")) {

			if (arg[3].length() > 5 && arg[3].matches("[0-9A-Za-z_]*") && arg[4].matches("[0-9A-Za-z_]*")) {
				sql = sqlmface.modSqlface(1, arg);
				logDetector.send(DataType.basicType, "01168", "修改为新用户名，密码", sql);
				a = sqlUtil.sql_exec(sql);
			} else {
				logDetector.send(DataType.basicType, "01168", "无法修改用户名，密码", "不满足条件");
				a = 0;
			}

		}

		/*
		 * String sql = sqlmface.modSqlface(0, arg); log.send(DataType.basicType,
		 * "01168", "查询要添加的二级用户是否重复", sql); user = sqlUtil.get_string(sql);
		 * if(user.equals("0")){
		 * 
		 * if(arg[3].length()>5&&arg[3].matches("[0-9A-Za-z_]*")&&arg[4].matches(
		 * "[0-9A-Za-z_]*")){ sql = sqlmface.modSqlface(1, arg);
		 * log.send(DataType.basicType, "01168", "修改为新用户名，密码", sql); a =
		 * sqlUtil.sql_exec(sql); }else{ log.send(DataType.basicType, "01168",
		 * "无法修改用户名，密码", "不满足条件"); a = 0; }
		 * 
		 * 
		 * }
		 */

		if (a == 1) {
			inOutUtil.return_ajax("1");
		} else {
			inOutUtil.return_ajax("0");
		}
	}

	/*
	 * private void admin_mod1(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.modSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "代理对应等级修改", sql); int a =
	 * sqlUtil.sql_exec(sql); if (a == 1) { inOutUtil.return_ajax("1"); } }
	 */

	private void admin_mod(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.modSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "代理对应等级修改", "/uiface/boss/admin_mod1.jsp");
		inOutUtil.return_list(list, "/uiface/boss/admin_mod1.jsp");
	}

	/***********************************
	 * deleteface
	 ******************************************/
	@Override
	public void deleteface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {

		// 删除女神审核中未通过数据
		case "renzheng_record_del":
			renzheng_record_del(arg);
			break;
		// 删除轮播图
		case "photo_del":
			photo_del(arg);
			break;
		// 删除打赏礼物
		case "dashang_del":
			dashang_del(arg);
			break;
		// 删除私信屏蔽关键词
		case "delchatfilter":
			delchatfilter(arg);
			break;
		// 删除vip充值
		case "vip_del":
			vip_del(arg);
			break;
		// 删除趣豆充值设置
		case "recharge_del":
			recharge_del(arg);
			break;
		// 删除举报信息
		case "bl_del":
			bl_del(arg);
			break;
		// 删除意见反馈信息
		case "com_del":
			com_del(arg);
			break;
		// 渠道商删除
		case "agent_delete":
			agent_delete(arg);
			break;
		// 删除鉴黄信息
		case "jianhuang_sc":
			jianhuang_sc(arg);
			break;
		}
	}

	private void jianhuang_sc(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void agent_delete(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void bl_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void com_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void recharge_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void vip_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void delchatfilter(String[] arg) throws SQLException, ServletException {

		String sql = sqlmface.deleteSqlface(arg);
		logDetector.send(DataType.noType, "01162", "delchatfilter()-sql: ", sql);
		int result = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.noType, "01162", "delchatfilter()-result: ", result);

		inOutUtil.return_ajax("{\"result\":\"" + result + "\"}");
	}

	private void dashang_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void photo_del(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.deleteSqlface(arg);
		int a = sqlUtil.sql_exec(sql);
	}

	private void renzheng_record_del(String[] arg) throws ServletException, IOException, SQLException {
		String sql = sqlmface.deleteSqlface(arg);
		logDetector.send(DataType.noType, "01162", "删除未通过的认证记录", sql);
		sqlUtil.sql_exec(sql);
	}

	/***********************************
	 * searchface
	 ******************************************/
	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case "admin_quanxian":
			admin_quanxian(arg);
			break;
		case "admin_quanxian1":
			admin_quanxian1(arg);
			break;

		// 用户登录
		case "admin_login":
			admin_login(arg);
			break;
		// 用户登录
		case "admin_logins":
			admin_logins(arg);
			break;

		// 管理员管理
		case "admin_list":
			admin_list(arg);
			break;
		// 会员管理
		case "memberbackstage":// 会员后台
			memberbackstage(arg);
			break;
		// 女神管理
		case "anchor_search1":
			anchor_search1(arg);
			break;
		case "get_photo":
			get_photo(arg);
			break;
		// 女神管理(查看相册)
		case "renzheng_photosearch1":
			renzheng_photosearch1(arg);
			break;
		// 充值明细
		case "pay_table_search":
			pay_table_search(arg);
			break;
		case "pay_table_search2":
			pay_table_search2(arg);
			break;
		// 个人充值明细
		case "pay_list":
			pay_list(arg);
			break;
		// 收入明细
		case "income_table_search":
			income_table_search(arg);
			break;
		case "income_table_search2":
			income_table_search2(arg);
			break;
		// 支出明细
		case "consumptionlist":
			consumptionlist(arg);
			break;
		case "consumptionlist2":
			consumptionlist2(arg);
			break;
		// 提现明细
		case "cash_withdrawal":
			cash_withdrawal(arg);
			break;
		case "cash_withdrawal_fx":
			cash_withdrawal_fx(arg);
			break;
		case "cash_withdrawal_fx2":
			cash_withdrawal_fx2(arg);
			break;
		case "cash_withdrawal2":
			cash_withdrawal2(arg);
			break;
		// 举报管理
		case "blacklist_manage":
			blacklist_manage(arg);
			break;
		case "certification_manage":
			certification_manage(arg);
			break;
		case "version_search":
			version_search(arg);
			break;
		// VIP充值设置
		case "vip_set":
			vip_set(arg);
			break;
		// 爱豆充值设置
		case "recharge_set":
			recharge_set(arg);
			break;
		// 认证管理(待审核)
		case "renzheng_v":
			renzheng_v(arg);
			break;
		case "log_search":
			log_search(arg);
			break;
		// 认证查看图片
		case "renzheng_photosearch":
			renzheng_photosearch(arg);
			break;
		// 广告轮播图设置
		case "recycle_photo":
			recycle_photo(arg);
			break;
		// 提现
		case "cash_set":
			cash_set(arg);
			break;
		// 女神审核(已通过)
		case "renzheng_v_passed":
			renzheng_v_passed(arg);
			break;
		// 女神审核(未通过)
		case "renzheng_v_no":
			renzheng_v_no(arg);
			break;
		// 用户信息管理
		case "album_list":
			album_list(arg);
			break;
		// 审核图片
		case "shenhe_photo":
			shenhe_photo(arg);
			break;
		// 个人收入明细
		case "income_table_search_daochu":
			income_table_search_daochu(arg);
			break;
		// 意见反馈
		case "complaint_search":
			complaint_search(arg);
			break;
		// 用户表中相册
		case "renzheng_photosearch2":
			renzheng_photosearch2(arg);
			break;
		// 系统通知
		case "notification_search":
			notification_search(arg);
			break;
		// 分销比例查询
		case "fenxiao_search_set":
			fenxiao_search_set(arg);
			break;
		// 分销比例设置(查询)
		case "fenxiao_search_mod":
			fenxiao_search_mod(arg);
			break;
		// 礼物打赏查询
		case "dashang_search_set":
			dashang_search_set(arg);
			break;
		// 私信屏蔽关键词
		case "getchatfilter":
			getchatfilter(arg);
			break;
		// 点击查看管理员密码
		case "admin_password":
			admin_password(arg);
			break;
		// 手动充值(管理员充值)
		case "recharge_user":
			recharge_user(arg);
			break;
		case "recharge_user2":
			recharge_user2(arg);
			break;
		// 礼物明细
		case "gift_record":
			gift_record(arg);
			break;
		case "gift_record2":
			gift_record2(arg);
			break;
		// 首页
		case "main_user":
			main_user(arg);
			break;
		// 查询客服信息
		case "service_search":
			service_search(arg);
			break;
		// 分销列表
		case "fenxiao_liebiao":
			fenxiao_liebiao(arg);
			break;
		// 分销(一级分销成员)
		case "fencheng_yiji":
			fencheng_yiji(arg);
			break;
		// 鉴黄列表
		case "jianhuang_list":
			jianhuang_list(arg);
			break;
		// 鉴黄设置(时间间隔)
		case "jianhuang_set":
			jianhuang_set(arg);
			break;
		// 代理商列表
		case "agentist1":
			agentist1(arg);
			break;
		// 渠道商提现
		case "acash_withdrawal":
			acash_withdrawal(arg);
			break;
		// 渠道商报表
		case "agent_analyse"://
			agent_analyse(arg);
			break;
		// 渠道商名称
		case "selagentlist":
			selagentlist(arg);
			break;
		// 渠道商登录
		case "agent_login":
			agent_login(arg);
			break;
		// 男用户：会员后台(渠道商)
		case "amemberbackstage":
			amemberbackstage(arg);
			break;
		// 女神用户：会员后台(渠道商)
		case "aanchor_search":
			aanchor_search(arg);
			break;
		// 查看男用户个个人明细(渠道商)
		case "pay_search":
			pay_search(arg);
			break;
		}
	}

	public void pay_search(String[] arg) throws SQLException, IOException, ServletException {
		arg[0] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01158", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01158", "总条数Int", total);
		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agentpay_search.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	public void aanchor_search(String[] arg) throws SQLException, IOException, ServletException {

		arg[0] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01158", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01158", "总条数Int", total);
		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);

		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		if ("tojsp".equals(arg[5])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_meberbackstage.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	public void amemberbackstage(String[] arg) throws SQLException, IOException, ServletException {
		arg[0] = request.getSession().getAttribute("channelcode").toString();
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01158", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01158", "总条数Int", total);
		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到会员列表SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01178", "获取到会员列表", list);
		if ("tojsp".equals(arg[5])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agenttcqo_meberbackstage.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	private void agent_login(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql1);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql1);
		logDetector.send(DataType.basicType, "01156", "admin_login()-list: ", list);
		if (list.size() == 1) {

			String channelcode = list.get(0).get("agent_channelcode") + "";
			HttpSession session = request.getSession();
			session.setAttribute("channelcode", channelcode);
			session.setMaxInactiveInterval(60 * 60 * 5);

			inOutUtil.return_listpage(list, pages, "/uiface/boss/agentcenter.jsp");
		} else {
			// request.getSession().setAttribute("adminLoginStatus", "err");
			inOutUtil.return_only("boss/agentLogin.jsp");
		}
	}

	public void selagentlist(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "相册图片审核-无条件搜索", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_ajax(JsonUtil.listToJson(list));
	}

	public void agent_analyse(String[] arg) throws SQLException, IOException, ServletException {

		if (arg[6].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			logDetector.send(DataType.basicType, "01162", "提现明细", pages);
			arg[2] = pages[2] + "";
			logDetector.send(DataType.basicType, "01162", "提现明细", arg);
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
//				String sum = sqlUtil.get_string(sql);
//				log.send(DataType.basicType, "01162", "提现明细---总和", sum);
//				if(list.size()>0){
//					list.get(0).put("sum", sum);
//				}
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_biganalyse.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			list = sqlUtil.get_list(sql);
			sql = sqlmface.searchSqlface(5, arg);
			logDetector.send(DataType.basicType, "01162", "提现明细", sql);
			if (arg[5].equals("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_biganalyse.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}

	}

	public void acash_withdrawal(String[] arg) throws SQLException, IOException, ServletException {

		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01178", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01178", "求总条数Int", total);

		pages = JyHelpManager_boss.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01178", "获取到cash_withdrawal表的SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		// log.send(DataType.basicType, "01178", "获取到cash_withdrawal表的集合",
		// list);
		if (list.size() > 0) {
			sql1 = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01178", "计算渠道商金额数量", sql1);
			String sum = sqlUtil.get_string(sql1);
			list.get(0).put("sum", sum);
		}

		if ("tojsp".equals(arg[4])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/agentcash_withdrawal1.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	public void agentist1(String[] arg) throws SQLException, IOException, ServletException {
		if (arg[3].equals("")) {
			String sql = sqlmface.searchSqlface(0, arg);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01162", "相册图片审核-无条件搜索", sql);
			list = sqlUtil.get_list(sql);
			if (arg[4].endsWith("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_list1.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		} else {
			String sql = sqlmface.searchSqlface(2, arg);
			int total = sqlUtil.get_int(sql);
			pages = JyHelpManager.pages(arg[2], total);
			arg[2] = pages[2] + "";
			sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01162", "相册图片审核-根据条件搜索", sql);
			list = sqlUtil.get_list(sql);
			if (arg[4].endsWith("tojsp")) {
				inOutUtil.return_listpage(list, pages, "/uiface/boss/agent_list1.jsp");
			} else {
				inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
			}
		}
	}

	public void jianhuang_set(String[] arg) throws SQLException, IOException, ServletException {
		// 查询鉴黄设置表
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01168", "查询鉴黄设置表", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/jianhuang_set.jsp");
	}

	/**
	 * arg[2]:页码 arg[3]:tojsp
	 */
	public void jianhuang_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String sql3 = "";
		String sh = "";
		String sk = "";
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01168", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01168", "总条数Int", total);
		pages = JyHelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 1);
		arg[0] = pages[2] + "";
		// arg[arg.length - 1] = JyHelpManager.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01168", "获取到普通用户列表SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		for (int i = 0; i < list.size(); i++) {
			arg[0] = list.get(i).get("id").toString();
			// 根据查询到的数据查找违规涉黄数据
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01168", "根据查询到的数据查找违规涉黄数据SQL", sql);
			sh = sqlUtil.get_string(sql);
			if ("1".equals(sh)) {
				list.get(i).put("sh", "t_sh");
			} else {
				list.get(i).put("sh", "f_sh");
			}
			// 根据查询到的数据查找违规涉恐数据
			sql3 = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01168", "根据查询到的数据查找违规涉恐数据SQL", sql3);
			sk = sqlUtil.get_string(sql3);
			if ("1".equals(sk)) {
				list.get(i).put("sk", "t_sk");
			} else {
				list.get(i).put("sk", "f_sk");
			}

		}

		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/jianhuang_list_01168.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	public void fencheng_yiji(String[] arg) throws SQLException, IOException, ServletException {

		/*
		 * String sql = sqlmface.searchSqlface(0, arg); log.send(DataType.basicType,
		 * "01152", "比例(查询)-sql:", sql); String yijifencheng = sqlUtil.get_string(sql);
		 */

		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01168", "求总条数SQL", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01168", "总条数Int", total);
		pages = JyHelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 1);
		arg[0] = pages[2] + "";
		// arg[arg.length - 1] = JyHelpManager.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01168", "获取到普通用户列表SQL", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		/*
		 * if(list.size()!=0){ for(int i=0;i<list.size();i++){ list.get(i).put("money",
		 * yijifencheng); list.get(i).put("ids", arg[4]); } }
		 */
		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/fencheng_yiji_01168.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	public void fenxiao_liebiao(String[] arg) throws SQLException, IOException, ServletException {
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		String ids = "";
		String nicknames = "";
		String phones = "";
		String yiji = "";
		String sql3 = "";
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-sql: ", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-count: ", count);
		int[] pages = JyHelpManager.pages(arg[2], count);

		arg[2] = pages[2] + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-list: ", list);

		for (int i = 0; i < list.size(); i++) {
			arg[0] = list.get(i).get("promoter_id").toString();
			arg[6] = list.get(i).get("user_id1").toString();
			if (arg[0].equals("0")) {
				list.get(i).put("ids", "");
				list.get(i).put("nicknames", "");
				list.get(i).put("phones", "");

			} else {
				sql = sqlmface.searchSqlface(2, arg);
				list1 = sqlUtil.get_list(sql);
				ids = list1.get(0).get("id").toString();
				nicknames = list1.get(0).get("nickname").toString();
				phones = list1.get(0).get("phone").toString();
				list.get(i).put("ids", ids);
				list.get(i).put("nicknames", nicknames);
				list.get(i).put("phones", phones);

			}
			// 一级人数
			sql3 = sqlmface.searchSqlface(3, arg);
			yiji = sqlUtil.get_string(sql3);

			list.get(i).put("yiji", yiji);

		}
		if ("tojsp".equals(arg[3])) {
			logDetector.send(DataType.basicType, "01168", "查找用户表信息list", "进入fenxiao_liebiao.jsp");
			inOutUtil.return_listpage(list, pages, "/uiface/boss/fencheng_list_01168.jsp");
		} else {
			logDetector.send(DataType.basicType, "01168", "查找用户表信息list", "没有进入fenxiao_liebiao.jsp");
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	private void service_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "complaint_search()-sql1: ", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/service_search.jsp");
	}

	private void admin_quanxian(String[] arg) {

		try {

			String sql1 = sqlmface.searchSqlface(-1, arg);
			logDetector.send(DataType.basicType, "01202", "agent_power_edit-sql1", sql1);
			ArrayList<Map<String, Object>> list1 = sqlUtil.get_list(sql1);
			logDetector.send(DataType.basicType, "01202", "agent_power_edit-list1", list1);

			inOutUtil.return_list(list1, "/uiface/boss/agent_power_add.jsp");
			logDetector.send(DataType.basicType, "01202", "agent_power_list-res", json);
		} catch (Exception e) {
			logDetector.send(DataType.exceptionType, "01202", "agent_power_list-res", e);
			// e.printStackTrace();
		}

	}

	private void admin_quanxian1(String[] arg) {
		try {
			logDetector.send(DataType.basicType, "01202", "agent_power_edit-arg", arg);
			String sql = sqlmface.searchSqlface(-1, arg);
			logDetector.send(DataType.basicType, "01202", "agent_power_edit-sql", sql);
			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
			// json = listToJsonTree(list);
			// inOutUtil.return_ajax(json);
			logDetector.send(DataType.basicType, "01168", "admin_quanxian-测试list", list);
			json = listToJsonTree(list);
			inOutUtil.return_ajax(json);

		} catch (Exception e) {
			logDetector.send(DataType.exceptionType, "01202", "agent_power_list-res", e);

		}
	}

	/*
	 * try { log.send(DataType.basicType, "01202", "agent_power_edit-arg", arg);
	 * String sql = sqlmface.searchSqlface(-1, arg); log.send(DataType.basicType,
	 * "01202", "agent_power_edit-sql", sql); ArrayList<Map<String, Object>> list =
	 * sqlUtil.get_list(sql); //json = listToJsonTree(list);
	 * //inOutUtil.return_ajax(json); log.send(DataType.basicType, "01168",
	 * "admin_quanxian-测试list", list); inOutUtil.return_list(list,
	 * "/uiface/boss/agent_power_add.jsp");
	 * 
	 * } catch (Exception e) { log.send(DataType.exceptionType, "01202",
	 * "agent_power_list-res", e);
	 * 
	 * }
	 */

	/* *//**
			 * @param arg arg[0] A-boss-search arg[1] agent_power_edit arg[2] 代理商id
			 *//*
				 * private void agent_power_edit(String[] arg) { try {
				 * log.send(DataType.basicType, "01202", "agent_power_edit-arg", arg); String
				 * sql0 = sqlmface.searchSqlface(-2, arg); log.send(DataType.basicType, "01202",
				 * "agent_power_edit-sql0", sql0); list = sqlUtil.get_list(sql0);
				 * log.send(DataType.basicType, "01202", "agent_power_edit-list", list);
				 * 
				 * String sql1 = sqlmface.searchSqlface(-1, arg); log.send(DataType.basicType,
				 * "01202", "agent_power_edit-sql1", sql1); ArrayList<Map<String, Object>> list1
				 * = sqlUtil.get_list(sql1); log.send(DataType.basicType, "01202",
				 * "agent_power_edit-list1", list1);
				 * 
				 * 
				 * inOutUtil.return_list(list, list1, "/uiface1/boss/agent_power_add.jsp");
				 * log.send(DataType.basicType, "01202", "agent_power_list-res", json); } catch
				 * (Exception e) { log.send(DataType.exceptionType, "01202",
				 * "agent_power_list-res", e); //e.printStackTrace(); } }
				 */

	/*
	 * private void main_user(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.searchSqlface(0, arg);
	 * log.send(DataType.basicType, "01168", "首页: ", sql); list =
	 * sqlUtil.get_list(sql); inOutUtil.return_list(list,
	 * "/uiface/boss/main_user.jsp"); }
	 */

	private void main_user(String[] arg) throws SQLException, IOException, ServletException {
		String[] sql = sqlmface.searchSqlface(0, arg).split(";");
		logDetector.send(DataType.basicType, "01168", "首页: ", sql);
		logDetector.send(DataType.basicType, "01208", "main_user-sql0: ", sql[0]);
		ArrayList<Map<String, Object>> list0 = sqlUtil.get_list(sql[0]);
		logDetector.send(DataType.basicType, "01208", "main_user-sql1: ", sql[1]);
		ArrayList<Map<String, Object>> list1 = sqlUtil.get_list(sql[1]);

		logDetector.send(DataType.basicType, "01208", "main_user-sql6: ", sql[6]);
		ArrayList<Map<String, Object>> list6 = sqlUtil.get_list(sql[6]);
		list1.addAll(list6);
		logDetector.send(DataType.basicType, "01208", "main_user-list1.addAll(list6); ", list1);

		logDetector.send(DataType.basicType, "01208", "main_user-sql2: ", sql[2]);
		ArrayList<Map<String, Object>> list2 = sqlUtil.get_list(sql[2]);
		logDetector.send(DataType.basicType, "01208", "main_user-sql3: ", sql[3]);
		ArrayList<Map<String, Object>> list3 = sqlUtil.get_list(sql[3]);
		logDetector.send(DataType.basicType, "01208", "main_user-sql4: ", sql[4]);
		ArrayList<Map<String, Object>> list4 = sqlUtil.get_list(sql[4]);
		logDetector.send(DataType.basicType, "01208", "main_user-sql5: ", sql[5]);
		ArrayList<Map<String, Object>> list5 = sqlUtil.get_list(sql[5]);
		inOutUtil.return_list5(list0, list1, list2, list3, list4, list5, "/uiface/boss/main_user.jsp");
	}

	private void gift_record(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String name = "";
		String price = "";
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-total: ", total);
		pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		for (int i = 0; i < list.size(); i++) {
			arg[0] = list.get(i).get("gift_id").toString();
			if ("".equals(arg[0])) {
				list.get(i).put("name", "未知");
				list.get(i).put("price", "未知");
			} else {
				sql = sqlmface.searchSqlface(2, arg);
				logDetector.send(DataType.basicType, "01162", "提现明细", sql);
				list1 = sqlUtil.get_list(sql);
				if (list1.size() != 0) {
					list.get(i).put("name", list1.get(0).get("name").toString());
					list.get(i).put("price", list1.get(0).get("price").toString());
				} else {
					list.get(i).put("name", "未知");
					list.get(i).put("price", "未知");
				}

			}
		}
		if (list.size() != 0) {
			// 查询礼物总价值
			sql = sqlmface.searchSqlface(3, arg);
			logDetector.send(DataType.basicType, "01170", "查询礼物总价值-sql: ", sql);
			String str = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01170", "查询礼物总价值-str: ", str);
			list.get(0).put("sum", str);
		}

		/*
		 * String sql = sqlmface.searchSqlface(2, arg); log.send(DataType.basicType,
		 * "01162", "提现明细", sql); String sum = sqlUtil.get_string(sql);
		 * log.send(DataType.basicType, "01162", "提现明细---总和", sum); if(list.size()>0){
		 * list.get(0).put("sum", sum); }
		 */

		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", list);
		if ("tojsp".equals(arg[6])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/gift_record.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	private void gift_record2(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String name = "";
		String price = "";
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01170", "gift_record2-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "gift_record2-total: ", total);
		pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "gift_record2-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);

		for (int i = 0; i < list.size(); i++) {
			arg[0] = list.get(i).get("gift_id").toString();
			if ("".equals(arg[0])) {
				list.get(i).put("name", "未知");
				list.get(i).put("price", "未知");
			} else {
				sql = sqlmface.searchSqlface(2, arg);
				logDetector.send(DataType.basicType, "01162", "提现明细", sql);
				list1 = sqlUtil.get_list(sql);
				if (list1.size() != 0) {
					list.get(i).put("name", list1.get(0).get("name").toString());
					list.get(i).put("price", list1.get(0).get("price").toString());
				} else {
					list.get(i).put("name", "未知");
					list.get(i).put("price", "未知");
				}

			}
		}

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "会员ID", "会员昵称", "女神ID", "女神昵称", "礼物ID", "礼物名称", "礼物价格" };
		// 导出字段
		String[] keyList = { "user_id1", "nickname1", "target_id", "nickname2", "user_id2", "name", "price" };

		try {
			String exportFileName = "礼物明细";
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//                OutputStream out1 = new FileOutputStream("c:/" );
//                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("礼物明细", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void recharge_user(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-total: ", total);
		pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		String sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		String sum = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);

		sql = sqlmface.searchSqlface(3, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		String nsum = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "提现明细---总和", nsum);

		if (list.size() > 0) {
			list.get(0).put("sum", sum);
			list.get(0).put("nsum", nsum);
		}

		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", list);
		if ("tojsp".equals(arg[6])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/recharge_user.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	private void recharge_user2(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01170", "recharge_user2-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "recharge_user2-total: ", total);
		pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "recharge_user2-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "管理员", "用户id", "用户昵称", "趣豆数量", "充值时间" };
		// 导出字段
		String[] keyList = { "recharge_name", "user_id", "nickname", "recharge_num", "recharge_time" };

		try {
			String exportFileName = "后台充值";
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//                OutputStream out1 = new FileOutputStream("c:/" );
//                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("后台充值", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void admin_password(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "getchatfilter()-sql: ", sql);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "getchatfilter()-list: ", list);

		inOutUtil.return_list(list, "/uiface/boss/admin_password.jsp");
	}

	private void getchatfilter(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "getchatfilter()-sql: ", sql);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "getchatfilter()-list: ", list);

		inOutUtil.return_list(list, "/uiface/boss/chatfilter_list.jsp");
	}

	// 分销比例查询
	private void dashang_search_set(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "主播列表", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/dashang_set.jsp");
	}

	private void fenxiao_search_mod(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		arg[1] = "fenxiao_search_set";
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "fenxiao_search_mod:", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/fenxiao_search_set.jsp");
	}

	// 分销比例查询
	private void fenxiao_search_set(String[] arg) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "主播列表", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/fenxiao_set.jsp");
	}

	private void notification_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		int a = sqlUtil.get_countint(sql);
		pages = JyHelpManager.pages(arg[2], a);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		if (arg[4].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/notification_list.jsp");
		} else if (arg[4].equals("tojson")) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	private void complaint_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "complaint_search()-sql1: ", sql);
		int a = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], a);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "complaint_search()-sql2: ", sql);
		list = sqlUtil.get_list(sql);
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/complaint.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}

	private void income_table_search_daochu(String[] arg) throws SQLException, IOException, ServletException {
		arg[1] = "income_table_search_daochu";
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "income_table_search_daochu()-sql: ", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01162", "income_table_search_daochu()-count: ", count);
		int[] pages = JyHelpManager.pages(arg[2], count);

		arg[4] = pages[2] + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "income_table_search_daochu()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01162", "income_table_search_daochu()-list: ", list);

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "用户id", "用户昵称", "收入类型", "收入价格", "消费者ID", "消费者昵称", "时间" };
		// 导出字段
		String[] keyList = { "u_user_id", "u_nickname", "type", "money", "z_user_id", "z_nickname", "time" };

		try {
			String exportFileName = "收入明细";
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//                OutputStream out1 = new FileOutputStream("c:/" );
//                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("收入明细", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void album_list(String[] arg) throws SQLException, IOException, ServletException {
		String sss = "";
		String sql = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01162", "审核用户修改信息", sql);
		int total = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], total);
		// arg[2] = pages[2] + "";
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";

		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "审核用户修改信息", sql);
		list = sqlUtil.get_list(sql);
		for (int i = 0; i < list.size(); i++) {
			// 根据id查询title
			arg[0] = list.get(i).get("cid").toString();
			sql = sqlmface.searchSqlface(2, arg);
			sss = sqlUtil.get_string(sql);
			sss = replaceBlank(sss);
			list.get(i).put("signature", sss);
		}

		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/mod_info.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}
	/*
	 * if (arg[3].equals("")) { String sql = sqlmface.searchSqlface(0, arg); int
	 * total = sqlUtil.get_int(sql); pages = hm.pages(arg[2], total); arg[2] =
	 * pages[2] + ""; sql = sqlmface.searchSqlface(1, arg);
	 * log.send(DataType.basicType, "01162", "相册图片审核-无条件搜索", sql); list =
	 * sqlUtil.get_list(sql); if (arg[4].endsWith("tojsp")) {
	 * inOutUtil.return_listpage(list, pages, "/uiface/boss/mod_info.jsp"); } else {
	 * inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages)); } } else {
	 * String sql = sqlmface.searchSqlface(2, arg); int total =
	 * sqlUtil.get_int(sql); pages = hm.pages(arg[2], total); arg[2] = pages[2] +
	 * ""; sql = sqlmface.searchSqlface(3, arg); log.send(DataType.basicType,
	 * "01162", "相册图片审核-根据条件搜索", sql); list = sqlUtil.get_list(sql); if
	 * (arg[4].endsWith("tojsp")) { inOutUtil.return_listpage(list, pages,
	 * "/uiface/boss/mod_info.jsp"); } else {
	 * inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages)); } }
	 */

	// 未通过
	private void renzheng_v_no(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		int totle = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], totle);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		/*
		 * for (int i = 0; i < list.size(); i++) { String a = (String)
		 * list.get(i).get("picture"); String a1[] = a.split(","); int b = a1.length;
		 * String a2 = String.valueOf(b); list.get(i).put("photo1", a2); }
		 */
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/renzheng_check_no.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	// 已通过
	private void renzheng_v_passed(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		int totle = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], totle);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		/*
		 * for (int i = 0; i < list.size(); i++) { String a = (String)
		 * list.get(i).get("picture"); String a1[] = a.split(","); int b = a1.length;
		 * String a2 = String.valueOf(b); list.get(i).put("photo1", a2); }
		 */
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/renzheng_check_passed.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	public void cash_set(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "提现金额-提现手续费设置", sql);
		inOutUtil.return_list(list, "/uiface/boss/cash_set.jsp");
	}

	private void recycle_photo(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		int b = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], b);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/recycle_photo.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}

	private void renzheng_photosearch(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "认证图片查看 ", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/renzheng_photosearch.jsp");

	}

	// 待审核
	private void renzheng_v(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		int totle = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], totle);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		list = sqlUtil.get_list(sql);
		/*
		 * for (int i = 0; i < list.size(); i++) { String a = (String)
		 * list.get(i).get("picture"); String a1[] = a.split(","); int b = a1.length;
		 * String a2 = String.valueOf(b); list.get(i).put("photo1", a2); }
		 */
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/renzheng_check.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	private void log_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "日志查询 - sql0 ", sql);
		int totle = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], totle);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01208", "日志查询 - sql1 ", sql);
		list = sqlUtil.get_list(sql);
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/log_search.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	public void recharge_set(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "vip充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/recharge_set.jsp");
	}

	public void vip_set(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01162", "vip充值设置查询", sql);
		inOutUtil.return_list(list, "/uiface/boss/vip_set.jsp");
	}

	private void blacklist_manage(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "complaint_search()-sql1: ", sql);
		int a = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], a);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "complaint_search()-sql2: ", sql);
		list = sqlUtil.get_list(sql);
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/blacklist.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}

	private void certification_manage(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "certification_manage()-sql1: ", sql);
		int a = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], a);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01208", "certification_manage()-sql2: ", sql);
		list = sqlUtil.get_list(sql);
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/certification.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}

	private void version_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "version_search()-sql1: ", sql);
		int a = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], a);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01208", "version_search()-sql2: ", sql);
		list = sqlUtil.get_list(sql);
		if (arg[3].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/version_search.jsp");
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}
	/*
	 * public void blacklist_manage(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.searchSqlface(0, arg);
	 * log.send(DataType.basicType, "01162", "举报拉黑", sql); int totle =
	 * sqlUtil.get_int(sql); pages = hm.pages(arg[2], totle); arg[2] = pages[2] +
	 * ""; sql = sqlmface.searchSqlface(1, arg); arg = Arrays.copyOfRange(arg, 0,
	 * arg.length + 2); list = sqlUtil.get_list(sql); for (int i = 0; i <
	 * list.size(); i++) { arg[5] = list.get(i).get("report_id") + ""; arg[6] =
	 * list.get(i).get("reported_id") + ""; sql = sqlmface.searchSqlface(2, arg);
	 * log.send(DataType.basicType, "01162", "举报拉黑", sql); list1 =
	 * sqlUtil.get_list(sql);
	 * 
	 * if(list1.size() <= 0) { list.remove(i); if(i > 0) { i--; } continue; }
	 * 
	 * list.get(i).put("name1", list1.get(0).get("nickname"));
	 * list.get(i).put("phone1", list1.get(0).get("phone")); sql =
	 * sqlmface.searchSqlface(3, arg); log.send(DataType.basicType, "01162", "举报拉黑",
	 * sql); list2 = sqlUtil.get_list(sql); list.get(i).put("name2",
	 * list2.get(0).get("nickname")); list.get(i).put("phone2",
	 * list2.get(0).get("phone")); } if (arg[4].equals("tojsp")) {
	 * inOutUtil.return_listpage(list, pages, "/uiface/boss/blacklist.jsp"); } else
	 * { inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages)); } }
	 */

	private void cash_withdrawal_fx(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		int total = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], total);
		// arg[2] = pages[2] + "";
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		list = sqlUtil.get_list(sql);
		sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		String sum = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);
		if (list.size() > 0) {
			list.get(0).put("sum", sum);
		}
		if (arg[5].equals("tojsp")) {
			// if (arg[7].equals("0")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/cash_withdrawal_fx.jsp");
			/*
			 * } else { inOutUtil.return_listpage(list, pages,
			 * "/uiface/boss/cash_withdrawal1.jsp"); }
			 */
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	private void cash_withdrawal_fx2(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		int total = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], total);
		// arg[2] = pages[2] + "";
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		list = sqlUtil.get_list(sql);
		sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		String sum = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "用户id", "用户昵称", "提现时间", "提现金额", "提现账号", "订单号", "提现状态" };
		// 导出字段
		String[] keyList = { "user_id1", "nickname", "time", "cash", "tixian_account", "payorder_id", "status" };

		try {
			String exportFileName = "提现明细";
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//                OutputStream out1 = new FileOutputStream("c:/" );
//                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("提现明细", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * arg[0] A-boss-search arg[1] cash_withdrawal arg[2] page arg[3] startdate
	 * arg[4] enddate arg[5] tojsp tojson arg[6] arg[7] arg[8]: id号(按id搜索用户) arg[9]:
	 * 昵称(按用户昵称搜索用户)
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void cash_withdrawal(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		int total = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], total);
		// arg[2] = pages[2] + "";
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		list = sqlUtil.get_list(sql);
		sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		String sum = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);
		if (list.size() > 0) {
			list.get(0).put("sum", sum);
		}
		if (arg[5].equals("tojsp")) {
			// if (arg[7].equals("0")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/cash_withdrawal.jsp");
			/*
			 * } else { inOutUtil.return_listpage(list, pages,
			 * "/uiface/boss/cash_withdrawal1.jsp"); }
			 */
		} else {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	private void cash_withdrawal2(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		int total = sqlUtil.get_int(sql);
		pages = JyHelpManager.pages(arg[2], total);
		// arg[2] = pages[2] + "";
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		list = sqlUtil.get_list(sql);
		sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "用户id", "用户昵称", "提现时间", "提现金额", "提现账号", "订单号", "提现状态" };
		// 导出字段
		String[] keyList = { "user_id1", "nickname", "time", "cash", "tixian_account", "payorder_id", "status" };

		try {
			String exportFileName = "提现明细";
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//                OutputStream out1 = new FileOutputStream("c:/" );
//                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("提现明细", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 消费列表 arg[0]: A-user-search arg[1]: consumptionlist arg[2]: pageindex arg[3]:
	 * out tojson/tojsp arg[4]: pagefirst arg[5]: userid 用户id arg[6]: username 用户昵称
	 * arg[7]: startdate 开始日期 arg[8]: enddate 结束日期 arg[9]: date
	 * 按年或按月查找(与开始日期和结束日期互斥)
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void consumptionlist(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-sql: ", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-count: ", count);
		int[] pages = JyHelpManager.pages(arg[2], count);

		arg[4] = pages[2] + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-list: ", list);

		if (list.size() != 0) {
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "总支出()-sql2: ", sql);
			String str = sqlUtil.get_string(sql);
			list.get(0).put("sum", str);
		}

		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/consumption_list.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	private void consumptionlist2(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist2()-sql: ", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01162", "consumptionlist2()-count: ", count);
		int[] pages = JyHelpManager.pages(arg[2], count);

		arg[4] = pages[2] + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist2()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01162", "consumptionlist2()-list: ", list);

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "用户id", "用户昵称", "支出类型", "支出数量", "收入者ID", "收入者昵称", "时间" };
		// 导出字段
		String[] keyList = { "u_user_id", "u_nickname", "type", "num", "z_user_id", "z_nickname", "time" };

		try {
			String exportFileName = "支出明细";
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//                OutputStream out1 = new FileOutputStream("c:/" );
//                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("支出明细", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void income_table_search(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-sql: ", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-count: ", count);
		int[] pages = JyHelpManager.pages(arg[2], count);

		arg[4] = pages[2] + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01162", "consumptionlist()-list: ", list);
		if (list.size() != 0) {
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "总收入()-sql: ", sql);
			String str = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01162", "总收入()-str: ", str);
			list.get(0).put("sum", str);
		}

		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/income_table.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	private void income_table_search2(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "income_table_search2()-sql: ", sql);
		int count = sqlUtil.get_int(sql);
		logDetector.send(DataType.basicType, "01162", "income_table_search2()-count: ", count);
		int[] pages = JyHelpManager.pages(arg[2], count);

		arg[4] = pages[2] + "";

		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "income_table_search2()-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01162", "income_table_search2()-list: ", list);
		if (list.size() != 0) {
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01162", "总收入()-sql: ", sql);
			String str = sqlUtil.get_string(sql);
			logDetector.send(DataType.basicType, "01162", "总收入()-str: ", str);
			list.get(0).put("sum", str);
		}

		if ("tojsp".equals(arg[3])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/income_table.jsp");
		} else if ("tojson".equals(arg[3])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}
	/*
	 * private void income_table_search(String[] arg) throws SQLException,
	 * IOException, ServletException {
	 * 
	 * String sql = sqlmface.searchSqlface(-1, arg); log.send(DataType.basicType,
	 * "01162", "提现明细", sql); int total = sqlUtil.get_int(sql); pages =
	 * hm.pages(arg[2], total); //arg[2] = pages[2] + ""; arg =
	 * Arrays.copyOfRange(arg, 0, arg.length + 2); arg[arg.length - 2] = pages[2] +
	 * ""; arg[arg.length - 1] = JyHelpManager.item + ""; sql =
	 * sqlmface.searchSqlface(1, arg); log.send(DataType.basicType, "01162", "提现明细",
	 * sql); list = sqlUtil.get_list(sql); String xf_name = ""; // 追加消费者昵称字段 for(int
	 * i = 0; i < list.size(); i++) { arg[0] = list.get(i).get("pay_id").toString();
	 * if("".equals(arg[0])){ list.get(i).put("xf_name", ""); }else{ sql =
	 * sqlmface.searchSqlface(2, arg); log.send(DataType.basicType, "01162",
	 * "追加消费者昵称字段", sql); xf_name = sqlUtil.get_string(sql);
	 * list.get(i).put("xf_name", xf_name); }
	 * 
	 * 
	 * Map<String,Object> item = list.get(i); String[] subArg =
	 * {"","getcustomerinfo",item.get("pay_id")+""}; sql = sqlmface.searchSqlface(2,
	 * subArg); ArrayList<Map<String,Object>> listCustomer = sqlUtil.get_list(sql);
	 * if(listCustomer.size() > 0) { item.put("payer",
	 * listCustomer.get(0).get("nickname")+""); } else { item.put("payer", ""); }
	 * 
	 * 
	 * }
	 * 
	 * sql = sqlmface.searchSqlface(3, arg); log.send(DataType.basicType, "01162",
	 * "提现明细", sql); String sum = sqlUtil.get_string(sql);
	 * log.send(DataType.basicType, "01162", "提现明细---总和", sum);
	 * 
	 * if(list.size()>0){ list.get(0).put("sum", sum); } if (arg[5].equals("tojsp"))
	 * { inOutUtil.return_listpage(list, pages, "/uiface/boss/income_table.jsp"); }
	 * else { inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages)); }
	 * 
	 * }
	 */

	private void pay_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01168", "pay_list-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01168", "pay_list-total: ", total);
		pages = JyHelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";

		String sql2 = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01168", "pay_list-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01168", "pay_list-sql2: ", list);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				sql1 = sqlmface.searchSqlface(3, arg);
				logDetector.send(DataType.basicType, "01168", "求和: ", sql1);
				String sum = sqlUtil.get_string(sql1);
				list.get(i).put("sum", sum);
			}
		}

		if ("tojsp".equals(arg[4])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/pay_list.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	private void pay_table_search(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-total: ", total);
		pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);

		/*
		 * for(int i=0;i<list.size();i++){
		 * arg[0]=list.get(i).get("up_agentid").toString(); sql2 =
		 * sqlmface.searchSqlface(2, arg); log.send(DataType.basicType, "01170",
		 * "pay_table_search-sql1: ", sql2); String agent =sqlUtil.get_string(sql2);
		 * list.get(i).put("agentname", agent); }
		 */
		String sql = sqlmface.searchSqlface(2, arg);
		logDetector.send(DataType.basicType, "01162", "提现明细", sql);
		String sum = sqlUtil.get_string(sql);
		logDetector.send(DataType.basicType, "01162", "提现明细---总和", sum);
		if (list.size() > 0) {
			list.get(0).put("sum", sum);
		}

		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", list);
		if ("tojsp".equals(arg[6])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/pay_table.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}

	private void pay_table_search2(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(-1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search2-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search2-total: ", total);
		pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search2-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);
		logDetector.send(DataType.basicType, "01170", "pay_table_search2-list: ", list);

		HttpServletRequest request = inOutUtil.getRequest();
		HttpServletResponse response = inOutUtil.getResponse();
		// 导出功能
		String[] cellname = { "序号", "用户id", "用户昵称", "充值方式", "充值金额", "趣豆数量", "充值状态", "订单号", "充值时间" };
		logDetector.send(DataType.basicType, "01170", "pay_table_search2-cellname: ", cellname);
		// 导出字段
		String[] keyList = { "ids", "nickname", "pay_type", "pay_price", "pay_value", "pay_status", "payorder_id",
				"pay_time" };
		logDetector.send(DataType.basicType, "01170", "pay_table_search2-keyList: ", keyList);
		try {
			String exportFileName = "充值记录表";
			logDetector.send(DataType.basicType, "01170", "pay_table_search2-exportFileName: ", exportFileName);
			response.setContentType("application/vnd.ms-excel");
			// 根据浏览器类型处理文件名称
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			if (agent.indexOf("firefox") > -1) {// 若是火狐
				exportFileName = new String(exportFileName.getBytes("UTF-8"), "ISO8859-1");
			} else {// 其他浏览器
				exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF-8");
			}
			response.reset();
			OutputStream out = response.getOutputStream();
			// 保存导出的excel的名称
			response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName + ".xls");
			if (list != null) {
//	                OutputStream out1 = new FileOutputStream("c:/" );
//	                log.send(LogDetect.DataType.basicType, "01212", "导出列表()-sql2: ", "导出"+list);
				com.net.aipeng.redirect.resolverE.interface4.NewExportToExcelUtil.exportExcel("充值记录表", cellname, list,
						keyList, out, logDetector);
			}
			out.flush();
			out.close();

		} catch (UnsupportedEncodingException e) {
			logDetector.send(DataType.basicType, "01212", "导出列表()-sql2: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logDetector.send(DataType.basicType, "01170", "导出-结束   ", "");

	}

	private void shenhe_photo(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "认证图片查看 ", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/shenhe_photo.jsp");

	}

	private void renzheng_photosearch1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "认证图片查看 ", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/renzheng_photosearch1.jsp");

	}

	private void renzheng_photosearch2(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "认证图片查看 ", sql);
		list = sqlUtil.get_list(sql);
		inOutUtil.return_list(list, "/uiface/boss/renzheng_photosearch2.jsp");

	}

	// A-boss-search,anchor_search1,1,0,tojsp,0,,,

	// A-boss-search,anchor_search1,2,0,tojson,0,,,
	// ar?p0=A-boss-search&p1=anchor_search1&p2="+pageIndex+"&p3="+searchtxt+"&p4=tojson&p5="+searchtxt1+"&p6="+startdate+"&p7="+enddate+"&p8="+searchname+"&p9="+nickname;
	/**
	 * arg[2]:页码 arg[3]:ID arg[4]:tojsp arg[5]:手机号 arg[6]:开始时间 arg[7]:结束时间
	 * arg[8]:账号状态 arg[9]:昵称
	 */
	public void get_photo(String[] arg) throws SQLException, IOException, ServletException {

		String sql0 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01208", "get_photo-sql0: ", sql0);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql0);

		logDetector.send(DataType.basicType, "01208", "get_photo-list: ", list);

		inOutUtil.return_listpage(list, pages, "/uiface/boss/get_photo.jsp");

	}

	public void anchor_search1(String[] arg) throws SQLException, IOException, ServletException {
		String sql = "";
		String is_nvshen = "";
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-total: ", total);
		pages = JyHelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[arg.length - 2] = pages[2] + "";
		arg[arg.length - 1] = JyHelpManager_boss.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);

		for (int i = 0; i < list.size(); i++) {
			is_nvshen = list.get(i).get("is_goddess").toString();
			if (!"1".equals(is_nvshen)) {
				// b.gifts_sum,b.wordchat_price,b.videochat_price,b.voicechat_price,b.jieting_rate,b.online_time,b.time
				// as ns_tims,a.last_login_time,b.pingjia
				list.get(i).put("gifts_sum", "");
				list.get(i).put("wordchat_price", "");
				list.get(i).put("videochat_price", "");
				list.get(i).put("voicechat_price", "");
				list.get(i).put("jieting_rate", "");
				list.get(i).put("online_time", "");
				list.get(i).put("ns_tims", "");
				// list.get(i).put("last_login_time", "");
				list.get(i).put("pingjia", "");
			} else {
				arg[0] = list.get(i).get("id").toString();
				sql = sqlmface.searchSqlface(2, arg);
				logDetector.send(DataType.basicType, "01168", "查询女神表信息", sql);
				list1 = sqlUtil.get_list(sql);
				list.get(i).put("gifts_sum", list1.get(0).get("gifts_sum").toString());
				list.get(i).put("wordchat_price", list1.get(0).get("wordchat_price").toString());
				list.get(i).put("videochat_price", list1.get(0).get("videochat_price").toString());
				list.get(i).put("voicechat_price", list1.get(0).get("voicechat_price").toString());
				list.get(i).put("jieting_rate", list1.get(0).get("jieting_rate").toString());
				list.get(i).put("online_time", list1.get(0).get("online_time").toString());
				list.get(i).put("ns_tims", list1.get(0).get("ns_tims").toString());
				// list.get(i).put("last_login_time",
				// list1.get(0).get("last_login_time").toString());
				list.get(i).put("pingjia", list1.get(0).get("pingjia").toString());
			}
			/*
			 * arg[0] = list.get(i).get("id").toString(); sql1 = sqlmface.searchSqlface(2,
			 * arg); log.send(DataType.basicType, "01170", ": ", sql1);
			 */
		}

		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", list);

		/*
		 * if(list.size()>0){ if(!"".equals(arg[11])){ //计算分析的值 String sql12 =
		 * sqlmface.searchSqlface(3, arg); log.send(DataType.basicType, "01170",
		 * "pay_table_search-sql2: ", sql12); String fx_num = sqlUtil.get_string(sql12);
		 * list.get(0).put("fx_num", fx_num); }else{ list.get(0).put("fx_num", "0"); } }
		 * 
		 * String sql3 = ""; String tjr_name = ""; for(int i=0;i<list.size();i++){
		 * arg[0] = list.get(i).get("promoter_id").toString(); if(!"0".equals(arg[0])){
		 * sql3 = sqlmface.searchSqlface(4, arg); log.send(DataType.basicType, "01170",
		 * "pay_table_search-sql3: ", sql3); tjr_name = sqlUtil.get_string(sql3);
		 * list.get(i).put("tjr_name", tjr_name); }else{ list.get(i).put("tjr_name",
		 * ""); } }
		 */

		if ("tojsp".equals(arg[4])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/anchor_list1.jsp");
		} else {
			/*
			 * Map<String, Object> map = new HashMap<String, Object>(); map.put("nickname",
			 * "张三"); ArrayList<Map<String, Object>> list11 = new ArrayList<Map<String,
			 * Object>>(); list11.add(map);
			 */
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}

	}

	/**
	 * p0=A-boss-search&p1=memberbackstage&p2="+pageIndex+"&p3="+startdate+"&p4="+enddate+"&p5=tojson&p6="+searchtxt+"&p7="+phone+"&p8="+gender+"&p9="+member+"&p10="+recommand+"&p11="+sealing;
	 * arg[2]:页码 arg[3]:开始时间 arg[4]:结束时间 arg[5]:tojson arg[6]:ID arg[7]:手机号
	 * arg[8]:gender arg[9]:是否会员 arg[10]:是否推荐 arg[11]:是否封禁
	 */
	public void memberbackstage(String[] arg) throws SQLException, IOException, ServletException {

		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql1: ", sql1);
		int total = sqlUtil.get_int(sql1);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-total: ", total);
		pages = JyHelpManager.pages(arg[2], total);
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2);
		arg[0] = pages[2] + "";
		// arg[arg.length - 1] = JyHelpManager.item + "";
		String sql2 = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", sql2);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql2);

		logDetector.send(DataType.basicType, "01170", "pay_table_search-sql2: ", list);

		/*
		 * if(list.size()>0){ if(!"".equals(arg[12])){ //计算分析的值 String sql12 =
		 * sqlmface.searchSqlface(2, arg); log.send(DataType.basicType, "01170",
		 * "pay_table_search-sql2: ", sql12); String fx_num = sqlUtil.get_string(sql12);
		 * list.get(0).put("fx_num", fx_num); }else{ list.get(0).put("fx_num", "0"); } }
		 */
		String sql3 = "";
		String tjr_name = "";
		for (int i = 0; i < list.size(); i++) {
			arg[0] = list.get(i).get("promoter_id").toString();
			if (!"0".equals(arg[0])) {
				sql3 = sqlmface.searchSqlface(3, arg);
				logDetector.send(DataType.basicType, "01170", "pay_table_search-sql3: ", sql3);
				tjr_name = sqlUtil.get_string(sql3);
				list.get(i).put("tjr_name", tjr_name);
			} else {
				list.get(i).put("tjr_name", "");
			}
		}

		if ("tojsp".equals(arg[5])) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/tcqo_meberbackstage01178.jsp");
		} else {
			json = JsonUtil.listPageToJson(list, pages);
			inOutUtil.return_ajax(json);
		}
	}
	/*
	 * if (arg[6].equals("0")) { String sql1 = sqlmface.searchSqlface(0, arg);
	 * log.send(DataType.basicType, "01158", "求总条数SQL", sql1); int total =
	 * sqlUtil.get_int(sql1); log.send(DataType.basicType, "01158", "总条数Int",
	 * total); pages = JyHelpManager.pages(arg[2], total); arg =
	 * Arrays.copyOfRange(arg, 0, arg.length + 2); arg[arg.length - 2] = pages[2] +
	 * ""; arg[arg.length - 1] = JyHelpManager.item + ""; String sql2 =
	 * sqlmface.searchSqlface(1, arg); log.send(DataType.basicType, "01178",
	 * "获取到会员列表SQL", sql2); ArrayList<Map<String, Object>> list =
	 * sqlUtil.get_list(sql2);
	 * 
	 * if ("tojsp".equals(arg[5])) { inOutUtil.return_listpage(list, pages,
	 * "/uiface/boss/tcqo_meberbackstage01178.jsp"); } else { json =
	 * JsonUtil.listPageToJson(list, pages); inOutUtil.return_ajax(json); } } else
	 * if (!arg[6].equals("0")) { int count = 1; String sqlm =
	 * sqlmface.searchSqlface(3, arg); log.send(DataType.basicType, "01150",
	 * "查询魅聊号（）sqlm:", sqlm); list = sqlUtil.get_list(sqlm);
	 * log.send(DataType.basicType, "01150", "查询魅聊号（）list:", list); pages =
	 * JyHelpManager.pages(arg[2], count);
	 * 
	 * if (list.size() != 0) {
	 * 
	 * if ("tojsp".equals(arg[5])) { inOutUtil.return_listpage(list, pages,
	 * "/uiface/boss/tcqo_meberbackstage01178.jsp"); } else { json =
	 * JsonUtil.listPageToJson(list, pages); inOutUtil.return_ajax(json); }
	 * 
	 * 
	 * } }
	 */

	private void admin_list(String[] arg) throws SQLException, IOException, ServletException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01162", "admin_list-sql: ", sql);
		int a = sqlUtil.get_countint(sql);
		pages = JyHelpManager.pages(arg[2], a);
		arg[2] = pages[2] + "";
		sql = sqlmface.searchSqlface(1, arg);
		logDetector.send(DataType.basicType, "01162", "admin_list-sql: ", sql);
		list = sqlUtil.get_list(sql);
		if (arg[4].equals("tojsp")) {
			inOutUtil.return_listpage(list, pages, "/uiface/boss/admin_list.jsp");
		} else if (arg[4].equals("tojson")) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}

	}

	/**
	 * arg[2]:用户名 arg[3]:密码
	 */
	/*
	 * private void admin_logins(String[] arg) throws SQLException, IOException,
	 * ServletException { arg=Arrays.copyOfRange(arg, 0, arg.length+2); //arg[3]
	 * arg[4] //查询用户密码 String sql = sqlmface.searchSqlface(0, arg);
	 * log.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql); String
	 * str = sqlUtil.get_string(sql);
	 * 
	 * 
	 * 
	 * if(str.equals(arg[4])){
	 * 
	 * String sql5 = sqlmface.addSqlface(5, arg); log.send(DataType.basicType,
	 * "01208", "登录成功日志 - sql5", sql5); int a = sqlUtil.sql_exec(sql5);
	 * 
	 * //用户存在,用户id sql = sqlmface.searchSqlface(1, arg);
	 * log.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql); arg[0] =
	 * sqlUtil.get_string(sql); //查询一级列表 sql = sqlmface.searchSqlface(2, arg);
	 * log.send(DataType.basicType, "01156", "查询一级列表: ", sql); //一级
	 * ArrayList<Map<String, Object>> menu1List = sqlUtil.get_list(sql); for(int
	 * i=0;i<menu1List.size();i++){ Map<String, Object> menu1=menu1List.get(i);
	 * arg[5] = menu1.get("menu_id")+""; sql = sqlmface.searchSqlface(3, arg);
	 * log.send(DataType.basicType, "01156", "查询二级列表: ", sql); //二级
	 * ArrayList<Map<String, Object>> menu2List=sqlUtil.get_list(sql);
	 * menu1.put("itemlist", menu2List); log.send(DataType.basicType, "01156",
	 * "查询二级列表menu2List: ", menu2List); log.send(DataType.basicType, "01156",
	 * "查询二级列表menu1: ", menu1); } //获取后台用户信息 sql = sqlmface.searchSqlface(4, arg);
	 * log.send(DataType.basicType, "01156", "获取后台用户信息: ", sql);
	 * log.send(DataType.basicType, "01156", "获取后台用户信息menu1List: ", menu1List); list
	 * = sqlUtil.get_list(sql); HttpSession session = request.getSession();
	 * session.setAttribute("admin_name", list.get(0).get("username").toString());
	 * session.setAttribute("id", arg[0]); session.setAttribute("admin", "success");
	 * session.setMaxInactiveInterval(60 * 60 * 5); session.setAttribute("menulist",
	 * menu1List); inOutUtil.return_listpage(list, pages,
	 * "/uiface/boss/admincenter.jsp"); }else{ //不存在该用户 String sql6 =
	 * sqlmface.addSqlface(6, arg); log.send(DataType.basicType, "01208",
	 * "登录失败日志 - sql6", sql6); int a = sqlUtil.sql_exec(sql6);
	 * response.sendRedirect("http://120.27.98.128:9810/uiface/boss/adminLogin.jsp")
	 * ; }
	 * 
	 * 
	 * 
	 * }
	 */

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}

	private void admin_logins(String[] arg) throws SQLException, IOException, ServletException {
		arg = Arrays.copyOfRange(arg, 0, arg.length + 2); // arg[3] arg[4]
		// 查询用户密码
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql);
		String str = sqlUtil.get_string(sql);

		if (str.equals(arg[7])) {

			String ip = "";
			ip = getIpAddr(request);
			// arg[arg.length + 1] = ip;
			logDetector.send(DataType.basicType, "01208", "登录成功日志 - ip", ip);
			arg[8] = ip;
			logDetector.send(DataType.basicType, "01208", "登录成功日志 - arg[8]", arg[8]);

			String sql5 = sqlmface.addSqlface(5, arg);
			logDetector.send(DataType.basicType, "01208", "登录成功日志 - sql5", sql5);
			int a = sqlUtil.sql_exec(sql5);

			// 用户存在,用户id
			sql = sqlmface.searchSqlface(1, arg);
			logDetector.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql);
			arg[0] = sqlUtil.get_string(sql);

			// 查询一级列表
			sql = sqlmface.searchSqlface(2, arg);
			logDetector.send(DataType.basicType, "01156", "查询一级列表: ", sql);
			// 一级
			ArrayList<Map<String, Object>> menu1List = sqlUtil.get_list(sql);
			for (int i = 0; i < menu1List.size(); i++) {
				Map<String, Object> menu1 = menu1List.get(i);
				arg[5] = menu1.get("menu_id") + "";
				sql = sqlmface.searchSqlface(3, arg);
				logDetector.send(DataType.basicType, "01156", "查询二级列表: ", sql);
				// 二级
				ArrayList<Map<String, Object>> menu2List = sqlUtil.get_list(sql);
				menu1.put("itemlist", menu2List);
				logDetector.send(DataType.basicType, "01156", "查询二级列表menu2List: ", menu2List);
				logDetector.send(DataType.basicType, "01156", "查询二级列表menu1: ", menu1);
			}
			// 获取后台用户信息
			sql = sqlmface.searchSqlface(4, arg);
			logDetector.send(DataType.basicType, "01156", "获取后台用户信息: ", sql);
			logDetector.send(DataType.basicType, "01156", "获取后台用户信息menu1List: ", menu1List);
			list = sqlUtil.get_list(sql);
			HttpSession session = request.getSession();
			session.setAttribute("admin_name", list.get(0).get("username").toString());
			session.setAttribute("id", arg[0]);
			session.setAttribute("admin", "success");
			session.setMaxInactiveInterval(60 * 60 * 5);
			session.setAttribute("menulist", menu1List);
			inOutUtil.return_listpage(list, pages, "/uiface/boss/admincenter.jsp");
		} else {
			// 不存在该用户
			String ip = "";
			ip = getIpAddr(request);
			logDetector.send(DataType.basicType, "01208", "登录失败日志 - ip", ip);
			arg[8] = ip;
			logDetector.send(DataType.basicType, "01208", "登录失败日志 - arg[8]", arg[8]);

			String sql6 = sqlmface.addSqlface(6, arg);
			logDetector.send(DataType.basicType, "01208", "登录失败日志 - sql6", sql6);
			int a = sqlUtil.sql_exec(sql6);
			response.sendRedirect("http://" + JyGlobalConstant.getIp() + ":8091/uiface/boss/adminLogin.jsp");
		}

		// http://47.99.110.97:8092/
	}

	private void admin_login(String[] arg) throws SQLException, IOException, ServletException {
		String sql1 = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01156", "admin_login()-sql1: ", sql1);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql1);
		logDetector.send(DataType.basicType, "01156", "admin_login()-list: ", list);

		if (list.size() == 1) {
			String power = list.get(0).get("power") + "";
			String username = list.get(0).get("username") + "";
			HttpSession session = request.getSession();
			session.setAttribute("admin", "success");
			session.setAttribute("power", power);
			session.setAttribute("username", username);

			session.setMaxInactiveInterval(60 * 60 * 5);
			inOutUtil.return_listpage(list, pages, "/uiface/boss/admincenter.jsp");
		} else {
			// request.getSession().setAttribute("adminLoginStatus", "err");
			// inOutUtil.return_only("boss/adminLogin.jsp");
			response.sendRedirect("http://" + JyGlobalConstant.getIp() + ":8091/uiface/boss/adminLogin.jsp");
		}
	}

	/**
	 * arg[0] A-boss-mod arg[1] response_money arg[2] id
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 */
	/*
	 * private void response_money(String[] arg) throws SQLException, IOException,
	 * ServletException { String sql = sqlmface.modSqlface(0, arg); list =
	 * sqlUtil.get_list(sql); if (list.size() == 1) { log.send(DataType.basicType,
	 * "01158", "response_money()-list: ", list);
	 * 
	 * double realmoney = Double.parseDouble(list.get(0).get("cash") .toString());
	 * 
	 * AlipayClient alipayClient = new DefaultAlipayClient(
	 * "https://openapi.alipay.com/gateway.do", Ordercreat.APPID,
	 * Ordercreat.RSA2_PRIVATE, "json", "utf-8", Ordercreat.ALIPAY_PUBLIC_KEY,
	 * "RSA2"); AlipayFundTransToaccountTransferRequest request = new
	 * AlipayFundTransToaccountTransferRequest(); request.setBizContent("{" +
	 * "\"out_biz_no\":\"" + list.get(0).get("out_biz_no").toString() + "\"," +
	 * "\"payee_type\":\"ALIPAY_LOGONID\"," + "\"payee_account\":\"" +
	 * list.get(0).get("tixian_account").toString() + "\"," + "\"amount\":\"" +
	 * list.get(0).get("cash").toString() + "\"," + "\"payer_show_name\":\"悦币\"," +
	 * "\"payee_real_name\":\"\"," + "\"remark\":\"悦币提现\"" // + //
	 * "\"payee_real_name\":\"张三\"," + "}");
	 * AlipayFundTransToaccountTransferResponse response = null;
	 * log.send(DataType.basicType, "01158", "response_money()-response: ", 1); try
	 * { response = alipayClient.execute(request); } catch (AlipayApiException e) {
	 * // TODO Auto-generated catch block e.printStackTrace();
	 * log.send(DataType.basicType, "01158", "response_money()-response: ",
	 * e.toString()); } log.send(DataType.basicType, "01158",
	 * "response_money()-response: ", response.getParams());
	 * log.send(DataType.basicType, "01158", "response_money()-response: ",
	 * response.getBody() + response.getErrorCode() + response.getParams()); if
	 * (response.isSuccess()) { System.out.println("调用成功");
	 * 
	 * String code = response.getCode(); if (code.equals("10000")) { String[] newarg
	 * = new String[5]; newarg[0] = response.getOutBizNo(); newarg[1] = arg[1];
	 * newarg[2] = response.getOrderId(); newarg[3] = response.getPayDate();
	 * newarg[4] = response.getMsg(); sql = sqlmface.modSqlface(1, newarg);
	 * log.send(DataType.basicType, "01158", "response_money()-sql: ", sql); int i =
	 * sqlUtil.sql_exec(sql); log.send(DataType.basicType, "01158",
	 * "response_money()-i: ", i); String jsonadd = "提现成功！";
	 * log.send(DataType.basicType, "01158", "response_money()-jsonadd: ", jsonadd);
	 * 
	 * if (list.get(0).get("c_type").toString().equals("0")) {
	 * 
	 * ArrayList<Map<String, Object>> list2 = null; sql = "select * from cash_set ";
	 * log.send("01158", "sql", sql); list2 = sqlUtil.get_list(sql);
	 * 
	 * ArrayList<Map<String, Object>> list1 = null; // 一级分销 sql =
	 * "select promoter_id,twopromoter_id,up_agentid from user_data where id=" +
	 * list.get(0).get("user_id").toString(); log.send("01158", "sql", sql); list1 =
	 * sqlUtil.get_list(sql); if (list1.size() == 0) {
	 * 
	 * } else { String oneid = list1.get(0).get("promoter_id") .toString(); if
	 * (!oneid.equals("0")) { String isv = "0"; String sacleone = list2.get(0)
	 * .get("dvcash_onefee").toString(); Double oneable_money = Double
	 * .parseDouble(sacleone) * realmoney; sql =
	 * "insert into tuiguang_detail (upuser_id,downuser_id,is_dv,levle,money_type,money_num,scale_num,able_money,uptime) values ('"
	 * + oneid + "','" + list.get(0).get("user_id").toString() + "','" + isv +
	 * "',1,'提现','" + realmoney + "','" + sacleone + "','" + oneable_money +
	 * "',now())"; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); sql =
	 * "update user_data set invite_price = invite_price+" + oneable_money +
	 * ",ableinvite_price=ableinvite_price+" + oneable_money + " where id=" + oneid
	 * + " "; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); }
	 * 
	 * String twoid = list1.get(0).get("twopromoter_id") .toString(); if
	 * (!twoid.equals("0") && !list2.get(0)
	 * .get("dvcash_twofee").toString().equals("0")) { String isv = "0"; String
	 * sacletwo = list2.get(0) .get("dvcash_twofee").toString(); Double
	 * twoable_money = Double .parseDouble(sacletwo) * realmoney; sql =
	 * "insert into tuiguang_detail (upuser_id,downuser_id,is_dv,levle,money_type,money_num,scale_num,able_money,uptime) values ('"
	 * + twoid + "','" + list.get(0).get("user_id").toString() + "','" + isv +
	 * "',1,'提现','" + realmoney + "','" + sacletwo + "','" + twoable_money +
	 * "',now())"; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); sql =
	 * "update user_data set invite_price = invite_price+" + twoable_money +
	 * ",ableinvite_price=ableinvite_price+" + twoable_money + " where id=" + twoid
	 * + " "; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); }
	 * 
	 * String up_agentid=list1.get(0).get("up_agentid").toString();
	 * if(!up_agentid.equals("0")){ //
	 * sql="select b.agent_percent from agentlist as a ,agent_set as b where a.agent_channelcode="
	 * +up_agentid+" and a.agent_level=b.agent_level";
	 * sql="select fencheng_v from agentlist   where agent_channelcode='"+up_agentid
	 * +"'"; String agent_fee=sqlUtil.get_string(sql);
	 * 
	 * //String agent_fee=listcast.get(0).get("agent_fee").toString(); String
	 * isv="0"; Double agentable_money=Double.parseDouble(agent_fee)*realmoney;
	 * sql="insert into agenttuiguang_detail (upuser_id,downuser_id,is_dv,levle,money_type,money_num,scale_num,able_money,uptime) values ('"
	 * +up_agentid+"','"+list.get(0).get("user_id").toString()+"','"+isv+
	 * "',1,'提现','"+realmoney+"','"+agent_fee+"','"+agentable_money+"',now())";
	 * log.send("01158", "sql", sql); sqlUtil.sql_exec(sql);
	 * sql="update agentlist set totle_money = totle_money+"+agentable_money+
	 * ",ablew_money=ablew_money+"+agentable_money+" where agent_channelcode='"
	 * +up_agentid+"' "; log.send("01158", "sql", sql); sqlUtil.sql_exec(sql); } } }
	 * 
	 * 
	 * BizRenderTask send = new BizRenderTask(list.get(0).get("user_id").toString()
	 * + "卍您于"+list.get(0).get("time").toString()+"提交的 "+list.get(0).get("cash").
	 * toString()+" 元的提现申请已经通过，请注意查看！"); send.run();
	 * 
	 * 
	 * inOutUtil.return_ajax(jsonadd); } else { String[] newarg = new String[3];
	 * newarg[0] = arg[2]; newarg[1] = arg[1]; newarg[2] = response.getSubMsg(); sql
	 * = sqlmface.modSqlface(2, newarg); log.send(DataType.basicType, "01158",
	 * "response_money()-sql: ", sql); int i = sqlUtil.sql_exec(sql);
	 * log.send(DataType.basicType, "01158", "response_money()-i: ", i); String
	 * jsonadd = newarg[2]; log.send(DataType.basicType, "01158",
	 * "response_money()-jsonadd: ", jsonadd); inOutUtil.return_ajax(jsonadd); }
	 * 
	 * } else { String[] newarg = new String[3]; newarg[0] = arg[2]; newarg[1] =
	 * arg[1]; newarg[2] = response.getSubMsg(); sql = sqlmface.modSqlface(2,
	 * newarg); log.send(DataType.basicType, "01158", "response_money()-sql: ",
	 * sql); int i = sqlUtil.sql_exec(sql); log.send(DataType.basicType, "01158",
	 * "response_money()-i: ", i); String jsonadd = newarg[2];
	 * log.send(DataType.basicType, "01158", "response_money()-jsonadd: ", jsonadd);
	 * inOutUtil.return_ajax(jsonadd); }
	 * 
	 * } else { String jsonadd = "没有此条提现明细或已提现！"; log.send(DataType.basicType,
	 * "01158", "response_money()-jsonadd: ", jsonadd);
	 * inOutUtil.return_ajax(jsonadd); }
	 * 
	 * }
	 */

	public class BizRenderTask implements Runnable {
		private String title;
		private String content;
		private String group;

		BizRenderTask(String content) {
			// this.group=group;
			// this.title=title;
			this.content = content;
		}

		@Override
		public void run() {
			Socket client;

			try {
				// log.send(DataType.specialType, "01066", "BizRenderTask",
				// content);
				// 120.27.98.128:9810
				// client = new Socket("116.62.220.67", 9200);
				logDetector.send(DataType.basicType, "01160", "run", "系统通知1");
				client = new Socket(JyGlobalConstant.getIp(), 9200);
				logDetector.send(DataType.basicType, "01160", "run", "系统通知2");
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"), true);
				logDetector.send(DataType.basicType, "01160", "run", "系统通知3");
				writer.println(content);
				logDetector.send(DataType.basicType, "01160", "run", "系统通知4");
				writer.close();
				client.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logDetector.send(DataType.basicType, "01160", "流错误", e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logDetector.send(DataType.basicType, "01160", "IO错 ", e);
			}
		}

	}

	/**
	 * \n 回车(\u000a) \t 水平制表符(\u0009) \s 空格(\u0008) \r 换行(\u000d)
	 */
	// 去掉换行、空格
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	// 自写listToJsonTree
	public static String listToJsonTree(ArrayList<Map<String, Object>> list) {
		// String name="";
		// String value="";
		StringBuffer json = new StringBuffer("[");
		for (Map<String, Object> map : list) {
			json.append("{");
			for (Entry<String, Object> entry : map.entrySet()) {
				if ("id".equals(entry.getKey()) || "parent_id".equals(entry.getKey())
						|| "open".equals(entry.getKey())) {
					json.append("" + entry.getKey() + ":" + entry.getValue() + ",");
				} else {
					json.append("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\",");
				}
			}
			json.deleteCharAt(json.length() - 1);
			json.append("},");
		}

		json.deleteCharAt(json.length() - 1);

		if (json.length() > 0) {
			json.append("]");
		}
		return json.toString();
	}

	// 去除回车
	public static String replaceBlank_hc(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

}
