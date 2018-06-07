package oeg.upm.isantana.ldvserver.algorithm.utils;

import javax.servlet.http.HttpServletRequest;

import oeg.upm.isantana.ldvserver.ServerConfigListener;
import oeg.upm.isantana.ldvserver.algorithm.Algorithm;
import oeg.upm.isantana.ldvserver.algorithm.ThresholdWeightAlg;
import oeg.upm.isantana.ldvserver.algorithm.TopKAlg;
import oeg.upm.isantana.ldvserver.gui.GUIConstants;

public class AlgorithmDipatcher {
	
	public static Algorithm dispathAlgorithm(int maxNodes,  HttpServletRequest request)
	{
	
		String algKey = ServerConfigListener.getProperty("default.algorithm");
		if(request.getParameter("algorid")!=null)
		{
			algKey = request.getParameter("algorid");
			System.out.println("algorid not null->" + algKey);
		}
		else
		{
			System.out.println("algorid IS null, default alg->" + algKey);
		}
		
		if(algKey.equals(GUIConstants.ALG_TWA_1))
		{
			return new ThresholdWeightAlg(GUIConstants.ALG_TWA_1, maxNodes, request);
		}
		if(algKey.equals(GUIConstants.ALG_TOP_K_1))
		{
			return new TopKAlg(GUIConstants.ALG_TOP_K_1, maxNodes, request);
		}
		
		return null;
	}

}
