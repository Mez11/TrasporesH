package co.persistencia.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import co.persistencia.entity.Camion;

public class Principal {
	
	
	public static void main(String [] args){
		
		SessionFactory sessionFactory;
		//Para que utilize el archivo de configuracion
		Configuration configuration = new Configuration();
		configuration.configure( );
		sessionFactory = configuration.buildSessionFactory();
		//mETODO save guarda datos para la base de datos
		Session session = sessionFactory.openSession();
	
		//Insertar un registro
		Camion camion = new Camion("ABC123",2.0,"El tipo de camion",100.5);
		camion.setMatricula( "abc" );
		camion.setTipo( "Grande" );
		//Abrir conexion @.@ PreparentS
		session.beginTransaction( );
		//Guarda el registro es decir el obj
		session.save(camion);
		//Escribir cambio en la BD
		session.getTransaction().commit();
		
		//Obtener el camion con ID 1
		Camion camion2 =(Camion) session.get( Camion.class, 1 );
		
		System.out.println("ID: "+ camion2.getId());
		System.out.println("MATRICULA: " + camion2.getMatricula());
		System.out.println("POTENCIA: " + camion2.getPotencia());
		
//		camion2.setPotencia( 2.2 );
//		
//		session.update( camion2 );
//		
		//Para las Llaves anidadas
		//List <Camion> camiones =(List<Camion>)session.createQuery("FROM camion");
		
		
		@SuppressWarnings("unchecked")
		List <Camion> camiones =
				(List<Camion>)session.createCriteria(Camion.class)
				.setMaxResults(2) //Para obtener los maximos resultados
				.list( );
		int i =0;
		
		for (Camion camion3 :camiones){
			i++;
			System.out.println(i +".ID: "+camion3.getId());
			System.out.println(i+".MATRICULA: "+camion3.getMatricula());
		}
		
		//Actualizar con HQL
		//parametros se indica con : para mayor numeros de parametros
		session.beginTransaction( );
		String hql  ="UPDATE Camion set matricula =:matricula WHERE id=:id";
		Query query = session.createQuery(hql);
		//cLAVE ,VALOR .PARAMETRO,VALOR
		query.setParameter("matricula", "TYUEP");
		query.setParameter("id", 6);
		query.executeUpdate();
		session.getTransaction().commit();
		
		
		//Actualizar Objetos para parametros especificos
		session.beginTransaction();
		Camion camion4 = (Camion) session.get( Camion.class, 6 );
		if( camion4 == null ){
			System.out.println( "El camion con ID 6 no existe." );
		}
		else{
			camion4.setMatricula("POTUI");
		}
		session.getTransaction().commit();
		
		//ELiminar con obj
		/*session.beginTransaction();
		Camion camion5 =(Camion) session.get(Camion.class,4);
		session.delete(camion5);
		session.getTransaction().commit();*/
		
		//ELiminar con HQL
		session.beginTransaction();
		hql ="DELETE  FROM Camion Where  id =:id";
		query = session.createQuery(hql);
		query.setParameter("id", 5);
		query.executeUpdate();
		session.getTransaction().commit();
		 
		
		//Cerrar Sessiones
		session.close();
		sessionFactory.close();
		
	}
} //end all 
