package competition.po;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/25/025.
 */
@Entity
@Table(name = "t_trades_publish", schema = "competition", catalog = "")
public class TTradesPublishEntity {
    private int id;
    private BigDecimal tradeMoney;
    private int initiator;
    private String startTime;
    private String zhukehe;
    private String banchangzhukehe;
    private String rangqiuzhukehe;
    private String rangqiu;
    private String rangqiudaxi;
    private String banchangrangqiudaxi;
    private String jiaoqiudaxi;
    private String bodan;
    private String banchangbodan;
    private String diyiqiuruqiu;
    private String zongruqiu;
    private String ruqiudanshuang;
    private String banquanchang;
    private String message;
    private String screen;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "initiator")
    public int getInitiator() {
        return initiator;
    }

    public void setInitiator(int initiator) {
        this.initiator = initiator;
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
    @Column(name = "zhukehe")
    public String getZhukehe() {
        return zhukehe;
    }

    public void setZhukehe(String zhukehe) {
        this.zhukehe = zhukehe;
    }

    @Basic
    @Column(name = "banchangzhukehe")
    public String getBanchangzhukehe() {
        return banchangzhukehe;
    }

    public void setBanchangzhukehe(String banchangzhukehe) {
        this.banchangzhukehe = banchangzhukehe;
    }

    @Basic
    @Column(name = "rangqiuzhukehe")
    public String getRangqiuzhukehe() {
        return rangqiuzhukehe;
    }

    public void setRangqiuzhukehe(String rangqiuzhukehe) {
        this.rangqiuzhukehe = rangqiuzhukehe;
    }

    @Basic
    @Column(name = "rangqiu")
    public String getRangqiu() {
        return rangqiu;
    }

    public void setRangqiu(String rangqiu) {
        this.rangqiu = rangqiu;
    }

    @Basic
    @Column(name = "rangqiudaxi")
    public String getRangqiudaxi() {
        return rangqiudaxi;
    }

    public void setRangqiudaxi(String rangqiudaxi) {
        this.rangqiudaxi = rangqiudaxi;
    }

    @Basic
    @Column(name = "banchangrangqiudaxi")
    public String getBanchangrangqiudaxi() {
        return banchangrangqiudaxi;
    }

    public void setBanchangrangqiudaxi(String banchangrangqiudaxi) {
        this.banchangrangqiudaxi = banchangrangqiudaxi;
    }

    @Basic
    @Column(name = "jiaoqiudaxi")
    public String getJiaoqiudaxi() {
        return jiaoqiudaxi;
    }

    public void setJiaoqiudaxi(String jiaoqiudaxi) {
        this.jiaoqiudaxi = jiaoqiudaxi;
    }

    @Basic
    @Column(name = "bodan")
    public String getBodan() {
        return bodan;
    }

    public void setBodan(String bodan) {
        this.bodan = bodan;
    }

    @Basic
    @Column(name = "banchangbodan")
    public String getBanchangbodan() {
        return banchangbodan;
    }

    public void setBanchangbodan(String banchangbodan) {
        this.banchangbodan = banchangbodan;
    }

    @Basic
    @Column(name = "diyiqiuruqiu")
    public String getDiyiqiuruqiu() {
        return diyiqiuruqiu;
    }

    public void setDiyiqiuruqiu(String diyiqiuruqiu) {
        this.diyiqiuruqiu = diyiqiuruqiu;
    }

    @Basic
    @Column(name = "zongruqiu")
    public String getZongruqiu() {
        return zongruqiu;
    }

    public void setZongruqiu(String zongruqiu) {
        this.zongruqiu = zongruqiu;
    }

    @Basic
    @Column(name = "ruqiudanshuang")
    public String getRuqiudanshuang() {
        return ruqiudanshuang;
    }

    public void setRuqiudanshuang(String ruqiudanshuang) {
        this.ruqiudanshuang = ruqiudanshuang;
    }

    @Basic
    @Column(name = "banquanchang")
    public String getBanquanchang() {
        return banquanchang;
    }

    public void setBanquanchang(String banquanchang) {
        this.banquanchang = banquanchang;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTradesPublishEntity that = (TTradesPublishEntity) o;

        if (id != that.id) return false;
        if (initiator != that.initiator) return false;
        if (tradeMoney != null ? !tradeMoney.equals(that.tradeMoney) : that.tradeMoney != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (zhukehe != null ? !zhukehe.equals(that.zhukehe) : that.zhukehe != null) return false;
        if (banchangzhukehe != null ? !banchangzhukehe.equals(that.banchangzhukehe) : that.banchangzhukehe != null)
            return false;
        if (rangqiuzhukehe != null ? !rangqiuzhukehe.equals(that.rangqiuzhukehe) : that.rangqiuzhukehe != null)
            return false;
        if (rangqiu != null ? !rangqiu.equals(that.rangqiu) : that.rangqiu != null) return false;
        if (rangqiudaxi != null ? !rangqiudaxi.equals(that.rangqiudaxi) : that.rangqiudaxi != null) return false;
        if (banchangrangqiudaxi != null ? !banchangrangqiudaxi.equals(that.banchangrangqiudaxi) : that.banchangrangqiudaxi != null)
            return false;
        if (jiaoqiudaxi != null ? !jiaoqiudaxi.equals(that.jiaoqiudaxi) : that.jiaoqiudaxi != null) return false;
        if (bodan != null ? !bodan.equals(that.bodan) : that.bodan != null) return false;
        if (banchangbodan != null ? !banchangbodan.equals(that.banchangbodan) : that.banchangbodan != null)
            return false;
        if (diyiqiuruqiu != null ? !diyiqiuruqiu.equals(that.diyiqiuruqiu) : that.diyiqiuruqiu != null) return false;
        if (zongruqiu != null ? !zongruqiu.equals(that.zongruqiu) : that.zongruqiu != null) return false;
        if (ruqiudanshuang != null ? !ruqiudanshuang.equals(that.ruqiudanshuang) : that.ruqiudanshuang != null)
            return false;
        if (banquanchang != null ? !banquanchang.equals(that.banquanchang) : that.banquanchang != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tradeMoney != null ? tradeMoney.hashCode() : 0);
        result = 31 * result + initiator;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (zhukehe != null ? zhukehe.hashCode() : 0);
        result = 31 * result + (banchangzhukehe != null ? banchangzhukehe.hashCode() : 0);
        result = 31 * result + (rangqiuzhukehe != null ? rangqiuzhukehe.hashCode() : 0);
        result = 31 * result + (rangqiu != null ? rangqiu.hashCode() : 0);
        result = 31 * result + (rangqiudaxi != null ? rangqiudaxi.hashCode() : 0);
        result = 31 * result + (banchangrangqiudaxi != null ? banchangrangqiudaxi.hashCode() : 0);
        result = 31 * result + (jiaoqiudaxi != null ? jiaoqiudaxi.hashCode() : 0);
        result = 31 * result + (bodan != null ? bodan.hashCode() : 0);
        result = 31 * result + (banchangbodan != null ? banchangbodan.hashCode() : 0);
        result = 31 * result + (diyiqiuruqiu != null ? diyiqiuruqiu.hashCode() : 0);
        result = 31 * result + (zongruqiu != null ? zongruqiu.hashCode() : 0);
        result = 31 * result + (ruqiudanshuang != null ? ruqiudanshuang.hashCode() : 0);
        result = 31 * result + (banquanchang != null ? banquanchang.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        return result;
    }
}
