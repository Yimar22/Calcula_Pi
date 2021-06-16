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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class ClientImpl
implements Runnable
{
	//--------------------------------------------------------------------------
	// SCA Reference
	// --------------------------------------------------------------------------

	private CalcularPi s;
	
	private GUI gui;
	
	private long seed;
	private long ptsTotales;
	private int nodos;
	private int ptsDentro;
	
	@Reference(name="calcularPi")
	public final void setGenerarPts(CalcularPi service)
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
		try {
			gui = new GUI();
			gui.setLocationRelativeTo(null);
			gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gui.setVisible(true);
			guiEvents();
	    } catch (Exception e) {
            e.printStackTrace();
	    }
		
	}

	private void guiEvents() {
		gui.getCalculate().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				seed = Long.parseLong(gui.getSeedTF().getText().trim());
				ptsTotales = Long.parseLong(gui.getPointsTF().getText().trim());
				nodos = Integer.parseInt(gui.getNodesTF().getText().trim());
				
				double pi = s.calcularPi(ptsTotales, nodos, seed);
				
				gui.getTextField_3().setText("El valor de pi es: " + pi);
				
			}
		});		
	}
}
