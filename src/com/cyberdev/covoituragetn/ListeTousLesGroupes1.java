/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entity.Groupe;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;





/**
 *
 * @author esprit
 */
public class ListeTousLesGroupes1 {
    
 Form f;
       Command cmd1, cmd2,cmd4, cmdBack,cmd3;
       ImageViewer img;
     private ConnectionRequest connectionRequest;
     UIBuilder ui = new UIBuilder();
       Groupe groupe = new Groupe();
       User user=LogIn.e;
       Button btnAdhere = new Button("Adhèrer au groupe");
         Button btnEntrer= new Button("Accèder au groupe");
           Button btnReAdherer = new Button("Rèabonner au groupe");

Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
   public  ListeTousLesGroupes1(Resources theme){
     f =new Form("Liste des Groupes",BoxLayout.y());
   Toolbar.setGlobalToolbar(true);
//btnAdhere.setEnabled(true);
//btnEntrer.setEnabled(true);
//btnReAdherer.setEnabled(true);
//btnAdhere.setVisible(false);
//btnEntrer.setEnabled(false);
//btnReAdherer.setEnabled(false);
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
         cmd1 = new Command("Mon profil");
        cmd2 = new Command("Mes alertes");
  cmd3 = new Command("Mes groupes");
        cmd4 = new Command("Page 4");
        cmdBack = new Command("Back");
         f.getToolbar().addCommandToSideMenu("Mes groupes", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           MesGroupes mesGroupes = new MesGroupes(theme);
           mesGroupes.getF().show();
            }
        });
           f.getToolbar().addCommandToSideMenu("Mes invitations", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           MesInvitations mesInv = new MesInvitations(theme);
           mesInv.getF().show();
            }
        });
       ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/script_sarra/groupes/selectAll.php");
   
      
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
           ArrayList<Groupe> groupes=getListGroupes(new String(con.getResponseData()));

    for(Groupe gr : groupes ){
           ConnectionRequest con2 = new ConnectionRequest();
                            con2.setUrl("http://localhost/script_sarra/abonnement/SelectEtatAbonneByIdGroueAndUserConn.php?idGroupe=" + gr.getId()+"&userConn="+user.getId());
                            con2.addResponseListener(new ActionListener<NetworkEvent>() {

                                @Override
                                public void actionPerformed(NetworkEvent evt) {

                                      
                                 int etat = getEtatAbonnementbyIdGroupeandUser(new String(con2.getResponseData()));
                                  
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
         
       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 
       Label lbl1 = new Label(gr.getNom());
       Label lbl2 = new Label(gr.getDescription());
   Label cadrephoto=new Label();
  
  cadrephoto.setIcon(URLImage.createToStorage(placeholder, "default3.jpg", "http://localhost/Covoiturage/web/images/groupes/default3.jpg"));

       ctn2.add(lbl1);
       ctn2.add(lbl2);
 //  ctn2.add(btn);
   
       ctn1.add(cadrephoto);
       ctn1.add(ctn2);
       ctn3.add(ctn1);
ctn3.setTactileTouch(true);
ctn3.setFocusable(true);
 
       f.add(ctn3);
                               
                   ctn3.addPointerReleasedListener(new ActionListener() {
                    
                                     @Override
                                     public void actionPerformed(ActionEvent evt) {
                                              System.out.println(etat);
                                         if(etat==1)
                                         {  boolean a=Dialog.show("Groupe :"+" "+gr.getNom(),"Vous voulez accèder au groupe", "Refuser","Accepter");
                                           if(a==false){
                                            AfficherGroupe afficherGroupe = new AfficherGroupe(theme,gr);
         afficherGroupe.getF().show();
                                           }
                                         }
                                         else if(etat==-1)
                                         {  boolean a=Dialog.show("Groupe :"+" "+gr.getNom(),"Vous voulez adhèrer au groupe?", "Refuser","Accepter");
                                         if(a==false){
                                                           ConnectionRequest req2 = new ConnectionRequest();
                req2.setUrl("http://localhost/script_sarra/Abonnement/adherer.php?idGroupe=" + gr.getId() + "&idUser=" + user.getId());

                req2.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Votre adhèsion a ètè effectuè avec succèe", null, null);
                                AfficherGroupe afficherGroupe = new AfficherGroupe(theme,gr);
         afficherGroupe.getF().show();
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req2);
                                         }
                                         }
                                           else  if(etat==0)
                                         {  boolean a=Dialog.show("Groupe :"+" "+gr.getNom(),"Vous voulez rèadhèrer au groupe?", "Refuser","Accepter");
                                          if(a==false){
                                              
            ConnectionRequest req2 = new ConnectionRequest();
                req2.setUrl("http://localhost/script_sarra/Abonnement/Readherer.php?idGroupe=" + gr.getId() + "&idUser=" + user.getId());

                req2.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                                AfficherGroupe afficherGroupe = new AfficherGroupe(theme,gr);
         afficherGroupe.getF().show();
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req2);
         
                                          }
                                         }
                                  
                                     }
                                 }); 
    // btn.addActionListener(new ActionListener() {
          //                           @Override
             //                        public void actionPerformed(ActionEvent evt) {
                            //             System.out.println(etat);
                              //            System.out.println(gr.getId());
                          //           }
                      //           }); 
    } });
        NetworkManager.getInstance().addToQueue(con2);}
   

            }
        });
        NetworkManager.getInstance().addToQueue(con);
 f.show();

 }
     public ArrayList<Groupe> getListGroupes(String json) {
        
        ArrayList<Groupe> listeGroupes = new ArrayList<>();
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));
         
 List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("groupe");
    // System.out.println(list);
//Map<String, Object> list2 = (Map<String, Object>) groupes.get("groupe");

                          //    System.out.println(groupes.size());
                           //     System.out.println(groupes);
                                  
                                       //   System.out.println(list);
                     //          System.out.println(list2.size());
    
        for (Map<String, Object> obj : list) {
              
              Groupe groupe=new Groupe();
 groupe.setId(Integer.parseInt(obj.get("id").toString()));         
groupe.setNom(obj.get("nom").toString());
groupe.setDescription(obj.get("description").toString());

 listeGroupes.add(groupe);

         }
    
           //   f.show();
        } catch (IOException ex) {
         }
     
      return listeGroupes;
     

    }
       public int getEtatAbonnementbyIdGroupeandUser(String json) {
        
     int etat = -1;
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));
        
          
           
 //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
        Map<String, Object> ab = (Map<String, Object>) groupes.get("abonnees");
    
 if(ab==null){return etat;}
  else {
       //  for (Map<String, Object> obj : list) {
              
       etat= Integer.parseInt(ab.get("etat").toString());   
          
return etat;

//etat=Integer.parseInt(obj.get("etat").toString());
  //           System.out.println(etat);
}
      
        //   }
         
           //   f.show();
        } catch (IOException ex) {
         }
     
   //   return listeGroupes;
     return etat;

    }
        public int getCountElement(String json) {
        
     int nb = -1;
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));
        
          
           
 //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
        Map<String, Object> ab = (Map<String, Object>) groupes.get("groupe");
    


       //  for (Map<String, Object> obj : list) {
              
       nb= Integer.parseInt(ab.get("nb").toString());   
          


//etat=Integer.parseInt(obj.get("etat").toString());
  //           System.out.println(etat);

      
        //   }
         
           //   f.show();
        } catch (IOException ex) {
         }
     
   //   return listeGroupes;
     return nb;

    }
  public Form getF() {
        return f;
    }   
}
