<%@page import="oeg.upm.isantana.ldvserver.algorithm.ThresholdWeightAlg"%>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.Algorithm"%>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.utils.QueryExecutor"%>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.utils.AlgorithmDipatcher"%>
<%@page import="oeg.upm.isantana.ldvserver.gui.GraphSelector"%>
<%@page import="oeg.upm.isantana.ldvserver.gui.AlgorithmSelector"%>
<%@page import="oeg.upm.isantana.ldviz.utils.HashNodes"%>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.utils.Constants"%>
<%@page import="oeg.upm.isantana.ldvserver.ServerConfigListener" %>
<%@page import="oeg.upm.isantana.ldvserver.algorithm.gui.GUIAssets" %>
<%@page import="oeg.upm.isantana.ldvserver.*" %>


<!DOCTYPE html>
<!-- saved from url=(0057)http://visjs.org/examples/network/nodeStyles/shadows.html -->
<html>
<head>

	<link rel="apple-touch-icon" sizes="57x57" href="./img/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="./img/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="./img/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="./img/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="./img/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="./img/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="./img/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="./img/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="./img/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="./img/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="./img/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="./img/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="./img/favicon/favicon-16x16.png">
	<link rel="manifest" href="./img/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
	<meta name="theme-color" content="#CE3C3C">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Graphless</title>
    <script type="text/javascript" src="./visjs/vis.js"></script>
    <link href="./visjs/vis-network.min.css" rel="stylesheet" type="text/css">
    
        <!-- Bootstrap core CSS -->
    <link href="./bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="./assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="https://use.fontawesome.com/98ca09acb5.js"></script>
    
    
    <!-- https://www.w3schools.com/howto/howto_js_sidenav.asp -->
    <!-- Custom styles for this template -->
    <link href="./css/graphless.css" rel="stylesheet">
    <link href="./css/slider.css" rel="stylesheet">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="./bsselect/dist/css/bootstrap-select.css">
	
	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	
  
	    
    
    
</head>

<body>

<%


Algorithm algor = null;
GraphSelector graphSel = new GraphSelector();
AlgorithmSelector algSel = new AlgorithmSelector();
int maxNodes = 1000; //this should come from properties


//algor = new ThresholdWeightAlg("TWA", maxNodes, request);
algor = AlgorithmDipatcher.dispathAlgorithm(maxNodes, request);
	
	
System.out.println("RNU="+ algor.getRootUri());
System.out.println("GN="+ algor.getGraphName());




String parsHtml  = GUIAssets.getParametersHtml(algor.getParameters());


%>


<!-- Sidenav panel -->
<div id="mySidenav" class="sidenav">

	<form method="post" id="graphform" action="./index.jsp" role="form">
    <!-- <a href="about/#datasets" target="_blank"><span class="infotitle">Dataset<sup><i class="fa fa-info-circle"></i></sup></span></a> -->
	<div class="form-group panelsection">
	  
   	   <br><br>
	  <a href="about/#datasets" target="_blank">
	  		<span class="infotitle"><%out.print(graphSel.printGraphName(algor.getGraphName()));%><sup><i class="fa fa-info-circle"></i></sup>
	  		</span>
 	  </a>
			
	  <!-- </select>-->
    </div>
    
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">  <i class="fa fa-chevron-circle-right" aria-hidden="true"></i></a>

	
	<hr>
	
	<div  class="form-group panelsection">
	
	

	<h4 class="paneltitle">Explore</h4>

	  <label for="rooturi" class="mtop">URI</label>
	  <input type="text" placeholder="Type a URI" class="form-control" id="rooturi" aria-describedby="" name="rooturi" value="<%out.print(algor.getRootUri());%>">

	  <%
	  
		out.write(parsHtml);
	  
	  %> 	  
 	   <div class="center">
 	      <input  type="button" class="btn btn-danger gobtn"  onclick="submitNewResource()" value="Go!">	
 	   </div>
       


      
   </div> 
   <hr>
   
   <div class="panelsection">
   	<h4 class="paneltitle" id="abouttitle">Select a node...</h4>
   	<p class="suburi" id="abouturi"></p>
   </div>
   
   
   <div class="panelsection">
     <table class="table">
	  <thead>
	    <tr class="bg-danger">
	      <th scope="col">Property</th>
	      <th scope="col">Value</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">Types</th>
	      <td><span id="typeList">...</span></td>
	    </tr>
	    <tr>
	      <th scope="row">Ing. Degree</th>
	      <td><span id="indegree">...</span></td>
	    </tr>
	    <tr>
	      <th scope="row">Out. Degree</th>
	      <td><span id="outdegree">...</span></td>
	    </tr>
	  </tbody>
	</table>
   </div>
   
   <hr>
   
   <div class="panelsection">
   	<h4 class="advancedpaneltitle">Advanced</h4>
	<!-- <input type="checkbox" onchange="switchPhysics()" id="physcheck" checked> Physics -->
	
	<input type="checkbox" data-width="100%" data-toggle="toggle" data-on="Physics On" data-off="Physics Off" data-onstyle="danger" id="physcheck" onchange="switchPhysics()" checked>
	<script>
	  $(function() {
	    $('#physcheck').bootstrapToggle({
	      on: 'Physics On',
	      off: 'Physics Off'
	    });
	  })
	</script>
	
	<hr>
	
   	<h4 class="advancedpaneltitle">Data options</h4>
   	
	<div class="form-group">
	 <label for="algorid" class="mtop">Algorithm</label>
	  <select class="form-control  show-tick" id="algorid" name="algorid" data-style="btn-danger">
			<%out.print(algSel.printOptions(algor.getAlgName()));%>
	  </select>
	  
	 <label for="algorid" class="mtop">Dataset</label>
	  <select class="form-control  show-tick" id="graphnameid" name="graphnameid" data-style="btn-danger">
			<%out.print(graphSel.printOptions(algor.getGraphName()));%>
	  </select>
	   <div class="center">
	   		<input  type="button" class="btn btn-danger gobtn"  onclick="submitNewResource()" value="Change">
	   </div>
    </div>
 


 	 </form>  

   
