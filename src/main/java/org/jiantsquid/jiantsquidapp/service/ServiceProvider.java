package org.jiantsquid.jiantsquidapp.service;

import org.jiantsquid.core.identity.Identity;
import org.jiantsquid.core.identity.NetworkIdentity;
import org.jiantsquid.core.logger.LoggerI;
import org.jiantsquid.jiantsquidapp.database.SmartCodeDataBase;
import org.jiantsquid.jiantsquidapp.database.SystemDatabase;
import org.jiantsquid.network.p2p.peer.Peers;
import org.jiantsquid.network.p2p.service.AbstractServiceprovider;


public final class ServiceProvider extends AbstractServiceprovider {
	
	private LoggerI logger ;
	private final Identity identity ;
	
	public ServiceProvider( final Identity identity ) {
		super(  );
		this.identity = identity ;
		logger = new LoggerI() {

			@Override
			public void log(Identity identity, String message) {
				System.out.println( "LOGGER:" + identity.getId() + " " + message ) ;
			}
			
		} ;
	}
	
	public Identity getIdentity() {
		return identity ;
	}
	
	
	@Override
	protected void registerServices() {
//		SmartCodeDataBase smartCodeDataBase = new SmartCodeDataBase( getIdentity() ) ;
//		SystemDatabase    systemDataBase    = new SystemDatabase( getIdentity() )  ;
//		logger = new Logger( systemDataBase ) ;
//		
//		services.add( new LoadCodeService( getIdentity(), smartCodeDataBase ) ) ;
//		services.add( new PeersService( getIdentity() ) ) ;
//		services.add( new DatabaseService( getIdentity(), systemDataBase, smartCodeDataBase ) ) ;
		//TO DO : lot of problems !
		//services.add( new DelegateService( peers ) ) ;
	}

	@Override
	public LoggerI getLogger() {
		return logger ;
	}
}
