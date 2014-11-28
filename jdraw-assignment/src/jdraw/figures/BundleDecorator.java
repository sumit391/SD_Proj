package jdraw.figures;


import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

@SuppressWarnings("serial")
public class BundleDecorator extends AbstractDecorator{

		
	public BundleDecorator(Figure f) {
		super(f);
		
	}
	
	@Override
	public List<FigureHandle> getHandles() {
		return null;
	} 

	@Override
	public Object clone(){
		BundleDecorator newF;
		newF=(BundleDecorator) super.clone();
		return newF;
	}

}