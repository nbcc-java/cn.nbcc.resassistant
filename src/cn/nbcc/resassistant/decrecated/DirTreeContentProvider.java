package cn.nbcc.resassistant.decrecated;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DirTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		ArrayList<Path> pathArrs =new ArrayList<Path>();
		if (inputElement instanceof Path) {
			Path path = (Path) inputElement;
			DirectoryStream<Path> ds = null;
			try {
				ds = Files.newDirectoryStream(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (Path p : ds) {
				pathArrs.add(p);
			}
			Path arr[] = new Path[pathArrs.size()];
			pathArrs.toArray(arr);
			return arr;
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Path) {
			Path parentPath = (Path) parentElement;
			DirectoryStream<Path> ds = null;
			try {
				ds = Files.newDirectoryStream(parentPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<Path> subPaths= new ArrayList<Path>();
			for (Path path : ds) {
				subPaths.add(path);
			}
			Path[] arr = new Path[subPaths.size()];
			subPaths.toArray(arr);
			return arr;
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Path) {
			Path path = (Path) element;
			File file = new File(path.toUri());
			if(file.isDirectory())
				return file.list().length>0;
		}
		return false;
	}
}
