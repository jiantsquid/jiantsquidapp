package org.jiantsquid.jiantsquidapp.service;

import java.util.HashMap;
import java.util.Map;

import org.jiantsquid.core.identity.NetworkIdentity;
import org.jiantsquid.core.logger.LoggerI;
import org.jiantsquid.network.p2p.message.Request;
import org.jiantsquid.network.p2p.message.Response;
import org.jiantsquid.network.p2p.service.Actions;
import org.jiantsquid.network.p2p.service.ServiceI;


public final class PeersService implements ServiceI {

	private NetworkIdentity identity ;
	
	PeersService( NetworkIdentity identity ) {
		this.identity = identity ;
	}
	
	@Override
	public boolean process( LoggerI logger, Request request, Response response) {
		if( Actions.PEER_REQUEST.equals( request.getAction() ) ) {
			logger.log( identity, Actions.PEER_REQUEST + " from " + request.getParameter( Actions.IDENTITY + " accepted") ) ;
			Map<String,String> parameters = new HashMap<>() ;
			parameters.put( Actions.REQUEST_STATUS, Actions.REQUEST_OK ) ;
			response.merge( identity, parameters, null ) ;
			return true ;
		}
		return false ;
	}

}
