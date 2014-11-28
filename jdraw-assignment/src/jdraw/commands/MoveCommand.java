package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;
// implementing the move functionality
public class MoveCommand implements DrawCommand{

	private Figure toMove;
	private int dx;
	private int dy;
	
	public MoveCommand(Figure f, int dx, int dy){
		toMove=f;
		this.dx=dx;
		this.dy=dy;
	}
	
	@Override
	public void redo() {
		toMove.move(dx, dy);
	}

	@Override
	public void undo() {
		toMove.move(-dx, -dy);
	}

}