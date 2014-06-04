package cn.nbcc.resassistant.entities;

import java.util.Date;

public class RAProject {
	
	private String srcPath;		//申报项目的源文件夹路径
	private Date startDate;		//项目开始时间
	private Date endDate;		//项目结束时间
	private Date deadlineDate;	//项目申报截止时间
	private PStatus status;
	private String title;		//项目名称
	private String id;			//项目编号

	
	public RAProject() {
		startDate = new Date();
		status = PStatus.APPLYING;
		title = "UnKnown";
		id = "Unknown";
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
	public PStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(PStatus status) {
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
	
	
	

}

