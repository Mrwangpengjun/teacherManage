package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import dao.UserDao;
import entity.Course;
import entity.User;

public class UserServlet extends HttpServlet{
	
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
		String action = req.getParameter("action");
		if("getList".equals(action)) {
			UserDao userDao = new UserDao();
			List<User> users = userDao.queryAllByPage("", 0);
			req.setAttribute("users", users);
			req.getRequestDispatcher("admin_users.jsp").forward(req,resp);
		} else if("doAdd".equals(action)) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			int type = Integer.parseInt(req.getParameter("type"));
			UserDao dao = new UserDao();
			User entity = new User();
			entity.setPassword(password);
			entity.setUsername(username);
			entity.setType(type);
			List<User> users = dao.queryAllByPage(username, type);
			if(users.size()>0) {
				PrintWriter out= resp.getWriter();
				//修改失败
				out.print("<script type='' language='javascript'>alert('已存在相同的用户名');location.href='admin_user_add.jsp';</script>");
				out.flush();
				out.close();
			} else {
				boolean flag = dao.save(entity);
				if(flag) {
					PrintWriter out= resp.getWriter();
					out.print("<script type='' language='javascript'>alert('添加成功');location.href='UserServlet?action=getList'; </script>");
					out.flush();
					out.close();
				}
			}
		} else if("toUpdate".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			UserDao dao = new UserDao();
			User user = dao.getById(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher("admin_user_update.jsp").forward(req,resp);
		} else if("doUpdate".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			int type = Integer.parseInt(req.getParameter("type"));
			UserDao dao = new UserDao();
			List<User> users = dao.queryAllByPage(username, type);
			if(users.size()>0 && users.get(0).getId()!=id) {
				PrintWriter out= resp.getWriter();
				//修改失败
				out.print("<script type='' language='javascript'>alert('已存在相同的用户名');history.go(-1);</script>");
				out.flush();
				out.close();
			} else {
				User entity = new User();
				entity.setPassword(password);
				entity.setUsername(username);
				entity.setType(type);
				entity.setId(id);
				boolean flag = dao.update(entity);
				if(flag) {
					PrintWriter out= resp.getWriter();
					out.print("<script type='' language='javascript'>alert('修改成功');location.href='UserServlet?action=getList'; </script>");
					out.flush();
					out.close();
				}
			}
		} else if("delete".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			CourseDao courseDao = new CourseDao();
			List<Course> courses = courseDao.queryAllByPage(id);
			if(courses.size()>0) {
				PrintWriter out= resp.getWriter();
				out.print("<script type='' language='javascript'>alert('该用户关联了课程信息，无法删除');location.href='UserServlet?action=getList'; </script>");
				out.flush();
				out.close();
			} else {
				UserDao dao = new UserDao();
				boolean flag = dao.delete(id);
				if(flag) {
					PrintWriter out= resp.getWriter();
					out.print("<script type='' language='javascript'>alert('删除成功');location.href='UserServlet?action=getList'; </script>");
					out.flush();
					out.close();
				}
			}
		} else if("userUpdate".equals(action)) {
			int userid = 0;
			if(req.getSession().getAttribute("userid")!=null) {
				userid = Integer.parseInt(req.getSession().getAttribute("userid").toString());
				String password = req.getParameter("password");
				UserDao dao = new UserDao();
				User user = dao.getById(userid);
				user.setPassword(password);
				boolean flag = dao.update(user);
				if(flag) {
					PrintWriter out= resp.getWriter();
					out.print("<script type='' language='javascript'>alert('修改成功');location.href='user_main.jsp'; </script>");
					out.flush();
					out.close();
				}
			} else {
				PrintWriter out= resp.getWriter();
				out.print("<script type='' language='javascript'>alert('请重新登录');location.href='login.jsp'; </script>");
				out.flush();
				out.close();
			}
		}
	}
}
