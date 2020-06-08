package metier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Surcategorie")
public class SurCategorieEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSurCategorie", nullable = false, insertable = false, updatable = false)
    private int idSurCategorie;

    @Column(name = "nomSurCategorie")
    private String nomSurCategorie;

    public SurCategorieEntity() {
    }

    //region getter/etc

    public int getIdSurCategorie() {
        return idSurCategorie;
    }

    public void setIdSurCategorie(int idSurCategorie) {
        this.idSurCategorie = idSurCategorie;
    }

    public String getNomSurCategorie() {
        return nomSurCategorie;
    }

    public void setNomSurCategorie(String nomSurCategorie) {
        this.nomSurCategorie = nomSurCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurCategorieEntity that = (SurCategorieEntity) o;
        return idSurCategorie == that.idSurCategorie &&
                Objects.equals(nomSurCategorie, that.nomSurCategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSurCategorie, nomSurCategorie);
    }

    @Override
    public String toString() {
        return idSurCategorie + " - " + nomSurCategorie;
    }
    //endregion
}
