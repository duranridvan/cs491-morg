package com.aselsan.targettracking.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.aselsan.targettracking.sensornetwork.SensorManager;

public class SensorManagerView extends ViewPart implements SensorManager.Listener{
	public static final String ID = "com.aselsan.targettracking.sensormanagerview";
	//private org.eclipse.swt.graphics.Image image;
	private GridLayout gridLayout;
	private Button button;
	private GridData gridData;
	private SensorManager sensorManager;
	private List sensorList;
	public SensorManagerView(){
		//image = com.aselsan.targettracking.Activator.getImageDescriptor("icons/arkaplan.jpg").createImage();
		sensorManager = SensorManager.getInstance();
		sensorManager.addListener(this);
	}
	private void refreshList(){
		sensorList.removeAll();
		for(int i = 0 ; i < sensorManager.getSensorList().size() ; i++)
		{
			sensorList.add(sensorManager.getSensorList().get(i).toString());
		}
	}
	
	
	public void createPartControl(final Composite parent) {
		
		//parent.setBackgroundImage(image);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		parent.setLayout(gridLayout);
		gridData = new GridData();
		sensorList = new List(parent, SWT.BORDER | SWT.MULTI);
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		sensorList.setLayoutData(gridData);
		for(int i = 0 ; i < sensorManager.getSensorList().size() ; i++)
		{
			sensorList.add(sensorManager.getSensorList().get(i).toString());
		}
		
		button = new Button(parent, SWT.PUSH);
		button.setText("Add Sensor");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Handle the selection event
				
				final Shell shell = new Shell();
				shell.setVisible(false);
				shell.setLayout(new GridLayout(2, false));
				
				Label macLabel = new Label(shell, SWT.NONE);
				macLabel.setText("Mac:");
				
				final Text macText = new Text(shell, SWT.BORDER);
				gridData = new GridData();
				gridData.horizontalAlignment = SWT.FILL;
				gridData.grabExcessHorizontalSpace = true;
				macText.setLayoutData(gridData);
				macText.setText("");
				
				Label xCoorLabel = new Label(shell, SWT.NONE);
				xCoorLabel.setText("X Coordinate:");
				
				final Text xCoorText = new Text(shell, SWT.BORDER);
				gridData = new GridData();
				gridData.horizontalAlignment = SWT.FILL;
				gridData.grabExcessHorizontalSpace = true;
				xCoorText.setLayoutData(gridData);
				xCoorText.setText("");
				
				Label yCoorLabel = new Label(shell, SWT.NONE);
				yCoorLabel.setText("Y Coordinate:");
				
				final Text yCoorText = new Text(shell, SWT.BORDER);
				gridData = new GridData();
				gridData.horizontalAlignment = SWT.FILL;
				gridData.grabExcessHorizontalSpace = true;
				yCoorText.setLayoutData(gridData);
				yCoorText.setText("");
								
				button = new Button(shell, SWT.PUSH);
				button.setText("Add Sensor");
				button.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						
						boolean success = true;
						boolean emptyTextField = false;
						try{
						if(macText.getText()== "" || xCoorText.getText()== "" || yCoorText.getText() == "")
						{	
							emptyTextField = true;
							throw new Exception();
						}
						sensorManager.addSensor(macText.getText(), new Point(Integer.parseInt(xCoorText.getText()),Integer.parseInt(yCoorText.getText())));
						} catch (Exception e1) {
							if(emptyTextField)
							{	
								final Shell dialogError = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
								dialogError.setText("WARNING");
								dialogError.setLayout(gridLayout);
								Text warningText = new Text(dialogError,SWT.SINGLE);
								warningText.setText("You cannot leave Mac,X Coordinate,Y Coordinate fields empty");
								Button buttonOK = new Button(dialogError, SWT.PUSH);
								buttonOK.setText("OK");
								buttonOK.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										dialogError.close();
										dialogError.dispose();
									}
								});
								dialogError.pack();
								dialogError.setSize(400, 100);
				                dialogError.open();
							}
							else
							{																	
								final Shell dialogError = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
								dialogError.setText("WARNING");
								dialogError.setLayout(gridLayout);
								Text warningText = new Text(dialogError,SWT.SINGLE);
								warningText.setText("You should enter a number for Mac,X Coordinate,Y Coordinate fields");
								Button buttonOK = new Button(dialogError, SWT.PUSH);
								buttonOK.setText("OK");
								buttonOK.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										dialogError.close();
										dialogError.dispose();
									}
								});
								dialogError.pack();
								dialogError.setSize(400, 100);
				                dialogError.open();							
							}
							success = false;
						}
						if(success)
						{
							shell.close();
							shell.dispose();
						}
					}
				});
				shell.pack();
				shell.setSize(350,200);
				shell.setVisible(true);
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
	@Override
	public void sensorManagerUpdate() {
		refreshList();
	}
}