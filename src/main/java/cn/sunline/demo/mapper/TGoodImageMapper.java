package cn.sunline.demo.mapper;
import cn.sunline.demo.entity.TGoodImage;

public interface TGoodImageMapper extends BaseMapper<TGoodImage,String> {

	public TGoodImage getProImage( String p );

}