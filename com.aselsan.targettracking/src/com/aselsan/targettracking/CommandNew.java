package com.aselsan.targettracking;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandler2;
import org.eclipse.core.commands.IHandlerListener;

import com.aselsan.targettracking.gis.GISController;

public class CommandNew extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	//	new CommandStop().execute(event);
//		System.out.println("clean!!");
		GISController.getInstance().clean();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
