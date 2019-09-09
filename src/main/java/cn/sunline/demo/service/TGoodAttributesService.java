package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TGoodAttributes;
import cn.sunline.demo.mapper.*;
@Service("tGoodAttributesService")
public class TGoodAttributesService extends BaseService<TGoodAttributes,String> {
	@Autowired
	private TGoodAttributesMapper tGoodAttributesMapper;

	public BaseMapper<TGoodAttributes,String> getMapper() {
		return tGoodAttributesMapper;
	}
}
