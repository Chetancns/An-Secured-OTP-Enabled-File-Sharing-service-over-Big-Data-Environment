<%@page import="java.util.ArrayList"%>
<%@page import="com.hfs.model.DataSharing"%>
<%@page import="com.hfs.dao.UserDAO"%>
<%@page import="com.hfs.dao.UserDAO"%>
<%@page import="com.hfs.dao.DataSharedDAO"%>
<%@page import="com.hfs.dao.DataSharedDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hfs.model.Data"%>
<%@page import="java.util.List"%>
<%@page import="com.hfs.dao.DataDAO"%>
<%@page import="com.hfs.dao.DataDAO"%>
<%@page import="com.hfs.model.User"%>
<%
	User u1 = (User) session.getAttribute("user");
   if (u1 == null)
   {
      response.sendRedirect("login.jsp?msg=Session expired. Login again");
   }
   else
   {
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>OTP Enabled File Sharing</title>

<!-- Custom fonts for this theme -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Theme CSS -->
<link href="css/freelancer.min.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="#page-top">OTP-HFS</a>
			<button
				class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="welcome.jsp">Welcome</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="hdfs.jsp">HDFS</a></li>
					<li class="nav-item mx-0 mx-lg-1 dropdown"><a href="#"  
						class="dropdown-toggle nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" data-toggle="dropdown" ><%=u1.getFname()%>
							<%=u1.getLname()%> <b class="caret"></b></a>
						<ul class="dropdown-menu" >
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="updateprofile.jsp" style='color: navy;'>Edit Profile</a></li>
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="changepassword.jsp" style='color: navy;'>Change Password</a></li>
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="account?request_type=deleteprofile" style='color: navy;'>Delete Profile</a></li>
							<li class="nav-item mx-0 mx-lg-1" style='color: navy;'><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
								href="account?request_type=logout" style='color: navy;'>Logout</a></li>
						</ul></li>  

				</ul>
			</div>
		</div>
	</nav>

	<!-- Portfolio Section -->
	<section class="page-section portfolio" id="portfolio">
		<div class="container" style='min-height: 700px;'>
			<br />
			<br />
			<!-- Portfolio Section Heading -->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">HDFS Operations</h2>

			<!-- Icon Divider -->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon"> 
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>



<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link " href="hdfs.jsp">HDFS</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="myfiles.jsp">My Files</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="addfile.jsp">Add File</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" href="sharedfiles.jsp">Shared Files</a>
  </li>
</ul>

<br/>

			<%
				String msg = request.getParameter("msg");
			%>
			<%
				if (msg != null)
						      {
			%>
			<div class="alert alert-success alert-dismissable">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Message!</strong>
				<%=msg%>.
			</div>
			<%
				}
			%>


			<%
				DataSharedDAO dsDao = new DataSharedDAO();
							List<DataSharing> sharedFiles = dsDao.getSharedFilesForUser(u1.getEmail());
							
							DataDAO dDao = new DataDAO();
							List<Data> files = dDao.read(u1.getEmail());
							SimpleDateFormat  sdf = new SimpleDateFormat("dd-MMM-yyyy | hh: mm");
							UserDAO uDao = new UserDAO();
							List<User> users = uDao.getAllUsers();
							DataSharedDAO sharingDao = new DataSharedDAO();
							if (sharedFiles != null && sharedFiles.size() > 0)
							{
			%>
				   		<table class='table'>
				   			<tr>
				   				<th>File Name</th>
				   				<th>Access Type</th>
				   				<th>File Type</th>
				   				<th>Version</th>
				   				<th>Last Modified Time</th>
				   			</tr>
				   			<%
				   				int i=0;
				   				for (DataSharing dataSharing : sharedFiles)
				   				{
				   				   Data file = dDao.getDetails(dataSharing.getId());
				   				   i++;
				   				   %>
				   				   		<tr>
				   				   			<td>   
												<div class="input-group mb-3">
												  <div class="input-group-prepend">
												    
												    <button  style='text-align: left; border: none; width: 100%;' class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-file"></i> <%=file.getFilename().substring(file.getFilename().indexOf("_")+1) %> </button>												    
												    <div class="dropdown-menu">
														<a class="dropdown-item" href="access?filename=<%=file.getFilename() %>" >Get Access</a>

<%-- 												      <a class="dropdown-item" href="filedownload?filename=<%=file.getFilename() %>" >Download</a> --%>
<%-- 												      <a class="dropdown-item" href="#" data-toggle="modal" data-target="#sharing<%=i%>">Sharing</a> --%>
<%-- 												      <a class="dropdown-item" href="#" data-toggle="modal" data-target="#reupload<%=i%>" >Upload New Version</a> --%>
<%-- 												      <a class="dropdown-item" href="filedelete?filename=<%=file.getFilename()%>" onclick='javascript: showGif(<%=i %>);' >Delete File</a> --%>
												    </div>
												  </div>
												</div>
												
													
												
												<!--  MODALS -->

														<div class="portfolio-modal modal fade" id="sharing<%=i%>"
															tabindex="-1" role="dialog" aria-labelledby="portfolioModal1Label"
															aria-hidden="true">
															<div class="modal-dialog modal-xl" role="document">
																<div class="modal-content">
																	<button type="button" class="close" data-dismiss="modal"
																		aria-label="Close">
																		<span aria-hidden="true"> <i class="fas fa-times"></i>
																		</span>
																	</button>
																	<div class="modal-body text-center">
																		<div class="container">
																			<div class="row justify-content-center">
																				<div class="col-lg-8">
																					<h2
																						class="portfolio-modal-title text-secondary text-uppercase mb-0">File Sharing</h2>
																					<div class="divider-custom">
																						<div class="divider-custom-line"></div>
																						<div class="divider-custom-icon">
																							<i class="fas fa-star"></i>
																						</div>
																						<div class="divider-custom-line"></div>
																					</div>
																					<%
																						List<DataSharing> shareDetails = sharingDao.getSharedUsersForFile(file.getId());
																						List<String> sharedUsers = new ArrayList<>();
																						for (DataSharing ds : shareDetails)
																						{
																						   sharedUsers.add(ds.getEmail());
																						}
																						   sharedUsers.add(u1.getEmail());
																						%>
																					<h4>Share this File</h4>
																					<hr/>
																					<form action='grant' method=post>
																						<input type=hidden name='filename' value='<%=file.getFilename() %>' />
																						<label>Select the User</label>
																						<select name='sharedEmail' class='form-control'>
																							<%
																								for (User u: users)
																								{
																								   if (!sharedUsers.contains(u.getEmail()))
																								   {
																								   %>
																								   		<option value='<%=u.getEmail() %>'><%=u.getFname() %> <%=u.getLname()%> (<%=u.getEmail() %>)</option>
																								   <%
																								   }
																								}
																							%>
																						</select>
																						<br/>
																						<label>Select the Access Level</label>
																						<select name='accesslevel' class='form-control' >
																							<option value='Read Only'> Read Only Access</option>
																							<option value='Read Write' > Read Write Access</option>
																						</select>
																						<br/>
																						<input type=submit value='Grant Access' class='btn btn-primary' />
																					</form>																				
							
																					<%
																						if (shareDetails != null && shareDetails.size() > 0)
																						{
																					%>
																					<hr />
																					<h4>This File is Shared with</h4>
																					<hr/>
																						<table class='table'>
																							<tr>
																								<th> Email </th>
																								<th> Access Level </th>
																							</tr>
																							<%
																								for (DataSharing ds: shareDetails)
																								{
																								   %>
																								   		<tr>
																								   			<td>
																								   															<div class="input-group mb-3">
																											  <div class="input-group-prepend">
																											    
																											    <button  style='text-align: left; border: none; width: 100%;' class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user"></i> <%=ds.getEmail() %></button>												    
																											    <div class="dropdown-menu">
																												<%
																													if (ds.getAccesslevel().equals("Read Write"))
																													{
																													   %>
																													      <a class="dropdown-item" href="change?filename=<%=file.getFilename()%>&sharedEmail=<%=ds.getEmail()%>&accesslevel=Read Only" >Change Access to Read Only</a>																											   
																													   <%
																													}
																													else
																													{
																													   %>
																											      <a class="dropdown-item" href="change?filename=<%=file.getFilename()%>&sharedEmail=<%=ds.getEmail()%>&accesslevel=Read Write" >Change Access to Read Write</a>																													   
																													   <%
																													}
																												%>
																											      <a class="dropdown-item" href="remove?filename=<%=file.getFilename()%>&sharedEmail=<%=ds.getEmail()%>" >Remove Access</a>
																											    </div>
																											  </div>
																											</div>
																																							   			
																								   			
																								   			
																								   			</td>
																								   			<td> <%=ds.getAccesslevel() %> Access</td>
																								   		</tr>
																								   <%
																								}
																							%>
																						</table>
																					<% } 
																						else
																						{
																							%>
																								<hr/>
																								<h4> This File is Not Shared with Anyone</h4>
																								<hr/>
																							<%
																						}
																					%>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
												
												
												
				   				   			</td>
				   				   			<td> <%=dataSharing.getAccesslevel() %> Access</td>
				   				   			<td> <%=file.getMimetype().toUpperCase() %> File </td>
				   				   			<td> <%=file.getVersion()%> </td>
				   				   			<td> <%=sdf.format(file.getEntrytime()) %> </td>
				   				   		</tr>
				   				   <%
				   				}
				   			%>
				   		</table>
				   		
				   		
				   		<%
				   			String otpSent = (String) request.getAttribute("otpSent");
				   			if (otpSent != null && otpSent.equals("yes"))
				   			{
				   			   String filename = (String) request.getAttribute("filename");
				   			   %>
							   		<hr/>
				   			   		
				   			   		<h4>Please provide the OTP to access <i class='fa fa-file'></i> <%=filename.substring(filename.indexOf("_")+1) %></h4>
				   			   		<br/>
				   			   		<form action='verifyOTP' method=post>
				   			   			<input type=hidden name='filename' value='<%=filename %>' /> 
				   			   			<label>Mobile OTP</label>
				   			   			<input type=text class='form-control' name='mobileOTP' required="required" placeholder="Mobile OTP" />
				   			   			<br/>
				   			   			<label>Email OTP</label>
				   			   			<input type=text class='form-control' name='emailOTP' required="required" placeholder="Email OTP" />
				   			   			<br/>
				   			   			<input type=submit value='Verify OTP' class='btn btn-primary' />
				   			   		</form>
				   			   	
				   			   <%
				   			}
				   		%>
				   		
				   		<%
							String accessGranted = (String) request.getAttribute("accessGranted");
		  	   			    String filename = (String) request.getAttribute("filename");
				   		
				   			if (accessGranted != null && accessGranted.equals("yes"))
				   			{
				   			   %>
				   			   <hr/>
				   			   
				   			   <h4><i class='fa fa-file'></i> <%=filename.substring(filename.indexOf("_")+1) %></h4>
				   			   <hr/>
				   			   		<%
				   			   			String access = dsDao.getAccess(u1.getEmail(), filename.substring(0, filename.indexOf("_")));
				   			   			if (access.equals("Read Write"))
				   			   			{
				   			   			   %>
												      <a class='btn btn-primary' href="filedownload?filename=<%=filename %>" >Download</a>
												      &nbsp;&nbsp;&nbsp;&nbsp;<a class='btn btn-warning' href="#" data-toggle="modal" data-target="#reupload<%=filename.substring(0, filename.indexOf("_"))%>" >Upload New Version</a>
												      &nbsp;&nbsp;&nbsp;&nbsp;<a class='btn btn-danger'  href="filedelete?from=shared&filename=<%=filename%>" onclick='javascript: showGif(<%=filename.substring(0, filename.indexOf("_")) %>);' >Delete File</a>
												      <img src='uploading.gif' id='gif<%=filename.substring(0, filename.indexOf("_")) %>' style='width: 75px; display: none;' />
														<div class="portfolio-modal modal fade" id="reupload<%=filename.substring(0, filename.indexOf("_"))%>"
															tabindex="-1" role="dialog" aria-labelledby="portfolioModal1Label"
															aria-hidden="true">
															<div class="modal-dialog modal-xl" role="document">
																<div class="modal-content">
																	<button type="button" class="close" data-dismiss="modal"
																		aria-label="Close">
																		<span aria-hidden="true"> <i class="fas fa-times"></i>
																		</span>
																	</button>
																	<div class="modal-body text-center">
																		<div class="container">
																			<div class="row justify-content-center">
																				<div class="col-lg-8">
																					<h2
																						class="portfolio-modal-title text-secondary text-uppercase mb-0">Upload New Version</h2>
																					<div class="divider-custom">
																						<div class="divider-custom-line"></div>
																						<div class="divider-custom-icon">
																							<i class="fas fa-star"></i>
																						</div>
																						<div class="divider-custom-line"></div>
																					</div>
																					
																					<form id='reuploadform' action='filereupload?from=shared&filename=<%=filename %>' method=post enctype="multipart/form-data">
																						<label>Upload New Version</label>
																						<input type=file name='file' class='form-control' />
																						<br/>
																						<img src='uploading.gif' id='ugifuploading' style='display: none; width: 100px;'/>
																						<input id='reuploadbtn' onclick='javascript: reupload("uploading");' type=button value='Upload' class='btn btn-primary' />
																					</form>
																					

																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
												      
				   			   			   <%
				   			   			}
				   			   			else if (access.equals("Read Only"))
				   			   			{
				   			   			   %>
												      <a class='btn btn-primary' href="filedownload?filename=<%=filename %>" >Download</a>
				   			   			   
				   			   			   <%
				   			   			}
				   			   		%>
				   			   		
													   			   	
				   			   <%
				   			}
				   			else if (accessGranted != null && accessGranted.equals("no"))
				   			{
				   			   %>
				   			   	<hr/>
				   			   	Invalid OTP. Access Denied
				   			   <%
				   			}
				   		%>
				   		
				   		
				   <%
				}
				else
				{
				   %>
				   		No Files Shared With You
				   <%
				}
			%>


			<!-- Portfolio Grid Items -->
			<div class="row">

				<!-- Portfolio Item 1 -->
				<!--         <div class="col-md-6 col-lg-4"> -->
				<!--           <div class="portfolio-item mx-auto" data-toggle="modal" data-target="#portfolioModal1"> -->
				<!--             <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100"> -->
				<!--               <div class="portfolio-item-caption-content text-center text-white"> -->
				<!--                 <i class="fas fa-plus fa-3x"></i> -->
				<!--               </div> -->
				<!--             </div> -->
				<!--             <img class="img-fluid" src="img/portfolio/cabin.png" alt=""> -->
				<!--           </div> -->
				<!--         </div> -->


			</div>
			<!-- /.row -->

		</div>
	</section>

	<!-- Copyright Section -->
	<section class="copyright py-4 text-center text-white">
		<div class="container">
			<small>Copyright &copy; OTP-HFS</small>
		</div>
	</section>

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-to-top d-lg-none position-fixed ">
		<a class="js-scroll-trigger d-block text-center text-white rounded"
			href="#page-top"> <i class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- Portfolio Modals -->

	<!-- Portfolio Modal 1 -->
	<div class="portfolio-modal modal fade" id="portfolioModal1"
		tabindex="-1" role="dialog" aria-labelledby="portfolioModal1Label"
		aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"> <i class="fas fa-times"></i>
					</span>
				</button>
				<div class="modal-body text-center">
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-8">
								<!-- Portfolio Modal - Title -->
								<h2
									class="portfolio-modal-title text-secondary text-uppercase mb-0">Log
									Cabin</h2>
								<!-- Icon Divider -->
								<div class="divider-custom">
									<div class="divider-custom-line"></div>
									<div class="divider-custom-icon">
										<i class="fas fa-star"></i>
									</div>
									<div class="divider-custom-line"></div>
								</div>
								<!-- Portfolio Modal - Image -->
								<img class="img-fluid rounded mb-5"
									src="img/portfolio/cabin.png" alt="">
								<!-- Portfolio Modal - Text -->
								<p class="mb-5">Lorem ipsum dolor sit amet, consectetur
									adipisicing elit. Mollitia neque assumenda ipsam nihil,
									molestias magnam, recusandae quos quis inventore quisquam velit
									asperiores, vitae? Reprehenderit soluta, eos quod consequuntur
									itaque. Nam.</p>
								<button class="btn btn-primary" href="#" data-dismiss="modal">
									<i class="fas fa-times fa-fw"></i> Close Window
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/freelancer.min.js"></script>

	<script type="text/javascript">
		
	function showGif(i)
	{
		$('#gif'+i).show();
	}
	
	function reupload(i)
	{
		$('#reuploadform').submit();
		$('#reuploadbtn').hide();
		$('#ugif'+i).show();
	}
	
	
	</script>
</body>

</html>

<%
   }
%>