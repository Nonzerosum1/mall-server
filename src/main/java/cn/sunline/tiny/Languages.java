package cn.sunline.tiny;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.sunline.demo.entity.TOtherSysCode;
import cn.sunline.demo.mapper.TOtherSysCodeMapper;
import cn.sunline.tiny.core.BeanFactory;
import cn.sunline.tiny.core.Constant;
import cn.sunline.tiny.core.data.database.TinyJdbcDao;
import cn.sunline.tiny.core.util.JSON;
import flow.file.util.BeanUtil;

@Component("languages")
public class Languages {

    private static final Logger LOG = LoggerFactory.getLogger(Languages.class);

//    @Autowired
//    @Qualifier("tinyJdbcDao")
//    private TinyJdbcDao dao;

    private final String sql = "select erorcd, erortx, data_language from t_other_sys_code where sys_n='A' and t_status = '0' and del = 0 ";

    private JSONObject lang_zh_CN = new JSONObject();
    private JSONObject lang_en_US = new JSONObject();
    private JSONObject lang_th_TH = new JSONObject();

    public void load() {
	if (lang_zh_CN.size() == 0) {
	    synchronized (this) {
		if (lang_zh_CN.size() == 0) {
//					loadData();
		    loadDBParam();
		}
	    }
	}
    }

    public JSONObject lang(String langStr) {
	load();
//		if(StringUtils.isBlank(langStr))
//			return lang_en_US;
	if ("lang_th_TH".equals(langStr)) {
	    return lang_th_TH;
	}
//		try {
////			return (JSONObject) Languages.class.getDeclaredField(langStr).get(Languages.class);
//			return (JSONObject) this.getClass().getDeclaredField(langStr).get(this);
//		} catch (Exception e) {
//			LOG.debug(e.getMessage(), e);
//		}
	return lang_en_US;
    }

    public synchronized void refresh() {
	loadDBParam();
    }

    /**
     * 从数据库件中获取对应的返回码
     */
    public void loadDBParam() {
	//TinyJdbcDao dao = BeanFactory.getBean("tinyJdbcDao");
	//List<Map<String, Object>> languages = dao.queryList(this.sql);
	List<TOtherSysCode> sysCodelists = BeanUtil.get_tOtherSysCodeService().getAllSysCode();
	List<Map<String, Object>> languages = BeanUtil.get_tls().getSysCodeEr(sysCodelists);
	JSONObject lang_en_US_temp = new JSONObject();
	JSONObject lang_th_TH_temp = new JSONObject();
	for (Map<String, Object> rlang : languages) {
	    int dataLang = Integer.parseInt(rlang.get("data_language").toString());
	    if (dataLang == 2) {
		lang_en_US_temp.put("i_" + rlang.get("erorcd"), rlang.get("erortx"));
	    } else if (dataLang == 3) {
		lang_th_TH_temp.put("i_" + rlang.get("erorcd"), rlang.get("erortx"));
	    }
	}
	lang_th_TH = lang_th_TH_temp;
	lang_en_US = lang_en_US_temp;
	lang_zh_CN = lang_en_US_temp;
    }

    /**
     * 从数据库获取数据
     */
    public void loadData() {
	lang_zh_CN = JSONObject.parseObject(ReadFile(Constant.SRC_CONFIG + "/tinyConf/language_zh_CN.json"));
	lang_en_US = JSONObject.parseObject(ReadFile(Constant.SRC_CONFIG + "/tinyConf/language_en_US.json"));
	lang_th_TH = JSONObject.parseObject(ReadFile(Constant.SRC_CONFIG + "/tinyConf/language_th_TH.json"));
    }

    /**
     * 从文件中获取对应的返回码
     * 
     * @param path
     * @return
     */
    public static String ReadFile(String path) {
	BufferedReader reader = null;
	String laststr = "";
	try {
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
	    String tempString = null;
	    while ((tempString = reader.readLine()) != null) {
		int ind = tempString.indexOf("//");
		if (ind >= 0) {
		    tempString = tempString.substring(0, ind);
		}
		laststr += tempString + "\n";
	    }
//        System.out.print(laststr);
	} catch (IOException e) {
	    LOG.debug(e.getMessage(), e);
	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException e) {
		    LOG.debug(e.getMessage(), e);
		}
	    }
	}
	return laststr;
    }
}
