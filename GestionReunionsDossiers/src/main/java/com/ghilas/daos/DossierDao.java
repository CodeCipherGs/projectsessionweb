/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.Dossier;
import java.util.List;

/**
 *
 * @author Raritetnik
 */
public interface DossierDao {
    public List<Dossier> trouverTout();
    public boolean créer(Dossier dossier);
    public boolean modifierTitre(Dossier dossier);
    public boolean modifierDescription(Dossier dossier);
    public boolean modifierDossier(Dossier dossier);
    public boolean activer(Dossier dossier);
    public boolean désactiver(Dossier dossier);
    public Dossier trouverParId(String id);
    public List<Dossier> trouverParTitre(String titre);
}