</div>
<!-- /Sidenav panel -->


	<div class="logo" id="logo"><img src="./img/logobg.png"></div>
	<div class="logo gps_ring" id="loadinglogo" ><img src="./img/loadingbg.png"></div>
	
</div>

<!-- Use any element to open the sidenav -->
<!-- <button onclick="openNav()" type="button" class="btn btn-danger topbtn"> -->
<!-- 	<i class="fa fa-chevron-circle-left" aria-hidden="true"> -->
<!-- </button> -->

<a href="javascript:void(0)" class="openbtn  topbtn" onclick="openNav()">  <i class="fa fa-chevron-circle-left" aria-hidden="true"></i></a>
    
    
<div id="mynetwork">
	<div id="divcanvasnetwork" class="vis-network" tabindex="900" style="position: relative; overflow: hidden; touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); width: 100%; height: 100%;">
		<canvas id="myCanvas" width="1600" height="1600" style="position: relative; touch-action: none; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); width: 100%; height: 100%;">
		</canvas>
	</div>
</div>

<a id="downpng" href="javascript:void(0)" download="graphless.png" class="openbtn  bottomleftbtn btn-circle btn-danger"  onclick="getPngImage()">  <i class="fa fa-camera-retro" aria-hidden="true"></i></a>

<a href="javascript:void(0)" class="openbtn  bottomleftbtn2 btn-circle btn-danger"  onclick="alert('RDF coming soon... [ToDo]')">  <i class="fa fa-download" aria-hidden="true"></i></a>

<a href="./about/" target="_blank"  class="openbtn bottomleftbtn3 btn-circle btn-danger">  <i class="fa fa-info" aria-hidden="true"></i></a>

<a href="http://www.oeg-upm.net/" target="_blank"><img src="./img/oeggl.png" class="oeglogo"></a>

<script type="text/javascript">

    var len = undefined;
    var nodes, edges, network;

    <%

    
	algor.start();

	
	out.println(algor.getGraph().printVisJS());
    %>

    // create a network
    var container = document.getElementById('mynetwork');
    var data = {
    	nodes: new vis.DataSet(nodes),
        edges: new vis.DataSet(edges)
    };
    var options = {
		interaction:{hover:true},
   		layout: {
   			improvedLayout: true,
		    nodeSpacing: 425,
   			randomSeed:2
        },
        physics: {
            "forceAtlas2Based": {
              "springLength": 100,
              "avoidOverlap": 1
            },
            "minVelocity": 0.75,
            "solver": "forceAtlas2Based"
          },
        nodes: {		
            font: {
                size: 16
            },
            borderWidth: 2,
            shadow:false
        },
        edges: {
        	smooth: {
        	      "type": "cubicBezier",
        	      "forceDirection": "none",
        	      "roundness": 0
        	    },
       	    font: {
       	        size: 10,
       	      },
            width: 1,
            shadow:false
        }
    };
    network = new vis.Network(container, data, options);
    
    network.on("doubleClick", function (params) {
    	openNav();
        document.getElementById('rooturi').value =  data.nodes.get(params.nodes.toString()).uri;
        updateAboutTitle(data.nodes.get(params.nodes.toString()).label,
				data.nodes.get(params.nodes.toString()).uri,
				data.nodes.get(params.nodes.toString()).types,
				data.nodes.get(params.nodes.toString()).indegree,
				data.nodes.get(params.nodes.toString()).outdegree);
    });


//     network.on("showPopup", function (params) {
//         document.getElementById('eventSpan').innerHTML = '<h2>showPopup event: </h2>' + JSON.stringify(params, null, 4);
//     });
    
    network.on("afterDrawing", function (ctx) {
    	document.getElementById("loadinglogo").style.display = "none";
    	document.getElementById("logo").style.display = "block";
   	});

    
    // subscribe to any change in the DataSet
//      data.on('*', function (event, properties, senderId) {
//          console.log('event', event, properties);
//      });
    

    
//     function updateNode() {
//         try {
//         	data.nodes.update({
//                 id: document.getElementById('rooturi').value,
//                 label: 'XY'
//             });
//         }
//         catch (err) {
//             console.log(err);
//         }
//     }
    


//     function removeNodeLabels() {
//         try {
//         	data.nodes.forEach(function(node){
//                 data.nodes.update({
//                     id: node.id,
//                     label: ''
//                 })
//              });
        	
//         }
//         catch (err) {
//             console.log(err);
//         }
//     }
</script>

<!-- TODO manipulate nodes view-source:http://visjs.org/examples/network/data/dynamicData.html -->

    
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> 
	<script src="./js/graphless.js"></script>
	<script src="https://getbootstrap.com/assets/js/vendor/popper.min.js"></script>
    <script src="./bootstrap/dist/js/bootstrap.js"></script>
   
   
	<!-- Latest compiled and minified JavaScript -->
	<script src="./bsselect/dist/js/bootstrap-select.min.js"></script>
	
	<!-- (Optional) Latest compiled and minified JavaScript translation files -->
	<script src="./bsselect/dist/js/i18n/defaults-*.min.js"></script>
	
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
       
    
</body>

</html>