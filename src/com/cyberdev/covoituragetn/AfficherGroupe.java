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
public class AfficherGroupe {

    Form f;
    Command cmd1, cmd2, cmd4, cmdBack, cmd3;
    ImageViewer img;
    private ConnectionRequest connectionRequest;
    UIBuilder ui = new UIBuilder();
   Groupe groupe = new Groupe(); 
    User userConn = LogIn.e;
    Button btnAdhere = new Button("Adhèrer au groupe");
    Button btnEntrer = new Button("Accèder au groupe");
    Button btnReAdherer = new Button("Rèabonner au groupe");
Abonnes abonnement;
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

    public AfficherGroupe(Resources theme, Groupe groupeSelectionnèe) {
        groupe = groupeSelectionnèe;
        f = new Form("Groupe :" + groupe.getNom(), BoxLayout.y());

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
        Tabs tab = new Tabs();
        Container ctnLstSujets = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        f.getToolbar().addCommandToOverflowMenu("Ajouter  un sujet", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                AjoutSujet ajoutSujet = new AjoutSujet(theme, groupe);
                ajoutSujet.getF().showBack();
            }
        });
        f.getToolbar().addCommandToOverflowMenu("Inviter des membres", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                InviterMembres initer = new InviterMembres(theme, groupe);
                initer.getF().showBack();
            }
        });
        f.getToolbar().addCommandToOverflowMenu("Discussion des membres", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //                ChatForm1 chat = new ChatForm1(theme, groupe);
//                chat.getF().showBack();
ListeAmis chat = new ListeAmis(theme,groupe);
chat.getF().show();
            }
        });
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
     
        ConnectionRequest con2 = new ConnectionRequest();
        con2.setUrl("http://localhost/script_sarra/abonnement/SelectEtatAbonneByIdGroueAndUserConn.php?idGroupe=" + groupe.getId() + "&userConn=" + userConn.getId());
        con2.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (getRoleAbonnementbyIdGroupeandUser(new String(con2.getResponseData())) == 0) {
                    f.getToolbar().addCommandToOverflowMenu("Quitter le groupe", null, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                             ConnectionRequest con3 = new ConnectionRequest();
                            con3.setUrl("http://localhost/script_sarra/abonnement/desab.php?idAb=" + abonnement.getId());
                            con3.addResponseListener(new ActionListener<NetworkEvent>() {

                                @Override
                                public void actionPerformed(NetworkEvent evt) {
                                    byte[] data = (byte[]) evt.getMetaData();
                                    String s = new String(data);
                                    System.out.println(s);
                                    if (s.equals("success")) {
                                        Dialog.show("Confirmation", "desabonnment effectuè avec succèe", "Ok", null);
                                        ListeTousLesGroupes liste = new ListeTousLesGroupes(theme);
                                        liste.getF().show();
                                    } else {
                                        Dialog.show("Erreur", "erreur", "Ok", null);
                                    }

                                }
                            });
                            NetworkManager.getInstance().addToQueue(con3);
                        }
                    });
                } else {

                    f.getToolbar().addCommandToOverflowMenu("modifier le groupe", null, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ModifierGroupe modifGroupe = new ModifierGroupe(theme, groupe);
                            modifGroupe.getF().showBack();
                        }
                    });
                    f.getToolbar().addCommandToOverflowMenu("supprimer le groupe", null, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ConnectionRequest con3 = new ConnectionRequest();
                            con3.setUrl("http://localhost/script_sarra/groupes/delete.php?idGroupe=" + groupe.getId());
                            con3.addResponseListener(new ActionListener<NetworkEvent>() {

                                @Override
                                public void actionPerformed(NetworkEvent evt) {
                                    byte[] data = (byte[]) evt.getMetaData();
                                    String s = new String(data);
                                    System.out.println(s);
                                    if (s.equals("success")) {
                                        Dialog.show("Confirmation", "Suppression effectuè avec succèe", "Ok", null);
                                        ListeTousLesGroupes liste = new ListeTousLesGroupes(theme);
                                        liste.getF().show();
                                    } else {
                                        Dialog.show("Erreur", "erreur", "Ok", null);
                                    }

                                }
                            });
                            NetworkManager.getInstance().addToQueue(con3);

                        }
                    });

                }
            }
        });
        NetworkManager.getInstance().addToQueue(con2);
       ConnectionRequest con1 = new ConnectionRequest();
        con1.setUrl("http://localhost/script_sarra/sujet/CountSujetbyGroupe.php?idGroupe=" + groupe.getId());

        con1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                 int nb = getCountElement(new String(con1.getResponseData()));     
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/script_sarra/sujet/AffichageSujetbyGroupe.php?idGroupe=" + groupe.getId());

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                if(nb==0){    Label lbl4 = new Label("Aucun Sujet Disponible");
                    ctnLstSujets.add(lbl4);
}
                         if(nb==1)
                {   Sujet sujet = getSujet(new String(con.getResponseData()));
       
                  ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/script_sarra/abonnement/SelectUserByAbonnAndIDGroupe.php?idAb="+sujet.getCreator().getId()+"&idGroupe="+groupe.getId());

        cnx.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
           User userCreator=getUser(new String(cnx.getResponseData()));
                System.out.println(userCreator);
             

                        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        Label lbl1 = new Label(sujet.getObjet());
                        Label lbl2 = new Label("Par: "+userCreator.getPrenom()+" "+userCreator.getNom());
                        Label lbl3 = new Label(sujet.getDatePublication().toString());
Button btn=new Button("Plus de detail");
                        Label cadrephoto = new Label();
                        cadrephoto.setIcon(URLImage.createToStorage(placeholder, "default.jpg", "http://localhost/Covoiturage/web/images/sujets/default.jpg"));

                        ctn2.add(lbl1);
                        ctn2.add(lbl2);
                        ctn2.add(lbl3);
ctn2.add(btn);
                        ctn1.add(cadrephoto);
                        ctn1.add(ctn2);
                        ctn3.add(ctn1);

                        ctnLstSujets.add(ctn3);
                        // f.add(ctn3);
                                             btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            DetailSujet sujetDetail=new DetailSujet(theme, sujet, groupe,userCreator);
                                       sujetDetail.getF().show();
            }
        });
                }});
         NetworkManager.getInstance().addToQueue(cnx);
                    }
            
                else if(nb>1)
                {   ArrayList<Sujet> sujets = getListSujets(new String(con.getResponseData()));
               

                
               

                    for (Sujet sujet : sujets) {
                        System.out.println(sujet);
           ConnectionRequest cnx = new ConnectionRequest();
                        System.out.println(sujet.getCreator().getId());
        cnx.setUrl("http://localhost/script_sarra/abonnement/SelectUserByAbonnAndIDGroupe.php?idAb="+sujet.getCreator().getId()+"&idGroupe="+groupe.getId());

        cnx.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
           User userCreator=getUser(new String(cnx.getResponseData()));
                System.out.println(userCreator);
                        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
Button btn=new Button("Plus de detail");
                        Label lbl1 = new Label(sujet.getObjet());
                                Label lbl2 = new Label("Par: "+userCreator.getPrenom()+" "+userCreator.getNom());

                        Label lbl3 = new Label("crèe le : "+sujet.getDatePublication().toString());

                        Label cadrephoto = new Label();
                        cadrephoto.setIcon(URLImage.createToStorage(placeholder, "default.jpg", "http://localhost/Covoiturage/web/images/sujets/default.jpg"));

                        ctn2.add(lbl1);
                     ctn2.add(lbl2);
                        ctn2.add(lbl3);
ctn2.add(btn);
                        ctn1.add(cadrephoto);
                        ctn1.add(ctn2);
                        ctn3.add(ctn1);

                        ctnLstSujets.add(ctn3);
                                             btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
            DetailSujet sujetDetail=new DetailSujet(theme, sujet, groupe,userCreator);
                                       sujetDetail.getF().show();
            }
        });
                    
                    }});
        
 NetworkManager.getInstance().addToQueue(cnx);
                }
            }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
