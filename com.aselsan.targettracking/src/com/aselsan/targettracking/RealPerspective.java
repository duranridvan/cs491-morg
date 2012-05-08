package com.aselsan.targettracking;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.aselsan.targettracking.view.ComSelectView;
import com.aselsan.targettracking.view.GISView;
import com.aselsan.targettracking.view.SensorManagerView;
import com.aselsan.targettracking.view.SoftwareJoystickView;

public class RealPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		System.out.println("real");
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		layout.addStandaloneView(SensorManagerView.ID, false, IPageLayout.LEFT, 0.25f, editorArea);
		layout.addStandaloneView(ComSelectView.ID, true, IPageLayout.BOTTOM, 0.65f, SensorManagerView.ID);
		
		IFolderLayout folder = layout.createFolder("asdf", IPageLayout.TOP, 0.5f, editorArea);
		folder.addPlaceholder(GISView.ID + ":*");
		folder.addView(GISView.ID);

	}

}
