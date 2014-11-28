package jdraw.commands;

import java.util.LinkedList;
import java.util.ListIterator;

import jdraw.framework.DrawCommand;
// implements list of commands
public class GroupCommand implements DrawCommand{
	
	LinkedList<DrawCommand> commands=new LinkedList<DrawCommand>();

	public GroupCommand(LinkedList<DrawCommand> commandList) {
		commands=commandList;
	}

	@Override
	public void redo() {
		for(DrawCommand cmd: commands){
			cmd.redo();
		}
		
	}

	@Override
	public void undo() {
		ListIterator<DrawCommand> li = commands.listIterator(commands.size());

		while(li.hasPrevious()) {
			li.previous().undo();
		}
	}

}