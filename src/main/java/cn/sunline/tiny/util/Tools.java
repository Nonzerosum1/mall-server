package cn.sunline.tiny.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.sunline.demo.entity.TOtherSysCode;
import cn.sunline.tiny.Languages;
import cn.sunline.tiny.PassControl;
import cn.sunline.tiny.core.Constant;
import cn.sunline.tiny.web.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component("Tools")
public class Tools {

    private static final Logger LOG = LoggerFactory.getLogger(Tools.class);
    
    @Autowired
    private Languages languages;

    @Autowired
    private PassControl passControl;

    /*
     * 判断字符串是否为空
     */
    public boolean isNull(Object o) {
	if (o == null) {
	    return true;
	}

	if (o instanceof String) {
	    String str = o.toString();

	    if (str == null || "".equals(str) || str == "null" || str == "undefined") {
		return true;
	    }
	} else if (o instanceof JSONArray) {
	    return ((JSONArray) o).size() == 0;
	} else if (o instanceof List) {
	    return ((List) o).size() == 0;
	}

	return false;
    }

    public Date strToDate(String dateStr) {
	Date date = null;
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	if ("".equals(dateStr) || dateStr == null || "null".equals(dateStr)) {
	    date = new Date();
	} else {
	    try {
		date = format1.parse(dateStr);
	    } catch (ParseException e) {
		LOG.debug(e.getMessage(), e);
	    }
	}
	return date;
    }

    // 当前/指定 年-月-日 时:分:秒
    public String currentDate(Date nowdate) {
	if (nowdate == null) {
	    nowdate = new Date();
	}
	DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String dateString = null;
	dateString = format2.format(nowdate);
	return dateString;
    }

    // 当前/指定 年-月-日 时:分:秒
    public String currentDate(String nowdate) {
	Date date = strToDate(nowdate);
	return currentDate(date);
    }

    // 当前/指定 年-月-日 时:分:秒
    public String currentDate() {
	return currentDate(new Date());
    }

    public String languageMsg(String id, Context ct) {
	//String langStr = (String) ct.getPubDataCache().get("languageStr");
	String langStr = "lang_en_US";
	//return languages.lang(langStr).getString("i_" + id);
	return languages.lang(langStr).getString("i_" + id); 
    }

    public void returnFun(String rid, JSONObject robj, String rparam, Context ct) {
	JSONObject dataJson = new JSONObject();
	HttpServletRequest req = ct.getRequest();
	if (req != null) {
	    String path = req.getRequestURI();
	    dataJson.put("transCode", path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".tml")));
	} else {
	    dataJson.put("transCode", "other");
	}
	dataJson.put("rcode", rid);
	dataJson.put("rtime", currentDate());
	if (rparam != null) {
//	    TemplateParser templateParser = new TemplateParser();
//	    dataJson.put("msg", templateParser.parse0(languageMsg(rid, ct), rparam));
	} else {
	    dataJson.put("msg", languageMsg(rid, ct));
	}
	if (dataJson.getString("msg") == null) {
	    dataJson.put("msg", languageMsg("999900", ct));
	}
	dataJson.put("transSeq", ct.getPriDataCache().getString("seq"));
	
	LOG.debug("dataJson:" + dataJson.toString());

	if (robj != null) {
	    LOG.debug("robj:" + robj.toString());
	    dataJson.put("robj", robj);
//		ct.getPriDataCache().setParam("views", dataJson.toString());
//		if(dataJson.getString("transCode").indexOf("_jsons") > 0 && BeanUtil.get_DataUtil().inAes(dataJson.getString("transCode").toLowerCase())) {
//			if("app_init_jsons".equals(dataJson.getString("transCode"))){
//				dataJson.put("robj", AESUtil.encrypt(robj.toJSONString(), null , null));
//			}else{
//				dataJson.put("robj", AESUtil.encrypt(robj.toJSONString(), 
//						(byte[])ct.getRequest().getSession().getAttribute("tinyRsaAesKey"), 
//						(byte[])ct.getRequest().getSession().getAttribute("tinyRsaAesIv") ));
//			}
//		}
//	}else {
//		dataJson.put("robj", new JSONObject());
//		ct.getPriDataCache().setParam("views", dataJson.toString());
//		if(dataJson.getString("transCode").indexOf("_jsons") > 0 && BeanUtil.get_DataUtil().inAes(dataJson.getString("transCode").toLowerCase())) {
//			dataJson.put("robj", AESUtil.encrypt("{}", 
//						(byte[])ct.getRequest().getSession().getAttribute("tinyRsaAesKey"), 
//						(byte[])ct.getRequest().getSession().getAttribute("tinyRsaAesIv") ));
//		}
	}

	if (passControl.getAccessJson().containsKey(dataJson.getString("transCode"))) {
	    passControl.returnRecord(dataJson, ct.getPubDataCache());
	}

	ct.getRequest().getSession().setAttribute("pub", ct.getPubDataCache());
	ct.getPriDataCache().setParam("view", dataJson.toString());
    }

    public void returnFun(String rid, JSONObject robj, Context ct) {
	returnFun(rid, robj, null, ct);
    }

    public void returnFun(String rid, Context ct) {
	returnFun(rid, null, null, ct);
    }
    
    public List<Map<String, Object>> getSysCodeEr(List<TOtherSysCode> sysCodelists){
	//JSONArray arrays= JSONArray.parseArray(JSON.toJSONString(sysCodelists));
	TOtherSysCode tos = null;
	List<Map<String, Object>> listMapEr = new ArrayList<>();
	for(int i = 0;i < sysCodelists.size(); i++) {
	    tos = sysCodelists.get(i);
	    Map<String, Object> map = new HashMap<>();
	    map.put("data_language", tos.getDataLanguage());
	    map.put("erorcd", tos.getErorcd());
	    map.put("erortx", tos.getErortx());
	    listMapEr.add(map);
	}
	return listMapEr;
	
    }
    
    

}
