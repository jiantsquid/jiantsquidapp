package org.jiantsquid.jiantsquidapp.database;

import org.jiantsquid.core.identity.NetworkIdentity;

public class SmartCodeDataBase {

	public static final String SEARCH_ENGINE_SERVICE = "SEARCH_ENGINE" ;
	public static final String SEARCH_ENGINE_SERVICE_CODE = "import javax.swing.JTextField; public class MainClass extends javax.swing.JPanel "
			+ "{  public MainClass() { JTextField field = new JTextField(80) ; add( field ) ; } }" ;
	public SmartCodeDataBase( NetworkIdentity entity ) {
	}
	
	
	public SmartCode getCode( String codeKey ) {
		//return (SmartCode) get( codeKey ) ;
		return null ;
	}

	public String getName() {
		return "SmartCodeDataBase" ;
	}
}
