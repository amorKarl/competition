package competition.po;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/29/029.
 */
@Entity
@Table(name = "v_trade_detail", schema = "competition", catalog = "")
public class VTradeDetailEntity {
    private int userId;
    private String username;
    private Integer repuation;
    private String name;
    private String icon;
    private int tradeId;
    private String startTime;
    private String message;
    private String screen;
    private BigDecimal tradeMoney;

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "repuation")
    public Integer getRepuation() {
        return repuation;
    }

    public void setRepuation(Integer repuation) {
        this.repuation = repuation;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Id
    @Column(name = "trade_id")
    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
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
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "screen")
    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @Basic
    @Column(name = "trade_money")
    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VTradeDetailEntity that = (VTradeDetailEntity) o;

        if (userId != that.userId) return false;
        if (tradeId != that.tradeId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (repuation != null ? !repuation.equals(that.repuation) : that.repuation != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;
        if (tradeMoney != null ? !tradeMoney.equals(that.tradeMoney) : that.tradeMoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (repuation != null ? repuation.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + tradeId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (tradeMoney != null ? tradeMoney.hashCode() : 0);
        return result;
    }
}
