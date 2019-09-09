package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TOrder;
import cn.sunline.demo.mapper.*;
@Service("tOrderService")
public class TOrderService extends BaseService<TOrder,String> {
	@Autowired
	private TOrderMapper tOrderMapper;

	public BaseMapper<TOrder,String> getMapper() {
		return tOrderMapper;
	}
}
