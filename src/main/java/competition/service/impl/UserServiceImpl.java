package competition.service.impl;

import competition.dao.UserDao;
import competition.po.TUsersEntity;
import competition.service.UserService;
import competition.vo.Upload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7/007.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 001.用户注册
     *
     * @param username
     * @param password
     * @param name
     * @return
     */
    public Map enroll(String username, String password, String name) {
        Map map = new HashMap();
        if ((userDao.isExist(username).get("isExist")).toString().equals("true")) {
            //用户名已经存在,不能注册
            map.put("message", "用户名已经存在");
            map.put("status", "198");
            return map;
        }
        if (userDao.insertData(username, password, name).get("insert").toString().equals("true")) {
            //注册成功
            map.put("message", "注册成功");
            map.put("status", "200");
        } else {
            //注册失败
            map.put("message", "注册失败");
            map.put("status", "199");
        }
        return map;
    }

    /**
     * 002.账户登录
     *
     * @param username
     * @param password
     * @return
     */
    public Map login(String username, String password) {
        Map map = new HashMap();
        Map map_data = userDao.isExist(username);
        if (map_data.get("isExist").toString().equals("false")) {
            //用户名不存在,不能登录
            map.put("message", "用户名不存在");
            map.put("status", "201");
            return map;
        }
        List<TUsersEntity> list = (List) map_data.get("object");
        if (list.get(0).getPassword().equals(password)) {
            //登录成功
            map.put("user_id",list.get(0).getId());
            map.put("message", "登录成功");
            map.put("status", "200");

        } else {
            //登录失败
            map.put("message", "密码错误");
            map.put("status", "202");
        }
        return map;
    }

    /**
     * 003.设置新密码
     *
     * @param username
     * @param newPassword
     * @return
     */
    public Map modify(String username, String newPassword) {
        Map map = new HashMap();
        if (userDao.modify(username, newPassword).get("setting").toString().equals("true")) {
            //设置成功
            map.put("message", "密码重置成功");
            map.put("status", "200");
        } else {
            //设置失败
            map.put("message", "密码重置失败");
            map.put("status", "203");
        }
        return map;
    }

    public Map consumeTimes(String username) {
        Map map_user = userDao.isExist(username);
        Map map = new HashMap();
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list = (List<TUsersEntity>) map_user.get("object");
            int times = userDao.consumeTimes(list.get(0).getId());
            map.put("status", "200");
            map.put("times", times);
            return map;
        } else {
            map.put("status", "224");
            map.put("message", "用户名不存在");
            return map;
        }
    }

    public Map personMessage(String username) {
        Map map_user = userDao.isExist(username);
        Map map = new HashMap();
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list = (List<TUsersEntity>) map_user.get("object");
            map.put("result", userDao.personMessage(list.get(0).getId()));
            map.put("times", userDao.consumeTimes(list.get(0).getId()));
            map.put("status", "200");
            return map;
        } else {
            map.put("status", "225");
            map.put("message", "用户名不存在");
            return map;
        }
    }

    public Map queryHadPublishTrade(String username) {
        Map map_user = userDao.isExist(username);
        Map map = new HashMap();
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list = (List<TUsersEntity>) map_user.get("object");
            int user_id = list.get(0).getId();
            List list_trade = userDao.queryHadPublishTrade(user_id);
            map.put("result", list_trade);
            List user_message=userDao.personMessage(user_id);
            map.put("message",user_message);
            map.put("length", list_trade.size());
            map.put("status", "200");
            return map;
        } else {
            map.put("status", "230");
            map.put("message", "用户名不存在");
            return map;
        }
    }

    public Map uploadIcon(MultipartFile img, HttpServletRequest request, String username) {
        Map map = new HashMap();
        if (Upload.isImage(img)) {
            Map map_user = userDao.isExist(username);
            if (map_user.get("isExist").toString().equals("true")) {
                List<TUsersEntity> list = (List<TUsersEntity>) map_user.get("object");
                if (userDao.uploadIcon(Upload.upload(img, request), list.get(0).getId())) {
                    map.put("status", "200");
                    map.put("message", "更新成功");
                    return map;
                } else {
                    map.put("status", "235");
                    map.put("message", "更新失败");
                    return map;
                }
            } else {
                map.put("status", "234");
                map.put("message", "用户不存在");
                return map;
            }
        } else {
            map.put("status", "233");
            map.put("message", "传入的非图片");
            return map;
        }
    }
}
