package cn.nbcc.resassistant.views;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

import cn.nbcc.resassistant.model.*;
import cn.nbcc.resassistant.utils.ContextConstants;

public class ProjectView extends ViewPart {
	public ProjectView() {
	}
	public static final String ID = "cn.nbcc.resassistant.view";
	public static String COLUMN_NAMES[]={"项目编号","申报截止时间","项目名称","创建时间","最后更新时间"};

	private TableViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent instanceof ResearchProject[]) {
				return (ResearchProject[]) parent;
			}
	        return new Object[0];
		}
	}

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider{
		public String getColumnText(Object obj, int index) {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			if (obj instanceof ResearchProject) {
				ResearchProject p = (ResearchProject) obj;
				ProjectMeta meta = new ProjectMeta(p.getSrcPath());
				switch (index) {
				case 0:
					return p.getId();
				case 1:
					return sdf.format(p.getDeadlineDate());
				case 2:
					return p.getTitle();
				case 3:
					
					if (meta.getCreateTime()!=null) {
						return meta.getCreateTime();
					}else
						return ContextConstants.UNKNOWN;
				case 4:
					if (meta.getLastModify()!=null) {
						return meta.getLastModify();
					}else {
						return ContextConstants.UNKNOWN;
					}
					
				default:
					break;
				}
			}
			return getText(obj);
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

//		public Image getColumnImage(Object obj, int index) {
//			return getImage(obj);
//		}
//
//		public Image getImage(Object obj) {
//			return PlatformUI.getWorkbench().getSharedImages().getImage(
//					ISharedImages.IMG_OBJ_ELEMENT);
//		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL|SWT.FULL_SELECTION);
		Table table = viewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		for (int i = 0; i < COLUMN_NAMES.length; i++) {
			
			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setWidth(100);
			tableColumn.setText(COLUMN_NAMES[i]);
			tableColumn.setMoveable(true);
		}
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(ResearchProjectManager.getManager().getRProjects());
	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}


	/**
	 * @return the viewer
	 */
	public TableViewer getViewer() {
		return viewer;
	}
}