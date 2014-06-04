package cn.nbcc.resassistant;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.nbcc.resassistant.views.ProjectView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		layout.addView("cn.nbcc.resassistant.SemesterView", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView(ProjectView.ID, IPageLayout.RIGHT, 0.2f, IPageLayout.ID_EDITOR_AREA);
	}

}
