package com.HSsim.util;

import java.awt.print.Pageable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QQ
 */
@WebServlet("/QQ")
public class QQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QQ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String targetUrl = "https://kr.battle.net/oauth/token?grant_type=authorization_code&client_id=0533b43ca53c46b7ba17482a1c72ac8b&client_secret=jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ&code="+code+"&redirect_uri=http://localhost:8080/BDGG/QQ";
		
		targetUrl = "http://localhost:8080/BDGG/Server";
		
		targetUrl = "https://kr.battle.net/oauth/authorize?response_type=code&client_id=0533b43ca53c46b7ba17482a1c72ac8b&redirect_uri=http://localhost:8080/BDGG/QQ";
		
		System.out.println(targetUrl);
		try {
			HttpConnection.sendGet(targetUrl, "GET");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("doPost");
	}

}
