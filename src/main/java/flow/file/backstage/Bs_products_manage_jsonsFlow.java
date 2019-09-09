package flow.file.backstage;

/**
*this is flow filebs_products_manage_jsons.flow to change,
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

@Service("bs_products_manage_jsons_flow")
public class Bs_products_manage_jsonsFlow extends BaseFlow implements RunFlow{
	private static final Logger log = LoggerFactory.getLogger(Bs_products_manage_jsonsFlow.class);
	public  Bs_products_manage_jsonsFlow(){
	}
	public int action(Context ct){
		String flowsn = ct.getFlowsn();
		if(flowsn.equals("1")){
 		 try{
 		 	bs_products_manage_jsons_1(ct,ct.getPriDataCache(),ct.getPubDataCache());
 		 }catch(Exception ex){
 		 	log.error(ex.getMessage(),ex);
 		 	flowERROR(ct,ct.getPriDataCache(),ex);
 		 }
		}
			 return 0;
	}

	private void bs_products_manage_jsons_1(Context ct,PriCache pri,PubCache pub){
JSONObject data = ((JSONObject)pri.get("jsonsObj")); //flow->3//
if((BeanUtil.get_tls().isNull((data.getString("typeId"))) || BeanUtil.get_tls().isNull((data.getString("markPro")))) && (BeanUtil.get_tls().isNull((data.getString("goodName"))) || BeanUtil.get_tls().isNull((data.getString("goodDec"))) ||  //flow->4//
BeanUtil.get_tls().isNull((data.getString("originalPrice"))) || BeanUtil.get_tls().isNull((data.getString("goodPrice"))) || BeanUtil.get_tls().isNull((data.getString("goodStock"))) || BeanUtil.get_tls().isNull((data.getString("typeId")))) &&  //flow->5//
(BeanUtil.get_tls().isNull((data.getString("cateId"))) || BeanUtil.get_tls().isNull((data.getString("markCate"))))){ //flow->6//
BeanUtil.get_tls().returnFun("000001",ct); //flow->7//
return; //flow->8//
} //flow->9//
 pri.put("typeId",(data.getString("typeId")));  //flow->10//
 pri.put("markPro",(data.getString("markPro")));  //flow->11//
 pri.put("goodName",(data.getString("goodName")));  //flow->12//
 pri.put("goodDec",(data.getString("goodDec")));  //flow->13//
 pri.put("originalPrice",(data.getString("originalPrice")));  //flow->14//
 pri.put("goodPrice",(data.getString("goodPrice")));  //flow->15//
 pri.put("goodStock",(data.getString("goodStock")));  //flow->16//
 //flow->17//
 pri.put("markCate",(data.getString("markCate")));  //flow->18//
 pri.put("cateId",(data.getString("cateId")));  //flow->19//
 //flow->20//
if(equals((pri.getParam("markPro")) , "get_products_info")){ //flow->21//

bs_products_manage_jsons_get_products_info(ct,pri,pub);
 return  ; //flow->22//
}else if(equals((pri.getParam("markPro")) , "insert_products")){ //flow->23//

bs_products_manage_jsons_insert_products(ct,pri,pub);
 return  ; //flow->24//
}else if(equals((pri.getParam("markCate")) , "delete_pro")){  //flow->25//

bs_products_manage_jsons_delete_pro(ct,pri,pub);
 return  ; //flow->26//
} //flow->27//
 //flow->28//	}

	private void bs_products_manage_jsons_get_products_info(Context ct,PriCache pri,PubCache pub){
pri.put("proList",BeanUtil.get_tGoodsService().getAllPros(((pri.getParam("typeId"))).toString()));  //flow->32//
 pub.put("goodType",(pri.getParam("typeId")));  //flow->33//
JSONObject robj = toJSON() 
 ; //flow->34//
 robj.put("productList",toJSONList());    //flow->35//
for(int iiii=0;iiii<((List<TGoods>)pri.get("proList")).size();iiii++){
TGoods obj=(TGoods)((List<TGoods>)pri.get("proList")).get(iiii); //flow->36//
JSONObject product = toJSON() 
 ; //flow->37//
TGoodImage tGoodImg  = BeanUtil.get_tGoodImageService().getProImage(obj.getGoodId()); //flow->38//
 //flow->39//
if(!BeanUtil.get_tls().isNull(tGoodImg)){ //flow->40//
 product.put("goodImg",tGoodImg.getImageUrl());  //flow->41//
} //flow->42//
 product.put("id",obj.getId());  //flow->43//
 product.put("goodId",obj.getGoodId());  //flow->44//
 product.put("goodName",obj.getGoodName());  //flow->45//
 product.put("goodDesc",obj.getGoodDesc());  //flow->46//
 product.put("goodPrice",obj.getGoodPrice());  //flow->47//
 product.put("goodType",obj.getGoodType());  //flow->48//
 product.put("goodStock",obj.getGoodStock());  //flow->49//
 product.put("originalPrice",obj.getOriginalPrice());  //flow->50//
 //flow->51//
(robj.getJSONArray("productList")).add(product); //flow->52//
}; //flow->53//
BeanUtil.get_tls().returnFun("000000", robj, ct); //flow->54//
return; //flow->55//
 
	}

	private void bs_products_manage_jsons_insert_products(Context ct,PriCache pri,PubCache pub){
TGoods tGood  = new TGoods(); //flow->59//
 pri.put("id",BeanUtil.get_DataUtil().getUUID());  //flow->60//
 pri.put("goodId",BeanUtil.get_tGoodsService().getMaxGoodId((pri.getParam("typeId"))));  //flow->61//
tGood.setId((pri.getParam("id"))); //flow->62//
tGood.setCreateDate(new Date()); //flow->63//
tGood.setModifyDate(new Date()); //flow->64//
tGood.setGoodId((pri.getParam("goodId"))); //flow->65//
tGood.setGoodName((pri.getParam("goodName"))); //flow->66//
tGood.setGoodDesc((pri.getParam("goodDec"))); //flow->67//
tGood.setGoodPrice((pri.getParam("goodPrice"))); //flow->68//
tGood.setGoodType((pri.getParam("typeId"))); //flow->69//
tGood.setGoodStock((pri.getParam("goodStock"))); //flow->70//
tGood.setOriginalPrice((pri.getParam("originalPrice"))); //flow->71//
tGood.setDel("0"); //flow->72//
BeanUtil.get_tGoodsService().insert(tGood); //flow->73//
BeanUtil.get_tls().returnFun("000000", ct); //flow->74//
return; //flow->75//	}

	private void bs_products_manage_jsons_delete_pro(Context ct,PriCache pri,PubCache pub){
Integer res  = BeanUtil.get_tGoodsService().deleteByPK(((pri.getParam("cateId"))).toString()); //flow->79//
if(!BeanUtil.get_tls().isNull(res)){ //flow->80//
if(res > 0){ //flow->81//
BeanUtil.get_tls().returnFun("000000",ct); //flow->82//
return; //flow->83//
}else{ //flow->84//
BeanUtil.get_tls().returnFun("408005",ct); //flow->85//
return; //flow->86//
} //flow->87//
}else{ //flow->88//
BeanUtil.get_tls().returnFun("408005",ct); //flow->89//
return; //flow->90//
} //flow->91//	}

	private void bs_products_manage_jsons_10(Context ct,PriCache pri,PubCache pub){
String view = cn.sunline.tiny.core.util.JSON.stringify(toJSON( //flow->95//
) 
 
);
		pri.setParam("view",view );
		return ;
	}

	private void bs_products_manage_jsons_999(Context ct,PriCache pri,PubCache pub){
	flow_999(ct,pri,pub);
	}

}
