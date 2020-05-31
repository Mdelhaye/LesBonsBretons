package dao;

import metier.AnnonceEntity;
import metier.CategorieEntity;
import metier.SurCategorieEntity;
import metier.UtilisateurEntity;
import org.hibernate.query.Query;

import java.util.Collection;

public class JpaAnnonceDao extends JpaDao<AnnonceEntity> implements AnnonceDao {

    private Class classAnnonce = new AnnonceEntity().getClass();

    public Collection<AnnonceEntity> findAnnonce(String nomAnnonce, SurCategorieEntity id) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a, CategorieEntity b WHERE b.idSurCategorie = :idCategorie AND b.idCategorie= a.categorie.id AND a.titreAnnonce LIKE :nomAnnonce");
        query.setParameter("nomAnnonce", "%" + nomAnnonce + "%");
        query.setParameter("idCategorie", (SurCategorieEntity) id);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    public Collection<AnnonceEntity> findAnnonce(String nomAnnonce, CategorieEntity category) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a  WHERE a.categorie = :idCategorie AND a.titreAnnonce LIKE :nomAnnonce ORDER BY  a.dateAnnonce DESC");
        query.setParameter("nomAnnonce", "%" + nomAnnonce + "%");
        query.setParameter("idCategorie", (CategorieEntity) category);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    public Collection<AnnonceEntity> findAnnonce(String nomAnnonce, CategorieEntity category, int prixAnnonce) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a  WHERE a.categorie = :idCategorie AND a.titreAnnonce LIKE :nomAnnonce AND a.prix<= :prixAnnonce ORDER BY  a.dateAnnonce DESC");
        query.setParameter("nomAnnonce", "%" + nomAnnonce + "%");
        query.setParameter("idCategorie", (CategorieEntity) category);
        query.setParameter("prixAnnonce", prixAnnonce);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    public Collection<AnnonceEntity> findAnnonceByIdUser(UtilisateurEntity user) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a WHERE a.idUtilisateurAnnonce = :user ORDER BY  a.dateAnnonce DESC");
        query.setParameter("user", (UtilisateurEntity) user);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    @Override
    public AnnonceEntity find(Integer id) {
        return null;
    }

    @Override
    public AnnonceEntity find(Class c, Integer id) {
        return null;
    }

    @Override
    public Collection<AnnonceEntity> findAll() {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a");
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    @Override
    public boolean deleteAll() {
        Query query = session.createQuery("DELETE FROM AnnonceEntity a");
        return (boolean) query.getSingleResult();
    }

    public AnnonceEntity findFirstAvailable() {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a ORDER BY dateAnnonce asc");
        return (AnnonceEntity) query.setMaxResults(1).getResultList();
    }
}
