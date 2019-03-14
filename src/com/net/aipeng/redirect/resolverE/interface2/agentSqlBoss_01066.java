package com.net.aipeng.redirect.resolverE.interface2;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;
import com.net.aipeng.redirect.resolverE.interface4.JyHelpManager_boss;
/*import com.ssctrl.interface4.JyHelpManager_boss;
import com.ssctrl.interface4.JyLogDetect.DataType;*/

public class agentSqlBoss_01066 extends SqlManager implements SqlManagerFace {

	// select * from user_data a,agenttuiguang_detail b where
	// a.up_agentid=b.upuser_id
	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException {

		switch (arg[1]) {
		case "qds_search":
			String qds_sql = "";
			// p2:页码 p3:jsp p4:性别 p5:id p6:昵称 p7:开始时间 p8:结束时间 p9:年、月 p10:包含下属
			if (!"".equals(arg[4])) {
				qds_sql = qds_sql + " and b.gender='" + arg[4] + "' ";
			}

			if (!"".equals(arg[5])) {
				qds_sql = qds_sql + " and b.up_agentid='" + arg[5] + "' ";
			}

			if (!"".equals(arg[6])) {
				qds_sql = qds_sql + " and c.agent_name='" + arg[6] + "' ";
			}

			if (!"".equals(arg[10])) {

			} else {
				qds_sql = qds_sql + " and b.up_agentlevel='1' ";
			}

			// 判断起始日期条件
			if (!arg[7].isEmpty()) {

				qds_sql += " and uptime >='" + arg[7] + " 00:00:00' ";

			}
			// 判断结束日期条件
			if (!arg[8].isEmpty()) {
				qds_sql += " and uptime <='" + arg[8] + " 23:59:59' ";

			}
			// 判断结束日期条件
			if (!arg[9].isEmpty()) {

				// 判断按年还是按月
				int yearOrMonth = 0; // 1:年 2:月
				String date = "";
				if (arg[9].replaceAll("[0-9]{4}", "").isEmpty()) {
					yearOrMonth = 1;
					date = arg[9] + "-01-01"; // 补全日期，否则sql不认
				} else if (arg[9].replaceAll("[0-9]{4}-[0-9]{2}", "").isEmpty()) {
					yearOrMonth = 2;
					date = arg[9] + "-01"; // 补全日期，否则sql不认
				}
				if (yearOrMonth == 1) {
					qds_sql += " and year(uptime)=year('" + arg[9] + "-00-00') ";
				} else if (yearOrMonth == 2) {
					qds_sql += " and month(uptime)=month('" + arg[9] + "-00') ";
				}
			}

			if (current == 0) {
				if ("".equals(qds_sql)) {
					ressql = "select count(*) from agenttuiguang_detail a,user_data b,agentlist c where a.upuser_id=b.up_agentid and a.downuser_id=b.id and b.up_agentid=c.agent_channelcode ";
				} else {
					ressql = "select count(*) from agenttuiguang_detail a,user_data b,agentlist c where a.upuser_id=b.up_agentid and a.downuser_id=b.id and b.up_agentid=c.agent_channelcode "
							+ qds_sql + " ";
				}
			} else if (current == 1) {
				if ("".equals(qds_sql)) {
					// ressql = "select a.user_id as u_user_id,a.nickname,b.* from user_data
					// a,agenttuiguang_detail b where a.up_agentid=b.upuser_id ";
					ressql = "select a.*,b.user_id,b.nickname,b.gender,c.* from agenttuiguang_detail a,user_data b,agentlist c where a.upuser_id=b.up_agentid and a.downuser_id=b.id and b.up_agentid=c.agent_channelcode ";
				} else {
					// ressql = "select a.user_id as u_user_id,a.nickname,b.* from user_data
					// a,agenttuiguang_detail b where a.up_agentid=b.upuser_id "+qds_sql+" ";
					ressql = "select a.*,b.user_id,b.nickname,b.gender,c.* from agenttuiguang_detail a,user_data b,agentlist c where a.upuser_id=b.up_agentid and a.downuser_id=b.id and b.up_agentid=c.agent_channelcode "
							+ qds_sql + " ";
				}
			} else if (current == 2) {
				if ("".equals(qds_sql)) {
					// ressql = "select a.user_id as u_user_id,a.nickname,b.* from user_data
					// a,agenttuiguang_detail b where a.up_agentid=b.upuser_id ";
					// ressql = "select ifnull(sum(a.able_money),0) from agenttuiguang_detail
					// a,user_data b,agentlist c where a.upuser_id=b.up_agentid and
					// a.downuser_id=b.id and b.up_agentid=c.agent_channelcode ";
					// convert(ifnull(sum(a.able_money),0),decimal(10,2))
					ressql = "select convert(ifnull(sum(a.able_money),0),decimal(10,2)) from agenttuiguang_detail a,user_data b,agentlist c where a.upuser_id=b.up_agentid and a.downuser_id=b.id and b.up_agentid=c.agent_channelcode ";
				} else {
					// ressql = "select a.user_id as u_user_id,a.nickname,b.* from user_data
					// a,agenttuiguang_detail b where a.up_agentid=b.upuser_id "+qds_sql+" ";
					ressql = "select convert(ifnull(sum(a.able_money),0),decimal(10,2)) from agenttuiguang_detail a,user_data b,agentlist c where a.upuser_id=b.up_agentid and a.downuser_id=b.id and b.up_agentid=c.agent_channelcode "
							+ qds_sql + " ";
				}

			}
			break;

		case "memberbackstage":
			if (current == 0) { // 会员列表
				if (arg[5].equals("") && arg[3].equals("") && arg[4].equals("")) {
					ressql = "select count(*) from user_data where is_v!='1' and is_anchor!='1'";
				} else if (arg[3].equals("") && arg[4].equals("") && !arg[5].equals("")) {
					if ("男".equals(arg[5]) || "女".equals(arg[5])) {
						ressql = "select count(*) from user_data where gender = '" + arg[5]
								+ "' and is_v!='1' and is_anchor!='1'";
					} else {
						ressql = "select count(*) from user_data where vip = '" + arg[5]
								+ "' and is_v!='1' and is_anchor!='1'";
					}
				} else if (!arg[3].equals("") && !arg[4].equals("") && arg[5].equals("")) {
					ressql = "select count(*) from user_data where register_time between '" + arg[3]
							+ " 00:00:00' and '" + arg[4] + " 23:59:59' and is_v!='1' and is_anchor!='1' ";
				} else if (!arg[3].equals("") && !arg[4].equals("") && !arg[5].equals("")) {
					if ("男".equals(arg[5]) || "女".equals(arg[5])) {
						ressql = "select count(*) from user_data where gender = '" + arg[5]
								+ "' and register_time between '" + arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' and is_v!='1' and is_anchor!='1' ";
					} else {
						ressql = "select count(*) from user_data where vip = '" + arg[5]
								+ "' and register_time between '" + arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' and is_v!='1' and is_anchor!='1' ";
					}
				}
			} else if (current == 1) {
				if (arg[5].equals("") && arg[3].equals("") && arg[4].equals("")) {
					ressql = "select * from user_data where is_v!='1' and is_anchor!='1' order by id desc limit "
							+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
				} else if (arg[3].equals("") && arg[4].equals("") && !arg[5].equals("")) {
					if ("男".equals(arg[5]) || "女".equals(arg[5])) {
						ressql = "select * from user_data where gender = '" + arg[5]
								+ "' and is_v!='1' and is_anchor!='1' order by id desc limit " + arg[arg.length - 2]
								+ "," + JyHelpManager_boss.item;
					} else {
						ressql = "select * from user_data where vip = '" + arg[5]
								+ "' and is_v!='1' and is_anchor!='1' order by id desc limit " + arg[arg.length - 2]
								+ "," + JyHelpManager_boss.item;
					}
				} else if (!arg[3].equals("") && !arg[4].equals("") && arg[5].equals("")) {
					ressql = "select * from user_data where register_time between '" + arg[3] + " 00:00:00' and '"
							+ arg[4] + " 00:00:00' and is_v!='1' and is_anchor!='1' order by id desc limit "
							+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
				} else if (!arg[3].equals("") && !arg[4].equals("") && !arg[5].equals("")) {
					if ("男".equals(arg[5]) || "女".equals(arg[5])) {
						ressql = "select * from user_data where gender = '" + arg[5] + "' and register_time between '"
								+ arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' and is_v!='1' and is_anchor!='1' order by id desc limit "
								+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					} else {
						ressql = "select * from user_data where vip = '" + arg[5] + "' and register_time between '"
								+ arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' and is_v!='1' and is_anchor!='1' order by id desc limit "
								+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					}
				}
			} else if (current == 3) {
				ressql = "select * from user_data where id = '" + arg[7] + "' ";
			} else if (current == 4) {
				ressql = "select count(*) from user_data where id = '" + arg[7] + "' ";
			}
			break;
		// 1
		case "amemberbackstage":
//			if(current == 0) {	//
//				ressql = "select count(*) from user_data where up_agentid = '"+arg[0]+"' and is_v!='1' and is_anchor!='1'";
//			} else if(current == 1) {
//				ressql = "select * from user_data where  up_agentid = '"+arg[0]+"' and is_v!='1' and is_anchor!='1' order by id desc limit "+arg[arg.length-2]+","+JyHelpManager_boss.item;
//			}

			// log.send(DataType.basicType, "01162", "提现明细---总和", arg);
			if (current == 0) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select count(*) from user_data where up_agentid = '" + arg[0] + "'  and gender='男' ";
				} else {
					ressql = "select count(*) from user_data  where up_agentid = '" + arg[0]
							+ "'  and gender='男'  and registered_time between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'";
				}
			} else if (current == 1) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select * from user_data  where up_agentid = '" + arg[0]
							+ "'  and gender='男'  order by id desc  limit " + arg[2] + "," + JyHelpManager_boss.item
							+ " ";
				} else {
					ressql = "select * from user_data  where up_agentid = '" + arg[0]
							+ "'  and gender='男' and registered_time between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  order by id desc limit " + arg[2] + "," + JyHelpManager_boss.item + " ";
				}
			} else if (current == 3) {
				ressql = "select count(*) from user_data  where up_agentid = '" + arg[0]
						+ "'  and gender='男'   and registered_time like '%" + arg[6] + "%' ";
			} else if (current == 4) {
				ressql = "select * from user_data  where  up_agentid = '" + arg[0]
						+ "'  and gender='男'  and registered_time like '%" + arg[6] + "%' order by id desc  limit "
						+ arg[2] + "," + JyHelpManager_boss.item + "";
			}

			break;

		// 1
		case "aanchor_search":
