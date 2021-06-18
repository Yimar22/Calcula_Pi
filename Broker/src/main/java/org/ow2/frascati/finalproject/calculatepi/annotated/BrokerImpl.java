package main.java.org.ow2.frascati.finalproject.calculatepi.annotated;
import java.rmi.*;
import java.util.LinkedList;



public class BrokerImpl extends UnicastRemoteObject, BringPointsAPI, BrokerService {
	
	private static LinkedList<GeneratePointsAPI> servers = new LinkedList<GeneratePointsAPI>();

    private static LinkedList<String> availableUris = new LinkedList<String>();

    

    @Override
  //Subscribe server to broker
  	public synchronized void attachServer(String serverUri) {
  		try {
  			System.out.println("Attaching server: " + serverUri);
  			GeneratePointsAPI server = (GeneratePointsAPI) Naming.lookup(serverUri);
  			servers.add(server);  			
            availableUris.add(uri);
            System.out.println("Generator connected: " + uri);
            System.out.println("Total points: " + availablePoints.size());
          } catch (Exception e) {
              System.out.println("Error on binding with server: " + serverUri);
              e.printStackTrace();
          }
  	}
  	
  	//Unsubscribe server from broker
  	public synchronized void detachServer(String serverUri) {
  		int index = serverUris.indexOf(serverUri);
  		String uriRemoved=serverUris.remove(index);
  		servers.remove(index);	
  		assert(serverUri.equals(uriRemoved));				
  	}
  	
    @Override
    public GeneratePointsAPI getPoints() {
        int size=servers.size();
        if(size>0){
        	GeneratePointsAPI ret=availableGenerators.poll();
        	availableUris.add(availableUris.poll());
        	servers.add(ret);
            return ret;
        }
        return null;
    }

    @Override
    public int generatorCount() {
        return getAvailablePoints.size();
    }
  	
  	 public LinkedList<GeneratePointsAPI> getAvailablePoints(){
         return availablePoints;
     }
}
