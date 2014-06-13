package cn.nbcc.resassistant.decrecated;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class DirTreeLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	
	@Override
	public String getText(Object element) {
		if (element instanceof Path) {
			Path path = (Path) element;
			return path.getFileName()+"";
		}
		return "";
	}


	@Override
	public String getColumnText(Object element, int columnIndex) {
		return null;
	}


}

