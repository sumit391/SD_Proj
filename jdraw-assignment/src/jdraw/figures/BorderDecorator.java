package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class BorderDecorator extends AbstractDecorator{

	private static final int FRAME_DISTANCE=5;	
	
	public BorderDecorator(Figure f) {
		super(f);
	}

	@Override
	public void draw(Graphics g) {		
		//get the bounding rectangle
		Rectangle b=getBounds();
		
		g.setColor(Color.BLUE);
		g.drawLine(b.x, b.y, b.x+b.width, b.y);
		g.drawLine(b.x, b.y, b.x, b.y+b.height);
		
		g.setColor(Color.RED);
		g.drawLine(b.x+b.width, b.y+b.height, b.x+b.width, b.y);
		g.drawLine(b.x+b.width, b.y+b.height, b.x, b.y+b.height);
	
		super.draw(g);
	}
	
	@Override
	public boolean contains(int x, int y){
		return getBounds().contains(x,y);
	}
	
	@Override
	public Rectangle getBounds(){
		//changing the bounds for the decorator
		Rectangle b=super.getBounds();
		b.x-=FRAME_DISTANCE;
		b.y-=FRAME_DISTANCE;
		b.width+=2*FRAME_DISTANCE;
		b.height+=2*FRAME_DISTANCE;
		
		return b;
	}
	
	@Override
	public Object clone(){
		BorderDecorator newF;
		newF=(BorderDecorator) super.clone();
		return newF;
	}

}