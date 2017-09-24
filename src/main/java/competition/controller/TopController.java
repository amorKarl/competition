package competition.controller;

import competition.service.TopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9/009.
 */
@Controller
@RequestMapping("top")
public class TopController {
    @Resource(name = "topService")
    private TopService topService;

    /**
     * 001.插入话题类型
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "insertTopicType", method = RequestMethod.POST)
    @ResponseBody
    public Map insertTopicType(@RequestParam String type) {
        return topService.insertTopicType(type);
    }

    /**
     * 002.列举所有话题分类
     *
     * @return
     */
    @RequestMapping(value = "listType", method = RequestMethod.POST)
    @ResponseBody
    public Map listType() {
        Map map = new HashMap();
        List list = topService.listType();
        map.put("result", list);
        map.put("length", list.size());
        return map;
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
    @RequestMapping(value = "publishTop", method = RequestMethod.POST)
    @ResponseBody
    public Map publishTop(@RequestParam String title, @RequestParam String type, @RequestParam String content, @RequestParam String username) {
//        Map map=new HashMap();
//        map.put("message",type);
        return topService.publishTop(title, type, content, username);
//        return map;
    }

    /**
     * 004.列举所有已发布话题
     *
     * @return
     */
    @RequestMapping(value = "listTopic", method = RequestMethod.POST)
    @ResponseBody
    public Map listTopic(@RequestParam int page, @RequestParam int num) {
        Map map = new HashMap();
        List list = topService.listTopic(page, num);
        map.put("result", list);
        map.put("length", list.size());
        return map;
    }

    /**
     * 005.评论话题
     *
     * @param topicId
     * @param content
     * @param username
     * @return
     */
    @RequestMapping(value = "topicComment", method = RequestMethod.POST)
    @ResponseBody
    public Map topicComment(@RequestParam int topicId, @RequestParam String content, @RequestParam String username) {
        return topService.topicComment(topicId, content, username);
    }

    /**
     * 006.查询已发布话题
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "queryHadPublishTop", method = RequestMethod.POST)
    @ResponseBody
    public Map queryHadPublishTop(@RequestParam String username) {
        return topService.queryHadPublishTop(username);
    }

    /**
     * 007.查询发布话题留言次数
     *
     * @param top_id
     * @return
     */
    @RequestMapping(value = "queryHadMessageTimes", method = RequestMethod.POST)
    @ResponseBody
    public Map queryHadMessageTimes(@RequestParam int top_id) {
        return topService.queryHadMessageTimes(top_id);
    }

    /**
     * 008.根据id查询评论
     *
     * @param top_id
     * @param page
     * @param num
     * @return
     */
    @RequestMapping(value = "queryCommentById", method = RequestMethod.POST)
    @ResponseBody
    public Map queryCommentById(@RequestParam int top_id, @RequestParam int page, @RequestParam int num) {
        return topService.queryCommentById(top_id, page, num);
    }

    /**
     * 009.根据id点赞
     * @param top_id
     * @param user_id
     * @return
     */
    @RequestMapping(value = "topGood", method = RequestMethod.POST)
    @ResponseBody
    public Map topGood(@RequestParam int top_id,@RequestParam int user_id) {
        return topService.topGood(top_id,user_id);
    }


}
