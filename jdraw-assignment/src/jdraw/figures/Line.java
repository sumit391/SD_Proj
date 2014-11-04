/*
 * Copyright (c) 2000-2014 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

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
public class Line implements Figure {
	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private static final int DIST = 5;
	private Line2D.Double line;
	
	
	/**
	 * Create a new line of the given dimension.
	 
	 */
	
	public Line(int x, int y, int w, int h) {
		line = new Line2D.Double(x,y,x+w,y+h);
	}

	private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	public void notifyAllListeners(){
		for(FigureListener l : listeners){
			l.figureChanged(new FigureEvent(this));
		}
	}
	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int)line.x1, (int)line.y1,(int)line.x2, (int)line.y2);
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		line.setLine(origin,corner);
		notifyAllListeners();
		
	}

	@Override
	public void move(int dx, int dy) {
		line.x1+=dx;
		line.x2+=dx;
		line.y1+=dy;
		line.y2+=dy;
		
		notifyAllListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return line.ptLineDist(x,y)<DIST;
	}

	@Override
	public Rectangle getBounds() {
		return line.getBounds();
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	public List<FigureHandle> getHandles() {
		return null;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		// done
		if (!listeners.contains(listener)){
			listeners.add(listener);
		}
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		// done
		listeners.remove(listener);
	}

	@Override
	public Figure clone() {
		return null;
	}

	

}
