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
    public boolean update(UtilisateurEntity utilisateurEntity) {

        Transaction tx = session.beginTransaction();
        try {
            UtilisateurEntity person = this.find(utilisateurEntity.getIdUtilisateur());
            person.setPassword(utilisateurEntity.getPassword());
            person.setNumTel(utilisateurEntity.getNumTel());
            person.setRue(utilisateurEntity.getRue());
            person.setNumRue(utilisateurEntity.getNumRue());
            person.setVille(utilisateurEntity.getVille());
            person.setIdLocalisationUtilisateur(utilisateurEntity.getIdLocalisationUtilisateur());
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
        return session.createQuery("DELETE FROM UtilisateurEntity").executeUpdate() > 0;
    }

    //endregion


}
