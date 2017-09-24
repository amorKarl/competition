package competition.service.impl;

import competition.dao.TradeDao;
import competition.dao.UserDao;
import competition.po.*;
import competition.service.TradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11/011.
 */
@Service("tradeService")
public class TradeServiceImpl implements TradeService {

    @Resource(name = "tradeDao")
    private TradeDao tradeDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 001.增加场次
     *
     * @param screen
     * @return
     */
    public Map addScreen(String screen) {
        Map map = new HashMap();
        if (tradeDao.addScreen(screen) == true) {
            //插入成功
            map.put("status", "200");
            map.put("message", "场次插入成功");
        } else {
            //插入失败
            map.put("status", "212");
            map.put("message", "场次插入失败");
        }
        return map;
    }

    /**
     * 002.列举所有场次
     *
     * @return
     */
    public List listScreen() {
        return tradeDao.listScreen();
    }

    /**
     * 003.发布交易
     *
     * @return
     */
    public Map publishTrade(TTradesPublishEntity tTradesPublishEntity) {
        Map map = new HashMap();
        if (tradeDao.publishTrade(tTradesPublishEntity) == true) {
            map.put("status", "200");
            map.put("message", "交易发布成功");
            return map;
        } else {
            map.put("status", "215");
            map.put("message", "交易发布失败");
            return map;
        }


    }

    /**
     * 004.列出已发布交易
     *
     * @return
     */
    public List listTrade(int page, int num) {
        return tradeDao.listTrade(page, num);
    }

    /**
     * 005.购买交易接口
     *
     * @param username
     * @param trade_id
     * @return
     */
    public Map buyTrade(String username, int trade_id) {
        Map map = new HashMap();
        Map map_user = userDao.isExist(username);
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list_user = (List) map_user.get("object");
            if (tradeDao.hadBuyTrade(list_user.get(0).getId(), trade_id) == false) {
                Map map_trade = tradeDao.existTrade(trade_id);
                if (map_trade.get("isExist").toString().equals("true")) {
                    List<TTradesPublishEntity> list_trade = (List) map_trade.get("object");
                    BigDecimal big_user = list_user.get(0).getMoney();
                    BigDecimal big_trade = list_trade.get(0).getTradeMoney();

                    if (big_user.compareTo(big_trade) >= 0) {
                        //买方扣钱

                        if (tradeDao.money(list_user.get(0).getId(), big_trade, 0) == true) {
                            tradeDao.personRecord(list_user.get(0).getId(), big_trade, "支出");
                            //卖方加钱
                            if (tradeDao.money(list_trade.get(0).getInitiator(), big_trade, 1) == true) {
                                tradeDao.personRecord(list_trade.get(0).getInitiator(), big_trade, "收入");

                                if (tradeDao.tradeRecord(list_user.get(0).getId(), list_trade.get(0).getStartTime(), trade_id) == true) {
                                    map.put("status", "200");
                                    map.put("message", "交易成功");
                                    return map;
                                } else {
                                    map.put("status", "222");
                                    map.put("message", "交易记录失败");
                                    return map;
                                }

                            } else {
                                map.put("status", "221");
                                map.put("message", "卖方加钱失败");
                                return map;
                            }
                        } else {
                            map.put("status", "220");
                            map.put("message", "买方扣款失败");
                            return map;
                        }

                    } else {
                        //余额不足
                        map.put("status", "219");
                        map.put("message", "用户余额不足");
                        return map;
                    }

                } else {
                    //交易不存在
                    map.put("status", "218");
                    map.put("message", "交易不存在，请重新输入");
                    return map;
                }


            } else {
                //已经购买过
                map.put("status", "217");
                map.put("message", "已经购买过，请不要重复购买");
                return map;
            }
        } else {
            map.put("status", "216");
            map.put("message", "用户不存在");
            return map;
        }

    }

    public List queryTradeRecord(String username) {
        Map map_user = userDao.isExist(username);
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list = (List) map_user.get("object");
            return tradeDao.queryTradeRecord(list.get(0).getId());
        } else {
            return null;
        }
    }

    public List queryPersonRecord(String username) {
        Map map_user = userDao.isExist(username);
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list = (List) map_user.get("object");
            return tradeDao.queryPersonRecord(list.get(0).getId());
        } else {
            return null;
        }
    }

    public Map queryBuyDetail(int trade_id, int user_id) {


        Map map = new HashMap();
        Map map_self=tradeDao.queryBuyDetailSelf(trade_id, user_id);
        if(map_self.get("isExist").toString().equals("true")){
            List<TTradesPublishEntity> list = (List<TTradesPublishEntity>) map_self.get("object");
            map.put("result", list);
            map.put("status", "200");
            return map;
        }



        Map map_query = tradeDao.queryBuyDetail(trade_id, user_id);
        if (map_query.get("isExist").toString().equals("true")) {
            List<VTradesPublishDetailEntity> list = (List<VTradesPublishDetailEntity>) map_query.get("object");
            map.put("result", list);
            map.put("status", "200");
            return map;

        } else {
            map.put("status", "223");
            map.put("message", "无权限查看");
            return map;
        }
    }

    public Map tradeComment(int trade_id, String comment, String username) {
        Map map = new HashMap();
        Map map_user = userDao.isExist(username);
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list_user = (List<TUsersEntity>) map_user.get("object");
            Map map_record = tradeDao.hasComment(list_user.get(0).getId(), trade_id);
            if (map_record.get("isExist").toString().equals("true")) {
                Map map_trade = tradeDao.existTrade(trade_id);
                if (map_trade.get("isExist").toString().equals("true")) {
                    List<TTradesPublishEntity> list = (List<TTradesPublishEntity>) map_trade.get("object");
                    List<TTradesRecordEntity> list_record = (List<TTradesRecordEntity>) map_record.get("object");
                    if (tradeDao.tradeComment(list.get(0).getInitiator(), comment, list_record.get(0).getId()) == true) {
                        map.put("status", "200");
                        map.put("message", "评价成功");
                        return map;
                    } else {
                        map.put("status", "227");
                        map.put("message", "评价失败");
                        return map;
                    }
                } else {
                    map.put("status", "226");
                    map.put("message", "交易不存在");
                    return map;
                }
            } else {
                map.put("status", "229");
                map.put("message", "不能评价");
                return map;
            }

        } else {
            map.put("status", "228");
            map.put("message", "用户不存在");
            return map;
        }


    }

    public Map queryTradeById(int user_id) {
        Map map=new HashMap();
        List list=tradeDao.queryTradeById(user_id);
        map.put("result",list);
        map.put("length",list.size());
        return map;
    }


}
