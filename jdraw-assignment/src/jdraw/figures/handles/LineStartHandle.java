package jdraw.figures.handles;
import jdraw.figures.AbstractHandle;


import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

//import jdraw.fdanieli.commands.ResizeCommand;
import jdraw.figures.Line;
import jdraw.framework.DrawView;

public class LineStartHandle extends AbstractHandle {

	Point end;
	Point start;
	Line line;
	
	public LineStartHandle(Line f) {
		super(f);
		end=new Point(f.getEnd());
		start=new Point(f.getStart());
		line=f;
	}

	@Override
	public Point getLocation() {		
		return line.getStart();
	}
	
	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		end=line.getEnd();
		start=line.getStart();
	}
	
	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		
		Point newEnd=new Point(end);
		Point newStart=new Point(x,y);
		
		line.setBounds(newStart, newEnd);

	}
	
	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		
		Point oldStart=new Point(start);
		Point oldEnd=new Point(end);
		
		Point newEnd=new Point(end);
		Point newStart=new Point(x,y);
		
		
		//ResizeCommand resizeCmd=new ResizeCommand(this.getOwner(), 
		//		oldStart, oldEnd, newStart, newEnd);
		//v.getModel().getDrawCommandHandler().addCommand(resizeCmd);
	}

}
