package org.ow2.frascati.finalproject.calculatepi.annotated;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import org.osoa.sca.annotations.Service;

@Service
public interface BringPointsAPI extends Remote{
		/*public BringPointsAPI getClient();
		public int clientCount();
		public LinkedList<BringPointsAPI> getclients();
		*/
		public float bringPts(long totalpts, long nodos, long seed) throws RemoteException;
	}