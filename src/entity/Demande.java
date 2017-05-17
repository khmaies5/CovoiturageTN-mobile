/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
//import java.util.Objects;

/**
 *
 * @author amrouche
 */
public class Demande {
    private int idDemande ;
    //private int idannonce ;
   // private int id_user ;
    private String etat ;
    Date date;
    Annonce annonce ;
    User user ;
    private int NbrPlaces ;
    User conducteur ;
//private String nomuse=user.getNom();
    public Demande() {
    }

   

  /* public Demande(String etat, Annonce annonce, User user) {
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
    }*/

  

    public Demande(int idDemande) {
        this.idDemande = idDemande;
    }

    public Demande(int id_demande, String etat, Annonce annonce, User user) {
        this.idDemande = id_demande;
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
    }

    public Demande(String etat) {
        this.etat = etat;
    }

    public Demande(int idDemande, String etat, Annonce annonce, User user, int NbrPlaces) {
        this.idDemande = idDemande;
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
        this.NbrPlaces = NbrPlaces;
    }

    public Demande(String etat, Annonce annonce, User user, int NbrPlaces, User conducteur) {
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
        this.NbrPlaces = NbrPlaces;
        this.conducteur = conducteur;
    }

    public Demande(int idDemande, String etat, Annonce annonce, User user, int NbrPlaces, User conducteur) {
        this.idDemande = idDemande;
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
        this.NbrPlaces = NbrPlaces;
        this.conducteur = conducteur;
    }

    public Demande(String etat, Annonce annonce, User user, int NbrPlaces) {
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
        this.NbrPlaces = NbrPlaces;
    }

    public Demande(String etat, Annonce annonce, User user) {
        this.etat = etat;
        this.annonce = annonce;
        this.user = user;
    }
    

   

   

    public int getId_demande() {
        return idDemande;
    }

  

    public String getEtat() {
        return etat;
    }

    public void setId_demande(int id_demande) {
        this.idDemande = id_demande;
    }

 

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.idDemande;
       
        return hash;
    }

    public int getNbrPlaces() {
        return NbrPlaces;
    }

    public void setNbrPlaces(int NbrPlaces) {
        this.NbrPlaces = NbrPlaces;
    }

    public User getConducteur() {
        return conducteur;
    }

    public void setConducteur(User conducteur) {
        this.conducteur = conducteur;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Demande other = (Demande) obj;
        if (this.idDemande != other.idDemande) {
            return false;
        }
     
     
        return true;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + idDemande +  ", etat=" + etat + '}';
    }
    
    
}
