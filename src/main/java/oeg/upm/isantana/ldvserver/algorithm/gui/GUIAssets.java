package oeg.upm.isantana.ldvserver.algorithm.gui;

import java.util.List;
import oeg.upm.isantana.ldvserver.algorithm.gui.parameter.Parameter;;

public class GUIAssets {
	
	public static String getParametersHtml(List<Parameter> pars)
	{
		StringBuilder str = new StringBuilder();

		
		for(Parameter par : pars)
		{
			str.append(par.toHtmlString());
			str.append("\n\n");
		}
	    
	    
	    return str.toString();
		
	}
	
}
