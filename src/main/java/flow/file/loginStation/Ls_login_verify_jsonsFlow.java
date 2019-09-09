package flow.file.loginStation;

/**
*this is flow filels_login_verify_jsons.flow to change,
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

@Service("ls_login_verify_jsons_flow")
public class Ls_login_verify_jsonsFlow extends BaseFlow implements RunFlow{
	private static final Logger log = LoggerFactory.getLogger(Ls_login_verify_jsonsFlow.class);
	public  Ls_login_verify_jsonsFlow(){
	}
	public int action(Context ct){
		String flowsn = ct.getFlowsn();
		if(flowsn.equals("1")){
 		 try{
 		 	ls_login_verify_jsons_1(ct,ct.getPriDataCache(),ct.getPubDataCache());
 		 }catch(Exception ex){
 		 	log.error(ex.getMessage(),ex);
 		 	flowERROR(ct,ct.getPriDataCache(),ex);
 		 }
		}
			 return 0;
	}

	private void ls_login_verify_jsons_1(Context ct,PriCache pri,PubCache pub){
JSONObject data = ((JSONObject)pri.get("jsonsObj"));//flow->3//
if(BeanUtil.get_tls().isNull((data.get("userName"))) || BeanUtil.get_tls().isNull((data.getString("password")))){//flow->4//
BeanUtil.get_tls().returnFun("000001",ct);//flow->5//
return;//flow->6//
}//flow->7//
 pri.put("userName",(data.get("userName"))); //flow->8//
 pri.put("password",(data.getString("password"))); //flow->9//

ls_login_verify_jsons_10(ct,pri,pub);
 return  ;//flow->10//	}

	private void ls_login_verify_jsons_10(Context ct,PriCache pri,PubCache pub){
TUsers tUser  = new TUsers();//flow->14//
tUser.setNickName((pri.getParam("nickName")));//flow->15//
tUser.setLoginPassword((pri.getParam("password")));//flow->16//
TUsers tUserLog = BeanUtil.get_tUsersService().slectTUsersLogin(tUser);//flow->17//
//flow->18//
if(BeanUtil.get_tls().isNull(tUserLog)){//flow->19//
BeanUtil.get_tls().returnFun("402004", ct);//flow->20//
return;//flow->21//
}//flow->22//
 pub.put("userId",tUserLog.getUserId()); //flow->23//
 pub.put("nickName",tUserLog.getNickName()); //flow->24//
BeanUtil.get_tls().returnFun("000000", ct);//flow->25//
return;//flow->26//	}

	private void ls_login_verify_jsons_999(Context ct,PriCache pri,PubCache pub){
	flow_999(ct,pri,pub);
	}

}
