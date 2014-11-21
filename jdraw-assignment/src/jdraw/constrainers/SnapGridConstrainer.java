package jdraw.constrainers;

import java.awt.Point;
import java.util.LinkedList;

import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;

public class SnapGridConstrainer implements PointConstrainer {

	private final static int SNAP_DISTANCE=15;
	private DrawView view;
	private boolean snapped=false;
	private Figure figureAdded=null;
	private Point fixingPoint;
	
	private DrawModelListener listener;
	
	public SnapGridConstrainer(DrawView view){
		this.view=view;
		listener=new DrawModelListener(){
			public void modelChanged(DrawModelEvent e) {
				if (e.getType() == DrawModelEvent.Type.FIGURE_CHANGED) {
					figureAdded=e.getFigure();
				}else{
					figureAdded=null;
				}
			}
		};
		view.getModel().addModelChangeListener(listener);
	}
	
	@Override
	public Point constrainPoint(Point p) {
		
		if(snapped){
			if(fixingPoint.distance(p)>SNAP_DISTANCE){
				snapped=false;
				fixingPoint=p;
				return p;
			}else{
				return fixingPoint;
			}
		}
		
		fixingPoint=p;
		
		LinkedList<Figure> selection= new LinkedList<Figure>();
		LinkedList<Figure> notInSelection=new LinkedList<Figure>();
		
		selection.addAll(view.getSelection());
		if (figureAdded!=null){
			selection.add(figureAdded);
		}
		
		notInSelection.addAll((LinkedList<Figure>) view.getModel().getFigures());
		notInSelection.removeAll(selection);	//removing the selected figure from the list
		
		if(selection.isEmpty() || notInSelection.isEmpty()){
			return p;
		}
		
		Point closerPointSel=selection.getFirst().getHandles().get(0).getLocation();
		Point closerPointNotSel=notInSelection.getFirst().getHandles().get(0).getLocation();
		double distance=closerPointSel.distance(closerPointNotSel);
		
		for(Figure f: selection){
			for(FigureHandle h: f.getHandles()){
				for(Figure of: notInSelection){
					for(FigureHandle oh: of.getHandles()){
						if(h.getLocation().distance(oh.getLocation())<distance){
							distance=h.getLocation().distance(oh.getLocation());
							closerPointSel=h.getLocation();
							closerPointNotSel=oh.getLocation();
						}
					}
				}
			}
		}
		
		if(distance<SNAP_DISTANCE){
			snapped=true;
			fixingPoint=new Point(fixingPoint.x+closerPointNotSel.x-closerPointSel.x,
					fixingPoint.y+closerPointNotSel.y-closerPointSel.y);
			return fixingPoint;
		}else{
			fixingPoint=p;
			return fixingPoint;
		}
		
	}

	@Override
	public int getStepX(boolean right) {
		return 1;
	}

	@Override
	public int getStepY(boolean down) {
		return 1;
	}

	@Override
	public void activate() {		
	}

	@Override
	public void deactivate() {		
	}

	@Override
	public void mouseDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp() {
		// TODO Auto-generated method stub
		
	}

}