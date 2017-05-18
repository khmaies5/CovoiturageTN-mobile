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
public class Groupe {
    private int id;
    private String nom;
     private String description;
     private String dateCreation;
     private List<Abonnes> abonnes;
     private List<Sujet> sujets;
     private List<Invitation> invitations;
     String pathImage;
     String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
     
private int   nombre;

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Groupe(String nom, String description, String pathImage) {
        this.nom = nom;
        this.description = description;
        this.pathImage = pathImage;
    }

    public int getNombre() {
        return abonnes.size();
    }

    public Groupe(int id, String nom, String description, String pathImage) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.pathImage = pathImage;
    }

    public Groupe(int id, String nom, String description, String dateCreation, List<Abonnes> abonnes, List<Sujet> sujets, String pathImage) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.abonnes = abonnes;
        this.sujets = sujets;
        this.pathImage = pathImage;
    }

    public Groupe(int id, String nom, String description, String dateCreation, List<Sujet> sujets, String pathImage) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.sujets = sujets;
        this.pathImage = pathImage;
    }

    public Groupe(int id, String nom, String description, String dateCreation, String pathImage) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.pathImage = pathImage;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }


//    public Groupe(int id, String nom, String description, Date dateCreation, List<Abonnes> abonnes) {
//        this.id = id;
//        this.nom = nom;
//        this.description = description;
//        this.dateCreation = dateCreation;
//        this.abonnes = abonnes;
//    }

    public Groupe(int id, String nom, String description, String dateCreation, List<Abonnes> abonnes, List<Sujet> sujets) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.abonnes = abonnes;
        this.sujets = sujets;
    }

  

    public Groupe(int id, String nom, String description, String dateCreation, List<Sujet> sujets) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.sujets = sujets;
    }

    public Groupe(int id) {
        this.id = id;
    }
public Groupe() {
    
    }
    public Groupe(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Groupe(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }


  

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

    public List<Sujet> getSujets() {
        return sujets;
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Abonnes> getAbonnes() {
        return abonnes;
    }

    public void setAbonnes(List<Abonnes> abonnes) {
        this.abonnes = abonnes;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", dateCreation=" + dateCreation + ", abonnes=" + abonnes + ", sujets=" + sujets + ", invitations=" + invitations + ", pathImage=" + pathImage + ", nombre=" + nombre + '}';
    }



   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Groupe other = (Groupe) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

  

    
   

}
