package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TShoppingcart;
import cn.sunline.demo.mapper.*;
@Service("tShoppingcartService")
public class TShoppingcartService extends BaseService<TShoppingcart,String> {
	@Autowired
	private TShoppingcartMapper tShoppingcartMapper;

	public BaseMapper<TShoppingcart,String> getMapper() {
		return tShoppingcartMapper;
	}
}
