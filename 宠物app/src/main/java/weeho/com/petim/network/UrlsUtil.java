package weeho.com.petim.network;

import java.io.Serializable;

/**
 * @author 作者 E-mail: wk
 * @version 创建时间：2017-4-24 上午10:44:50 类说明 联网接口地址信息管理类
 * @param
 */
public class UrlsUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	/******* 元数据获取********/
	public static final String initdata = "system/version.jhtml?";
	
	/******* 登录接口 ********/
	public static final String login = "user/login.jhtml?";

	/******* 个人信息接口 ********/
	public static final String info = "user/info.jhtml?";

	/******* 设置默认地址接口 ********/
	public static final String defaultAddress = "address/receivedefault.jhtml?";

	/******* 登出接口 ********/
	public static final String login_out = "user/logout.jhtml?";

	/******* 获取验证码接口 ********/
	public static final String register_code = "user/getsmscode.jhtml?";

	/******* 注册接口 ********/
	public static final String register = "user/register.jhtml?";

	/******* 修改密码接口 ********/
	public static final String updatepwd = "user/updatepwd.jhtml?";

	/******* 获取基本信息接口 ********/
	public static final String shopinfo = "shop/info.jhtml?";

	/******* 提交基本信息接口 ********/
	public static final String shopinfocommit = "shop/add.jhtml?";

	/******* 获取下游用户信息列表接口 ********/
	public static final String downuser_list = "downuser/list.jhtml?";

	/******* 提交修改下游用户信息接口 ********/
	public static final String downuser = "downuser/add.jhtml?";

	/******* 删除下游用户信息接口 ********/
	public static final String downuser_delete = "downuser/delete.jhtml?";

	/******* 区域城市接口 ********/
	public static final String hotCity = "system/arealist.jhtml?";

	/******* 获取拼单活动列表 ********/
	public static final String productList = "fightactivity/pdlist.jhtml?";
	
	/******* 获取批发活动列表 ********/
	public static final String wholeList = "activity/list.jhtml?";

	// 获取商品详情
	public static final String productDetail = "fightactivity/pddetail.jhtml?";

	// 添加购物车
	public static final String addShopCart = "shopcart/add.jhtml?";

	// 获取购物车详情
	public static final String getShopcartInfor = "shopcart/list.jhtml?";

	// 删除购物车中的商品
	public static final String deleteShopcartProduct = "shopcart/delete.jhtml?";

	// 支付接口获取
	public static final String pay = "mobile/pay.jhtml";
	
	// 京东支付接口获取
	public static final String jd_pay = "pay/jdpay.jhtml";

	// 送货地址接口获取
	public static final String list_address = "address/receivedlist.jhtml?";

	// 生成预付订单接口
	public static final String create_payment = "order/preoder.jhtml?";

	// 生成订单接口
	public static final String create_order = "order/pdcreateorder.jhtml?";

	// 获取订单列表
	public static final String get_order_list = "order/list.jhtml?";

	// 获取订单详情
	public static final String order_detail = "order/orderdetail.jhtml?";
	
	// 获取扫码订单支付
	public static final String SCAN_ORDER_DETAIL = "order/paydetail.jhtml?";

	// 获取订单详情
	public static final String order_express_detail = "order/express.jhtml?";
	
	// 获取省市区元数据接口
	public static final String province_list = "system/pcclist.jhtml?";
	
	// 获取基本数据接口
	public static final String baseinfo_list = "system/basedata.jhtml?";

	// 获取沙龙活动详情
	public static final String salon_detail = "salon/salondetail.jhtml?";

	// 获取沙龙列表
	public static final String salon_list = "salon/salonlist.jhtml?";

	// 沙龙报名
	public static final String salon_join = "salon/salonsign.jhtml?";

	// 沙龙报名二期
	public static final String salon_join_two = "salon/salonsign2.jhtml?";

	// 我的沙龙列表
	public static final String my_salon_list = "salon/mysalonlist.jhtml?";

	// 获取我的沙龙活动详情
	public static final String my_salon_detail = "salon/mysalondetail.jhtml?";

	// 请求交换联系方式
	public static final String exchange_contact = "salon/exchange.jhtml?";

	// 同意交换联系方式
	public static final String agreed_exchange_contact = "salon/exchangeagree.jhtml?";

	// 嘉宾详情
	public static final String customer_detail = "salon/guestdetail.jhtml?";

	// 提交地址数据接口
	public static final String commit_address = "address/receivedadd.jhtml?";

	// 提交地址数据接口
	public static final String delete_address = "address/receiveddelete.jhtml?";

	// 广告获取
	public static final String get_advertisement = "system/adlist.jhtml?";

	// 确认收货
	public static final String confirmGoods = "order/confirmorder.jhtml?";

	// 上传图片
	public static final String upload_image = "fileupload/uploadimg.jhtml?";

	// 绑定消息推送cid
	public static final String bindcid = "user/bindcid.jhtml?";

	// H5页面监听
	public static final String h5Url = "http://www.heheys.com/payfinished";
	
	// 优惠券H5页面监听
	public static final String h5Url_yhq = "http://www.heheys.com/choicecoupon";
	
	// H5页面监听需要登录
