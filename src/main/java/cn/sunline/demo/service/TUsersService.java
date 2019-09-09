package cn.sunline.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TUsers;
import cn.sunline.demo.mapper.*;

@Service("tUsersService")
public class TUsersService extends BaseService<TUsers, String> {
    @Autowired
    private TUsersMapper tUsersMapper;

    // ======================================

    public BaseMapper<TUsers, String> getMapper() {
	return tUsersMapper;
    }

    public TUsers slectTUsersByMobile(String p) {
	return tUsersMapper.slectTUsersByMobile(p);
    }

    public TUsers slectTUsersByName(String p) {
	return tUsersMapper.slectTUsersByName(p);
    }

    public TUsers getMaxUserId() {
	return tUsersMapper.getMaxUserId();
    }

    public TUsers slectTUsersLogin(TUsers p) {
	return tUsersMapper.slectTUsersLogin(p);
    }

}