<%@page import="oeg.upm.isantana.ldvserver.algorithm.ThresholdWeightAlg"%>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.utils.QueryExecutor"%>
<%@page import="oeg.upm.isantana.ldvserver.gui.GraphSelector"%>
<%@page import="oeg.upm.isantana.ldviz.utils.HashNodes"%>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.utils.Constants"%>
<%@page import="oeg.upm.isantana.ldvserver.ServerConfigListener" %>
<%@page import="oeg.upm.isantana.ldvserver.*" %>


<!DOCTYPE html>
<!-- saved from url=(0057)http://visjs.org/examples/network/nodeStyles/shadows.html -->
<html>
<head>

	<link rel="apple-touch-icon" sizes="57x57" href="../../gview/img/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="../../gview/img/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="../../gview/img/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="../../gview/img/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="../../gview/img/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="../../gview/img/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="../../gview/img/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="../../gview/img/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="../../gview/img/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="../../gview/img/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="../../gview/img/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="../../gview/img/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="../../gview/img/favicon/favicon-16x16.png">
	<link rel="manifest" href="../../gview/img/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
	<meta name="theme-color" content="#CE3C3C">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Graphless</title>
    <script type="text/javascript" src="../../gview/visjs/vis.js"></script>
    <link href="../../gview/visjs/vis-network.min.css" rel="stylesheet" type="text/css">
    
        <!-- Bootstrap core CSS -->
    <link href="../../gview/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../gview/bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="./assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../gview/bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="https://use.fontawesome.com/98ca09acb5.js"></script>
    
    
    <!-- https://www.w3schools.com/howto/howto_js_sidenav.asp -->
    <!-- Custom styles for this template -->
    <link href="../../gview/css/graphless.css" rel="stylesheet">
    <link href="../../gview/css/slider.css" rel="stylesheet">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="../../gview/bsselect/dist/css/bootstrap-select.css">
	
	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	
  
	    
    
    
</head>

<body>



<div id="mySidenav" class="sidenav">

    <span class="infotitle">Menu</span>
	
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">  <i class="fa fa-chevron-circle-right" aria-hidden="true"></i></a>
	<br>
	<hr>
   
   <div class="panelsection">
   	<h3 class="paneltitle">About</h3>
   </div>
   <hr>
   <h1 class="social">
   		<i class="fa fa-github" aria-hidden="true"></i>
		<i class="fa fa-twitter" aria-hidden="true"></i>
	</h1>
   
   <a href="http://www.oeg-upm.net/" target="_blank"><img src="../../gview/img/oeggl.png" class="oeglogo"></a>
   
</div>

	<div class="logo" id="logo"><img src="../../gview/img/logobg.png"></div>
	
</div>

<a href="javascript:void(0)" class="openbtn  topbtn" onclick="openNav()">  <i class="fa fa-chevron-circle-left" aria-hidden="true"></i></a>

    
<div class="aboutcontent">

	<h2 class="abouttitle" id="about">What is Graphless?</h2>
	<p class="abouttext">
		Graphless is a tool for visualizing statistical data summaries from semantic datasets. Sounds complicated, right? Well&hellip; it is not. 
		The basic idea is rather simple: we study the distribution of types, properties and the ingoing and outgoing degree of each node in the graph. Using this information we 
		have developed a set of heuristics for exploring the data. Starting from a given node (i.e. root node) we traverse the 
		graph until a given condition is reached. 
	</p>	
	<h3 class="abouttitle">Statistical calculations</h3>
	
	<p class="abouttext">
		In a nutshell: we count things. For each type we count how many times each property is used. We count how many times an individual of a given type 
		is used with a given property, both when appearing in the subject (outgoing property) or the object (ingoing property). We also count how many properties 
		(ingoing and outgoing) each node has. We normalize all these numbers to 1, by dividing them by the corresponding maximun value. 
	</p>
	
	<p class="abouttext">
		By doing this we a have weighted measure of the <emph>importance</emph> of each element. We can then used this normalized information to decide which nodes and properties 
		to explore in our algorithms. 
	</p>
	
	<hr>



</div>



<!-- TODO manipulate nodes view-source:http://visjs.org/examples/network/data/dynamicData.html -->

    
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> 
	<script src="../../gview/js/graphless.js"></script>
	<script src="https://getbootstrap.com/assets/js/vendor/popper.min.js"></script>
    <script src="../../gview/bootstrap/dist/js/bootstrap.js"></script>
   
   
	<!-- Latest compiled and minified JavaScript -->
	<script src="../../gview/bsselect/dist/js/bootstrap-select.min.js"></script>
	
	<!-- (Optional) Latest compiled and minified JavaScript translation files -->
	<script src="../../gview/bsselect/dist/js/i18n/defaults-*.min.js"></script>
	
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
       
    
</body>

</html>