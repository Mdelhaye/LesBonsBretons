package metier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Caracteristique")
public class CaracteristiqueEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idCaracteristique",nullable = false,insertable = false,updatable = false)
    private int idCaracteristique;

    @Basic
    @Column(name = "nomCaracteristique")
    private String nomCaracteristique;

    @Basic
    @Column(name = "typeCaracteristique")
    private String typeCaracteristique;

    public CaracteristiqueEntity() {
    }

    //region getter / etc

    public int getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(int idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public String getNomCaracteristique() {
        return nomCaracteristique;
    }

    public void setNomCaracteristique(String nomCaracteristique) {
        this.nomCaracteristique = nomCaracteristique;
    }

    public String getTypeCaracteristique() {
        return typeCaracteristique;
    }

    public void setTypeCaracteristique(String typeCaracteristique) {
        this.typeCaracteristique = typeCaracteristique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristiqueEntity that = (CaracteristiqueEntity) o;
        return idCaracteristique == that.idCaracteristique &&
                Objects.equals(nomCaracteristique, that.nomCaracteristique) &&
                Objects.equals(typeCaracteristique, that.typeCaracteristique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaracteristique, nomCaracteristique, typeCaracteristique);
    }

    @Override
    public String toString() {
        return "CaracteristiqueEntity{" +
                "idCaracteristique=" + idCaracteristique +
                ", nomCaracteristique='" + nomCaracteristique + '\'' +
                ", typeCaracteristique='" + typeCaracteristique + '\'' +
                '}';
    }

    //endregion
}
