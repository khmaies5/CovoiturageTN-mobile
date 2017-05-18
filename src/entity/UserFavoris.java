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
 * @author Nacef Fethi
 */
public class UserFavoris {
        private int id;
        private Date dateEnregistrement;
           private User userRecommendes;
      private User userConnect;
      private int nbrRecommendation;

 

    public UserFavoris(int id, User userRecommendes, User userConnect, int nbrRecommendation) {
        this.id = id;
        this.userRecommendes = userRecommendes;
        this.userConnect = userConnect;
        this.nbrRecommendation = nbrRecommendation;
    }

    public UserFavoris(int id, Date dateEnregistrement, User userRecommendes, User userConnect, int nbrRecommendation) {
        this.id = id;
        this.dateEnregistrement = dateEnregistrement;
        this.userRecommendes = userRecommendes;
        this.userConnect = userConnect;
        this.nbrRecommendation = nbrRecommendation;
    }

    public UserFavoris( User userRecommendes, User userConnect ) {
       
        this.userRecommendes = userRecommendes;
        this.userConnect = userConnect;
    
    }


   




    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public User getUserRecommendes() {
        return userRecommendes;
    }

    public void setUserRecommendes(User userRecommendes) {
        this.userRecommendes = userRecommendes;
    }

    public User getUserConnect() {
        return userConnect;
    }

    public void setUserConnect(User userConnect) {
        this.userConnect = userConnect;
    }

    public int getNbrRecommendation() {
       nbrRecommendation++;
        return nbrRecommendation;
    }

    public void setNbrRecommendation(int nbrRecommendation) {
        this.nbrRecommendation = nbrRecommendation;
    }

  




 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final UserFavoris other = (UserFavoris) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserRecommendes{" + "id=" + id + ", user_recommendes=" + userRecommendes + ", user_connect=" + userConnect + ", nbr_recommendation=" + nbrRecommendation + '}';
    }


      

    
}
