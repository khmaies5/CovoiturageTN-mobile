/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entity.Abonnes;
import entity.Groupe;
import entity.Sujet;
import entity.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;





/**
 *
 * @author esprit
 */
public class AfficherGroupe1 {
    
 Form f;
       Command cmd1, cmd2,cmd4, cmdBack,cmd3;
       ImageViewer img;
     private ConnectionRequest connectionRequest;
     UIBuilder ui = new UIBuilder();
       Groupe groupe = new Groupe();
       User user=LogIn.e;
  

Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
   public  AfficherGroupe1(Resources theme,Groupe groupeSelectionnèe){
       groupe=groupeSelectionnèe;
       f =new Form("Groupe :"+groupe.getNom(),BoxLayout.y());
  
   Toolbar.setGlobalToolbar(true);

        cmdBack = new Command("Back");
   Tabs tab=new Tabs();
Container ctnLstSujets= new Container(new BoxLayout(BoxLayout.Y_AXIS));
Container ctnInviter= new Container(new BoxLayout(BoxLayout.Y_AXIS));

         f.getToolbar().addCommandToSideMenu("Mes groupes", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           MesGroupes mesGroupes = new MesGroupes(theme);
           mesGroupes.getF().show();
            }
        });
     tab.addSelectionListener((i1, i2) -> {
       //  System.out.println(i1);
             System.out.println(i2);
    switch(i2) {
        case 0:
            ctnLstSujets.removeAll();
               ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/script_sarra/sujet/AffichageSujetbyGroupe.php?idGroupe="+groupe.getId());
   
      
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
           ArrayList<Sujet> sujets=getListSujets(new String(con.getResponseData()));
                System.out.println(sujets);
if(sujets==null){  
 
       Label lbl4 = new Label("Aucun Sujet Disponible");
       ctnLstSujets.add(lbl4);
       
     }
else{
   
    for(Sujet sujet : sujets ){
        
                                      
                            
                                  
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                            
       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 
       Label lbl1 = new Label(sujet.getObjet());
       Label lbl2 = new Label(sujet.getTopic());
//       Label lbl3=new Label(sujet.getDatePublication().toString());
             
   Label cadrephoto=new Label();
 cadrephoto.setIcon(URLImage.createToStorage(placeholder, "default.jpg", "http://localhost/Covoiturage/web/images/sujets/default.jpg"));

       ctn2.add(lbl1);
       ctn2.add(lbl2);
//   ctn2.add(btn);
   
      ctn1.add(cadrephoto);
       ctn1.add(ctn2);
       ctn3.add(ctn1);

ctnLstSujets.add(ctn3);
      // f.add(ctn3);
          ctn3.addPointerReleasedListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent evt) {
                               
                                          System.out.println(sujet.getId());
                                     }
                                 }); 


    }

  f.getToolbar().addCommandToOverflowMenu("Ajouter  un sujet", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
        AjoutSujet ajoutSujet =  new AjoutSujet(theme,groupe);
        ajoutSujet.getF().showBack();
         }
     });
    
}
   

            }
        });
        NetworkManager.getInstance().addToQueue(con);
            break;
        case 1:
          ctnLstSujets.refreshTheme();
            break;
     }
});
//if(tab.getSelectedComponent()==ctnLstSujets)
//{  
//    ConnectionRequest con = new ConnectionRequest();
//       con.setUrl("http://localhost/script/sujet/AffichageSujetbyGroupe.php?idGroupe="+groupe.getId());
//   
//      
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//           ArrayList<Sujet> sujets=getListSujets(new String(con.getResponseData()));
//                System.out.println(sujets);
//if(sujets==null){  
// 
//       Label lbl4 = new Label("Aucun Sujet Disponible");
//       ctnLstSujets.add(lbl4);
//       
//     }
//else{
//   
//    for(Sujet sujet : sujets ){
//        
//                                      
//                            
//                                  
//        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
//
//                            
//       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
// 
//       Label lbl1 = new Label(sujet.getObjet());
//       Label lbl2 = new Label(sujet.getTopic());
//       Label lbl3=new Label(sujet.getDatePublication().toString());
//             
//   Label cadrephoto=new Label();
// cadrephoto.setIcon(URLImage.createToStorage(placeholder, "default.jpg", "http://localhost/Covoiturage/web/images/sujets/default.jpg"));
//
//       ctn2.add(lbl1);
//       ctn2.add(lbl2);
////   ctn2.add(btn);
//   
//      ctn1.add(cadrephoto);
//       ctn1.add(ctn2);
//       ctn3.add(ctn1);
//
//ctnLstSujets.add(ctn3);
//      // f.add(ctn3);
//          ctn3.addPointerReleasedListener(new ActionListener() {
//                                     @Override
//                                     public void actionPerformed(ActionEvent evt) {
//                               
//                                          System.out.println(sujet.getId());
//                                     }
//                                 }); 
//
//
//    }
//
//  f.getToolbar().addCommandToOverflowMenu("Ajouter  un sujet", null, new ActionListener() {
//
//         @Override
//         public void actionPerformed(ActionEvent evt) {
//        AjoutSujet ajoutSujet =  new AjoutSujet(theme,groupe);
//        ajoutSujet.getF().showBack();
//         }
//     });
//    
//}
//   
//
//            }
//        });
//        NetworkManager.getInstance().addToQueue(con);
   //}
 tab.addTab("Sujets",URLImage.createToStorage(placeholder, "dossier2.png", "http://localhost/Covoiturage/web/FO/images/dossier2.png"),ctnLstSujets);
tab.addTab("Inviter Membres",ctnInviter);
tab.addTab("Discussion ",new Label("fcvgddbhnjk"));
f.add(tab);
 f.show();

 }
     public ArrayList<Sujet> getListSujets(String json) {
        
        ArrayList<Sujet> listeSujets = new ArrayList<>();
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));
 
 List<Map<String, Object>> list = (List<Map<String, Object>>) sujets.get("sujet");
if(list==null){return listeSujets; }
else{     for (Map<String, Object> obj : list) {
              
              Sujet sujet=new Sujet();
 sujet.setId(Integer.parseInt(obj.get("id").toString()));         
sujet.setObjet(obj.get("objet").toString());
sujet.setTopic(obj.get("topic").toString());
   
//sujet.setDatePublication(obj.get("datePublication").toString());
sujet.setCreator(new Abonnes(new User(Integer.parseInt(obj.get("idUser").toString()))));
sujet.setPathFiles(obj.get("image_name").toString());
 listeSujets.add(sujet);
}
         }
           //   f.show();
        } catch (IOException ex) {
         }
     
      return listeSujets;
     

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
  public Form getF() {
        return f;
    }   
}
