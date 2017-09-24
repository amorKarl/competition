package competition;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Administrator on 2017/8/7/007.
 */
public class BaseDao {
    public static SessionFactory sessionFactory;
    public static Session getCurrentSession(){
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        Session session=sessionFactory.getCurrentSession();
        return session;
    }
}
