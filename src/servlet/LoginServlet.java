package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action1");
		if("login".equals(action)) {
			boolean flag = false;
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			int type = Integer.parseInt(req.getParameter("type"));
			UserDao dao = new UserDao();
			User user = dao.login(username, password,type);
			
			if(user!=null) {
				req.getSession().setAttribute("type", user.getType());
				req.getSession().setAttribute("userid", user.getId());
				if(type==1) {
					resp.sendRedirect("admin_main.jsp");
				} else {
					resp.sendRedirect("user_main.jsp");
				}
			} else {
				PrintWriter out= resp.getWriter();
				//修改失败
				out.print("<script type='' language='javascript'>alert('用户名或密码错误。');location.href='login.jsp'; </script>");
				out.flush();
				out.close();
			}
		}
	}
}
