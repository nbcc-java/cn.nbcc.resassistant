package cn.nbcc.resassistant.model;

import java.util.Date;

import cn.nbcc.resassistant.utils.ContextConstants;

public class ResearchProject {
	
	static ResearchProject[] NONE = new ResearchProject[]{};
	private String srcPath;		//申报项目的源文件夹路径
	private Date startDate;		//项目开始时间
	private Date endDate;		//项目结束时间
	private Date deadlineDate;	//项目申报截止时间
	private ResearchProjectStatus status;
	private String title;		//项目名称
	private String id;			//项目编号
	private Contactor[] contactors;
	

	
	public ResearchProject() {
		startDate = new Date();
		status = ResearchProjectStatus.APPLYING;
		title = id = ContextConstants.UNKNOWN;
		srcPath="";
	}

	public ResearchProject(String title, Date deadline) {
		this();
		this.title = title;
		this.deadlineDate = deadline;
	}

	public ResearchProject(String id, String title, Date deadline) {
		this(title,deadline);
		this.id = id;
	}

	/**
	 * @return the srcPath
	 */
	public String getSrcPath() {
		return srcPath;
	}

	/**
	 * @param srcPath the srcPath to set
	 */
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the deadlineDate
	 */
	public Date getDeadlineDate() {
		return deadlineDate;
	}

	/**
	 * @param deadlineDate the deadlineDate to set
	 */
	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	/**
	 * @return the status
	 */
	public ResearchProjectStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResearchProjectStatus status) {
		this.status = status;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public Contactor[] getContactors() {
		return contactors;
	}
	
	public Contactor getFirstContactor(){
		if (getContactors()!=null) {
			return getContactors()[0];
		}
		return null;
	}

	public void setContactor(Contactor[] contactors) {
		this.contactors = contactors;
	}
	
	
	

}

