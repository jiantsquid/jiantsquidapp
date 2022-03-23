package org.jiantsquid.jiantsquidapp.test;

import org.jiantsquid.core.database.DatabaseCluster;
import org.jiantsquid.jiantsquidapp.database.SmartCode;

public class RunDatabaseCluster {

	public final static int DATABASE_CLUSTER_PORT = 33000 ;
	public static void main( String[] args ) {
		DatabaseCluster dbc = new DatabaseCluster( "localhost", DATABASE_CLUSTER_PORT ) ;
		dbc.init() ;
	}
}
