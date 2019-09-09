package cn.sunline.demo.mapper;
import java.util.List;

import cn.sunline.demo.entity.TGoodsType;

public interface TGoodsTypeMapper extends BaseMapper<TGoodsType,String> {

	public TGoodsType checkCateExisted( String p );

	public List<TGoodsType> getAlltype();

}