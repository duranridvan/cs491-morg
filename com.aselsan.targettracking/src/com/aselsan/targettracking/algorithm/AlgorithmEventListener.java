package com.aselsan.targettracking.algorithm;

import org.eclipse.swt.graphics.Point;

public interface AlgorithmEventListener {
	void notifyPoint(Point p);
	void notifyStart();
	void notifyFinish();
}
