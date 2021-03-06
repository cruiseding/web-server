package com.net.aipeng.redirect.resolverA.interface1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;

@WebServlet("/uiface/gift_dealings") // 礼物往来
public class aipengServletInout_gift_01205 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] arg = null;
		InOutFace inOutFace;
		JyLogDetect log = new JyLogDetect(request);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		arg = JsonUtil.jsonReceive(request);
		log.send(DataType.specialType, "01165_____", "aipengServletInout_gift_01205_arg:", arg);

		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

//		arg = JsonUtil.jsonReceive(request);
		/*
		 * if(arg[0].contains("user")){ if(map.containsKey("userid")) {
		 * arg[2]=map.get("userid"); } }
		 */

		try {
			switch (arg[0]) {

			case "A-user-add":
				inOutFace = new aipengInoutUser_gift_01205(arg, request, response);
				inOutFace.addface();
				break;
			case "A-user-mod":
				inOutFace = new aipengInoutUser_gift_01205(arg, request, response);
				inOutFace.modface();
				break;
			case "A-user-search":
				inOutFace = new aipengInoutUser_gift_01205(arg, request, response);
				inOutFace.searchface();
				break;
			case "A-user-delete":
				inOutFace = new aipengInoutUser_gift_01205(arg, request, response);
				inOutFace.deleteface();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.send("01165", "Exception", e);
		}
	}
}
