package RareBlog;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
					getTitles(request,response,"essay");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "getNov":
				try {
					getTitles(request,response,"novel");
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
			
			case "login":
				try {
					login(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "checkLogin":
				checkLogin(request,response);
				break;
			
			case "getContent":
				try {
					getContent(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "comment":
				try {
					comment(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "getComments":
				try {
					getComments(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			
			
			
			default:
				try {
					recordVisitor(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("main.jsp");
				
			}
		}
		
	}
	
	private void getTitles(HttpServletRequest request, HttpServletResponse response
			,String category) throws SQLException, IOException{
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog"
				+ "?useUnicode=true&characterEncoding=UTF-8","root","");
		
		Statement stmt=conn.createStatement();
		ResultSet rs;
		if(category.equals("essay")){
			rs= stmt.executeQuery("select title, time,id from articles where category='essay'");
		}else{
			rs=stmt.executeQuery("select title, time,id from articles where category='novel'");
		}
		PrintWriter pw=response.getWriter();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
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
			int id=rs.getInt(3);
			pw.print("<div class='entry'><hr><a href='display.html?category="+category+"&id="
			+id+"'><span class='title'>"+title+"</span><span class='date'>"+dateStr
			+"</span></a></div>");
		}
		
	}
	private void writer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		if(request.getSession().getAttribute("loggedIn")==null){
			response.sendRedirect("writerLogin.jsp");
			return;
		}
		if(!(boolean)request.getSession().getAttribute("loggedIn")){
			response.sendRedirect("writerLogin.jsp");
			return;
		}
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
		String category=request.getParameter("category");
		stmt.executeUpdate("insert into articles (title,content,time,category)"
				+ " value('"+title+"','"+content+"','"+timeStr+"','"+category+"')");
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog","root","");
		Statement stmt=conn.createStatement();
		String enteredPass=request.getParameter("writerPass");
		ResultSet rs=stmt.executeQuery("select pass from password where id=1" );
		rs.next();
		String realPass=rs.getString(1);
		System.out.println(realPass);
		System.out.println(enteredPass);
		if(enteredPass.equals(realPass)){
			System.out.println("loggedin");
			request.getSession().setAttribute("loggedIn", true);
			response.sendRedirect("writer.jsp");
		}else{
			System.out.println("wrongpass");
			response.sendRedirect("writerLogin.jsp");
		}
	}
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(request.getSession().getAttribute("loggedIn")==null){
			response.getWriter().print("stranger");
			return;
		}
		if((boolean)request.getSession().getAttribute("loggedIn")){
			response.getWriter().print("loggedIn");
		}else{
			response.getWriter().print("stranger");
		}
	}
	
	private void getContent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog","root","");
		Statement stmt=conn.createStatement();
		String category=request.getParameter("category");
		long id=Long.valueOf(request.getParameter("id"));
		ResultSet rs;
		
		rs=stmt.executeQuery("select title, content, time from articles where category='"
		+category+"'and id="+id);
		
		if(rs.next()){
			String title=rs.getString(1);
			String content=rs.getString(2);
			System.out.println(content);
			
			response.getWriter().print("<div id='title'>"+title
					+"</div><div id='content'>"+content+"</div>");
		}
		
	}
	private void recordVisitor(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String remoteHost=request.getRemoteHost();
		String remoteAddr=request.getRemoteAddr();
		String sessionId=request.getSession().getId();
		String serverName=request.getServerName();
		Timestamp time=new Timestamp(new Date().getTime());
		String datetime=time.toString();
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog","root","");
		Statement stmt= conn.createStatement();
		stmt.executeUpdate("insert into visitors(sessionId,remoteAddr, remoteHost,datetime ,serverName) values('"
				+sessionId+"','"
				+remoteAddr+"','"
				+remoteHost+"','"
				+datetime+"','"
				+serverName+
				"')"
				);
		
	}
	private void comment(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		String text=request.getParameter("commentText");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long serverGMTMilli=System.currentTimeMillis();
		Timestamp time=new Timestamp(System.currentTimeMillis());
		
		String datetime=time.toString();
		String sessionId=request.getSession().getId();
		String category=request.getParameter("category");
		long id=Long.valueOf(request.getParameter("id"));
		int tzo=Integer.valueOf(request.getParameter("tzo"));
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog","root","");
		Statement stmt=conn.createStatement();
		stmt.executeUpdate("insert into comment (sessionId,serverTime, tzo, text, articleId, articleCategory"
				+ ",serverGMTMilli) values('"
				+sessionId+"','"
				+datetime+"',"
				+tzo+",'"
				+text+"',"
				+id+",'"
				+category+"',"
				+serverGMTMilli+ ")");
		
		
	}
	private void getComments(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String category=request.getParameter("category");
		long id=Long.valueOf(request.getParameter("id"));
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rareblog","root","");
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select serverGMTMilli, tzo, text from comment where articleCategory='"
				+category
				+"' and articleId="+id
				+" order by serverGMTMilli");
		
		while(rs.next()){
			long serverGMTMilli=rs.getLong(1);
			long tzo=rs.getInt(2);
			String text=rs.getString(3);
			
			Date date=new Date(serverGMTMilli);
			
			DateFormat dfmt=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			dfmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			String GMTTimeStr=dfmt.format(serverGMTMilli);
			String localTimeStr=dfmt.format(serverGMTMilli-tzo*60000);
			response.getWriter().print("<br><span class='time'>当地时间："+localTimeStr
					+" GMT: "+GMTTimeStr+"</span><br><br><span class='commentText'>"
					+text+"</span><hr>");
			
			
		}
	}
}
