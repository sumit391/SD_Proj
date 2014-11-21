/*
 * Copyright (c) 2000-2014 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;


import jdraw.framework.DrawContext;
import jdraw.figures.Ellipse;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 * @version 2.1, 27.09.07
 */
public class EllipseTool extends AbstractFigureTool {
  
	/**
	 * Create a new rectangle tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public EllipseTool(DrawContext context) {
		super(context, "Ellipse", "oval.png");
	}
	
	public EllipseTool(DrawContext context, String name, String iconName) {
		super(context, name, iconName);
	}

	@Override
	public Ellipse createNewFigure(int x, int y) {
		return new Ellipse(x,y,0,0);
	}

}
