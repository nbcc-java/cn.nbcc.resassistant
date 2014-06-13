package cn.nbcc.resassistant.model;
public class Contactor implements Comparable<Contactor>{
	
	private String id;
	private String name;
	private String mobilePhone;
	private String email;
	private boolean isDefault;
	
	
	public Contactor(String id, String name, String mobilePhone, String email,boolean isDefault) {
		super();
		this.id = id;
		this.name = name;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.isDefault=isDefault;
	}
	public Contactor(String id, String name, String mobilePhone, String email) {
		this(id,name,mobilePhone,email,false);
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s%s", id,name);
	}
	public boolean isDefaultContactor() {
		return isDefault;
	}
	@Override
	public int compareTo(Contactor o) {
		return id.compareTo(o.id);
	}
	
	

}

