package competition.dao.impl;

import competition.BaseDao;
import competition.dao.UserDao;
import competition.po.TTradesPublishEntity;
import competition.po.TUsersEntity;
import competition.po.TUsersRecordEntity;
import competition.vo.AreaName;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7/007.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{
    /**
     * 001.判断用户是否存在
     * @param username
     * @return
     */
    public Map isExist(String username) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(TUsersEntity.class);
        Criterion criterion= Restrictions.eq("username",username);
        criteria.add(criterion);
        List<TUsersEntity>list=criteria.list();
        Map map=new HashMap();
        if(list.size()==0){
            map.put("isExist","false");
            return map;
        }else{
            map.put("isExist","true");
            map.put("object",list);
            return map;
        }
    }

    /**
     * 002.注册数据
     * @param username
     * @param password
     * @param name
     * @return
     */
    public Map insertData(String username, String password, String name) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        TUsersEntity tUsersEntity=new TUsersEntity();
        tUsersEntity.setUsername(username);
        tUsersEntity.setPassword(password);
        tUsersEntity.setName(name);
        tUsersEntity.setRepuation(0.0);
        tUsersEntity.setMoney(new BigDecimal(0));
        tUsersEntity.setIcon("upload//demo//user.jpg");
        Map map=new HashMap();
        try {
            session.save(tUsersEntity);
            transaction.commit();
            map.put("insert","true");
            return map;
        }catch (Exception e){
            transaction.rollback();
        }
        map.put("insert","false");
        return map;
    }

    /**
     * 003.修改密码
     * @param username
     * @param newPassword
     * @return
     */
    public Map modify(String username, String newPassword) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(TUsersEntity.class);
        Criterion criterion=Restrictions.eq("username",username);
        criteria.add(criterion);
        List<TUsersEntity>list=criteria.list();
        TUsersEntity tUsersEntity=session.load(TUsersEntity.class,list.get(0).getId());
        tUsersEntity.setPassword(newPassword);
        Map map=new HashMap();
        try {
            session.update(tUsersEntity);
            transaction.commit();
            map.put("setting","true");
            return map;
        }catch (Exception e){
            transaction.rollback();
            map.put("setting","false");
        }
        return map;
    }

    public int consumeTimes(int user_id) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(TUsersRecordEntity.class);
        Criterion criterion=Restrictions.eq("userId",user_id);
        criteria.add(criterion);
        int times=criteria.list().size();
        return times;
    }

    public List personMessage(int user_id) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(TUsersEntity.class);
        Criterion criterion=Restrictions.eq("id",user_id);
        criteria.add(criterion);
        List<TUsersEntity>list=criteria.list();
        list.get(0).setIcon(AreaName.name+list.get(0).getIcon());
        return list;
    }

    public List queryHadPublishTrade(int user_id) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        Criteria criteria=session.createCriteria(TTradesPublishEntity.class);
        Criterion criterion=Restrictions.eq("initiator",user_id);
        criteria.add(criterion);
        return criteria.list();
    }

    public boolean uploadIcon(String img, int user_id) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        TUsersEntity tUsersEntity=session.load(TUsersEntity.class,user_id);
        tUsersEntity.setIcon(img);
        try{
            session.update(tUsersEntity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }
        return false;
    }
}
