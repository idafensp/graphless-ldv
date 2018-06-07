package oeg.upm.isantana.ldvserver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerConfigListener  implements ServletContextListener {

	static ServerConfig sc;
	
	public ServerConfigListener()
	{
		String confPath = System.getenv("LDV_SERVER_CONF");
		
		System.out.println("LDSERVER - Configuration path file=" + confPath);

		sc = new ServerConfig(confPath);
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static String getProperty(String key)
	{
		return sc.getProperty(key);
	}

}
