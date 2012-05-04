package com.aselsan.targettracking.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.gis.GISEventManager;
import com.aselsan.targettracking.sensornetwork.RealSensorNetwork;
import com.aselsan.targettracking.sensornetwork.SensorNetwork;

public class ComSelectView extends ViewPart  {

	ArrayList<Button> buttons;
	public static final String ID = "com.aselsan.targettracking.comselectview";
	public ComSelectView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		buttons = new ArrayList<Button>();
		// TODO Auto-generated method stub
		RowLayout l = new RowLayout();
		l.wrap = true;
		l.pack = true;
		l.type = SWT.VERTICAL;
		parent.setLayout(l);
		Collection<String> comNames = RealSensorNetwork.listPorts();
		for(String s : comNames){
			Button b =new Button(parent,SWT.RADIO);
			b.setText(s);
			final String s2 = s;
			b.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					SensorNetwork net = new RealSensorNetwork(s2);
					net.start();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			buttons.add(b);
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
