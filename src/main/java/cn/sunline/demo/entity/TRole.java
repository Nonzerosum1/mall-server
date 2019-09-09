package cn.sunline.demo.entity;
public class TRole{//t_role

	private String id;//id

	public void setId(String id){
		this.id=id;
	}

	public String getId(){
		return this.id;
	}
	private java.util.Date createDate;//create_date

	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}

	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	private java.util.Date modifyDate;//modify_date

	public void setModifyDate(java.util.Date modifyDate){
		this.modifyDate=modifyDate;
	}

	public java.util.Date getModifyDate(){
		return this.modifyDate;
	}
	private Long roleId;//role_id

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}

	public Long getRoleId(){
		return this.roleId;
	}
	private String roleName;//role_name

	public void setRoleName(String roleName){
		this.roleName=roleName;
	}

	public String getRoleName(){
		return this.roleName;
	}
	private String del;//del

	public void setDel(String del){
		this.del=del;
	}

	public String getDel(){
		return this.del;
	}
}
