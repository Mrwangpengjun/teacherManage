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

public class CourseServlet extends HttpServlet{
	
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
			CourseDao courseDao = new CourseDao();
			List<Course> courses = courseDao.queryAllByPage(0);
			req.setAttribute("courses", courses);
			req.getRequestDispatcher("admin_course.jsp").forward(req,resp);
		} else if("toAdd".equals(action)) {
			UserDao dao = new UserDao();
			List<User> users = dao.queryAllByPage("", 2);
			req.setAttribute("users", users);
			req.getRequestDispatcher("admin_course_add.jsp").forward(req,resp);
		} else if("doAdd".equals(action)) {
			String name = req.getParameter("name");
			String time = req.getParameter("time");
			int userid = Integer.parseInt(req.getParameter("userid"));
			CourseDao dao = new CourseDao();
			Course entity = new Course();
			entity.setName(name);
			entity.setTime(time);
			entity.setUserid(userid);
			boolean flag = dao.save(entity);
			if(flag) {
				PrintWriter out= resp.getWriter();
				out.print("<script type='' language='javascript'>alert('添加成功');location.href='CourseServlet?action=getList'; </script>");
				out.flush();
				out.close();
			}
		} else if("toUpdate".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			CourseDao courseDao = new CourseDao();
			Course course = courseDao.getById(id);
			UserDao dao = new UserDao();
			List<User> users = dao.queryAllByPage("", 2);
			req.setAttribute("users", users);
			req.setAttribute("course", course);
			req.getRequestDispatcher("admin_course_update.jsp").forward(req,resp);
		} else if("doUpdate".equals(action)) {
			String name = req.getParameter("name");
			String time = req.getParameter("time");
			int userid = Integer.parseInt(req.getParameter("userid"));
			int id = Integer.parseInt(req.getParameter("id"));
			CourseDao dao = new CourseDao();
			Course entity = new Course();
			entity.setName(name);
			entity.setTime(time);
			entity.setUserid(userid);
			entity.setId(id);
			boolean flag = dao.update(entity);
			if(flag) {
				PrintWriter out= resp.getWriter();
				out.print("<script type='' language='javascript'>alert('修改成功');location.href='CourseServlet?action=getList'; </script>");
				out.flush();
				out.close();
			}
		} else if("delete".equals(action)) {
			int id = Integer.parseInt(req.getParameter("id"));
			CourseDao dao = new CourseDao();
			boolean flag = dao.delete(id);
			if(flag) {
				PrintWriter out= resp.getWriter();
				out.print("<script type='' language='javascript'>alert('删除成功');location.href='CourseServlet?action=getList'; </script>");
				out.flush();
				out.close();
			}
		} else if("userGetList".equals(action)) {
			CourseDao courseDao = new CourseDao();
			int userid = 0;
			if(req.getSession().getAttribute("userid")!=null) {
				userid = Integer.parseInt(req.getSession().getAttribute("userid").toString());
			}
			List<Course> courses = courseDao.queryAllByPage(userid);
			req.setAttribute("courses", courses);
			req.getRequestDispatcher("user_course.jsp").forward(req,resp);
		}
	}
}
