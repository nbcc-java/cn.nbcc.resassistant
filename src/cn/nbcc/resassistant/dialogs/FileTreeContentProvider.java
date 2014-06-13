package cn.nbcc.resassistant.dialogs;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// These are the root elements of the tree
	    // We don't care what arg0 is, because we just want all
	    // the root nodes in the file system
	    return ((File)inputElement).listFiles();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return ((File) parentElement).listFiles();
	}

	@Override
	public Object getParent(Object element) {
		return ((File) element).getParentFile();
	}

	@Override
	public boolean hasChildren(Object element) {
		// Get the children
	    Object[] obj = getChildren(element);

	    // Return whether the parent has children
	    return obj == null ? false : obj.length > 0;
	}


}
