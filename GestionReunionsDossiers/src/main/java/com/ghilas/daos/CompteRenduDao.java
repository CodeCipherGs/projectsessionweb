/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.CompteRendu;
import java.util.List;

/**
 *
 * @author Raritetnik
 */
public interface CompteRenduDao {
    public boolean ajouter(CompteRendu compteRendu);
    public boolean supprimer(String idCompteRendu);
    public boolean modifier(CompteRendu compteRendu);
    public List<CompteRendu> findAll();
    public List<CompteRendu> findByIdPointDordre(String idPointDordre);
}
