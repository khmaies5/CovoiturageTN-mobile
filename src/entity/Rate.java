/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author Momo
 */
public class Rate {
    private int id;
    private int nbrVote;
    private User idUser;
    private Annonce idAnnonce;

    public Rate() {
    }

    public Rate(int id, int nbrVote, User idUser, Annonce idAnnonce) {
        this.id = id;
        this.nbrVote = nbrVote;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
    }

    public Rate(int nbrVote, User idUser, Annonce idAnnonce) {
        this.nbrVote = nbrVote;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrVote() {
        return nbrVote;
    }

    public void setNbrVote(int nbrVote) {
        this.nbrVote = nbrVote;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Annonce getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    @Override
    public String toString() {
        return "Rate{" + "id=" + id + ", nbrVote=" + nbrVote + ", idUser=" + idUser + ", idAnnonce=" + idAnnonce + '}';
    }

    
    
    
}
