package com.hfs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.core.OTP;

public class VerifyOTPServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      String mobileOTP = req.getParameter("mobileOTP");
      String emailOTP = req.getParameter("emailOTP");
      String filename = req.getParameter("filename");
      req.getSession().removeAttribute("otpSent");
      req.setAttribute("filename", filename);
      OTP otp = new OTP();
      if (otp.verifyOTP(req, mobileOTP, emailOTP))
      {
         req.setAttribute("accessGranted", "yes");
         req.getRequestDispatcher("sharedfiles.jsp?msg=OTP Verification Successful.").forward(req, resp);
      }
      else
      {
         req.setAttribute("accessGranted", "no");
         req.getRequestDispatcher("sharedfiles.jsp?msg=OTP Verification Failed.").forward(req, resp);
      }

   }

}
