package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TRole;
import cn.sunline.demo.mapper.*;
@Service("tRoleService")
public class TRoleService extends BaseService<TRole,String> {
	@Autowired
	private TRoleMapper tRoleMapper;

	public BaseMapper<TRole,String> getMapper() {
		return tRoleMapper;
	}
}
