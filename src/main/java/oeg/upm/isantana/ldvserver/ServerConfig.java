package oeg.upm.isantana.ldvserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ServerConfig {
	
	Properties prop;
	Logger log = LogManager.getLogger(ServerConfig.class);

	
	public ServerConfig(String path)
	{

		org.apache.log4j.BasicConfigurator.configure();

		
		prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(path);

			// load a properties file
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				System.out.println("Prop "+key + "=" + value);
			}
			
//			read complex properties
//			https://stackoverflow.com/questions/1432481/multiple-values-in-java-util-properties
			
		} catch (IOException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getProperty(String key)
	{
		return prop.getProperty(key);
	}

}	
