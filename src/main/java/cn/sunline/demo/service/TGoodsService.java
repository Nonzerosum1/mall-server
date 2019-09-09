package cn.sunline.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.sunline.demo.entity.TGoods;
import cn.sunline.demo.mapper.*;
import cn.sunline.tiny.util.Tools;

@Service("tGoodsService")
public class TGoodsService extends BaseService<TGoods, String> {
    @Autowired
    private TGoodsMapper tGoodsMapper;
      
    @Autowired
    private Tools tls;
    
    public String getMaxGoodId(String id) {
	String tGoodId = maxGoodId(id);
	if(tls.isNull(tGoodId)) {
	    return "1";
	}
	Long goodId = Long.parseLong(tGoodId) + 1;
	return (goodId).toString();
    }

    public BaseMapper<TGoods, String> getMapper() {
	return tGoodsMapper;
    }

    public List<TGoods> getAllPros(String p) {
	return tGoodsMapper.getAllPros(p);
    }

    public String maxGoodId(String p) {
	return tGoodsMapper.maxGoodId(p);
    }

}