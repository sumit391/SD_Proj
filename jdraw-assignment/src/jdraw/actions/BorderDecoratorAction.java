package jdraw.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import jdraw.figures.BorderDecorator;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class BorderDecoratorAction extends AbstractAction implements MenuListener{

	private DrawView view;
	
	public BorderDecoratorAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.NAME, "Add Border");
		menu.addMenuListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		LinkedList<Figure> selection=new LinkedList<Figure>(view.getSelection());
		// for each figure create a decorator
		for(Figure f: selection){
			Figure decoratedFigure=new BorderDecorator(f);
			
			int index=((LinkedList<Figure>)(view.getModel().getFigures())).indexOf(f);
			//remove the figure and add the decorated figure
			view.getModel().removeFigure(f);
			view.removeFromSelection(f);
			
			view.getModel().addFigure(decoratedFigure);
			view.getModel().setFigureIndex(decoratedFigure, index);
			view.addToSelection(decoratedFigure);
			
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