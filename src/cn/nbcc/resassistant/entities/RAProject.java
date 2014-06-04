package cn.nbcc.resassistant.entities;

import java.util.Date;

public class RAProject {
	
	private String srcPath;		//�걨��Ŀ��Դ�ļ���·��
	private Date startDate;		//��Ŀ��ʼʱ��
	private Date endDate;		//��Ŀ����ʱ��
	private Date deadlineDate;	//��Ŀ�걨��ֹʱ��
	private PStatus status;
	private String title;		//��Ŀ����
	private String id;			//��Ŀ���

	
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

