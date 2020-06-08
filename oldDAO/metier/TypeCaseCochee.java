package metier;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TypeCaseCochee")
public class TypeCaseCochee {
    @Id
    @Column(name = "idTypeCaseCochee", nullable = false, insertable = false, updatable = false)
    private int idTypeCaseCochee;

    @Basic
    @Column(name = "nomCaseCochee")
    private String nomCaseCochee;

    public TypeCaseCochee() {
    }

    //region getter/setter
    public void setNomCaseCochee(String nomCaseCochee) {
        this.nomCaseCochee = nomCaseCochee;
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
        TypeCaseCochee that = (TypeCaseCochee) o;
        return idTypeCaseCochee == that.idTypeCaseCochee &&
                Objects.equals(nomCaseCochee, that.nomCaseCochee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeCaseCochee, nomCaseCochee);
    }

    @Override
    public String toString() {
        return "TypeCaseCochee{" +
                "idTypeCaseCochee=" + idTypeCaseCochee +
                ", nomCaseCochee='" + nomCaseCochee + '\'' +
                '}';
    }
    //endregion
}
