package metier;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Annonce")
public class AnnonceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnnonce", nullable = false, insertable = false, updatable = false)
    private int idAnnonce;

    @Basic
    @Column(name = "titreAnnonce")
    private String titreAnnonce;

    @Basic
    @Column(name = "descriptionAnnonce")
    private String descriptionAnnonce;

    @Basic
    @Column(name = "isOffreAnnonce")
    private boolean isOffreAnnonce;

    //@OneToMany(mappedBy = "AnnonceCategorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne
    @JoinColumn(name = "idCategorieAnnonce", referencedColumnName = "idCategorie")
    private CategorieEntity categorie;


    @OneToOne
    @JoinColumn(name = "idUtilisateurAnnonce", referencedColumnName = "idUtilisateur")
    private UtilisateurEntity idUtilisateurAnnonce;


    @OneToOne
    @JoinColumn(name = "idAnnonceLocalisation", referencedColumnName = "ville_id")
    private LocalisationEntity idAnnonceLocalisation;

    @Basic
    @Column(name = "dateAnnonce")
    private Timestamp dateAnnonce;

    @Basic
    @Column(name = "prix")
    private int prix;

    @Basic
    @Column(name = "lienImage")
    private String lienImage;

    public AnnonceEntity() {

    }

    public AnnonceEntity(String titreAnnonce, String descriptionAnnonce, boolean isOffreAnnonce, CategorieEntity categorie, UtilisateurEntity idUtilisateurAnnonce, LocalisationEntity idAnnonceLocalisation, Timestamp dateAnnonce, int prix, String lienImage) {
        this.titreAnnonce = titreAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;
        this.isOffreAnnonce = isOffreAnnonce;
        this.categorie = categorie;
        this.idUtilisateurAnnonce = idUtilisateurAnnonce;
        this.idAnnonceLocalisation = idAnnonceLocalisation;
        this.dateAnnonce = dateAnnonce;
        this.prix = prix;
        this.lienImage = lienImage;
    }

    //region Getter / Setter / toString / Hashcode

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getTitreAnnonce() {
        return titreAnnonce;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public String getDescriptionAnnonce() {
        return descriptionAnnonce;
    }

    public void setDescriptionAnnonce(String descriptionAnnonce) {
        this.descriptionAnnonce = descriptionAnnonce;
    }

    public boolean isOffreAnnonce() {
        return isOffreAnnonce;
    }

    public void setOffreAnnonce(boolean offreAnnonce) {
        isOffreAnnonce = offreAnnonce;
    }

    public CategorieEntity getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieEntity categorie) {
        this.categorie = categorie;
    }

    public UtilisateurEntity getIdUtilisateurAnnonce() {
        return idUtilisateurAnnonce;
    }

    public void setIdUtilisateurAnnonce(UtilisateurEntity idUtilisateurAnnonce) {
        this.idUtilisateurAnnonce = idUtilisateurAnnonce;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getLienImage() {
        return lienImage;
    }

    public void setLienImage(String lienImage) {
        this.lienImage = lienImage;
    }

    /*  public List<UtilisateurEntity> getUser() {
        return user;
    }

    public void setUser(List<UtilisateurEntity> user) {
        this.user = user;
    }*/

    public LocalisationEntity getIdAnnonceLocalisation() {
        return idAnnonceLocalisation;
    }

    public void setIdAnnonceLocalisation(LocalisationEntity idAnnonceLocalisation) {
        this.idAnnonceLocalisation = idAnnonceLocalisation;
    }

    public Timestamp getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Timestamp dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnonceEntity that = (AnnonceEntity) o;
        return idAnnonce == that.idAnnonce &&
                isOffreAnnonce == that.isOffreAnnonce &&
                idAnnonceLocalisation == that.idAnnonceLocalisation &&
                Objects.equals(titreAnnonce, that.titreAnnonce) &&
                Objects.equals(descriptionAnnonce, that.descriptionAnnonce) &&
                Objects.equals(categorie, that.categorie) &&
                Objects.equals(dateAnnonce, that.dateAnnonce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnnonce, titreAnnonce, descriptionAnnonce, isOffreAnnonce, categorie, idAnnonceLocalisation, dateAnnonce);
    }

    @Override
    public String toString() {
        return "AnnonceEntity{" +
                "idAnnonce=" + idAnnonce +
                ", titreAnnonce='" + titreAnnonce + '\'' +
                ", descriptionAnnonce='" + descriptionAnnonce + '\'' +
                ", isOffreAnnonce=" + isOffreAnnonce +
                ", categorie=" + categorie +

                ", idAnnonceLocalisation=" + idAnnonceLocalisation +
                ", dateAnnonce=" + dateAnnonce +
                '}';
    }

    //endregion

}
