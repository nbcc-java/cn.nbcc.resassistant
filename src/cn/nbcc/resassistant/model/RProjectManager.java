package cn.nbcc.resassistant.model;

import java.util.*;

public class RProjectManager {
	
	private static final String TAG_PROJECTS = "项目集合";
	private static final String TAG_PRJ_ITEM = "项目";
	private static final String TAG_NAME = "项目名称";

	
	private static RProjectManager manager;
	private Collection<RProject> rProjects;
	private List listeners = new ArrayList();
	
	public static RProjectManager getManager()
	{
		if(manager == null)
			manager = new RProjectManager();
		return manager;
	}
	
	
	public boolean isExist(RProject item){
		return rProjects.contains(item);		
	}
	public RProject[] getAddresses(){
		if(rProjects == null)
			loadRProjects();
		return (RProject[])rProjects.toArray(
				new RProject[rProjects.size()]);
	}
	public void addRProjects(RProject[] items){
		if(rProjects == null)
			loadRProjects();
		if(rProjects.addAll(Arrays.asList(items)))
		{
			fireAddressesChanged(items, RProject.NONE, RProject.NONE);
		}
	}
	public void removeRProjects(RProject[] items)
	{
		if(rProjects == null)
			loadRProjects();
		if(rProjects.removeAll(Arrays.asList(items)))
		{
			fireAddressesChanged(RProject.NONE, items, RProject.NONE);
		}
	}


	private void loadRProjects() {
		rProjects = new HashSet<RProject>();
		for (int i = 0; i < 10; i++) {
			RProject item1 = new RProject("校内课题"+i,new Date());
			rProjects.add(item1);
		}
	}


	private void fireAddressesChanged(RProject[] nONE, RProject[] items,
			RProject[] nONE2) {
		
	}
}