//			if(current == 0) {	//
//				ressql = "select count(*) from user_data where up_agentid = '"+arg[0]+"' and is_v='1' and is_anchor='1'";
//			} else if(current == 1) {
//				ressql = "select * from user_data where  up_agentid = '"+arg[0]+"' and  is_v='1' and is_anchor='1' order by id desc limit "+arg[arg.length-2]+","+JyHelpManager_boss.item;
//			}

			// log.send(DataType.basicType, "01162", "提现明细---总和", arg);
			if (current == 0) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select count(*) from user_data where up_agentid = '" + arg[0] + "'  and gender='女' ";
				} else {
					ressql = "select count(*) from user_data  where up_agentid = '" + arg[0]
							+ "'  and gender='女'   and registered_time between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'";
				}
			} else if (current == 1) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select * from user_data  where up_agentid = '" + arg[0]
							+ "'  and gender='女' order by id desc  limit " + arg[2] + "," + JyHelpManager_boss.item
							+ " ";
				} else {
					ressql = "select * from user_data  where up_agentid = '" + arg[0]
							+ "'  and gender='女' and registered_time between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  order by id desc limit " + arg[2] + "," + JyHelpManager_boss.item + " ";
				}
			} else if (current == 3) {
				ressql = "select count(*) from user_data  where up_agentid = '" + arg[0]
						+ "'  and gender='女'  and registered_time like '%" + arg[6] + "%' ";
			} else if (current == 4) {
				ressql = "select * from user_data  where  up_agentid = '" + arg[0]
						+ "'  and gender='女'  and registered_time like '%" + arg[6] + "%' order by id desc  limit "
						+ arg[2] + "," + JyHelpManager_boss.item + "";
			}

			break;
		// 1
		case "pay_search":
			if (current == 0) { //
				ressql = "select count(*) from agenttuiguang_detail where upuser_id = '" + arg[0] + "' and downuser_id="
						+ arg[4];
			} else if (current == 1) {
				ressql = "select * from agenttuiguang_detail where upuser_id = '" + arg[0] + "' and downuser_id="
						+ arg[4] + " order by id desc limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
			}
			break;

		case "mymoney":

			ressql = "select * from agentlist where agent_channelcode = '" + arg[0] + "' ";

			break;

		// 1
		case "agentpay_table_search":
