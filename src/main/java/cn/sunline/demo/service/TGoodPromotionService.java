package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TGoodPromotion;
import cn.sunline.demo.mapper.*;
@Service("tGoodPromotionService")
public class TGoodPromotionService extends BaseService<TGoodPromotion,String> {
	@Autowired
	private TGoodPromotionMapper tGoodPromotionMapper;

	public BaseMapper<TGoodPromotion,String> getMapper() {
		return tGoodPromotionMapper;
	}
}
