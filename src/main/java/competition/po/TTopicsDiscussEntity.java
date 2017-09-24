package competition.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/8/9/009.
 */
@Entity
@Table(name = "t_topics_discuss", schema = "competition", catalog = "")
public class TTopicsDiscussEntity {
    private int id;
    private Integer topic;
    private String content;
    private Integer userId;
    private String talkTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "topic")
    public Integer getTopic() {
        return topic;
    }

    public void setTopic(Integer topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "talk_time")
    public String getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTopicsDiscussEntity that = (TTopicsDiscussEntity) o;

        if (id != that.id) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (talkTime != null ? !talkTime.equals(that.talkTime) : that.talkTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (talkTime != null ? talkTime.hashCode() : 0);
        return result;
    }
}
