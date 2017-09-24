package competition.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/8/30/030.
 */
@Entity
@Table(name = "v_topic_publish", schema = "competition", catalog = "")
public class VTopicPublishEntity {
    private int id;
    private String title;
    private String content;
    private String startTime;
    private String type;
    private String publishAccount;
    private String publishName;
    private Integer comment;
    private String icon;
    private Integer good;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "publish_account")
    public String getPublishAccount() {
        return publishAccount;
    }

    public void setPublishAccount(String publishAccount) {
        this.publishAccount = publishAccount;
    }

    @Basic
    @Column(name = "publish_name")
    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    @Basic
    @Column(name = "comment")
    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "good")
    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VTopicPublishEntity that = (VTopicPublishEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (publishAccount != null ? !publishAccount.equals(that.publishAccount) : that.publishAccount != null)
            return false;
        if (publishName != null ? !publishName.equals(that.publishName) : that.publishName != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (good != null ? !good.equals(that.good) : that.good != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (publishAccount != null ? publishAccount.hashCode() : 0);
        result = 31 * result + (publishName != null ? publishName.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (good != null ? good.hashCode() : 0);
        return result;
    }
}
