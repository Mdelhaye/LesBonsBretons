package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CaracteristiqueHasCaseCochee")
public class CaracteristiqueHasCC implements Serializable {

    @Id
    @Column(name = "idCaracteristique", nullable = false, insertable = false, updatable = false)
    private int idCaracteristique;

    @Id
    @Column(name = "idTypeCaseCochee", nullable = false, insertable = false, updatable = false)
    private int idTypeCaseCochee;

    //region getter/setter
    public CaracteristiqueHasCC() {
    }

    public int getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(int idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public int getIdTypeCaseCochee() {
        return idTypeCaseCochee;
    }

    public void setIdTypeCaseCochee(int idTypeCaseCochee) {
        this.idTypeCaseCochee = idTypeCaseCochee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristiqueHasCC that = (CaracteristiqueHasCC) o;
        return idCaracteristique == that.idCaracteristique &&
                idTypeCaseCochee == that.idTypeCaseCochee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaracteristique, idTypeCaseCochee);
    }

    @Override
    public String toString() {
        return "CaracteristiqueHasCC{" +
                "idCaracteristique=" + idCaracteristique +
                ", idTypeCaseCochee=" + idTypeCaseCochee +
                '}';
    }
    //endregion


}
