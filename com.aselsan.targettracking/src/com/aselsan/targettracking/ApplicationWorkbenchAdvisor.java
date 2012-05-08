package com.aselsan.targettracking;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.aselsan.targettracking.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

    @Override
    public void postStartup()
    { 
    	IWorkbenchWindow
    		workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            MyPerspectiveAdapter perspectiveListener = new MyPerspectiveAdapter();
            workbenchWindow.addPerspectiveListener(perspectiveListener);
     }  



}
