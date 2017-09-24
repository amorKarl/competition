package competition.po;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/8/11/011.
 */
@Entity
@Table(name = "t_trades_screen", schema = "competition", catalog = "")
public class TTradesScreenEntity {
    private int id;
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

        TTradesScreenEntity that = (TTradesScreenEntity) o;

        if (id != that.id) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        return result;
    }
}
