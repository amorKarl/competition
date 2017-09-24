package competition.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7/007.
 */
public interface UserDao {
    public Map isExist(String username);
    public Map insertData(String username,String password,String name);
    public Map modify(String username,String newPassword);
    //2017.08.15
    public int consumeTimes(int user_id);
    public List personMessage(int user_id);
    //2017.08.17
    public List queryHadPublishTrade(int user_id);
    //2017.08.19
    public boolean uploadIcon(String img,int user_id);
}
