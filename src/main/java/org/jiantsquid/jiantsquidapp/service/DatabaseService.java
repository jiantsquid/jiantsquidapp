package org.jiantsquid.jiantsquidapp.service;

import java.util.HashMap;
import java.util.Map;

import org.jiantsquid.core.data.Data;
import org.jiantsquid.core.database.DatabaseClient;
import org.jiantsquid.core.identity.NetworkIdentity;
import org.jiantsquid.core.logger.LoggerI;
import org.jiantsquid.jiantsquidapp.database.SmartCodeDataBase;
import org.jiantsquid.jiantsquidapp.database.SystemDatabase;
import org.jiantsquid.network.p2p.message.Request;
import org.jiantsquid.network.p2p.message.Response;
import org.jiantsquid.network.p2p.service.Actions;
import org.jiantsquid.network.p2p.service.ServiceI;


public class DatabaseService implements ServiceI {

	private NetworkIdentity identity ;
	private Map<String,DatabaseClient> databases = new HashMap<>();
	
	public DatabaseService( NetworkIdentity identity, SystemDatabase systemDataBase, SmartCodeDataBase smartCodeDataBase ) {
		this.identity = identity ;
//		databases.put( systemDataBase.getName(), systemDataBase );
//		databases.put( smartCodeDataBase.getName(), smartCodeDataBase );
	}
	
	@Override
	public boolean process( LoggerI logger, Request request, Response response) {
		if( Actions.INSERT_DATA.equals( request.getAction() ) ) {
			logger.log( identity, Actions.INSERT_DATA + " from " + request.getParameter( Actions.IDENTITY + " accepted") ) ;
			
			String dataBaseName = request.getParameter( Actions.DATABASE_NAME ) ;
			DatabaseClient database = databases.get( dataBaseName ) ;
			
			Map<String,Data> userData = request.getUserData() ;
			boolean status = true;
			for( Map.Entry<String,Data> entries : userData.entrySet() ) {
				//status = database.put( entries.getKey(), entries.getValue() ) ;
			}
			Map<String,String> parameters = new HashMap<>() ;
			parameters.put( Actions.REQUEST_STATUS, status ? Actions.REQUEST_OK : Actions.REQUEST_FAILED ) ;
			response.merge( identity, parameters, null ) ;
			return status ;
		} else  if( Actions.GET_DATA.equals( request.getAction() ) ) {
			logger.log( identity, Actions.GET_DATA + " from " + request.getParameter( Actions.IDENTITY + " accepted") ) ;
			String dataBaseName = request.getParameter( Actions.DATABASE_NAME ) ;
			DatabaseClient database = databases.get( dataBaseName ) ;
			
			String dataKey =  request.getParameter( Actions.DATA_KEY ) ;
//			if( database.get( dataKey ) == null ) {
//				return false ;
//			}
			
			Map<String,String> parameters = new HashMap<>() ;
			parameters.put( Actions.REQUEST_STATUS, Actions.REQUEST_OK ) ;
			
			//response.addUserData( dataKey, database.get( dataKey ) ) ;
			response.merge( identity, parameters, null ) ;
			return true ;
		}
		return false;
	}

}
