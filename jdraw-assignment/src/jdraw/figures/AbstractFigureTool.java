package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
//import jdraw.fdanieli.commands.AddFigureCommand;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

public abstract class AbstractFigureTool implements DrawTool {
	
	public static final String IMAGES = "/images/";
	private String name="";
	private String imageName="";

	private DrawContext context;
	private DrawView view;
	private AbstractFigure newFig = null;
	private Point anchor = null;
	public AbstractFigureTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}
	public AbstractFigureTool(DrawContext context, String name, String imageName) {
		this.context = context;
		this.view = context.getView();
		this.name=name;
		this.imageName=imageName;
	}
	public void deactivate() {
		this.context.showStatusText("");
	}
	public void activate() {
		this.context.showStatusText(name+" Mode");
	}
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newFig != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newFig=createNewFigure(x, y);
		view.getModel().addFigure(newFig);
	}
	
	/**
	 * Creates a new Figure object by specifying the kind of figure it is
	 * @param x x-coordinate of origin of figure
	 * @param y y-coordinate of origin of figure
	 * @return 
	 */
	public abstract AbstractFigure createNewFigure(int x, int y);

	/**
	 * During a mouse drag, the Figure will be resized according to the mouse
	 * position. The status bar shows the current size.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
	 */
	public void mouseDrag(int x, int y, MouseEvent e) {
		view.getModel().removeFigure(newFig);
		newFig=createNewFigure(x, y);
		view.getModel().addFigure(newFig);
		newFig.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newFig.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * When the user releases the mouse, the Rectangle object is updated
	 * according to the color and fill status settings.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
	 */
	public void mouseUp(int x, int y, MouseEvent e) {
		//newFig.setBounds(anchor, new Point(x, y));
		
		//AddFigureCommand cmd=new AddFigureCommand(newFig, this.view.getModel());
		//this.view.getModel().getDrawCommandHandler().addCommand(cmd);
		
		newFig = null;
		anchor = null;
		this.context.showStatusText(name+" Mode");
		
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}
	
	@Override
	public Icon getIcon() {
		// getResource method is returning null so you get the nullpointerexception
		return new ImageIcon(getClass().getResource(IMAGES + imageName));
		//return new ImageIcon(getClass().getResource("/images/line.png"));
	}

	@Override
	public String getName() {
		return name;
	}
}