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
    private String header = "->";
    
    private GenerarPts generarPts;
    private GenerarPts generarPts1;
    private GenerarPts generarPts2;
    private GenerarPts generarPts3;
    
    private static ArrayList<GenerarPts> nodes = new ArrayList<GenerarPts>();
    
    @Reference(name="generarPts")
	public final void setGenerarPts(
			GenerarPts generarPts) {
		this.generarPts = generarPts;
	}

    @Reference(name="generarPts1")
	public final void setGenerarPts1(
			GenerarPts generarPts1) {
		this.generarPts1 = generarPts1;
	}

    @Reference(name="generarPts2")
	public final void setGenerarPts2(
			GenerarPts generarPts2) {
		this.generarPts2 = generarPts2;
	}

    @Reference(name="generarPts3")
	public final void setGenerarPts3(
			GenerarPts generarPts3) {
		this.generarPts3 = generarPts3;
	}

    public Server()
    {
        System.out.println("SERVER created.");
    }
	
	public double calcularPi(long ptsTotal, int nodos, long seed) {
		
		long inicio = System.currentTimeMillis();
		
		nodes.add(generarPts);
		nodes.add(generarPts1);
		nodes.add(generarPts2);
		nodes.add(generarPts3);
		
		int ptsDentro = 0;
		
		ExecutorService executor = Executors.newFixedThreadPool(nodes.size());
		
		switch (nodos) {
			case 1:
				
				
				
				break;
				
			case 2:
				
				break;
				
			case 3:
				
				break;
				
			case 4:
				
				break;
				
			default:
				break;
		}
		
        double pi = 4 * ((double) ptsDentro / (ptsTotal));
        
        return pi;
	}
	
}
