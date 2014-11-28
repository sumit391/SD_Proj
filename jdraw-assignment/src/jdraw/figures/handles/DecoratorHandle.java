package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractDecorator;
import jdraw.figures.AbstractHandle;
import jdraw.framework.DrawView;
import jdraw.framework.FigureHandle;

public class DecoratorHandle extends AbstractHandle{

	FigureHandle state;
	
	public DecoratorHandle(FigureHandle h, AbstractDecorator abstractDecorator) {
		super(abstractDecorator);
		state=h;
	}
	
	@Override
	public Point getLocation() {
		return state.getLocation();
	}

	@Override
	public Cursor getCursor() {
		return state.getCursor();
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.startInteraction(x, y, e, v);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.dragInteraction(x, y, e, v);
	}
	
	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.stopInteraction(x, y, e, v);
	}

}