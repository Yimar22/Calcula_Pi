package org.ow2.frascati.finalproject.calculatepi.annotated;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class BrokerImpl extends UnicastRemoteObject implements BringPointsAPI, BrokerService, Runnable {
	
	protected BrokerImpl() throws RemoteException {
        super();
    }

    private static LinkedList<GeneratePointsAPI> servers = new LinkedList<GeneratePointsAPI>();

    private static LinkedList<BringPointsAPI> clients = new LinkedList<BringPointsAPI>();

    private static LinkedList<String> sUris = new LinkedList<String>();
    
    private static LinkedList<String> cUris = new LinkedList<String>();

    private long pointsInCircle;
    
    public void run() {
       
    }
    
    //Subscribe server to broker
  	public synchronized void attachServer(String sUri) {
  		try {
  			System.out.println("Attaching server: " + sUri);
  			GeneratePointsAPI s = (GeneratePointsAPI) Naming.lookup(sUri);
  			servers.add(s);  			
            /*availableUris.add();
            System.out.println("Generator connected: " + uri);
            System.out.println("Total points: " + availablePoints.size());*/
          } catch (Exception e) {
              System.out.println("Error on binding with server: " + sUri);
              e.printStackTrace();
          }
  	}
  	
  	//Unsubscribe server from broker
  	public synchronized void detachServer(String sUri) {
  		int index = sUris.indexOf(sUri);
  		String uriRemoved=sUris.remove(index);
  		servers.remove(index);	
  		assert(sUri.equals(uriRemoved));				
  	}

    public GeneratePointsAPI getServer() {
        int size=servers.size();
        if(size>0){
        	GeneratePointsAPI ret=servers.poll();
        	sUris.add(sUris.poll());
        	servers.add(ret);
            return ret;
        }
        return null;
    }

    public int serverCount() {
        return servers.size();
    }
  	
  	public LinkedList<GeneratePointsAPI> getservers(){
         return servers;
     }

     

    //Client
    //Sub Client to Broker
    public synchronized void attachClient(String cUri){
        try {
            System.out.println("Attaching server: " + cUri);
            BringPointsAPI c = (BringPointsAPI) Naming.lookup(cUri);
            clients.add(c);
        } catch (Exception e) {
            System.out.println("Error on binding with server: " + cUri);
            e.printStackTrace();
        }
    }

    //UnSub Client from Broker
    public synchronized void detachClient(String cUri){
        int index = cUris.indexOf(cUri);
  		String uriRemoved=sUris.remove(index);
  		clients.remove(index);	
  		assert(cUri.equals(uriRemoved));	
    }

    /*
    public BringPointsAPI getClient(){
        int size=clients.size();
        if(size>0){
        	BringPointsAPI ret=clients.poll();
        	cUris.add(cUris.poll());
        	clients.add(ret);
            return ret;
        }
        return null;
    }

    public int clientCount(){
        return clients.size();
    }

    public LinkedList<BringPointsAPI> getclients(){
        return clients;
    }
    */
    
    public float bringPts(long totalpts, long nodos, long seed) throws RemoteException{
        while(totalpts>0){
            ArrayList<Node> nodes = new ArrayList<Node>();
            ExecutorService ex = Executors.newFixedThreadPool(servers.size());
            Node node;
            for(final GeneratePointsAPI server : servers){
				if(totalpts>0){
					long points = 1000000> totalpts ? totalpts:1000000;
					node = new Node(seed, totalpts, server);
					nodes.add(node);
					ex.execute(node);
					totalpts-=points;
				}
				else{
					break;
				}
			}	
			ex.shutdown();
			while(!ex.isTerminated());

			for(Node n : nodes){
				pointsInCircle += n.getPointsInCircle();
			}
        }
        return 0;
    }

    public void getPoints(long resultpoints) {
        // TODO Auto-generated method stub
        
    }

}
