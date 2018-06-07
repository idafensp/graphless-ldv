package oeg.upm.isantana.ldvserver.graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import oeg.upm.isantana.ldvserver.algorithm.utils.AlgorithmOutCodes;
import oeg.upm.isantana.ldvserver.gui.ColorManager;

public class Graph 
{
	protected List<GraphNode> nodes;
	protected List<GraphEdge> edges;
	protected int maxDepth;
	protected AlgorithmOutCodes error;
	
	public Graph() {
		this.nodes = new ArrayList<GraphNode>();
		this.edges = new ArrayList<GraphEdge>();
		this.maxDepth = -1;
		this.error = AlgorithmOutCodes.EVERYTHING_OK;
	}

	public List<GraphNode> getNodes() {
		return nodes;
	}

	public List<GraphEdge> getEdges() {
		return edges;
	}

	public void setNodes(List<GraphNode> nodes) {
		this.nodes = nodes;
	}

	public void setEdges(List<GraphEdge> edges) {
		this.edges = edges;
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public void updateMaxDepth(int mx) {
		if(mx>this.maxDepth)
			this.maxDepth = mx;
	}

	public AlgorithmOutCodes getError() {
		return error;
	}

	public void setError(AlgorithmOutCodes error) {
		this.error = error;
	}

	public void addNode(GraphNode n) {
		this.nodes.add(n);
		this.updateMaxDepth(n.getDepth());
	}

	public void addRootNode(GraphNode n) {
		this.nodes.add(n);
	}
	
	public void addEdge(GraphEdge e) {
		this.edges.add(e);
	}
	
	//TODO check this
	public boolean containsNode(GraphNode n) {
		return this.nodes.contains(n);
	}
	
	//TODO check this
	public boolean containsEdge(GraphEdge e) {
		return this.edges.contains(e);
	}
	
	public void print()
	{
		for (GraphEdge ge : this.edges)
		{
			System.out.println(ge.getFromid() + "--" + ge.getId() + "->" + ge.getToid());
		}
	}
	
	public String printVisJS()
	{
		
		System.out.println("printVisJS error?: " + this.error);
		if(this.error != AlgorithmOutCodes.EVERYTHING_OK)
		{
			return checkErrors();
		}
		

		//TODO change to string builder
		String res = "";

	    res +="    var nodes = [\n";
	    

    	//Color col = new Color(206,60,62);
    	//Color col = new Color(70,130,180);
    	Color col = new Color(251,64,76);
    	List<Color> colorList = ColorManager.getColorBands(col, this.maxDepth+3);
    	
	    for(GraphNode gn : this.nodes)
	    {
	    	int color = gn.getDepth() % 10;
	    	String label = gn.getLabel(); 
	    	
	    	String fixed = "";
//	    	if(gn.getDepth() == 0)
//	    		fixed = ", x:0, y:0, fixed:true";
	    	
	    	String boxshape = "";
	    	if(gn.isLiteral())
	    	{
	    		boxshape = ", shape: 'box'";
	    		label = gn.getLiteralValue();
	    		if(label.length() > 20)
	    		{
	    			label=label.substring(0,18) + "...";
	    		}
	    		label="''"+label+"''";
	    	}
	    	
	    	
	    	Color nodecolor = colorList.get(gn.getDepth());
	    	String rgba = "'rgba("+nodecolor.getRed()+","+nodecolor.getGreen()+","+nodecolor.getBlue()+",1)'";
	        res += "  {id: \"" + gn.getId() +"\", label: \"" + label +"\",  rdfslabel: \"" 
	        		+ label +"\", uri: \"" + gn.getUri() +"\", indegree: \"" 
	        		+ gn.getIndegree()+"\", outdegree: \"" + gn.getOutdegree()+"\", types: \"" 
	        		+ gn.getTypesString() +"\", color: "+rgba+" "+ fixed + boxshape + ", title: \"" + gn.getUri() +"\"},\n";

	    }
	    
	    res +="];\n";
	    
	    res += "var edges = [\n";
	    
		for (GraphEdge ge : this.edges)
		{	
			String weight = String.valueOf(ge.getWeight());
			if(weight.length()> 6)
			{
				weight = weight.substring(0,4) + "...";
			}
				
				
			res += "        {from: \"" + ge.getFromid() +"\", to: \"" + ge.getToid() + "\",label: \"" + ge.getLabel() + "(" + weight + ")\", rdfslabel: \"" + ge.getLabel() + "(" + weight + ")\",  arrows:'to', font: {align: 'middle'}},\n";
		}
		
		res +="]";
	    
		return res;
	}
	
	private String errorGraphVisJS(String cause)
	{
		
		//TODO change to string builder
		String res = "";
		res +="var nodes = [\n";
		res += "{id: 1, label: 'ERROR', color: 'rgba(255,0,0,1)', shape: 'circle',font: {'face': 'Monospace', align: 'center'}},\n";
		res += "{id: 2, label: '"+cause+"', shape: 'circle', font: {'face': 'Monospace', align: 'center'}}\n";
		res += "];\n";
	    res += "var edges = [\n";
		res +="{from: 1, to: 2, label: 'why', arrows:'to', font: {align: 'middle'}}\n";
		res +="];";


		
		return res;

	}
	

	
	private String msgGraphVisJS(String msg)
	{
		
		//TODO change to string builder
		String res = "";
		res +="var nodes = [\n";
		res += "{id: 1, image: '../img/pending.png', color: 'rgba(206,60,62,1)', shape: 'circularImage',font: {'face': 'Monospace', align: 'center'}},\n";
		//res += "{id: 1, label: 'Pending', color: 'rgba(206,60,62,1)', shape: 'circle',font: {'face': 'Monospace', align: 'center'}},\n";
		res += "{id: 2, label: '"+msg+"', color: 'rgba(211,96,100,1)',shape: 'circle', font: {'face': 'Monospace', align: 'center'}}\n";
		res += "];\n";
	    res += "var edges = [\n";
		res +="{from: 2, to: 1, label: 'action', arrows:'to', font: {align: 'middle'}}\n"; 
		res +="];";
		
		return res;

	}
	
	private String checkErrors()
	{
		
		switch (error) {
	        case ROOT_NODE_NOT_FOUND:  
	        	return  errorGraphVisJS("Root node not found :(");
	        case MAX_NODES_EXCEED:  
	        	return  errorGraphVisJS("Too many nodes :(");
	        case ROOT_NODE_PENDING :  
	        	return  msgGraphVisJS("Open the right panel and start browsing");
	        default: 
	        	return  errorGraphVisJS("Unexpected error :(");
	    }
		
	}

}
