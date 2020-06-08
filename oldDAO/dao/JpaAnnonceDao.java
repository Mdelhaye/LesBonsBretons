package dao;

import metier.AnnonceEntity;
import org.hibernate.query.Query;

import java.util.Collection;

public class JpaAnnonceDao extends JpaDao<AnnonceEntity> implements AnnonceDao {

    private Class classAnnonce = new AnnonceEntity().getClass();

    public Collection<AnnonceEntity> findAnnonce(String nomAnnonce) {
        Query query = session.createQuery("SELECT a FROM AnnonceEntity a WHERE a.titreAnnonce LIKE :nomAnnonce");
        query.setParameter("nomAnnonce", "%" + nomAnnonce + "%");
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
