package com.net.aipeng.redirect.resolverB.interface1.mA;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyHelpManager;
import com.net.aipeng.classroot.interface4.JyInOutUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.SqlUtil;
import com.net.aipeng.redirect.resolverB.interface4.ImageSyncScanRequestSample;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class p2pvideoInout {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String[] arg;

	private JyLogDetect log;
	private SqlUtil sqlUtil;
	private JyInOutUtil inOutUtil;
	private JyHelpManager hm = new JyHelpManager();

	public p2pvideoInout(HttpServletRequest request, HttpServletResponse response, String[] arg) throws IOException {
		this.arg = arg;
		this.response = response;
		this.request = request;

		log = new JyLogDetect(request);
		log.send(DataType.specialType, "01066", "dbName", "aipeng");
		sqlUtil = new SqlUtil("aipeng");

		inOutUtil = new JyInOutUtil(arg, request, response);
	}

	public void run() throws ServletException, SQLException, IOException {

		log.send(DataType.basicType, "01107", "---------------------------------------------", "");
		log.send(DataType.basicType, "01107", "p2pvideoInout: ", arg);
		switch (arg[1]) {
		case "upscreenshot":
			upscreenshot(request, response, arg);
			break;
		// --------------------------------------------
		// 鉴黄功能后台操作接口
		case "jianhuang_list":
			jianhuang_list(arg);
			break;
		}
	}

	/**
	 * 查看鉴黄上传图片列表 arg[0]: A-boss-search arg[1]: jianhuang_list arg[2]: out
	 * tojsp/tojson arg[3]: pagenum ---- 页码 ---------------------------------
	 * arg[4]: pagefirst
	 * 
	 * @param arg
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void jianhuang_list(String[] arg) throws SQLException, IOException, ServletException {

		String sql = "select count(*) from jianhuang_list"; // sqlmface.searchSqlface(0, arg);
		log.send(DataType.basicType, "01162", "jianhuang_list()-sql: ", sql);
		int total = sqlUtil.get_int(sql);
		log.send(DataType.basicType, "01162", "jianhuang_list()-total: ", total);

		int[] pages = JyHelpManager.pages(arg[3], total);
		arg = Arrays.copyOf(arg, arg.length + 1);
		arg[arg.length - 1] = pages[2] + "";

		sql = "select * from(" + "select * from jianhuang_list order by time desc limit " + arg[4] + ","
				+ JyHelpManager.item + ")as a left join " + "(select id,nickname from user_data)as b on a.user_id=b.id"; // sqlmface.searchSqlface(1,
																															// arg);
		log.send(DataType.basicType, "01162", "jianhuang_list()-sql2: ", sql);
		ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
		log.send(DataType.basicType, "01162", "jianhuang_list()-list: ", list);

		if ("tojsp".equals(arg[2])) {
			inOutUtil.return_listpage(list, pages, "/uiface1/boss/jianhuang_list.jsp");
		} else if ("tojson".equals(arg[2])) {
			inOutUtil.return_ajax(JsonUtil.listPageToJson(list, pages));
		}
	}

	/**
	 * 上传一对一视频截图 arg[0]: A-user-add arg[1]: upscreenshot arg[2]: userid arg[3]:
	 * imgname
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SQLException
	 */
	private void upscreenshot(HttpServletRequest request, HttpServletResponse response, String[] arg)
			throws IOException, SQLException {

		log.send(DataType.noType, "01107", "upscreenshot: ", "request in");

		String path = this.getClass().getResource("/").getFile();
		File file1 = new File(path.substring(1, path.length() - 1));
		File file2 = new File(new File(file1.getParent()).getParent());
		File homeDir = new File(file2, "screenshot");
		if (!homeDir.exists()) {
			homeDir.mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("UTF-8");
		List<FileItem> items = new ArrayList<FileItem>();

		try {
			items = fileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<FileItem> it = items.iterator();

		File imgFile = null;
		while (it.hasNext()) {
			FileItem fileItem = it.next();
			if (fileItem.isFormField()) {
				log.send(DataType.noType, "01107", "upscreenshot: ", "isFormField(): " + fileItem.getName());
			} else {
				log.send(DataType.noType, "01107", "upscreenshot: ",
						"isFormField()else: " + fileItem.getName() + ":" + fileItem.getSize());
				if (fileItem.getName() != null && fileItem.getSize() != 0) {
					File fullFile = new File(fileItem.getName());
					File newFile = new File(homeDir, fullFile.getName());
					imgFile = newFile;
					try {
						fileItem.write(newFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// System.out.println("no file choosen or empty file");
				}
			}
		}

		try {
			log.send(DataType.noType, "01107", "upscreenshot: ",
					"pic-url: " + "http://" + JyGlobalConstant.getIp() + ":8090/screenshot/" + imgFile.getName());
			String retJson = ImageSyncScanRequestSample
					.getResult("http://" + JyGlobalConstant.getIp() + ":8090/screenshot/" + imgFile.getName());

			JSONObject jsonObj = JSONObject.fromObject(retJson);
			int code = jsonObj.getInt("code");
			if (code == 200) { // 正确返回鉴黄结果后才插入数据库
				arg[3] = "http://" + JyGlobalConstant.getIp() + ":8090/screenshot/" + imgFile.getName(); // 补全图片地址
				arg = Arrays.copyOf(arg, arg.length + 6);
				// 填充鉴黄暴恐结果
				int newLength = arg.length;
				JSONObject dObj = jsonObj.getJSONArray("data").getJSONObject(0);
				JSONArray resultArray = dObj.getJSONArray("results");
				for (int i = 0; i < resultArray.size(); i++) {
					JSONObject obj = resultArray.getJSONObject(i);
					String scene = obj.getString("scene");

					String rate, suggestion, label;
					if ("porn".equals(scene)) {
						rate = obj.getString("rate");
						suggestion = obj.getString("suggestion");
						label = obj.getString("label");

						arg[newLength - 6] = rate;
						arg[newLength - 5] = suggestion;
						arg[newLength - 4] = label;
					} else if ("terrorism".equals(scene)) {
						rate = obj.getString("rate");
						suggestion = obj.getString("suggestion");
						label = obj.getString("label");

						arg[newLength - 3] = rate;
						arg[newLength - 2] = suggestion;
						arg[newLength - 1] = label;
					}
				}
				String sql = "insert into jianhuang_list (user_id,img,porn_rate,porn_suggestion,porn_label,terrorism_rate,terrorism_suggestion,terrorism_label) values "
						+ "('" + arg[2] + "','" + arg[3] + "','" + arg[4] + "','" + arg[5] + "','" + arg[6] + "','"
						+ arg[7] + "','" + arg[8] + "','" + arg[9] + "')";
				log.send(DataType.noType, "01107", "upscreenshot()-sql: ", sql);
				int result = sqlUtil.sql_exec(sql);
				log.send(DataType.noType, "01107", "upscreenshot()-result: ", result);
			}

			log.send(DataType.noType, "01107", "upscreenshot-jianhuang: ", retJson);

			PrintWriter out = response.getWriter();
			out.write(retJson);
			// inOutUtil.return_ajax(retJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
