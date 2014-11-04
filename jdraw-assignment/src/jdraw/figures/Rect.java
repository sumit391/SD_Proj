/*
 * Copyright (c) 2000-2014 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
=======
import jdraw.framework.FigureListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.LinkedList;
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
<<<<<<< HEAD
import jdraw.framework.FigureListener;
/**
 * Represents rectangles in JDraw.
 * 
 * @author Sumit Chouhan
 *
 */
=======

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
@SuppressWarnings("serial")
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
public class Rect implements Figure {
	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private java.awt.Rectangle rectangle;
<<<<<<< HEAD
	
=======
	private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
<<<<<<< HEAD
	
=======
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	public Rect(int x, int y, int w, int h) {
		rectangle = new java.awt.Rectangle(x, y, w, h);
	}

<<<<<<< HEAD
	private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	public void notifyAllListeners(){
		for(FigureListener l : listeners){
			l.figureChanged(new FigureEvent(this));
		}
	}
=======
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
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
<<<<<<< HEAD
		// done
		notifyAllListeners();
		
=======
		for (FigureListener l:listeners){
			l.figureChanged(new FigureEvent(this));
		}
		// TODO notification of change
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public void move(int dx, int dy) {
		rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
<<<<<<< HEAD
		// done
		notifyAllListeners();
=======
		for (FigureListener l:listeners){
			l.figureChanged(new FigureEvent(this));
		}
		// TODO notification of change
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
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
		return null;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
<<<<<<< HEAD
		// done
		if (!listeners.contains(listener)){
			listeners.add(listener);
		}
=======
		// TODO Auto-generated method stub
		listeners.add(listener);
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
<<<<<<< HEAD
		// done
		listeners.remove(listener);
=======
		listeners.remove(listener);
		// TODO Auto-generated method stub
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public Figure clone() {
		return null;
	}

<<<<<<< HEAD
	

=======
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
}
