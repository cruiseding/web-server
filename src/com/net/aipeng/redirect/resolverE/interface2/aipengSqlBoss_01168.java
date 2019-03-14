package com.net.aipeng.redirect.resolverE.interface2;

import java.io.IOException;
import java.sql.SQLException;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;
import com.net.aipeng.redirect.resolverE.interface4.JyFileUploadServlet;
import com.net.aipeng.redirect.resolverE.interface4.JyHelpManager_boss;

/**
 * 分成一级部分有错误,查询条件暂不知道 搜索："分成奖励",查找错误
 *
 */

//carousel_figure_table

public class aipengSqlBoss_01168 extends SqlManager implements SqlManagerFace {
	private String sql_zj = "";
	JyFileUploadServlet jfus;
	// 图片路径
	String sites = jfus.sites;

	/***************************************
	 * addSqlface
	 ***************************************/
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
			case "agent_add1":
				if (current == 0) {
					ressql = "insert into agentlist (agent_username,agent_password,agent_channelcode,agent_name,agent_url,agent_pay,agent_payname,fencheng_u,fencheng_v) values ('"
							+ arg[2] + "','" + arg[3] + "','" + arg[4] + "','" + arg[5] + "','" + arg[6] + "','" + arg[7]
							+ "','" + arg[8] + "','" + arg[9] + "','" + arg[10] + "')";
				}
				break;
	
			case "recharge_add":
				if (current == 0) {
					ressql = "insert into love_bean_table (bean_num,give_num,bean_price) values ('" + arg[2] + "','"
							+ arg[3] + "','" + arg[4] + "')";
				}
				break;
			case "admin_logins":
				if (current == 5) {
					ressql = "insert into log_table (username,action,time,ip) values('" + arg[6] + "','登录系统',now(),'"
							+ arg[8] + "')";
				} else if (current == 6) {
					ressql = "insert into log_table (username,action,time,ip) values('" + arg[6] + "','登录系统 失败',now(),'"
							+ arg[8] + "')";
				}
				break;
			case "vip_add":
				if (current == 0) {
					ressql = "insert into vip_price_table (name,day_price,money,is_recommand) values ('" + arg[2] + "','"
							+ arg[3] + "','" + arg[4] + "','" + arg[5] + "')";
				}
				break;
	
			case "addchatfilter":
				if (current == 0) {
					ressql = "select count(*) from chatfilter where content='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "insert into chatfilter (content) values ('" + arg[2] + "')";
				}
				break;
	
			case "dashang_add":
				if (current == 0) {
					ressql = "insert into play_tour (img,name,price) values ('" + sites + "" + arg[2] + "','" + arg[3]
							+ "','" + arg[4] + "')";
				}
				break;
	
			// 系统通知(添加)
			/*
			 * case "notification_add": if(current==0){
			 * ressql="insert into notification_table(content,time,result) values('"+arg[2]+
			 * "',now(),'1')"; }else if(current==1){ ressql="select id from user_data"; }
			 * break;
			 */
			case "notification_add_all":
				if (current == 0) {
					ressql = "insert into notification_table(content,time,result,role) values('" + arg[2]
							+ "',now(),'1','全部')";
				} else if (current == 1) {
					ressql = "select id from user_data";
				}
				break;
			case "notification_add_god":
				if (current == 0) {
					ressql = "insert into notification_table(content,time,result,role) values('" + arg[2]
							+ "',now(),'1','女神')";
				} else if (current == 1) {
					ressql = "select id from user_data where is_goddess = '1' and gender = '女'";
				}
				break;
			case "notification_add_male":
				if (current == 0) {
					ressql = "insert into notification_table(content,time,result,role) values('" + arg[2]
							+ "',now(),'1','男用户')";
				} else if (current == 1) {
					ressql = "select id from user_data where gender = '男' ";
				}
				break;
	
			case "notification_add_female":
				if (current == 0) {
					ressql = "insert into notification_table(content,time,result,role) values('" + arg[2]
							+ "',now(),'1','女用户')";
				} else if (current == 1) {
					ressql = "select id from user_data where is_goddess = '0' and gender = '女'";
				}
				break;
	
