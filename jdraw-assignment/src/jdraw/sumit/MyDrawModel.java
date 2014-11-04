/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.sumit;

import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
<<<<<<< HEAD
import jdraw.framework.DrawModelEvent.Type;
=======
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
<<<<<<< HEAD
 * @author TODO add your name here
=======
 * @author Sumit Chouhan
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
 *
 */
public class MyDrawModel implements DrawModel, FigureListener {

<<<<<<< HEAD
	
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	private LinkedList<DrawModelListener> drawModelListeners= new LinkedList<DrawModelListener>();
	@Override
	public void addFigure(Figure f) {
		// done
		figures.add(f);
		notifyAllModelListeners(f,DrawModelEvent.Type.FIGURE_ADDED);
=======
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	private LinkedList<DrawModelListener> drawModelListeners = new LinkedList<DrawModelListener>();
	@Override
	public void addFigure(Figure f) {
		// TODO to be implemented
		figures.add(f);
		//System.out.println("StdDrawModel.addFigure has to be implemented");
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public Iterable<Figure> getFigures() {
<<<<<<< HEAD
		// done
		return figures;
		
=======
		// TODO to be implemented  
		return figures;
		//System.out.println("StdDrawModel.getFigures has to be implemented");
		//return new LinkedList<Figure>(); // Only guarantees, that the application starts -- has to be replaced !!!
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public void removeFigure(Figure f) {
<<<<<<< HEAD
		// done  
		figures.remove(f);
=======
		// TODO to be implemented
		figures.remove(f);
		//System.out.println("StdDrawModel.removeFigure has to be implemented");
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
<<<<<<< HEAD
		//done  
		drawModelListeners.add(listener);
=======
		// TODO to be implemented  
		drawModelListeners.add(listener);
		//System.out.println("StdDrawModel.addModelChangeListener has to be implemented");
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
<<<<<<< HEAD
		// done 
		drawModelListeners.remove(listener);
=======
		// TODO to be implemented  
		drawModelListeners.remove(listener);
		//System.out.println("StdDrawModel.removeModelChangeListener has to be implemented");
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
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
<<<<<<< HEAD
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
=======
		// TODO to be implemented
		if (figures.indexOf(f)!=index){
			figures.remove(f);
			figures.add(index,f);
			//notifying model listenrs
		}
		//System.out.println("StdDrawModel.setFigureIndex has to be implemented");
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
	}

	@Override
	public void removeAllFigures() {
<<<<<<< HEAD
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
=======
		// TODO to be implemented
		
		for (Figure f : figures){
			removeFigure(f);
		}
		//System.out.println("StdDrawModel.removeAllFigures has to be implemented");
	}


	@Override
	public void figureChanged(FigureEvent e) {
		// TODO Auto-generated method stub
>>>>>>> a903ca582a53c748e6b938fce902a5246e41f7fd
		
	}

}
