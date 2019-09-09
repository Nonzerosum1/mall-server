package cn.sunline.demo.entity;
public class TOrder{//t_order

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
	private String orderId;//order_id

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}
	private String goodId;//good_id

	public void setGoodId(String goodId){
		this.goodId=goodId;
	}

	public String getGoodId(){
		return this.goodId;
	}
	private Long userId;//user_id

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long getUserId(){
		return this.userId;
	}
	private java.util.Date dateOfOeder;//date_of_oeder

	public void setDateOfOeder(java.util.Date dateOfOeder){
		this.dateOfOeder=dateOfOeder;
	}

	public java.util.Date getDateOfOeder(){
		return this.dateOfOeder;
	}
	private String orderState;//order_state

	public void setOrderState(String orderState){
		this.orderState=orderState;
	}

	public String getOrderState(){
		return this.orderState;
	}
	private String del;//del

	public void setDel(String del){
		this.del=del;
	}

	public String getDel(){
		return this.del;
	}
}
