package flow.file.util;
import cn.sunline.tiny.core.BeanFactory;
public class BeanUtil{
	public static cn.sunline.demo.service.TRoleService get_tRoleService(){
		return BeanFactory.getBean("tRoleService");
	}
	public static cn.sunline.demo.service.TOrderService get_tOrderService(){
		return BeanFactory.getBean("tOrderService");
	}
	public static cn.sunline.demo.service.TGoodsTypeService get_tGoodsTypeService(){
		return BeanFactory.getBean("tGoodsTypeService");
	}
	public static cn.sunline.demo.service.TGoodImageService get_tGoodImageService(){
		return BeanFactory.getBean("tGoodImageService");
	}
	public static cn.sunline.demo.service.TGoodAttributesService get_tGoodAttributesService(){
		return BeanFactory.getBean("tGoodAttributesService");
	}
	public static cn.sunline.demo.service.TUsersService get_tUsersService(){
		return BeanFactory.getBean("tUsersService");
	}
	public static cn.sunline.demo.service.TUserRoleService get_tUserRoleService(){
		return BeanFactory.getBean("tUserRoleService");
	}
	public static cn.sunline.demo.service.TMenuService get_tMenuService(){
		return BeanFactory.getBean("tMenuService");
	}
	public static cn.sunline.demo.service.TGoodsDetailService get_tGoodsDetailService(){
		return BeanFactory.getBean("tGoodsDetailService");
	}
	public static cn.sunline.demo.service.TGoodsTypeDetailService get_tGoodsTypeDetailService(){
		return BeanFactory.getBean("tGoodsTypeDetailService");
	}
	public static cn.sunline.tiny.DataUtil get_DataUtil(){
		return BeanFactory.getBean("dataUtil");
	}
	public static cn.sunline.demo.service.TGoodsService get_tGoodsService(){
		return BeanFactory.getBean("tGoodsService");
	}
	public static cn.sunline.demo.service.TShoppingcartService get_tShoppingcartService(){
		return BeanFactory.getBean("tShoppingcartService");
	}
	public static cn.sunline.tiny.util.Tools get_tls(){
		return BeanFactory.getBean("Tools");
	}
	public static cn.sunline.demo.service.TOtherSysCodeService get_tOtherSysCodeService(){
		return BeanFactory.getBean("tOtherSysCodeService");
	}
	public static cn.sunline.demo.service.TCollectionService get_tCollectionService(){
		return BeanFactory.getBean("tCollectionService");
	}
	public static cn.sunline.tiny.Languages get_lang(){
		return BeanFactory.getBean("languages");
	}
	public static cn.sunline.tiny.Customer get_cust(){
		return BeanFactory.getBean("customer");
	}
	public static cn.sunline.demo.service.TGoodPromotionService get_tGoodPromotionService(){
		return BeanFactory.getBean("tGoodPromotionService");
	}
}
