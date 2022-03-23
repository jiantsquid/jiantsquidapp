package org.jiantsquid.jiantsquidapp.test;

import org.jiantsquid.core.database.DatabaseClient;
import org.jiantsquid.jiantsquidapp.database.SmartCode;

public class InitDataModel {

	public static final String SEARCH_ENGINE_SERVICE = "SEARCH_ENGINE" ;
	public static final String SEARCH_ENGINE_SERVICE_CODE = "import javax.swing.JTextField; public class MainClass extends javax.swing.JPanel "
			+ "{  public MainClass() { JTextField field = new JTextField(80) ; add( field ) ; } }" ;
	
	public static void main( String[] args ) {
		DatabaseClient client = new DatabaseClient( "localhost", RunDatabaseCluster.DATABASE_CLUSTER_PORT ) ;
		
		SmartCode code = new SmartCode() ;
		code.setCode( SEARCH_ENGINE_SERVICE_CODE ) ;
		code.setMainClass( "MainClass" ) ;
		
		client.getClient().getMap( "SMARTCODE" ).put( SEARCH_ENGINE_SERVICE, code ) ;
		// ????
		client.getClient().getMap( "SMARTCODE" ).get( SEARCH_ENGINE_SERVICE ) ;
		client.getClient().shutdown();
	}
}
