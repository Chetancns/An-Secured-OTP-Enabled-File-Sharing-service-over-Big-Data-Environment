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
    <a class="nav-link active" href="hdfs.jsp">HDFS</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="myfiles.jsp">My Files</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="addfile.jsp">Add File</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="sharedfiles.jsp">Shared Files</a>
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


<div>
	Here, you can perform the following operations on HDFS
	<ul>
		<li>Manage your files</li>
		<li>Upload new files</li>
		<li>Access files shared with you</li>
	</ul>

</div>



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

</body>

</html>

<%
   }
%>