//			if(current == 0) {	//
//				ressql = "select count(*) from agenttuiguang_detail where upuser_id = '"+arg[0]+"'";
//			} else if(current == 1) {
//				ressql = "select * from agenttuiguang_detail  as a , user_data as b  where a.upuser_id = '"+arg[0]+"' and a.downuser_id=b.id order by a.id desc limit "+arg[arg.length-2]+","+JyHelpManager_boss.item;
//			}
			// log.send(DataType.basicType, "01162", "提现明细---总和", arg);
			if (current == 0) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select count(*) from agenttuiguang_detail where upuser_id = '" + arg[0] + "'";
				} else {
					ressql = "select count(*) from agenttuiguang_detail c,user_data u where c.upuser_id = '" + arg[0]
							+ "' and c.downuser_id=u.id  and c.uptime between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'";
				}
			} else if (current == 1) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select * from agenttuiguang_detail c,user_data u where c.upuser_id = '" + arg[0]
							+ "' and c.downuser_id=u.id   order by c.uptime desc  limit " + arg[2] + ","
							+ JyHelpManager_boss.item + " ";
				} else {
					ressql = "select * from agenttuiguang_detail c,user_data u where c.upuser_id = '" + arg[0]
							+ "' and c.downuser_id=u.id and c.uptime between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  order by c.uptime desc limit " + arg[2] + "," + JyHelpManager_boss.item
							+ " ";
				}
			} else if (current == 2) {
				if (arg[3].equals("") && arg[4].equals("")) {
					ressql = "select sum(able_money) from agenttuiguang_detail where upuser_id = '" + arg[0] + "'";
				} else {
					ressql = "select sum(able_money) from agenttuiguang_detail where  upuser_id = '" + arg[0]
							+ "' and  uptime between '" + arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59'";
				}
			} else if (current == 3) {
				ressql = "select count(*) from agenttuiguang_detail c,user_data u where  c.upuser_id = '" + arg[0]
						+ "' and c.downuser_id=u.id  and c.uptime like '%" + arg[6] + "%' ";
			} else if (current == 4) {
				ressql = "select * from agenttuiguang_detail c,user_data u where  c.upuser_id = '" + arg[0]
						+ "' and c.downuser_id=u.id and c.uptime like '%" + arg[6] + "%' order by c.uptime desc  limit "
						+ arg[2] + "," + JyHelpManager_boss.item + "";
			} else if (current == 5) {
				ressql = "select sum(able_money) from agenttuiguang_detail where   upuser_id = '" + arg[0]
						+ "' and  uptime like '%" + arg[6] + "%'";
			}

			break;

		// 1
		case "agentcash_withdrawl":
			if (current == 0) { //
				ressql = "select count(*) from agentcash_withdrawl where user_id = '" + arg[0] + "'";
			} else if (current == 1) {
				ressql = "select * from agentcash_withdrawl  as a ,agentlist as b  where a.user_id = '" + arg[0]
						+ "' and a.user_id=b.agent_channelcode order by a.id desc limit " + arg[arg.length - 2] + ","
						+ JyHelpManager_boss.item;
			}
			break;
		// 1
		case "acash_withdrawal":
			if (current == 0) { // 会员列表
				ressql = "select count(*) from agentcash_withdrawl ";
			} else if (current == 1) {
				ressql = "select * from agentcash_withdrawl  as a ,agentlist as b  where a.user_id=b.agent_channelcode order by a.id desc limit "
						+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
			}
			break;

		// 1
		case "agentist1":
			if (current == 0) {
				ressql = "select count(id) from agentlist  ";
			} else {
				ressql = "select * from agentlist    order by id desc limit " + arg[2] + "," + JyHelpManager_boss.item
						+ "";
			}
			break;

		case "agent_modbf1":

			ressql = "select * from agentlist    where id=" + arg[2];

			break;

		case "agentfencheng_set":

			ressql = "select * from agent_set    where id=1";

			break;
		case "agent_analyse":

			// log.send(DataType.basicType, "01162", "提现明细---总和", arg);

			if (arg[7].equals("0")) {
				if (current == 0) {
					if (arg[3].equals("") && arg[4].equals("")) {
						ressql = "select count(*) from agent_analyse ";
					} else {
						ressql = "select count(*) from agent_analyse  where  time_riqi between '" + arg[3]
								+ " 00:00:01' and '" + arg[4] + " 23:59:59'";
					}
				} else if (current == 1) {
					if (arg[3].equals("") && arg[4].equals("")) {
						ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode order by a.id desc  limit "
								+ arg[2] + "," + JyHelpManager_boss.item + " ";
					} else {
						ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.time_riqi between '"
								+ arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59' order by a.id desc limit " + arg[2]
								+ "," + JyHelpManager_boss.item + " ";
					}
				} else if (current == 3) {
					ressql = "select count(*) from agent_analyse  where  time_riqi like '%" + arg[6] + "%' ";
				} else if (current == 4) {
					ressql = "select * from agent_analyse as a, agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.time_riqi  like '%"
							+ arg[6] + "%' order by a.id desc  limit " + arg[2] + "," + JyHelpManager_boss.item + "";
				}
			} else {
				if (current == 0) {
					if (arg[3].equals("") && arg[4].equals("")) {
						ressql = "select count(*) from agent_analyse where agent_channelcode='" + arg[7] + "'";
					} else {
						ressql = "select count(*) from agent_analyse  where agent_channelcode='" + arg[7]
								+ "' and  time_riqi between '" + arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59'";
					}
				} else if (current == 1) {
					if (arg[3].equals("") && arg[4].equals("")) {
						ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.agent_channelcode='"
								+ arg[7] + "'   order by a.id desc  limit " + arg[2] + "," + JyHelpManager_boss.item
								+ " ";
					} else {
						ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.agent_channelcode='"
								+ arg[7] + "'   and a.time_riqi between '" + arg[3] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59' order by a.id desc limit " + arg[2] + "," + JyHelpManager_boss.item + " ";
					}
				} else if (current == 3) {
					ressql = "select count(*) from agent_analyse  where and agent_channelcode='" + arg[7]
							+ "' and time_riqi like '%" + arg[6] + "%' ";
				} else if (current == 4) {
					ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.agent_channelcode='"
							+ arg[7] + "'   and a.time_riqi  like '%" + arg[6] + "%' order by a.id desc  limit "
							+ arg[2] + "," + JyHelpManager_boss.item + "";
				}
			}

			break;
		}
		return ressql;

	}

	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {

		case "admin_add":
			if (current == 0) {
				ressql = "insert into login(username,password,power) values('" + arg[2] + "','" + arg[3] + "','2')";
			}

			break;

		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {

		// 1
		case "agent_add1":
			if (current == 0) {
				ressql = "insert into agentlist (agent_username,agent_password,agent_channelcode,agent_name,agent_url,agent_pay,agent_payname) values ('"
						+ arg[2] + "','" + arg[3] + "','" + arg[4] + "','" + arg[5] + "','" + arg[6] + "','" + arg[7]
						+ "','" + arg[8] + "')";
			}
			break;
		case "agent_mod1":
			if (current == 0) {
				ressql = "update  agentlist set agent_username='" + arg[2] + "',agent_password='" + arg[3]
						+ "',agent_channelcode='" + arg[4] + "',agent_name='" + arg[5] + "',agent_url='" + arg[6]
						+ "',agent_pay='" + arg[7] + "',agent_payname='" + arg[8] + "' where id=" + arg[9];

			}
			break;

		case "agentfencheng_mod":
			if (current == 0) {
				ressql = "update  agent_set set agent_percent='" + arg[2] + "' where id=1";

			}
			break;

		case "agent_tixian":
			if (current == -1) {
				ressql = "select ablew_money from agentlist where agent_channelcode='" + arg[2] + "'";
			} else if (current == 0) {
				ressql = "insert into agentcash_withdrawl (user_id,out_biz_no,cash,status,time,c_type) values ('"
						+ arg[2] + "','" + arg[0] + "'," + arg[3] + ",'申请中',now(),'1')";
			} else if (current == 1) {
				ressql = "update agentlist set ablew_money=ablew_money-" + arg[3] + " where agent_channelcode='"
						+ arg[2] + "'";
			}
			break;

		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
		switch (arg[1]) {

		case "admin_del":
			ressql = "delete from login where id='" + arg[2] + "'";
			break;

		case "agent_delete":
			ressql = "delete from agentlist where id='" + arg[2] + "'";
			break;

		}
		return ressql;
	}

}
