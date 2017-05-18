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
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
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
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sarra
 */
public class ModifierGroupe {
    Abonnes abonnes=new Abonnes(); 
Form f;
       Command cmd1, cmd2,cmd4, cmdBack,cmd3;
       ImageViewer img;
     private ConnectionRequest connectionRequest;
     UIBuilder ui = new UIBuilder();
       Groupe groupe = new Groupe();
       User user=LogIn.e;
       Button btnAjouter = new Button("Ajouter");
      

Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
   public  ModifierGroupe(Resources theme,Groupe groupeSelectionnèe){
     f =new Form("Ajouter un sujet",BoxLayout.y());
     f.setUIID("log");
     groupe=groupeSelectionnèe;
   Toolbar.setGlobalToolbar(true);

         cmd1 = new Command("Mon profil");
        cmd2 = new Command("Mes alertes");
  cmd3 = new Command("Mes groupes");
        cmd4 = new Command("Page 4");
      
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
        f.getToolbar().addCommandToSideMenu("Mes invitations", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                MesInvitations mesInv = new MesInvitations(theme);
                mesInv.getF().show();
            }
        });
              f.getToolbar().addCommandToSideMenu("Tous les groupes", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeTousLesGroupes tsGroupes = new ListeTousLesGroupes(theme);
                tsGroupes.getF().show();
            }
        });
     
        TextField tfNom = new TextField(groupe.getNom(), "Nom du groupe");
        TextField tfDescription = new TextField(groupe.getDescription(), "Description du groupe");
    

        f.add(tfNom);
        f.add(tfDescription);
  
  Abonnes ab=new Abonnes();
        Button btnOk = new Button("Modifier le groupe ");
 Button btnAnnuler = new Button("Retourner au groupe");
        f.add(btnOk);
        f.add(btnAnnuler);
       //  ConnectionRequest con = new ConnectionRequest();
        

    
 
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
             
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/script_sarra/groupes/update.php?nom=" + tfNom.getText() + "&description=" + tfDescription.getText()+"&idGroupe="+groupe.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                            groupe.setDescription(tfDescription.getText());
                            groupe.setNom( tfNom.getText() );
                               AfficherGroupe afficherGroupe = new AfficherGroupe(theme,groupe);
         afficherGroupe.getF().show();
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });
             btnAnnuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               AfficherGroupe afficherGroupe = new AfficherGroupe(theme,groupe);
         afficherGroupe.getF().show();
            }
        });
   
    
        f.show();

 }
 public Abonnes getAbonnementByIdUserandIdGroupe(String json) {
       
     
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> abonnements = j.parseJSON(new CharArrayReader(json.toCharArray()));
        
          
           
 //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
        Map<String, Object> abonnement = (Map<String, Object>) abonnements.get("abonnees");
            System.out.println(abonnement);  
      //  System.out.println("gggg :"+Integer.parseInt(abonnement.get("id").toString()));
    // etat= Integer.parseInt(abonnement.get("etat").toString());   
       //  for (Map<String, Object> obj : list) {
              
     abonnes.setId(Integer.parseInt(abonnement.get("id").toString()));   
          


//etat=Integer.parseInt(obj.get("etat").toString());
  //           System.out.println(etat);
}
      
        //   }
         
           //   f.show();
         catch (IOException ex) {
         }
     
   //   return listeGroupes;
     return abonnes;

    }
  public Form getF() {
        return f;
    }   
}
