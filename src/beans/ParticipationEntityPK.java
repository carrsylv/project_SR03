package beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Sylvain on 24/04/2017.
 */
public class ParticipationEntityPK implements Serializable {
    private int etu;
    private int cren;

    @Column(name = "Etu")
    @Id
    public int getEtu() {
        return etu;
    }

    public void setEtu(int etu) {
        this.etu = etu;
    }

    @Column(name = "Cren")
    @Id
    public int getCren() {
        return cren;
    }

    public void setCren(int cren) {
        this.cren = cren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipationEntityPK that = (ParticipationEntityPK) o;

        if (etu != that.etu) return false;
        if (cren != that.cren) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = etu;
        result = 31 * result + cren;
        return result;
    }
}
