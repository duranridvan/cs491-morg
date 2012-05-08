package com.aselsan.targettracking;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import com.aselsan.targettracking.gis.GISController;

public class CommandSave extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		GISController.getInstance().saveCurrent();
		// TODO Auto-generated method stub
		return null;
	}

}
