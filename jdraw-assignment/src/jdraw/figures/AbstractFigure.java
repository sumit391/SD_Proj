package jdraw.figures;

import java.util.LinkedList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;


/**
 * Represents Figures.
 * 
 * @author Sumit Chouhan
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractFigure implements Figure{

	private LinkedList<FigureListener> listeners=new LinkedList<FigureListener>();

	public void notifyAllListeners(){
		for(FigureListener l: listeners){
			l.figureChanged(new FigureEvent(this));
		}
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		if(!listeners.contains(listener)){
			listeners.add(listener);
		}
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	public Figure clone() {
		AbstractFigure newF;
		try {
			newF=(AbstractFigure) super.clone();
			newF.listeners=new LinkedList<FigureListener>(this.listeners);
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
		
		return newF;
	}

}