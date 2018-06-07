package oeg.upm.isantana.ldvserver.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;

import oeg.upm.isantana.ldvserver.ServerConfigListener;
import oeg.upm.isantana.ldvserver.algorithm.gui.parameter.DoubleSliderParameter;
import oeg.upm.isantana.ldvserver.algorithm.gui.parameter.IntegerSliderParameter;
import oeg.upm.isantana.ldvserver.algorithm.utils.AlgorithmOutCodes;
import oeg.upm.isantana.ldvserver.algorithm.utils.NodeLabels;
import oeg.upm.isantana.ldvserver.graph.GraphEdge;
import oeg.upm.isantana.ldvserver.graph.GraphEdgeFactory;
import oeg.upm.isantana.ldvserver.graph.GraphNode;
import oeg.upm.isantana.ldvserver.graph.GraphNodeFactory;

public class TopKAlg  extends Algorithm{
	
	private int kTop;
	private int maxDepth;
	private Queue<GraphNode> queue;

	public TopKAlg(String aname, String gname, String rt, int mx, int k, int md) {
		super(aname, gname, rt, mx);
		this.kTop = k;
		this.maxDepth = md;
		this.queue = new LinkedList<GraphNode>();

		this.parameters.add(new IntegerSliderParameter(0, 4, 3, 1, "topk", "K"));
		this.parameters.add(new IntegerSliderParameter(0, 5, 3, 1, "maxdepth", "Max depth"));

	}

	public TopKAlg(String aname, int mx, HttpServletRequest request) {
		super(aname, mx, request);
		this.queue = new LinkedList<GraphNode>();
		
		Integer defaultKTop = Integer.valueOf(ServerConfigListener.getProperty("default.par.topk1.topk"));
		Integer defaultMaxDepth = Integer.valueOf(ServerConfigListener.getProperty("default.par.topk1.maxdepth"));

		if(request.getParameter("topk")!=null)
			this.kTop = Integer.valueOf(request.getParameter("topk"));
		else
			this.kTop = defaultKTop;
		 
		if(request.getParameter("maxdepth")!=null)
			this.maxDepth = Integer.valueOf(request.getParameter("maxdepth"));
		else
			this.maxDepth = defaultMaxDepth;
		
		
		this.parameters.add(new IntegerSliderParameter(0, 4, this.kTop, 1, "topk", "Top K"));
		this.parameters.add(new IntegerSliderParameter(0, 5, this.maxDepth, 1, "maxdepth", "Max depth"));

	}

	@Override
	public boolean start() {

		//add root node
		GraphNode rn = this.addRootnode();
		if( rn == null)
			return false;
		else
			queue.add(rn);
		
		
		while(!queue.isEmpty())
		{
			
			if(this.graph.getNodes().size() >= this.maxNodes)
			{
				//we have exceeded the max amount of nodes, stop and return error.
				this.graph.setError(AlgorithmOutCodes.MAX_NODES_EXCEED);
				return false;
			}
			
			GraphNode cn = queue.poll();
			
			if(cn.getDepth() >= this.maxDepth)
			{
				//STOP CONDITION
				//if we reach max depth, we do not go any further in this branch
				continue;
			}
			
			System.out.println("\n\n");
			StatementResult cursor = qex.executeQuerySortedProps(cn, kTop);
			
			
			System.out.println("Top-" + this.kTop + " of: " + cn.getId() + " at depth " + cn.getDepth());
			while(cursor.hasNext())
			{
				Record rec = cursor.next();
				
				GraphNode neig = GraphNodeFactory.getCursorNode(rec, cn.getDepth()+1);
				//check if the node is already added
				if(!this.graph.containsNode(neig))
				{
					System.out.println(">>"+neig.getId());
					//add new node
					this.graph.addNode(neig);
					queue.add(neig);
				}
				else 
				{
					System.out.println("Found bucle: " + neig.getId() + " at depth " + neig.getDepth());
				}
				

				
				GraphEdge ge = GraphEdgeFactory.getCursorEdge(rec, neig, cn);
				if(!graph.containsEdge(ge))
				{
					//add new edge
					this.graph.addEdge(ge);	
				}
				
			}
			
			
		}

		return true;
	}

}
