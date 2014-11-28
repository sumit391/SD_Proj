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

import jdraw.commands.PasteCommand;
import jdraw.figures.AbstractFigureTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class PasteAction extends AbstractAction implements MenuListener{

	protected static LinkedList<Figure> modifiedFigures=new LinkedList<Figure>();
	protected static int timesPasted=0;
	private static final int SLIDE=25;//moving clones by this amount of pixels
	private DrawView view;
	
	public PasteAction(DrawView view, JMenu menu){
		this.view=view;
		putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(AbstractFigureTool.IMAGES + "selection.png")));
		putValue(Action.NAME, "Paste");
		putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
		menu.addMenuListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!modifiedFigures.isEmpty()){
			view.clearSelection(); //clears the current selection
			timesPasted++;//increment  no of times pasted
			//create new linkedlist
			LinkedList<Figure> copiedFigures=new LinkedList<Figure>();
			
			for(Figure f: modifiedFigures){
				Figure newF=(Figure) f.clone();//clone the figure
				newF.move(timesPasted*SLIDE,timesPasted*SLIDE);//move the newly created figures
				
				copiedFigures.add(newF); // add the figures to the new linkedlist
				view.getModel().addFigure(newF); //add everything to the model, listeners are registered
				view.addToSelection(newF);//add it to the current selection
			}
			//adding the command
			PasteCommand pstCmd=new PasteCommand(modifiedFigures, copiedFigures, view.getModel());
			view.getModel().getDrawCommandHandler().addCommand(pstCmd);
			
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