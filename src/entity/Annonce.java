/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.Date;









/**
 *
 * @author khmai
 */
public class Annonce {
    private int idAnnonce;
    private Date tripDate;
   private Date annonceDate;
    private String lieuDepart;
    private String lieuArriver;
    private String typeAnnonce;
    private int nbrPersonne;
    private float prix;
    private String critere;
    private String distance;
    private User creator;
    private String userImg;

    public Annonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Annonce(Date tripDate, String lieuDepart, String lieuArriver, int nbrPersonne, float prix, String critere, String distance, User creator) {
        this.tripDate = tripDate;
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.nbrPersonne = nbrPersonne;
        this.prix = prix;
        this.critere = critere;
        this.distance = distance;
        this.creator = creator;
    }
    
    

    public Annonce(int idAnnonce, Date tripDate, Date annonceDate, String lieuDepart, String lieuArriver, String typeAnnonce, int nbrPersonne, float prix, String critere) {
        this.idAnnonce = idAnnonce;
        this.tripDate = tripDate;
        this.annonceDate = annonceDate;
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.typeAnnonce = typeAnnonce;
        this.nbrPersonne = nbrPersonne;
        this.prix = prix;
        this.critere = critere;
    }

    public Annonce(Date tripDate, Date annonceDate, String lieuDepart, String lieuArriver, String typeAnnonce, int nbrPersonne, float prix, String critere) {
        this.tripDate = tripDate;
        this.annonceDate = annonceDate;
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.typeAnnonce = typeAnnonce;
        this.nbrPersonne = nbrPersonne;
        this.prix = prix;
        this.critere = critere;
    }

    public Annonce(Date tripDate, Date annonceDate, String lieuDepart, String lieuArriver, String typeAnnonce, int nbrPersonne, float prix, String critere,String distance, User creator) {
        this.tripDate = tripDate;
        this.annonceDate = annonceDate;
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.typeAnnonce = typeAnnonce;
        this.nbrPersonne = nbrPersonne;
        this.prix = prix;
        this.critere = critere;
        this.distance = distance;
        this.creator = creator;
    }

    public Annonce(int idAnnonce, Date tripDate, Date annonceDate, String lieuDepart, String lieuArriver, String typeAnnonce, int nbrPersonne, float prix, String critere,String distance, User creator) {
        this.idAnnonce = idAnnonce;
        this.tripDate = tripDate;
        this.annonceDate = annonceDate;
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.typeAnnonce = typeAnnonce;
        this.nbrPersonne = nbrPersonne;
        this.prix = prix;
        this.critere = critere;
        this.distance=distance;
        this.creator = creator;
    }

    public Annonce(int idAnnonce, Date tripDate, Date annonceDate, String lieuDepart, String lieuArriver, int nbrPersonne, float prix, String critere, String distance, User creator,String userImg) {
        this.idAnnonce = idAnnonce;
        this.tripDate = tripDate;
        this.annonceDate = annonceDate;
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.nbrPersonne = nbrPersonne;
        this.prix = prix;
        this.critere = critere;
        this.distance = distance;
        this.creator = creator;
        this.userImg = userImg;
    }

    @Override
    public String toString() {
        return "Annonce{" + "idAnnonce=" + idAnnonce + ", tripDate=" + tripDate + ", annonceDate=" + annonceDate + ", lieuDepart=" + lieuDepart + ", lieuArriver=" + lieuArriver + ", typeAnnonce=" + typeAnnonce + ", nbrPersonne=" + nbrPersonne + ", prix=" + prix + ", critere=" + critere + ", creator=" + creator + '}';
    }

   
    
    

    

   

   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Annonce other = (Annonce) obj;
        if (this.idAnnonce != other.idAnnonce) {
            return false;
        }
        return true;
    }

    public String getUserImg() {
        return userImg;
    }
    
    

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Date getAnnonceDate() {
        return annonceDate;
    }

    public void setAnnonceDate(Date annonceDate) {
        this.annonceDate = annonceDate;
    }

  

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    
    
    
    
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    

    public Annonce() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idAnnonce;
        return hash;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

   

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArriver() {
        return lieuArriver;
    }

    public void setLieuArriver(String lieuArriver) {
        this.lieuArriver = lieuArriver;
    }

    public String getTypeAnnonce() {
        return typeAnnonce;
    }

    public void setTypeAnnonce(String typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    public int getNbrPersonne() {
        return nbrPersonne;
    }

    public void setNbrPersonne(int nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

  
    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCritere() {
        return critere;
    }

    public void setCritere(String critere) {
        this.critere = critere;
    }
    
}
