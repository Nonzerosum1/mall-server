package cn.sunline.demo.mapper;
import cn.sunline.demo.entity.TGoodsTypeDetail;

public interface TGoodsTypeDetailMapper extends BaseMapper<TGoodsTypeDetail,String> {

	public TGoodsTypeDetail getGoodTypeDetailByTypeId( String p );

}