package com.ktds.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginCheck")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String DB_URL = "jdbc:mysql://ktrip-mysql.crl10pgh3bye.ap-northeast-2.rds.amazonaws.com:3306/ktripdb";
		String DB_USER = "ktrip";
		String DB_PASSWORD = "123456789";

		Connection conn = null;
		PreparedStatement pstmt = null;

		String jdbc_driver = "com.mysql.jdbc.Driver";

		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");

		try {
			Class.forName(jdbc_driver);

			conn = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			/*
			 * login.jsp form에서 넘어온 ID,password
			 */
			String id = request.getParameter("ID");
			String password = request.getParameter("Password");
			/*
			 * EncMD5.getInstance().getEncMD5(password); password =
			 * EncMD5.getInstance().getEncMD5(password);
			 */

			// id가 존재하는 id인지 검사
			request.setAttribute("ID", id);
			String idSelectSql = "select id from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(idSelectSql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			String existID = null;

			if (rs.next()) {
				existID = rs.getString(1);
			}

			/*
			 * id가 존재하지 않는 경우 result attribute에 idErr로 setting 후 jsp 페이지로 이동
			 */
			if (existID == null) {
				request.setAttribute("success", "false");
				response.sendRedirect("/ktrip/loginFail.jsp");
			}

			/*
			 * id가 존재하는 경우 password와 id가 일치하는 검사
			 */
			String selectSql = "select pwd from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(selectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String checkpwd = null;

			if (rs.next()) {
				checkpwd = rs.getString("pwd");
			}

			// password null exception 처리
			if (checkpwd == null) {
				System.out.println("비밀번호 입력 안한 경우");
				request.setAttribute("success", "false");
				response.sendRedirect("/ktrip/loginFail.jsp");
			}
			
			System.out.println("폼에서 넘어온 id "+id);
			System.out.println("폼에서 넘어온 pwd "+password);
			System.out.println("db에서 넘어온 id "+existID);
			System.out.println("db에서 넘어온 pwd "+checkpwd);

			/*
			 *   패스워드가 일치하면 login
			 * result attribute를 success로 setting 하고 jsp 페이지로 이동
			 */
			if (checkpwd.equals(password)) {
				System.out.println("로그인 성공"); 
				rd = request.getRequestDispatcher("/home.jsp");
				request.setAttribute("success", "true");
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				response.sendRedirect("/ktrip/index.html");
				return;
			} else {
				// 패스워드가 일치하지 않는경우
				// result attribute를 passwdErr로 setting 후 jsp 페이지로 이동
				System.out.println("id와 pwd불일치");
				request.setAttribute("success", "false");
				response.sendRedirect("/ktrip/loginFail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("success", "false");
		}

	}
}