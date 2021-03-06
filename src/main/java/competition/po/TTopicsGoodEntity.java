package competition.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/1/001.
 */
@Entity
@Table(name = "t_topics_good", schema = "competition", catalog = "")
public class TTopicsGoodEntity {
    private int id;
    private Integer topicId;
    private Integer userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "topic_id")
    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTopicsGoodEntity that = (TTopicsGoodEntity) o;

        if (id != that.id) return false;
        if (topicId != null ? !topicId.equals(that.topicId) : that.topicId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
