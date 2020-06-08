package dao;

import org.hibernate.query.Query;

import java.util.Collection;


public class JpaCaracteristiqueDao extends JpaDao<CaracteristiqueEntity> implements CaracteristiqueDao {

    @Override
    public CaracteristiqueEntity find(Integer id) {
        Query query = session.createQuery("SELECT t FROM CaracteristiqueEntity t WHERE id = " + id);
        return (CaracteristiqueEntity) query.getResultList();
    }

    @Override
    public boolean deleteAll() {
        if (session.createQuery("DELETE FROM CaracteristiqueEntity").executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<CaracteristiqueEntity> findAll() {
        Query query = session.createQuery("SELECT c FROM CaracteristiqueEntity c");
        return (Collection<CaracteristiqueEntity>) query.getResultList();
    }
}
