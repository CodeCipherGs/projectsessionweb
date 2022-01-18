/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghilas.daos;

import com.ghilas.entites.ReunionMembres;
import java.util.List;

/**
 *
 * @author Raritetnik
 */
public interface MembresReunionDao {
    public boolean retirer(String id);
    public boolean ajouter(ReunionMembres membre);
    public boolean participer(ReunionMembres membre);
    public boolean infirmer(ReunionMembres membre);
    public List<ReunionMembres> trouverReunionsParIdMembre(String idMembre);
    public List<ReunionMembres> trouverMembresParIdReunion(String idReunion);
}
