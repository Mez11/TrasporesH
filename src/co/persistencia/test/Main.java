package co.persistencia.test;

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
		printList( camiones );
	}
	
	private static void printList( List<Camion> camiones ){
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
		System.out.println( "********************" );
		System.out.println( "Id del camion: " + camion.getId( ) );
		System.out.println( "Matricula del camion: " + camion.getMatricula( ) );
		System.out.println( "Modelo del camion: " + camion.getModelo( ) );
		System.out.println( "Tipo del camion: " + camion.getTipo( ) );
		System.out.println( "Potencia del camion: " + camion.getPotencia( ) );
		System.out.println( "********************" );
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
		System.out.println( "Ingrese la nueva información del camión" );
		//Antes de leer un string, usar "nextLine" para limpiar el buffer
		//Limpiar el buffer
		scanner.nextLine( );
		System.out.println( "Matricula [string]" );
		camion.setMatricula( scanner.nextLine( ) );
		System.out.println( "Modelo [double]");
		camion.setModelo( scanner.nextDouble( ) );
		System.out.println( "Potencia [double]" );
		camion.setPotencia( scanner.nextDouble( ) );
		//Limpiar buffer
		scanner.nextLine( );
		System.out.println( "Tipo [string] ");
		camion.setTipo( scanner.nextLine( ) );
		printCamionInfo( camion );
		new CamionDAO( ).update( camion,  session );
	}
	
	private static void deleteCamion( Scanner scanner ){
		Camion camion = askForId( scanner );
		System.out.println( "Eliminando el siguiente camion:" );
		printCamionInfo( camion );
		new CamionDAO( ).delete(camion, session);
	}
	
	private static int getSubOption( Scanner scanner ){
		System.out.println( "Que desea hacer?" );
		System.out.println( "1) Actualizar los registros" );
		System.out.println( "2) Eliminar los registros" );
		System.out.println( "3) Regresar al menú principal" );
		return scanner.nextInt( );
	}
	
	private static void changeList( List<Camion> camiones, Scanner scanner ){
		CamionDAO dao = null;
		String matricula = null;
		double modelo;
		double potencia;
		String tipo = null;
		
		System.out.println( "Ingrese la nueva información de los camiones" );
		//Antes de leer un string, usar "nextLine" para limpiar el buffer
		//Limpiar el buffer
		scanner.nextLine( );
		System.out.println( "Matricula [string]" );
		matricula =  scanner.nextLine( );
		System.out.println( "Modelo [double]");
		modelo = scanner.nextDouble( );
		System.out.println( "Potencia [double]" );
		potencia = scanner.nextDouble( );
		//Limpiar buffer
		scanner.nextLine( );
		System.out.println( "Tipo [string] ");
		tipo = scanner.nextLine( );
		
		dao = new CamionDAO( );
		for( Camion camion : camiones ){
			camion.setMatricula( matricula );
			camion.setModelo( modelo );
			camion.setPotencia( potencia );
			camion.setTipo( tipo );
			dao.update( camion, session );
		}
	}
	
	private static void deleteList( List<Camion> camiones, Scanner scanner ){
		CamionDAO dao = new CamionDAO();
		for( Camion camion : camiones ){
			dao.delete(camion, session);
		}
		System.out.println( "Camiones eliminados" );
	}
	
	private static void optByMatricula( Scanner scanner ){
		String matricula = null;
		List<Camion> camiones = null;
		int opt;
		System.out.println( "Ingrese la matricula por la cual filtrar" );
		//vaciar el buffer
		scanner.nextLine( );
		//obtener la opcion del usuario
		matricula = scanner.nextLine( );
		//obtener la lista
		camiones = new CamionDAO( ).getByMatricula( matricula, session );
		System.out.println( "Resultado" );
		printList( camiones );
		opt = getSubOption( scanner );
		switch( opt ){
		case 1:
			changeList( camiones, scanner);
			break;
		case 2:
			deleteList(camiones, scanner);
			break;
		}
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
			case 3:
				updateCamion( scanner );
				break;
			case 4:
				deleteCamion( scanner );
				break;
			case 5:
				optByMatricula( scanner );
				break;
			}
			System.out.println( );
		}
		
		
		scanner.close( );
		HibernateUtil.shutdown( );
	}

}
