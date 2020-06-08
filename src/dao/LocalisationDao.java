package dao;

import metier.LocalisationEntity;

import java.util.Collection;

public interface LocalisationDao extends Dao<LocalisationEntity> {
    public Collection<LocalisationEntity> findVilles(String codePostal);
}
