package com.hfs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.core.OTP;

public class GetAccessServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      String filename = req.getParameter("filename");
      OTP otp = new OTP();
      otp.generateOTP(req);
      
      req.setAttribute("otpSent", "yes");
      req.setAttribute("filename", filename);
      
      req.getRequestDispatcher("sharedfiles.jsp?msg=OTP sent for Verification.").forward(req, resp);
      

   }

}
