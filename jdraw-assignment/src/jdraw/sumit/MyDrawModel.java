/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.sumit;

import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
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
	private LinkedList<DrawModelListener> drawModelListeners = new LinkedList<DrawModelListener>();
	@Override
	public void addFigure(Figure f) {
		// TODO to be implemented
		figures.add(f);
		//System.out.println("StdDrawModel.addFigure has to be implemented");
	}

	@Override
	public Iterable<Figure> getFigures() {
		// TODO to be implemented  
		return figures;
		//System.out.println("StdDrawModel.getFigures has to be implemented");
		//return new LinkedList<Figure>(); // Only guarantees, that the application starts -- has to be replaced !!!
	}

	@Override
	public void removeFigure(Figure f) {
		// TODO to be implemented
		figures.remove(f);
		//System.out.println("StdDrawModel.removeFigure has to be implemented");
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		// TODO to be implemented  
		drawModelListeners.add(listener);
		//System.out.println("StdDrawModel.addModelChangeListener has to be implemented");
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		// TODO to be implemented  
		drawModelListeners.remove(listener);
		//System.out.println("StdDrawModel.removeModelChangeListener has to be implemented");
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
		// TODO to be implemented
		if (figures.indexOf(f)!=index){
			figures.remove(f);
			figures.add(index,f);
			//notifying model listenrs
		}
		//System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	}

	@Override
	public void removeAllFigures() {
		// TODO to be implemented
		
		for (Figure f : figures){
			removeFigure(f);
		}
		//System.out.println("StdDrawModel.removeAllFigures has to be implemented");
	}


	@Override
	public void figureChanged(FigureEvent e) {
		// TODO Auto-generated method stub
		
	}

}
