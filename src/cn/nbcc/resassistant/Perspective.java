package cn.nbcc.resassistant;

import org.eclipse.ui.*;

import cn.nbcc.resassistant.views.ProjectView;
import cn.nbcc.resassistant.views.SemesterView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setFixed(true);
		layout.setEditorAreaVisible(false);
		IFolderLayout left = layout.createFolder("left",IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		left.addView(SemesterView.ID);
		IFolderLayout right= layout.createFolder("right", IPageLayout.RIGHT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		right.addView(ProjectView.ID);
//		left.addView("cn.nbcc.resassistant.SemesterView", IPageLayout.LEFT, 0.5f, IPageLayout.ID_EDITOR_AREA);
//		layout.addView(ProjectView.ID, IPageLayout.RIGHT, 0.2f, IPageLayout.ID_EDITOR_AREA);
	}

}
