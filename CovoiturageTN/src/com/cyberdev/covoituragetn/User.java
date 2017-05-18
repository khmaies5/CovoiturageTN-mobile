/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import java.util.Date;

/**
 *
 * @author khmai
 */
public class User {
 public static int idd;

    public static int getIdd() {
        return idd;
    }

    public static void setIdd(int idd) {
        User.idd = idd;
    }
    public static int etat_compte;

    public static int getEtat_compte() {
        return etat_compte;
    }

    public static void setEtat_compte(int etat_compte) {
        User.etat_compte = etat_compte;
    }

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public User(String nom, String login, String prenom, String password, String email, String cin, String photo_Profil, String voiture, String niv_Experience, int nbr_Signalisation, int nbr_Recommandation, String travail, String sexe, Date date_naissance, String telephone, String gouvernorat, int role) {
        this.nom = nom;
        this.login = login;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.photo_Profil = photo_Profil;
        this.voiture = voiture;
        this.niv_Experience = niv_Experience;
        this.nbr_Signalisation = nbr_Signalisation;
        this.nbr_Recommandation = nbr_Recommandation;
        this.travail = travail;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.role = role;
    }

    public User(String nom, String prenom, String email, String photo_Profil, String voiture, String niv_Experience, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.photo_Profil = photo_Profil;
        this.voiture = voiture;
        this.niv_Experience = niv_Experience;
        this.telephone = telephone;
    }

    public User(int id, String nom, String prenom, String email, String photo_Profil,String niv_Experience , String telephone , String voiture) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.photo_Profil = photo_Profil;
        this.niv_Experience = niv_Experience;
        this.telephone = telephone;
        this.voiture = voiture;

    }

  

   
    
     private int id;
    private String nom;
    private String login;
    private String prenom;
    private String password;
    private String email;
    private String cin;
    private String photo_Profil;
    private String voiture;
    
    private String niv_Experience;
    private int nbr_Signalisation;
    private int nbr_Recommandation;
    private String travail;
    private String sexe;
    private Date date_naissance;
    private String telephone;
    private String gouvernorat;
    private int role;
   // private List<Annonce> annonces;
    // private List<Vehicule> vehiculess;
    // private List<Reservation> reservations;
    // private List<Sujet> sujets;
      // private List<Avis> avis;
    
    

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String nom, String login, String prenom, String password, String email) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
    }

    public User(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(int id, String nom, String prenom, String password, String email, String cin, String photo_Profil, int etat_compte, String niv_Experience, int nbr_Signalisation, int nbr_Recommandation, String travail, String sexe, Date date_naissance, String telephone, String gouvernorat, int role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.photo_Profil = photo_Profil;
        this.etat_compte = etat_compte;
        this.niv_Experience = niv_Experience;
        this.nbr_Signalisation = nbr_Signalisation;
        this.nbr_Recommandation = nbr_Recommandation;
        this.travail = travail;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", cin=" + cin + ", photo_Profil=" + photo_Profil + ", etat_compte=" + etat_compte + ", niv_Experience=" + niv_Experience + ", nbr_Signalisation=" + nbr_Signalisation + ", nbr_Recommandation=" + nbr_Recommandation + ", travail=" + travail + ", sexe=" + sexe + ", date_naissance=" + date_naissance + ", telephone=" + telephone + ", gouvernorat=" + gouvernorat + ", role=" + role + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPhoto_Profil() {
        return photo_Profil;
    }

    public void setPhoto_Profil(String photo_Profil) {
        this.photo_Profil = photo_Profil;
    }

   
    public String getNiv_Experience() {
        return niv_Experience;
    }

    public void setNiv_Experience(String niv_Experience) {
        this.niv_Experience = niv_Experience;
    }

    public int getNbr_Signalisation() {
        return nbr_Signalisation;
    }

    public void setNbr_Signalisation(int nbr_Signalisation) {
        this.nbr_Signalisation = nbr_Signalisation;
    }

    public int getNbr_Recommandation() {
        return nbr_Recommandation;
    }

    public void setNbr_Recommandation(int nbr_Recommandation) {
        this.nbr_Recommandation = nbr_Recommandation;
    }

    public String getTravail() {
        return travail;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

//    public List<Annonce> getProducts() {
//        return annonces;
//    }

//    public void setProducts(List<Annonce> products) {
//        this.annonces = products;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
