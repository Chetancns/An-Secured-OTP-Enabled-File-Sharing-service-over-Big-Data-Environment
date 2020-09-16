package com.hfs.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.core.HFSService;
import com.hfs.dao.DataDAO;
import com.hfs.model.Data;
import com.hfs.model.User;
import com.hfs.util.Constants;
import com.oreilly.servlet.MultipartRequest;

public class FileReUploadServlet extends HttpServlet
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
      User uModel = (User) req.getSession().getAttribute("user");
      new MultipartRequest(req, Constants.TEMP_FILE_UPLOAD);
      String filename = req.getParameter("filename");

      File folder = new File(Constants.TEMP_FILE_UPLOAD);
      File file = folder.listFiles()[0];
      File newFile = new File(Constants.TEMP_FILE_UPLOAD + File.separator + filename);
      file.renameTo(newFile);

      HFSService hfsService = new HFSService();
      try
      {
         hfsService.remove(newFile.getName());
         hfsService.put(newFile.getName());
      }
      catch (Exception e1)
      {
         e1.printStackTrace();
         resp.sendRedirect("myfiles.jsp?msg=Error: " + e1.getMessage());
      }

      newFile.delete();
      file.delete();

      DataDAO dDao = new DataDAO();
      Data dModel = new Data();
      dModel.setEntrytime(new Timestamp(System.currentTimeMillis()));
      dModel.setFilename(newFile.getName());
      dModel.setId(filename.substring(0, filename.indexOf("_")));
      dModel.setMimetype(newFile.getName().substring(newFile.getName().lastIndexOf(".") + 1));
      dModel.setUsername(uModel.getEmail());
      String from = req.getParameter("from");

      try
      {
         dDao.reupload(dModel);
         if (from != null && from.equals("shared"))
         {
            resp.sendRedirect("sharedfiles.jsp?msg=New Version of " + filename.substring(filename.indexOf("_") + 1) + " uploaded successfully");
         }
         else
         {
            resp.sendRedirect("myfiles.jsp?msg=New Version of " + filename.substring(filename.indexOf("_") + 1) + " uploaded successfully");
         }
      }
      catch (Exception e2)
      {
         e2.printStackTrace();
         if (from != null && from.equals("shared"))
         {
            resp.sendRedirect("sharedfiles.jsp?msg=Error: "+e2.getMessage());
         }
         else
         {
            resp.sendRedirect("myfiles.jsp?msg=Error: "+e2.getMessage());
         }
      }
   }

}
