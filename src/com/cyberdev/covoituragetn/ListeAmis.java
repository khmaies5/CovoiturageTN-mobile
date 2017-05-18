/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entity.Groupe;
import entity.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cimope
 */
public class ListeAmis {

    Form f;
    EncodedImage encoded;
    private   ArrayList<User> contacts;
      Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

    public ListeAmis(Resources theme,Groupe gr) {

//        UIBuilder uib = new UIBuilder();
//        Container ctn = uib.createContainer(theme, "ListeAmis");
//        f = ctn.getComponentForm();
          f= new Form("Membres");
        f.setUIID("log");
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    //    try {
       //     encoded = EncodedImage.create("/load.png");
            // Pro only feature, uncomment if you have a pro subscription
            // Log.bindCrashProtection(true);
       // } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
      //  }
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
                  f.getToolbar().addCommandToRightBar("Back",null, new ActionListener() {
   @Override
            public void actionPerformed(ActionEvent evt) {
                AfficherGroupe affiGroupe = new AfficherGroupe(theme,gr);
         affiGroupe.getF().show();
            }});
                     ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/script_sarra/abonnement/SelectUserAbonneByIdGroupe.php?idGroupe=" + gr.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
              contacts=loadContacts(new String(con.getResponseData()));
          //      List<String> l = StringUtil.tokenize(listamis, "/");

                for (User u : contacts) {
               

                           
                            Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Label lbl1 = new Label(u.getNom() + " " + u.getPrenom());
                            Image imgServer = URLImage.createToStorage(placeholder, u.getNom(), "http://localhost/Covoiturage/web/FO/images/user/" + u.getPhoto_Profil());

                            Label image = new Label();
                            image.setIcon(imgServer);
                            ctn1.add(image);
                            ctn1.add(lbl1);
                            ctn2.add(ctn1);
                            ctn1.setLeadComponent(image);
                            image.addPointerPressedListener(x -> {

                       //  chatbox ct = new chatbox(theme, u,gr);
                         //   ct.getF().show();
                             
                                try {
                                    ChatForm11 ct = new ChatForm11(theme, gr,u);
                                     ct.getF().show();
                                } catch (IOException ex) {
                                  
                                }
                           
                          //      showChatForm(u,image);

                            });

                            f.add(ctn2);


                }

            }
        });
        NetworkManager.getInstance().addToQueue(con);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    private     ArrayList<User> loadContacts(String json) {
        // we sort the contacts by name which is pretty concise code thanks to Java 8 lambdas
      //  Display.getInstance().scheduleBackgroundTask(() -> {
           // contacts = data.getContacts();
             ArrayList<User> listUsers = new ArrayList<>(); 
        try {
            JSONParser j = new JSONParser();
             Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));    
           
            
      List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("user");
         System.out.println("list");
      System.out.println(list);
              for (Map<String, Object> obj : list) {
          User user =new User();
  user.setId(Integer.parseInt(obj.get("id").toString())); 
         user.setNom(obj.get("nom").toString()); 
          user.setPrenom(obj.get("prenom").toString()); 
          
             user.setGouvernorat(obj.get("gv").toString()); 
           user.setSexe(obj.get("sexe").toString());
          //        System.out.println(obj.get("photo").toString());
          if(obj.get("photo")=="")
          { user.setPhoto_Profil(null); }
                else
          { user.setPhoto_Profil(obj.get("photo").toString()); }
      
 listUsers.add(user);
     System.out.println("contact");
                  System.out.println(listUsers);

   //contactsContainer.addComponent(createContactComponent(user));
         }

            //   f.show();
        } catch (IOException ex) {
        }
        

     //           contactsContainer.removeComponent(ip);

                     
              
   return listUsers;
    }
}
