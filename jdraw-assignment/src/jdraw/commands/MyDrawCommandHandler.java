package jdraw.commands;

import java.util.LinkedList;
import java.util.Stack;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;
//implementation of the drawcommandhandler interface
public class MyDrawCommandHandler implements DrawCommandHandler{

	private boolean scriptOpened=false;
	private LinkedList<DrawCommand> commandList;//list to add to the script
	//undo and redo commands stored in a stack
	
	private Stack<DrawCommand> redoStack=new Stack<DrawCommand>();
	private Stack<DrawCommand> undoStack=new Stack<DrawCommand>();
	
	
	@Override
	public void addCommand(DrawCommand cmd) {
		redoStack.clear();
		
		if(scriptOpened){
			commandList.add(cmd);
		}else{
			undoStack.push(cmd);
		}
	}

	@Override
	public void undo() {
		if(undoPossible()){
			DrawCommand command=undoStack.pop();
			command.undo();
			redoStack.push(command);
		}
	}

	@Override
	public void redo() {
		if(redoPossible()){
			DrawCommand command=redoStack.pop();
			command.redo();
			undoStack.push(command);
		}
		
	}

	@Override
	public boolean undoPossible() {
		return (!undoStack.isEmpty());
	}

	@Override
	public boolean redoPossible() {
		return (!redoStack.isEmpty());
	}

	//commands that are executed within a script
	//are combined to give a single macro command
	//a single macro command to undo a move
	//instead of one for each pixel of movement
	//this is an example of compoosite pattern
	@Override
	public void beginScript() {
		if(scriptOpened){
			throw new IllegalStateException();
		}
		
		scriptOpened=true;
		commandList=new LinkedList<DrawCommand>();
	}

	@Override
	public void endScript() {
		if(!commandList.isEmpty()){
			GroupCommand commandSerie=new GroupCommand(commandList);
			undoStack.push(commandSerie);
		}
		commandList=null;
		scriptOpened=false;
	}

	@Override
	public void clearHistory() {
		commandList.clear();
		undoStack.clear();
		redoStack.clear();
		scriptOpened=false;
	}

}