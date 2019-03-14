package com.net.aipeng.redirect.resolverA.interface4;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.SqlUtil;

@WebListener
public class ClearAmount_01162 implements ServletContextListener {

	// 开一个定时循环线程
	private ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(4);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("CtrlInit destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("CtrlInit initialize");
		final JyLogDetect log = new JyLogDetect();
		executor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				Date lastdayofmonth, lastdayofweek1, thisnight, lastdayofmontht, lastdayofweekt, thisnightt;
				try {
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.MONTH,1);
					// cal.set(Calendar.DAY_OF_MONTH,0);
					// String lastday = df1.format(cal.getTime());
					// lastdayofmonth= df1.parse(lastday.split("\\s")[0]+" 23:59:50");
					// cal=Calendar.getInstance();
					cal.set(Calendar.DAY_OF_WEEK, 1);
					cal.add(Calendar.WEEK_OF_YEAR, 0);
					String lastdayofweek = df1.format(cal.getTime());
					lastdayofweek1 = df1.parse(lastdayofweek.split("\\s")[0] + " 23:59:30");
					// cal=Calendar.getInstance();
					// cal.setTime(df1.parse(lastday.split("\\s")[0]+" 00:00:01")); //本月最后一天往后加一天
					// cal.add(Calendar.DAY_OF_YEAR,1);
					// lastdayofmontht=cal.getTime();
					cal = Calendar.getInstance();
					cal.setTime(df1.parse(lastdayofweek.split("\\s")[0] + " 00:00:01"));
					cal.add(Calendar.DAY_OF_YEAR, 1);
					lastdayofweekt = cal.getTime();
					cal = Calendar.getInstance();
					cal.setTime(df1.parse(df1.format(cal.getTime()).split("\\s")[0] + " 23:59:30"));
					thisnight = cal.getTime();
					cal = Calendar.getInstance();
					cal.setTime(df1.parse(df1.format(cal.getTime()).split("\\s")[0] + " 00:00:01"));
					cal.add(Calendar.DAY_OF_YEAR, 1);
					thisnightt = cal.getTime();

					// date

					log.send(DataType.specialType, "01162", "时间", thisnight);
					log.send(DataType.specialType, "01162", "时间", thisnightt);
					log.send(DataType.specialType, "01162", "时间", lastdayofweek1);
					log.send(DataType.specialType, "01162", "时间", lastdayofweekt);
					// log.send(DataType.specialType, "01162", "时间", lastdayofmonth);
					// log.send(DataType.specialType, "01162", "时间", lastdayofmontht);

					if (date.compareTo(thisnight) > 0 && date.compareTo(thisnightt) < 0) {
						clearToday(date);
					}

					if (date.compareTo(lastdayofweek1) > 0 && date.compareTo(lastdayofweekt) < 0) {
						clearWeek();
					}

					log.send(DataType.specialType, "01160", "活动检测", "Thread start");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.send(DataType.specialType, "01160", "活动检测-SQLException", e);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.send(DataType.specialType, "01160", "活动检测-ClassNotFoundException", e);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.send(DataType.specialType, "01160", "活动检测-日期转换异常", e);
				}
			}
		}, 10, 30, TimeUnit.SECONDS);
	}

	public void clearToday(Date date) throws SQLException, ClassNotFoundException {
		SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
		JyLogDetect log = new JyLogDetect();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String b = df.format(date);
		String sql = "update user_data  set member_end_time='',is_member=0 where member_end_time<='" + b
				+ "'  and member_end_time!='' and is_member=1";
		log.send(DataType.specialType, "01160", "清除日榜邀请人数", sql);
		sqlUtil.sql_exec(sql);
	}

	public void clearWeek() throws SQLException, ClassNotFoundException {
		SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
		JyLogDetect log = new JyLogDetect();
		String sql = "update user_data set week_inout=0";
		log.send(DataType.specialType, "01160", "清除周榜邀请人数", sql);
		sqlUtil.sql_exec(sql);

	}
}
