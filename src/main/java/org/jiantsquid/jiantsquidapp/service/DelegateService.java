package org.jiantsquid.jiantsquidapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jiantsquid.core.logger.LoggerI;
import org.jiantsquid.network.p2p.message.Message;
import org.jiantsquid.network.p2p.message.Request;
import org.jiantsquid.network.p2p.message.Response;
import org.jiantsquid.network.p2p.service.ServiceI;

public class DelegateService implements ServiceI {

	private List<Message> currentMessages = Collections.synchronizedList( new ArrayList<>() ) ;
	
	
	DelegateService() {
	}
	
	@Override
	public boolean process(LoggerI logger, Request request, Response response) {
		currentMessages.add( request ) ;
//		for( Peer p : peers.getPeers() ) {
//			try {
//				if( p.getId().equals( request.getFrom() ) ) {
//					Response r = p.sendRequest( request ) ;
//					if( response != null ) {
//						response.merge( r ) ;
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		currentMessages.remove(request ) ;
		return false;
	}

}
