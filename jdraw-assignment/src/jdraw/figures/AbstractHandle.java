	package jdraw.figures;


	import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

	import jdraw.figures.Rect;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

	public abstract class AbstractHandle implements FigureHandle,FigureListener {

		private static final int SIZE=6;
		private final Figure owner;
		private Rectangle startingBoundary;
	
		public AbstractHandle(Figure f){
			this.owner=f;
			startingBoundary=f.getBounds();
		}
	
		@Override
		public Figure getOwner() {
			return owner;
		}


		@Override
		public void draw(Graphics g) {
			Point loc=getLocation();
			Rect handle=new Rect(loc.x-SIZE/2, loc.y-SIZE/2, SIZE, SIZE);
			handle.draw(g);
		}


		@Override
		public boolean contains(int x, int y) {
			Point loc=getLocation();
			Rectangle handle=new Rectangle(loc.x-SIZE/2, loc.y-SIZE/2, SIZE, SIZE);
			return handle.contains(x, y);
		}

		@Override
		public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
			startingBoundary=owner.getBounds();
		}

	
		public Rectangle getStartingBoundary(){
			return startingBoundary;
		}
		public void figureChanged(FigureEvent e){
			
		}
		
	}