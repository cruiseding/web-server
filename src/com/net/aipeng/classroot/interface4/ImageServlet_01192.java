package com.net.aipeng.classroot.interface4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;

/**
 * 文件上传的Serlvet�?
 * 
 * Servlet implementation class FileImageUploadServlet
 * 
 * 此处的文件上传比较简单没有处理各种验证，文件处理的错误等�? 如果�?要处理，请修改源代码即可�?
 * 
 * @Title:
 * @Description: 实现TODO
 * @Copyright:Copyright (c) 2011
 * @Company:易程科技股份有限公司
 * @Date:2012-7-22
 * @author longgangbai
 * @version 1.0
 */

@WebServlet("/uiface/ImageServlet_01192")
public class ImageServlet_01192 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ServletFileUpload upload;
	private final long MAXSize = 4194304 * 2L;// 4*2MB
	private String filedir = null;
	String sqlUpdate1;
	// private String dizhi =
	// "http://120.27.98.128:9111/img/imgheadpic/";注意：可以看动态表是不是全名，是就要不加这个；不是全名，就要加上全地址
	private String dizhi = "http://" + JyGlobalConstant.getIp() + ":8090/img/imgheadpic/";
	// http://120.27.98.128:9112/img/imgheadpic/07.jpg
	// http://139.129.38.194:9000/logs/log1.htm

	private String istongguo = "未审核";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet_01192() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JyLogDetect log = new JyLogDetect(request);
		log.send("01150", "arg:", 111);
		PrintWriter out = response.getWriter();
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory
															// for disk-based
															// file items
		this.upload = new ServletFileUpload(factory);// Create a new file upload
														// handler
		this.upload.setSizeMax(this.MAXSize);// Set overall request size
												// constraint 4194304

		int j = -1;
		String fullPath = "img/imgheadpic";
		String tmppath = request.getSession().getServletContext().getRealPath("/");
		filedir = tmppath + fullPath;
		File fullDir = new File(tmppath + fullPath);
		if (!fullDir.exists()) {
			fullDir.mkdirs();
		}
		String fileName = "";
		try {
			String user_id = "";
			List<FileItem> items = this.upload.parseRequest(request);
			if (items != null && !items.isEmpty()) {
				for (FileItem fileItem : items) {

					if (fileItem.isFormField()) {
						user_id = fileItem.getString("UTF-8");
					} else {

						fileName = fileItem.getName();

						String type2 = fileName.substring(fileName.lastIndexOf("."));
						Random rnd = new Random();
						int r = rnd.nextInt(100);
						Date date2 = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
						String strDate2 = formatter.format(date2);
						fileName = strDate2 + r + type2;

						String filepath = filedir + File.separator + fileName;
						File file = new File(filepath);
						InputStream inputSteam = fileItem.getInputStream();
						BufferedInputStream fis = new BufferedInputStream(inputSteam);
						FileOutputStream fos = new FileOutputStream(file);
						int f;
						while ((f = fis.read()) != -1) {
							fos.write(f);
						}
						fos.flush();
						fos.close();
						fis.close();
						inputSteam.close();
					}
				}
			}
			log.send("01165", "arg:", fileName);
			/*
			 * JSONObject jsonObj0 = new JSONObject(); jsonObj0.put("imgurl", fileName); //
			 * out.write("[\"imgurl\","+fileName+"]"); out.write(jsonObj0.toString());
			 */
			SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
			// 我的相册页面，如果没有图片，直接显示这一段，如果有图片，每一段加上逗号
//			String ressql = "select * from user_data where id ="+user_id ;
//			log.send("01178", "arg:", ressql);
//			ArrayList<Map<String, Object>> list = sqlUtil.get_list(ressql);
//			log.send("01178", "list:", list);
			String img = "";
			String tupiandizhi = fileName;

//			if(list.isEmpty() || list == null){
//				j = -1;
//			}else{
//				j = 0;
//			}

			if (j == -1) { // 新图片
//				sqlUpdate1 = "insert into say_art_table (user_id, photo,time) values "
//						+ " ('"+user_id+"','"+tupiandizhi+"',now())";
//				log.send("01178", "arg:", sqlUpdate1);
//				sqlUtil.sql_exec(sqlUpdate1);
				log.send("01178", "arg:", sqlUpdate1);
				img = dizhi + fileName;
				log.send("01178", "img:", img);
			} else {
				// 否则在将我的相册后加上逗号
//				sqlUpdate1 = "update say_art_table set photo = photo+',"+tupiandizhi+"' "
//						+ " where id = '"+user_id+"' ";
//				
//				log.send("01165---", "arg_sqlUpdate1:", sqlUpdate1);
//				int i = sqlUtil.sql_exec(sqlUpdate1);
//				log.send("01165---", "arg_i:", i);
				img = dizhi + fileName;
				log.send("01165---", "img:", img);
			}

			JSONObject jsonObj0 = new JSONObject();
			jsonObj0.put("imgurl", img);
			// out.write("[\"imgurl\","+fileName+"]");
			out.write(jsonObj0.toString());
			log.send("01165---", "jsonObj0:", jsonObj0);

		} catch (FileUploadException e) {
			e.printStackTrace();
			JSONObject jsonObj0 = new JSONObject();
			jsonObj0.put("imgurl", "0");
			out.write(jsonObj0.toString());
		}
	}

}