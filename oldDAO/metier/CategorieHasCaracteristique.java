package metier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CategorieHasCaracteristique")
public class CategorieHasCaracteristique implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "idCaracteristique", referencedColumnName = "idCaracteristique")
    private CaracteristiqueEntity idCaracteristique;

    @Id
    @OneToOne
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    private CategorieEntity idCategorie;

    public CategorieHasCaracteristique() {
    }

    //region getter/setter
    public CaracteristiqueEntity getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(CaracteristiqueEntity idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public CategorieEntity getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(CategorieEntity idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieHasCaracteristique that = (CategorieHasCaracteristique) o;
        return idCaracteristique == that.idCaracteristique &&
                idCategorie == that.idCategorie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaracteristique, idCategorie);
    }

    @Override
    public String toString() {
        return "CategorieHasCaracteristique{" +
                "idCaracteristique=" + idCaracteristique +
                ", idCategorie=" + idCategorie +
                '}';
    }
    //endregion
}
