package co.persistencia.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import co.persistencia.entity.Camion;

public class CamionDAO {
	
	/**
	 * Agrega un registro a la base de datos
	 * (INSERT INTO... ).
	 * @param camion Registro a agregar a la base de datos
	 * @param session 
	 */
	public void insert(Camion camion, Session session) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction( );
			session.save( camion );
			transaction.commit( );
		}catch (Exception e) {
			e.printStackTrace( );
		}
	}
	
	/**
	 * Obtiene el camion con ID especificado
	 * @param id ID coincidente
	 * @param session
	 * @return Camion con el ID, o nulo si no se encuentra
	 */
	public  Camion  getById( int id, Session session){
		Camion camion = null;
		try {
			camion =(Camion) session.get( Camion.class, id );
		}catch (Exception e) {
			e.printStackTrace( );
		}
		return camion;
	}
	
	/**
	 * Devuelve todos los camiones con matricula coincidente
	 * @param matricula
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Camion> getByMatricula( String matricula, Session session ){
		Criteria criteria = null;
		List<Camion> list = null;
		try{
			criteria = session.createCriteria( Camion.class );
			//Este es el filtro
			criteria.add( Restrictions.eq( "matricula", matricula ) );
			list = criteria.list( );
		} catch (Exception ex ){
			ex.printStackTrace( );
		}
		
		return list;
	}
	
	/**
	 * Devuelve todos los camiones con modelo coincidente
	 * @param modelo
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Camion> getByModelo( double modelo, Session session ){
		Criteria criteria = null;
		List<Camion> list = null;
		try{
			criteria = session.createCriteria( Camion.class );
			//Este es el filtro
			criteria.add( Restrictions.eq( "modelo", modelo ) );
			list = criteria.list( );
		} catch (Exception ex ){
			ex.printStackTrace( );
		}
		
		return list;
	}
	
	/**
	 * Devuelve todos los camiones con potencia coincidente
	 * @param potencia
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Camion> getByPotencia( double potencia, Session session ){
		Criteria criteria = null;
		List<Camion> list = null;
		try{
			criteria = session.createCriteria( Camion.class );
			//Este es el filtro
			criteria.add( Restrictions.eq( "potencia", potencia ) );
			list = criteria.list( );
		} catch (Exception ex ){
			ex.printStackTrace( );
		}
		
		return list;
	}
	
	//Obtener todos los registros
	
	/**
	 * Devuelve todos los registros de camiones
	 * (SELECT * FROM... )
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Camion> getAll(  Session session ){
		Criteria criteria = null;
		List<Camion> list = null;
		try {
			criteria = session.createCriteria( Camion.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
			list = criteria.list( );
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
	
	
	/**
	 * Actualiza en la base de datos los parametros del objeto camion.
	 * (UPDATE Camion SET ... )
	 * @param camion Objeto con los nuevos datos a almacenar en la base
	 * @param session
	 */
   public void  update( Camion camion, Session session){
	   Transaction transaction = null;
	   try {
		   transaction = session.beginTransaction( );
		   session.update( camion );
		   transaction.commit( );
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
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
   


	/**
	 * Elimina un registro de la base de datos
	 * @param camion Objeto que contiene el ID del registro a eliminar.
	 * @param session
	 */
   	public void  delete( Camion camion, Session session ){
	   Transaction transaction = null;
	   try {
		   transaction = session.beginTransaction( );
		   session.delete( camion );
		   transaction.commit( );
	   } catch (Exception e) {
		   e.printStackTrace( );
	   }
   }
}




