package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TGoodsDetail;
import cn.sunline.demo.mapper.*;
@Service("tGoodsDetailService")
public class TGoodsDetailService extends BaseService<TGoodsDetail,String> {
	@Autowired
	private TGoodsDetailMapper tGoodsDetailMapper;

	public BaseMapper<TGoodsDetail,String> getMapper() {
		return tGoodsDetailMapper;
	}
}
