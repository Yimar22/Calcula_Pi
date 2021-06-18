package org.ow2.frascati.finalproject.calculatepi.annotated;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import org.osoa.sca.annotations.Service;

@Service
public interface GeneratePointsAPI extends Remote{
		public GeneratePointsAPI getServer();
		public int serverCount();
		public LinkedList<GeneratePointsAPI> getservers();
		public long generatePts(long ptsTotal, long seed) throws RemoteException;
	}
	