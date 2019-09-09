package cn.sunline.demo.service;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TGoodsType;
import cn.sunline.demo.mapper.*;
@Service("tGoodsTypeService")
public class TGoodsTypeService extends BaseService<TGoodsType,String> {
	@Autowired
	private TGoodsTypeMapper tGoodsTypeMapper;

	public BaseMapper<TGoodsType,String> getMapper() {
		return tGoodsTypeMapper;
	}
	public TGoodsType checkCateExisted( String p ){
		return tGoodsTypeMapper.checkCateExisted(p);
	}

	public List<TGoodsType> getAlltype(){
		return tGoodsTypeMapper.getAlltype();
	}

}