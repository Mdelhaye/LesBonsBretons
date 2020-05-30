package dao;

import metier.UtilisateurEntity;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;

public class JpaUtilisateurDao extends JpaDao<UtilisateurEntity> implements UtilisateurDao {

    //region Override
    @Override
    public Collection<UtilisateurEntity> FindAllUserAnnonce() {
        Query query = session.createQuery("SELECT t FROM UtilisateurEntity t ");
        return (Collection<UtilisateurEntity>) query.getResultList();
    }


    @Override
    public UtilisateurEntity find(Integer idUser) {
        Query query = session.createQuery("SELECT t FROM UtilisateurEntity t WHERE id = " + idUser);
        return (UtilisateurEntity) query.getSingleResult();
    }


    public UtilisateurEntity connexionUser(String mEmail, String mPassword) {
        Query query = session.createQuery("SELECT t FROM UtilisateurEntity t WHERE t.login =  :mEmail  AND t.password = :mPassword");
        query.setParameter("mEmail", mEmail); // replace first ? with value for first name
        query.setParameter("mPassword", mPassword);
        return (UtilisateurEntity) query.getSingleResult();
    }

    @Override
    public boolean update(String password, String numTel, String rue, int numRue, String ville, int idUtilisateur) {

        Transaction tx = session.beginTransaction();
        try {
            UtilisateurEntity person = this.find(idUtilisateur);
            person.setPassword(password);
            person.setNumTel(numTel);
            person.setRue(rue);
            person.setNumRue(numRue);
            person.setVille(ville);
            tx.commit();
            return true;
        } catch (Exception e) {

            tx.rollback();
            return false;
        }
        //
    }

    @Override
    public Collection<UtilisateurEntity> findAll() {
        Query query = session.createQuery("SELECT t FROM UtilisateurEntity t");
        return (Collection<UtilisateurEntity>) query.getResultList();
    }

    public boolean deleteAll() {
        if (session.createQuery("DELETE FROM UtilisateurEntity").executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    //endregion


}
