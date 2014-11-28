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
import jdraw.figures.handles.*;
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
public class Rect extends AbstractFigure {
	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private java.awt.Rectangle rectangle;
	
	//handles
	public ArrayList<FigureHandle> handles = new ArrayList<FigureHandle>(8);
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	
	public Rect(int x, int y, int w, int h) {
		rectangle = new java.awt.Rectangle(x, y, w, h);
		handles.add(new EHandle(this));
		handles.add(new NEHandle(this));
		handles.add(new NHandle(this));
		handles.add(new NWHandle(this));
		handles.add(new WHandle(this));
		handles.add(new SWHandle(this));
		handles.add(new SHandle(this));
		handles.add(new SEHandle(this));
	}

	//private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	
	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		rectangle.setFrameFromDiagonal(origin, corner);
		// done
		notifyAllListeners();
		
	}

	@Override
	public void move(int dx, int dy) {
		rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
		// done
		notifyAllListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return rectangle.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return rectangle.getBounds();
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
			Rect newF;
			newF = (Rect) super.clone();
			newF.rectangle = new Rectangle(this.rectangle);
			newF.handles = new ArrayList<FigureHandle>(8);
			newF.handles.add(new EHandle(newF));
			newF.handles.add(new NEHandle(newF));
			newF.handles.add(new NHandle(newF));
			newF.handles.add(new NWHandle(newF));
			newF.handles.add(new WHandle(newF));
			newF.handles.add(new SWHandle(newF));
			newF.handles.add(new SHandle(newF));
			newF.handles.add(new SEHandle(newF));
			return newF;
	}

	

}
