package dao;

import metier.UtilisateurEntity;

import java.util.Collection;

public interface UtilisateurDao extends Dao<UtilisateurEntity> {
    Collection<UtilisateurEntity> FindAllUserAnnonce();

    boolean update(UtilisateurEntity utilisateurEntity);
}
