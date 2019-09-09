package cn.sunline.tiny;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.sunline.tiny.core.PubCache;
import cn.sunline.tiny.core.TinyException;
import cn.sunline.tiny.util.ReadXmlConfig;
import cn.sunline.tiny.web.Context;

//import cn.sunline.tiny.web.filter.TinyControlMore;

@Component("PassControl")
public class PassControl {

    private static final Logger LOG = LoggerFactory.getLogger(PassControl.class);

    private JSONObject accessJson = new JSONObject();

    public JSONObject getAccessJson() {
	return accessJson;
    }

    public void setAccessJson(JSONObject accessJson) {
	this.accessJson = accessJson;
    }

    public PassControl() {
	initXml();
    }

    public boolean pass(Context ct) {
	LOG.debug("start passControl");
//		LOG.debug("pub:"+ct.getPubDataCache().toString());

	// 根据 xml文件的内容 比较pub中记录的值
	JSONObject accessParam = ((JSONObject) ct.getPubDataCache().getOrDefault("accessParam", new JSONObject()));
	LOG.debug("transcode:" + ct.getTransCode());
	JSONObject accessJsonOne = accessJson.getJSONObject(ct.getTransCode());

	JSONArray lasttranses = accessJsonOne.getJSONArray("lasttranses");
	LOG.debug("transes:" + lasttranses.toJSONString());

	// 根据trans获取上一接口码, 然后获取上一接口码保存的状态
	boolean pass = false;
	// allpass配合pass,避免出现所有的判断都是true,将没有把pass变为true的机会
	boolean allpass = true;
	if (accessJsonOne.getBooleanValue("judgeAnd")) {
	    for (int i = 0, length = lasttranses.size(); i < length; i++) {
		// 检查所有接口对应的实际值, "且",前置接口只要有一个不通过, 不能继续当前接口
		LOG.debug("key:" + lasttranses.getJSONObject(i).getString("value"));
		// 进入访问不删除标记,在访问完成判断是否删除标记
		if (accessParam.get(lasttranses.getJSONObject(i).getString("value")) == null) {
		    allpass = false;
		    break;
		}
	    }
	    if (allpass) {
		pass = true;
	    }
	} else {
	    for (int i = 0, length = lasttranses.size(); i < length; i++) {
		// 检查上一接口对应的实际值, 前置接口只要有一个通过,那么可以接续访问当前接口
		LOG.debug("key:" + lasttranses.getJSONObject(i).getString("value"));
		if (accessParam.get(lasttranses.getJSONObject(i).getString("value")) != null) {
		    // accessParam.remove(lasttranses.getJSONObject(i).getString("value"));
		    pass = true;
		    break;
		}
	    }
	}
//		LOG.debug("pri:"+ct.getPriDataCache().toString());
	if (!pass && accessJsonOne.get("reqlimit") != null) {
	    // 检查当前接口的请求访问限制,只要有一个参数没通过,不允许访问
	    allpass = true;
	    for (int i = 0, length = accessJsonOne.getJSONArray("reqlimit").size(); i < length; i++) {
		LOG.debug("key:" + accessJsonOne.getJSONArray("reqlimit").getJSONObject(i).getString("key"));
		if (!((JSONObject) ct.getPriDataCache().get("jsonsObj"))
			.get(accessJsonOne.getJSONArray("reqlimit").getJSONObject(i).getString("key"))
			.equals(accessJsonOne.getJSONArray("reqlimit").getJSONObject(i).get("value"))) {
		    allpass = false;
		    break;
		}
	    }
	    if (allpass) {
		pass = true;
	    }
	}

	return pass;
    }

