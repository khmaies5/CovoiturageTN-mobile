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
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entity.Groupe;
import entity.Invitation;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author esprit
 */
public class MesInvitations {

    Form f;
    Command cmd1, cmd2, cmd4, cmdBack, cmd3;
    private ConnectionRequest connectionRequest;
    UIBuilder ui = new UIBuilder();
    Groupe groupe = new Groupe();
    User user = LogIn.e;
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

    public MesInvitations(Resources theme) {

        // f= ui.createContainer(theme, "Home").getComponentForm();
        f = new Form("Mes Invitation", BoxLayout.y());
        f.setUIID("log");
        //   Label lNom =(Label) ui.findByName("Label", f);

        //lNom.setText("Welcome " +nom);
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
        cmd1 = new Command("Mon profil");
        cmd2 = new Command("Mes alertes");
        cmd3 = new Command("Mes groupes");
        cmd4 = new Command("Page 4");
        cmdBack = new Command("Back");
        f.getToolbar().addCommandToSideMenu(cmd1);
        f.getToolbar().addCommandToSideMenu(cmd2);
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
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
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/script_sarra/invitation/CountMyInvitations.php?userConn=" + user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
            int nb = getCountElement(new String(con.getResponseData()));     
        ConnectionRequest con1 = new ConnectionRequest();
        con1.setUrl("http://localhost/script_sarra/invitation/SelectInvitationsByUserConn.php?userConn=" + user.getId());
        con1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (nb == 0) {
                    Label lbl1 = new Label("aucune invitation recue");

                    ctn2.add(lbl1);
                    f.add(ctn2);
                } else if(nb>1){

                    ArrayList<Invitation> invitations = getListInvitations(new String(con1.getResponseData()));

                    for (Invitation in : invitations) {
                        ConnectionRequest con2 = new ConnectionRequest();
                        con2.setUrl("http://localhost/script_sarra/groupes/SelectGroupeById.php?idGroupe=" + in.getGroupe().getId());
                        con2.addResponseListener(new ActionListener<NetworkEvent>() {

                            @Override
                            public void actionPerformed(NetworkEvent evt) {

                                Groupe gr = getListGroupesByAbonnements(new String(con2.getResponseData()));
                                UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                                Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                Label lbl1 = new Label(gr.getNom());
                                Label lbl2 = new Label(gr.getDescription());
                                final Label cadrephoto = new Label();
                                cadrephoto.setIcon(URLImage.createToStorage(placeholder, "ddddd", "http://localhost/Covoiturage/web/images/groupes/default3.jpg"));

                                ctn2.add(lbl1);
                                ctn2.add(lbl2);
                                //  try {
                                //     ctn1.add( new ImageViewer(Image.createImage("localhost/Covoiturage/web/images/groupes/default3.jpg"))); // Logger.getLogger(GroupesView1.class.getName()).log(Level.SEVERE, null, ex);
                                // } catch (IOException ex) {
                                //    System.out.println("erreur");
                                //  }

                                ctn1.add(cadrephoto);
                                ctn1.add(ctn2);
                                ctn3.add(ctn1);
                                ctn3.setTactileTouch(true);
                                ctn3.setFocusable(true);

//     ctn3.add(img);
                                f.add(ctn3);
                            }

                        });
                        NetworkManager.getInstance().addToQueue(con2);

                    }

                }
            else {

                   Invitation invitation = getInvitation(new String(con1.getResponseData()));

                 
                        ConnectionRequest con2 = new ConnectionRequest();
                        con2.setUrl("http://localhost/script_sarra/groupes/SelectGroupeById.php?idGroupe=" + invitation.getGroupe().getId());
                        con2.addResponseListener(new ActionListener<NetworkEvent>() {

                            @Override
                            public void actionPerformed(NetworkEvent evt) {

                                Groupe gr = getListGroupesByAbonnements(new String(con2.getResponseData()));
                                UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                                Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                Label lbl1 = new Label(gr.getNom());
                                Label lbl2 = new Label(gr.getDescription());
                                final Label cadrephoto = new Label();
                                cadrephoto.setIcon(URLImage.createToStorage(placeholder, "ddddd", "http://localhost/Covoiturage/web/images/groupes/default3.jpg"));

                                ctn2.add(lbl1);
                                ctn2.add(lbl2);
                                //  try {
                                //     ctn1.add( new ImageViewer(Image.createImage("localhost/Covoiturage/web/images/groupes/default3.jpg"))); // Logger.getLogger(GroupesView1.class.getName()).log(Level.SEVERE, null, ex);
                                // } catch (IOException ex) {
                                //    System.out.println("erreur");
                                //  }

                                ctn1.add(cadrephoto);
                                ctn1.add(ctn2);
                                ctn3.add(ctn1);
                                ctn3.setTactileTouch(true);
                                ctn3.setFocusable(true);
//      ctn3.addCommandToOverflowMenu("Ajouter  un sujet", null, new ActionListener() {
//
//         @Override
//         public void actionPerformed(ActionEvent evt) {
//        AjoutSujet ajoutSujet =  new AjoutSujet(theme,groupe);
//        ajoutSujet.getF().showBack();
//         }
//     });
//     ctn3.add(img);
                                f.add(ctn3);
                            }

                        });
                        NetworkManager.getInstance().addToQueue(con2);

                    

                }
            
            
            
            }    });
            

            NetworkManager.getInstance ().addToQueue(con1);


                   
            }});
     NetworkManager.getInstance ().addToQueue(con);
 //ctn1.add(ctn2);
