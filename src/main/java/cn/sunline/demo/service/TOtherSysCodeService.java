package cn.sunline.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TOtherSysCode;

import cn.sunline.demo.mapper.*;

@Service("tOtherSysCodeService")
public class TOtherSysCodeService extends BaseService<TOtherSysCode, String> {
    @Autowired
    private TOtherSysCodeMapper tOtherSysCodeMapper;

    public BaseMapper<TOtherSysCode, String> getMapper() {
	return tOtherSysCodeMapper;
    }

    public List<TOtherSysCode> getAllSysCode() {
	return tOtherSysCodeMapper.getAllSysCode();
    }

}