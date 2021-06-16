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
package org.ow2.frascati.examples.helloworld.annotated;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osoa.sca.annotations.Property;

public class Node
  implements GenerarPts
{
    @Property
    private String header = "->";

    public Server()
    {
        System.out.println("SERVER created.");
    }

	public int generarPts(long seed, long ptsTotales) {
		
		Random rnd = new Random(seed);
		
		int dentro = 0;
		
		double x, y;
		
		for(int i=0; i<ptsTotales; i++) {
			
			x = rnd.nextDouble();
			y = rnd.nextDouble();
			
			if((x * x) + (y * y) <= 1) {
				dentro++;
			}
		}
		
		System.out.println(dentro);
		
		return dentro;

	}

	public int generarPts(long seed, long ptsTotales, int nodos) {
		
		Random rnd = new Random(seed);
		
		long hilos = (j-i) / 10000000;
		
		if(hilos == 0) {
			hilos == 1;
		}
		
		final long points = (j-i) / hilos;
		
		int dentro = 0;
		
		ExecutorService executor = Executors.newFixedThreadPool((int)threads);
		
		double x, y;
		
		for(int k = 0; k < threads; k++) {
            Thread newHilo = new Thread() {
                
                public void run() {
                    System.out.println("Hilo arranca");
                    try {
                        for (int j = 0; j < points; j++) {

                            x = random.nextDouble();
                            y = random.nextDouble();
        
                            if ((x * x) + (y * y) <= 1) {
                                dentro++;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Algo salio mal");
                    }
                }
            };
            executor.execute(newHilo);
        }
		
		System.out.println(dentro);
		
		executor.shutdown();
        while (!executor.isTerminated());
		
		return dentro;
	}
}
