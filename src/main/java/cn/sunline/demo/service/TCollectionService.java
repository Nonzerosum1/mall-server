package cn.sunline.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sunline.demo.entity.TCollection;
import cn.sunline.demo.mapper.*;
@Service("tCollectionService")
public class TCollectionService extends BaseService<TCollection,String> {
	@Autowired
	private TCollectionMapper tCollectionMapper;

	public BaseMapper<TCollection,String> getMapper() {
		return tCollectionMapper;
	}
}
