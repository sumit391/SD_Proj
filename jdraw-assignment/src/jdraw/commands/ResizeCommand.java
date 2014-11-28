package jdraw.commands;

import java.awt.Point;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

public class ResizeCommand implements DrawCommand{

	private Figure toResize;
	private Point newOrigin;
	private Point newCorner;
	private Point oldOrigin;
	private Point oldCorner;
	
	public ResizeCommand(Figure f, Point oldOrigin, Point oldCorner, 
			Point newOrigin, Point newCorner){
		this.toResize=f;
		this.newOrigin=new Point(newOrigin);
		this.newCorner=new Point(newCorner);
		this.oldOrigin=new Point(oldOrigin);
		this.oldCorner=new Point(oldCorner);
	}
	
	@Override
	public void redo() {
		toResize.setBounds(newOrigin, newCorner);
	}

	@Override
	public void undo() {
		toResize.setBounds(oldOrigin, oldCorner);		
	}

}