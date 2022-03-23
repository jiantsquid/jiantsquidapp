package org.jiantsquid.jiantsquidapp.service;

import org.jiantsquid.core.crypto.Hash;
import org.jiantsquid.core.data.Log;
import org.jiantsquid.core.identity.Identity;
import org.jiantsquid.core.logger.LoggerI;
import org.jiantsquid.jiantsquidapp.database.SystemDatabase;

public final class Logger implements LoggerI {

	private SystemDatabase logBase ;
	
	public Logger( SystemDatabase base ) {
		logBase = base ;
	}
	public void log( Identity identity, String message ) {
		Log log = new Log( identity.getId(), message ) ;
		//logBase.manage( Hash.hash( message ), log ) ;
	}
	
	public void print() {
		//logBase.print() ; 
	}
}
