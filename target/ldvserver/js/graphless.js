/* Set the width of the side navigation to 250px */
function openNav() {
    document.getElementById("mySidenav").style.width = "350px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

function switchPhysics()
{
	if(document.getElementById("physcheck").checked)
	{
		network.setOptions( { physics: true } );
	}
	else
	{
		network.setOptions( { physics: false } );
	}
}

function setValueDesc(text){
	document.getElementById("desc").value = text;
}
//
//function removeNodeLabels() {
//    try {
//    	data.nodes.forEach(function(node){
//            data.nodes.update({
//                id: node.id,
//                label: ''
//            })
//         });
//    	
//    	/*data.edges.forEach(function(edge){
//            data.edge.update({
//                id: edge.id,
//                label: 'https://github.com/almende/vis/issues/94'
//            })
//         });*/
//    }
//    catch (err) {
//        console.log(err);
//    }
//}

function updateRender() {

	if(document.getElementById("graphrender").value === "Labeless")
	{
		removeNodeLabels();
		alert("labelesssss!");
	}
	else {
		alert("nothing to do");
	}
	
}

function updateAboutTitle(t1, uri1, types1, id1, od1)
{
	document.getElementById("abouttitle").innerHTML = t1;
	
	if(uri1 != "null")
	{
		document.getElementById("abouturi").innerHTML = "<a href=\""+uri1+"\">"+uri1+"</a>";
	}
	else
	{
		document.getElementById("abouturi").innerHTML = "";
	}
	
	if(types1 != "null")
	{
		document.getElementById("typeList").innerHTML = types1;
	}
	else
	{
		document.getElementById("typeList").innerHTML = "...";
	}
	

	document.getElementById("indegree").innerHTML = id1;
	document.getElementById("outdegree").innerHTML = od1;
	
	
}

function submitNewResource()
{
	//TODO validation pending

	closeNav();

	document.getElementById("loadinglogo").style.display = "block";
	document.getElementById("logo").style.display = "none";

	document.getElementById("graphform").submit();
}
//
//function showSelectGraph()
//{
//	document.getElementById("graphselectdiv").style.display = "block";
//}

//https://stackoverflow.com/questions/12796513/html5-canvas-to-png-file
function getPngImage() {
  	
  	
      var networklist = document.getElementById("mynetwork").childNodes;
      var canvasdivlist = networklist[0].children;
      var canvas = canvasdivlist[0];
      
      
      var ctx = canvas.getContext("2d");
      
      // save canvas image as data url (png format by default)
      var img = canvas.toDataURL("image/png;");
      

      var enlace = document.getElementById("downpng");
      
      enlace.href = img;
      
      
      //img = img.replace("image/png","image/octet-stream"); // force download, user would have to give the file name.
      // you can also use anchor tag with download attribute to force download the canvas with file name.
      //window.open(img,"","width=700,height=700");
      
      
  }
