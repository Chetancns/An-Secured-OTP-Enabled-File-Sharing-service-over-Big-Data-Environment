package com.hfs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.dao.DataSharedDAO;

public class RemoveAccessServlet extends HttpServlet
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
      try
      {
         String filename = req.getParameter("filename");
         String sharedEmail = req.getParameter("sharedEmail");
         DataSharedDAO dDao = new DataSharedDAO();
         dDao.delete(filename.substring(0, filename.indexOf("_")), sharedEmail);
         resp.sendRedirect("myfiles.jsp?msg=Accessed Removed for " + sharedEmail + " on " + filename.substring(filename.indexOf("_") + 1));
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("myfiles.jsp?msg=Error while performing the data share operation: " + e.getMessage());
      }
   }

}
