/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Sarra
 */
public class Invitation {
    private int id;
    private User user;
    private Groupe groupe;
private User creator;
private Date dateInvitation;

    public Invitation(User user, Groupe groupe, User creator) {
        this.user = user;
        this.groupe = groupe;
        this.creator = creator;
    }

    public Invitation() {
    }

    public User getCreator() {
        return creator;
    }

    public Invitation(int id, User user, Groupe groupe, User creator, Date dateInvitation) {
        this.id = id;
        this.user = user;
        this.groupe = groupe;
        this.creator = creator;
        this.dateInvitation = dateInvitation;
    }

    public Invitation(User user, Groupe groupe, User creator, Date dateInvitation) {
        this.user = user;
        this.groupe = groupe;
        this.creator = creator;
        this.dateInvitation = dateInvitation;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
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
        final Invitation other = (Invitation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invitation{" + "id=" + id + ", user=" + user + ", groupe=" + groupe + ", creator=" + creator + ", dateInvitation=" + dateInvitation + '}';
    }


}
