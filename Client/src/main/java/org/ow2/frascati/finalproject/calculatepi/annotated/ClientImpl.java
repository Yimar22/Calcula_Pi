package org.ow2.frascati.finalproject.calculatepi.annotated;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.oasisopen.sca.annotation.Property;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class ClientImpl
implements Runnable
{
	//--------------------------------------------------------------------------
	// SCA Reference
	// --------------------------------------------------------------------------

	private BringPointsAPI s;
	private BrokerService bs;
	
	private static GUI gui;
	
	private long seed;
	private long ptsTotales;
	private int nodos;

	@Property
	private String clienturi;
	
	@Reference(name="bringPointsAPI")
	public final void setGenerarPts(BringPointsAPI service)
	{
		this.s = service;
	}

	@Reference(name = "enlaceBroker")
	public final void setBrokerService(BrokerService service){
		this.bs=service;
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
			bs.attachClient(clienturi);
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
				long tinicio = 0;
				long tfinal = 0;
				
				seed = Long.parseLong(gui.getSeedTF().getText().trim());
				ptsTotales = Long.parseLong(gui.getPointsTF().getText().trim());
				nodos = Integer.parseInt(gui.getNodesTF().getText().trim());
				tinicio = System.currentTimeMillis();
				long pi = s.bringPts(ptsTotales, nodos, seed);
				tfinal = System.currentTimeMillis();
				gui.getTextField_3().setText("El valor de pi es: " + pi + " y el tiempo de espera fue: "+(tfinal-tinicio));
				
			}
		});		
	}
}
