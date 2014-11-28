package jdraw.commands;


import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;
//command to add new figures in the model in the draw tools
public class AddFigureCommand implements DrawCommand{
	//figure and the model reference
	private Figure added;
	private DrawModel model;
	
	public AddFigureCommand(Figure added, DrawModel model){
		this.added=added;
		this.model=model;
	}
	
	@Override
	public void redo() {
		model.addFigure(added);
	}

	@Override
	public void undo() {
		model.removeFigure(added);
	}
}