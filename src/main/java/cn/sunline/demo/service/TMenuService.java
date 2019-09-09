package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TMenu;
import cn.sunline.demo.mapper.*;
@Service("tMenuService")
public class TMenuService extends BaseService<TMenu,String> {
	@Autowired
	private TMenuMapper tMenuMapper;

	public BaseMapper<TMenu,String> getMapper() {
		return tMenuMapper;
	}
}
