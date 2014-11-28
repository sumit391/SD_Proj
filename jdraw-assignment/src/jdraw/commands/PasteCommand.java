package jdraw.commands;

import java.util.LinkedList;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class PasteCommand implements DrawCommand{
	
	private LinkedList<Figure> original;
	private LinkedList<Figure> copy;
	private DrawModel model;
	
	public PasteCommand(LinkedList<Figure> original, LinkedList<Figure> copy, DrawModel model){
		this.original=original;
		this.copy=copy;
		this.model=model;
	}
	
	@Override
	public void redo() {
		for(Figure f: copy){
			model.addFigure(f);
		}
	}

	@Override
	public void undo() {
		for(Figure f: copy){
			model.removeFigure(f);
		}
		
		//this is to undo cut too..but maybe I should add a CutCommand..
		for(Figure f: original){
			if(!((LinkedList<Figure>)(model.getFigures())).contains(f)){
				model.addFigure(f);
			}
		}
	}
}