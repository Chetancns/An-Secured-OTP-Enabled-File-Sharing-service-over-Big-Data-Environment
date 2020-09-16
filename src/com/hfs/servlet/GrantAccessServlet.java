package com.hfs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.dao.DataSharedDAO;
import com.hfs.model.DataSharing;

public class GrantAccessServlet extends HttpServlet
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
         String accesslevel = req.getParameter("accesslevel");
         DataSharing model = new DataSharing();
         model.setAccesslevel(accesslevel);
         model.setEmail(sharedEmail);
         model.setId(filename.substring(0, filename.indexOf("_")));
         DataSharedDAO dDao = new DataSharedDAO();
         dDao.write(model);
         resp.sendRedirect("myfiles.jsp?msg=Data Sharing Successful with " + sharedEmail + " on " + filename.substring(filename.indexOf("_") + 1));
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("myfiles.jsp?msg=Error while performing the data share operation: " + e.getMessage());
      }
   }

}
