package cn.sunline.demo.mapper;

import cn.sunline.demo.entity.TUsers;

public interface TUsersMapper extends BaseMapper<TUsers, String> {

    public TUsers slectTUsersByMobile(String p);

    public TUsers slectTUsersByName(String p);

    public TUsers getMaxUserId();

	public TUsers slectTUsersLogin( TUsers p );

}