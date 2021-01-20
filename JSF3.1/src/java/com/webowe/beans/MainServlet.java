package com.webowe.beans;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)    throws ServletException, IOException {
      PrintWriter out = response.getWriter();

      String parametr1 = request.getParameter("parametr1");
      String parametr2 = request.getParameter("parametr2");
      String parametr3 = request.getParameter("parametr3");

      out.println("Wczytanie 3 parametrow z zadania :");
      out.println(parametr1);
      out.println(parametr2);
      out.println(parametr3);
    }
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)	throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("Wczytanie 3 parametrow z zadania :");
		out.println(request.getParameter("parametr1"));
		out.println(request.getParameter("parametr2"));
		out.println(request.getParameter("parametr3"));
	}

}
