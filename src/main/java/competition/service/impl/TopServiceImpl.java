package competition.service.impl;

import competition.dao.TopDao;
import competition.dao.UserDao;
import competition.po.TTopicsEntity;
import competition.po.TUsersEntity;
import competition.service.TopService;
import competition.vo.Upload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9/009.
 */
@Service("topService")
public class TopServiceImpl implements TopService {
    @Resource(name = "topDao")
    private TopDao topDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 001.插入话题类型
     *
     * @param type
     * @return
     */
    public Map insertTopicType(String type) {
        Map map = new HashMap();
        if (topDao.isExistType(type).get("isExist").toString().equals("false")) {
            //可以插入
            if (topDao.insertType(type).get("insert").toString().equals("true")) {
                map.put("status", "200");
                map.put("message", "分类创建成功");
                return map;
            } else {
                map.put("status", "205");
                map.put("message", "数据插入失败");
                return map;
            }
        } else {
            //不可以插入
            map.put("status", "204");
            map.put("message", "分类已存在，插入失败");
            return map;
        }
    }

    /**
     * 002.列举所有话题分类
     *
     * @return
     */
    public List listType() {
        return topDao.listType();
    }

    /**
     * 003.发布话题
     *
     * @param title
     * @param type
     * @param content
     * @param username
     * @return
     */
    public Map publishTop(String title, String type, String content, String username) {
        Map map = new HashMap();
        Map map_type = topDao.isExistType(type);
        Map map_user = userDao.isExist(username);
        if (map_type.get("isExist").toString().equals("true")) {
            //存在话题
            List<TTopicsEntity> list_type = (List) map_type.get("object");
            if (map_user.get("isExist").toString().equals("true")) {
                //用户存在
                List<TUsersEntity> list_user = (List) map_user.get("object");
                if (topDao.publishTop(title, list_type.get(0).getId(), content, list_user.get(0).getId()).get("insert").toString().equals("true")) {
                    map.put("status", "200");
                    map.put("message", "话题发布成功");
                    return map;
                } else {
                    //插入失败
                    map.put("status", "208");
                    map.put("message", "发布失败");
                    return map;
                }
            } else {
                //用户不存在
                map.put("status", "207");
                map.put("message", "用户不存在，发布失败");
                return map;
            }
        } else {
            //不存在话题，插入失败
            map.put("status", "206");
            map.put("message", "话题不存在，发布失败");
            return map;
        }
    }

    /**
     * 004.列举所有已发布话题
     *
     * @return
     */
    public List listTopic(int page, int num) {
        return topDao.listTopic(page, num);
    }

    /**
     * 005.评论话题
     *
     * @param topicId
     * @param comment
     * @param username
     * @return
     */
    public Map topicComment(int topicId, String comment, String username) {
        Map map = new HashMap();
        if (topDao.existTopicId(topicId).get("isExist").toString().equals("true")) {
            //存在话题id
            Map map_user = userDao.isExist(username);
            if (map_user.get("isExist").equals("true")) {
                //用户存在
                List<TUsersEntity> list = (List) map_user.get("object");
                if (topDao.insertComment(topicId, comment, list.get(0).getId()).get("insert").equals("true")) {

                    topDao.addComment(topicId);

                    map.put("status", "200");
                    map.put("message", "评论成功");
                    return map;
                } else {
                    map.put("status", "211");
                    map.put("message", "评论失败");
                    return map;
                }
            } else {
                //用户不存在
                map.put("status", "210");
                map.put("message", "用户不存在");
                return map;
            }
        } else {
            //不存在话题id
            map.put("status", "209");
            map.put("message", "话题不存在");
            return map;

        }
    }

    public Map queryHadPublishTop(String username) {
        Map map_user = userDao.isExist(username);
        Map map = new HashMap();
        if (map_user.get("isExist").toString().equals("true")) {
            List<TUsersEntity> list_user = (List<TUsersEntity>) map_user.get("object");
            int user_id = list_user.get(0).getId();
            List list = topDao.queryHadPublishTop(user_id);
            map.put("result", list);
            map.put("length", list.size());
            map.put("status", "200");
            return map;
        } else {
            map.put("status", "231");
            map.put("message", "用户名不存在");
            return map;
        }
    }

    public Map queryHadMessageTimes(int top_id) {
        Map map = new HashMap();
        if (topDao.existTopicId(top_id).get("isExist").toString().equals("true")) {
            map.put("times", topDao.queryHadMessageTimes(top_id));
            map.put("status", "200");
            map.put("message", "查询成功");
            return map;
        } else {
            map.put("status", "232");
            map.put("message", "话题不存在");
            return map;
        }
    }

    public Map queryCommentById(int top_id, int page, int num) {
        Map map = new HashMap();
        if (topDao.existTopicId(top_id).get("isExist").toString().equals("true")) {
            List list = topDao.queryCommentById(top_id, page, num);
            map.put("result", list);
            map.put("length", list.size());
            map.put("status", "200");
            return map;
        } else {
            map.put("status", "232");
            map.put("message", "话题不存在");
            return map;
        }
    }

    public Map topGood(int top_id, int user_id) {
        Map map = new HashMap();
        if (topDao.existTopicId(top_id).get("isExist").toString().equals("true")) {

            if (!topDao.isAlreadyGood(top_id, user_id)) {
                if (topDao.topGood(top_id, user_id)) {
                    map.put("status", "200");
                    map.put("message", "点赞成功");
                    return map;
                } else {
                    map.put("status", "266");
                    map.put("message", "点赞失败");
                    return map;
                }

            } else {
                map.put("status", "267");
                map.put("message", "已经点赞过啦");
                return map;
            }
        } else {
            map.put("status", "232");
            map.put("message", "话题不存在");
            return map;
        }


    }


}
