package metier;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Ville")
public class LocalisationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ville_id", nullable = false, insertable = false, updatable = false)
    private int idVille;

    @Column(name = "ville_nom")
    private String nomVille;

    @Column(name = "ville_code_postal")
    private String code_postal;

    public LocalisationEntity() {
    }

    //region setter/getter
    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalisationEntity that = (LocalisationEntity) o;
        return idVille == that.idVille &&
                code_postal == that.code_postal &&
                Objects.equals(nomVille, that.nomVille);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVille, nomVille, code_postal);
    }

    @Override
    public String toString() {
        return code_postal + " - " + nomVille;
    }
    //endregion
}
