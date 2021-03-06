/**
 * OW2 FraSCAti Examples: HelloWorld RMI
 * Copyright (C) 2008-2010 INRIA, University of Lille 1
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
 * Contact: frascati@ow2.org
 *
 * Author: Damien Fournier
 * 
 * Contributor(s): Nicolas Dolet
 *                 Philippe Merle
 *
 */
package org.ow2.frascati.examples.helloworld.annotated;

import java.util.Scanner;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class ClientImpl
implements Runnable
{
	//--------------------------------------------------------------------------
	// SCA Reference
	// --------------------------------------------------------------------------

	private GenerarPts s;

	@Reference
	public final void setGenerarPts(GenerarPts service)
	{
		this.s = service;
	}

	//--------------------------------------------------------------------------
	// Default constructor
	// --------------------------------------------------------------------------

	public ClientImpl()
	{
		System.out.println("CLIENT created");
	}

	@Init
	public final void init()
	{
		System.out.println("CLIENT initialized");
	}

	//--------------------------------------------------------------------------
	// Implementation of the Runnable interface
	// --------------------------------------------------------------------------

	public final void run()
	{
		System.out.println("Call the service...");
		System.out.println("=============================================");
		System.out.println("Digite el seed");
		Scanner sc = new Scanner(System.in);
		long seed = sc.nextLong();
		System.out.println("Digite el numero de puntos totales");
		long ptsTotales = sc.nextLong();
		
		long ptsDentro = s.generarPts(seed, ptsTotales);
		
		double pi = calcularPi(ptsDentro, ptsTotales);
		
		System.out.println(pi);
	}

	public double calcularPi(long ptsDentro, long ptsTotal) {
		
        double pi = 4 * (ptsDentro / (ptsTotal));
        
        return pi;
	}
}
