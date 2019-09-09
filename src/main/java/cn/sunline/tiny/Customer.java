package cn.sunline.tiny;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.sunline.demo.entity.TUsers;
import cn.sunline.tiny.util.Tools;
import flow.file.util.BeanUtil;

@Component("customer")
public class Customer {

    private static Long totalCount = (long) 0;
    private Long customerID;

    @Autowired
    private Tools tls;

    public Customer() {
	++totalCount;
	customerID = totalCount;
	System.out.println("增加一个");
    }

    public String getCustomerID() {
	TUsers tUser = BeanUtil.get_tUsersService().getMaxUserId();
	//DecimalFormat decimalFormat = null;
	String userIdStr;
	if(tls.isNull(tUser)) {
	    //decimalFormat = new DecimalFormat("1000000000");	
	    userIdStr = "1000000001";
	}else {
	    Long userId = tUser.getUserId();
	    //decimalFormat = new DecimalFormat(userId.toString());
	    userIdStr = Long.toString(userId + customerID);
	}
	return userIdStr;
    }

}
