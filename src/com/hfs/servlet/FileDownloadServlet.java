package com.hfs.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.core.HFSService;
import com.hfs.util.Constants;

public class FileDownloadServlet extends HttpServlet
{

   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      String filename = req.getParameter("filename");

      HFSService hfsService = new HFSService();
      try
      {
         hfsService.get(filename);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("myfiles.jsp?msg=Error: " + e.getMessage());
      }

      resp.setContentType("text/html");
      PrintWriter out = resp.getWriter();
      String filepath = Constants.TEMP_FILE_DOWNLOAD + File.separator;
      resp.setContentType("APPLICATION/OCTET-STREAM");
      resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

      FileInputStream fileInputStream = new FileInputStream(filepath + filename);

      int i;
      while ((i = fileInputStream.read()) != -1)
      {
         out.write(i);
      }
      fileInputStream.close();
      out.close();
      
      new File(Constants.TEMP_FILE_DOWNLOAD+File.separator+filename).delete();
      
      resp.sendRedirect("myfiles.jsp");
   }

}
