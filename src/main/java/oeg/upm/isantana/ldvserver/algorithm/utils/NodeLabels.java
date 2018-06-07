package oeg.upm.isantana.ldvserver.algorithm.utils;

public class NodeLabels {

	public static final String DEFAULT_LABEL = "....";
	
	public static String getNodeLabel(String label, String uri)
	{
		String reslabel = DEFAULT_LABEL;
		
		System.out.print("Checking " + uri + "- label " + label);
		if(label.equals(DEFAULT_LABEL))
		{
			if (uri.indexOf("/") > 0) {
				uri = uri.substring(uri.lastIndexOf("/")+1, uri.length());
				System.out.print(" is / ");

				if (uri.indexOf("#") > 0) {
					uri = uri.substring(uri.lastIndexOf("#")+1);
					System.out.print(" is # ");
				}	
				
				if(uri.length()>1)
				{	
					System.out.println(uri);
					reslabel= uri;
				}
				else
				{
					System.out.print(" not long ");
					int len = uri.length()-15;
					if(len < 0)
					{
						len = 0;
					}
					System.out.println("..."+uri.toString().substring(len));
					reslabel= "..."+uri.toString().substring(len);
				}
			}
			else 
			{
				if (uri.indexOf("#") > 0) {
					uri = uri.substring(uri.lastIndexOf("#")+1);
					System.out.print("is # ");
					if(uri.length()>1)
					{
						System.out.println(uri);
						reslabel= uri;
					}
				}
				else
				{
					System.out.print(" is not long ");
					int len = uri.length()-15;
					if(len < 0)
					{
						len = 0;
					}
					System.out.println("..."+uri.toString().substring(len));
					reslabel= "..."+uri.toString().substring(len);
				}
			}
		}
		else
		{
			reslabel = label;
		}
		
		System.out.println("returning label:" + reslabel);
		return reslabel;
	}
}