    // 根据 xml文件的内容 比较robj返回结果 然后 xml内容 记录到pub中
    public void returnRecord(JSONObject rview, PubCache pub) {
	JSONObject accessJsonOne = accessJson.getJSONObject(rview.getString("transCode"));
	boolean pass = false;
	LOG.debug("rview:" + rview.toJSONString());
	LOG.debug("accessJsonOne:" + accessJsonOne.toJSONString());
	if (rview.getString("rcode").equals(accessJsonOne.getString("rcode"))) {
	    pass = true;
	    if (accessJsonOne.get("robj") != null) {
		for (int i = 0, length = accessJsonOne.getJSONArray("robj").size(); i < length; i++) {
		    if (!rview.getJSONObject("robj")
			    .get(accessJsonOne.getJSONArray("robj").getJSONObject(i).getString("key"))
			    .equals(accessJsonOne.getJSONArray("robj").getJSONObject(i).get("value"))) {
			pass = false;
		    }
		}
	    }
	}
	if (pass) {
	    JSONObject accessParam = ((JSONObject) pub.getOrDefault("accessParam", new JSONObject()));
	    accessParam.put(rview.getString("transCode"), true);

	    // 在当前接口的访问控制验证成功且访问成功(默认000000+robj)后删除标记
	    if (accessJsonOne.get("lasttranses") != null) {
		JSONArray lasttranses = accessJsonOne.getJSONArray("lasttranses");
		for (int i = 0, length = lasttranses.size(); i < length; i++) {
		    LOG.debug("key:" + lasttranses.getJSONObject(i).getString("value"));
		    if (accessParam.get(lasttranses.getJSONObject(i).getString("value")) != null) {
			accessParam.remove(lasttranses.getJSONObject(i).getString("value"));
		    }
		}
	    }

	    pub.put("accessParam", accessParam);
	}
    }

    public void initXml() {
	try {
	    Document doc = ReadXmlConfig.getSrcDoc21("tinyConf/tinyControlMore.xml");
	    Element root = doc.getRootElement();
	    List list = root.elements();
	    for (int i = 0; i < list.size(); i++) {
		Element control = (Element) list.get(i);
		String controlgroup = control.attributeValue("trans");

		List clist = control.elements();
		for (int ii = 0; ii < clist.size(); ii++) {
		    Element url = (Element) clist.get(ii);

		    String transcode = url.attributeValue("name").substring(0, url.attributeValue("name").length() - 4);
		    JSONObject accessJsonTemp = accessJson.getJSONObject(transcode);
		    if (accessJsonTemp == null) {
			accessJsonTemp = new JSONObject();
		    }
		    accessJsonTemp.put("id", url.attributeValue("id"));
		    if (url.attributeValue("rcode") != null) {
			accessJsonTemp.put("rcode", url.attributeValue("rcode"));
		    } else {
			accessJsonTemp.put("rcode", "000000");
		    }
		    if (url.attributeValue("robj") != null) {
			accessJsonTemp.put("robj", JSONArray.parse(url.attributeValue("robj").replace("'", "\"")));
		    }
		    if (url.attributeValue("reqlimit") != null) {
			accessJsonTemp.put("reqlimit",
				JSONArray.parse(url.attributeValue("reqlimit").replace("'", "\"")));
		    }

		    if (ii > 0) {
			String lasturl = url.attributeValue("lasturl");
			if ("true".equals(url.attributeValue("judgeAnd"))) {
			    accessJsonTemp.put("judgeAnd", true);
			} else {
			    accessJsonTemp.put("judgeAnd", false);
			}
			// 记录上一个接口值
			if (accessJsonTemp.get("lasttranses") == null) {
			    accessJsonTemp.put("lasttranses", new JSONArray());
			}
			accessJsonTemp.getJSONArray("lasttranses").add(JSONObject.parse(
				("{'key':'" + controlgroup + "','value':'" + lasturl + "'}").replace("'", "\"")));
		    } else {
			if (accessJsonTemp.get("lasttranses") == null) {
			    accessJsonTemp.put("lasttranses", new JSONArray());
			}
		    }
		    accessJson.put(transcode, accessJsonTemp);
		}
	    }
	    LOG.info("accessJson:" + accessJson.toJSONString());
	} catch (Exception e) {
	    LOG.error(e.getMessage(), e);
	    throw new TinyException("s0104", "load config error...", e);
	}
    }
}
