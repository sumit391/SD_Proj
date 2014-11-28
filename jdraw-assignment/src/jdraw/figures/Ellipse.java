/*
 * Copyright (c) 2000-2014 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Ellipse2D;

import jdraw.figures.handles.EHandle;
import jdraw.figures.handles.NHandle;
import jdraw.figures.handles.SHandle;
import jdraw.figures.handles.WHandle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.framework.FigureListener;
/**
 * Represents rectangles in JDraw.
 * 
 * @author Sumit Chouhan
 *
 */
@SuppressWarnings("serial")
public class Ellipse extends AbstractFigure {
	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private Ellipse2D.Double ellipse;
	// handles
	private ArrayList<FigureHandle> handles = new ArrayList<FigureHandle>(4);
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	
	public Ellipse(int x, int y, int w, int h) {
		ellipse	 = new Ellipse2D.Double(x, y, w, h);
		handles.add(new EHandle(this));
		handles.add(new NHandle(this));
		handles.add(new WHandle(this));
		handles.add(new SHandle(this));

	}

	//private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)ellipse.x,(int)ellipse.y,(int)ellipse.width,(int)ellipse.height);
		g.setColor(Color.BLACK);
		g.drawOval((int)ellipse.x,(int)ellipse.y,(int)ellipse.width,(int)ellipse.height);
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		ellipse.setFrameFromDiagonal(origin, corner);
		// done
		notifyAllListeners();
		
	}

	@Override
	public void move(int dx, int dy) {
		Rectangle border = new Rectangle(ellipse.getBounds());
		border.setLocation(border.x+dx,border.y+dy);
		// done
		ellipse.setFrame(border);
		notifyAllListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return ellipse.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return ellipse.getBounds();
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	public List<FigureHandle> getHandles() {
		return handles;
	}

	
	@Override
	public Object clone() {
		Ellipse newF;		
		
		newF=(Ellipse) super.clone();
		newF.ellipse= new Ellipse2D.Double(this.ellipse.x, this.ellipse.y,
				this.ellipse.width, this.ellipse.height);
		newF.handles= new ArrayList<FigureHandle>(4);
		
		newF.handles.add(new EHandle(newF));
		newF.handles.add(new NHandle(newF));
		newF.handles.add(new WHandle(newF));
		newF.handles.add(new SHandle(newF));
	
		return newF;
	}

	

}
