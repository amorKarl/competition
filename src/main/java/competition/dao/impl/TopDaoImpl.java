package competition.dao.impl;

import competition.BaseDao;
import competition.dao.TopDao;
import competition.po.*;
import competition.vo.AreaName;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9/009.
 */
@Repository("topDao")
public class TopDaoImpl extends BaseDao implements TopDao {

    /**
     * 001.判断类型是不是存在
     *
     * @param type
     * @return
     */
    public Map isExistType(String type) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTopicsEntity.class);
        Criterion criterion = Restrictions.eq("type", type);
        criteria.add(criterion);
        List<TTopicsEntity> list = criteria.list();
        Map map = new HashMap();
        if (list.size() == 0) {
            //不存在，可以插入
            map.put("isExist", "false");
        } else {
            //存在，不可以插入
            map.put("isExist", "true");
            map.put("object", list);
        }
        return map;
    }

    /**
     * 002.发布新类型
     *
     * @param type
     * @return
     */
    public Map insertType(String type) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TTopicsEntity tTopicsEntity = new TTopicsEntity();
        tTopicsEntity.setType(type);
        Map map = new HashMap();
        try {
            session.save(tTopicsEntity);
            transaction.commit();
            map.put("insert", "true");
            return map;
        } catch (Exception e) {
            transaction.rollback();
        }
        map.put("insert", "false");
        return map;
    }

    /**
     * 003.列举所有话题分类
     *
     * @return
     */
    public List listType() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTopicsEntity.class);
        List<TTopicsEntity> list = criteria.list();
        return list;
    }

    /**
     * 004.发布话题
     *
     * @param title
     * @param type
     * @param content
     * @param username
     * @return
     */
    public Map publishTop(String title, int type, String content, int username) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TTopicsPublishEntity tTopicsPublishEntity = new TTopicsPublishEntity();
        tTopicsPublishEntity.setTitle(title);
        tTopicsPublishEntity.setType(type);
        tTopicsPublishEntity.setContent(content);
        tTopicsPublishEntity.setPublisherId(username);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tTopicsPublishEntity.setStartTime(ft.format(dNow).toString());
        tTopicsPublishEntity.setComment(0);
        tTopicsPublishEntity.setGood(0);
        Map map = new HashMap();
        try {
            session.save(tTopicsPublishEntity);
            transaction.commit();
            map.put("insert", "true");
            return map;

        } catch (Exception e) {
            transaction.rollback();
        }
        map.put("insert", "false");
        return map;
    }

    /**
     * 005.列举所有已发布话题
     *
     * @return
     */
    public List listTopic(int page, int num) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Order order = Order.desc("startTime");
        Criteria criteria = session.createCriteria(VTopicPublishEntity.class).setFirstResult((page - 1) * num).setMaxResults(num).addOrder(order);
        List<VTopicPublishEntity> list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIcon(AreaName.name + list.get(i).getIcon());
        }
        return list;
    }

    /**
     * 006.判断发布话题id是否存在
     *
     * @param topicId
     * @return
     */
    public Map existTopicId(int topicId) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTopicsPublishEntity.class);
        Criterion criterion = Restrictions.eq("id", topicId);
        criteria.add(criterion);
        List<TTopicsPublishEntity> list = criteria.list();
        Map map = new HashMap();
        if (list.size() == 0) {
            //不存在
            map.put("isExist", "false");
        } else {
            //存在
            map.put("isExist", "true");
        }
        return map;
    }

    /**
     * 007.插入评论
     *
     * @param topicId
     * @param content
     * @param user_id
     * @return
     */
    public Map insertComment(int topicId, String content, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TTopicsDiscussEntity tTopicsDiscussEntity = new TTopicsDiscussEntity();
        tTopicsDiscussEntity.setContent(content);
        tTopicsDiscussEntity.setUserId(user_id);
        tTopicsDiscussEntity.setTopic(topicId);
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tTopicsDiscussEntity.setTalkTime(ft.format(dNow).toString());
        Map map = new HashMap();
        try {
            session.save(tTopicsDiscussEntity);
            transaction.commit();
            map.put("insert", "true");
            return map;
        } catch (Exception e) {
            transaction.rollback();
        }
        map.put("insert", "false");
        return map;
    }

    public List queryHadPublishTop(int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Order order = Order.desc("startTime");
        Criteria criteria = session.createCriteria(TTopicsPublishEntity.class).addOrder(order);
        Criterion criterion = Restrictions.eq("publisherId", user_id);
        criteria.add(criterion);
        return criteria.list();
    }

    public int queryHadMessageTimes(int top_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTopicsDiscussEntity.class);
        Criterion criterion = Restrictions.eq("topic", top_id);
        criteria.add(criterion);

        return criteria.list().size();
    }

    public boolean addComment(int top_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TTopicsPublishEntity tTopicsPublishEntity = session.load(TTopicsPublishEntity.class, top_id);
        tTopicsPublishEntity.setComment(tTopicsPublishEntity.getComment() + 1);
        try {
            session.update(tTopicsPublishEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public List queryCommentById(int top_id, int page, int num) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Order order = Order.desc("talkTime");
        Criteria criteria = session.createCriteria(VTopicDiscussEntity.class).setFirstResult((page - 1) * num).setMaxResults(num);
        Criterion criterion = Restrictions.eq("topic", top_id);
        criteria.add(criterion);


        List<VTopicDiscussEntity>list=criteria.addOrder(order).list();
        for(int i=0;i<list.size();i++){
            list.get(i).setIcon(AreaName.name+list.get(i).getIcon());
        }
        return list;
    }

    public boolean topGood(int top_id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //更新点赞数
        TTopicsPublishEntity tTopicsPublishEntity = session.load(TTopicsPublishEntity.class, top_id);
        tTopicsPublishEntity.setGood(tTopicsPublishEntity.getGood() + 1);
        //插入点赞集合
        TTopicsGoodEntity tTopicsGoodEntity = new TTopicsGoodEntity();
        tTopicsGoodEntity.setUserId(user_id);
        tTopicsGoodEntity.setTopicId(top_id);

        try {
            session.save(tTopicsGoodEntity);
            session.update(tTopicsPublishEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public boolean isAlreadyGood(int top_id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(TTopicsGoodEntity.class);
        Criterion c1 = Restrictions.eq("topicId", top_id);
        Criterion c2 = Restrictions.eq("userId", user_id);
        criteria.add(c1);
        criteria.add(c2);

        List<TTopicsGoodEntity> list = criteria.list();
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }

    }
}
