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
public class Commentaire {
    
    private int idCommentaire;
    private String description;
    private Annonce idAnnonce;
    private User idUser;

    public Commentaire(int idCommentaire, String description, Annonce idAnnonce, User idUser) {
        this.idCommentaire = idCommentaire;
        this.description = description;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
    }
    
    public Commentaire() {
    }
    

    public Commentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Commentaire(String description) {
        this.description = description;
    }

    
    public Commentaire(String description, Annonce idAnnonce, User idUser) {
        this.description = description;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public String getDescription() {
        return description;
    }

    public Annonce getIdAnnonce() {
        return idAnnonce;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setDescription(String description) { 
        this.description = description;
    }

    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", description=" + description + ", idAnnonce=" + idAnnonce + ", idUser=" + idUser + '}';
    }
    
    
    
    
}
