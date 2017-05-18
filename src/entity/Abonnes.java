/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Sarra
 */
public class Abonnes {
    int id;
    Date dateAbonnement;


      int etatAbonnement;
     public int idgroupe;
    String roleUser;
   User user;
   Groupe groupe;
private List<Reponse> reponses;
private List<Sujet> sujets;

    public int getIdgroupe() {
        return idgroupe;
    }

    public void setIdgroupe(int idgroupe) {
        this.idgroupe = idgroupe;
    }

    public Abonnes(int id) {
        this.id = id;
    }

    public Abonnes(Groupe groupe) {
        this.groupe = groupe;
    }

    public Abonnes(User user) {
        this.user = user;
    }

    public Abonnes(int id, Date dateAbonnement, int etatAbonnement, String roleUser, User user, Groupe groupe) {
        this.id = id;
        this.dateAbonnement = dateAbonnement;
        this.etatAbonnement = etatAbonnement;
        this.roleUser = roleUser;
        this.user = user;
        this.groupe = groupe;
    }

    public Abonnes(int id, Date dateAbonnement, String role_user, User user, Groupe groupe) {
        this.id = id;
        this.dateAbonnement = dateAbonnement;
        this.roleUser = role_user;
        this.user = user;
        this.groupe = groupe;
    }
public Abonnes(int id, Date dateAbonnement, String roleUser, User user) {
        this.id = id;
        this.dateAbonnement = dateAbonnement;
        this.roleUser = roleUser;
        this.user = user;

    }

    public Abonnes(Date dateAbonnement, String roleUser, User user, Groupe groupe) {
        this.dateAbonnement = dateAbonnement;
        this.roleUser = roleUser;
        this.user = user;
        this.groupe = groupe;
    }

    public Abonnes(String roleUser, User user, Groupe groupe) {
        this.roleUser = roleUser;
        this.user = user;
        this.groupe = groupe;
    }

    public Abonnes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int isEtatAbonnement() {
        return etatAbonnement;
    }

    public void setEtatAbonnement(int etatAbonnement) {
        this.etatAbonnement = etatAbonnement;
    }
    public Date getDateAbonnement() {
        return dateAbonnement;
    }

    public void setDateAbonnement(Date dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String role_user) {
        this.roleUser = role_user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public List<Sujet> getSujets() {
        return sujets;
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Abonnes other = (Abonnes) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Abonnes{" + "id=" + id + ", dateAbonnement=" + dateAbonnement + ", etatAbonnement=" + etatAbonnement + ", roleUser=" + roleUser + ", user=" + user + ", groupe=" + groupe + ", reponses=" + reponses + ", sujets=" + sujets + '}';
    }

   
 
}
