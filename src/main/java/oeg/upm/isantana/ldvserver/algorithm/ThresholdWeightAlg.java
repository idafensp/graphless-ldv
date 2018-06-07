package oeg.upm.isantana.ldvserver.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import oeg.upm.isantana.ldvserver.ServerConfigListener;
import oeg.upm.isantana.ldvserver.algorithm.gui.parameter.DoubleSliderParameter;
import oeg.upm.isantana.ldvserver.algorithm.gui.parameter.Parameter;
import oeg.upm.isantana.ldvserver.algorithm.utils.AlgorithmOutCodes;
import oeg.upm.isantana.ldvserver.algorithm.utils.NodeLabels;
import oeg.upm.isantana.ldvserver.algorithm.utils.QueryExecutor;
import oeg.upm.isantana.ldvserver.graph.GraphEdge;
import oeg.upm.isantana.ldvserver.graph.GraphEdgeFactory;
import oeg.upm.isantana.ldvserver.graph.GraphNode;
import oeg.upm.isantana.ldvserver.graph.GraphNodeFactory;

public class ThresholdWeightAlg extends Algorithm {

	private double threshold;
	private double decay;
	private Queue<GraphNode> queue;
	private final double maxThreshold = 1.0;
	
	
	public ThresholdWeightAlg(String aname, String gname, String rt, double thr, double dec, QueryExecutor qe, int mx) {
		super(aname, gname, rt, mx);
		this.threshold = thr;
		this.decay = dec;
		this.queue = new LinkedList<GraphNode>();
		this.qex = qe;

		//Add the required parameters to be shown, with the default values
		this.parameters.add(new DoubleSliderParameter(0, 1, thr, 0.05, "threshold", "Threshold"));
		this.parameters.add(new DoubleSliderParameter(0, 1, dec, 0.05, "decay", "Decay"));
		
	}
	
	
//	public ThresholdWeightAlg(String aname, QueryExecutor qe, int mx, HttpServletRequest request) {
//		super(aname, mx, request);
//		this.queue = new LinkedList<GraphNode>();
//		this.qex = qe;
//
//
//		this.threshold = Double.valueOf(request.getParameter("threshold"));
//		this.decay = Double.valueOf(request.getParameter("decay"));
//		
//		//Add the required parameters to be shown, with the default values
//		this.parameters.add(new DoubleSliderParameter(0, 1, this.threshold, 0.05, "threshold", "Threshold"));
//		this.parameters.add(new DoubleSliderParameter(0, 1, this.decay, 0.05, "decay", "Decay"));
//				
//	}
	
	
	public ThresholdWeightAlg(String aname, int mx, HttpServletRequest request) {
		super(aname, mx, request);
		this.queue = new LinkedList<GraphNode>();
		

		Double defaultThresh = Double.valueOf(ServerConfigListener.getProperty("default.par.twa1.threshold"));
		Double defaultDecay = Double.valueOf(ServerConfigListener.getProperty("default.par.twa1.decay"));
		 
		if(request.getParameter("threshold")!=null)
			this.threshold = Double.valueOf(request.getParameter("threshold"));
		else
			this.threshold = defaultThresh;
		 
		if(request.getParameter("decay")!=null)
			this.decay = Double.valueOf(request.getParameter("decay"));
		else
			this.decay = defaultDecay;
		
		//Add the required parameters to be shown, with the default values
		this.parameters.add(new DoubleSliderParameter(0, 1, this.threshold, 0.05, "threshold", "Threshold"));
		this.parameters.add(new DoubleSliderParameter(0, 1, this.decay, 0.05, "decay", "Decay"));
				
	}

	//http://www.techiedelight.com/breadth-first-search/
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
			System.out.println("\n\n");
			if(this.getThreshold(cn.getDepth()) > this.maxThreshold)
			{
				//we will not find more nodes, due to normalization
				continue;
			}
			
			StatementResult cursor = qex.executeQueryNeighbours(cn, this.getThreshold(cn.getDepth()));
			
			System.out.println("Neig("+this.getThreshold(cn.getDepth())+") of: " + cn.getId());
			while(cursor.hasNext())
			{
				Record rec = cursor.next();
//				String neigid = rec.get("x.nodeid").asString();
//				String propid = rec.get("r.propid").asString();
//				
//				String proplab = rec.get("r.label").asString();
//				String cnlab = rec.get("x.label").asString();
//				String neiguri = rec.get("x.uri").asString();
//				String neigtypes = rec.get("x.types").asString();
//				int neigind = rec.get("x.indeg").asInt();
//				int neigoutd = rec.get("x.outdeg").asInt();
//				
//
//				String neiglab = rec.get("x.label", NodeLabels.DEFAULT_LABEL);
//				neiglab = NodeLabels.getNodeLabel(neiglab, neiguri);
//				
//				
//				GraphNode neig = new GraphNode(neigid, neiguri, cn.getDepth()+1, neiglab);
//				neig.setIndegree(neigind);
//				neig.setOutdegree(neigoutd);
//				
//				//TODO parse string to list
//				String[] typeArray = neigtypes.split(",");
//				ArrayList<String> typeList= new ArrayList<String>();
//				for(int i = 0; i<typeArray.length; i++)
//				{
//					typeList.add(typeArray[i]);
//				}
//				
//				neig.setTypes(typeList);
//				
//				//TODO find a better way of checking literal
//				if(!rec.get("x.value", "NOT_FOUND").equals("NOT_FOUND"))
//				{
//					System.out.println("FOUND LIT >>>>>>>***" + rec.get("x.value").asString());
//					String litVal = rec.get("x.value").asString();
//					neig.setLiteral(true, litVal);
//				}
//				
//				
				
				
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
				
//				Double pweight = rec.get("r.weight", this.DEFAULT_WIEHGT);
//				String propid = rec.get("r.propid").asString();				
//				String proplab = rec.get("r.label").asString();
//
//				//create edge -> check direction!
//				String propdir = rec.get("r.dir").asString();
//				GraphEdge ge = null;
//				if(propdir.equals("inverse"))
//				{
//					//inverse: from neighbour to current
//					ge = new GraphEdge(propid, neig.getId(),cn.getId(), pweight, proplab);
//				}
//				else
//				{ 
//					//direct: from current to neighbour
//					ge = new GraphEdge(propid, cn.getId(),neig.getId(),pweight, proplab);
//				}
				
				//check if the edge already exists
				if(!graph.containsEdge(ge))
				{
					//add new edge
					this.graph.addEdge(ge);	
				}
				
			}
			
			
		}

		return true;
	}
	

	private double getThreshold(int d)
	{
		return this.threshold + (d * this.decay);
	}

}
