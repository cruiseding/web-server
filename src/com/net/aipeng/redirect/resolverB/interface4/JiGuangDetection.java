package com.net.aipeng.redirect.resolverB.interface4;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.SqlUtil;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

@WebListener
public class JiGuangDetection implements ServletContextListener {
	public static final String TAG = JiGuangDetection.class.getName() + "----";

	public static final long DETECTE_DELTA = 30 * 1000; // 30 seconds
	// public static final long DETECTE_DELTA_DEBUG = 2 * 60 * 1000;// 2 minutes
	public static final long DETECTE_ONLINE_TIME = 5 * 1000;// 5 seconds
	// public static final boolean DEBUG = false;

	// message key
	public static final String MSG_K_STATE_DETECTE = "MSG_K_STATE_DETECTE";

	// message value
	public static final int MSG_V_STATE_DETECTE = 0x00000001;

	private Thread mDetectionThread = null;
	private Runnable mRunnable = null;
	private long mLastTime = 0;
	private LinkedList<String> mPushRecord = new LinkedList<>();
	// private SqlUtil sqlUtil = null;
	private JyLogDetect log = new JyLogDetect();

	public static JiGuangDetection sInstance = null;

	public static JiGuangDetection GetInstance() {
		// if(sInstance == null)
		// sInstance = new JiGuangDetection();

		return sInstance;
	}

	public void Close() {
		if (mDetectionThread.isAlive())
			mDetectionThread.interrupt();
		mDetectionThread = null;
		mRunnable = null;
		mPushRecord.clear();
		sInstance = null;
	}

	public JiGuangDetection() {
	}

