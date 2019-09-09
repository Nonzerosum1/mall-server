package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TUserRole;
import cn.sunline.demo.mapper.*;
@Service("tUserRoleService")
public class TUserRoleService extends BaseService<TUserRole,String> {
	@Autowired
	private TUserRoleMapper tUserRoleMapper;

	public BaseMapper<TUserRole,String> getMapper() {
		return tUserRoleMapper;
	}
}
