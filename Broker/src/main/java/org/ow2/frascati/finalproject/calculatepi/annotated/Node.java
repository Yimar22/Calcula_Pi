package org.ow2.frascati.finalproject.calculatepi.annotated;

import java.rmi.RemoteException;

public class Node extends Thread{
    
    private long seed;
	private long totalPts;
	private GeneratePointsAPI server;
    private long ptsInCircle;

    public Node(long seed, long totalpts, GeneratePointsAPI server){
        super();
        this.seed=seed;
        this.totalPts=totalpts;
        this.server=server;
    }

    @Override
    public void run() {
        super.run();
        try {
            ptsInCircle=server.generatePts(totalPts, seed);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public long getPointsInCircle() {
		return ptsInCircle;
	}
}
