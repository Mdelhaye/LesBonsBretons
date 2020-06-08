package dao;

import metier.CategorieEntity;
import org.hibernate.query.Query;

import java.util.Collection;

public class JpaCategorieDao extends JpaDao<CategorieEntity> implements CategorieDao{


    @Override
    public CategorieEntity find(Integer id) {
        Query query = session.createQuery("SELECT t FROM CategorieEntity t WHERE id = " + id);
        return (CategorieEntity) query.getResultList();
    }

    @Override
    public boolean deleteAll() {
        if(session.createQuery("DELETE FROM CategorieEntity").executeUpdate() > 0){
            return true;
        }
        return false;
    }


    public Collection<CategorieEntity> findAll() {
        Query query = session.createQuery("SELECT t FROM CategorieEntity t");
        return (Collection<CategorieEntity>) query.getResultList();
    }

    public Collection<CategorieEntity> getCategorieFromSurCategorie(int idSurCategorie) {
        Query query = session.createQuery("SELECT t FROM CategorieEntity t where idSurCategorie = " + idSurCategorie);
        return (Collection<CategorieEntity>) query.getResultList();
    }
}
