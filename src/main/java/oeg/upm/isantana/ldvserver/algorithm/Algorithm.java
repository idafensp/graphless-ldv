package oeg.upm.isantana.ldvserver.algorithm;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import oeg.upm.isantana.ldviz.utils.HashNodes;
import oeg.upm.isantana.ldvserver.ServerConfigListener;
import oeg.upm.isantana.ldvserver.algorithm.gui.parameter.Parameter;
import oeg.upm.isantana.ldvserver.algorithm.utils.AlgorithmOutCodes;
import oeg.upm.isantana.ldvserver.algorithm.utils.Constants;
import oeg.upm.isantana.ldvserver.algorithm.utils.QueryExecutor;
import oeg.upm.isantana.ldvserver.graph.Graph;
import oeg.upm.isantana.ldvserver.graph.GraphNode;

import javax.servlet.http.HttpServletRequest;

public abstract class Algorithm {
	
	protected String algName;
	protected String graphName;
	protected String rootId;
	protected String rootUri;
	protected Graph graph;
	protected int maxNodes;
	protected QueryExecutor qex;
	protected LinkedList<Parameter> parameters;
	
	
	public Algorithm(String aname, String gname, String rt, int mx)
	{
		this.algName = aname;
		
		if(gname!=null)
			this.graphName = gname;
		else
			this.graphName = "NO_GRAPH";
		
		if(rt != null)
			this.rootId = rt;
		else
		{
			System.out.println("Root Id rt is null");
			this.rootId = "NO_ROOT_NODE";
		}
		
		this.graph = new Graph();
		this.maxNodes = mx;
		this.parameters = new LinkedList<Parameter>();
	}
	


	public Algorithm(String aname, int mx, HttpServletRequest request)
	{
		
		 this.rootUri = ServerConfigListener.getProperty("default.dataset.root");
		 System.out.println("Default URI is " + this.rootUri);

		 this.graphName = ServerConfigListener.getProperty("default.dataset");

		this.algName = aname;
		
		if(request.getParameter("graphnameid")!=null)
			this.graphName = request.getParameter("graphnameid");
		
		if(request.getParameter("rooturi") != null)
		{
			System.out.println("RootURI is not null, is request= "+request.getParameter("rooturi"));
			this.rootUri = request.getParameter("rooturi") ;
		}

		System.out.println(">rooturi is="+this.rootUri);
		this.rootId = "NOT_GENERATED_ROOT_ID";
		if(!this.rootUri.equals(Constants.ROOT_PENDIG_STRING))
		{
			try {
				this.rootId = HashNodes.hashNodeUri(this.rootUri, this.graphName);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		System.out.println(">rootnodeid is="+this.rootId);

		this.qex = new QueryExecutor(this.graphName, 
				ServerConfigListener.getProperty("neo4j.url"),
				ServerConfigListener.getProperty("neo4j.user"),
				ServerConfigListener.getProperty("neo4j.pass"));
		
		this.graph = new Graph();
		this.maxNodes = mx;
		this.parameters = new LinkedList<Parameter>();
		
	}
	
	public abstract boolean start();

	public String getAlgName() {
		return algName;
	}

	public String getGraphName() {
		return graphName;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setAlgName(String algName) {
		this.algName = algName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	public void setGraph(Graph g) {
		this.graph = g;
	}

	public String getRoot() {
		return rootId;
	}

	public void setRoot(String root) {
		this.rootId = root;
	}

	public int getMaxNodes() {
		return maxNodes;
	}

	public void setMaxNodes(int maxNodes) {
		this.maxNodes = maxNodes;
	}
	
	
	public GraphNode addRootnode()
	{//add root node
		
		System.out.println(">>>>>>>>>>>>>>ROOT:" + rootId);
		if(rootId.equals(Constants.ROOT_PENDIG_STRING))
		{
			System.out.println(">>>>>>>>>>>>>>ROOT pending");
			this.graph.setError(AlgorithmOutCodes.ROOT_NODE_PENDING);
			return null;
		}
		
		System.out.println("start(): " + rootId  + " root node");
		GraphNode rnode = qex.executeQueryNodeLabel(rootId, 0);
		
		System.out.println(">>>>>>>>>>>>>>RNODE ID:" + rootId);
		
		if(rnode.getId().startsWith("NODE_NOT_FOUND_"))
		{

			System.out.println(ServerConfigListener.getProperty("neo4j.url"));
			System.out.println(ServerConfigListener.getProperty("neo4j.user"));
			System.out.println(ServerConfigListener.getProperty("neo4j.pass"));
			
			//we have not found the node, stop and return error.
			this.graph.setError(AlgorithmOutCodes.ROOT_NODE_NOT_FOUND);
			return null;
		}
		
		graph.addNode(rnode);
		return rnode;
	}


	public LinkedList<Parameter> getParameters() {
		return parameters;
	}


	public void addParameter(Parameter par) {
		this.parameters.add(par);
	}


	public void setParameters(LinkedList<Parameter> parameters) {
		this.parameters = parameters;
	}



	public String getRootUri() {
		return rootUri;
	}



	public void setRootUri(String rootUri) {
		this.rootUri = rootUri;
	}
	

}