	public void Startup() {
		if (sInstance != null)
			return;
		sInstance = this;

		mRunnable = new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						// Thread.sleep((DEBUG ? DETECTE_DELTA_DEBUG : DETECTE_DELTA) -
						// DETECTE_ONLINE_TIME);
						Thread.sleep(DETECTE_DELTA - DETECTE_ONLINE_TIME);
						ArrayList<Map<String, Object>> userList = SelectOnlineUsers();
						PushDetectionMessage(userList);
						mLastTime = System.currentTimeMillis();
						Thread.sleep(DETECTE_ONLINE_TIME);
						UpdateUsersState();
						// Log("Thread targeted");
						// OnTimeTrigged(curTime, curTime - mLastTime);

					} catch (Exception e) {
						e.printStackTrace();
						LogError("ERROR:Runnable.run :  ", getStackInfo(e));
					}
				}
			}

		};
		mDetectionThread = new Thread(mRunnable);
		mDetectionThread.start();
	}

	private void OnTimeTrigged(long timeCur, long timeDelta) {
		// 1. Update users state and clear table.
		UpdateUsersState();

		// 2.Get all online users
		ArrayList<Map<String, Object>> userList = SelectOnlineUsers();

		// 3.Push message and record user
		PushDetectionMessage(userList);
	}

	/**
	 * 1.Set the user in the list to offline. 2.clear the list
	 */
	private void UpdateUsersState() {
		if (mPushRecord.isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append("update user_data set is_online=0 where(");

		boolean first = true;
		for (String user : mPushRecord) {
			if (!first)
				sb.append(" or ");
			else
				first = false;
			sb.append("id='").append(user).append("'");
		}

		sb.append(") and is_online='1'");

		// build a sql command to do work 1;
		String sql = sb.toString();
		Log("UpdateUsersState : ", sql);
		// runn the sql command
		try {
			SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
			sqlUtil.sql_exec(sql);
		} catch (Exception e) {
			e.printStackTrace();
			LogError("ERROR: UpdateUsersState(): run sql command failed! ", getStackInfo(e));
		}

		mPushRecord.clear();
	}

	private void SetUserState(String user, int state) {
	}

	/**
	 * Get and return all online users in database.
	 * 
	 * @return
	 */
//	private ArrayList<Map<String,Object>> SelectOnlineUsers()
//	{
//		try{
//			SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
//			String sql = "select id,platform from user_data where platform='android'";
//			Log("SelectOnlineUsers() sql : ", sql);
//			ArrayList<Map<String,Object>> list = sqlUtil.get_list(sql);
//			return list;
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
	private ArrayList<Map<String, Object>> SelectOnlineUsers() {
		try {
			SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
			String sql = "select id,platform from user_data where platform='android'";
			Log("SelectOnlineUsers() sql : ", sql);
			ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 1.Push message to all given users. 2.Record users
	 * 
	 * @param users given users.
	 */
	private void PushDetectionMessage(ArrayList<Map<String, Object>> users) {
		mPushRecord.clear();
		if (users == null || users.isEmpty())
			return;
		ClientConfig clientConfig = ClientConfig.getInstance();
		final JPushClient jpushClient = new JPushClient("f75d1137307c1cfb48d58efd", "86bb99d8ad8a3bd98aa27a1e", null,
				clientConfig);

		for (Map<String, Object> user : users) {
			String device = user.get("platform").toString();
			if ("android".equals(device)) {
				String userID = user.get("id").toString();
				// 1.Push message to user
				try {
					PushPayload payload = buildPushObject_android_and_ios(userID);// buildPushObject_ios_audienceMore_messageWithExtras(userID);
					jpushClient.sendPush(payload);
					Log("PushDetectionMessage user  ", userID);
				} catch (Exception e) {
					e.printStackTrace();
					Log("ERROR:PushDetectionMessage : ", userID, "  ", getStackInfo(e));
				}
				// 2.record user
				// Whether it was sent successfully or not, put it into waiting table
				mPushRecord.add(userID);
			}
		}
	}

	/**
	 * Set user state to online and remove form record list.
	 * 
	 * @param user
	 */
	public void UserResponse(String user) {
		Log("UserResponse()  ", user);
		if (user == null || user.isEmpty())
			return;

		int idx = mPushRecord.indexOf(user);
		if (idx < 0)
			return;
		mPushRecord.remove(idx);

		try {
			String sql = "update user_data set is_online=1 where id='" + user + "' and is_online='0'"; // Build a sql
																										// command to
																										// update user
																										// state
			SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
			sqlUtil.sql_exec(sql);
		} catch (Exception e) {
			e.printStackTrace();
			LogError("ERROR : update user state : user:", user, "    ", getStackInfo(e));
		}

	}

	/**
	 * @return
	 */
	protected PushPayload buildPushObject_android_and_ios(String user) {
		Log("buildPushObject_android_and_ios : ", user);

		Map<String, String> extras = new HashMap<String, String>();
		// extras.put("test", "https://community.jiguang.cn/push");
		extras.put(MSG_K_STATE_DETECTE, MSG_V_STATE_DETECTE + "");

		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(user))
				// .setAudience(Audience.tag_and("285", "tag_all"))
				.setNotification(Notification.newBuilder().setAlert("")
						.addPlatformNotification(
								AndroidNotification.newBuilder().setTitle("新的来电").addExtras(extras).build())
						// .addPlatformNotification(IosNotification.newBuilder()
						// .setBadge(0)
						// //.setSound("music1.mp3")
						// .addExtras(extras)
						// .setContentAvailable(true)
						// .build())
						.build())
				.build();
	}

	private void Log(Object... args) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			sb.append(arg);
		}

		log.send(DataType.specialType, "01205", TAG, sb.toString());
	}

	private void LogError(Object... args) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			sb.append(arg);
		}

		log.send(DataType.exceptionType, "01205", TAG, sb.toString());
	}

	private void LogF(String str, Object... args) {
		String info = String.format(str, args);
		log.send(DataType.specialType, "01205", TAG, info);
	}

	private String getStackInfo(Throwable e) {
		String info;
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		e.printStackTrace(pw);
		info = writer.toString();
		pw.close();
		try {
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return info;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Log("contextDestroyed()");
		Close();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.send(DataType.specialType, "01205", "contextInitialized()", arg0);
		Startup();
	}
}
