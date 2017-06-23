package RareBlog;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String mAction=request.getParameter("mAction");
		if(mAction==null){
			response.sendRedirect("main.jsp");
		}else{
			switch(mAction){
			case "getEss":
				try {
					getEssTitles(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case "writer":
				try {
					writer(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			default:
				response.sendRedirect("main.jsp");
				
			}
		}
		
	}
	
	private void getEssTitles(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog"
				+ "?useUnicode=true&characterEncoding=UTF-8","root","");
		
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("select title, time from essay");
		PrintWriter pw=response.getWriter();
		DateFormat df=new SimpleDateFormat();
		String title;
		while(rs.next()){
			title=rs.getString(1);
			Date date=rs.getDate(2);
			String dateStr;
			if(date==null){
				dateStr="";
			}else{
				dateStr=df.format(date);
			}
			pw.print("<div class='entry'><hr><span class='title'>"+title+"</span><span class='date'>"+dateStr
			+"</span></div><br>");
		}
		
	}
	private void writer(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		response.setCharacterEncoding("UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog"
				+ "?useUnicode=true&characterEncoding=UTF-8","root","");
		Statement stmt=conn.createStatement();
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String timeStr=request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		System.out.println(timeStr);
		System.out.println(title);
		System.out.println(content);
		stmt.executeUpdate("insert into essay (title,content,time)"
				+ " value('"+title+"','"+content+"','"+timeStr+"')");
	}

}
