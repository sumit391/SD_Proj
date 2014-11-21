/*
 * Copyright (c) 2000-2014 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;


import jdraw.framework.DrawContext;
import jdraw.figures.Line;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 * @version 2.1, 27.09.07
 */
public class LineTool extends AbstractFigureTool {
	
	public LineTool(DrawContext context) {
		super(context, "Line", "line.png");
	}
	public LineTool(DrawContext context, String name, String iconName) {
		super(context, name, iconName);
	}

	@Override
	public Line createNewFigure(int x, int y) {
		return new Line(x,y,0,0);
	}

	
}
