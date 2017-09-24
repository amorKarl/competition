package competition.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9/009.
 */
public interface TopService {
    public Map insertTopicType(String type);
    public List listType();
    public Map publishTop(String title,String type,String content,String username);
    public List listTopic(int page,int num);
    public Map topicComment(int topicId,String comment,String username);
    public Map queryHadPublishTop(String username);
    public Map queryHadMessageTimes(int top_id);
    public Map queryCommentById(int top_id,int page,int num);
    public Map topGood(int top_id,int user_id);
}
