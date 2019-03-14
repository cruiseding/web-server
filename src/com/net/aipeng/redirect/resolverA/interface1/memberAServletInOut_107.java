package com.net.aipeng.redirect.resolverA.interface1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyGlobalConstant;
import com.net.aipeng.classroot.interface4.JyInOutUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.SqlUtil;

@WebServlet("/uiface/member107A")
public class memberAServletInOut_107 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String[] arg = JsonUtil.jsonReceive(request);

		SqlUtil sqlUtil = new SqlUtil(JyGlobalConstant.getDbBaseName());
		JyInOutUtil inOutUtil = new JyInOutUtil(arg, request, response);
		JyLogDetect log = new JyLogDetect(request);

		try {
			switch (arg[1]) {
				case "check_update": {
					String sql = "select * from updown"; // sqlmface.searchSqlface(0, arg);
					log.send(DataType.specialType, "01160", "check_update()-sql: ", sql);
					ArrayList<Map<String, Object>> list = sqlUtil.get_list(sql);
					log.send(DataType.specialType, "01160", "check_update()-list: ", list);
					inOutUtil.return_ajax(JsonUtil.listToJson(list));
					break;
				}
			}
		} catch (Exception e) {
			log.send(DataType.exceptionType, "01107", "memberAServletInOut_107-Exception: ", e);
		}

	}

}
