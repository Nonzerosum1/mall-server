package cn.sunline.demo.mapper;

import java.util.List;

import cn.sunline.demo.entity.TGoods;

public interface TGoodsMapper extends BaseMapper<TGoods, String> {

    public List<TGoods> getAllPros(String p);

    public String maxGoodId(String p);

}