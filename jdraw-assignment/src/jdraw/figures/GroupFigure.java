package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdraw.figures.handles.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;
import jdraw.framework.FigureHandle;

@SuppressWarnings("serial")
public class GroupFigure extends AbstractFigure implements FigureGroup{
	
	private LinkedList<Figure> components;
	// handles for the group figure
	private ArrayList<FigureHandle> handles=new ArrayList<FigureHandle>(4);
	// constructor initializes the components and adds the handles
	public GroupFigure(LinkedList<Figure> components){
		this.components=new LinkedList<Figure>(components);
		
		handles.add(new NEHandle(this));
		handles.add(new SEHandle(this));
		handles.add(new NWHandle(this));
		handles.add(new SWHandle(this));	
	}
	// when is the draw method called
	@Override
	public void draw(Graphics g) {
		for(Figure f: components){
			f.draw(g);
		}
	}

	@Override
	public void move(int dx, int dy) {
		for(Figure f: components){
			f.move(dx, dy);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return getBounds().contains(x,y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {		
	}

	@Override
	public Rectangle getBounds() {
		//get the bounds of the first figure
		Rectangle border=components.getFirst().getBounds();
		
		for(Figure f: components){
			border.add(f.getBounds());
		}
		return border;
	}

	@Override
	public List<FigureHandle> getHandles() {
		return handles;
	}

	@Override
	public Iterable<Figure> getFigureParts() {
		return components;
	}
	/*
	@Override
	public Object clone(){
		GroupFigure newF;
		newF=(GroupFigure) super.clone();
		
		LinkedList<Figure> newComponents= new LinkedList<Figure>();
		for(Figure f: components){
			newComponents.add((Figure) f.clone());
		}
		newF.components=newComponents;
		
		newF.handles= new ArrayList<FigureHandle>(4);
		newF.handles.add(new NEHandle(newF));
		newF.handles.add(new NWHandle(newF));
		newF.handles.add(new SWHandle(newF));
		newF.handles.add(new SEHandle(newF));
		
		return newF;
	}*/
	 

}