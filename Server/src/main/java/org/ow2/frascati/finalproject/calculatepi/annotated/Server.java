package main.java.org.ow2.frascati.finalproject.calculatepi.annotated;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class Server implements GeneratePointsAPI
{
    @Property
    private String uri;
    
    private GenerarPts generarPts;

    @Reference(name="generarPts")
	public final void setGenerarPts(
			GenerarPts generarPts) {
		this.generarPts = generarPts;
	}


    public Server()
    {
        System.out.println("SERVER created.");
        System.out.println("Conecting generator");
        //generarPts.enlazarGeneradorPts(uri);
        System.out.println("Conection succeded");
    }
	
    public long generatePts(final long ptsTotal, final long seed) {
        long ptsCircle = 0;
        Random r = new Random(seed);

        for(long i=0; i < ptsTotal; i++){
            double x = r.nextDouble();
            double y = r.nextDouble();
            if(x*x+y*y<=1){
                ptsCircle++;
            }
        }
        return ptsCircle;
}
	
}
