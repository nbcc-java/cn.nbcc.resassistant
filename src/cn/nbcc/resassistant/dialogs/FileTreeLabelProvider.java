package cn.nbcc.resassistant.dialogs;

import java.io.File;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class FileTreeLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 显示时调用该方法
	 */
	@Override
	public String getText(Object element) {
		// Get the name of the file
	    String text = ((File) element).getName();

	    // If name is blank, get the path
	    if (text.length() == 0) {
	      text = ((File) element).getPath();
	    }
	    // Check the case settings before returning the text
	    return text;
	}



}

