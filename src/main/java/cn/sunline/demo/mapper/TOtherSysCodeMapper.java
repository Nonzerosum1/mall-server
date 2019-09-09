package cn.sunline.demo.mapper;

import java.util.List;

import cn.sunline.demo.entity.TOtherSysCode;

public interface TOtherSysCodeMapper extends BaseMapper<TOtherSysCode, String> {

    public List<TOtherSysCode> getAllSysCode();

}