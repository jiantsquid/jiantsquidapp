package org.jiantsquid.jiantsquidapp.service;

import java.util.HashMap;
import java.util.Map;

import org.jiantsquid.core.identity.NetworkIdentity;
import org.jiantsquid.core.logger.LoggerI;
import org.jiantsquid.jiantsquidapp.database.SmartCode;
import org.jiantsquid.jiantsquidapp.database.SmartCodeDataBase;
import org.jiantsquid.network.p2p.message.Request;
import org.jiantsquid.network.p2p.message.Response;
import org.jiantsquid.network.p2p.service.Actions;
import org.jiantsquid.network.p2p.service.ServiceI;

public final class LoadCodeService implements ServiceI {

	private final SmartCodeDataBase smartCodeDatabase ;
	
	private final NetworkIdentity identity ;
	
	private final Map<String,String> services = new HashMap<>() ;
	
	LoadCodeService( NetworkIdentity identity, SmartCodeDataBase base  ) {
		smartCodeDatabase = base ;
		services.put(Actions.LOAD_MAINGUI_PARAM, SmartCodeDataBase.SEARCH_ENGINE_SERVICE ) ;
		this.identity = identity ;
	}
	
	public boolean process( LoggerI logger, Request request, Response response ) {
		
		String action = request.getAction() ; 
		if( action != null && action.equals( Actions.LOAD_SERVICE_ACTION ) ) {
			loadCode( logger, request, response ) ;
			return true ;
		}
		return false ;
	}
	
	private void loadCode( LoggerI logger, Request request, Response response ) {
		String what = services.get( request.getParameter( Actions.WHAT ) ) ;
		SmartCode code = smartCodeDatabase.getCode( what ) ;
		if( code != null ) {
			logger.log( identity, "Load code " + what + " for " + request.getFrom() ) ;
			Map<String,String> parameters = new HashMap<>() ;
			parameters.put( SmartCode.MAIN_CLASS, code.getMainclass() ) ;
			parameters.put( SmartCode.MAIN_CODE, code.getCode() ) ;
			
			response.merge( identity, parameters, null ) ;
			
		} 
	}

}