//	public static final String h5Url_login = RequestConfiguration.BASE_URL_TEST_LACALHOST+"h5/user/login.jhtml";
	public static final String h5Url_login = "http://www.heheys.com/api/h5/user/login.jhtml";

	public static final String h5Url_login_pay = "heheys://usercenter/login";

	//我的积分
	public static final String h5Url_coin = "heheys://usercenter/scoreitem";

	//我的个人中心
	public static final String h5Url_info = "heheys://usercenter/info";

	//H5调导航
	public static final String h5Url_route = "https://map.qq.com";

	//H5调地图导航
	public static final String h5Url_route_map = "qqmap://map/routeplan";

	//H5调地图导航下载
	public static final String h5Url_route_map_down = "3gimg.qq.com";

	//H5继续支付
	public static final String H5_PAYAGAIN = "http://www.heheys.com/api/h5/order/paydetail.jhtml ";


	
//	heheys://redirect?title=领取优惠券界面&heheurl=http://heheys.wllei.com/api/coupon/h5/list.jhtml&from=app
	// H5页面监听领取优惠券界面
	public static final String h5Url_mycoupn= "heheys://redirect";

	// H5页面监听需要支付
	public static final String H5URL_ORDER= "heheys://order/paydetail?oid=";

	// H5页面充值喝喝币界面
	public static final String h5Url_recharge= "heheys://usercenter/buycoin";

	// H5我要发行
	public static final String H5_PUBLISH= "publish.html";

	// 活动专场H5页面监听
	public static final String h5Url_hdzc = "heheys://activity/detail";
	
	// 拼单或者批发列表
	public static final String h5Url_pdlb = "heheys://activity/list";
	
	// 云店
	public static final String h5Url_cloudshop = "heheys://cloudshop/list";
	
	// 茅台云店
	public static final String h5Url_maoshop = "heheys://cloudshop/detail";
	
	// 我的钱包
	public static final String h5Url_myaccount = "heheys://usercenter/walltet";
	
	// 我的优惠券
	public static final String h5Url_coupon = "heheys://usercenter/coupon";
	
	// 我的积分
	public static final String h5Url_scoreitem = "heheys://usercenter/scoreitem";

	// 调取扫描
	public static final String H5_SCAN = "heheys://system/scancode";

	// 调取拨打电话
	public static final String H5_TEL = "tel:";

	// 调取我的
	public static final String H5_MY = "heheys://usercenter/info";

	// 我的充值记录
	public static final String h5Url_coinitem = "heheys://usercenter/coinitem";
	
	// 活动详情
	public static final String h5Url_activitydetaile = "heheys://activity/detail";

	// 消息提醒类型
	public static final String msgMain = "message/typelist.jhtml?";

	// 消息提醒列表接口
	public static final String msgList = "message/list.jhtml?";

	// 名片列表接口
	public static final String businessCardList = "buscard/buscardlist.jhtml?";

	// 个人名片详情接口
	public static final String businessCardDetail = "buscard/buscarddetail.jhtml?";

	// 添加个人名片接口
	public static final String AddbusinessCard = "buscard/addbuscard.jhtml?";

	// 修改名片接口
	public static final String UpdatebusinessCard = "buscard/remark.jhtml?";
	
	// 酒水需求列表
	public static final String drinksDemand = "drinksdemand/querydrinksdemand.jhtml?";
	
	// 业务数据获取
	public static final String businessData = "system/bizdata.jhtml?";
	
	// 酒水品牌列表
	public static final String drinksBrand = "system/winetype.jhtml?";
	
	// 增加酒水需求
	public static final String drinksDemandAdd = "drinksdemand/adddrinksdemand.jhtml?";
	
	// 修改酒水需求
	public static final String drinksDemandUpdate = "drinksdemand/updatedrinksdemand.jhtml?";
	
	// 获取酒水需求
	public static final String drinksDemanDetail = "drinksdemand/querydrinksdemanddetails.jhtml?";
	
	// 获取酒讯列表
	public static final String drinksInfoList = "/winemsg/list.jhtml?";
	
	// 获取产地列表
	public static final String placeNameList = "system/winearealistall.jhtml?";
	
	// 获取酒品列表
	public static final String brandList = "system/winetypelist.jhtml?";
	
	// 获取酒品详情v1.0
	public static final String newdetaileList = "activity/detail.jhtml?";

	// 获取酒品详情v2.0
	public static final String GOODS_PRODUCES = "activity/detail.jhtml?";

	// 支付宝支付签名
	public static final String alipay_sign = "pay/ordersign.jhtml?";
	
	// 首页商品推荐
	public static final String home_goods = "activity/home.jhtml?";
	
	// 获取当前用户的余额
	public static final String user_balance = "wallet/query.jhtml?";
	
	// 获取当前用户的默认提现卡号
	public static final String user_card = "wallet/getdefaultaccount.jhtml?";
	
	// 设置当前用户的默认提现卡号
	public static final String set_default_card = "wallet/add.jhtml?";
	
	// 设置提现操作
	public static final String get_withdraw = "wallet/apply.jhtml?";
	
	// 获取提现列表
	public static final String payed_record = "wallet/list.jhtml?";
	
	// 获取订单列表
	public static final String order_list = "order/list.jhtml?";
	
	// 确认收货
	public static final String sure_order = "order/confirmorder.jhtml?";
	
	// 取消订单
	public static final String cancle_order = "order/cancelorder.jhtml?";
	
	// 余额支付
	public static final String hehe_pay = "wallet/hehepay.jhtml?";
	
	// 商铺列表
	public static final String shop_list = "shop/list.jhtml?";
	
	// 商铺详情列表
	public static final String shop_goods_list = "shop/prolist.jhtml?";
	
	// 商铺详情简介
	public static final String shop_goods_item = "shop/item.jhtml?";
	
	// 阿里支付异步回调结果
	public static final String alipay_result = "pay/getpayresult.jhtml?";
	
	// 一键分享
	public static final String share_url = "share/shareurl.jhtml?";
	
	// 在线客服IM
	public static final String im_list = "customer/glist.jhtml?";
	
	// 微信支付paysign
	public static final String wx_pay = "pay/ordersign.jhtml?";
	
	// 优惠券列表
	public static final String coupon_msg = "pay/couponmessage.jhtml?";
	
	// 优惠券列表
	public static final String coupon_list = "wallet/mycoupon/listcount.jhtml?";
	
	// 可用优惠券列表
	public static final String coupon_user = "order/couponlist.jhtml?";
	
	// 发票历史信息
	public static final String invoice_bean = "order/invoice.jhtml?";
	
	// 默认送货地址信息
	public static final String default_add = "address/info.jhtml?";
	
	// 默认用户积分明细信息
	public static final String user_points_item = "score/scoreitem.jhtml?";
	
	// 默认用户积分信息
	public static final String user_points = "score/info.jhtml?";
	
	// 签到
	public static final String user_sign = "score/checkin.jhtml?";
	
	// 取消订单提示
	public static final String order_cancle = "order/cancelmsg.jhtml?";

	// 删除订单提示
	public static final String ORDER_DELETE = "order/delete.jhtml?";

	// 再次购买
	public static final String BUY_MORE = "order/buyagain.jhtml?";

	// 默认用户喝喝币
	public static final String my_coin = "coin/info.jhtml?";
	
	// 用户需要充值喝喝币
	public static final String my_coin_cz = "coin/coinlist.jhtml?";
	
	// 默认用户喝喝币明细信息
	public static final String user_hehemoney_item = "coin/coinitem.jhtml?";
	
	// 默认用户喝喝币明细信息
	public static final String rechange_card = "coin/buycoin.jhtml?";
	
	// 默认用户喝喝币明细信息
	public static final String rechange_result = "coin/payresult.jhtml?";
	
	// 全额喝喝币支付 
	public static final String heheall_pay = "order/pay.jhtml?";
	
	//线下支付
	public static final String line_off = "order/changecod.jhtml?";
	
	//套装列表
	public static final String suit_list = "activity/suitlist.jhtml?";
	
	//套装加入购物车
	public static final String suit_shopping = "shopcart/addSuit.jhtml?";

	//发送位置
	public static final String send_location = "shopcart/addSuit.jhtml?";

	//获取当前区域品牌列表
	public static final String CURRT_BRAND = "system/searchBrand.jhtml?";

	//获取当前品牌地图分布
	public static final String GOODS_MAP = "activity/activityArea.jhtml?";

	//获取检索结果
	public static final String SEARCH_RESULT = "activity/search.jhtml?";

	//获取扫描结果
	public static final String SCAN_RESULT = "scancode/result.jhtml?";

	//获取需要分享结果
	public static final String SHAER_RESULT = "share/shareurl.jhtml?";

	//获取商品收藏结果
	public static final String COLLECT_GOOD_RESULT = "user/favorite/product.jhtml?";

	//获取商家收藏结果
	public static final String COLLECT_BUSSINESS_RESULT = "user/favorite/shop.jhtml?";

	//添加收藏结果
	public static final String ADD_COLLECT_RESULT = "user/favorite/add.jhtml?";

	//取消收藏结果
	public static final String CANCLE_COLLECT_RESULT = "user/favorite/delete.jhtml?";

	//发送心跳
	public static final String HEART_RESULT = "/api/ee/gps/location.jhtml?";

	//修改密码
	public static final String UPDATE_PAW = "user/changepwd.jhtml?";
}
