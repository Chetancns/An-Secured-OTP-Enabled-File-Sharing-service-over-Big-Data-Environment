package com.hfs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.dao.UserDAO;
import com.hfs.mail.MailThread;
import com.hfs.model.User;

public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		UserDAO dao = new UserDAO();

		try
		{
			String request_type = req.getParameter("request_type");
			if (request_type.equals("register"))
			{
				User user = new User();
				String addr = req.getParameter("addr");
				user.setAddr(addr);
				String email = req.getParameter("email");
				user.setEmail(email);
				String fname = req.getParameter("fname");
				user.setFname(fname);
				String lname = req.getParameter("lname");
				user.setLname(lname);
				String gender = req.getParameter("gender");
				user.setGender(gender);
				String mobile = req.getParameter("mobile");
				user.setMobile(mobile);
				String password = req.getParameter("password");
				user.setPassword(password);
				String role = req.getParameter("role");
				if (role == null || role.trim().length() == 0)
					role = "USER";
				user.setRole(role);

				if ((addr == null || addr.trim().length() == 0) || (email == null || email.trim().length() == 0)
						|| (fname == null || fname.trim().length() == 0)
						|| (lname == null || lname.trim().length() == 0)
						|| (mobile == null || mobile.trim().length() == 0)
						|| (role == null || role.trim().length() == 0)
						|| (gender == null || gender.trim().length() == 0)
						|| (password == null || password.trim().length() == 0))
				{
					resp.sendRedirect(
							"register.jsp?msg=Error! All the fields are mandatory. Please provide the details.");
				}
				else
				{

					dao.register(user);
					resp.sendRedirect("register.jsp?msg=Registration Successful");
				}
			}
			else if (request_type.equals("login"))
			{
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				User user = dao.getUserDetails(email, password);
				if (email == null || email.trim().length() == 0 || password == null || password.trim().length() == 0)
				{
					resp.sendRedirect("login.jsp?msg=Error! All the fields are mandatory. Please provide the details");
				}
				else if (user != null)
				{
					req.getSession().setAttribute("user", user);
					resp.sendRedirect("welcome.jsp?msg=Successfully logged in as " + user.getFname() + " "
							+ user.getLname() + " (" + user.getRole() + ") ");

				}
				else
				{
					resp.sendRedirect("login.jsp?msg=Invalid Credentials");
				}
			}

			else if (request_type.equals("updateprofile"))
			{
				User user = new User();

				String addr = req.getParameter("addr");
				String email = req.getParameter("email");
				String fname = req.getParameter("fname");
				String lname = req.getParameter("lname");
				String gender = req.getParameter("gender");
				String mobile = req.getParameter("mobile");
				String role = req.getParameter("role");
				if (role == null || role.trim().length() == 0)
					role = "USER";
				user.setAddr(addr);
				user.setEmail(email);
				user.setFname(fname);
				user.setLname(lname);
				user.setGender(gender);
				user.setMobile(mobile);
				user.setRole(role);

				if ((addr == null || addr.trim().length() == 0) || (email == null || email.trim().length() == 0)
						|| (fname == null || fname.trim().length() == 0)
						|| (lname == null || lname.trim().length() == 0)
						|| (mobile == null || mobile.trim().length() == 0)
						|| (role == null || role.trim().length() == 0)
						|| (gender == null || gender.trim().length() == 0))
				{
					resp.sendRedirect(
							"updateprofile.jsp?msg=Error! All the fields are mandatory. Please provide the details");

				}
				else
				{

					dao.updateProfile(user);
					req.getSession().removeAttribute("user");
					req.getSession().setAttribute("user", user);
					resp.sendRedirect("updateprofile.jsp?msg=Profile Updated Successfully");
				}

			}
			else if (request_type.equals("changepassword"))
			{
				String oldpassword = req.getParameter("oldpassword");
				String newpassword = req.getParameter("newpassword");

				if (oldpassword == null || oldpassword.trim().length() == 0 || newpassword == null
						|| newpassword.trim().length() == 0)
				{
					resp.sendRedirect(
							"changepassword.jsp?msg=Error! All the fields are mandatory. Please provide the details");
				}
				else
				{

					boolean result = dao.changePassword(((User) req.getSession().getAttribute("user")).getEmail(),
							oldpassword, newpassword);

					if (result)
					{
						resp.sendRedirect("changepassword.jsp?msg=Successfully Updated Your Password");
					}
					else
					{
						resp.sendRedirect("changepassword.jsp?msg=Your Current Password is Wrong");

					}
				}
			}
			else if (request_type.equals("deleteprofile"))
			{
				dao.deleteProfile(((User) req.getSession().getAttribute("user")).getEmail());
				req.getSession().invalidate();
				resp.sendRedirect("login.jsp?msg=Profile Deleted Successfully");
			}
			else if (request_type.equals("forgotpassword"))
			{
				String email = req.getParameter("email");
				if (email == null || email.trim().length() == 0)
				{
					resp.sendRedirect("forgotpassword.jsp?msg=Please enter your email ID");
				}
				else
				{

					String password = dao.forgotPassword(email);

					if (password != null)
					{
						List<String> rcv = new ArrayList<>();
						rcv.add(email);
						new MailThread(
								"<b>Dear " + email + ",</b> <br> Your Current Password is: <b>" + password + "</b>",
								"Password Recovery", rcv);
						resp.sendRedirect("forgotpassword.jsp?msg=Password Recovery mail sent to your email address");
					}
					else
					{
						resp.sendRedirect("forgotpassword.jsp?msg=Email ID did not match with any account");
					}
				}
			}
			else if (request_type.equals("logout"))
			{
				req.getSession().removeAttribute("dir");
				req.getSession().removeAttribute("sync");
				req.getSession().invalidate();
				resp.sendRedirect("login.jsp?msg=Successfully Logged Out");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			resp.sendRedirect("error.jsp?msg=OOPS! Something went wrong");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

}
