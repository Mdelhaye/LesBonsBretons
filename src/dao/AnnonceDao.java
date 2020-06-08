package dao;

import metier.AnnonceEntity;
import metier.UtilisateurEntity;

import java.util.Collection;

public interface AnnonceDao extends Dao<AnnonceEntity> {
    public AnnonceEntity findFirstAvailable();
    public Collection<AnnonceEntity> findAnnonceByIdUser(UtilisateurEntity user);

    public boolean update(AnnonceEntity annonce);
}
