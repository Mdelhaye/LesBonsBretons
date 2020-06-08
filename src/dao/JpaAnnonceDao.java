package dao;

import metier.AnnonceEntity;
import metier.CategorieEntity;
import metier.UtilisateurEntity;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;

public class JpaAnnonceDao extends JpaDao<AnnonceEntity> implements AnnonceDao {

    private Class classAnnonce = new AnnonceEntity().getClass();


    public Collection<AnnonceEntity> findAnnonce(String nomAnnonce, CategorieEntity category, boolean offre) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a  WHERE a.categorie = :idCategorie AND a.titreAnnonce LIKE :nomAnnonce AND a.isOffreAnnonce = :offre ORDER BY  a.dateAnnonce DESC");
        query.setParameter("nomAnnonce", "%" + nomAnnonce + "%");
        query.setParameter("idCategorie", (CategorieEntity) category);
        query.setParameter("offre", offre);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    public Collection<AnnonceEntity> findAnnonce(String nomAnnonce, CategorieEntity category, int prixAnnonce, boolean offre) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a  WHERE a.categorie = :idCategorie AND a.titreAnnonce LIKE :nomAnnonce AND a.isOffreAnnonce = :offre AND a.prix<= :prixAnnonce ORDER BY  a.dateAnnonce DESC");
        query.setParameter("nomAnnonce", "%" + nomAnnonce + "%");
        query.setParameter("idCategorie", (CategorieEntity) category);
        query.setParameter("prixAnnonce", prixAnnonce);
        query.setParameter("offre", offre);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    public Collection<AnnonceEntity> findAnnonceByIdUser(UtilisateurEntity user) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a WHERE a.idUtilisateurAnnonce = :user ORDER BY  a.dateAnnonce DESC");
        query.setParameter("user", (UtilisateurEntity) user);
        return (Collection<AnnonceEntity>) query.getResultList();
    }

    @Override
    public AnnonceEntity find(Integer id) {
        Query query = session.createQuery("SELECT t FROM AnnonceEntity t WHERE id = " + id);
        return (AnnonceEntity) query.getSingleResult();
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

    @Override
    public boolean update(AnnonceEntity an) {

        Transaction tx = session.beginTransaction();
        try {
            AnnonceEntity annonce = this.find(an.getIdAnnonce());
            annonce.setCategorie(an.getCategorie());
            annonce.setDateAnnonce(an.getDateAnnonce());
            annonce.setDescriptionAnnonce(an.getDescriptionAnnonce());
            annonce.setLienImage(an.getLienImage());
            annonce.setPrix(an.getPrix());
            annonce.setTitreAnnonce(an.getTitreAnnonce());
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
            return false;
        }
        //
    }
}
