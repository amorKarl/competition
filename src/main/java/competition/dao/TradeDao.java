package competition.dao;

import competition.po.TTradesPublishEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11/011.
 */
public interface TradeDao {
    public boolean addScreen(String screen);
    public List listScreen();
    public Map existScreen(String screen);
    public boolean publishTrade(TTradesPublishEntity tTradesPublishEntity);
    public List listTrade(int page,int num);
    public boolean hadBuyTrade(int user_id,int trade_id);
    public Map existTrade(int trade_id);
    public boolean money(int user_id,BigDecimal money,int val);
    public void personRecord(int user_id,BigDecimal charge,String profit);
    public boolean tradeRecord(int purchaser,String startTime,int trade_id);
    public List queryTradeRecord(int user_id);
    public List queryPersonRecord(int user_id);
    public Map queryBuyDetail(int trade_id,int user_id);
    public Map queryBuyDetailSelf(int trade_id,int user_id);
    //2017.08.15
    public boolean tradeComment(int user_id,String comment,int recordId);

    //2017.08.16
    public Map hasComment(int user_id,int trade_id);

    //2017.09.20
    public List queryTradeById(int user_id);
}
