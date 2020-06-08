package dao;

import metier.UtilisateurEntity;

import java.util.Collection;

public interface UtilisateurDao extends Dao<UtilisateurEntity> {
    public Collection<UtilisateurEntity> FindAllUserAnnonce();
}
