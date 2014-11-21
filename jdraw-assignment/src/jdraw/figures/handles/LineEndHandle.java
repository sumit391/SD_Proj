
package jdraw.figures.handles;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

//import jdraw.fdanieli.commands.ResizeCommand;
import jdraw.figures.Line;
import jdraw.framework.DrawView;
import jdraw.figures.AbstractHandle;
public class LineEndHandle extends AbstractHandle {

	Point start;
	Point end;
	Line line;
	
	public LineEndHandle(Line f) {
		super(f);
		start=new Point(f.getStart());
		end=new Point(f.getEnd());
		line=f;
	}

	@Override
	public Point getLocation() {		
		return line.getEnd();
	}
	
	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		start=line.getStart();
		end=line.getEnd();
	}
	
	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		
		Point newStart=new Point(start);
		Point newEnd=new Point(x,y);
		
		line.setBounds(newStart, newEnd);
	}
	
	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		
		Point oldStart=new Point(start);
		Point oldEnd=new Point(end);
		
		Point newStart=new Point(start);
		Point newEnd=new Point(x,y);
		
		
		//ResizeCommand resizeCmd=new ResizeCommand(this.getOwner(), 
		//		oldStart, oldEnd, newStart, newEnd);
		//v.getModel().getDrawCommandHandler().addCommand(resizeCmd);
	}

}
