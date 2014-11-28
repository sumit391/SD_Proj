package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import jdraw.figures.handles.DecoratorHandle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

@SuppressWarnings("serial")
public class AbstractDecorator extends AbstractFigure implements FigureListener{

	private Figure owner;
	private ArrayList<FigureHandle> handles;

	
	public AbstractDecorator(Figure f){
		owner=f;
		f.addFigureListener(this);
		handles=new ArrayList<FigureHandle>(f.getHandles().size());
		
		for(FigureHandle h: f.getHandles()){
			handles.add(new DecoratorHandle(h, this));
		}
	}

	public Figure getOwner() {
		return owner;
	}

	public void setOwner(Figure owner) {
		this.owner = owner;
	}
	
	@Override
	public void draw(Graphics g) {		
		getOwner().draw(g);
	}

	@Override
	public void move(int dx, int dy) {
		getOwner().move(dx, dy);
		
		
		notifyAllListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return getOwner().contains(x,y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		getOwner().setBounds(origin, corner);
		notifyAllListeners();
	}

	@Override
	public Rectangle getBounds() {
		return getOwner().getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		return handles;
	}

	@Override
	public void figureChanged(FigureEvent e) {
		notifyAllListeners();
	}
	
	@Override
	public Object clone(){
		AbstractDecorator newF;
		newF=(AbstractDecorator) super.clone();
		//deep clone everything
		newF.owner=(Figure) this.owner.clone();
		newF.handles=new ArrayList<FigureHandle>(newF.owner.getHandles().size());
		
		for(FigureHandle h: newF.owner.getHandles()){
			newF.handles.add(new DecoratorHandle(h, newF));
		}
		
		return newF;
	}
	
	
}	