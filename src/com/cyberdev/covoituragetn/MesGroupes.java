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
import com.codename1.ui.Dialog;
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
import entity.Abonnes;
import entity.Groupe;
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
public class MesGroupes {

    Form f;
    Command cmd1, cmd2, cmd4, cmdBack, cmd3;
    private ConnectionRequest connectionRequest;
    UIBuilder ui = new UIBuilder();
    Groupe groupe = new Groupe();
    User user = LogIn.e;
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

    public MesGroupes(Resources theme) {

        // f= ui.createContainer(theme, "Home").getComponentForm();
        f = new Form("Mes Groupes", BoxLayout.y());
        f.setUIID("log");
        
        //   Label lNom =(Label) ui.findByName("Label", f);

        //lNom.setText("Welcome " +nom);
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription

      
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctn3.setUIID("ctn");
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

        ConnectionRequest con4 = new ConnectionRequest();
        con4.setUrl("http://localhost/script_sarra/groupes/CountMyGroupes.php?idUserConn=" + user.getId());

        con4.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                int nb = getCountElement(new String(con4.getResponseData()));
                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://localhost/script_sarra/groupes/SelectAbonnementByUserConn.php?userConn=" + user.getId());
                con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        if (nb == 0) {

                            Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                            Label lbl1 = new Label("aucun groupe disponible");

                            ctn2.add(lbl1);

                            ctn1.add(ctn2);
                            ctn3.add(ctn1);

                            f.add(ctn3);

                        } else if (nb > 1) {
                            ArrayList<Abonnes> abonnements = getListAbonnements(new String(con.getResponseData()));

                            for (Abonnes Ab : abonnements) {
                                ConnectionRequest con2 = new ConnectionRequest();
                                con2.setUrl("http://localhost/script_sarra/groupes/SelectGroupeById.php?idGroupe=" + Ab.getIdgroupe());
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
                                        
                                    ctn3.addPointerReleasedListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                         
                                                boolean a = Dialog.show("Groupe :" + " " + gr.getNom(), "Vous voulez accèder au groupe", "Refuser", "Accepter");
                                                if (a == false) {
                                                    AfficherGroupe afficherGroupe = new AfficherGroupe(theme, gr);
                                                    afficherGroupe.getF().show();
                                                }
                                            

                                        }
                                    });
                                    }

                                });
                                NetworkManager.getInstance().addToQueue(con2);

                            }
                        } else {
                            Abonnes abonnement = getAbonnement(new String(con.getResponseData()));

                            System.out.println(abonnement.getIdgroupe());
                            ConnectionRequest con2 = new ConnectionRequest();
                            con2.setUrl("http://localhost/script_sarra/groupes/SelectGroupeById.php?idGroupe=" + abonnement.getIdgroupe());
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
                               
//     ctn3.add(img);
                                    f.add(ctn3);
                                    
                                    lbl1.addPointerReleasedListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                         
                                                boolean a = Dialog.show("Groupe :" + " " + gr.getNom(), "Vous voulez accèder au groupe", "Refuser", "Accepter");
                                                if (a == false) {
                                                    AfficherGroupe afficherGroupe = new AfficherGroupe(theme, gr);
                                                    afficherGroupe.getF().show();
                                                }
                                            

                                        }
                                    });
                                    ctn3.setLeadComponent(lbl1);
                                }

                            });
                            NetworkManager.getInstance().addToQueue(con2);

                        }
                    }

                });
                NetworkManager.getInstance().addToQueue(con);

            }
        });
        NetworkManager.getInstance().addToQueue(con4);

        f.show();
    }

    public Form getF() {
        return f;
    }

    public ArrayList<Abonnes> getListAbonnements(String json) {

        ArrayList<Abonnes> listeAbonnements = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> abonnements = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) abonnements.get("abonnees");

            for (Map<String, Object> obj : list) {

                Abonnes abonnes = new Abonnes();
                abonnes.setId(Integer.parseInt(obj.get("id").toString()));
                abonnes.setIdgroupe(Integer.parseInt(obj.get("id_groupe").toString()));
                //  abonnes.setDateAbonnement((Date) obj.get("date"));
                listeAbonnements.add(abonnes);

            }

        } catch (IOException ex) {
        }

        return listeAbonnements;

    }

    public Abonnes getAbonnement(String json) {

        Abonnes abonnes = new Abonnes();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> abonnements = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Map<String, Object> list = (Map<String, Object>) abonnements.get("abonnees");

            abonnes.setId(Integer.parseInt(list.get("id").toString()));
            abonnes.setIdgroupe(Integer.parseInt(list.get("id_groupe").toString()));
            //  abonnes.setDateAbonnement((Date) obj.get("date"));

        } catch (IOException ex) {
        }

        return abonnes;

    }

    public Groupe getListGroupesByAbonnements(String json) {
        Groupe gr = new Groupe();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Map<String, Object> list = (Map<String, Object>) groupes.get("groupe");

            System.out.println(list);

     
                   gr.setId(Integer.parseInt(list.get("id").toString()));
            gr.setNom(list.get("nom").toString());
            gr.setDescription(list.get("description").toString());
  gr.setDateCreation(list.get("datecreation").toString());
gr.setPathImage(list.get("pathFichier").toString());
gr.setImageName(list.get("image_name").toString());

        } catch (IOException ex) {
        }

        return gr;

    }

    public int getCountElement(String json) {

        int nb = -1;

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
            Map<String, Object> ab = (Map<String, Object>) groupes.get("abonnees");

            //  for (Map<String, Object> obj : list) {
            nb = Integer.parseInt(ab.get("nb").toString());

//etat=Integer.parseInt(obj.get("etat").toString());
            //           System.out.println(etat);
            System.out.println(nb);
            //   }

            //   f.show();
        } catch (IOException ex) {
        }

        //   return listeGroupes;
        return nb;

    }
}
