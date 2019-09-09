package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TGoodImage;
import cn.sunline.demo.mapper.*;
@Service("tGoodImageService")
public class TGoodImageService extends BaseService<TGoodImage,String> {
	@Autowired
	private TGoodImageMapper tGoodImageMapper;

	public BaseMapper<TGoodImage,String> getMapper() {
		return tGoodImageMapper;
	}
	public TGoodImage getProImage( String p ){
		return tGoodImageMapper.getProImage(p);
	}

}