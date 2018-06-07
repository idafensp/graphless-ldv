package oeg.upm.isantana.ldvserver.graph;

import org.neo4j.driver.v1.Record;

public class GraphEdgeFactory {
	
	protected static final double DEFAULT_WIEHGT = -2.0;

	public static GraphEdge getCursorEdge(Record rec, GraphNode fromNode, GraphNode toNode)
	{

		Double pweight = rec.get("r.weight", DEFAULT_WIEHGT);
		String propid = rec.get("r.propid").asString();				
		String proplab = rec.get("r.label").asString();

		
		//create edge -> check direction!
		String propdir = rec.get("r.dir").asString();
		GraphEdge ge = null;
		if(propdir.equals("inverse"))
		{
			//inverse: from neighbour to current
			ge = new GraphEdge(propid, fromNode.getId(),toNode.getId(), pweight, proplab);
		}
		else
		{ 
			//direct: from current to neighbour
			ge = new GraphEdge(propid, toNode.getId(),fromNode.getId(),pweight, proplab);
		}
		
		return ge;
	}

}
