package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TGoodsTypeDetail;
import cn.sunline.demo.mapper.*;
@Service("tGoodsTypeDetailService")
public class TGoodsTypeDetailService extends BaseService<TGoodsTypeDetail,String> {
	@Autowired
	private TGoodsTypeDetailMapper tGoodsTypeDetailMapper;

	public BaseMapper<TGoodsTypeDetail,String> getMapper() {
		return tGoodsTypeDetailMapper;
	}
	public TGoodsTypeDetail getGoodTypeDetailByTypeId( String p ){
		return tGoodsTypeDetailMapper.getGoodTypeDetailByTypeId(p);
	}

}