// tab.addTab("Sujets",URLImage.createToStorage(placeholder, "dossier2.png", "http://localhost/Covoiturage/web/FO/images/dossier2.png"),ctnLstSujets);
//tab.addTab("Inviter Membres",ctnInviter);
//tab.addTab("Discussion ",new Label("fcvgddbhnjk"));
    }});
      NetworkManager.getInstance().addToQueue(con1);
        f.add(ctnLstSujets);
        f.show();

    }

    public ArrayList<Sujet> getListSujets(String json) {

        ArrayList<Sujet> listeSujets = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) sujets.get("sujet");

            for (Map<String, Object> obj : list) {

                Sujet sujet = new Sujet();
                sujet.setId(Integer.parseInt(obj.get("id").toString()));
                sujet.setObjet(obj.get("objet").toString());
                sujet.setTopic(obj.get("topic").toString());

                sujet.setDatePublication(obj.get("datePublication").toString());
                sujet.setCreator(new Abonnes(Integer.parseInt(obj.get("idUser").toString())));
                sujet.setPathFiles(obj.get("image_name").toString());
                listeSujets.add(sujet);
            }

            //   f.show();
        } catch (IOException ex) {
        }

        return listeSujets;

    }

    public Sujet getSujet(String json) {

        Sujet sujet = new Sujet();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Map<String, Object> list = (Map<String, Object>) sujets.get("sujet");

            sujet.setId(Integer.parseInt(list.get("id").toString()));
            sujet.setObjet(list.get("objet").toString());
            sujet.setTopic(list.get("topic").toString());

            sujet.setDatePublication(list.get("datePublication").toString());
            sujet.setCreator(new Abonnes(Integer.parseInt(list.get("idUser").toString())));
            sujet.setPathFiles(list.get("image_name").toString());

            //   f.show();
        } catch (IOException ex) {
        }

        return sujet;

    }
     public User getUser(String json) {

        User u = new User();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Map<String, Object> list = (Map<String, Object>) user.get("user");
            System.out.println(list);
            u.setId(Integer.parseInt(list.get("id").toString()));
            u.setNom(list.get("nom").toString());
            u.setPrenom(list.get("prenom").toString());

          

            //   f.show();
        } catch (IOException ex) {
        }

        return u;

    }

    public int getRoleAbonnementbyIdGroupeandUser(String json) {

        int etat = -1;

        //Admin=1
        //simpleUser=0
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
            Map<String, Object> ab = (Map<String, Object>) groupes.get("abonnees");

            //  for (Map<String, Object> obj : list) {
            String role = ab.get("role").toString();
            abonnement=new Abonnes(Integer.parseInt(ab.get("id").toString()));
            if ("admin".equals(role)) {
                etat = 1;
            } else {
                etat = 0;
            }
            System.out.println(role);
            System.out.println(etat);
//etat=Integer.parseInt(obj.get("etat").toString());
            //           System.out.println(etat);
        } //   }
        //   f.show();
        catch (IOException ex) {
        }

        //   return listeGroupes;
        return etat;

    }

    public int getCountElement(String json) {

        int nb = -1;

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
            Map<String, Object> ab = (Map<String, Object>) sujets.get("sujet");

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

    public Form getF() {
        return f;
    }
}
