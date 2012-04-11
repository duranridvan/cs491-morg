package com.aselsan.targettracking.view;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.ScalebarLayer;
import gov.nasa.worldwind.layers.SkyGradientLayer;
import gov.nasa.worldwind.layers.StarsLayer;
import gov.nasa.worldwind.layers.WorldMapLayer;
import gov.nasa.worldwind.layers.Earth.BMNGOneImage;
import gov.nasa.worldwind.layers.Earth.BMNGWMSLayer;
import gov.nasa.worldwind.layers.Earth.LandsatI3WMSLayer;
import gov.nasa.worldwind.layers.Earth.MSVirtualEarthLayer;
import gov.nasa.worldwind.layers.Earth.NASAWFSPlaceNameLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;


public class GISView extends ViewPart{
	enum Tool {
		HAND, WAYPOINT
	};

	private Composite SWT_AWT_container;
	private Frame awtFrame;
	private LayerList layerList = new gov.nasa.worldwind.layers.LayerList();
	private WorldWindowGLCanvas worldWindow;

	private gov.nasa.worldwindx.examples.util.AudioPlayer player;
	private RenderableLayer renderableLayer;
	private URL url;
	private Clip clip;

	public GISView() throws IOException {
		this.layerList.add(new StarsLayer());
		this.layerList.add(new SkyGradientLayer());
		this.layerList.add(new BMNGOneImage());
		this.layerList.add(new BMNGWMSLayer());
		this.layerList.add(new LandsatI3WMSLayer());
		this.layerList.add(new MSVirtualEarthLayer());
		this.layerList.add(new WorldMapLayer());
		this.layerList.add(new CompassLayer());
		this.layerList.add(new ScalebarLayer());
		this.layerList.add(new NASAWFSPlaceNameLayer());

		renderableLayer = new RenderableLayer();
		this.layerList.add(renderableLayer);
	}


	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {

		// setting initial position
		Configuration.setValue(AVKey.INITIAL_LATITUDE, 39.9667);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, 32.7659);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 20000000);

		this.SWT_AWT_container = new Composite(parent, SWT.EMBEDDED);
		this.SWT_AWT_container.setLayout(new FillLayout());
		this.awtFrame = SWT_AWT.new_Frame(this.SWT_AWT_container);
		this.worldWindow = new WorldWindowGLCanvas();
		//WorldWindManager.setSWT_AWT_container(SWT_AWT_container);
		this.awtFrame.add(this.worldWindow);

		// this.annotationFont = makeToolTipFont();

		// set offline mode on
		WorldWind.setOfflineMode(true);

		Model model = (Model) WorldWind
				.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);

		model.setShowWireframeExterior(false);
		model.setShowWireframeInterior(false);
		model.setShowTessellationBoundingVolumes(false);
		model.setLayers(this.layerList);
		this.worldWindow.setModel(model);

		//WorldWindManager.setWorldWindow(this.worldWindow);

		// set offline mode on
		WorldWind.setOfflineMode(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		this.SWT_AWT_container.setFocus();
	}

}