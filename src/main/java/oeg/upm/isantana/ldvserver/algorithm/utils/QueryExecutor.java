package oeg.upm.isantana.ldvserver.algorithm.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;

import oeg.upm.isantana.ldvserver.graph.GraphNode;

public class QueryExecutor {
	
	private String graphName;
	private Driver neodriver;
	private Session session;

	public QueryExecutor(String gname, String uri, String user, String pass)
	{
		if(gname!=null)
			this.graphName = gname;
		else
			this.graphName = "NO_GRAPH";
		
		this.neodriver = GraphDatabase.driver(uri, AuthTokens.basic(user, pass));
		session = this.neodriver.session();
	}
	
	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}
	
	
	//https://neo4j.com/docs/developer-manual/current/drivers/sessions-transactions/
	public StatementResult executeQueryNeighbours(GraphNode node, double weight)
	{
		
		
		String query =  "MATCH (n:@@GRAPHNAME@@)-[r:@@GRAPHNAME@@]->(x:@@GRAPHNAME@@) "
				 	  + "WHERE n.nodeid = '@@NODEID@@' "
				      + "AND r.weight > @@WEIGHT@@ "
			     	  + "RETURN n.label, r.weight, r.label, r.propid, r.dir, x.nodeid, x.label, x.value, x.uri, x.types, x.indeg, x.outdeg";

		query = query.replace("@@GRAPHNAME@@", this.graphName)
				.replace("@@NODEID@@", node.getId())
				.replace("@@WEIGHT@@", String.valueOf(weight));
		
		System.out.println("[QEX] - Query: " + query);

		StatementResult cursor = session.run(query);
		return cursor;
	}
	
	public GraphNode executeQueryNodeLabel(String nid, int depth)
	{
		String query =  "MATCH (n:@@GRAPHNAME@@) "
			 	  + "WHERE n.nodeid = '@@NODEID@@' "
		     	  + "RETURN n.label, n.uri";
		
		
		query = query.replace("@@GRAPHNAME@@", this.graphName)
				.replace("@@NODEID@@", nid);
		

		System.out.println("[QEX-NL] - Query: " + query);
		
		StatementResult cursor = session.run(query);

		if(cursor.hasNext())
		{
			Record r = cursor.single();
			String lab = r.get("n.label", NodeLabels.DEFAULT_LABEL);
			String uri = r.get("n.uri").asString();
			lab = NodeLabels.getNodeLabel(lab, uri);
			
			return new GraphNode(nid, uri, depth, lab);
		}
		else
		{
			System.out.println(">> Node not found");
		}
		

		return new GraphNode("NODE_NOT_FOUND_" +  nid);
	}
	
	public StatementResult executeQuerySortedProps(GraphNode node, int topk)
	{
		
		//TODO return all properties sorted, or look for a top K in neo4j
		//TODO what to to with props with same weight
		String query =  "MATCH (n:@@GRAPHNAME@@)-[r:@@GRAPHNAME@@]->(x:@@GRAPHNAME@@) "
				 	  + "WHERE n.nodeid = '@@NODEID@@' "
			     	  + "RETURN n.label, r.weight, r.label, r.propid, r.dir, x.nodeid, x.label, x.value, x.uri, x.types, x.indeg, x.outdeg "
				 	  + "ORDER BY r.weight DESC "
			     	  + "LIMIT @@TOPK@@";

		query = query.replace("@@GRAPHNAME@@", this.graphName)
				.replace("@@NODEID@@", node.getId())
				.replace("@@TOPK@@", String.valueOf(topk));
		
		System.out.println("[QEX] - Query: " + query);

		StatementResult cursor = session.run(query);
		return cursor;
	}

}
