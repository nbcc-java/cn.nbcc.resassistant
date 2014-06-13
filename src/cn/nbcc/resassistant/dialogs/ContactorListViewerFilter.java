package cn.nbcc.resassistant.dialogs;

import org.eclipse.jface.viewers.*;

import cn.nbcc.resassistant.model.Contactor;
import cn.nbcc.resassistant.utils.StringMatcher;

public class ContactorListViewerFilter  extends ViewerFilter {

	  private final StructuredViewer viewer;
	   private String pattern = "";
	   private StringMatcher matcher;
	   
	   public ContactorListViewerFilter(StructuredViewer viewer) {
	      this.viewer = viewer;
	   }

	   public String getPattern() {
	      return pattern;
	   }

	   public void setPattern(String newPattern) {
	      boolean filtering = matcher != null;
	      if (newPattern != null && newPattern.trim().length() > 0) {
	         pattern = newPattern;
	         matcher = new StringMatcher(pattern, true, false);
	         if (!filtering)
	            viewer.addFilter(this);
	         else
	            viewer.refresh();
	      }
	      else {
	         pattern = "";
	         matcher = null;
	         if (filtering)
	            viewer.removeFilter(this);
	      }
	   }
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return matcher.match(((Contactor)element).toString());
	}

}

