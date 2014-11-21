package jdraw.actions;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

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
import jdraw.framework.FigureGroup;

@SuppressWarnings("serial")
public class UngroupFiguresAction extends AbstractAction implements MenuListener{

	DrawView view;
	
	public UngroupFiguresAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(AbstractFigureTool.IMAGES + "group.png")));
		putValue(Action.SHORT_DESCRIPTION, "Ungroups selected figure");
		putValue(Action.NAME, "Ungroup");
		putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl U"));
		menu.addMenuListener(this);	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LinkedList<Figure> selection=new LinkedList<Figure>();
		selection.addAll(view.getSelection());
		
		if(selection==null || selection.isEmpty()){
			return;
		}
		
		for(Figure f: selection){
			if(f instanceof FigureGroup){//if figure itself is a group do type cast FigureGroup
				LinkedList<Figure> parts=(LinkedList<Figure>) ((FigureGroup) f).getFigureParts();
				for(Figure part: parts){
					view.getModel().addFigure(part);
					view.addToSelection(part);
				}
				view.getModel().removeFigure(f);
				view.removeFromSelection(f);
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