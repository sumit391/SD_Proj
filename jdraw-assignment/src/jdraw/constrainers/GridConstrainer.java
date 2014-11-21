package jdraw.constrainers;

import java.awt.Point;

import jdraw.framework.PointConstrainer;

public class GridConstrainer implements PointConstrainer {

	private final int STEP;
	
	public GridConstrainer(int step){
		STEP=step;
	}
	
	@Override
	public Point constrainPoint(Point p) {
		return new Point(p.x/STEP*STEP, p.y/STEP*STEP);//p.x is integer hence gives the grid values
	}

	@Override
	public int getStepX(boolean right) {
		return STEP;
	}

	@Override
	public int getStepY(boolean down) {
		return STEP;
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