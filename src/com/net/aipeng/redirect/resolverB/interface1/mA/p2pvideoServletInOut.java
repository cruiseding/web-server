package com.net.aipeng.redirect.resolverB.interface1.mA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface4.JsonUtil;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;

@WebServlet("/uiface/p2pvideo")
public class p2pvideoServletInOut extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3088970456162918642L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String[] arg = JsonUtil.jsonReceive(request);

		try {
			new p2pvideoInout(request, response, arg).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new JyLogDetect().send(DataType.exceptionType, "01107", "IAPManagerServlet-Exception: ", e);
		}
	}

}
