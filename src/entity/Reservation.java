/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

//import java.util.Objects;

/**
 *
 * @author amrouche
 */
public class Reservation {
    private int id ;
    private String dateReservation ;
    private String etat_reservation ;
    private double montant ;
    private String typePayement;
    private User creator ;
    private int nbr_place;
    private Annonce annonce ;
    

    public Reservation() {
    }

    public Reservation(String etat_reservation, double montant, String Type_payement, User creator, int nbr_place, Annonce annonce) {
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
        this.creator = creator;
        this.nbr_place = nbr_place;
        this.annonce = annonce;
    }

    public Reservation(String date_reservation, String etat_reservation, double montant, String Type_payement, User creator) {
        this.dateReservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
        this.creator = creator;
    }

    public Reservation(String etat_reservation) {
        this.etat_reservation = etat_reservation;
    }

    public Reservation(String date_reservation, String etat_reservation, double montant, String Type_payement, User creator, int nbr_place, Annonce annonce) {
        this.dateReservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
        this.creator = creator;
        this.nbr_place = nbr_place;
        this.annonce = annonce;
    }
    

    public Reservation(String date_reservation, String etat_reservation, double montant, String Type_payement, int id_user) {
        this.dateReservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
        //this.id_user = id_user;
    }

    public Reservation(int id, String date_reservation, String etat_reservation, double montant, String Type_payement, User creator) {
        this.id = id;
        this.dateReservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
        this.creator = creator;
    }

    public Reservation(int id, String date_reservation, String etat_reservation, double montant, String Type_payement, User creator, int nbr_place, Annonce annonce) {
        this.id = id;
        this.dateReservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
        this.creator = creator;
        this.nbr_place = nbr_place;
        this.annonce = annonce;
    }

    public Reservation(int id, String date_reservation, String etat_reservation, double montant, String Type_payement, int id_user) {
        this.id = id;
        this.dateReservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.typePayement = Type_payement;
       // this.id_user = id_user;
        
    }

    public int getId() {
        return id;
    }

    public String getDate_reservation() {
        return dateReservation;
    }

    public String getEtat_reservation() {
        return etat_reservation;
    }

    public double getMontant() {
        return montant;
    }

    public String getType_payement() {
        return typePayement;
    }

  

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_reservation(String date_reservation) {
        this.dateReservation = date_reservation;
    }

    public void setEtat_reservation(String etat_reservation) {
        this.etat_reservation = etat_reservation;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setType_payement(String Type_payement) {
        this.typePayement = Type_payement;
    }

   
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
       
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.montant) ^ (Double.doubleToLongBits(this.montant) >>> 32));
        //hash = 79 * hash + this.id_user;
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.montant) != Double.doubleToLongBits(other.montant)) {
            return false;
        }
      
       
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_reservation=" + dateReservation + ", etat_reservation=" + etat_reservation + ", montant=" + montant + ", Type_payement=" + typePayement +  '}';
    }
    
    
}
