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
			 * login.jsp form���� �Ѿ�� ID,password
			 */
			String id = request.getParameter("ID");
			String password = request.getParameter("Password");
			/*
			 * EncMD5.getInstance().getEncMD5(password); password =
			 * EncMD5.getInstance().getEncMD5(password);
			 */

			// id�� �����ϴ� id���� �˻�
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
			 * id�� �������� �ʴ� ��� result attribute�� idErr�� setting �� jsp �������� �̵�
			 */
			if (existID == null) {
				request.setAttribute("success", "false");
				response.sendRedirect("/ktrip/loginFail.jsp");
			}

			/*
			 * id�� �����ϴ� ��� password�� id�� ��ġ�ϴ� �˻�
			 */
			String selectSql = "select pwd from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(selectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String checkpwd = null;

			if (rs.next()) {
				checkpwd = rs.getString("pwd");
			}

			// password null exception ó��
			if (checkpwd == null) {
				System.out.println("��й�ȣ �Է� ���� ���");
				request.setAttribute("success", "false");
				response.sendRedirect("/ktrip/loginFail.jsp");
			}
			
			System.out.println("������ �Ѿ�� id "+id);
			System.out.println("������ �Ѿ�� pwd "+password);
			System.out.println("db���� �Ѿ�� id "+existID);
			System.out.println("db���� �Ѿ�� pwd "+checkpwd);

			/*
			 *   �н����尡 ��ġ�ϸ� login
			 * result attribute�� success�� setting �ϰ� jsp �������� �̵�
			 */
			if (checkpwd.equals(password)) {
				System.out.println("�α��� ����"); 
				rd = request.getRequestDispatcher("/home.jsp");
				request.setAttribute("success", "true");
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				response.sendRedirect("/ktrip/index.html");
				return;
			} else {
				// �н����尡 ��ġ���� �ʴ°��
				// result attribute�� passwdErr�� setting �� jsp �������� �̵�
				System.out.println("id�� pwd����ġ");
				request.setAttribute("success", "false");
				response.sendRedirect("/ktrip/loginFail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("success", "false");
		}

	}
}