			case "admin_add":
				if (current == 0) {
					ressql = "select count(*) from logins where username='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "insert into logins (username,password,job) values('" + arg[2] + "','" + arg[3] + "','"
							+ arg[4] + "')";
				}
				break;
	
			/*
			 * case "photo_add":
			 *//**
				 * arg[2]:排序序号 arg[3]:图片名称 arg[4]:跳转地址 arg[5]:分属页面 arg[6]:是否隐藏
				 *//*
					 * if(current==0){ ressql =
					 * "insert into carousel_figure_table (serial_number,chained_address,type,result) values ('"
					 * +arg[2]+"','"+arg[4]+"','"+arg[5]+"','"+arg[6]+"')"; }else if(current==1){
					 * ressql =
					 * "insert into carousel_figure_table (serial_number,carousel_photo,chained_address,type,result) values ('"
					 * +arg[2]+"','"+sites+""+arg[3]+"','"+arg[4]+"','"+arg[5]+"','"+arg[6]+"')"; }
					 * //
					 * ressql="insert into carousel_figure_table(carousel_photo,chained_address,type) values('"
					 * +sites+""+arg[2]+"','"+arg[3]+"','"+arg[4]+"') "; break;
					 */
			case "photo_add":
				/**
				 * arg[2]:排序序号 arg[3]:图片名称 arg[4]:跳转地址 arg[5]:分属页面 arg[6]:是否隐藏
				 */
				if (current == 0) {
					ressql = "insert into carousel_figure_table (serial_number,title,chained_address,type,result) values ('"
							+ arg[2] + "','" + arg[3] + "','" + arg[5] + "','" + arg[6] + "','" + arg[7] + "')";
				} else if (current == 1) {
					ressql = "insert into carousel_figure_table (serial_number,title,carousel_photo,chained_address,type,result) values ('"
							+ arg[2] + "','" + arg[3] + "','" + sites + "" + arg[4] + "','" + arg[5] + "','" + arg[6]
							+ "','" + arg[7] + "')";
				}
				// ressql="insert into
				// carousel_figure_table(carousel_photo,chained_address,type)
				// values('"+sites+""+arg[2]+"','"+arg[3]+"','"+arg[4]+"') ";
				break;
			case "version_update":
				if (current == 0) {
					ressql = "insert into version_table (content,number,address,time) values ('" + arg[2] + "','" + arg[3]
							+ "','" + arg[4] + "',now())";
				}
	
				break;
	
			}
			return ressql;
		}
	
		/***************************************
		 * modSqlface
		 ***************************************/
		@Override
		public String modSqlface(int current, String[] arg) throws SQLException, IOException {
			switch (arg[1]) {
	
			case "anchor_banned_jh":
				if (current == 0) {
					ressql = "update user_data set is_sealing='1' where user_id='" + arg[3] + "'";
				} else if (current == 1) {
					ressql = "update jianhuang_list set result='已处理',auditing_time=now() where id='" + arg[2] + "'";
				}
				break;
			case "banned_cancel_jh":
				if (current == 0) {
					ressql = "update user_data set is_sealing='0' where user_id='" + arg[2] + "'";
				}
				break;
	
			case "jianhuang_cl":
				if (current == 0) {
					ressql = "update jianhuang_list set result = '已处理',auditing_time=now() where id='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[3] + "'),'鉴黄标记处理 已审核',now(),'" + arg[4] + "')";
				}
				break;
	
			case "admin_del":
				if (current == 0) {
					ressql = "delete from logins where id='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "delete from logins_power where log_id = '" + arg[2] + "' ";
				}
				break;
			case "agent_mod1":
				if (current == 0) {
					ressql = "update  agentlist set agent_username='" + arg[2] + "',agent_password='" + arg[3]
							+ "',agent_channelcode='" + arg[4] + "',agent_name='" + arg[5] + "',agent_url='" + arg[6]
							+ "',agent_pay='" + arg[7] + "',agent_payname='" + arg[8] + "',fencheng_u='" + arg[9]
							+ "',fencheng_v='" + arg[10] + "' where id=" + arg[11];
				}
				break;
	
			case "agent_modbf1":
	
				ressql = "select * from agentlist    where id=" + arg[2];
	
				break;
	
			case "jianhuang_xiugai":
				if (current == 0) {
					ressql = "update jianhuang_set set take_time='" + arg[2] + "' where id=1";
				}
				break;
			case "jianhuang_mod":
				if (current == 0) {
					ressql = "select * from jianhuang_set where id=1";
				}
				break;
	
			case "service_mod":
				if (current == 0) {
					ressql = "select * from contact_customer_service where id=" + arg[2] + "";
				}
				break;
			case "service_xiugai":
				if (current == 0) {
					ressql = "update contact_customer_service set weixin='" + arg[3] + "',tel='" + arg[4] + "' where id="
							+ arg[2] + "";
				}
				break;
	
			// 保存代理商权限
			case "logins_mod":
				if (current == -1) {// 删除元来的权限信息
					ressql = "delete from logins_power where log_id='" + arg[2] + "'";
				} else if (current == -2) {// 保存菜单信息
					ressql = "INSERT INTO logins_power ( log_id, menu_id) VALUES (?, ?)";
				}
				break;
	
			/*
			 * case "bl_mod": if(current==0){
			 * ressql="update report_table set result = '已审核',auditing_time=now() where id='"
			 * +arg[2]+"'"; } break; case "com_mod": if(current==0){
			 * ressql="update feedback_table set result = '已处理',handle_time=now() where id='"
			 * +arg[2]+"'"; } break;
			 */
			case "bl_mod":
				if (current == 0) {
					ressql = "update report_table set result = '已审核',auditing_time=now() where id='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[3] + "'),'举报标记处理 已审核',now(),'" + arg[4] + "')";
				}
				break;
			case "c_mod1":
				if (current == 0) {
					ressql = "update card_table set stat = '已通过',auditing_time=now() where id='" + arg[2] + "'";
					ressql += ";update user_data set is_realname = '1' where id = '" + arg[3] + "'";
					ressql += ";insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "' ),'实名认证 通过',now(),'" + arg[4] + "')";
				}
				break;
			case "c_mod2":
				if (current == 0) {
					ressql = "update card_table set stat = '未通过',auditing_time=now(),reason='" + arg[3] + "' where id='"
							+ arg[2] + "'";
					ressql += ";update user_data set is_realname = '3' where id = '" + arg[4] + "'";
					ressql += ";insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[6] + "'),'实名认证 不通过',now(),'" + arg[5] + "')";
				}
				break;
			case "com_mod":
				if (current == 0) {
					ressql = "update feedback_table set result = '已处理',handle_time=now() where id='" + arg[2] + "'";
				}
				break;
			case "qd_search_a":
				ressql = "select * from user_data where id =" + arg[2] + "";
				break;
			case "qd_search_b":
				ressql = "select * from user_data where id =" + arg[2] + "";
				break;
			case "qd_mod_a":
				if (current == 0) {
					ressql = "update user_data set balance=balance+" + arg[3] + " where id=" + arg[2] + "";
				} else if (current == 1) {
					ressql = "insert into recharge_record_table (user_id,recharge_num,recharge_price,recharge_time,recharge_status,pre_paid,recharge_name) values ("
							+ arg[2] + "," + arg[3] + ",'0',now(),'1','后台','" + arg[4] + "')";
				} else if (current == 2) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "'),'用户 趣豆充值',now(),'" + arg[6] + "')";
				} else if (current == 3) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "'),'用户 趣豆充值失败',now(),'" + arg[6] + "')";
				}
				break;
			case "qd_mod_b":
				if (current == 0) {
					ressql = "update user_data set balance=balance-" + arg[3] + " where id=" + arg[2] + "";
				} else if (current == 1) {
					ressql = "insert into recharge_record_table (user_id,recharge_num,recharge_price,recharge_time,recharge_status,pre_paid,recharge_name) values ("
							+ arg[2] + "," + "-" + arg[3] + ",'0',now(),'1','后台','" + arg[4] + "')";
				} else if (current == 2) {
					ressql = "select balance from user_data where id = " + arg[2] + "";
				} else if (current == 3) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "'),'用户 趣豆扣除',now(),'" + arg[6] + "')";
				} else if (current == 4) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "'),'用户 趣豆扣除失败',now(),'" + arg[6] + "')";
				} else if (current == 5) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "'),'用户余额不足 趣豆扣除失败',now(),'" + arg[6] + "')";
				}
				break;
			case "dashang_search_bianji":
				if (current == 0) {
					ressql = "update play_tour set img = '" + sites + "" + arg[3] + "',name='" + arg[4] + "',price='"
							+ arg[5] + "' where id = " + arg[2] + "";
				}
				break;
			case "dashang_search_mod":
				if (current == 0) {
					ressql = "select * from play_tour where id=" + arg[2] + "";
				}
				break;
	
			case "mod_fenxiao":
				if (current == 0) {
					ressql = "UPDATE set_table SET mon_onefee='" + arg[3] + "',pay_v_onefee='" + arg[4] + "',pay_onefee='"
							+ arg[5] + "',cash_onefee='" + arg[6] + "' WHERE id='1'";
				}
				break;
			case "album_checknopass":
				/*
				 * ressql = "update check_list set result='未通过',refusal='"+arg[4]+"' where id='"
				 * + arg[2] + "'";
				 */
				if (current == 0) {
					ressql = "update check_list set result='未通过',refusal='" + arg[4] + "' where id='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[5] + "'),'用户信息修改审核 不通过',now(),'" + arg[6] + "') ";
				}
				break;
			case "album_checkpass":
				if (current == 0) {
					ressql = "update  check_list set result='已通过' where id='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "select photo_album from  check_list where id = '" + arg[2] + "' ";
				} else if (current == 2) {
					ressql = "select personal_album	 from user_data where id = '" + arg[3] + "' ";
				} else if (current == 3) {
					ressql = "update user_data set personal_album	 = '" + arg[4] + "' where id = '" + arg[3] + "' ";
				} else if (current == 4) {
					ressql = "select * from  check_list where id='" + arg[2] + "'";
				} else if (current == 5) {
					ressql = "update user_data set nickname='" + arg[4] + "',user_photo='" + arg[5] + "',age='" + arg[6]
							+ "',city='" + arg[7] + "',signature='" + arg[8] + "' where id=" + arg[3] + "";
				} else if (current == 6) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id= '"
							+ arg[11] + "'),'用户信息修改审核 通过',now(),'" + arg[12] + "')";
				}
				break;
			case "renzheng_checknopass":
				if (current == 0) {
					ressql = "update authentication_table set result = '未通过',refusal='" + arg[4] + "' where id='" + arg[2]
							+ "'";
				} else if (current == 1) {
					ressql = "update user_data set is_goddess='3' where id=" + arg[3] + "";
				} else if (current == 2) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[4] + "' ),'认证不通过',now(),'" + arg[5] + "')";
				}
				break;
	
			case "cash_mod":
				ressql = "update set_table set cash_lower='" + arg[2] + "'";
				break;
			case "cash_mod1":
				ressql = "select * from set_table ";
				break;
			case "photo_mod":
				if (current == 0) {
					/**
					 * arg[2]:id arg[3]:排序 arg[4]:轮播图片 arg[5]:跳转地址 arg[6]:分属页面 arg[7]:显隐
					 */
					ressql = "update carousel_figure_table set serial_number='" + arg[3] + "',title='" + arg[4]
							+ "',chained_address='" + arg[6] + "',type='" + arg[7] + "',result='" + arg[8] + "' where id = "
							+ arg[2] + "";
				} else if (current == 1) {
					ressql = "update carousel_figure_table set serial_number='" + arg[3] + "',title='" + arg[4]
							+ "',carousel_photo='" + sites + "" + arg[5] + "',chained_address='" + arg[6] + "',type='"
							+ arg[7] + "',result='" + arg[8] + "' where id='" + arg[2] + "'";
				}
				break;
			case "photo2":
				ressql = "select * from carousel_figure_table where id='" + arg[2] + "'";
				break;
			case "renzheng_checkpass":
				if (current == 0) {
					ressql = "update authentication_table set result='已通过' where id='" + arg[2] + "'";
				} else if (current == 1) {
					ressql = "select * from authentication_table where user_id =" + arg[0] + "";
				} else if (current == 2) {
					ressql = "update user_data set nickname='" + arg[3] + "',user_photo='" + arg[4] + "',age='" + arg[5]
							+ "',city='" + arg[6] + "',signature='" + arg[7] + "'," + "personal_album='" + arg[8]
							+ "',is_goddess='1' where id=" + arg[0] + "";
				} else if (current == 3) {
					ressql = "select count(*) from roles_table where user_id = '" + arg[0] + "'";
				} else if (current == 4) {
					// 还需要添加一些默认值 //用户id,收到的礼物次数,成为女神时间,私信价格,视频聊天价格,语音聊天价格,接听率,评价值,在线时长
					ressql = "insert into roles_table (user_id,`status`,gifts_sum,time,wordchat_price,videochat_price,voicechat_price,jieting_rate,pingjia,online_time)"
							+ " values ('" + arg[0] + "','通过','0',now(),'1','20','10','100','3.5','0')";
				} else if (current == 5) {
					// ressql = "delete from authentication_table ";
					ressql = "delete from authentication_table where id='" + arg[2] + "'";
				} else if (current == 6) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[10] + "'),'认证通过',now(),'" + arg[9] + "')";
				} else if (current == 7) {
					ressql = "insert into log_table (username,action,time,ip) values((select username from logins where id = '"
							+ arg[10] + "'),'认证通过 失败',now(),'" + arg[9] + "')";
				}
				break;
			/*
			 * else if(current==1){ ressql =
			 * "update user_data set is_v = '"+arg[4]+"',is_anchor='1' where id = '"+arg[3]
			 * +"' "; }else if(current==2){ ressql =
			 * "select * from renzheng_list where id='"+arg[2]+"' "; }else if(current==3){
			 * ressql =
			 * "update user_data set phonenum='"+arg[6]+"',height='"+arg[7]+"',weight='"+arg
			 * [8]+"',constellation='"+arg[9]+"',city='"+arg[10]+"',introduce='"+arg[11]+
			 * "',image_label='"+arg[12]+"',signature='"+arg[13]+"',pictures = '"+arg[14]
			 * +"' where id = '"+arg[4]+"' "; }else if(current==4){ ressql =
			 * "update user_data set photo = '"+arg[5]+"' where id='"+arg[4]+"' "; }else
			 * if(current==5){ ressql =
			 * "update user_data set photo = '"+arg[5]+"',pictures = '"+arg[14]
			 * +"' where id='"+arg[4]+"'"; }else if(current==6){ ressql =
			 * "select labcolor from lablist_c where lab_name = '"+arg[0]+"' "; }
			 */
	
			case "vip_mod1":
				// A-boss-mod,vip_mod1,1,一年会员,0.20元/天,79.00,0
				ressql = "update vip_price_table set name='" + arg[3] + "',day_price='" + arg[4] + "',money='" + arg[5]
						+ "',is_recommand='" + arg[6] + "' where id='" + arg[2] + "'";
				break;
			case "vip_mod":
				ressql = "select * from vip_price_table where id='" + arg[2] + "'";
				break;
			case "recharge_mod1":
				ressql = "update love_bean_table set bean_num='" + arg[3] + "',give_num='" + arg[4] + "',bean_price='"
						+ arg[5] + "' where id='" + arg[2] + "'";
				break;
			case "recharge_mod":
				ressql = "select * from love_bean_table where id='" + arg[2] + "'";
				break;
			case "jujue_money":
				if (current == 0) {
					ressql = "update cash_table " + "set `status` = '提现失败" + "' where id = '" + arg[2] + "'";
				}
				break;
			case "picture_del":
				if (current == 0) {
					// 0为信息审核表中相册删除
					// 1为女神审核表中相册删除
					if (arg[2].equals("0")) {
						ressql = "select photo_album from check_list where id=" + arg[3];
					} else if (arg[2].equals("1")) {
						ressql = "select photo_album from authentication_table where id=" + arg[3];
					} else {
						ressql = "select personal_album	 from user_data where id=" + arg[3];
					}
	
				} else {
					if (arg[2].equals("0")) {
						ressql = "update check_list set photo_album='" + arg[0] + "' where id='" + arg[3] + "'";
					} else if (arg[2].equals("1")) {
						ressql = "update authentication_table set photo_album='" + arg[0] + "' where id='" + arg[3] + "'";
					} else {
						ressql = "update user_data set personal_album	='" + arg[0] + "' where id='" + arg[3] + "'";
					}
	
				}
				break;
	
			case "anchor_banned":
				if (current == 0) {
					ressql = "update user_data set is_sealing='1' where id='" + arg[2] + "'";
				}
				break;
			case "banned_cancel":
				if (current == 0) {
					ressql = "update user_data set is_sealing='0' where id='" + arg[2] + "'";
				}
				break;
			case "admin_mod":
				if (current == 0) {
					ressql = "select * from logins where id='" + arg[2] + "'";
				}
				break;
			/*
			 * case "admin_mod1": if(current==0){
			 * ressql="update logins set username='"+arg[3]+"',password='"+arg[4]+"',job='"+
			 * arg[5]+"' where id='"+arg[2]+"' "; } break;
			 */
			case "recycle_hide":
				if (current == 0) {
					ressql = "update carousel_figure_table set result='隐藏' where id='" + arg[2] + "' ";
				}
				break;
			case "recycle_appear":
				if (current == 0) {
					ressql = "update carousel_figure_table set result='显示' where id='" + arg[2] + "' ";
				}
				break;
			case "admin_mod1":
				if (current == 0) {
					ressql = "select count(*) from logins where username='" + arg[3] + "'";
				} else if (current == 1) {
					ressql = "update logins set username='" + arg[3] + "',password='" + arg[4] + "',job='" + arg[5]
							+ "' where id='" + arg[2] + "' ";
				}
				break;
	
			}
			return ressql;
		}
	
		/**************************************
		 * deleteSqlface
		 ************************************/
		@Override
		public String deleteSqlface(String[] arg) throws SQLException {
			switch (arg[1]) {
			case "jianhuang_sc":
				ressql = "delete from jianhuang_list where id='" + arg[2] + "'";
				break;
			case "agent_delete":
				ressql = "delete from agentlist where id='" + arg[2] + "'";
				break;
	
			case "bl_del":
				ressql = "delete from report_table where id='" + arg[2] + "'";
				break;
	
			case "com_del":
				ressql = "delete from feedback_table where id='" + arg[2] + "'";
				break;
			case "recharge_del":
				ressql = "delete from love_bean_table where id=" + arg[2] + "";
				break;
	
			case "vip_del":
				ressql = "delete from vip_price_table where id=" + arg[2] + "";
				break;
	
			case "delchatfilter":
				ressql = "delete from chatfilter where id='" + arg[2] + "'";
				break;
			case "dashang_del":
				ressql = "delete from play_tour where id='" + arg[2] + "'";
				break;
			case "photo_del":
				ressql = "delete from carousel_figure_table where id='" + arg[2] + "'";
				break;
	
			case "renzheng_record_del":
				ressql = "delete from authentication_table where id='" + arg[2] + "'";
				break;
	
			}
			return ressql;
		}
	
		/**************************************
		 * searchSqlface
		 ************************************/
		@Override
		public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
			switch (arg[1]) {
	
			case "pay_search":
				if (current == 0) { // 会员列表
					ressql = "select count(*) from agenttuiguang_detail where upuser_id = '" + arg[0] + "' and downuser_id="
							+ arg[4];
				} else if (current == 1) {
					ressql = "select * from agenttuiguang_detail where upuser_id = '" + arg[0] + "' and downuser_id="
							+ arg[4] + " order by id desc limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
				}
				break;
	
			case "aanchor_search":
				sql_zj = "";
				if (!arg[3].equals("") && !arg[4].equals("")) {
					sql_zj = sql_zj + " and o.recharge_time between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  ";
				}
				if (current == 0) {
					if (sql_zj.equals("")) {
						ressql = "select count(*) from user_data where up_agentid = '" + arg[0] + "' and gender='女'";
					} else {
						ressql = "select count(*) from user_data where up_agentid = '" + arg[0] + "' and gender='女' "
								+ sql_zj + " ";
					}
					// p2开始时间 p4结束时间 p5 未付款 p7 id p8 订单号 p9 渠道商
				} else if (current == 1) {// 查询所有集合
					if (sql_zj.equals("")) {
						ressql = "select id,user_id,user_photo,nickname,gender,phone,registered_time from user_data where  up_agentid = '"
								+ arg[0] + "' and gender='女' order by id desc limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					} else {
						ressql = "select id,user_id,user_photo,nickname,gender,phone,registered_time from user_data where  up_agentid = '"
								+ arg[0] + "' and gender='女' " + sql_zj + "  order by id desc limit " + arg[arg.length - 2]
								+ "," + JyHelpManager_boss.item;
					}
	
				}
				break;
			/*
			 * if(current == 0) { //会员列表 ressql =
			 * "select count(*) from user_data where up_agentid = '"+arg[0]
			 * +"' and gender='女'"; } else if(current == 1) { ressql =
			 * "select id,user_id,user_photo,nickname,gender,phone,registered_time from user_data where  up_agentid = '"
			 * +arg[0]+"' and  gender='女' order by id desc limit "+arg[arg.length-2]+","+
			 * JyHelpManager_boss.item; }
			 */
			case "amemberbackstage":
				sql_zj = "";
	
				if (!arg[3].equals("") && !arg[4].equals("")) {
					sql_zj = sql_zj + " and o.recharge_time between '" + arg[3] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  ";
				}
	
				/*
				 * if(!"0".equals(arg[5])){ sql_zj=sql_zj+" and o.user_id='" + arg[5] + "' "; }
				 */
				if (current == 0) {
					if (sql_zj.equals("")) {
						// ressql = "select count(*) " + "from recharge_record_table o,user_data b where
						// o.user_id=b.id and pre_paid='后台' ";
						ressql = "select count(*) from user_data where up_agentid = '" + arg[0] + "' and gender='男'";
					} else {
						// ressql = "select count(*) " + "from recharge_record_table o,user_data b where
						// o.user_id=b.id "+sql_zj +" and pre_paid='后台' ";
						ressql = "select count(*) from user_data where up_agentid = '" + arg[0] + "' and gender='男' "
								+ sql_zj + " ";
					}
					// }
	
					// p2开始时间 p4结束时间 p5 未付款 p7 id p8 订单号 p9 渠道商
				} else if (current == 1) {// 查询所有集合
					if (sql_zj.equals("")) {
						ressql = "select id,user_id,user_photo,nickname,gender,phone,registered_time from user_data where  up_agentid = '"
								+ arg[0] + "' and gender='男' order by id desc limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
						/*
						 * ressql = "select b.id,b.user_id,b.nickname,o.* " +
						 * "from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' "
						 * + " order by o.recharge_time desc  " + "limit " + arg[arg.length - 2] + "," +
						 * JyHelpManager_boss.item;
						 */
					} else {
						/*
						 * ressql = "select b.id,b.user_id,b.nickname,o.* " +
						 * "from recharge_record_table o,user_data b where o.user_id=b.id  "+sql_zj
						 * +" and pre_paid='后台' " + " order by o.recharge_time desc  " + "limit " +
						 * arg[arg.length - 2] + "," + JyHelpManager_boss.item;
						 */
						ressql = "select id,user_id,user_photo,nickname,gender,phone,registered_time from user_data where  up_agentid = '"
								+ arg[0] + "' and gender='男' " + sql_zj + "  order by id desc limit " + arg[arg.length - 2]
								+ "," + JyHelpManager_boss.item;
					}
	
				} /*
					 * else if(current==2){ if(sql_zj.equals("")){ ressql =
					 * "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' and o.recharge_num like '%-%'"
					 * ; }else{ ressql =
					 * "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id  "
					 * +sql_zj +" and pre_paid='后台' and o.recharge_num like '%-%'"; }
					 * 
					 * }
					 */
				break;
			/*
			 * if(current == 0) { //会员列表 ressql =
			 * "select count(*) from user_data where up_agentid = '"+arg[0]
			 * +"' and gender='男'"; } else if(current == 1) { ressql =
			 * "select id,user_id,user_photo,nickname,gender,phone,registered_time from user_data where  up_agentid = '"
			 * +arg[0]+"' and gender='男' order by id desc limit "+arg[arg.length-2]+","+
			 * JyHelpManager_boss.item; }
			 */
	
			case "agent_login":
				ressql = "select * from agentlist where agent_username='" + arg[3] + "' and agent_password='" + arg[4]
						+ "'";
				break;
			case "selagentlist":
				ressql = "select * from agentlist    order by id desc ";
				break;
	
			case "agent_analyse":
				if (arg[7].equals("0")) {
					if (current == 0) {
						if (arg[3].equals("") && arg[4].equals("")) {
							ressql = "select count(*) from agent_analyse ";
						} else {
							ressql = "select count(*) from agent_analyse  where  time_riqi between '" + arg[3]
									+ "' and DATE_ADD('" + arg[4] + "',INTERVAL 1 DAY)";
						}
					} else if (current == 1) {
						if (arg[3].equals("") && arg[4].equals("")) {
							ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode order by a.id desc  limit "
									+ arg[2] + "," + JyHelpManager_boss.item + " ";
						} else {
							ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.time_riqi between '"
									+ arg[3] + "' and DATE_ADD('" + arg[4] + "',INTERVAL 1 DAY) order by a.id desc limit "
									+ arg[2] + "," + JyHelpManager_boss.item + " ";
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
									+ "' and  time_riqi between '" + arg[3] + "' and DATE_ADD('" + arg[4]
									+ "',INTERVAL 1 DAY)";
						}
					} else if (current == 1) {
						if (arg[3].equals("") && arg[4].equals("")) {
							ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.agent_channelcode='"
									+ arg[7] + "'   order by a.id desc  limit " + arg[2] + "," + JyHelpManager_boss.item
									+ " ";
						} else {
							ressql = "select * from agent_analyse as a ,agentlist as b   where a.agent_channelcode = b.agent_channelcode and a.agent_channelcode='"
									+ arg[7] + "'   and a.time_riqi between '" + arg[3] + "' and DATE_ADD('" + arg[4]
									+ "',INTERVAL 1 DAY) order by a.id desc limit " + arg[2] + "," + JyHelpManager_boss.item
									+ " ";
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
	
			case "acash_withdrawal":
				if (current == 0) { // 会员列表
					ressql = "select count(*) from agentcash_withdrawl ";
				} else if (current == 1) {
					ressql = "select * from agentcash_withdrawl  as a ,agentlist as b  where a.user_id=b.agent_channelcode order by a.id desc limit "
							+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
				} else if (current == 2) {
					ressql = "select sum(cash) as sum from agentcash_withdrawl";
				}
				break;
	
			case "agentist1":
				if (current == 0) {
					ressql = "select count(id) from agentlist  ";
				} else {
					ressql = "select * from agentlist    order by id desc limit " + arg[2] + "," + JyHelpManager_boss.item
							+ "";
				}
				break;
	
			case "jianhuang_set":
				if (current == 0) {
					ressql = "select * from jianhuang_set where id=1";
				}
				break;
	
			case "jianhuang_list":
				sql_zj = "";
				if (!"".equals(arg[4])) {
					sql_zj = sql_zj + " and a.user_id='" + arg[4] + "' ";
				}
	
				if (!"".equals(arg[5])) {
					sql_zj = sql_zj + " and a.nickname like'%" + arg[5] + "%' ";
				}
				if (!"".equals(arg[6])) {
					sql_zj = sql_zj + " and b.porn_suggestion = '" + arg[6] + "'";
				}
	
				if (!"".equals(arg[7])) {
					sql_zj = sql_zj + " and b.terrorism_suggestion = '" + arg[7] + "'";
				}
	
				if (current == 0) {
	
					/*
					 * "+pageIndex+"&p3=tojson&p4="+nil_num+"&p5="+nil_txt+"&p6="+searchname1+"&p7=
					 * "+searchname2," //p4:id p5:昵称 p6:涉黄 p7:涉黑
					 * 
					 */
	
					if ("".equals(sql_zj)) {
						ressql = "select count(*) from user_data a,jianhuang_list b where a.id=b.user_id  ";
						// ressql = "select count(*) from user_data ";
					} else {
						ressql = "select count(*) from user_data a,jianhuang_list b where a.id=b.user_id  " + sql_zj + "  ";
						// ressql = "select count(*) from user_data where 1=1 "+sql_yiji;
					}
	
				} else if (current == 1) {
					if ("".equals(sql_zj)) {
						ressql = "select a.user_id as u_user_id,a.nickname,a.phone,b.* from user_data a,jianhuang_list b where a.id=b.user_id order by b.id desc  "
								+ "limit " + arg[0] + "," + JyHelpManager_boss.item;
					} else {
						ressql = "select a.user_id as u_user_id,a.nickname,a.phone,b.* from user_data a,jianhuang_list b where a.id=b.user_id  "
								+ sql_zj + "" + "  order by b.id desc limit " + arg[0] + "," + JyHelpManager_boss.item;
					}
	
				} else if (current == 2) {
					// 涉黄
					ressql = "select count(*) from jianhuang_list where id='" + arg[0]
							+ "' and result='处理中' and porn_suggestion='review'";
				} else if (current == 3) {
					// 涉恐
					ressql = "select count(*) from jianhuang_list where id='" + arg[0]
							+ "' and result='处理中' and terrorism_suggestion='review'";
				}
				break;
			case "fencheng_yiji":
				String sql_yiji = "";
	
				/*
				 * if(current==0){ ressql = "select onefee from earnings_set where id =1"; }else
				 * if(current==1){ ressql =
				 * "select count(*) from user_data where promoter_id = '"+arg[4]+"'"; }else
				 * if(current==2){ //序号 用户ID 用户昵称 余额 联系方式 ressql =
				 * "select id,nickname,phone,register_time from user_data where promoter_id = '"
				 * +arg[4]+"' limit "+arg[0]+","+JyHelpManager_boss.item+""; }
				 */
				/*
				 * if(!"".equals(arg[4])){ sql_yiji=sql_yiji+" and id='" + arg[4] + "' "; }
				 * if(!"".equals(arg[5])){ sql_yiji=sql_yiji+" and nickname like '%" + arg[5] +
				 * "%' "; }
				 */
	
				if (current == 0) {
					if ("".equals(sql_yiji)) {
						// ressql = "select count(*) from user_data a,Income_details_table b where
						// a.id=b.user_id and a.promoter_id='"+arg[4]+"' and b.type='分成奖励' ";
	
						ressql = "select a.nickname,b.* from user_data a,tuiguang_detail b where a.id=b.downuser_id and upuser_id='"
								+ arg[4] + "' ";
						// ressql = "select count(*) from user_data ";
					} else {
						// ressql = "select count(*) from user_data a,Income_details_table b where
						// a.id=b.user_id and a.promoter_id='"+arg[4]+"' and b.type='分成奖励' "+sql_yiji+"
						// ";
	
						ressql = "select a.user_id,a.nickname,b.* from user_data a,tuiguang_detail b where a.id=b.downuser_id and upuser_id='"
								+ arg[4] + "'  " + sql_yiji + " ";
	
					}
	
				} else if (current == 1) {
					if ("".equals(sql_yiji)) {
						ressql = "select a.id,a.user_id as u_user_id,a.nickname,a.phone,b.money,b.time from user_data a,Income_details_table b where a.id=b.user_id and a.promoter_id='"
								+ arg[4] + "' and b.type='分成奖励' " + "limit " + arg[0] + "," + JyHelpManager_boss.item;
					} else {
	
						ressql = "select a.id,a.user_id as u_user_id,a.nickname,a.phone,b.money,b.time from user_data a,Income_details_table b where a.id=b.user_id and a.promoter_id='"
								+ arg[4] + "' and b.type='分成奖励' " + sql_yiji + "" + "limit " + arg[0] + ","
								+ JyHelpManager_boss.item;
					}
	
				} /*
					 * else if(current==2){ ressql =
					 * "select id,nickname,phone from user_data where id="+arg[0]+""; }else
					 * if(current==3){ ressql =
					 * "select count(*) from user_data where promoter_id = '"+arg[6]+"'"; }
					 */
	
				break;
	
			case "fenxiao_liebiao":
				// &p2=1&p3=tojsp&p4=&p5=
				// p2:页码 p3:tojsp p4:id p5:昵称
				String sql_fencheng = "";
	
				if (!"".equals(arg[4])) {
					sql_fencheng = sql_fencheng + " and user_id='" + arg[4] + "' ";
				}
				if (!"".equals(arg[5])) {
					sql_fencheng = sql_fencheng + " and nickname like '%" + arg[5] + "%' ";
				}
	
				if (!"".equals(arg[6])) {
					sql_fencheng = sql_fencheng + " and promoter_id = '" + arg[6] + "' ";
				}
	
				if (!"".equals(arg[7])) {
					sql_fencheng = sql_fencheng + " and gender = '" + arg[7] + "' ";
				}
	
				if (current == 0) {
					if ("".equals(sql_fencheng)) {
						ressql = "select count(*) from user_data ";
					} else {
						ressql = "select count(*) from user_data where 1=1 " + sql_fencheng;
					}
	
				} else if (current == 1) {
					if ("".equals(sql_fencheng)) {
						ressql = "select id as user_id1,user_id as u_user_id,nickname as nickname1,phone as phone,promoter_id,gender from user_data  "
								+ "limit " + arg[2] + "," + JyHelpManager_boss.item;
					} else {
						ressql = "select id as user_id1,user_id as u_user_id,nickname as nickname1,phone as phone,promoter_id,gender from user_data  where 1=1 "
								+ sql_fencheng + "" + "limit " + arg[2] + "," + JyHelpManager_boss.item;
					}
	
				} else if (current == 2) {
					ressql = "select id,nickname,phone from user_data where id=" + arg[0] + "";
				} else if (current == 3) {
					ressql = "select count(*) from user_data where promoter_id = '" + arg[6] + "'";
				}
				break;
	
			case "service_search":
				if (current == 0) {
					ressql = "select * from contact_customer_service limit 0,2";
				}
				break;
	
			case "admin_quanxian":
				if (current == -1) {
					ressql = "select GROUP_CONCAT(p.menu_id order by p.menu_id separator '|') as menuids,p.log_id from logins_power p where p.log_id=1";
				}
				break;
			case "admin_quanxian1":
				ressql = "select a.id,a.menu_name as name,a.parent_id,a.`level`,case when level=1 then 'true' else 'false' end as open,case when b.id is null then 'false' else 'true' end as checked from logins_menu a left join logins_power b on a.id=b.menu_id and b.log_id= "
						+ arg[2] + " order by level ,id";
				break;
	
			/*
			 * case "admin_quanxian": if (current == -1) {//查询菜单信息 //设置中带不受限 String condis =
			 * ""; if ("1".equals(arg[3])) { condis = " or 1=1"; } ressql =
			 * "select a.id,a.menu_name as name,a.parent_id,a.`level`,case when b.id is null then 'false' else 'true' end  as checked from logins_menu a left join logins_power b on a.id=b.menu_id and b.log_id="
			 * +arg[2]+""; } break;
			 */
			case "admin_logins":
				if (current == 0) {
					ressql = "select password from logins where username='" + arg[6] + "'";
				} else if (current == 1) {
					ressql = "select id from logins where username='" + arg[6] + "'";
				} else if (current == 2) {
					// ressql = "select id from logins where usrename='"+arg[2]+"'";
					ressql = "select * from logins_power a,logins_menu b where a.menu_id=b.id and  log_id='" + arg[0]
							+ "' and level='1' ";
				} else if (current == 3) {
					// ressql = "select * from logins_power a,logins_menu b where a.menu_id=b.id and
					// and log_id='"+arg[0]+"' and level='2' ";
					ressql = "select * from logins_power a,logins_menu b where a.menu_id=b.id and  log_id='" + arg[0]
							+ "' and level='2' and parent_id = '" + arg[5] + "' ";
				} else if (current == 4) {
					ressql = "select * from logins where id=" + arg[0] + "";
				}
				break;
	
			/*
			 * case "main_user": if(current==0){ ressql =
			 * "select sum(balance) from user_data"; } break;
			 */
	
			/*
			 * case "main_user": if(current==0){ ressql =
			 * "select count(*) as register_number,count(case when is_goddess = '1' then is_goddess end) as goddess_number,count(case when is_goddess = '1' and is_online = '4' then is_goddess end) as online4_number from user_data"
			 * ; ressql
			 * +=";select sum(recharge_record_table.recharge_price) as recharge_price,count(*) as order_number,sum(recharge_record_table.recharge_num) as recharge_number from recharge_record_table where DATE_FORMAT(recharge_record_table.recharge_time,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')"
			 * ; ressql
			 * +=";select sum(case when pre_paid = '支付宝' then recharge_record_table.recharge_price else 0 end) as alipay_price,sum(case when pre_paid = '微信' then recharge_record_table.recharge_price else 0 end) as wechat_price,sum(case when pre_paid = '苹果' then recharge_record_table.recharge_price else 0 end) as iphone_price,sum(case when pre_paid = '后台' then recharge_record_table.recharge_price else 0 end) as background_price from recharge_record_table"
			 * ; ressql
			 * +=";select count(*) as upzhu_notyet from roles_table where status='未审核'";
			 * ressql
			 * +=";select sum(cash) as cash_price,sum(case when status = '提现成功' then cash end) as cash_success,count(case when user_id in(select id from user_data where is_goddess = '1') then cash end) as cash_upzhu_notyet,sum(case when status = '提现中' then cash end) as cash_notyet from cash_table"
			 * ; ressql
			 * +=";select id,nickname,user_photo from user_data where id in (select a.user_id from (select user_id from roles_table order by income+0 desc limit 3) as a )"
			 * ; } break;
			 */
	
			case "main_user":
				if (current == 0) {
					ressql = "select count(*) as register_number,count(case when is_goddess = '1' then is_goddess end) as goddess_number,count(case when is_goddess = '1' and is_online = '4' then is_goddess end) as online4_number from user_data";
					ressql += ";select ROUND(sum(case when order_management.pay_type <> '后台'  then order_management.pay_price else 0 end),2) as order_price,count(case when order_management.pay_type <> '后台'  then order_management.order_num else 0 end )as order_number from order_management where  DATE_FORMAT(order_management.pay_time,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')";
					ressql += ";select ROUND(sum(case when pay_type = '支付宝' then order_management.pay_price else 0 end),2) as alipay_price,ROUND(sum(case when pay_type = '微信' then order_management.pay_price else 0 end),2) as wechat_price,ROUND(sum(case when pay_type = '苹果' then order_management.pay_price else 0 end),2) as iphone_price from order_management";
					ressql += ";select count(*) as upzhu_notyet from roles_table where status='未审核'";
					ressql += ";select sum(cash) as cash_price,sum(case when status = '提现成功' then cash end) as cash_success,count(case when user_id in(select id from user_data where is_goddess = '1') then cash end) as cash_upzhu_notyet,sum(case when status = '提现中' then cash end) as cash_notyet from cash_table";
					ressql += ";select id,nickname,user_photo from user_data where id in (select a.user_id from (select user_id from roles_table order by income+0 desc limit 3) as a )";
					ressql += ";select ROUND(sum(case when recharge_record_table.pre_paid = '后台'  then recharge_record_table.recharge_num else 0 end)) as recharge_num, count(case when recharge_record_table.pre_paid = '后台'   then recharge_record_table.recharge_num else 0 end) as recharge_number from recharge_record_table where 1=1  and DATE_FORMAT(recharge_record_table.recharge_time,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') ";
				}
				break;
	
			// p2开始时间 p3页码 p4结束时间 p5 会员id p6tojsp p7主播id
	
			case "gift_record":
				String sql_gift = "";
	
				if (!arg[2].equals("") && !arg[4].equals("")) {
					sql_gift = sql_gift + " and b.send_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  ";
				}
	
				if (!"".equals(arg[5])) {
					sql_gift = sql_gift + " and a.user_id='" + arg[5] + "' ";
				}
				if (!"".equals(arg[7])) {
					sql_gift = sql_gift + " and c.user_id='" + arg[7] + "' ";
				}
	
				if (current == 0) {
					if ("".equals(sql_gift)) {
						// ressql = "select count(*) " + "from recharge_record_table o,user_data b where
						// o.user_id=b.id "+sql_gift +" and pre_paid='后台' ";
						ressql = "select count(*) from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id ";
					} else {
						ressql = "select count(*) from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ sql_gift;
					}
	
				} else if (current == 1) {
					if ("".equals(sql_gift)) {
						ressql = "select a.user_id as user_id1,a.nickname as nickname1,b.*,c.user_id as user_id2,c.nickname as nickname2 from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ "limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					} else {
						ressql = "select a.user_id as user_id1,a.nickname as nickname1,b.*,c.user_id as user_id2,c.nickname as nickname2 from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ sql_gift + "" + "limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					}
	
				} else if (current == 2) {
					ressql = "select price,name from play_tour where id = " + arg[0] + "";
				} else if (current == 3) {
					if ("".equals(sql_gift)) {
						// ressql = "select ifnull(sum(recharge_price),0) from user_data a,gift_record
						// b,user_data c where a.id=b.send_id and b.target_id=c.id ";
						ressql = "select ifnull(sum(e.price),0) from (select b.gift_id from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id ) d,play_tour e where d.gift_id=e.id";
					} else {
						// ressql = "select count(*) from user_data a,gift_record b,user_data c where
						// a.id=b.send_id and b.target_id=c.id "+sql_gift;
						ressql = "select ifnull(sum(e.price),0) from (select b.gift_id from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ sql_gift + ") d,play_tour e where d.gift_id=e.id";
					}
				}
	
				break;
	
			case "gift_record2":
				sql_gift = "";
	
				if (!arg[2].equals("") && !arg[4].equals("")) {
					sql_gift = sql_gift + " and b.send_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
							+ " 23:59:59'  ";
				}
	
				if (!"".equals(arg[5])) {
					sql_gift = sql_gift + " and a.user_id='" + arg[5] + "' ";
				}
				if (!"".equals(arg[7])) {
					sql_gift = sql_gift + " and c.user_id='" + arg[7] + "' ";
				}
	
				if (current == 0) {
					if ("".equals(sql_gift)) {
						// ressql = "select count(*) " + "from recharge_record_table o,user_data b where
						// o.user_id=b.id "+sql_gift +" and pre_paid='后台' ";
						ressql = "select count(*) from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id ";
					} else {
						ressql = "select count(*) from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ sql_gift;
					}
	
				} else if (current == 1) {
					if ("".equals(sql_gift)) {
						ressql = "select a.user_id as user_id1,a.nickname as nickname1,b.*,c.user_id as user_id2,c.nickname as nickname2 from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id ";
					} else {
						ressql = "select a.user_id as user_id1,a.nickname as nickname1,b.*,c.user_id as user_id2,c.nickname as nickname2 from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ sql_gift + "";
					}
	
				} else if (current == 2) {
					ressql = "select price,name from play_tour where id = " + arg[0] + "";
				} else if (current == 3) {
					if ("".equals(sql_gift)) {
						// ressql = "select ifnull(sum(recharge_price),0) from user_data a,gift_record
						// b,user_data c where a.id=b.send_id and b.target_id=c.id ";
						ressql = "select ifnull(sum(e.price),0) from (select b.gift_id from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id ) d,play_tour e where d.gift_id=e.id";
					} else {
						// ressql = "select count(*) from user_data a,gift_record b,user_data c where
						// a.id=b.send_id and b.target_id=c.id "+sql_gift;
						ressql = "select ifnull(sum(e.price),0) from (select b.gift_id from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
								+ sql_gift + ") d,play_tour e where d.gift_id=e.id";
					}
				}
	
				break;
	
			/*
			 * case "gift_record": String sql_gift = "";
			 * 
			 * if(!arg[2].equals("") && !arg[4].equals("")){
			 * sql_gift=sql_gift+" and b.send_time between '" + arg[2] + " 00:00:01' and '"
			 * + arg[4] + " 23:59:59'  "; }
			 * 
			 * if(!"".equals(arg[5])){ sql_gift=sql_gift+" and b.send_id='" + arg[5] + "' ";
			 * } if(!"".equals(arg[7])){ sql_gift=sql_gift+" and b.target_id='" + arg[7] +
			 * "' "; }
			 * 
			 * if (current == 0) { //ressql = "select count(*)  " +
			 * "from recharge_record_table o,user_data b where o.user_id=b.id  "+sql_gift
			 * +" and pre_paid='后台' "; ressql =
			 * "select count(*) from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
			 * ; }else if(current==1){ ressql =
			 * "select a.user_id as user_id1,a.nickname as nickname1,b.*,c.user_id as user_id2,c.nickname as nickname2 from user_data a,gift_record b,user_data c where a.id=b.send_id and b.target_id=c.id "
			 * ; }else if(current==2){ ressql =
			 * "select price,name from play_tour where id = "+arg[0]+""; }
			 * 
			 * break;
			 */
	
			//// p2开始时间 p3页码 p4结束时间 p5 用户id p6tojsp
			case "recharge_user2":
				String sql_user = "";
				if (current == -1) {
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						ressql = "select count(*) "
								+ "from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' ";
					} else {
						ressql = "select count(*)  " + "from recharge_record_table o,user_data b where o.user_id=b.id  "
								+ sql_user + " and pre_paid='后台' ";
					}
					// }
	
					// p2开始时间 p4结束时间 p5 未付款 p7 id p8 订单号 p9 渠道商
				} else if (current == 1) {// 查询所有集合
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						ressql = "select b.id,b.user_id,b.nickname,o.* "
								+ "from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' "
								+ " order by o.recharge_time desc  ";
					} else {
						ressql = "select b.id,b.user_id,b.nickname,o.* "
								+ "from recharge_record_table o,user_data b where o.user_id=b.id  " + sql_user
								+ " and pre_paid='后台' " + " order by o.recharge_time desc  ";
					}
	
				} else if (current == 2) {
	
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table where
						// pre_paid='后台'";
	
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' and o.recharge_num like '%-%'";
					} else {
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id  "
								+ sql_user + " and pre_paid='后台' and o.recharge_num like '%-%'";
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o
						// where o.pre_paid='后台' "+sql_user+"";
					}
	
				} else if (current == 3) {
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table where
						// pre_paid='后台'";
	
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' and o.recharge_num not like '%-%'";
					} else {
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id  "
								+ sql_user + " and pre_paid='后台' and o.recharge_num not like '%-%'";
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o
						// where o.pre_paid='后台' "+sql_user+"";
					}
				}
				break;
	
			case "recharge_user":
				sql_user = "";
				if (current == -1) {
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						ressql = "select count(*) "
								+ "from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' ";
					} else {
						ressql = "select count(*)  " + "from recharge_record_table o,user_data b where o.user_id=b.id  "
								+ sql_user + " and pre_paid='后台' ";
					}
					// }
	
					// p2开始时间 p4结束时间 p5 未付款 p7 id p8 订单号 p9 渠道商
				} else if (current == 1) {// 查询所有集合
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						ressql = "select b.id,b.user_id,b.nickname,o.* "
								+ "from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' "
								+ " order by o.recharge_time desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					} else {
						ressql = "select b.id,b.user_id,b.nickname,o.* "
								+ "from recharge_record_table o,user_data b where o.user_id=b.id  " + sql_user
								+ " and pre_paid='后台' " + " order by o.recharge_time desc  " + "limit "
								+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					}
	
				} else if (current == 2) {
	
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table where
						// pre_paid='后台'";
	
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' and o.recharge_num like '%-%'";
					} else {
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id  "
								+ sql_user + " and pre_paid='后台' and o.recharge_num like '%-%'";
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o
						// where o.pre_paid='后台' "+sql_user+"";
					}
	
				} else if (current == 3) {
					if (!arg[2].equals("") && !arg[4].equals("")) {
						sql_user = sql_user + " and o.recharge_time between '" + arg[2] + " 00:00:01' and '" + arg[4]
								+ " 23:59:59'  ";
					}
	
					if (!"0".equals(arg[5])) {
						sql_user = sql_user + " and b.user_id='" + arg[5] + "' ";
					}
	
					if (sql_user.equals("")) {
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table where
						// pre_paid='后台'";
	
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id and pre_paid='后台' and o.recharge_num not like '%-%'";
					} else {
						ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o,user_data b where o.user_id=b.id  "
								+ sql_user + " and pre_paid='后台' and o.recharge_num not like '%-%'";
						// ressql = "select ifnull(sum(recharge_num),0) from recharge_record_table o
						// where o.pre_paid='后台' "+sql_user+"";
					}
				}
				break;
	
			// arg[3]:time1 arg[4]:time2 arg[5]:tojsp arg[6]:user_id arg[7]:nickname
			// [A-boss-search,income_table_search,1,,,tojsp,,0,]
			case "admin_password":
				if (current == 0) {
					ressql = "select * from logins where id = " + arg[2] + "";
				}
				break;
			case "getchatfilter":
				ressql = "select * from chatfilter";
				break;
			case "dashang_search_set":
				ressql = "select * from play_tour";
				break;
	
			case "fenxiao_search_set":
				ressql = "SELECT * FROM set_table";
				break;
			// 系统通知部分(暂时没有表信息)
			case "notification_search":
				if (current == 0) {
					ressql = "select count(*) from notification_table";
				} else if (current == 1) {
					ressql = "select * from notification_table  order by time desc limit " + arg[2] + ","
							+ JyHelpManager_boss.item + "";
				}
				break;
	
			case "complaint_search":
	
				String fankui_sql = "";
				if (!arg[5].equals("") && !arg[6].equals("")) {
					fankui_sql = fankui_sql + " and c.time between '" + arg[5] + " 00:00:01' and '" + arg[6]
							+ " 23:59:59'  ";
				}
	
				// p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+startdate+"&p6="+enddate+"&p7="+result,
				// p4:id p5:开始时间 p6:结束时间 p7:查看状态
				if (!"".equals(arg[7])) {
					fankui_sql = fankui_sql + " and c.result='" + arg[7] + "' ";
				}
				if (!arg[4].equals("")) {
					fankui_sql = fankui_sql + " and u.user_id = '" + arg[4] + "'  ";
				}
	
				if (current == 0) {
					if ("".equals(fankui_sql)) {
						ressql = "select count(*) from feedback_table c,user_data u where c.user_id=u.id ";
					} else {
						ressql = "select count(*) from feedback_table c,user_data u where c.user_id=u.id   " + fankui_sql;
					}
				} else if (current == 1) {
					if ("".equals(fankui_sql)) {
						ressql = "select c.*,u.nickname,u.phone,u.user_id as u_user_id from feedback_table c,user_data u where c.user_id=u.id "
								+ " order by c.id desc  " + "limit " + arg[2] + "," + JyHelpManager_boss.item;
					} else {
						ressql = "select c.*,u.nickname,u.phone,u.user_id as u_user_id from feedback_table c,user_data u where c.user_id=u.id   "
								+ fankui_sql + " order by c.id desc  " + "limit " + arg[2] + "," + JyHelpManager_boss.item;
					}
				}
	
				break;
			/*
			 * if(current==0){ ressql = "select count(*) from(" +
			 * "select a.*,b.id as id1 from feedback_table as a, user_data as b where a.user_id=b.id) as c"
			 * ; }else if(current==1){
			 * ressql="select c.*,u.* from feedback_table c,user_data u where c.user_id=u.id order by c.time desc limit "
			 * +arg[2]+","+JyHelpManager_boss.item+" "; }
			 */
	
			case "shenhe_photo":
				ressql = "select * from check_list where id=" + arg[2] + "";
				break;
			case "album_list":
				String album_sql = "";
				if (!arg[4].equals("")) {
					album_sql = album_sql + " and  c.result = '" + arg[4] + "'  ";
				}
				if (current == -1) {
					if (album_sql.equals("")) {
						ressql = "select count(*) from  check_list  c,user_data u where c.user_id=u.id ";
					} else {
						ressql = "select count(*) from check_list c,user_data u where c.user_id=u.id   " + album_sql;
					}
				} else if (current == 1) {
					if (album_sql.equals("")) {
						ressql = "select u.id,u.user_id as ids,c.user_id,c.nickname,c.age,c.city,c.result,c.refusal,c.id as cid from check_list c,user_data u where c.user_id=u.id "
								+ " order by c.id desc  " + "limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					} else {
						ressql = "select u.id,u.user_id as ids,c.user_id,c.nickname,c.age,c.city,c.result,c.refusal,c.id as cid from check_list c,user_data u where c.user_id=u.id   "
								+ album_sql + " order by c.id desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					}
				} else if (current == 2) {
					ressql = "select signature from  check_list where id=" + arg[0] + "";
				}
	
				break;
			/*
			 * if (current == 0) { ressql = "select count(*) from  check_list  "; } else if
			 * (current == 1) { ressql =
			 * "select * from  check_list   order by id desc limit " + arg[2] + "," +
			 * JyHelpManager.item + ""; } else if (current == 2) { ressql =
			 * "select count(*) from  check_list  where status ='" + arg[3] + "'"; } else if
			 * (current == 3) { ressql = "select * from  check_list  where result='" +
			 * arg[3] + "' order by id desc limit " + arg[2] + "," + JyHelpManager.item +
			 * ""; }
			 */
	
			case "renzheng_v_no":
				if (current == 0) {// card_table 身份认证信息表
					ressql = "select count(*) from authentication_table where result='未通过' ";
				} else if (current == 1) {
					ressql = "select a.*,b.user_id as u_user_id from authentication_table a,user_data b where a.user_id=b.id and  a.result='未通过' order by a.id desc limit "
							+ arg[2] + "," + JyHelpManager_boss.item + "";
				}
				break;
			case "log_search":
				String logsql = "";
				if (!"".equals(arg[4])) {
					logsql += "and username like '%" + arg[4] + "%'";
				}
				if (!"".equals(arg[5])) {
					logsql += "and ip = '" + arg[5] + "'";
				}
				if (!arg[6].equals("") && !arg[7].equals("")) {
					logsql += "and time  between '" + arg[6] + " 00:00:01' and '" + arg[7] + " 23:59:59' ";
				}
	
				if (current == 0) {
					if (logsql == "") {
						ressql = "select count(*) from log_table";
					} else {
						ressql = "select count(*) from log_table where 1=1 " + logsql;
					}
	
				} else if (current == 1) {
					if (logsql == "") {
						ressql = "select * from log_table where 1=1 order by id desc limit " + arg[2] + ","
								+ JyHelpManager_boss.item + "";
					} else {
						ressql = "select * from log_table where 1=1 " + logsql + " order by id desc limit " + arg[2] + ","
								+ JyHelpManager_boss.item + "";
					}
	
				}
				break;
			case "renzheng_v_passed":
				if (current == 0) {
					ressql = "select count(*) from authentication_table where result='已通过'";
				} else if (current == 1) {
					ressql = "select a.*,b.user_id as u_user_id from authentication_table a,user_data b where a.user_id=b.id and a.result='已通过' order by a.id desc limit "
							+ arg[2] + "," + JyHelpManager_boss.item + "";
				}
				break;
	
			case "cash_set":
				ressql = "select * from set_table";
				break;
			case "recycle_photo":
				if (current == 0) {
					ressql = "select count(*) from carousel_figure_table";
				} else if (current == 1) {
					ressql = "select * from carousel_figure_table limit " + arg[2] + "," + JyHelpManager_boss.item + "";
				}
				break;
			case "renzheng_photosearch":
				ressql = "select * from authentication_table where id='" + arg[2] + "'";
				break;
			case "renzheng_v":
				if (current == 0) {
					// ressql = "select count(*) from authentication_table where result='审核中' and
					// is_realname='1' ";
					ressql = "select count(*) from user_data a,authentication_table b where a.id=b.user_id and b.result='审核中' and a.is_realname='1'";
				} else if (current == 1) {
					ressql = "select b.*,a.user_id as u_user_id from user_data a,authentication_table b where a.id=b.user_id and b.result='审核中' and a.is_realname='1' order by id desc limit "
							+ arg[2] + "," + JyHelpManager_boss.item + "";
				}
				break;
			case "recharge_set":
				ressql = "select * from love_bean_table";
				break;
	
			case "vip_set":
				ressql = "select * from vip_price_table";
				break;
			case "version_search":
				String vs_sql = "";
				if (!"".equals(arg[4]) || !"".equals(arg[5])) {
					vs_sql += "and time between '" + arg[4] + " 00:00:01' and '" + arg[5] + " 23:59:59' ";
				}
				if (current == 0) {
					if ("".equals(vs_sql)) {
						ressql = "select count(*) from version_table where 1 = 1";
	
					} else {
						ressql = "select count(*) from version_table where 1 = 1 " + vs_sql;
					}
				} else if (current == 1) {
					if ("".equals(vs_sql)) {
						ressql = "select * from version_table where 1 = 1 order by id desc";
	
					} else {
						ressql = "select * from version_table where 1 = 1 " + vs_sql + "order by id desc";
					}
				}
				break;
			case "certification_manage":
				String renzheng_sql = "";
				if (!arg[6].equals("") && !arg[7].equals("")) {
					renzheng_sql = renzheng_sql + " and r.auditing_time between '" + arg[6] + " 00:00:01' and '" + arg[7]
							+ " 23:59:59'  ";
				}
	
				// p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+startdate+"&p6="+enddate+"&p7="+result,
				// p4:id1 p5:id2 p6:开始时间 p7:结束时间 p8:查看状态
				if (!"".equals(arg[8])) {
					renzheng_sql = renzheng_sql + " and r.stat='" + arg[8] + "' ";
				}
				if (!"".equals(arg[4])) {
					renzheng_sql = renzheng_sql + " and u.user_id = '" + arg[4] + "'  ";
				}
	
				if (!"".equals(arg[5])) {
					renzheng_sql = renzheng_sql + " and u.nickname like '%" + arg[5] + "%'  ";
				}
	
				if (current == 0) {
					if ("".equals(renzheng_sql)) {
						// ressql = "select count(*) from feedback_table c,user_data u where
						// c.user_id=u.id ";
						ressql = "select count(*) from card_table r,user_data u where r.user_id = u.id  ";
					} else {
						// ressql = "select count(*) from feedback_table c,user_data u where
						// c.user_id=u.id "+jubao_sql ;
						ressql = "select count(*) from card_table r,user_data u where r.user_id = u.id " + renzheng_sql;
					}
				} else if (current == 1) {
					if ("".equals(renzheng_sql)) {
						ressql = "select r.*,u.id,u.nickname,u.user_id as u_user_id from card_table r,user_data u where r.user_id = u.id  order by r.id desc limit "
								+ arg[2] + "," + JyHelpManager_boss.item;
						/*
						 * ressql = "select * from feedback_table c,user_data u where c.user_id=u.id " +
						 * " order by c.id desc  " + "limit " + arg[2] + "," + JyHelpManager_boss.item;
						 */
					} else {
						ressql = "select r.*,u.id,u.nickname,u.user_id as u_user_id from card_table r,user_data u where r.user_id = u.id "
								+ renzheng_sql + " order by r.id desc limit " + arg[2] + "," + JyHelpManager_boss.item;
						/*
						 * ressql = "select * from feedback_table c,user_data u where c.user_id=u.id   "
						 * +jubao_sql + " order by c.id desc  " + "limit " + arg[2] + "," +
						 * JyHelpManager_boss.item;
						 */
					}
				}
	
				break;
			// arg[3]:time1 arg[4]:time2 arg[5]:tojsp arg[6]:年月 arg[7]:0 arg[8]:ID arg[9]:昵称
			case "blacklist_manage":
				String jubao_sql = "";
				if (!arg[6].equals("") && !arg[7].equals("")) {
					jubao_sql = jubao_sql + " and b.report_time between '" + arg[6] + " 00:00:01' and '" + arg[7]
							+ " 23:59:59'  ";
				}
	
				// p2="+pageIndex+"&p3=tojson&p4="+searchtxt+"&p5="+startdate+"&p6="+enddate+"&p7="+result,
				// p4:id1 p5:id2 p6:开始时间 p7:结束时间 p8:查看状态
				if (!"".equals(arg[8])) {
					jubao_sql = jubao_sql + " and b.result='" + arg[8] + "' ";
				}
				if (!"".equals(arg[4])) {
					jubao_sql = jubao_sql + " and a.id= '" + arg[4] + "'  ";
				}
	
				if (!"".equals(arg[5])) {
					jubao_sql = jubao_sql + " and c.id= '" + arg[5] + "'  ";
				}
	
				if (current == 0) {
					if ("".equals(jubao_sql)) {
						// ressql = "select count(*) from feedback_table c,user_data u where
						// c.user_id=u.id ";
						ressql = "select count(*) from user_data a,report_table b,user_data c where a.id=b.report_id and b.reported_id=c.id ";
					} else {
						// ressql = "select count(*) from feedback_table c,user_data u where
						// c.user_id=u.id "+jubao_sql ;
						ressql = "select count(*) from user_data a,report_table b,user_data c where a.id=b.report_id and b.reported_id=c.id "
								+ jubao_sql;
					}
				} else if (current == 1) {
					if ("".equals(jubao_sql)) {
						ressql = "select a.id as ids1,a.nickname as name1,a.phone as phone1,c.id ids2,c.nickname as name2,c.phone as phone2,b.* from user_data a,report_table b,user_data c where a.id=b.report_id and b.reported_id=c.id order by b.id desc limit "
								+ arg[2] + "," + JyHelpManager_boss.item;
						/*
						 * ressql = "select * from feedback_table c,user_data u where c.user_id=u.id " +
						 * " order by c.id desc  " + "limit " + arg[2] + "," + JyHelpManager_boss.item;
						 */
					} else {
						ressql = "select a.id as ids1,a.nickname as name1,a.phone as phone1,c.id ids2,c.nickname as name2,c.phone as phone2,b.* from user_data a,report_table b,user_data c where a.id=b.report_id and b.reported_id=c.id "
								+ jubao_sql + " order by b.id desc limit " + arg[2] + "," + JyHelpManager_boss.item;
						/*
						 * ressql = "select * from feedback_table c,user_data u where c.user_id=u.id   "
						 * +jubao_sql + " order by c.id desc  " + "limit " + arg[2] + "," +
						 * JyHelpManager_boss.item;
						 */
					}
				}
	
				break;
			/*
			 * if (current == 0) { ressql = "select count(*) from report_table"; } else if
			 * (current == 1) { ressql =
			 * "select * from report_table  order by id desc limit " + arg[2] + "," +
			 * JyHelpManager_boss.item + ""; }else if(current==2){ ressql =
			 * "select * from user_data where id='"+arg[5]+"' "; }else if(current==3){
			 * ressql = "select * from user_data where id='"+arg[6]+"' "; } break;
			 */
			case "cash_withdrawal_fx":
				sql_zj = "";
				if (!arg[3].equals("") && !arg[4].equals("")) {
					sql_zj = sql_zj + " and c.time between '" + arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59'  ";
				}
	
				if (!arg[6].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.time like '%"+arg[6]+"%'  "; }else{
					 */
					sql_zj = sql_zj + " and  c.time like '%" + arg[6] + "%'  ";
					// }
				}
	
				if (!arg[8].equals("0")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.user_id='" + arg[8] + "' "; }else{
					 */
					sql_zj = sql_zj + " and u.user_id='" + arg[8] + "' ";
					// }
				}
				if (!arg[9].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" u.nickname like '%"+arg[9]+"%'  "; }else{
					 */
					sql_zj = sql_zj + " and u.nickname like '%" + arg[9] + "%'  ";
					// }
				}
	
				if (!"".equals(arg[10])) {
					sql_zj = sql_zj + " and c.status like '" + arg[10] + "'  ";
				}
	
				if (current == -1) {
					if (sql_zj.equals("")) {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7];
						 */
						ressql = "select count(*) from tgcash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select count(*) from tgcash_table c,user_data u where c.user_id=u.id   " + sql_zj;
					}
				} else if (current == 1) {
					if (sql_zj.equals("")) {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7] + " order by c.time desc  " + "limit " + arg[arg.length - 2] + "," +
						 * JyHelpManager.item;
						 */
						ressql = "select * from tgcash_table c,user_data u where c.user_id=u.id "
								+ " order by c.time desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					} else {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql + " order by c.time desc  " + "limit " + arg[arg.length
						 * - 2] + "," + JyHelpManager.item;
						 */
						ressql = "select * from tgcash_table c,user_data u where c.user_id=u.id   " + sql_zj
								+ " order by c.time desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					}
				} else {
					if (sql_zj.equals("")) {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and  c.c_type="
						 * +arg[7];
						 */
						ressql = "select sum(c.cash) from tgcash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select sum(c.cash) from tgcash_table c,user_data u where c.user_id=u.id   " + sql_zj;
					}
				}
				break;
			case "cash_withdrawal_fx2":
				sql_zj = "";
				if (!arg[3].equals("") && !arg[4].equals("")) {
					sql_zj = sql_zj + " and c.time between '" + arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59'  ";
				}
	
				if (!arg[6].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.time like '%"+arg[6]+"%'  "; }else{
					 */
					sql_zj = sql_zj + " and  c.time like '%" + arg[6] + "%'  ";
					// }
				}
	
				if (!arg[8].equals("0")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.user_id='" + arg[8] + "' "; }else{
					 */
					sql_zj = sql_zj + " and u.user_id='" + arg[8] + "' ";
					// }
				}
				if (!arg[9].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" u.nickname like '%"+arg[9]+"%'  "; }else{
					 */
					sql_zj = sql_zj + " and u.nickname like '%" + arg[9] + "%'  ";
					// }
				}
	
				if (!"".equals(arg[10])) {
					sql_zj = sql_zj + " and c.status like '" + arg[10] + "'  ";
				}
	
				if (current == -1) {
					if (sql_zj.equals("")) {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7];
						 */
						ressql = "select count(*) from tgcash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select count(*) from tgcash_table c,user_data u where c.user_id=u.id   " + sql_zj;
					}
				} else if (current == 1) {
					if (sql_zj.equals("")) {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7] + " order by c.time desc  " + "limit " + arg[arg.length - 2] + "," +
						 * JyHelpManager.item;
						 */
						ressql = "select * from tgcash_table c,user_data u where c.user_id=u.id "
								+ " order by c.time desc  ";
					} else {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql + " order by c.time desc  " + "limit " + arg[arg.length
						 * - 2] + "," + JyHelpManager.item;
						 */
						ressql = "select * from tgcash_table c,user_data u where c.user_id=u.id   " + sql_zj
								+ " order by c.time desc  ";
					}
				} else {
					if (sql_zj.equals("")) {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and  c.c_type="
						 * +arg[7];
						 */
						ressql = "select sum(c.cash) from tgcash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select sum(c.cash) from tgcash_table c,user_data u where c.user_id=u.id   " + sql_zj;
					}
				}
				break;
			case "cash_withdrawal":
				String casql = "";
				if (!arg[3].equals("") && !arg[4].equals("")) {
					casql = casql + " and c.time between '" + arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59'  ";
				}
	
				if (!arg[6].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.time like '%"+arg[6]+"%'  "; }else{
					 */
					casql = casql + " and  c.time like '%" + arg[6] + "%'  ";
					// }
				}
	
				if (!arg[8].equals("0")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.user_id='" + arg[8] + "' "; }else{
					 */
					casql = casql + " and u.user_id='" + arg[8] + "' ";
					// }
				}
				if (!arg[9].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" u.nickname like '%"+arg[9]+"%'  "; }else{
					 */
					casql = casql + " and u.nickname like '%" + arg[9] + "%'  ";
					// }
				}
	
				if (!"".equals(arg[10])) {
					casql = casql + " and c.status like '" + arg[10] + "'  ";
				}
	
				if (current == -1) {
					if (casql.equals("")) {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7];
						 */
						ressql = "select count(*) from cash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select count(*) from cash_table c,user_data u where c.user_id=u.id   " + casql;
					}
				} else if (current == 1) {
					if (casql.equals("")) {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7] + " order by c.time desc  " + "limit " + arg[arg.length - 2] + "," +
						 * JyHelpManager.item;
						 */
						ressql = "select * from cash_table c,user_data u where c.user_id=u.id " + " order by c.time desc  "
								+ "limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
					} else {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql + " order by c.time desc  " + "limit " + arg[arg.length
						 * - 2] + "," + JyHelpManager.item;
						 */
						ressql = "select * from cash_table c,user_data u where c.user_id=u.id   " + casql
								+ " order by c.time desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					}
				} else {
					if (casql.equals("")) {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and  c.c_type="
						 * +arg[7];
						 */
						ressql = "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id   " + casql;
					}
				}
				break;
			case "cash_withdrawal2":
				casql = "";
				if (!arg[3].equals("") && !arg[4].equals("")) {
					casql = casql + " and c.time between '" + arg[3] + " 00:00:01' and '" + arg[4] + " 23:59:59'  ";
				}
	
				if (!arg[6].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.time like '%"+arg[6]+"%'  "; }else{
					 */
					casql = casql + " and  c.time like '%" + arg[6] + "%'  ";
					// }
				}
	
				if (!arg[8].equals("0")) {
					/*
					 * if(casql.equals("")){ casql=casql+" c.user_id='" + arg[8] + "' "; }else{
					 */
					casql = casql + " and u.user_id='" + arg[8] + "' ";
					// }
				}
				if (!arg[9].equals("")) {
					/*
					 * if(casql.equals("")){ casql=casql+" u.nickname like '%"+arg[9]+"%'  "; }else{
					 */
					casql = casql + " and u.nickname like '%" + arg[9] + "%'  ";
					// }
				}
	
				if (!"".equals(arg[10])) {
					casql = casql + " and c.status like '" + arg[10] + "'  ";
				}
	
				if (current == -1) {
					if (casql.equals("")) {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7];
						 */
						ressql = "select count(*) from cash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select count(*) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select count(*) from cash_table c,user_data u where c.user_id=u.id   " + casql;
					}
				} else if (current == 1) {
					if (casql.equals("")) {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7] + " order by c.time desc  " + "limit " + arg[arg.length - 2] + "," +
						 * JyHelpManager.item;
						 */
						ressql = "select * from cash_table c,user_data u where c.user_id=u.id " + " order by c.time desc  ";
					} else {
						/*
						 * ressql =
						 * "select * from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql + " order by c.time desc  " + "limit " + arg[arg.length
						 * - 2] + "," + JyHelpManager.item;
						 */
						ressql = "select * from cash_table c,user_data u where c.user_id=u.id   " + casql
								+ " order by c.time desc  ";
					}
				} else {
					if (casql.equals("")) {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and  c.c_type="
						 * +arg[7];
						 */
						ressql = "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id ";
					} else {
						/*
						 * ressql =
						 * "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id and c.c_type="
						 * +arg[7]+" and "+casql ;
						 */
						ressql = "select sum(c.cash) from cash_table c,user_data u where c.user_id=u.id   " + casql;
					}
				}
				break;
			// A-boss-search,income_table_search,1,,,tojson,,0,
			// A-boss-search,consumptionlist,1,tojson,,,,,,2018
			// p2页码 p3tojsp p4空 p5用户id p6昵称 p7开始时间 p8结束时间 p9时间条件
	
			case "consumptionlist": {
	
				boolean isFirst = false;
				String subSql = "";
				// 判断userid条件
				if (!arg[5].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_user_id='" + arg[5] + "' ";
					} else {
						subSql += " and u_user_id='" + arg[5] + "' ";
					}
				}
				// 判断昵称条件
				if (!arg[6].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_nickname like '%" + arg[6] + "%' ";
					} else {
						subSql += " and u_nickname like '%" + arg[6] + "%' ";
					}
				}
				// 判断起始日期条件
				if (!arg[7].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time >= '" + arg[7] + " 00:00:00' ";
					} else {
						subSql += " and time >='" + arg[7] + " 00:00:00' ";
					}
				}
				// 判断结束日期条件
				if (!arg[8].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time <= '" + arg[8] + " 23:59:59' ";
					} else {
						subSql += " and time <='" + arg[8] + " 23:59:59' ";
					}
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
						if (isFirst == false) {
							isFirst = true;
							subSql = " where year(time)=year('" + arg[9] + "-00-00') ";
						} else {
							subSql += " and year(time)=year('" + arg[9] + "-00-00') ";
						}
					} else if (yearOrMonth == 2) {
						if (isFirst == false) {
							isFirst = true;
							subSql = " where month(time)=month('" + arg[9] + "-00') ";
						} else {
							subSql += " and month(time)=month('" + arg[9] + "-00') ";
						}
					}
				}
	
				if (!arg[10].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where type='" + arg[10] + "' ";
					} else {
						subSql += " and type='" + arg[10] + "' ";
					}
				}
	
				if (current == 0) {
	
					ressql = "select count(*) from("
							+ "select * from pay_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on target_id=b.z_id)as c "
							+ subSql;
				} else if (current == 1) {
					ressql = "select * from pay_table inner join (select id as u_id,nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on target_id=b.z_id "
							+ subSql + " order by time desc limit " + arg[4] + "," + JyHelpManager_boss.item;
				} else if (current == 2) {
					// ifnull(sum(money),0)
					ressql = "select ifnull(sum(num),0) from("
							+ "select * from pay_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on target_id=b.z_id)as c "
							+ subSql;
				}
	
				break;
			}
	
			case "consumptionlist2": {
				boolean isFirst = false;
				String subSql = "";
				// 判断userid条件
				if (!arg[5].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_user_id='" + arg[5] + "' ";
					} else {
						subSql += " and u_user_id='" + arg[5] + "' ";
					}
				}
				// 判断昵称条件
				if (!arg[6].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_nickname like '%" + arg[6] + "%' ";
					} else {
						subSql += " and u_nickname like '%" + arg[6] + "%' ";
					}
				}
				// 判断起始日期条件
				if (!arg[7].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time >= '" + arg[7] + " 00:00:00' ";
					} else {
						subSql += " and time >='" + arg[7] + " 00:00:00' ";
					}
				}
				// 判断结束日期条件
				if (!arg[8].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time <= '" + arg[8] + " 23:59:59' ";
					} else {
						subSql += " and time <='" + arg[8] + " 23:59:59' ";
					}
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
						if (isFirst == false) {
							isFirst = true;
							subSql = " where year(time)=year('" + arg[9] + "-00-00') ";
						} else {
							subSql += " and year(time)=year('" + arg[9] + "-00-00') ";
						}
					} else if (yearOrMonth == 2) {
						if (isFirst == false) {
							isFirst = true;
							subSql = " where month(time)=month('" + arg[9] + "-00') ";
						} else {
							subSql += " and month(time)=month('" + arg[9] + "-00') ";
						}
					}
				}
	
				if (!arg[10].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where type='" + arg[10] + "' ";
					} else {
						subSql += " and type='" + arg[10] + "' ";
					}
				}
	
				if (current == 0) {
	
					ressql = "select count(*) from("
							+ "select * from pay_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on target_id=b.z_id)as c "
							+ subSql;
				} else if (current == 1) {
					ressql = "select * from pay_table inner join (select id as u_id,nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on target_id=b.z_id "
							+ subSql + " order by time desc ";
				} else if (current == 2) {
					// ifnull(sum(money),0)
					ressql = "select ifnull(sum(num),0) from("
							+ "select * from pay_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on target_id=b.z_id)as c "
							+ subSql;
				}
	
				break;
	
			}
			// A-boss-search,income_table_search,1,,,tojsp,0,
			// p2页码 p3开始时间 p4结束时间 p5tojsp p6用户id p7昵称
			// A-boss-search,income_table_search,1,,,tojson,2018,0,
			// p2页码 p3开始时间 p4结束时间 p5tojsp p6用户id p7昵称
	
			case "income_table_search": {
	
				boolean isFirst = false;
				String subSql = "";
				// 判断userid条件
				if (!arg[5].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_user_id = '" + arg[5] + "' ";
					} else {
						subSql += " and u_user_id = '" + arg[5] + "' ";
					}
				}
				// 判断昵称条件
				if (!arg[6].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_nickname like '%" + arg[6] + "%' ";
					} else {
						subSql += " and u_nickname like '%" + arg[6] + "%' ";
					}
				}
				// 判断起始日期条件
				if (!arg[7].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time >= '" + arg[7] + " 00:00:00' ";
					} else {
						subSql += " and time >='" + arg[7] + " 00:00:00' ";
					}
				}
				// 判断结束日期条件
				if (!arg[8].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time <= '" + arg[8] + " 23:59:59' ";
					} else {
						subSql += " and time <='" + arg[8] + " 23:59:59' ";
					}
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
						if (isFirst == false) {
							isFirst = true;
							subSql = " where year(time)=year('" + arg[9] + "-00-00') ";
						} else {
							subSql += " and year(time)=year('" + arg[9] + "-00-00') ";
						}
					} else if (yearOrMonth == 2) {
						if (isFirst == false) {
							isFirst = true;
							subSql = " where month(time)=month('" + arg[9] + "-00') ";
						} else {
							subSql += " and month(time)=month('" + arg[9] + "-00') ";
						}
					}
				}
	
				if (!arg[10].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where type='" + arg[10] + "' ";
					} else {
						subSql += " and type='" + arg[10] + "' ";
					}
				}
	
				if (current == 0) {
	
					ressql = "select count(*) from("
							+ "select * from Income_details_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id)as c "
							+ subSql;
				} else if (current == 1) {
					ressql = "select * from Income_details_table inner join (select id as u_id,nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id "
							+ subSql + " order by time desc limit " + arg[4] + "," + JyHelpManager_boss.item;
				} else if (current == 2) {
					ressql = "select ifnull(sum(money),0) from("
							+ "select * from Income_details_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id)as c "
							+ subSql;
				}
	
				break;
			}
			case "income_table_search2": {
	
				boolean isFirst = false;
				String subSql = "";
				// 判断userid条件
				if (!arg[5].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_user_id = '" + arg[5] + "' ";
					} else {
						subSql += " and u_user_id = '" + arg[5] + "' ";
					}
				}
				// 判断昵称条件
				if (!arg[6].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_nickname like '%" + arg[6] + "%' ";
					} else {
						subSql += " and u_nickname like '%" + arg[6] + "%' ";
					}
				}
				// 判断起始日期条件
				if (!arg[7].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time >= '" + arg[7] + " 00:00:00' ";
					} else {
						subSql += " and time >='" + arg[7] + " 00:00:00' ";
					}
				}
				// 判断结束日期条件
				if (!arg[8].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time <= '" + arg[8] + " 23:59:59' ";
					} else {
						subSql += " and time <='" + arg[8] + " 23:59:59' ";
					}
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
						if (isFirst == false) {
							isFirst = true;
							subSql = " where year(time)=year('" + arg[9] + "-00-00') ";
						} else {
							subSql += " and year(time)=year('" + arg[9] + "-00-00') ";
						}
					} else if (yearOrMonth == 2) {
						if (isFirst == false) {
							isFirst = true;
							subSql = " where month(time)=month('" + arg[9] + "-00') ";
						} else {
							subSql += " and month(time)=month('" + arg[9] + "-00') ";
						}
					}
				}
	
				if (!arg[10].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where type='" + arg[10] + "' ";
					} else {
						subSql += " and type='" + arg[10] + "' ";
					}
				}
	
				if (current == 0) {
	
					ressql = "select count(*) from("
							+ "select * from Income_details_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data where user_id = '"
							+ arg[5] + "')as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id)as c "
							+ subSql;
				} else if (current == 1) {
					ressql = "select * from Income_details_table inner join (select id as u_id,nickname as u_nickname,user_id as u_user_id from user_data where user_id = '"
							+ arg[5] + "')as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id "
							+ subSql + " order by time desc limit " + arg[4] + "," + JyHelpManager_boss.item;
				} else if (current == 2) {
					ressql = "select ifnull(sum(money),0) from("
							+ "select * from Income_details_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data where user_id = '"
							+ arg[5] + "')as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id)as c "
							+ subSql;
				}
	
				break;
			}
			case "income_table_search_daochu": {
	
				boolean isFirst = false;
				String subSql = "";
				// 判断userid条件
				if (!arg[5].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_user_id = '" + arg[5] + "' ";
					} else {
						subSql += " and u_user_id = '" + arg[5] + "' ";
					}
				}
				// 判断昵称条件
				if (!arg[6].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where u_nickname like '%" + arg[6] + "%' ";
					} else {
						subSql += " and u_nickname like '%" + arg[6] + "%' ";
					}
				}
				// 判断起始日期条件
				if (!arg[7].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time >= '" + arg[7] + " 00:00:00' ";
					} else {
						subSql += " and time >='" + arg[7] + " 00:00:00' ";
					}
				}
				// 判断结束日期条件
				if (!arg[8].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where time <= '" + arg[8] + " 23:59:59' ";
					} else {
						subSql += " and time <='" + arg[8] + " 23:59:59' ";
					}
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
						if (isFirst == false) {
							isFirst = true;
							subSql = " where year(time)=year('" + arg[9] + "-00-00') ";
						} else {
							subSql += " and year(time)=year('" + arg[9] + "-00-00') ";
						}
					} else if (yearOrMonth == 2) {
						if (isFirst == false) {
							isFirst = true;
							subSql = " where month(time)=month('" + arg[9] + "-00') ";
						} else {
							subSql += " and month(time)=month('" + arg[9] + "-00') ";
						}
					}
				}
	
				if (!arg[10].isEmpty()) {
	
					if (isFirst == false) {
						isFirst = true;
						subSql = " where type='" + arg[10] + "' ";
					} else {
						subSql += " and type='" + arg[10] + "' ";
					}
				}
	
				if (current == 0) {
	
					ressql = "select count(*) from("
							+ "select * from Income_details_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id)as c "
							+ subSql;
				} else if (current == 1) {
					ressql = "select * from Income_details_table inner join (select id as u_id,nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id "
							+ subSql + " order by time desc";
				} else if (current == 2) {
					ressql = "select ifnull(sum(money),0) from("
							+ "select * from Income_details_table inner join (select id as u_id, nickname as u_nickname,user_id as u_user_id from user_data)as a on user_id=a.u_id "
							+ "left join (select id as z_id, nickname as z_nickname,user_id as z_user_id from user_data)as b on pay_id=b.z_id)as c "
							+ subSql;
				}
	
				break;
			}
	
			/*
			 * 
			 * case "income_table_search": String insql=""; if(!arg[3].equals("") &&
			 * !arg[4].equals("")){ insql=insql+" c.time between '" + arg[3] +
			 * " 00:00:01' and '" + arg[4] + " 23:59:59'  "; }
			 * 
			 * 
			 * 
			 * if(!arg[6].equals("0")){ if(insql.equals("")){ insql=insql+" c.user_id='" +
			 * arg[6] + "' "; }else{ insql=insql+" and c.user_id='" + arg[6] + "' "; } }
			 * 
			 * if(!arg[7].equals("")){ if(insql.equals("")){
			 * insql=insql+" u.nickname like '%"+arg[7]+"%'  "; }else{
			 * insql=insql+" and u.nickname like '%"+arg[7]+"%'  "; } }
			 * 
			 * 
			 * if (current == -1) {
			 * 
			 * if(insql.equals("")){ ressql =
			 * "select count(*) from Income_details_table c,user_data u where c.user_id=u.id  "
			 * ; }else{ ressql =
			 * "select count(*) from Income_details_table c,user_data u where c.user_id=u.id  and "
			 * +insql ; } }else if(current==1){ if(insql.equals("")){ ressql =
			 * "select * from Income_details_table c,user_data u where c.user_id=u.id  " +
			 * " order by c.time desc  " + "limit " + arg[2] + "," + JyHelpManager.item; //+
			 * arg[arg.length - 2] + "," + JyHelpManager.item; }else{ ressql =
			 * "select * from Income_details_table c,user_data u where c.user_id=u.id and "
			 * +insql + " order by c.time desc  " + "limit " + arg[2] + "," +
			 * JyHelpManager.item;//+ arg[arg.length - 2] + "," + JyHelpManager.item; }
			 * }else if(current==2){ ressql =
			 * "select nickname from user_data where id = "+arg[0]+""; }else{
			 * if(insql.equals("")){ ressql =
			 * "select sum(c.money) from Income_details_table c,user_data u where c.user_id=u.id  "
			 * ; }else{ ressql =
			 * "select sum(c.money) from Income_details_table c,user_data u where c.user_id=u.id  and "
			 * +insql ; } }
			 * 
			 * break;
			 */
	
			case "pay_list":
				if (current == 1) {
					ressql = "select count(*) " + "from order_management " + "where user_id = " + arg[3] + " ";
				} else if (current == 2) {
					ressql = "select a.*,b.user_id as ids " + "from order_management a,user_data b where  a.user_id=b.id  "
							+ "and a.user_id = " + arg[3] + "  " + "order by pay_time desc " + "limit "
							+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
				} else if (current == 3) {
					ressql = "select ifnull(sum(pay_price),0) from order_management where user_id='" + arg[3] + "' ";
				}
				break;
	
			// p2开始时间 p3页码 p4结束时间 p5 未付款 p6tojsp p7 用户id p8昵称
			case "pay_table_search":
	
				String sql = "";
				// A-boss-search,pay_table_search,,1,,,tojson,2,undefined
	
				if (!arg[2].equals("") && !arg[4].equals("")) {
					sql = sql + " and o.pay_time between '" + arg[2] + " 00:00:01' and '" + arg[4] + " 23:59:59'  ";
				}
	
				if (!arg[5].equals("")) {
					sql = sql + " and o.pay_status='" + arg[5] + "' ";
				}
				if (!arg[7].equals("0")) {
					sql = sql + " and b.user_id='" + arg[7] + "' ";
				}
	
				if (!arg[8].equals("")) {
					sql = sql + " and b.nickname like '%" + arg[8] + "%' ";
				}
				if (!"".equals(arg[9])) {
					sql = sql + " and o.pay_type like '%" + arg[9] + "%' ";
				}
	
				if (current == -1) {
	
					if (sql.equals("")) {
						ressql = "select count(*) " + "from order_management o,user_data b where o.user_id=b.id  ";
					} else {
						ressql = "select count(*)  " + "from order_management o,user_data b where o.user_id=b.id  " + sql
								+ "  ";
					}
	
					// p2开始时间 p4结束时间 p5 未付款 p7 id p8 订单号 p9 渠道商
				} else if (current == 1) {// 查询所有集合
	
					if (sql.equals("")) {
						ressql = "select b.id,b.nickname,b.user_id as ids,o.* "
								+ "from order_management o,user_data b where o.user_id=b.id  "
								+ " order by o.pay_time desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					} else {
						ressql = "select b.id,b.nickname,b.user_id as ids,o.* "
								+ "from order_management o,user_data b where o.user_id=b.id  " + sql + "  "
								+ " order by o.pay_time desc  " + "limit " + arg[arg.length - 2] + ","
								+ JyHelpManager_boss.item;
					}
	
				} else if (current == 2) {
					if (sql.equals("")) {
						ressql = "select ifnull(sum(o.pay_price),0) from order_management o,user_data b where o.user_id=b.id  ";
					} else {
						ressql = "select ifnull(sum(o.pay_price),0) from order_management o,user_data b where o.user_id=b.id  "
								+ sql + " ";
					}
				}
				break;
	
			case "pay_table_search2":
	
				sql = "";
				// A-boss-search,pay_table_search,,1,,,tojson,2,undefined
	
				// A-boss-search,pay_table_search,,1,,,tojson,2,undefined
	
				if (!arg[2].equals("") && !arg[4].equals("")) {
					sql = sql + " and o.pay_time between '" + arg[2] + " 00:00:01' and '" + arg[4] + " 23:59:59'  ";
				}
	
				if (!arg[5].equals("")) {
					sql = sql + " and o.pay_status='" + arg[5] + "' ";
				}
				if (!arg[7].equals("0")) {
					sql = sql + " and b.user_id='" + arg[7] + "' ";
				}
	
				if (!arg[8].equals("")) {
					sql = sql + " and b.nickname like '%" + arg[8] + "%' ";
				}
				if (!"".equals(arg[9])) {
					sql = sql + " and o.pay_type like '%" + arg[9] + "%' ";
				}
	
				if (current == -1) {
	
					if (sql.equals("")) {
						ressql = "select count(*) " + "from order_management o,user_data b where o.user_id=b.id  ";
					} else {
						ressql = "select count(*)  " + "from order_management o,user_data b where o.user_id=b.id  " + sql
								+ "  ";
					}
	
					// p2开始时间 p4结束时间 p5 未付款 p7 id p8 订单号 p9 渠道商
				} else if (current == 1) {// 查询所有集合
	
					if (sql.equals("")) {
						ressql = "select b.id,b.nickname,b.user_id as ids,o.* "
								+ "from order_management o,user_data b where o.user_id=b.id  "
								+ " order by o.pay_time desc  ";
					} else {
						ressql = "select b.id,b.nickname,b.user_id as ids,o.* "
								+ "from order_management o,user_data b where o.user_id=b.id  " + sql + "  "
								+ " order by o.pay_time desc  ";
					}
	
				} else if (current == 2) {
					if (sql.equals("")) {
						ressql = "select ifnull(sum(o.pay_price),0) from order_management o,user_data b where o.user_id=b.id  ";
					} else {
						ressql = "select ifnull(sum(o.pay_price),0) from order_management o,user_data b where o.user_id=b.id  "
								+ sql + " ";
					}
				}
				break;
			case "renzheng_photosearch1":
				ressql = "select * from user_data where id='" + arg[2] + "'";
				break;
			case "renzheng_photosearch2":
				ressql = "select * from user_data where id='" + arg[2] + "'";
				break;
			case "get_photo":
				ressql = "select * from carousel_figure_table where id = '" + arg[2] + "' ";
				break;
			case "anchor_search1":
				if (current == 0) {
					// arg[3]:id
					// id 时间1 时间2 手机号 封禁
					// ar?p0=A-boss-search&p1=anchor_search1&p2="+pageIndex+"&p3="+searchtxt+"&p4=tojson&p5="+searchtxt1+"&p6="+startdate+"&p7="+enddate+"&p8="+searchname+"&p9="+nickname;
					/**
					 * arg[2]:页码 arg[3]:ID arg[4]:tojsp arg[5]:手机号 arg[6]:开始时间 arg[7]:结束时间
					 * arg[8]:账号状态 arg[9]:昵称 arg[10]:是否女神
					 */
					String sql1 = "";
					if (!"0".equals(arg[3])) {
						// 长ID
						sql1 = sql1 + " and a.user_id = '" + arg[3] + "' ";
					}
					if (!"0".equals(arg[5])) {
						sql1 = sql1 + " and a.phone like '%" + arg[5] + "%' ";
					}
					if (!"".equals(arg[6]) && !"".equals(arg[7])) {
						/*
						 * if(sql1.equals("")){ sql1=sql1+" a.registered_time between '" + arg[6] +
						 * " 00:00:00' and '" + arg[7] + " 23:59:59' "; }else{
						 */
						sql1 = sql1 + " and  a.registered_time between '" + arg[6] + " 00:00:00' and '" + arg[7]
								+ " 23:59:59' ";
						// }
					}
					if (!"".equals(arg[8])) {
						sql1 = sql1 + " and a.is_sealing='" + arg[8] + "' ";
					}
	
					if (!"".equals(arg[9])) {
						sql1 = sql1 + " and a.nickname like '%" + arg[9] + "%' ";
					}
	
					if (!"".equals(arg[10])) {
						sql1 = sql1 + " and a.is_goddess = '" + arg[10] + "' ";
					}
					/*
					 * if(!"".equals(arg[11])){ sql1=sql1+" and a.promoter_id = '"+arg[11]+"' "; }
					 */
	
					if (sql1.equals("")) {
						// ressql="select count(*) from user_data a,roles_table b where a.id=b.user_id
						// and a.gender='女' " ;
						ressql = "select count(*) from  user_data a where  a.gender='女'  ";
					} else {
						// ressql="select count(*) from user_data a,roles_table b where a.id=b.user_id "
						// + sql1+" and a.gender='女' ";
						ressql = "select count(*) from  user_data a  where a.gender='女'  " + sql1 + "   ";
					}
	
					// }
	
				} else if (current == 1) {
					String sql1 = "";
					if (!"0".equals(arg[3])) {
						// 长ID
						sql1 = sql1 + " and a.user_id = '" + arg[3] + "' ";
					}
					if (!"0".equals(arg[5])) {
						sql1 = sql1 + " and a.phone like '%" + arg[5] + "%' ";
					}
					if (!"".equals(arg[6]) && !"".equals(arg[7])) {
						/*
						 * if(sql1.equals("")){ sql1=sql1+" a.registered_time between '" + arg[6] +
						 * " 00:00:00' and '" + arg[7] + " 23:59:59' "; }else{
						 */
						sql1 = sql1 + " and  a.registered_time between '" + arg[6] + " 00:00:00' and '" + arg[7]
								+ " 23:59:59' ";
						// }
					}
					if (!"".equals(arg[8])) {
						sql1 = sql1 + " and a.is_sealing='" + arg[8] + "' ";
					}
	
					if (!"".equals(arg[9])) {
						sql1 = sql1 + " and a.nickname like '%" + arg[9] + "%' ";
					}
	
					if (!"".equals(arg[10])) {
						sql1 = sql1 + " and a.is_goddess = '" + arg[10] + "' ";
					}
	
					/*
					 * if(!"".equals(arg[11])){ sql1=sql1+" and a.promoter_id = '"+arg[11]+"' "; }
					 */
	
					if (sql1.equals("")) {
						/*
						 * ressql="select a.id,a.nickname,a.user_photo,a.phone,a.balance,a.is_goddess,a.is_sealing,a.is_recommand,a.is_goddess,b.gifts_sum,b.wordchat_price,b.videochat_price,b.voicechat_price,b.jieting_rate,b.online_time,b.time as ns_tims,a.last_login_time,b.pingjia from  user_data a,roles_table b where a.id=b.user_id and a.gender='女' limit "
						 * + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
						 */
						ressql = "select a.id,a.user_id as u_user_id,a.nickname,a.user_photo,a.phone,a.balance,a.is_goddess,a.is_sealing,a.is_recommand,a.is_goddess,a.last_login_time,a.promoter_id from  user_data a where  a.gender='女' limit "
								+ arg[arg.length - 2] + "," + JyHelpManager_boss.item;
						// ,b.gifts_sum,b.wordchat_price,b.videochat_price,b.voicechat_price,b.jieting_rate,b.online_time,b.time
						// as ns_tims,a.last_login_time,b.pingjia
	
					} else {
						/*
						 * ressql="select a.id,a.nickname,a.user_photo,a.phone,a.balance,a.is_goddess,a.is_sealing,a.is_recommand,a.is_goddess,b.gifts_sum,b.wordchat_price,b.videochat_price,b.voicechat_price,b.jieting_rate,b.online_time,b.time as ns_tims,a.last_login_time,b.pingjia from  user_data a,roles_table b where a.id=b.user_id  "
						 * + sql1+" and a.gender='女' limit " + arg[arg.length - 2] + "," +
						 * JyHelpManager_boss.item;
						 */
						ressql = "select a.id,a.user_id as u_user_id,a.nickname,a.user_photo,a.phone,a.balance,a.is_goddess,a.is_sealing,a.is_recommand,a.is_goddess,a.last_login_time,a.promoter_id from  user_data a where  a.gender='女'  "
								+ sql1 + "   limit " + arg[arg.length - 2] + "," + JyHelpManager_boss.item;
						// ,b.gifts_sum,b.wordchat_price,b.videochat_price,b.voicechat_price,b.jieting_rate,b.online_time,b.time
						// as ns_tims,a.last_login_time,b.pingjia
					}
				} else if (current == 2) {
					ressql = "select b.gifts_sum,b.wordchat_price,b.videochat_price,b.voicechat_price,b.jieting_rate,b.online_time,b.time as ns_tims,b.pingjia from roles_table b where user_id = '"
							+ arg[0] + "'";
				} else if (current == 3) {
					ressql = "";
				} else if (current == 4) {
					ressql = "select nickname from user_data where id = '" + arg[0] + "'";
				}
	
				break;
	
			// uiface/ar?p0=A-boss-search&p1=memberbackstage&p2=1&p3=&p4=&p5=tojson&p6=50
			case "memberbackstage":
				if (current == 0) {
					// arg[3]:id
					// id 时间1 时间2 手机号 封禁
					// p0=A-boss-search&p1=memberbackstage&p2="+pageIndex+"&p3="+startdate+"&p4="+enddate+"&p5=tojson&p6="+searchtxt+"&p7="+phone+"&p8="+gender+"&p9="+member+"&p10="+recommand+"&p11="+sealing;
	
					/**
					 * p0=A-boss-search&p1=memberbackstage&p2="+pageIndex+"&p3="+startdate+"&p4="+enddate+"&p5=tojson&p6="+searchtxt+"&p7="+phone+"&p8="+gender+"&p9="+member+"&p10="+recommand+"&p11="+sealing;
					 * arg[2]:页码 arg[3]:开始时间 arg[4]:结束时间 arg[5]:tojson arg[6]:ID arg[7]:手机号
					 * arg[8]:性别 arg[9]:是否会员 arg[10]:是否推荐 arg[11]:是否封禁
					 */
					String sql1 = "";
	
					if (!"".equals(arg[3]) && !"".equals(arg[4])) {
						/*
						 * if(sql1.equals("")){ sql1=sql1+" a.registered_time between '" + arg[6] +
						 * " 00:00:00' and '" + arg[7] + " 23:59:59' "; }else{
						 */
						sql1 = sql1 + " and  registered_time between '" + arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' ";
						// }
					}
	
					if (!"0".equals(arg[6])) {
						sql1 = sql1 + " and  user_id like '%" + arg[6] + "%' ";
					}
					if (!"".equals(arg[7])) {
						sql1 = sql1 + " and phones like '%" + arg[7] + "%' ";
					}
	
					/*
					 * if(!"".equals(arg[8])){ sql1=sql1+" and  a.gender='"+arg[8]+"' "; }
					 */
	
					if (!"".equals(arg[8])) {
						sql1 = sql1 + " and  is_member='" + arg[8] + "' ";
					}
					if (!"".equals(arg[9])) {
						sql1 = sql1 + " and  is_recommand='" + arg[9] + "' ";
					}
					if (!"".equals(arg[10])) {
						sql1 = sql1 + " and  is_sealing='" + arg[10] + "' ";
					}
					if (!"".equals(arg[11])) {
						sql1 += " and nickname like '%" + arg[11] + "%'";
					}
					/*
					 * if(!"".equals(arg[12])){ sql1 +=" and promoter_id = '"+arg[12]+"'"; }
					 */
	
					if (sql1.equals("")) {
						// ressql="select count(*) from user_data where gender='男' " ;
						ressql = "select count(*) from  user_data where gender='男' ";
					} else {
						ressql = "select count(*) from  user_data where gender='男' " + sql1;
					}
	
				} else if (current == 1) {
	
					String sql1 = "";
	
					if (!"".equals(arg[3]) && !"".equals(arg[4])) {
						/*
						 * if(sql1.equals("")){ sql1=sql1+" a.registered_time between '" + arg[6] +
						 * " 00:00:00' and '" + arg[7] + " 23:59:59' "; }else{
						 */
						sql1 = sql1 + " and  registered_time between '" + arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' ";
						// }
					}
	
					if (!"0".equals(arg[6])) {
						sql1 = sql1 + " and  user_id like '%" + arg[6] + "%' ";
					}
					if (!"".equals(arg[7])) {
						sql1 = sql1 + " and phone like '%" + arg[7] + "%' ";
					}
	
					/*
					 * if(!"".equals(arg[8])){ sql1=sql1+" and  gender='"+arg[8]+"' "; }
					 */
	
					if (!"".equals(arg[8])) {
						sql1 = sql1 + " and  is_member='" + arg[8] + "' ";
					}
					if (!"".equals(arg[9])) {
						sql1 = sql1 + " and  is_recommand='" + arg[9] + "' ";
					}
					if (!"".equals(arg[10])) {
						sql1 = sql1 + " and  is_sealing='" + arg[10] + "' ";
					}
					if (!"".equals(arg[11])) {
						sql1 += " and nickname like '%" + arg[11] + "%'";
					}
					/*
					 * if(!"".equals(arg[12])){ sql1 +=" and promoter_id = '"+arg[12]+"'"; }
					 */
	
					if (sql1.equals("")) {
						ressql = "select id,nickname,user_id,user_photo,phone,balance,registered_time,is_member,is_recommand,is_sealing,last_login_time,promoter_id from  user_data where gender='男'  limit "
								+ arg[0] + "," + JyHelpManager_boss.item + "";
					} else {
						ressql = "select id,nickname,user_id,user_photo,phone,balance,registered_time,is_member,is_recommand,is_sealing,last_login_time,promoter_id  from  user_data where gender='男' "
								+ sql1 + " limit " + arg[0] + "," + JyHelpManager_boss.item + "";
					}
	
				} else if (current == 2) {
					String sql1 = "";
					if (!"".equals(arg[3]) && !"".equals(arg[4])) {
						sql1 = sql1 + " and  a.registered_time between '" + arg[3] + " 00:00:00' and '" + arg[4]
								+ " 23:59:59' ";
					}
	
					if (!"0".equals(arg[6])) {
						sql1 = sql1 + " and  a.user_id like '%" + arg[6] + "%' ";
					}
					if (!"".equals(arg[7])) {
						sql1 = sql1 + " and a.phone like '%" + arg[7] + "%' ";
					}
	
					if (!"".equals(arg[8])) {
						sql1 = sql1 + " and  a.is_member='" + arg[8] + "' ";
					}
					/*
					 * if(!"".equals(arg[9])){ sql1=sql1+" and  is_recommand='"+arg[9]+"' "; }
					 */
					if (!"".equals(arg[10])) {
						sql1 = sql1 + " and  a.is_sealing='" + arg[10] + "' ";
					}
					if (!"".equals(arg[11])) {
						sql1 += " and a.nickname like '%" + arg[11] + "%'";
					}
	
					/*
					 * if(!"".equals(arg[12])){ sql1 +=" and a.promoter_id = '"+arg[12]+"'"; }
					 */
					if (sql1.equals("")) {
						ressql = "select sum(b.pay_price)  from  user_data a,order_management b where a.id=b.user_id and a.promoter_id='"
								+ arg[12] + "' and a.gender='男' and b.pay_status='已付款'";
					} else {
						ressql = "select sum(b.pay_price)  from  user_data a,order_management b where a.id=b.user_id and a.promoter_id='"
								+ arg[12] + "' and a.gender='男' and b.pay_status='已付款' " + sql1 + " ";
					}
				} else if (current == 3) {
					ressql = "select nickname from user_data where id = '" + arg[0] + "'";
				}
	
				break;
			/*
			 * if(current == 0) { //会员列表 if(arg[3].equals("") && arg[4].equals("")){ ressql
			 * = "select count(*) from user_data"; }else if(!arg[3].equals("") &&
			 * !arg[4].equals("")){ ressql =
			 * "select count(*) from user_data where registered_time between '"+arg[3]
			 * +" 00:00:00' and '"+arg[4]+" 23:59:59' "; }else if(!arg[3].equals("") &&
			 * arg[4].equals("") ){ ressql =
			 * "select count(*) from user_data where  registered_time >='"+arg[3]
			 * +" 00:00:00'  "; }else if(arg[3].equals("") && !arg[4].equals("") ){ ressql =
			 * "select count(*) from user_data where  registered_time <='"+arg[4]
			 * +" 00:00:00'  "; }
			 * 
			 * } else if(current == 1) { if(arg[3].equals("") && arg[4].equals("")){
			 * 
			 * ressql =
			 * "select id,nickname,user_photo,gender,phone,balance,registered_time,is_member,is_recommand,is_sealing from user_data  order by id desc limit "
			 * +arg[arg.length-2]+","+JyHelpManager.item; }else if(!arg[3].equals("") &&
			 * !arg[4].equals("")){
			 * 
			 * ressql =
			 * "select id,nickname,user_photo,gender,phone,balance,registered_time,is_member,is_recommand,is_sealing from user_data where registered_time between '"
			 * +arg[3]+" 00:00:00' and '"+arg[4]+" 23:59:59'  order by id desc limit "+arg[
			 * arg.length-2]+","+JyHelpManager.item; }else if(!arg[3].equals("") &&
			 * arg[4].equals("")){
			 * 
			 * ressql =
			 * "select id,nickname,user_photo,gender,phone,balance,registered_time,is_member,is_recommand,is_sealing from user_data where  registered_time >='"
			 * +arg[3]+" 00:00:00'  order by id desc limit "+arg[arg.length-2]+","+
			 * JyHelpManager.item; }else if(arg[3].equals("") && !arg[4].equals("")){
			 * 
			 * ressql =
			 * "select id,nickname,user_photo,gender,phone,balance,registered_time,is_member,is_recommand,is_sealing from user_data where  registered_time <='"
			 * +arg[4]+" 00:00:00'  order by id desc limit "+arg[arg.length-2]+","+
			 * JyHelpManager.item; } }else if(current==3){ ressql =
			 * "select id,nickname,user_photo,gender,phone,balance,registered_time,is_member,is_recommand,is_sealing from user_data where id = '"
			 * +arg[6]+"' "; }else if(current==4){ ressql =
			 * "select count(*) from user_data where id = '"+arg[6]+"' "; } break;
			 */
			case "admin_login":
				ressql = "select * from login where username='" + arg[3] + "' and password='" + arg[4] + "'";
				break;
			case "admin_list":
				if (current == 0) {
					ressql = "select count(*) from logins";
				} else if (current == 1) {
					ressql = "select * from logins limit " + arg[2] + "," + JyHelpManager_boss.item + "";
				}
				break;
		}
		return ressql;
	}

}
