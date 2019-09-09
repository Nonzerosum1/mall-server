package flow.file.backstage;

/**
*this is flow filebs_add_category_jsons.flow to change,
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

@Service("bs_add_category_jsons_flow")
public class Bs_add_category_jsonsFlow extends BaseFlow implements RunFlow{
	private static final Logger log = LoggerFactory.getLogger(Bs_add_category_jsonsFlow.class);
	public  Bs_add_category_jsonsFlow(){
	}
	public int action(Context ct){
		String flowsn = ct.getFlowsn();
		if(flowsn.equals("1")){
 		 try{
 		 	bs_add_category_jsons_1(ct,ct.getPriDataCache(),ct.getPubDataCache());
 		 }catch(Exception ex){
 		 	log.error(ex.getMessage(),ex);
 		 	flowERROR(ct,ct.getPriDataCache(),ex);
 		 }
		}
			 return 0;
	}

	private void bs_add_category_jsons_1(Context ct,PriCache pri,PubCache pub){
JSONObject data = ((JSONObject)pri.get("jsonsObj")); //flow->3//
if((BeanUtil.get_tls().isNull((data.getString("name"))) || BeanUtil.get_tls().isNull((data.getString("recommend"))) || //flow->4//
BeanUtil.get_tls().isNull((data.getString("quantityunit"))) || BeanUtil.get_tls().isNull((data.getString("catesort"))) || //flow->5//
BeanUtil.get_tls().isNull((data.getString("keyword"))) || BeanUtil.get_tls().isNull((data.getString("catedesc"))) || //flow->6//
BeanUtil.get_tls().isNull((data.getString("whethershow"))) || BeanUtil.get_tls().isNull((data.getString("whetherbar"))) || //flow->7//
BeanUtil.get_tls().isNull((data.getString("markCate"))) ) &&  //flow->8//
(BeanUtil.get_tls().isNull((data.getString("markCate"))) || BeanUtil.get_tls().isNull((data.getString("cateId")))) && //flow->9//
(BeanUtil.get_tls().isNull((data.getString("markCate"))))){ //flow->10//
BeanUtil.get_tls().returnFun("000001",ct); //flow->11//
return; //flow->12//
} //flow->13//
 pri.put("typeName",(data.getString("name")));  //flow->14//
 pri.put("recommend",(data.getString("recommend")));  //flow->15//
 pri.put("id",BeanUtil.get_DataUtil().getUUID());  //flow->16//
 pri.put("typeId",BeanUtil.get_DataUtil().generateNumber());  //flow->17//
 pri.put("markCate",(data.getString("markCate")));  //flow->18//
 pri.put("cateId",(data.getString("cateId")));  //flow->19//
 //flow->20//
 pri.put("quantityUnit",(data.getString("quantityunit")));  //flow->21//
 pri.put("cateSort",(data.getString("catesort")));  //flow->22//
 pri.put("keyWord",(data.getString("keyword")));  //flow->23//
 pri.put("cateDesc",(data.getString("catedesc")));  //flow->24//
 pri.put("whetherShow",(data.getString("whethershow")));  //flow->25//
 pri.put("whetherBar",(data.getString("whetherbar")));  //flow->26//
 pri.put("imgDataUrl",(data.getString("imgDataUrl")));  //flow->27//
 pri.put("cateDetailId",(data.getString("cateDetailId")));  //flow->28//
 //flow->29//
if(equals((pri.getParam("markCate")) , "init_cate")){ //flow->30//

bs_add_category_jsons_category_init(ct,pri,pub);
 return  ; //flow->31//
}else if(equals((pri.getParam("markCate")) , "delete_cate")){ //flow->32//

bs_add_category_jsons_category_delete(ct,pri,pub);
 return  ; //flow->33//
}else if(equals((pri.getParam("markCate")) , "update_cate")){ //flow->34//

bs_add_category_jsons_category_update(ct,pri,pub);
 return  ; //flow->35//
}else if(equals((pri.getParam("markCate")) , "add_cate")){ //flow->36//

bs_add_category_jsons_category_add(ct,pri,pub);
 return  ; //flow->37//
} //flow->38//	}

	private void bs_add_category_jsons_category_add(Context ct,PriCache pri,PubCache pub){
TGoodsType tGoodsTypeExist = BeanUtil.get_tGoodsTypeService().checkCateExisted((pri.getParam("typeName"))); //flow->42//
if(!BeanUtil.get_tls().isNull(tGoodsTypeExist)){ //flow->43//
BeanUtil.get_tls().returnFun("290010",ct); //flow->44//
return; //flow->45//
} //flow->46//
TGoodsType tGoodsType  = new TGoodsType(); //flow->47//
tGoodsType.setId((pri.getParam("id"))); //flow->48//
tGoodsType.setCreateDate(new Date()); //flow->49//
tGoodsType.setModifyDate(new Date()); //flow->50//
tGoodsType.setTypeId((pri.getParam("typeId"))); //flow->51//
tGoodsType.setTypeName((pri.getParam("typeName"))); //flow->52//
tGoodsType.setTypeParId((pri.getParam("recommend"))); //flow->53//
tGoodsType.setDel("0"); //flow->54//
BeanUtil.get_tGoodsTypeService().insert(tGoodsType); //flow->55//
 //flow->56//
TGoodsTypeDetail tGoodsTypeDetail  = new TGoodsTypeDetail(); //flow->57//
tGoodsTypeDetail.setId((pri.getParam("id"))); //flow->58//
tGoodsTypeDetail.setCreateDate(new Date()); //flow->59//
tGoodsTypeDetail.setModifyDate(new Date()); //flow->60//
tGoodsTypeDetail.setTypeId((pri.getParam("typeId"))); //flow->61//
tGoodsTypeDetail.setQuantityUnit((pri.getParam("quantityUnit"))); //flow->62//
tGoodsTypeDetail.setNavigationBar((pri.getParam("whetherBar"))); //flow->63//
tGoodsTypeDetail.setIsShow((pri.getParam("whetherShow"))); //flow->64//
//tGoodsTypeDetail.setSortManage(pri.id); //flow->65//
//tGoodsTypeDetail.setGoodsNum(pri.id); //flow->66//
tGoodsTypeDetail.setCateImage((pri.getParam("imgDataUrl"))); //flow->67//
tGoodsTypeDetail.setCateSort((pri.getParam("cateSort"))); //flow->68//
tGoodsTypeDetail.setKeyWord((pri.getParam("keyWord"))); //flow->69//
tGoodsTypeDetail.setCateDesc((pri.getParam("cateDesc"))); //flow->70//
tGoodsTypeDetail.setDel("0"); //flow->71//
BeanUtil.get_tGoodsTypeDetailService().insert(tGoodsTypeDetail); //flow->72//
BeanUtil.get_tls().returnFun("000000", ct); //flow->73//
return; //flow->74//	}

	private void bs_add_category_jsons_category_init(Context ct,PriCache pri,PubCache pub){
pri.put("catesList",BeanUtil.get_tGoodsTypeService().getAlltype());  //flow->78//
JSONObject robj = toJSON() 
 ; //flow->79//
 robj.put("categorysList",toJSONList());    //flow->80//
for(int iiii=0;iiii<((List<TGoodsType>)pri.get("catesList")).size();iiii++){
TGoodsType obj=(TGoodsType)((List<TGoodsType>)pri.get("catesList")).get(iiii); //flow->81//
JSONObject cates = toJSON() 
 ; //flow->82//
TGoodsTypeDetail tGoodSTypeDetail  = BeanUtil.get_tGoodsTypeDetailService().getGoodTypeDetailByTypeId(obj.getTypeId()); //flow->83//
 cates.put("cateId",obj.getId());  //flow->84//
 cates.put("typeId",obj.getTypeId());  //flow->85//
 cates.put("typeName",obj.getTypeName());  //flow->86//
 cates.put("typeParId",obj.getTypeParId());  //flow->87//
 //flow->88//
 cates.put("cateDetailId",tGoodSTypeDetail.getId());  //flow->89//
 cates.put("quantityunit",tGoodSTypeDetail.getQuantityUnit());  //flow->90//
 cates.put("catesort",tGoodSTypeDetail.getCateSort());  //flow->91//
 cates.put("keyword",tGoodSTypeDetail.getKeyWord());  //flow->92//
 cates.put("catedesc",tGoodSTypeDetail.getCateDesc());  //flow->93//
 cates.put("whethershow",tGoodSTypeDetail.getIsShow());  //flow->94//
 cates.put("whetherbar",tGoodSTypeDetail.getNavigationBar());  //flow->95//
 //flow->96//
(robj.getJSONArray("categorysList")).add(cates); //flow->97//
}; //flow->98//
 //flow->99//
BeanUtil.get_tls().returnFun("000000", robj, ct); //flow->100//
return; //flow->101//
 
	}

	private void bs_add_category_jsons_category_delete(Context ct,PriCache pri,PubCache pub){
Integer res  = BeanUtil.get_tGoodsTypeService().deleteByPK(((pri.getParam("cateId"))).toString()); //flow->105//
 //flow->106//
if(!BeanUtil.get_tls().isNull(res)){ //flow->107//
if(res > 0){ //flow->108//
BeanUtil.get_tls().returnFun("000000",ct); //flow->109//
return; //flow->110//
}else{ //flow->111//
BeanUtil.get_tls().returnFun("408005",ct); //flow->112//
return; //flow->113//
} //flow->114//
}else{ //flow->115//
BeanUtil.get_tls().returnFun("408005",ct); //flow->116//
return; //flow->117//
} //flow->118//
 //flow->119//	}

	private void bs_add_category_jsons_category_update(Context ct,PriCache pri,PubCache pub){
TGoodsType tGoodsType  = new TGoodsType(); //flow->123//
tGoodsType.setModifyDate(new Date()); //flow->124//
tGoodsType.setId((pri.getParam("cateId"))); //flow->125//
tGoodsType.setTypeName((pri.getParam("typeName"))); //flow->126//
tGoodsType.setTypeParId((pri.getParam("recommend"))); //flow->127//
Integer res  = BeanUtil.get_tGoodsTypeService().update(tGoodsType); //flow->128//
 //flow->129//
TGoodsTypeDetail tGoodsTypeDetail  = new TGoodsTypeDetail(); //flow->130//
tGoodsTypeDetail.setModifyDate(new Date()); //flow->131//
tGoodsTypeDetail.setId((pri.getParam("cateDetailId"))); //flow->132//
tGoodsTypeDetail.setQuantityUnit((pri.getParam("quantityUnit"))); //flow->133//
tGoodsTypeDetail.setNavigationBar((pri.getParam("whetherBar"))); //flow->134//
tGoodsTypeDetail.setIsShow((pri.getParam("whetherShow"))); //flow->135//
//tGoodsTypeDetail.setSortManage(pri.id); //flow->136//
//tGoodsTypeDetail.setGoodsNum(pri.id); //flow->137//
tGoodsTypeDetail.setCateImage((pri.getParam("imgDataUrl"))); //flow->138//
tGoodsTypeDetail.setCateSort((pri.getParam("cateSort"))); //flow->139//
tGoodsTypeDetail.setKeyWord((pri.getParam("keyWord"))); //flow->140//
tGoodsTypeDetail.setCateDesc((pri.getParam("cateDesc"))); //flow->141//
Integer resDetail  = BeanUtil.get_tGoodsTypeDetailService().update(tGoodsTypeDetail); //flow->142//
 //flow->143//
if(!BeanUtil.get_tls().isNull(res) && !BeanUtil.get_tls().isNull(resDetail)){ //flow->144//
if(res > 0 && resDetail > 0){ //flow->145//
BeanUtil.get_tls().returnFun("000000",ct); //flow->146//
return; //flow->147//
}else{ //flow->148//
log.info("==============>1"); //flow->149//
BeanUtil.get_tls().returnFun("408006",ct); //flow->150//
return; //flow->151//
} //flow->152//
}else{ //flow->153//
log.info("==============>2"); //flow->154//
BeanUtil.get_tls().returnFun("408006",ct); //flow->155//
return; //flow->156//
} //flow->157//
 //flow->158//	}

	private void bs_add_category_jsons_999(Context ct,PriCache pri,PubCache pub){
	flow_999(ct,pri,pub);
	}

}
