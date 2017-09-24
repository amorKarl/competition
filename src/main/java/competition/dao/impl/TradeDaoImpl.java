package competition.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import competition.BaseDao;
import competition.dao.TradeDao;
import competition.po.*;
import competition.vo.AreaName;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11/011.
 */
@Repository("tradeDao")
public class TradeDaoImpl extends BaseDao implements TradeDao {
    /**
     * 001.增加场次
     *
     * @param screen
     * @return
     */
    public boolean addScreen(String screen) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TTradesScreenEntity tTradesScreenEntity = new TTradesScreenEntity();
        tTradesScreenEntity.setScreen(screen);
        try {
            session.save(tTradesScreenEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    /**
     * 002.列举所有场次
     *
     * @return
     */
    public List listScreen() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String s = "select  distinct screen from TTradesScreenEntity";
        Query query = session.createQuery(s);

        return query.list();
    }

    /**
     * 003.是否存在场次
     *
     * @param screen
     * @return
     */
    public Map existScreen(String screen) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTradesScreenEntity.class);
        Criterion criterion = Restrictions.eq("screen", screen);
        criteria.add(criterion);
        List<TTradesScreenEntity> list = criteria.list();
        Map map = new HashMap();
        if (list.size() == 0) {
            //不存在
            map.put("isExist", "false");
            return map;
        } else {
            //存在
            map.put("isExist", "true");
            map.put("object", list);
            return map;
        }
    }

    /**
     * 004.发布交易
     *
     * @return
     */
    public boolean publishTrade(TTradesPublishEntity tTradesPublishEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tTradesPublishEntity.setStartTime(ft.format(dNow).toString());

        try {
            session.save(tTradesPublishEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }


    /**
     * 005.列举交易
     *
     * @return
     */
    public List listTrade(int page, int num) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(VTradeDetailEntity.class).setFirstResult((page - 1) * num).setMaxResults(num);
        Order order = Order.desc("startTime");
        List<VTradeDetailEntity> list = criteria.addOrder(order).list();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIcon(AreaName.name + list.get(i).getIcon());
        }

        return list;
    }

    /**
     * 006.是否已经买过
     *
     * @param user_id
     * @param trade_id
     * @return
     */
    public boolean hadBuyTrade(int user_id, int trade_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTradesRecordEntity.class);
        Criterion c1 = Restrictions.eq("purchaser", user_id);
        Criterion c2 = Restrictions.eq("tradeId", trade_id);
        criteria.add(c1);
        criteria.add(c2);
        List<TTradesRecordEntity> list = criteria.list();
        if (list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 007.交易是否存在
     *
     * @param trade_id
     * @return
     */
    public Map existTrade(int trade_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTradesPublishEntity.class);
        Criterion criterion = Restrictions.eq("id", trade_id);
        criteria.add(criterion);
        List<TTradesPublishEntity> list = criteria.list();
        Map map = new HashMap();
        if (list.size() == 0) {
            map.put("isExist", "false");
        } else {
            map.put("isExist", "true");
            map.put("object", list);
        }
        return map;
    }

    /**
     * 008.增加钱
     *
     * @param user_id
     * @param money
     * @return
     */
    public boolean money(int user_id, BigDecimal money, int val) {
        //val 为1 加钱，   为0 扣钱
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TUsersEntity tUsersEntity = session.load(TUsersEntity.class, user_id);
        BigDecimal org = tUsersEntity.getMoney();
        if (val == 1) {
            org = org.add(money);
        } else {
            org = org.subtract(money);
        }

        tUsersEntity.setMoney(org);
        try {
            session.update(tUsersEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public void personRecord(int user_id, BigDecimal charge, String profit) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TUsersRecordEntity tUsersRecordEntity = new TUsersRecordEntity();
        tUsersRecordEntity.setUserId(user_id);
        tUsersRecordEntity.setCharge(charge);
        tUsersRecordEntity.setProfit(profit);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tUsersRecordEntity.setTime(ft.format(dNow).toString());
        try {
            session.save(tUsersRecordEntity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public boolean tradeRecord(int purchaser, String startTime, int trade_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TTradesRecordEntity tTradesRecordEntity = new TTradesRecordEntity();
        tTradesRecordEntity.setPurchaser(purchaser);
        tTradesRecordEntity.setStartTime(startTime);
        tTradesRecordEntity.setTradeId(trade_id);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tTradesRecordEntity.setEndTime(ft.format(dNow).toString());
        tTradesRecordEntity.setComment(0);
        try {
            session.save(tTradesRecordEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public List queryTradeRecord(int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(VTradesPublishDetailEntity.class);
        Criterion criterion = Restrictions.eq("purchaser", user_id);
        criteria.add(criterion);
        List<VTradesPublishDetailEntity> list = criteria.list();
        return list;
    }

    public List queryPersonRecord(int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TUsersRecordEntity.class);
        Criterion criterion = Restrictions.eq("userId", user_id);
        criteria.add(criterion);
        Order order = Order.desc("time");
        List<TUsersRecordEntity> list = criteria.addOrder(order).list();
        for (int i = 0; i < list.size(); i++) {
            String times[] = list.get(i).getTime().split(" ");
            list.get(i).setTime(times[0]);
        }
        return list;
    }

    public Map queryBuyDetail(int trade_id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
//
//        Criteria criteria1 = session.createCriteria(TTradesPublishEntity.class);
//        Criterion c = Restrictions.eq("id", trade_id);
//        criteria1.add(c);
//        List<TTradesPublishEntity> list_trade = criteria1.list();
//        if (list_trade.get(0).getInitiator() == user_id) {
//            Map map = new HashMap();
//                map.put("isExist", "true");
//                map.put("object", list_trade);
//            return map;
//        }


        Criteria criteria = session.createCriteria(VTradesPublishDetailEntity.class);
        Criterion c1 = Restrictions.eq("tradeId", trade_id);
        Criterion c2 = Restrictions.eq("purchaser", user_id);
        criteria.add(c1);
        criteria.add(c2);
        List<VTradesPublishDetailEntity> list = criteria.list();
        Map map = new HashMap();
        if (list.size() == 0) {
            map.put("isExist", "false");
        } else {
            map.put("isExist", "true");
            map.put("object", list);
        }
        return map;
    }

    public Map queryBuyDetailSelf(int trade_id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria1 = session.createCriteria(TTradesPublishEntity.class);
        Criterion c = Restrictions.eq("id", trade_id);
        criteria1.add(c);
        List<TTradesPublishEntity> list_trade = criteria1.list();
        Map map = new HashMap();
        if (list_trade.get(0).getInitiator() == user_id) {

            map.put("isExist", "true");
            map.put("object", list_trade);
            return map;
        } else {
            map.put("isExist", "false");
            return map;
        }
    }


    public boolean tradeComment(int user_id, String comment, int recordId) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TUsersEntity tUsersEntity = session.load(TUsersEntity.class, user_id);

        //查询用户发布了几个交易
        Criteria criteria=session.createCriteria(TTradesRecordEntity.class);
        Criterion c1=Restrictions.eq("userId",user_id);
        Criterion c2=Restrictions.eq("comment",1);
        criteria.add(c1);
        criteria.add(c2);
        List<TTradesPublishEntity>list=criteria.list();
        int count=list.size();
        double com=Double.parseDouble(comment);


        tUsersEntity.setRepuation(((tUsersEntity.getRepuation()*count)+com)/(count+1));

        TTradesRecordEntity tTradesRecordEntity = session.load(TTradesRecordEntity.class, recordId);
        tTradesRecordEntity.setComment(1);
        try {
            session.update(tTradesRecordEntity);
            session.update(tUsersEntity);
            transaction.commit();

            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public Map hasComment(int user_id, int trade_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTradesRecordEntity.class);
        Criterion c1 = Restrictions.eq("purchaser", user_id);
        Criterion c2 = Restrictions.eq("tradeId", trade_id);
        Criterion c3 = Restrictions.eq("comment", 0);
        criteria.add(c1);
        criteria.add(c2);
        criteria.add(c3);
        Map map = new HashMap();
        List<TTradesRecordEntity> list = criteria.list();
        if (list.size() == 0) {
            map.put("isExist", "false");
        } else {
            map.put("isExist", "true");
            map.put("object", list);
        }
        return map;
    }

    public List queryTradeById(int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTradesPublishEntity.class);
        Criterion criterion = Restrictions.eq("initiator", user_id);
        criteria.add(criterion);
        Order order = Order.desc("startTime");

        return criteria.addOrder(order).list();
    }




}
