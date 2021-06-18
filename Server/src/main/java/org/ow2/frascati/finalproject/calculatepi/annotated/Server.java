/**
 * OW2 FraSCAti Examples: HelloWorld RMI
 * Copyright (C) 2009 INRIA, University of Lille 1
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Author: Damien Fournier
 *         
 * Contributor(s): Christophe Demarey
 *                 Nicolas Dolet
 *                 Philippe Merle
 *
 */
package main.java.org.ow2.frascati.finalproject.calculatepi.annotated;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class Server implements CalcularPi
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
	
    public double calcularPi(final long ptsTotal, final long seed) {
        double ptsCircle = 0;
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
