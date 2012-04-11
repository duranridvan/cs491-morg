package com.aselsan.targettracking;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.aselsan.targettracking.view.GISView;
import com.aselsan.targettracking.view.SensorManagerView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		layout.addStandaloneView(SensorManagerView.ID, false, IPageLayout.LEFT, 0.25f, editorArea);
		
		IFolderLayout folder = layout.createFolder("asdf", IPageLayout.TOP, 0.5f, editorArea);
		folder.addPlaceholder(GISView.ID + ":*");
		folder.addView(GISView.ID);
	}

}
