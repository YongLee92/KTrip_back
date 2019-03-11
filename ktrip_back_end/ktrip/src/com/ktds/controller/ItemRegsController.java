package com.ktds.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

@WebServlet("/itemRegs")
public class ItemRegsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemRegsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String DB_URL = "jdbc:mysql://ktrip-mysql.crl10pgh3bye.ap-northeast-2.rds.amazonaws.com:3306/ktripdb";
		String DB_USER = "ktrip";
		String DB_PASSWORD = "123456789";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String jdbc_driver = "com.mysql.jdbc.Driver";
		
		RequestDispatcher rq = request.getRequestDispatcher("/guide_item_registration.jsp");
		
		//db»ðÀÔ³¡
		
	
		
	}

}
