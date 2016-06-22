package co.persistencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import co.persistencia.Camion.Camion;
import org.hibernate.service.ServiceRegistry;

import org.hibernate.Query;

public class Principal {
	
	
	public static void main(String [] args){
		
		SessionFactory sessionFactory;
		//Para que utilize el archivo de configuracion
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		//mETODO save guarda datos para la base de datos
		Session session = sessionFactory.openSession();
	
		//Insertar un registro
		Camion camion = new Camion("ABC123",2.0,"El tipo de camion",100.5);
		
		
		//session.beginTransaction();
		//session.save(camion);
		//session.getTransaction().commit();
		//Obtener 
		//Camion camion2 =(Camion) session.get(Camion.class,1);//Trae el atributo 5 del query
		
		System.out.println("ID"+ camion.getId());
		System.out.println("MATRICULA"+ camion.getMatricula());
		System.out.println("POTENCIA"+ camion.getPotencia());
		//Para las Llaves anidadas
		//List <Camion> camiones =(List<Camion>)session.createQuery("FROM camion");
		
		List <Camion> camiones =
				(List<Camion>)session.createCriteria(Camion.class)
				.setMaxResults(2) //Para obtener los maximos resultados
				.list();
		int i =0;
		for (Camion camion3 :camiones){
		i++;
		
		System.out.println(i +".ID"+camion3.getId());
		System.out.println(i+".MATRICULA"+camion3.getMatricula());
		}
		//Actualizar con HQL
		//parametros se indica con : para mayor numeros de parametros
		
		session.beginTransaction();
		String hql  ="UPDATE Camion set matricula =:matricula WHERE id=:id";
		Query query = session.createQuery(hql);
		//cLAVE ,VALOR .PARAMETRO,VALOR
		query.setParameter("matricula", "TYUEP");
		query.setParameter("id", 5);
		query.executeUpdate();
		session.getTransaction().commit();
		
		
		//Actualizar Objetos para parametros especificos
		session.beginTransaction();
		Camion camion4 = (Camion) session.get(Camion.class,6);
		camion4.setMatricula("POTUI");
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

	
	


