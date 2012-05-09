package com.aselsan.targettracking;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import com.aselsan.targettracking.Path;

import com.aselsan.targettracking.databasemanager.DatabaseManager;
import com.aselsan.targettracking.gis.GISController;

public class CommandOpen extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		Shell shell = new Shell();
		shell.setText("Route List");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		shell.setLayout(gridLayout);
		GridData gridData = new GridData();
		final List routeList = new List(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);	
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		routeList.setLayoutData(gridData);
		java.util.List<Path> paths = DatabaseManager.getInstance().getRoutes();
		for(int i = 0 ; i < paths.size() ; i++)
		{
			routeList.add((i + 1) + " ) " + paths.get(i).toString());
		}	
	
		routeList.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int index = routeList.getSelectionIndex();
				String str = routeList.getItem(index);
				int f = str.indexOf("=");
				int a = str.indexOf("F");
				int l = str.lastIndexOf("=");
				String start = str.substring((f+2),(a-1));
				String finish = str.substring((l+2));			
				GISController.getInstance().updatePath(DatabaseManager.getInstance().getPath(start, finish));
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		shell.pack();
		shell.setSize(350,200);
		shell.setVisible(true);
		return null;
	}

}


