package co.persistencia.test;

import java.io.BufferedInputStream;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import co.persistencia.dao.CamionDAO;
import co.persistencia.entity.Camion;
import co.persistencia.util.HibernateUtil;

public class Main {
	
	private static Session session;
	
	private static void printList( ){
		List<Camion> camiones = new CamionDAO( ).getAll( session );
		System.out.println( "ID\tMatricula\tModelo\tTipo\tPotencia" );
		for( Camion camion : camiones ){
			System.out.print( camion.getId( ) );
			System.out.print( "\t" );
			System.out.print( camion.getMatricula( ) );
			System.out.print( "\t" );
			System.out.print( camion.getModelo( ) );
			System.out.print( "\t" );
			System.out.print( camion.getTipo( ) );
			System.out.print( "\t" );
			System.out.print( camion.getPotencia( ) );
			System.out.println( );
		}
		
	}
	
	private static void printOptions( ){
		System.out.println( "0) Salir" );
		System.out.println( "1) Mostrar listado de camiones" );
		System.out.println( "2) Mostrar datos de un camion" );
		System.out.println( "3) Editar un camion");
		System.out.println( "4) Eliminar un camion" );
		System.out.println( "5) Seleccionar camiones por matricula" );
		System.out.println( "6) Seleccionar camiones por modelo" );
		System.out.println( "7) Seleccionar camiones por potencia" );
	}
	
	private static void printCamionInfo( Camion camion ){
		System.out.println( "Id del camion: " + camion.getId( ) );
		System.out.println( "Matricula del camion: " + camion.getMatricula( ) );
		System.out.println( "Modelo del camion: " + camion.getModelo( ) );
		System.out.println( "Tipo del camion: " + camion.getTipo( ) );
		System.out.println( "Potencia del camion: " + camion.getPotencia( ) );
	}
	
	private static Camion askForId( Scanner scanner ){
		int id;
		System.out.println( "Ingrese el ID del camion deseado" );
		id = scanner.nextInt( );
		
		/*CamionDAO dao = new CamionDAO();
		Camion camion = dao.getById( id, session);
		return camion;*/
		
		return new CamionDAO( ).getById( id, session );
	}
	
	private static void printCamion( Scanner scanner ){
		Camion camion = askForId( scanner );
		
		if( camion == null ){
			System.out.println( "El camion no existe en la base de datos" );
		}
		else{			
			printCamionInfo( camion );
		}
	}
	
	
	
	private static void updateCamion( Scanner scanner ){
		Camion camion = askForId( scanner );
		
		if( camion == null ){
			System.out.println( "El camion no existe en la base de datos" );
			return;
		}
		
		printCamionInfo( camion );
		
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		int opc = 1;
		//abrir sesion
		System.out.println( "Abriendo sesion...");
		session = HibernateUtil.getSessionFactory().openSession( );
		
		while( opc != 0 ){
			printOptions( );
			opc = scanner.nextInt( );
			switch( opc ){
			case 1:
				printList( );
				break;
			case 2:
				printCamion( scanner );
				break;
			}
			System.out.println( );
		}
		
		
		scanner.close( );
		HibernateUtil.shutdown( );
	}

}
