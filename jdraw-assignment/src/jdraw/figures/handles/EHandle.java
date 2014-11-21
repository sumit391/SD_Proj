package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//import jdraw.fdanieli.commands.ResizeCommand;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.figures.AbstractHandle;
public class EHandle extends AbstractHandle{

	public EHandle(Figure f) {
		super(f);
	}

	@Override
	public Point getLocation() {
		Rectangle border=getOwner().getBounds();
		
		//East:
		return new Point(border.x+border.width, border.y+border.height/2);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle startingBoundary=getStartingBoundary();
		
		int x0=startingBoundary.x;
		int y0=startingBoundary.y;
		int h0=startingBoundary.height;
		int w0=startingBoundary.width;
		double ratioWH=startingBoundary.width/startingBoundary.height;
		
		Point newOrigin=new Point(x0, y0);
		Point newCorner=new Point(x, y0+h0);
				
		if(e.isShiftDown()){
			newOrigin.y=y0-(int)((double)(x-x0-w0)/(2*ratioWH));
			newCorner.y=y0+h0+(int)((double)(x-x0-w0)/(2*ratioWH));
		}else{
			if(e.isControlDown()){
				newOrigin.x= x0-(x-x0-w0);
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

		Point newOrigin=new Point(x0, y0);
		Point newCorner=new Point(x, y0+h0);
		
		//ResizeCommand resizeCmd=new ResizeCommand(this.getOwner(), origin, corner, newOrigin, newCorner);
		//v.getModel().getDrawCommandHandler().addCommand(resizeCmd);
	}

}
