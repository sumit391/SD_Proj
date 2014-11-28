/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.sumit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.actions.CopyAction;
import jdraw.actions.CutAction;
import jdraw.actions.GroupFiguresAction;
import jdraw.actions.PasteAction;
import jdraw.actions.UngroupFiguresAction;
import jdraw.constrainers.GridConstrainer;
import jdraw.constrainers.SnapGridConstrainer;
import jdraw.figures.EllipseTool;
import jdraw.figures.LineTool;
import jdraw.figures.RectTool;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

/**
 * Standard implementation of interface DrawContext.
 * 
 * @see DrawView
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 */
public class StdContext extends AbstractContext {

	/**
	 * Constructs a standard context with a default set of drawing tools.
	 * @param view the view that is displaying the actual drawing.
	 */
  public StdContext(DrawView view) {
		super(view, null);
	}
	
  /**
   * Constructs a standard context. The drawing tools available can be parameterized using <code>toolFactories</code>.
   * @param view the view that is displaying the actual drawing.
   * @param toolFactories a list of DrawToolFactories that are available to the user
   */
	public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
		super(view, toolFactories);
	}

	/**
	 * Creates and initializes the "Edit" menu.
	 * 
	 * @return the new "Edit" menu.
	 */
	@Override
	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		final JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		editMenu.add(undo);
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getModel().getDrawCommandHandler().undo();
			}
		});

		final JMenuItem redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
		editMenu.add(redo);
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getModel().getDrawCommandHandler().redo();
			}
		});
		editMenu.addSeparator();

		JMenuItem sa = new JMenuItem("SelectAll");
		sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
		editMenu.add(sa);
		sa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Figure f : getModel().getFigures()) {
					getView().addToSelection(f);
				}
				getView().repaint();
			}
		});

		editMenu.addSeparator();
		//add cut copy and paste functions
		editMenu.add(new JMenuItem(new CutAction(getView(), editMenu)));
		editMenu.add(new JMenuItem(new CopyAction(getView(), editMenu)));
		editMenu.add(new JMenuItem(new PasteAction(getView(), editMenu)));
		
		editMenu.addSeparator();
		
		//adding groupfigureaction
		JMenuItem group = new JMenuItem(new GroupFiguresAction(getView(),editMenu));
		group.setEnabled(true);
		editMenu.add(group);
		//adding ungroupfigureactionn
		JMenuItem ungroup = new JMenuItem(new UngroupFiguresAction(getView(),editMenu));
		ungroup.setEnabled(true);
		editMenu.add(ungroup);

		editMenu.addSeparator();

		JMenu orderMenu = new JMenu("Order...");
		JMenuItem frontItem = new JMenuItem("Bring To Front");
		frontItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bringToFront(getView().getModel(), getView().getSelection());
				getView().repaint();
			}
		});
		orderMenu.add(frontItem);
		JMenuItem backItem = new JMenuItem("Send To Back");
		backItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToBack(getView().getModel(), getView().getSelection());
				getView().repaint();
			}
		});
		orderMenu.add(backItem);
		editMenu.add(orderMenu);

		JMenu grid = new JMenu("Grid");
		//setting the constrainers
		
		getView().setConstrainer(new GridConstrainer(1)); //default option
		
		final JRadioButtonMenuItem snapGrid= new JRadioButtonMenuItem("Snap Grid");
		final JRadioButtonMenuItem noGrid= new JRadioButtonMenuItem("No Grid");
		noGrid.setSelected(true);
		final JRadioButtonMenuItem grid10= new JRadioButtonMenuItem("Grid 10x10");
		final JRadioButtonMenuItem grid50= new JRadioButtonMenuItem("Grid 50x50");
		
		noGrid.addActionListener(new ActionListener(){
			// When the actionevent occurs the objects 	actionPerformed method is invoked
			@Override
			public void actionPerformed(ActionEvent a) { 
				// TODO Auto-generated method stub
				getView().getConstrainer().deactivate();
				getView().setConstrainer(new GridConstrainer(1));
				getView().getConstrainer().activate();
				snapGrid.setSelected(false);
				noGrid.setSelected(true);
				grid10.setSelected(false);
				grid50.setSelected(false);
				getView().repaint();
			}
			
		});
		
		grid10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getView().getConstrainer().deactivate();
				
				getView().setConstrainer(new GridConstrainer(10));
				getView().getConstrainer().activate();
				
				snapGrid.setSelected(false);
				noGrid.setSelected(false);
				grid10.setSelected(true);
				grid50.setSelected(false);
				
				getView().repaint();
			}
		});
		
		grid50.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getView().getConstrainer().deactivate();
				
				getView().setConstrainer(new GridConstrainer(50));
				getView().getConstrainer().activate();
				
				snapGrid.setSelected(false);
				noGrid.setSelected(false);
				grid10.setSelected(false);
				grid50.setSelected(true);
				
				getView().repaint();
			}
		});
		
		snapGrid.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				getView().getConstrainer().deactivate();
				getView().setConstrainer(new SnapGridConstrainer(getView()));
				getView().getConstrainer().activate();
				
				snapGrid.setSelected(true);
				noGrid.setSelected(false);
				grid10.setSelected(false);
				grid50.setSelected(false);
				
				getView().repaint();
			}
		});
		grid.add(noGrid);
		grid.add(grid10);
		grid.add(grid50);
		grid.add(snapGrid);
		editMenu.add(grid);
		
		return editMenu;
	}

	/**
	 * Creates and initializes items in the file menu.
	 * 
	 * @return the new "File" menu.
	 */
	@Override
	protected JMenu createFileMenu() {
	  JMenu fileMenu = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		fileMenu.add(open);
		open.setAccelerator(KeyStroke.getKeyStroke("control O"));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOpen();
			}
		});

		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke("control S"));
		fileMenu.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return fileMenu;
	}

	@Override
	protected void doRegisterDrawTools() {
		// TODO Add new figure tools here
		// use factory pattern
		/*DrawTool rectangleTool = new RectTool(this);
		addTool(rectangleTool);
		DrawTool lineTool = new LineTool(this);
		addTool(lineTool);
		DrawTool ellipseTool = new EllipseTool(this);
		addTool(ellipseTool);*/
		for (DrawToolFactory d:getToolFactories()){
			if (d==null){
				addTool(null);
			}
			else{
				addTool(d.createTool(this));
			}
		}
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the front, i.e. moves them to the end of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to front
	 */
	public void bringToFront(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		int pos = 0;
		for (Figure f : model.getFigures()) {
			pos++;
			if (selection.contains(f)) {
				orderedSelection.add(0, f);
			}
		}
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, --pos);
		}
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the back, i.e. moves them to the front of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to the back
	 */
	public void sendToBack(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		for (Figure f : model.getFigures()) {
			if (selection.contains(f)) {
				orderedSelection.add(f);
			}
		}
		int pos = 0;
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, pos++);
		}
	}

	/**
	 * Handles the opening of a new drawing from a file.
	 */
	private void doOpen() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Open Graphic");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith(".draw");
			}
		});
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// read jdraw graphic
			System.out.println("read file "
					+ chooser.getSelectedFile().getName());
		}
	}

	/**
	 * Handles the saving of a drawing to a file.
	 */
	private void doSave() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Save Graphic");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		FileFilter filter = new FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".draw");
			}
		};
		chooser.setFileFilter(filter);
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// save graphic
			File file = chooser.getSelectedFile();
			if (chooser.getFileFilter() == filter && !filter.accept(file)) {
				file = new File(chooser.getCurrentDirectory(), file.getName() + ".draw");
			}
			System.out.println("save current graphic to file " + file.getName());
		}
	}

}
