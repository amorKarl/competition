package competition.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9/009.
 */
public interface TopDao {
    public Map isExistType(String type);
    public Map insertType(String type);
    public List listType();
    public Map publishTop(String title,int type,String content,int username);
    public List listTopic(int page,int num);
    public Map existTopicId(int topicId);
    public Map insertComment(int topicId,String content,int user_id);
    //2017.08.17
    public List queryHadPublishTop(int user_id);
    public int queryHadMessageTimes(int top_id);
    //2017.08.23
    public boolean addComment(int top_id);
    public List queryCommentById(int top_id,int page,int num);
    //2017.08.30
    public boolean topGood(int top_id,int user_id);
    //2017.09.01
    public boolean isAlreadyGood(int top_id,int user_id);
}
