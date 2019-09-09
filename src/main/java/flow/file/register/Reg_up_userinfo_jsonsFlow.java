package flow.file.register;

/**
*this is flow filereg_up_userinfo_jsons.flow to change,
*do not modify by manual,thank you
*/
import org.springframework.stereotype.Service;
import cn.sunline.tiny.web.*;
import cn.sunline.tiny.core.*;
import cn.sunline.tiny.core.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import flow.file.util.*;
import com.alibaba.fastjson.*;
import java.util.*;
import cn.sunline.tiny.core.control.flow.*;
import cn.sunline.demo.entity.*;

@Service("reg_up_userinfo_jsons_flow")
public class Reg_up_userinfo_jsonsFlow extends BaseFlow implements RunFlow{
	private static final Logger log = LoggerFactory.getLogger(Reg_up_userinfo_jsonsFlow.class);
	public  Reg_up_userinfo_jsonsFlow(){
	}
	public int action(Context ct){
		String flowsn = ct.getFlowsn();
		if(flowsn.equals("1")){
 		 try{
 		 	reg_up_userinfo_jsons_1(ct,ct.getPriDataCache(),ct.getPubDataCache());
 		 }catch(Exception ex){
 		 	log.error(ex.getMessage(),ex);
 		 	flowERROR(ct,ct.getPriDataCache(),ex);
 		 }
		}
			 return 0;
	}

	private void reg_up_userinfo_jsons_1(Context ct,PriCache pri,PubCache pub){
JSONObject data = ((JSONObject)pri.get("jsonsObj")); //flow->3//
if(BeanUtil.get_tls().isNull((data.getString("nickName"))) || BeanUtil.get_tls().isNull((data.getString("mobile"))) || BeanUtil.get_tls().isNull((data.getString("password")))){ //flow->4//
BeanUtil.get_tls().returnFun("000001",ct); //flow->5//
return; //flow->6//
} //flow->7//
 //flow->8//
 pri.put("nickName",(data.getString("nickName")));  //flow->9//
 pri.put("mobile",(data.getString("mobile")));  //flow->10//
 pri.put("password",(data.getString("password")));  //flow->11//
 pri.put("email",(data.getString("email")));  //flow->12//
 pri.put("address",(data.getString("address")));  //flow->13//
 pri.put("dateOfBirth",(data.getString("dateOfBirth")));  //flow->14//
 pri.put("gender",(data.getString("gender")));  //flow->15//
 pri.put("mobilePhone",(data.getString("mobilePhone")));  //flow->16//
 pri.put("status",(data.getString("status")));  //flow->17//
 pri.put("id",BeanUtil.get_DataUtil().getUUID());  //flow->18//

reg_up_userinfo_jsons_check(ct,pri,pub);
 return  ; //flow->19//	}

	private void reg_up_userinfo_jsons_check(Context ct,PriCache pri,PubCache pub){
TUsers tUsersByMobile  = BeanUtil.get_tUsersService().slectTUsersByMobile(((pri.getParam("mobile"))).toString()); //flow->23//
TUsers tUsersByName  = BeanUtil.get_tUsersService().slectTUsersByName(((pri.getParam("nickName"))).toString()); //flow->24//
 //flow->25//
//查询数据库查看用户是否存在 //flow->26//
if(!BeanUtil.get_tls().isNull(tUsersByMobile) || !BeanUtil.get_tls().isNull(tUsersByName)){ //flow->27//
BeanUtil.get_tls().returnFun("290009",ct); //flow->28//
return; //flow->29//
} //flow->30//
 pri.put("userId",BeanUtil.get_cust().getCustomerID());  //flow->31//
 //flow->32//
 //flow->33//
TUsers tUser  = new TUsers(); //flow->34//
tUser.setId((pri.getParam("id"))); //flow->35//
tUser.setCreateDate(new Date()); //flow->36//
tUser.setModifyDate(new Date()); //flow->37//
tUser.setNickName((pri.getParam("nickName"))); //flow->38//
tUser.setUserId(Long.parseLong((pri.getParam("userId")))); //flow->39//
tUser.setMobile((pri.getParam("mobile"))); //flow->40//
tUser.setEmail((pri.getParam("email"))); //flow->41//
 //flow->42//
if(!BeanUtil.get_tls().isNull((pri.getParam("dateOfBirth")))){ //flow->43//
 pri.put("dateOfBirthFormat",BeanUtil.get_DataUtil().simpleDateParse((pri.getParam("dateOfBirth"))));  //flow->44//
tUser.setDateOfBirth(((Date)pri.get("dateOfBirthFormat"))); //flow->45//
} //flow->46//
tUser.setLoginPassword(((pri.getParam("password"))).toString()); //flow->47//
tUser.setGender((pri.getParam("gender"))); //flow->48//
tUser.setAddress((pri.getParam("address"))); //flow->49//
tUser.setMobilePhone((pri.getParam("mobilePhone"))); //flow->50//
tUser.setStatus((pri.getParam("status"))); //flow->51//
tUser.setDel("0"); //flow->52//
BeanUtil.get_tUsersService().insert(tUser); //flow->53//
 //flow->54//
BeanUtil.get_tls().returnFun("000000",ct); //flow->55//
return; //flow->56//	}

	private void reg_up_userinfo_jsons_10(Context ct,PriCache pri,PubCache pub){
String view = cn.sunline.tiny.core.util.JSON.stringify(toJSON( //flow->60//
) 
 
);
		pri.setParam("view",view );
		return ;
	}

	private void reg_up_userinfo_jsons_999(Context ct,PriCache pri,PubCache pub){
	flow_999(ct,pri,pub);
	}

}
