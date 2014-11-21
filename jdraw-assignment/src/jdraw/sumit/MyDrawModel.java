/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.sumit;

import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelEvent.Type;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Sumit Chouhan
 *
 */
public class MyDrawModel implements DrawModel, FigureListener {

	
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	private LinkedList<DrawModelListener> drawModelListeners= new LinkedList<DrawModelListener>();
	@Override
	public void addFigure(Figure f) {
		// done
		figures.add(f);
		notifyAllModelListeners(f,DrawModelEvent.Type.FIGURE_ADDED);
	}

	@Override
	public Iterable<Figure> getFigures() {
		// done
		return figures;
		
	}

	@Override
	public void removeFigure(Figure f) {
		// done  
		figures.remove(f);
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		//done  
		drawModelListeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		// done 
		drawModelListeners.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation from the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		// done
		if (figures.indexOf(f)!=index){
			figures.remove(f);
			figures.add(index,f);
			notifyAllModelListeners(f,DrawModelEvent.Type.FIGURE_CHANGED);
		}
	}

	private void notifyAllModelListeners(Figure f, DrawModelEvent.Type type) {
		// TODO Auto-generated method stub
		for (DrawModelListener dml : drawModelListeners){
			dml.modelChanged(new DrawModelEvent(this, f, type));
		}
	}

	@Override
	public void removeAllFigures() {
		// done
		for (Figure f: figures){
			removeFigure(f);
		}
		notifyAllModelListeners(null,DrawModelEvent.Type.DRAWING_CLEARED);
	}

	@Override
	public void figureChanged(FigureEvent e) {
		// done
		notifyAllModelListeners(null,DrawModelEvent.Type.FIGURE_CHANGED);
		
	}

}	
