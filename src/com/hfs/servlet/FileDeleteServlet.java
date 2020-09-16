package com.hfs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.core.HFSService;
import com.hfs.dao.DataDAO;

public class FileDeleteServlet extends HttpServlet
{

   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      String from = req.getParameter("from");
      try
      {
         String filename = req.getParameter("filename");
         HFSService hfsService = new HFSService();
         hfsService.remove(filename);
         DataDAO dDao = new DataDAO();
         
         dDao.delete(filename.substring(0, filename.indexOf("_")));
         if (from != null && from.equals("shared"))
         {
            resp.sendRedirect("sharedfiles.jsp?msg=" + filename.substring(filename.indexOf("_") + 1) + "File Deleted");
         }
         else
         {
            resp.sendRedirect("myfiles.jsp?msg=" + filename.substring(filename.indexOf("_") + 1) + "File Deleted");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         if (from != null && from.equals("shared"))
         {
            resp.sendRedirect("sharedfiles.jsp?msg=Error: " + e.getMessage());
         }
         else
         {
            resp.sendRedirect("myfiles.jsp?msg=Error: " + e.getMessage());
         }
      }
   }

}
