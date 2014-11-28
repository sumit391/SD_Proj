package jdraw.actions;

import java.util.LinkedList;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import jdraw.figures.GroupFigure;
import jdraw.figures.AbstractFigureTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class GroupFiguresAction extends AbstractAction implements MenuListener{

	DrawView view;
	
	public GroupFiguresAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(AbstractFigureTool.IMAGES + "group.png")));
		putValue(Action.SHORT_DESCRIPTION, "Groups all selected figures");
		putValue(Action.NAME, "Group");
		putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl G"));

		menu.addMenuListener(this);	// how does this work
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//less than 2 figures in the selection
		if(view.getSelection()==null || view.getSelection().size()<2){
			return;
		}

		LinkedList<Figure> figureToGroup= new LinkedList<Figure>();
		for(Figure f: view.getModel().getFigures()){
			if(view.getSelection().contains(f)){
				figureToGroup.add(f);//add the figure 
			}
		}

		//creating a group figure
		GroupFigure newGroup=new GroupFigure(figureToGroup);
		//remove figures to be grouped from draw model
		for (Figure f: figureToGroup){
			view.getModel().removeFigure(f);
			//add the method to give the setindex here
			view.removeFromSelection(f);
		}
		//add figure group to draw model
		view.getModel().addFigure(newGroup);
		view.addToSelection(newGroup);
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