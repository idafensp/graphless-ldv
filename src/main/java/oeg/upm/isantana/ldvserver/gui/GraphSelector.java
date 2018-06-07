package oeg.upm.isantana.ldvserver.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GraphSelector {
	
	private Map<String, String> graphs;
	
	public GraphSelector()
	{
		this.graphs = new LinkedHashMap<String, String>();
//		this.graphs.put("MUSIC", "MusicBrainz");
//		this.graphs.put("PROPS", "Mini Sample");
//		this.graphs.put("PEELP", "Movies");
		this.graphs.put("JARLABS", "Jamendo Labels");
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
	
	public String printGraphName(String selected)
	{
		Iterator it = this.graphs.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        String sel = "";
	        if(pair.getKey().equals(selected))
	        	return (String) pair.getValue();
	        	
	    }
        return "Graph not found";
	}
	
	public String getGraphLabel(String key)
	{
		System.out.println("Got for key " + key + " label " + this.graphs.get(key));
		if(this.graphs.containsKey(key))
			return this.graphs.get(key);
		
		return "GRAPH_KEY_NOT_FOUND";
	}

}
