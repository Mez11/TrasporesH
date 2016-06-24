package co.persistencia.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import co.persistencia.entity.Camion;
import co.persistencia.util.HibernateUtil;

public class CamionDAO {
	public void insertar (Camion camion, Session session){
		try {
			session.beginTransaction();
			session.save(camion);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			// TODO: handle exception
			System.err.println("***ERROR AL INSERTAR CAMION****");
			he.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace( );
		}
	}	
	
	public  Camion  getById( int id, Session session){
		Camion camion= null;
		try {
			camion =(Camion) session.get(Camion.class,id);
			
			
		} catch (HibernateException he) {
			// TODO: handle exception
			
			System.err.println("***ERROR AL INSERTAR CAMION****");
			he.printStackTrace();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace( );
		}
		return camion;
	}

	//Obtener todos los registros
	
	@SuppressWarnings("unchecked")
	public List<Camion> getAll(  Session session ){
		Criteria criteria = null;
		List<Camion> list = null;
		try {
			criteria = session.createCriteria( Camion.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
			list = criteria.list( );
		} catch (HibernateException he) {
			System.err.println("***ERROR AL INSERTAR CAMION****");
			he.printStackTrace( );
		}catch (Exception e) {
			e.printStackTrace( );
		}
		
		return list;
	}
	
	public List<Camion> obtenerRegistrosParametros(HashMap<String, Object> parametros,Session session){
		String hql ="SELECT * FROM Camion Where";
		List <Camion> camiones = null;
		
		try {
			String parametrosQuery ="";
			int i =1;
			
			for(Map.Entry<String, Object> parametro: parametros.entrySet()){
				parametrosQuery += parametro.getKey() + "= :" +parametro.getKey();
				
			if(i < parametros.size()){
				
				parametrosQuery += "AND";
				
			}
			i++;
			}
	Query query = session.createQuery(hql +parametrosQuery);
	
	for(Map.Entry<String, Object> parametro: parametros.entrySet()){
		
		query.setParameter(parametro.getKey(), parametro.getValue());
	}
	session.beginTransaction();
	camiones = query.list();
	session.getTransaction().commit();
	
    }
		catch (HibernateException he) {
			// TODO: handle exception
			System.err.println("***ERROR DE REGISTROS DE PARAMETROS***");
			he.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return camiones;
		
	}
	
	
	//Query query = session.createQuery(hql);
//Creacion de Hash Map es decir clave(columna),valor(dato) 
	
   public void  actualizar( Camion camion, Session session){
	 
	   try {
		   Camion camion2 = (Camion) session.get(Camion.class,camion.getId());
		   camion2.setMatricula(camion.getMatricula());
		   camion2.setTipo (camion.getTipo());
		   camion2.setModelo(camion.getModelo());
		   
		   session.beginTransaction();
		   session.update(camion2);
		   session.getTransaction().commit();
		
	} catch (HibernateException he) {
		System.err.println("***Error al obtener los registros de los parametros**");
		he.printStackTrace();
		// TODO: handle exception
	}catch (Exception e) {
		// TODO: handle exception
	}
	//return rows_afectados;
   }
   
   
   public int actualizar(HashMap<String, Object> parametros,HashMap<String,Object> filtros, Session session){
	   int rows_afectados= 0;
	   
	   String hql="UPDATE Camion SET";
	   try {
		   
		   String parametrosQuerySET ="";
		   int i = 1;
		   for(Map.Entry<String, Object> parametro: parametros.entrySet()){
			   parametrosQuerySET += parametro.getKey() + "= :" + parametro.getKey();
			   
			   if(i < parametros.size()){
				   parametrosQuerySET += ", ";
			   }
			   i++;
		   }
		 
	   
   
	   String parametrosQueryWHERE = " WHERE ";
	   int j = 1;
	   for(Map.Entry<String, Object> filtro :filtros.entrySet()){
		   parametrosQueryWHERE += filtro.getKey() + "= :" + filtro.getKey();
		   if(j < filtros.size()){
			   
			   parametrosQueryWHERE += " AND ";
		   }
		   j++;
	   }
	   Query query = session.createQuery(hql + parametrosQuerySET + parametrosQueryWHERE);
	   for(Map.Entry<String, Object> parametro:parametros.entrySet()){
		   query.setParameter(parametro.getKey(), parametro.getValue());
		
	} 
	  for(Map.Entry<String, Object> filtro: filtros.entrySet()){
		   query.setParameter(filtro.getKey(),filtro.getValue());
	}
	  session.beginTransaction();
	  rows_afectados = query.executeUpdate();
	  session.getTransaction().commit();
	}
	  
   catch (HibernateException he) {
		// TODO: handle exception
	   System.err.println("*** ERROR AL ACTUALIZAR ṔOR PARAMETROS ***");
	   he.printStackTrace();
   }catch(Exception e){
	
   }
   return rows_afectados;
}
   


	
   public void  eliminar(Camion camion, Session session){
	   try {
		Camion camion2 = (Camion) session.get(Camion.class,camion.getId() );
		
		session.beginTransaction();
		session.delete(camion2);
		session.getTransaction().commit();
		
		
	} catch (HibernateException he) {
		// TODO: handle exception
		System.err.println("**ERROR AL ELIMINAR REGISTROS DEL OBJETO**");
		he.printStackTrace();
	}
	   catch (Exception e) {
		// TODO: handle exception
	}
	   //int rows_afectados=0;
	   //return rows_afectados;
   }
   
   public int eliminar(HashMap<Object, Object> parametros){
	   int rows_afectados=0;
	   return rows_afectados;
	  }
   }




