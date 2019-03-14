package com.net.aipeng.redirect.resolverC.interface2.mA;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.net.aipeng.classroot.interface2.mA.SqlManagerFace;
import com.net.aipeng.classroot.interface2.mA.SqlManager;
import com.net.aipeng.classroot.interface4.JyHelpManager;

public class aipengSqlUser_C_01182 extends SqlManager implements SqlManagerFace {
	@Override
	public String addSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "renzheng_submit":
			if (current == 0) {
				ressql = "INSERT INTO authentication_table (user_id,photo,nickname, "
						+ "age,city,signature,photo_album,selfie,result) VALUES (" + arg[2] + ", " + "'" + arg[3]
						+ "','" + arg[4] + "'," + arg[5] + ",'" + arg[6] + "' " + ",'" + arg[7] + "','" + arg[8] + "','"
						+ arg[9] + "','审核中')";
			} else if (current == 1) {
				ressql = "update user_data set is_goddess='2' where id='" + arg[2] + "'";
			}
			break;
		default:
			break;
		}
		return ressql;
	}

	@Override
	public String modSqlface(int current, String[] arg) throws SQLException, IOException {
		// TODO Auto-generated method stub
		switch (arg[1]) {
		case "pay_vip":
			if (current == 0) {
				ressql = "update user_data set is_member='1',member_end_time='" + arg[3] + "' where id='" + arg[2]
						+ "'";
			}
			break;
		case "sayhi":
			if (current == 0) {// 查询数量>0，修改；=0，添加
				ressql = "select count(0) from say_hi_table where send_id='" + arg[2] + "' and target_id='" + arg[3]
						+ "'";
			} else if (current == 1) {
				ressql = "update say_hi_table set time=NOW() where send_id='" + arg[2] + "' and target_id='" + arg[3]
						+ "'";
			} else if (current == 2) {
				ressql = "insert into say_hi_table (send_id,target_id,time,status) values('" + arg[2] + "','" + arg[3]
						+ "',now(),'1')";
			}
			break;
		case "change_status":
			if (current == 0) {
				ressql = "update user_data set is_public_contact='" + arg[3] + "' where id='" + arg[2] + "'";
			} else if (current == 1) {
				ressql = "update user_data set is_message_reminder='" + arg[3] + "' where id='" + arg[2] + "'";
			}
			break;
		case "binding_phone":
			if (current == 0) {
				ressql = "update user_data set phone='" + arg[3] + "' where id='" + arg[2] + "'";
			}
			break;
		case "chang_wechatOrqq":
			if (current == 0) {
				ressql = "update user_data set wechat='" + arg[3] + "' where id='" + arg[2] + "'";
			} else if (current == 1) {
				ressql = "update user_data set qq='" + arg[3] + "' where id='" + arg[2] + "'";
			}
			break;
		case "tijiao_edit":
			if (current == 0) {
				ressql = "update user_data set retui_time=now()";
			}
			break;
		case "refresh_time":
			if (current == 0) {
				ressql = "update user_data set retui_time=now() where id='" + arg[2] + "'";
			}
			break;
		case "update_personal_data":
			if (current == 0) {
				ressql = "select count(0) from check_list where user_id='" + arg[2] + "' and result='审核中'";
			} else if (current == 1) {
				ressql = "select id from check_list where user_id='" + arg[2] + "' and result='审核中' order by id desc";
			} else if (current == 2) {
				ressql = "update check_list set photo='" + arg[3] + "',photo_album='" + arg[4] + "',nickname='" + arg[5]
						+ "',gender='" + arg[6] + "',age='" + arg[7] + "'," + "city='" + arg[8] + "',signature='"
						+ arg[9] + "'  where id='" + arg[10] + "'";
			} else if (current == 3) {
				ressql = "insert into check_list(user_id,photo,nickname,age,gender,city,signature,photo_album,result)"
						+ " values('" + arg[2] + "','" + arg[3] + "','" + arg[5] + "','" + arg[7] + "','" + arg[6]
						+ "','" + arg[8] + "','" + arg[9] + "','" + arg[4] + "','审核中')";
			}
			break;
		default:
			break;
		}
		return ressql;
	}

	@Override
	public String deleteSqlface(String[] arg) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchSqlface(int current, String[] arg) throws SQLException, IOException {
		switch (arg[1]) {
		case "sefujin":
			if (current == 0) {
				ressql = "select latitude,longitude from user_data where id='" + arg[2] + "'";
			}
//			else if(current==1){
//ressql="select u.id,u.gender,u.age,u.nickname,u.signature,u.user_photo,r.videochat_price,u.latitude,u.longitude,m.target_id,IFNULL(m.message,0) message,"
//						+"abs(u.longitude-'"+arg[9]+"')as a, abs(u.latitude-'"+arg[10]+"')as b"
//						+" from roles_table r inner join user_data u" 
//						+" on r.user_id=u.id right join message_detail_table as m on m.target_id=u.id"
//						+" where r.status='通过' and m.message='嗨！美女，我对你有兴趣，可以聊聊么？'"
//						+" and u.latitude between '"+arg[4]+"' and '"+arg[5]+"'"
//						+" and u.longitude between '"+arg[6]+"' and '"+arg[7]+"'"
//						+" UNION ALL "
//+"select u.id,u.gender,u.age,u.nickname,u.signature,u.user_photo,r.videochat_price,u.latitude,u.longitude,m.target_id,IFNULL(m.message,0) message,"
//						+" abs(u.longitude-'"+arg[9]+"')as a, abs(u.latitude-'"+arg[10]+"')as b"
//						+" from roles_table r inner join user_data u"
//						+" on r.user_id=u.id left join message_detail_table as m on m.target_id=u.id"
//						+" where r.status='通过' and u.id not in (select target_id from message_detail_table)"
//						+" and u.latitude between '"+arg[4]+"' and '"+arg[5]+"'"
//						+" and u.longitude between '"+arg[6]+"' and '"+arg[7]+"'"
//						+" ORDER BY a+b asc";
//
//			}
//			else if(current==2){
//				ressql="select u.id,u.gender,u.age,u.nickname,u.signature,u.user_photo,r.videochat_price,u.latitude,u.longitude,m.target_id,IFNULL(m.message,0) message,"
//						+"abs(u.longitude-'"+arg[9]+"')as a, abs(u.latitude-'"+arg[10]+"')as b"
//						+" from roles_table r inner join user_data u" 
//						+" on r.user_id=u.id right join message_detail_table as m on m.target_id=u.id"
//						+" where r.status='通过' and m.message='嗨！美女，我对你有兴趣，可以聊聊么？'"
//						+" and u.latitude between '"+arg[4]+"' and '"+arg[5]+"'"
//						+" and u.longitude between '"+arg[6]+"' and '"+arg[7]+"'"
//						+" UNION ALL "
//+"select u.id,u.gender,u.age,u.nickname,u.signature,u.user_photo,r.videochat_price,u.latitude,u.longitude,m.target_id,IFNULL(m.message,0) message,"
//						+" abs(u.longitude-'"+arg[9]+"')as a, abs(u.latitude-'"+arg[10]+"')as b"
//						+" from roles_table r inner join user_data u"
//						+" on r.user_id=u.id left join message_detail_table as m on m.target_id=u.id"
//						+" where r.status='通过' and u.id not in (select target_id from message_detail_table)"
//						+" and u.latitude between '"+arg[4]+"' and '"+arg[5]+"'"
//						+" and u.longitude between '"+arg[6]+"' and '"+arg[7]+"'"
//						+" ORDER BY a+b asc"
//						+ " limit "+arg[3]+","+JyHelpManager.item+"";
//			}
			else if (current == 1) {
				ressql = "select u.id,u.gender,u.age,u.nickname,u.signature,u.user_photo,r.videochat_price,u.latitude,u.longitude,"
						+ "abs(u.longitude-'" + arg[9] + "')as a, abs(u.latitude-'" + arg[10] + "')as b"
						+ " from roles_table r inner join user_data u" + " on r.user_id=u.id"
						+ " where u.latitude between '" + arg[4] + "' and '" + arg[5] + "'"
						+ " and u.longitude between '" + arg[7] + "' and '" + arg[6] + "'" + " and r.status='通过'"
						+ " and u.gender='女'" + " and u.id!='" + arg[2] + "'" + " ORDER BY a+b asc";
			} else if (current == 2) {
				ressql = "select u.id,u.gender,u.age,u.nickname,u.signature,u.user_photo,r.videochat_price,u.latitude,u.longitude,"
						+ "abs(u.longitude-'" + arg[9] + "')as a, abs(u.latitude-'" + arg[10] + "')as b"
						+ " from roles_table r inner join user_data u" + " on r.user_id=u.id"
						+ " where u.latitude between '" + arg[4] + "' and '" + arg[5] + "'"
						+ " and u.longitude between '" + arg[7] + "' and '" + arg[6] + "'" + " and r.status='通过'"
						+ " and u.gender='女'" + " and u.id!='" + arg[2] + "'" + " ORDER BY a+b asc" + " limit " + arg[3]
						+ "," + JyHelpManager.item + "";
			} else if (current == 3) {
				ressql = "select count(0) from say_hi_table where send_id='" + arg[2] + "' and target_id='" + arg[0]
						+ "'" + " and to_days(time) = to_days(now())";
			}
			break;
		case "seactive":
			if (current == 0) {
				ressql = "select count(*)" + " from roles_table r inner join user_data u" + " on r.user_id=u.id"
						+ " where r.status='通过'" + " order by r.online_time desc";
			} else if (current == 1) {
				ressql = "select u.id,u.nickname,u.user_photo,r.videochat_price,r.voicechat_price,r.wordchat_price"
						+ " from roles_table r inner join user_data u" + " on r.user_id=u.id" + " where r.status='通过'"
						+ " order by r.online_time desc" + " limit " + arg[3] + "," + JyHelpManager.item + "";
			} else if (current == 2) {
//				ressql="select count(*)"
//						+ " from roles_table r inner join user_data u"
//						+ " on r.user_id=u.id"
//						+ " where r.status='通过'"
//						+ " and u.is_recommand='1'";	
				ressql = "select count(*)" + " from roles_table r inner join user_data u" + " on r.user_id=u.id"
						+ " where r.status='通过'" + " and u.retui_time>DATE_SUB(now(),interval 4 hour)";

			} else if (current == 3) {
				ressql = "select u.id,u.nickname,u.user_photo,r.videochat_price,r.voicechat_price,r.wordchat_price"
						+ " from roles_table r inner join user_data u" + " on r.user_id=u.id" + " where r.status='通过'"
						+ " and u.retui_time>DATE_SUB(now(),interval 4 hour)" + " order by u.retui_time desc"
						+ " limit " + arg[3] + "," + JyHelpManager.item + "";
			} else if (current == 4) {
				ressql = "select count(*)" + " from roles_table r inner join user_data u" + " on r.user_id=u.id"
						+ " where r.status='通过'";
			} else if (current == 5) {
				ressql = "select u.id,u.nickname,u.user_photo,r.videochat_price,r.voicechat_price,r.wordchat_price"
						+ " from roles_table r inner join user_data u" + " on r.user_id=u.id" + " where r.status='通过'"
						+ " limit " + arg[3] + "," + JyHelpManager.item + "";
			}
			break;
		case "kuailiao":
			if (current == 0) {
//				ressql="select count(*) from user_data where gender='男' and ";
				ressql = "select count(*) from user_data where gender='男' and retui_time>DATE_SUB(now(),interval 4 hour)";
			} else if (current == 1) {
				ressql = "select id,nickname,user_photo,gender,age,TIMESTAMPDIFF(MINUTE, retui_time, now()) as time from user_data"
						+ " where gender='男' and retui_time>DATE_SUB(now(),interval 4 hour) ORDER BY retui_time desc"
						+ " limit " + arg[2] + "," + JyHelpManager.item + "";
				// select date_sub(now(),interval 4 hour) from dual;
			}
			break;
		case "zhoubang":
			if (current == 0) {
				ressql = "select u.id,u.nickname,u.user_photo,r.videochat_price"
						+ " from roles_table r inner join user_data u" + " on r.user_id=u.id" + " where r.status='通过'";
			} else if (current == 1) {

			}
			break;
		case "seCarousel":
			if (current == 0) {
				ressql = "SELECT carousel_photo from carousel_figure_table where type='" + arg[2] + "'";
			}
			break;
		case "sevipprice":
			if (current == 0) {
				ressql = "select * from vip_price_table";
			}
			break;
		case "myaidou":

			String temp_str = "";
			Date dt = new Date();
			// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			temp_str = sdf.format(dt);

			if (current == 0) {
				ressql = "select balance,total_revenue,total_withdrawals from user_data where id='" + arg[2] + "'";
			} else if (current == 1) {// 本月收入
				ressql = "select sum(money) from Income_details_table where user_id='" + arg[2] + "' and time like '%"
						+ temp_str + "%' ";
			} else if (current == 2) {// 本月支出
				ressql = "select sum(num) from  pay_table where user_id='" + arg[2] + "'  and time like '%" + temp_str
						+ "%'";
			} else if (current == 3) {// 本月提现
				ressql = "select sum(cash) from cash_table where user_id='" + arg[2] + "'  and time like '%" + temp_str
						+ "%'";
			}
			break;
		case "vipstatus":
			if (current == 0) {
				ressql = "select user_photo,nickname,is_member,DATEDIFF(member_end_time,now()) AS days  from user_data where id='"
						+ arg[2] + "'";
			}
			break;
		case "se_systemset":
			if (current == 0) {
				ressql = "select id,phone,wechat_name,wechat,qq,is_public_contact,is_message_reminder from user_data where id='"
						+ arg[2] + "'";
			}
			break;
		case "senvsheng":
			if (current == 0) {
				ressql = "select u.user_photo,u.personal_album,u.nickname,u.gender,u.address,u.signature,r.videochat_price from  user_data u inner JOIN roles_table  r on r.user_id=u.id where r.id='"
						+ arg[2] + "'";
			}
			break;
		case "search_user":// 01215
			if (current == 0) {
				// 查询所有信息通过id和昵称并且为男用户或者为女性会员
				ressql = "select * from user_data where (user_id like '%" + arg[2] + "%' or  nickname like '%" + arg[2]
						+ "%')  and (gender='男' or (gender='女'and is_goddess='1')) limit 0,20 ";
			} /*
				 * else if(current==1){ ressql =
				 * "select * from user_data where user_id =id and gender= '女' and is_member='1' "
				 * ; }else if(current==2){ ressql =
				 * "select id,gender from user_data where user_id like '%"+arg[2]
				 * +"%' or nickname like '%"+arg[2]+"%' limit 0,20"; }else if(current==3){
				 * ressql = "select * from user_data where user_id like '%"+arg[2]
				 * +"%' or nickname like '%"+arg[2]+"%'   limit 0,20"; }
				 */
			break;
		case "se_renzheng":
			if (current == 0) {
				ressql = "select count(0) from check_list where user_id='" + arg[2] + "'";
			} else if (current == 1) {
				ressql = "select result,photo,nickname,age,gender,city,photo_album,signature from check_list where user_id='"
						+ arg[2] + "' order by id desc limit 1";
			} else if (current == 2) {
				ressql = " select user_photo as photo,nickname,age,gender,address as city,personal_album as photo_album,signature from user_data where id='"
						+ arg[2] + "'";
			}
			break;
		case "is_renzheng":
			if (current == 0) {
				ressql = " SELECT COUNT(*) FROM authentication_table WHERE user_id='" + arg[2]
						+ "' AND (result='审核中' or result='已通过')";
			}
			break;
		case "se_black_list":
			if (current == 0) {
				ressql = "select u.id,u.nickname,u.user_photo,u.gender,u.age,u.address,u.is_member,u.is_goddess"
						+ " from user_data as u inner join blacklist_table as b"
						+ " on u.id=b.black_id where b.user_id='" + arg[2] + "' and status='1'";
			}
			break;
		}
		return ressql;
		// seCarousel search type(聊场）
		// http://120.27.98.128:9810/uiface/memberC01182?mode0=A-user-search&mode1=seCarousel&mode2=聊场

	}

}
