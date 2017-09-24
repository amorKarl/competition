package competition.service;

import competition.po.TTradesPublishEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11/011.
 */
public interface TradeService {
    public Map addScreen(String screen);
    public List listScreen();
//    public Map publishTrade( String screen, String username, String money,String message, String zhukehe, String banchangzhukehe, String rangqiuzhukehe, String rangqiu, String rangqiudaxi, String banchangrangqiudaxi, String jiaoqiudaxi, String bodan, String banchangbodan, String diyiqiuruqiu, String zongruqiu , String ruqiudanshuang, String banquanchang );
    public Map publishTrade(TTradesPublishEntity tTradesPublishEntity);
    public List listTrade(int page,int num);
    public Map buyTrade(String username,int trade_id);
    public List queryTradeRecord(String username);
    public List queryPersonRecord(String username);
    public Map queryBuyDetail(int trade_id,int user_id);
    //2017.08.15
    public Map tradeComment(int trade_id,String comment,String username);

    //2017.09.20
    public Map queryTradeById(int user_id);
}
