package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//import jdraw.fdanieli.commands.ResizeCommand;
import jdraw.figures.AbstractHandle;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class NHandle extends AbstractHandle{

	public NHandle(Figure f) {
		super(f);
	}

	@Override
	public Point getLocation() {
		Rectangle border=getOwner().getBounds();
		
		//North:
		return new Point(border.x+border.width/2, border.y);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle startingBoundary=getStartingBoundary();
		
		int x0=startingBoundary.x;
		int y0=startingBoundary.y;
		int h0=startingBoundary.height;
		int w0=startingBoundary.width;
		double ratioWH=startingBoundary.width/startingBoundary.height;

		Point newOrigin=new Point(x0, y);
		Point newCorner=new Point(x0+w0, y0+h0);
				
		if(e.isShiftDown()){
			newOrigin.x=x0+(int)((double)(y-y0)/2*ratioWH);
			newCorner.x=x0+w0-(int)((double)(y-y0)/2*ratioWH);
		}else{
			if(e.isControlDown()){
				newCorner.y= 2*y0+h0-y;
				}
		}
	
		getOwner().setBounds(newOrigin, newCorner);	
	}
	
	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle startingBoundary=getStartingBoundary();
		
		int x0=startingBoundary.x;
		int y0=startingBoundary.y;
		int h0=startingBoundary.height;
		int w0=startingBoundary.width;
		
		Point origin=new Point(x0,y0);
		Point corner=new Point(x0+w0, y0+h0);

		Point newOrigin=new Point(x0, y);
		Point newCorner=new Point(x0+w0, y0+h0);
		
		//ResizeCommand resizeCmd=new ResizeCommand(this.getOwner(), origin, corner, newOrigin, newCorner);
		//v.getModel().getDrawCommandHandler().addCommand(resizeCmd);
	}

}
