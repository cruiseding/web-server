package com.net.aipeng.redirect.resolverD.interface1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.net.aipeng.classroot.interface1.mA.InOutFace;
import com.net.aipeng.classroot.interface4.JsonUtil;
//import com.net.feimiaoquan.classroot.interface1.mA.fmqInOutFace;
//import com.net.feimiaoquan.classroot.interface4.JsonUtil;
//import com.net.feimiaoquan.classroot.interface4.JyLogDetect;
//import com.net.feimiaoquan.classroot.interface4.JyLogDetect.DataType;
import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;

@WebServlet("/uiface/pay")
public class PayInOut extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PayInOut() {
		super();
	}

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
		log.send(DataType.specialType, "01162", "头信息:   ", arg);

		try {
			switch (arg[0]) {
			case "A-user-add":
				inOutFace = new PayInOutUser(arg, request, response);
				inOutFace.addface();
				break;
			case "A-user-delete":
				inOutFace = new PayInOutUser(arg, request, response);
				inOutFace.deleteface();
				break;
			case "A-user-mod":
				inOutFace = new PayInOutUser(arg, request, response);
				inOutFace.modface();
				break;
			case "A-user-search":
				inOutFace = new PayInOutUser(arg, request, response);
				inOutFace.searchface();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.send(DataType.exceptionType, "01160", "Exception: ", e);
		}
	}

}
