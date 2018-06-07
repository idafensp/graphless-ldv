package oeg.upm.isantana.ldvserver.gui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class AlgorithmSelector {
	
	private Map<String, String> graphs;
	
	public AlgorithmSelector()
	{
		this.graphs = new LinkedHashMap<String, String>();
		this.graphs.put(GUIConstants.ALG_TWA_1, "Threshold");
		this.graphs.put(GUIConstants.ALG_TOP_K_1, "Top K");
	}
	
	public String printOptions(String selected)
	{
		StringBuilder builder = new StringBuilder();
		
		Iterator it = this.graphs.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        String sel = "";
	        if(pair.getKey().equals(selected))
	        	sel=" selected";
	        	
	        builder.append("<option value=\""+pair.getKey()+"\" "+sel+">"+pair.getValue()+"</option>\n");
	    }
		
        return builder.toString();
	}
	
	public String getGraphLabel(String key)
	{
		System.out.println("Got for key " + key + " label " + this.graphs.get(key));
		if(this.graphs.containsKey(key))
			return this.graphs.get(key);
		
		return "GRAPH_KEY_NOT_FOUND";
	}

}
