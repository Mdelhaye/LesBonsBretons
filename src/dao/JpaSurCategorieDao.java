package dao;


import metier.SurCategorieEntity;
import org.hibernate.query.Query;

import java.util.Collection;

public class JpaSurCategorieDao extends JpaDao<SurCategorieEntity> implements SurCategorieDao{

    @Override
    public SurCategorieEntity find(Integer id) {
        Query query = session.createQuery("SELECT t FROM SurCategorieEntity t WHERE id = " + id);
        return (SurCategorieEntity) query.getResultList();
    }

    @Override
    public boolean deleteAll() {
        if(session.createQuery("DELETE FROM SurCategorieEntity").executeUpdate() > 0){
            return true;
        }
        return false;
    }

    public Collection<SurCategorieEntity> findAll() {
        Query query = session.createQuery("SELECT t FROM SurCategorieEntity t");
        return (Collection<SurCategorieEntity>) query.getResultList();
    }
}
