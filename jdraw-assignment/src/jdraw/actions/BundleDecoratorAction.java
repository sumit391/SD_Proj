package jdraw.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import jdraw.figures.BundleDecorator;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class BundleDecoratorAction extends AbstractAction implements MenuListener{

	private DrawView view;
	
	public BundleDecoratorAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.NAME, "Prevent Resizing");
		menu.addMenuListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		LinkedList<Figure> selection=new LinkedList<Figure>(view.getSelection());
		
		for(Figure f: selection){
			Figure decoratedFigure=new BundleDecorator(f);
			
			int index=((LinkedList<Figure>)(view.getModel().getFigures())).indexOf(f);
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