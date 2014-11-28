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

import jdraw.figures.AbstractFigureTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class CopyAction extends AbstractAction implements MenuListener{

	private DrawView view;
		
	public CopyAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(AbstractFigureTool.IMAGES + "selection.png")));
		putValue(Action.NAME, "Copy");
		putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl C"));
		menu.addMenuListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(view.getSelection().isEmpty()){
			return;
		}
		
		PasteAction.modifiedFigures= new LinkedList<Figure>(view.getSelection());//how is paste action visible here
		PasteAction.timesPasted=0;
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		setEnabled(true);//prevents accelerator keys from getting deactivated
	}

	@Override
	public void menuSelected(MenuEvent arg0) {		
		setEnabled(view.getSelection().size() > 0);
	}
		
}