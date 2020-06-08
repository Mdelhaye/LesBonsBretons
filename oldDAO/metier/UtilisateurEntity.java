package metier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Utilisateur")
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idUtilisateur",nullable = false,insertable = false,updatable = false)
    private int idUtilisateur;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "rue")
    private String rue;

    @Column(name = "numrue")
    private int numRue;

    @OneToOne
    @JoinColumn(name = "idLocalisationUtilisateur", referencedColumnName = "ville_id")
    private LocalisationEntity idLocalisationUtilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    private AnnonceEntity AnnonceUser;

    private String ville;

    public UtilisateurEntity() {
    }

    //region getter/etc
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumRue() {
        return numRue;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public LocalisationEntity getIdLocalisationUtilisateur() {
        return idLocalisationUtilisateur;
    }

    public void setIdLocalisationUtilisateur(LocalisationEntity idLocalisationUtilisateur) {
        this.idLocalisationUtilisateur = idLocalisationUtilisateur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurEntity that = (UtilisateurEntity) o;
        return idUtilisateur == that.idUtilisateur &&
                numRue == that.numRue &&
                idLocalisationUtilisateur == that.idLocalisationUtilisateur &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(numTel, that.numTel) &&
                Objects.equals(rue, that.rue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, login, password, numTel, rue, numRue, idLocalisationUtilisateur);
    }

    @Override
    public String toString() {
        return "UtilisateurEntity{" +
                "idUtilisateur=" + idUtilisateur +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", numTel='" + numTel + '\'' +
                ", rue='" + rue + '\'' +
                ", numRue=" + numRue +
                ", idLocalisationUtilisateur=" + idLocalisationUtilisateur +
                '}';
    }
    //endregion
}
