package com.example.xwang.xwangandroidframework.constant;

/**
 * Created by yejiasun on 2016/6/20.
 */
public class Constant {

    //    本地缓存的文件名
    public final static String SHAREDPREFERENCES_FILE = "ht_hy";
    //    邮箱
    public final static String EXTRA_USER_EMAIL = "email";
    //    用户名
    public final static String EXTRA_USER_USER_NAME = "user_name";
    //    用户的QQ号
    public final static String EXTRA_USER_QQ = "qq";
    //真实姓名
    public final static String EXTRA_USER_REAL_NAME = "real_name";
    //    手机号
    public final static String EXTRA_USER_TELPHONE = "telphone";
    //    用户的tokenIdÒ
    public final static String EXTRA_USER_TOKENID = "token_id";
    //    R的平方值
    public final static String EXTRA_RSQUARED = "rsquared";
    //    当前界面
    public final static String EXTRA_CURRENT_INDEX = "current_index";
    //    盈利比率
    public final static String EXTRA_PROFITABILITY_RATIO = "profitability_ratio";
    //    受益风险比
    public final static String EXTRA_EARNINGS_RISK_THAN = "earnings_risk_than";
    //    模型介绍
    public final static String EXTRA_MODEL_INTRODUCTION = "model_introduction";
    //策略运行股池id
    public final static String EXTRA_SYSTEM_STOCKS_POOL_ID = "system_stocks_pool_id";
    //模型名称
    public final static String EXTRA_MODEL_NAME = "model_name";
    //    模型显示名称
    public final static String EXTRA_MODEL_DISPLAY_NAME = "model_display_name";
    //    是否已订阅
    public final static String EXTRA_MODEL_SUBSCRIBE = "model_subscribe";
    //    与后台交互成功
    public final static int HTTP_STATE_SUCCESS = 0;
    //    与后台交互失败
    public final static int HTTP_STATE_FAIL = 1;
    //    监测登录
    public final static String RECEIVER_LOGIN = "receiver_login";


    // 内网url
//    private static String Url = "http://172.17.128.18:9000/";
//    外网url
    private static String Url = "http://183.129.255.162:9000/";

    //登录接口
    public static String LOGIN = Url + "UserService.svc/Login?telphone={phone}&password={pwd}";
    //  获取模型数据
    public static String MODELS = Url + "ModelService.svc/Models/{username}";
    //获取交易数据
    public static String MODEL_STRATEGY_EVALUATION = Url + "ModelService.svc/GetModelStrategyEvaluationData/{MODELNAME}";
    //    今日模型信号
    public static String MODEL_TODAY_TRADER_SIGNAL = Url + "ModelService.svc/GetUserSubscribeModelTodayTraderSignal/{USERNAME}";
    //历史模型
    public static String MODEL__TRADER_SIGNAL = Url + "ModelService.svc/GetUserSubscribeModelTraderSignal/{USERNAME}";
    //    订阅模型
    public static String USER_SUBSCRIBE_MODEL = Url + "UserService.svc/UserSubscribeModel?username={USERNAME}&modelname={MODELNAME}&tokenid={TOKENID}";
    //取消订阅模型
    public static String USER_UNSUBSCRIBE_MODEL = Url + "UserService.svc/UserUnSubscribeModel?username={USERNAME}&modelname={MODELNAME}&tokenid={TOKENID}";
    //注册模型
    public static String REGISTER = Url + "UserService.svc/Register?requestcode={requestcode}&verificationCode={verificationCode}";
    //获取验证码模型
    public static String GET_VERIFICATION_CODE = Url + "UserService.svc/GetMessagesVerificationCode/{Telphone}";
    //    获取单个策略详情
    public static String GET_MODLE_BY_NAME = Url + "ModelService.svc/GetModelByName?modelname={modelname}&username={username}";
    //提交反馈接口
    public static String POST_FEEDBACK = Url + "UserService.svc/FeedBack/{feedBack}";
    //获取我的股池接口
    public static String GET_USER_POOL = Url + "UserStockPoolService.svc/GetUserPool?userName={userName}";
    //获取用户已订阅策略接口
    public static String GET_SUBSCRIBE = Url + "ModelService.svc/GetUserSubscribeModels/{userName}";
    //提交接收信号开关
    public static String POST_GET_NOT = Url + "UserService.svc/SetSignalReceiverSwitch?username={username}&on_off={on_off}";

    //获取买卖点列表
    public static String GET_BUYANDSEAL = Url + "ModelService.svc/GetModelTraderSignal?modelname={modelname}&pageindex={pageindex}&pagesize={pagesize}";
    //获取策略股池
    public static String GET_STOCKPOOL = Url + "UserStockPoolService.svc/GetSystemStockPool?SystemStocksPoolid={systemstockspoolid}&userName={userName}&pageindex={pageindex}&pagesize={pagesize}";
    //获取所有买卖点列表
    public static String GET_AllBUYANDSEAL = Url + "ModelService.svc/GetAllModelTraderSignal?pageindex={pageindex}&pagesize={pagesize}";
    //   更新我的股池
    public static String UPDATE_USER_STOCKS_POOL = Url + "UserStockPoolService.svc/UpdateUserStocksPool/{username}";
    //   回测界面获取
    public static String GET_BACKPRO = Url + "PortfolioBackTestService.svc/GetBackTestData?ModelName={ModelName}&TimeType={TimeType}";
    //   获取极光推送tags
    public static String GET_TAGS = Url + "UserService.svc/Tags/{username}";
    //   新的获取回测数据
    public static String GET_NEWBACKPRO = Url + "PortfolioBackTestService.svc/GetDefaultBackTestData?ModelName={ModelName}\n";
}
