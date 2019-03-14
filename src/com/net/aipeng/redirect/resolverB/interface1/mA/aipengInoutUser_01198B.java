package com.net.aipeng.redirect.resolverB.interface1.mA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface1.mA.InOutManager;
import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.redirect.resolverB.interface2.mA.aipengSqlUser_01198B;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class aipengInoutUser_01198B extends InOutManager implements InOutFace {
	protected ArrayList<Map<String, Object>> list;
	protected ArrayList<Map<String, Object>> list1;
	protected ArrayList<Map<String, Object>> list2;
	protected String json = "";
	SqlManagerFace sqlmface = new aipengSqlUser_01198B();

	public aipengInoutUser_01198B(String[] arg, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		super(arg, request, response);
	}

	@Override
	public void addface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 用户提现
		case "add_withdrawals":// 20180912创建
			add_withdrawals(arg);
			break;
		case "pushp2pvideo":
			pushp2pvideo(arg);
			break;
		case "pushcmdmsg":
			pushcmdmsg(arg);
			break;
		}
	}

	/**
	 * 向对方推送命令消息 arg[0]: A-user-add arg[1]: pushcmdmsg arg[2]: 不使用 arg[3]: my_userid
	 * arg[4]: my_username arg[5]: my_headpic arg[6]: your_userid arg[7]: roomid
	 * arg[8]: cmd * arg[9]: calltype 1:一对一视频 2:一对一音频
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */
	private void pushcmdmsg(String[] arg) throws SQLException, IOException, ServletException {

		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient("1f649fe516da759c916538c5", "30d0a16dcb84cb5f2c3ff3ed", null,
				clientConfig);
		final PushPayload payload = buildPushObjectCmd_android_and_ios(arg[3], arg[4], arg[5], arg[6], arg[7], arg[8],
				arg[9]);

		try {
			PushResult result = jpushClient.sendPush(payload);
			logDetector.send("158", "pushcmdmsg()-result: ", result);
			inOutUtil.return_ajax(result.toString());
			return;
		} catch (Exception e) {
			inOutUtil.return_ajax("{\"result\":\"fail\"}");
			logDetector.send("158", "pushcmdmsg()-exception: ", e);
			return;
		}
	}

	protected PushPayload buildPushObjectCmd_android_and_ios(String fromuserid, String fromusername, String fromheadpic,
			String touserid, String roomid, String cmd, String calltype) {
		// Log("buildPushObject_android_and_ios : ", user);

		Map<String, String> extras = new HashMap<String, String>();
		// extras.put("test", "https://community.jiguang.cn/push");
		// extras.put(MSG_K_STATE_DETECTE, MSG_V_STATE_DETECTE + "");
		extras.put("fromid", fromuserid);
		extras.put("fromnickname", fromusername);
		extras.put("fromheadpic", fromheadpic);
		extras.put("roomid", roomid);
		extras.put("command", cmd);
		extras.put("calltype", calltype);
		extras.put("tag", "videocmd");

		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(touserid))
				// .setAudience(Audience.tag_and("285", "tag_all"))
				.setNotification(Notification.newBuilder().setAlert("")
						.addPlatformNotification(
								AndroidNotification.newBuilder().setTitle("新的来电").addExtras(extras).build())
						.addPlatformNotification(IosNotification.newBuilder().setBadge(0)
								// //.setSound("music1.mp3")
								.addExtras(extras).setContentAvailable(true).build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
	}

	/**
	 * arg[0]: A-user-add arg[1]: pushp2pvideo arg[2]: 不使用 arg[3]: guke_id arg[4]:
	 * guke_name arg[5]: guke_pic arg[6]: zhubo_id arg[7]: roomid arg[8]: command
	 * arg[9]: calltype 1:一对一视频 2:一对一音频
	 * 
	 * @param arg
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void pushp2pvideo(String[] arg) throws ServletException, SQLException, IOException {

		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send("158", "pushp2pvideo()-sql: ", sql);
		int ret = sqlUtil.sql_exec(sql);
		logDetector.send("158", "pushp2pvideo()-ret: ", ret);

		sql = sqlmface.addSqlface(1, arg);
		logDetector.send("158", "pushp2pvideo()-sql2: ", sql);
		ret = sqlUtil.sql_exec(sql);
		logDetector.send("158", "pushp2pvideo()-ret2: ", ret);

		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient("1f649fe516da759c916538c5", "30d0a16dcb84cb5f2c3ff3ed", null,
				clientConfig);
		String price = "";
		if (arg.length > 10) {
			price = arg[10];
		}
		final PushPayload payload = buildPushObject_android_and_ios(arg[3], arg[4], arg[5], arg[6], arg[7], arg[8],
				arg[9], price);

		try {
			PushResult result = jpushClient.sendPush(payload);
			inOutUtil.return_ajax(result.toString());
			return;
		} catch (Exception e) {
			inOutUtil.return_ajax("{\"result\":\"fail\"}");
			logDetector.send("158", "pushp2pvideo()-exception: ", e);
			return;
		}
	}

	public PushPayload buildPushObject_android_and_ios(String guke_id, String guke_name, String guke_headpic,
			String zhuboid, String roomid, String cmd, String calltype, String price) {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("fromid", guke_id);
		extras.put("fromnickname", guke_name);
		extras.put("fromheadpic", guke_headpic);
		extras.put("roomid", roomid);
		extras.put("command", cmd);
		extras.put("calltype", calltype);
		extras.put("price", price);
		extras.put("tag", "videocmd");
//    return PushPayload.newBuilder()
//            .setPlatform(Platform.android_ios())
//            .setAudience(Audience.all())
//            .setNotification(Notification.newBuilder()
//            		.setAlert("alert content")
//            		.addPlatformNotification(AndroidNotification.newBuilder()
//            				.setTitle("Android Title")
//                            .addExtras(extras).build())
//            		.addPlatformNotification(IosNotification.newBuilder()
//            				.incrBadge(1)
//            				.addExtra("extra_key", "extra_value").build())
//            		.build())
//            .build();

		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(zhuboid))
				// .setAudience(Audience.tag_and("285", "tag_all"))
				.setNotification(Notification.newBuilder().setAlert(guke_name + "来电")
						.addPlatformNotification(
								AndroidNotification.newBuilder().setTitle("新的来电").addExtras(extras).build())
						.addPlatformNotification(
								IosNotification.newBuilder().setBadge(1).setSound("music1.mp3").addExtras(extras)

										.setContentAvailable(true).addExtra("extra_key", "extra_value").build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
	}

	/**
	 * 用户提现-------01198 arg[2] user_id 用户id arg[3] cash 提现金额 arg[4] content 申请提现内容
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add_withdrawals(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.addSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "用户提现-sql:", sql);
		int success = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.basicType, "01198", "用户提现-success:", success);
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
		// 修改女神状态(通话状态)
		case "updateConversationStatus":
			updateConversationStatus(arg);
			break;
		// 发信息按条扣费
		case "message_one_pay":// 180922创建
			message_one_pay(arg);
			break;
		}
	}

	/**
	 * arg[2] id 发送者id arg[3] user_id 接收者id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void message_one_pay(String[] arg) throws SQLException, ServletException, IOException {

		String sql = sqlmface.modSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "对方设置的价格sql", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "对方设置的价格-list", list);

		if (list.size() == 0 || list == null) {
			arg[0] = "0";
		} else {
			arg[0] = list.get(0).get("wordchat_price").toString();
		}
		String sql1 = sqlmface.modSqlface(1, arg);
		logDetector.send(DataType.basicType, "01198", "发送者减趣豆sql", sql1);
		int success1 = sqlUtil.sql_exec(sql1);
		logDetector.send(DataType.basicType, "01198", "发送者减趣豆-success1", success1);

		String sql2 = sqlmface.modSqlface(2, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql2", sql2);
		int success2 = sqlUtil.sql_exec(sql2);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success2", success2);

		String sql3 = sqlmface.modSqlface(3, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql3", sql3);
		int success3 = sqlUtil.sql_exec(sql3);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success3", success3);

		String sql4 = sqlmface.modSqlface(4, arg);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆sql4", sql4);
		int success4 = sqlUtil.sql_exec(sql4);
		logDetector.send(DataType.basicType, "01198", "接收者得趣豆-success4", success4);

		if (success1 == 1 && success2 == 1 && success3 == 1 && success4 == 1) {
			String jsonadd = "{\"success\":\"1\"}";
			inOutUtil.return_ajax(jsonadd);
		} else {
			String jsonadd = "{\"success\":\"0\"}";
			inOutUtil.return_ajax(jsonadd);
		}

	}

	/**
	 * 修改女神状态(通话状态)
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateConversationStatus(String[] arg) throws SQLException, ServletException, IOException {
		if ("4".equals(arg[3])) {
			String sql = sqlmface.modSqlface(0, arg);
			logDetector.send(DataType.basicType, "01198", "更改通话状态sql", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01198", "更改通话状态-success", success);
			if (success == 1) {
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		} else {
			String sql = sqlmface.modSqlface(1, arg);
			logDetector.send(DataType.basicType, "01198", "更改通话状态sql", sql);
			int success = sqlUtil.sql_exec(sql);
			logDetector.send(DataType.basicType, "01198", "更改通话状态-success", success);
			if (success == 1) {
				String jsonadd = "{\"success\":\"1\"}";
				inOutUtil.return_ajax(jsonadd);
			} else {
				String jsonadd = "{\"success\":\"0\"}";
				inOutUtil.return_ajax(jsonadd);
			}
		}

	}

	@Override
	public void deleteface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		case "removep2pvideo":
			removep2pvideo(arg);
			break;
		}
	}

	/**
	 * 删除一对一视频记录 arg[0]: A-user-delete arg[1]: removep2pvideo arg[2]: 不使用 arg[3]:
	 * roomid
	 * 
	 * @param arg
	 * @throws SQLException
	 */
	private void removep2pvideo(String[] arg) throws SQLException {
		String sql = sqlmface.deleteSqlface(arg);
		logDetector.send(DataType.specialType, "01160", "removep2pvideo()-sql: ", sql);
		int result = sqlUtil.sql_exec(sql);
		logDetector.send(DataType.specialType, "01160", "removep2pvideo()-result: ", result);
	}

	@Override
	public void searchface() throws SQLException, ServletException, IOException {
		switch (arg[1]) {
		// 查询充值爱豆数量和价格等详情
		case "search_love_bean_details":// 20180912创建
			search_love_bean_details(arg);
			break;
		// 查询充值记录
		case "search_recharge_record":// 20180912创建
			search_recharge_record(arg);
			break;
		case "delayidle":
			delayidle(arg);
			break;
		case "checkroominfo":
			checkroominfo(arg);
			break;
		}
	}

	/**
	 * 检查当前一对一视频房间信息，用户主播收到一对一视频邀请点击接听按钮现场判断是否是仍然存活的房间(判断男号是否挂断) arg[0]:
	 * A-user-search arg[1]: checkroominfo arg[2]: 不使用 arg[3]: userid arg[4]: roomid
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 */

	private void checkroominfo(String[] arg) throws SQLException, IOException, ServletException {

		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send("01107", "checkroominfo()-sql: ", sql);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
		logDetector.send("01107", "checkroominfo()-list: ", list);

		String retJson;
		if (list.size() > 0) {
			retJson = "{\"result\":\"yes\"}";
		} else {
			retJson = "{\"result\":\"no\"}";
		}
		logDetector.send("01107", "checkroominfo()-retJson: ", retJson);
		inOutUtil.return_ajax(retJson);
	}

	private void delayidle(String[] arg) throws SQLException, IOException, ServletException {

		/*
		 * String sql = sqlmface.searchSqlface(0, arg); log.send("158",
		 * "delayidle()-sql: ", sql);
		 * 
		 * int getrewardvideoNumber = sqlUtil.get_int(sql); log.send("158",
		 * "delayidle()-getrewardvideoNumber: ", getrewardvideoNumber);
		 * 
		 * sql = sqlmface.searchSqlface(1, arg); log.send("158", "delayidle()-sql2: ",
		 * sql);
		 * 
		 * int result = sqlUtil.sql_exec(sql); log.send("158", "delayidle()-result: ",
		 * result);
		 */
		// JiGuangDetection.GetInstance().UserResponse(arg[2]);

		json = "OK";
		inOutUtil.return_ajax(json);
	}

	/**
	 * 查询充值记录------01198 arg[2] user_id 用户id
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_recharge_record(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询充值记录---sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "查询充值记录---list:", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

	/**
	 * 查询充值爱豆数量和价格等详情----------01198
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search_love_bean_details(String[] arg) throws SQLException, ServletException, IOException {
		String sql = sqlmface.searchSqlface(0, arg);
		logDetector.send(DataType.basicType, "01198", "查询充值爱豆数量和价格等详情---sql:", sql);
		list = sqlUtil.get_list(sql);
		logDetector.send(DataType.basicType, "01198", "查询充值爱豆数量和价格等详情---list:", list);

		inOutUtil.return_ajax(JsonUtil.listToJson(list));

	}

}
