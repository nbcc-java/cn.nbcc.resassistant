package cn.nbcc.resassistant.model;

import java.util.*;

public class ResearchProjectManager {
	
//	private static final String TAG_PROJECTS = "项目集合";
//	private static final String TAG_PRJ_ITEM = "项目";
//	private static final String TAG_NAME = "项目名称";

	
	private static ResearchProjectManager manager;
	private Collection<ResearchProject> rProjects;
	private List listeners = new ArrayList();
	
	public static ResearchProjectManager getManager()
	{
		if(manager == null)
			manager = new ResearchProjectManager();
		return manager;
	}
	
	
	public boolean isExist(ResearchProject item){
		return rProjects.contains(item);		
	}
	public ResearchProject[] getRProjects(){
		if(rProjects == null)
			loadRProjects();
		return (ResearchProject[])rProjects.toArray(
				new ResearchProject[rProjects.size()]);
	}
	public void addRProjects(ResearchProject[] items){
		if(rProjects == null)
			loadRProjects();
		if(rProjects.addAll(Arrays.asList(items)))
		{
			fireAddressesChanged(items, ResearchProject.NONE, ResearchProject.NONE);
		}
	}
	public void removeRProjects(ResearchProject[] items)
	{
		if(rProjects == null)
			loadRProjects();
		if(rProjects.removeAll(Arrays.asList(items)))
		{
			fireAddressesChanged(ResearchProject.NONE, items, ResearchProject.NONE);
		}
	}


	private void loadRProjects() {
		rProjects = new HashSet<ResearchProject>();
		for (int i = 0; i < 10; i++) {
			ResearchProject item1 = new ResearchProject(i+"","校内课题"+i,new Date());
			rProjects.add(item1);
		}
	}


	private void fireAddressesChanged(ResearchProject[] itemsAdded, ResearchProject[] itemsRemoved,
			ResearchProject[] itemsModified) {
		
	}
}

