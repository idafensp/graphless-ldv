package oeg.upm.isantana.ldvserver;

import oeg.upm.isantana.ldvserver.algorithm.ThresholdWeightAlg;
import oeg.upm.isantana.ldvserver.algorithm.utils.QueryExecutor;
import oeg.upm.isantana.ldvserver.graph.GraphNode;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		QueryExecutor qe = new QueryExecutor("PROPT", "bolt://localhost:7687","neo4j","12345");

		//GraphNode gn = new GraphNode("node_0a75fda892bf34a5eb1f17b86c0268e8e6e02ccf7ddd950f3e0810fc1ef9ab26", 0);
		
		//qe.executeQueryNeighbours(gn, 0.4);
		
		//	public ThresholdWeightAlg(String aname, String gname, String rt, double thr, double dec, QueryExecutor qe) {

		//ThresholdWeightAlg twa = new ThresholdWeightAlg("TWA", "PEELP", 
		//							"node_0a75fda892bf34a5eb1f17b86c0268e8e6e02ccf7ddd950f3e0810fc1ef9ab26", 0.7, 0.1, qe);
		
		
		//ThresholdWeightAlg twa = new ThresholdWeightAlg("TWA", "PROPT", 
		//		"node_b9a34de0af63e3e30744e7a74854c92f85487193e11e38342cc62b95e45e4540", 0.8, 0.1, qe, 100);
		
		
		//twa.start();
		
		//twa.getGraph().print();
	}

}
