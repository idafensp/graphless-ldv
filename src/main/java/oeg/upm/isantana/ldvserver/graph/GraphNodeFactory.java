package oeg.upm.isantana.ldvserver.graph;

import java.util.ArrayList;

import org.neo4j.driver.v1.Record;

import oeg.upm.isantana.ldvserver.algorithm.utils.NodeLabels;

public class GraphNodeFactory {
	
	public static GraphNode getCursorNode(Record rec, int depth)
	{

		String neigid = rec.get("x.nodeid").asString();
		String neiguri = rec.get("x.uri").asString();
		String neigtypes = rec.get("x.types").asString();
		int neigind = rec.get("x.indeg").asInt();
		int neigoutd = rec.get("x.outdeg").asInt();
		

		String neiglab = rec.get("x.label", NodeLabels.DEFAULT_LABEL);
		neiglab = NodeLabels.getNodeLabel(neiglab, neiguri);
		
		//Double pweight = rec.get("r.weight", this.DEFAULT_WIEHGT);

		//GraphNode neig = new GraphNode(neigid, neiguri, cn.getDepth()+1, neiglab);
		GraphNode neig = new GraphNode(neigid, neiguri, depth, neiglab);
		neig.setIndegree(neigind);
		neig.setOutdegree(neigoutd);
		
		//TODO parse string to list
		String[] typeArray = neigtypes.split(",");
		ArrayList<String> typeList= new ArrayList<String>();
		for(int i = 0; i<typeArray.length; i++)
		{
			typeList.add(typeArray[i]);
		}
		
		neig.setTypes(typeList);
		
		//TODO find a better way of checking literal
		if(!rec.get("x.value", "NOT_FOUND").equals("NOT_FOUND"))
		{
			System.out.println("FOUND LIT >>>>>>>***" + rec.get("x.value").asString());
			String litVal = rec.get("x.value").asString();
			neig.setLiteral(true, litVal);
		}
		
		
		return neig;
		
	}

}
