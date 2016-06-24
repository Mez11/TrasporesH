package co.persistencia.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	//private static ServiceRegistry serviceRegistry;
	
	static{
		try{
			Configuration configuration = new Configuration();
		    configuration.configure();
//		    serviceRegistry = new StandardServiceRegistryBuilder( ).applySettings( 
//		    			configuration.getProperties( ) 
//		    		).build();
		    sessionFactory = configuration.buildSessionFactory( );
		}catch( Exception th ){
			th.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void shutdown() {
		getSessionFactory( ).close();
	}

}
