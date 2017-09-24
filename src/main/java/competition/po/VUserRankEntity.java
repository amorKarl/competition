package competition.po;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Amor on 2017/09/24.
 */
@Entity
@Table(name = "v_user_rank", schema = "competition", catalog = "")
public class VUserRankEntity {
    private int id;
    private String username;
    private String name;
    private String icon;
    private Double repuation;
    private BigDecimal tradeMoney;
    private String message;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Basic
    @Column(name = "repuation")
    public Double getRepuation() {
        return repuation;
    }

    public void setRepuation(Double repuation) {
        this.repuation = repuation;
    }

    @Basic
    @Column(name = "trade_money")
    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VUserRankEntity that = (VUserRankEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (repuation != null ? !repuation.equals(that.repuation) : that.repuation != null) return false;
        if (tradeMoney != null ? !tradeMoney.equals(that.tradeMoney) : that.tradeMoney != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (repuation != null ? repuation.hashCode() : 0);
        result = 31 * result + (tradeMoney != null ? tradeMoney.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
