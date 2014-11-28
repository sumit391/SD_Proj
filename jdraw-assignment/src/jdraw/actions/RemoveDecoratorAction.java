package jdraw.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import jdraw.figures.AbstractDecorator;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class RemoveDecoratorAction extends AbstractAction implements MenuListener{

	private DrawView view;
	
	public RemoveDecoratorAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.NAME, "Remove Decorator");
		menu.addMenuListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		LinkedList<Figure> selection=new LinkedList<Figure>(view.getSelection());
		
		for(Figure f: selection){
			if(f instanceof AbstractDecorator){
				Figure inner=((AbstractDecorator) f).getOwner();
				int index=((LinkedList<Figure>)(view.getModel().getFigures())).indexOf(f);
				//remove the decorated figure and add the original figure
				view.removeFromSelection(f);
				view.getModel().removeFigure(f);
				
				view.addToSelection(inner);
				view.getModel().addFigure(inner);
				view.getModel().setFigureIndex(inner, index);
			}
		}
		
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {		
	}

	@Override
	public void menuSelected(MenuEvent arg0) {		
	}
	
}