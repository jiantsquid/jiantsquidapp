package org.jiantsquid.jiantsquidapp.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jiantsquid.core.identity.NetworkIdentity;
import org.jiantsquid.jiantsquidapp.service.ServiceProvider;
import org.jiantsquid.network.impl.Node;
import org.jiantsquid.network.p2p.node.AbstractNode;

public class Network {
	
	public static final int PORT = 32000 ;
	
	public static final int NB_NODES = 20 ;
	
	List<Node> nodes = new ArrayList<>(); 
	
	int nodeCount = 0 ;
	class NodeRunnable implements Runnable {
		
		AbstractNode node ;
		
		NodeRunnable( AbstractNode node ) {
			
			this.node = node ;
		}
		public void run() {
			try {
				System.out.println( "start node " + node .getPort() ) ;
				node.start();
				System.out.println( "started node " + node .getPort() ) ;
			} catch (IOException e) {
				e.printStackTrace();
			}
			nodeCount ++ ;
		}
	}
	public Network() throws ClassNotFoundException, IOException {
		long t0 = System.currentTimeMillis() ;
		
		System.out.println( "start network" ) ;
			for( int i = 0 ; i < NB_NODES ; i ++ ) {
				try {
					nodes.add( new Node( PORT + i, RunDatabaseCluster.DATABASE_CLUSTER_PORT+ 1 + i /*DATABASE_CLUSTER_PORT*/, 
							new ServiceProvider( new NetworkIdentity( "localhost", PORT ) ) ) ) ;
					//Thread.sleep( 1000 );
				} catch (IOException  e) {
					e.printStackTrace();
				} 
//				catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			
			
			for( final AbstractNode node : nodes ) {
				new Thread( new NodeRunnable( node ) ).start() ;
				try {
					Thread.sleep( 1000 );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			while( nodeCount < NB_NODES ) {
				try {
					Thread.sleep( 100 ) ;
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
//			for( Node node : nodes ) {
//				int port = node.getPort() ;
//				for( int i = port+1 ; i < port + 4 && i < port + Network.NB_NODES ; i ++ ) {
//					int p =  i ;
//					if( p != port ) {
//						node.connectPeer( "localhost", p ) ;
//					}
//				}
//			}
			long t1 = System.currentTimeMillis() ;
			System.out.println( "network started " + ( ( t1- t0 ) / 1000 ) + " s") ;
			print() ;
	}
	
	public void print() {
		boolean print = true ;
		//int count = 0 ;
		for( AbstractNode node : nodes ) {
			node.print( print /*count == NB_NODES - 1*/ ) ;
			//print = false ;
			//count ++ ;
		}
	}
	public static void main( String[] args ) throws ClassNotFoundException, IOException {
		new Network() ;
	}
}