//ctn3.add(ctn1);
//ctn3.setTactileTouch(true);
//ctn3.setFocusable(true);
 // f.add(ctn3);
            f.show ();
        }

    public Form getF() {
        return f;
    }

    public ArrayList<Invitation> getListInvitations(String json) {

        ArrayList<Invitation> listInv = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> invittions = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //  List<Map<String, Object>> list = (List<Map<String, Object>>) invittions.get("invitation");
            List<Map<String, Object>> list = (List<Map<String, Object>>) invittions.get("invitation");

            for (Map<String, Object> obj : list) {

                Invitation in = new Invitation();
                in.setId(Integer.parseInt(obj.get("id").toString()));
                in.setGroupe(new Groupe(Integer.parseInt(obj.get("idGroupe").toString())));
            in.setCreator(new User(Integer.parseInt(obj.get("idUserCreator").toString())));
                //  abonnes.setDateAbonnement((Date) obj.get("date"));
                listInv.add(in);

            }

        } catch (IOException ex) {
        }

        return listInv;

    }

    public Invitation getInvitation(String json) {

        Invitation in = new Invitation();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> invittions = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //  List<Map<String, Object>> list = (List<Map<String, Object>>) invittions.get("invitation");
            Map<String, Object> list = (Map<String, Object>) invittions.get("invitation");

            in.setId(Integer.parseInt(list.get("id").toString()));
            in.setGroupe(new Groupe(Integer.parseInt(list.get("idGroupe").toString())));
            in.setCreator(new User(Integer.parseInt(list.get("idUserCreator").toString())));
            //  abonnes.setDateAbonnement((Date) obj.get("date"));

        } catch (IOException ex) {
        }

        return in;

    }

    public Groupe getListGroupesByAbonnements(String json) {
        Groupe groupe2 = null;

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Map<String, Object> list = (Map<String, Object>) groupes.get("groupe");
            System.out.println(list);
            groupe2 = new Groupe();
            System.out.println(list.get("id").toString() + "");
            groupe2.setId(Integer.parseInt(list.get("id").toString()));
            groupe2.setNom(list.get("nom").toString());
            groupe2.setDescription(list.get("description").toString());
            //groupe.setDateCreation((Date) list.get("dateCreation"));
            //listeGroupes.add(groupe);

            System.out.println(groupe);

        } catch (IOException ex) {
        }

        return groupe2;

    }

    public int getCountElement(String json) {

        int nb = -1;

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
            Map<String, Object> ab = (Map<String, Object>) groupes.get("invitation");

            //  for (Map<String, Object> obj : list) {
            nb = Integer.parseInt(ab.get("nb").toString());

            System.out.println(nb);
            //   }

            //   f.show();
        } catch (IOException ex) {
        }

        //   return listeGroupes;
        return nb;

    }
}
