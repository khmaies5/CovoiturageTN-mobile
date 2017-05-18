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

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import entity.Abonnes;
import entity.Groupe;
import entity.Reponse;
import entity.Sujet;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author esprit
 */
public class DetailSujet {

    Form f;
Resources theme;
    Command cmd1, cmd2, cmd4, cmdBack, cmd3;
    private ImageViewer image1;
    UIBuilder ui = new UIBuilder();
   Sujet sujet=new Sujet();
   Abonnes abonnement=new Abonnes();
          Groupe groupe = new Groupe(); 
    User userConn = LogIn.e;
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);

    public DetailSujet(Resources theme, Sujet Selectionnèe, Groupe groupeSelectionnèe,User userCreator) {
           f  = new Form("Sujet Details", BoxLayout.y());

                        f.setUIID ("log");
  sujet=Selectionnèe;
          groupe = groupeSelectionnèe;
            Toolbar.setGlobalToolbar(true);
      f.getToolbar().addCommandToSideMenu("Mes groupes", null, (ActionListener) (ActionEvent evt) -> {
          MesGroupes mesGroupes = new MesGroupes(theme);
          mesGroupes.getF().show();
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
                AfficherGroupe affiGroupe = new AfficherGroupe(theme,groupe);
         affiGroupe.getF().show();
            }});
        /////////////////////Affichage de la liste Commentaires
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/script_sarra/abonnement/SelectAbonnementByUserConnAndIDGroupe.php?userConn=" + userConn.getId()+"&idGroupe="+groupe.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnement=new Abonnes(getAbonnementbyIdGroupeandUser(new String(con.getResponseData())));
            }
            });
         NetworkManager.getInstance().addToQueue(con);
             
                     f.revalidate();
       // image1  = new ImageViewer(theme.getImage("mah.jpg"));
                        TextField tfRep = new TextField("", "reponse");
                        Button btnOK = new Button("Rèpondre");

                        btnOK.setUIID ( "az");
                     //   btnOK.setIcon (theme.getImage ("chat.png"));
        Label labelStar = new Label();
                    //    Label annonceDetails = new Label("Sujet Details");

                   //     annonceDetails.setUIID ("annoncedetails");
        Label objet = new Label(sujet.getTopic());
                     
        Label comments = new Label("Reponses: ");
                        Label remp = new Label("Votre réponse:");
                            
                        objet.getStyle () .setPaddingBottom(150);
                        labelStar.getStyle ().setPaddingBottom(150);
                        comments.getStyle ().setPaddingBottom(70);
           //             annonceDetails.getStyle ().setPaddingTop(3);
         TextField tfComment = new TextField("", "Comment");
     
        btnOK.setUIID("az");
        //btnOK.setIcon(theme.getImage("chat.png"));
        Container ctn9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        SpanLabel lbl1 = new SpanLabel(sujet.getObjet());
                        SpanLabel lbl2 = new SpanLabel("Par: "+userCreator.getPrenom()+" "+userCreator.getNom());
                        Label lbl3 = new Label(sujet.getDatePublication().toString());

                        Label cadrephoto = new Label();
                        cadrephoto.setIcon(URLImage.createToStorage(placeholder, "default.jpg", "http://localhost/Covoiturage/web/images/sujets/default.jpg"));

                        ctn2.add(lbl1);
                        ctn2.add(lbl2);
                        ctn2.add(lbl3);

                        ctn1.add(cadrephoto);
                        ctn1.add(ctn2);
                        ctn3.add(ctn1);

                        Container ctn10 = new Container(new BoxLayout(BoxLayout.X_AXIS));
      

                        ctn9.add (ctn10);

           
                        ctn9.setUIID ("ctn3");
        
//                        f.add (FlowLayout.encloseCenter(annonceDetails));
//                        comment.add (image1);
f.add(ctn3);
                    //    f.add (objet);
                        //comment.add(FlowLayout.encloseCenter(vote));
                        //comment.add(FlowLayout.encloseCenter(createStarRankSlider()));
                        //comment.add(FlowLayout.encloseCenter(labelStar));

                        f.add (ctn9);

 f.add(remp);
        f.add(tfComment);
        f.add(FlowLayout.encloseCenter(btnOK));

                     //   f.add (tfRep);
//f.add(btnOK);
                    //    f.add (FlowLayout.encloseCenter (btnOK));
                        f.show ();
                        afficher();

                                        btnOK.addActionListener (new ActionListener() {
            @Override
                            public void actionPerformed
                            (ActionEvent evt
                            
                                ) {
                if ("".equals(tfComment.getText())) {
                                    Dialog.show("Alert", "Votre réponse est Vide", "ok", null);
                                } else {
                                    ConnectionRequest con = new ConnectionRequest();
                                    con.setUrl("http://localhost/script_sarra/reponse/insert.php?idSujet=" + sujet.getId()+ "&reponse=" + tfComment.getText() + "&idAbbUser=" +abonnement.getId());
                            
                                    NetworkManager.getInstance().addToQueue(con);
                                 afficher();
                                          TextField tfComment = new TextField("", "Comment");
                                DetailSujet detail =new DetailSujet(theme, sujet, groupe, userConn);
                                detail.getF().show();
                                }
                            }
                        }
                    );

    }
//////////Recuperation liste des Commentaires
    public void afficher()
    {
    
    
   ConnectionRequest con3 = new ConnectionRequest();

                        con3.setUrl ( "http://localhost/script_sarra/reponse/CountReponsebyIdSujet.php?idSujet=" + sujet.getId());
                        con3.addResponseListener (new ActionListener<NetworkEvent>() {
                                 @Override
                            public void actionPerformed(NetworkEvent evt ) {
       
int nb=getCountElement(new String(con3.getResponseData()));

        /////////////////////Affichage de la liste des reponses
        ConnectionRequest con2 = new ConnectionRequest();

                        con2.setUrl ( "http://localhost/script_sarra/reponse/ListReponseByIdSujet.php?idSujet=" + sujet.getId());
                        con2.addResponseListener (new ActionListener<NetworkEvent>() {
                                 @Override
                            public void actionPerformed(NetworkEvent evt ) {
                                UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
                                            
                                              System.out.println("nb");
                                            System.out.println(nb);
                                if(nb==0)
                                {
                                  Label lbl1 = new Label("aucune rèponse trouvèe");
                                      
                                       //     ctn2.add(lbl1);
                                          
                                         //   ctn3.add(ctn1);
                                         f.add(FlowLayout.encloseCenter(lbl1));
                                
                                }
if(nb>1)
{  
    ArrayList<Reponse> listeReponse = getListReponses(new String(con2.getResponseData()));

                                for (Reponse rp : listeReponse) {
                                    ConnectionRequest con5 = new ConnectionRequest();
                                    con5.setUrl("http://localhost/script_sarra/abonnement/SelectUserByAbonnAndIDGroupe.php?idAb=" + rp.getCreator().getId()+"&idGroupe="+groupe.getId());
                                    con5.addResponseListener(new ActionListener<NetworkEvent>() {

                                        @Override
                                        public void actionPerformed(NetworkEvent evt) {
                                            User userRep = getUserbyId(new String(con5.getResponseData()));
                                             Container ctn6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                                 Container ctn7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                            Label lbl1 = new Label(userRep.getNom());
                                            Label lbl2 = new Label(rp.getReponse_sujet());
                                            Label cadrephoto = new Label();
                                            String lien = "http://localhost/Covoiturage2/Covoiturage/web/user/" + userRep.getPhoto_Profil();
                                            cadrephoto.setIcon(URLImage.createToStorage(placeholder, userRep.getPhoto_Profil(), lien));
                                         ctn7.add(lbl1);
                                            ctn7.add(lbl2);
                                            ctn6.add(cadrephoto);
                                            ctn6.add(ctn7);
                                            ctn8.add(ctn6);
                                      
                                            f.add(ctn8);

////////////////////////////////////Suprimer Modifier
                         
                                        lbl2.addPointerReleasedListener (new ActionListener() {
                                            @Override
                            public void actionPerformed
                            (ActionEvent evt) {
                            
                                 System.out.println("ctn8 cl");
                                                        System.out.println("userR");
                                                            System.out.println(userRep.getId());
                                                                  System.out.println("userc");
                                                            System.out.println(userConn.getId());
                                                    if (userRep.getId()== userConn.getId()) {
                                                        boolean a = Dialog.show("Reponse: " + userRep.getNom(), rp.getReponse_sujet(), "Modifier", "Supprimer");

                                                        if (a == true) {///////////modifier
                                                            Form f2 = new Form("Modifier la réponse", BoxLayout.y());
                                                            f2.setUIID("formModif");
                                                            TextField ModReponse = new TextField();
                                                            Button btn = new Button();
                                                            btn.setUIID("aze");
                                                            Label label = new Label("Veillez Modifier Votre réponse:");
                                                           // btn.setIcon(theme.getImage("writing.png"));
                                                            f2.add(label);
                                                            f2.add(ModReponse);
                                                            f2.add(FlowLayout.encloseCenter(btn));
                                                            f2.show();
                                                            f2.getToolbar().addCommandToRightBar("back", null, new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent evt) {
                                                                    f.showBack();
                                                                }
                                                            });
                                                            btn.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent evt) {
                                                                   ConnectionRequest conn = new ConnectionRequest();
   conn.setUrl("http://localhost/script_sarra/reponse/update.php?idReponse=" +rp.getId()+ "&text=" +rp.getReponse_sujet());//                                                                    NetworkManager.getInstance().addToQueue(conn);
                                                                   f.showBack();
//                                                                    MyCommentaire mc = new MyCommentaire(theme);
//                                                                    mc.getComment().show();
                                                               }
                                                          });

                                                        } else if (a == false) {/////////////supprimer
                                                            ConnectionRequest conn = new ConnectionRequest();
  conn.setUrl("http://localhost/script_sarra/reponse/delete.php?idReponse=" + rp.getId());                                                            NetworkManager.getInstance().addToQueue(conn);
    DetailSujet detail =new DetailSujet(theme, sujet, groupe, userConn);
                                detail.getF().show();
                                                        }
                                                    } else {
                                                        Dialog.show("Alert", "Vous ne pouvez pas supprimer ou mettre à jour la commentaire de  " + userRep.getNom(), "Annuler", null);
                                                    }
                            
                            }           });
                                                                              ctn8.setLabelForComponent(lbl2);
                                        }
                                    });
                                    NetworkManager.getInstance().addToQueue(con5);
                                }
                            } 
else if(nb==1){

Reponse reponse = getReponse(new String(con2.getResponseData()));

                              
                                    ConnectionRequest con4 = new ConnectionRequest();
                                    con4.setUrl("http://localhost/script_sarra/abonnement/SelectUserByAbonnAndIDGroupe.php?idAb=" + reponse.getCreator().getId()+"&idGroupe="+groupe.getId());
                                    con4.addResponseListener(new ActionListener<NetworkEvent>() {

                                        @Override
                                        public void actionPerformed(NetworkEvent evt) {
                                            User userRep = getUserbyId(new String(con4.getResponseData()));
                                              Container ctn6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                                                 Container ctn7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                            Label lbl1 = new Label(userRep.getNom());
                                            Label lbl2 = new Label(reponse.getReponse_sujet());
                                            Label cadrephoto = new Label();
                                            String lien = "http://localhost/Covoiturage/web/FO/images/user/" + userRep.getPhoto_Profil();
                                            cadrephoto.setIcon(URLImage.createToStorage(placeholder, userRep.getPhoto_Profil(), lien));
                                                ctn7.add(lbl1);
                                            ctn7.add(lbl2);
                                            ctn6.add(cadrephoto);
                                            ctn6.add(ctn7);
                                            ctn8.add(ctn6);
                                           
                                            f.add(ctn8);

////////////////////////////////////Suprimer Modifier
                                          lbl2.addPointerReleasedListener (new ActionListener() {
                                            @Override
                            public void actionPerformed
                            (ActionEvent evt) {
                            
                                 System.out.println("ctn8 cl");
                                                        System.out.println("userR");
                                                            System.out.println(userRep.getId());
                                                                  System.out.println("userc");
                                                            System.out.println(userConn.getId());
                                                    if (userRep.getId()== userConn.getId()) {
                                                        boolean a = Dialog.show("Reponse: " + userRep.getNom(), reponse.getReponse_sujet(), "Modifier", "Supprimer");

                                                        if (a == true) {///////////modifier
                                                            Form f2 = new Form("Modifier la réponse", BoxLayout.y());
                                                            f2.setUIID("formModif");
                                                            TextField ModReponse = new TextField();
                                                            Button btn = new Button();
                                                            btn.setUIID("aze");
                                                            Label label = new Label("Veillez Modifier Votre réponse:");
                                                           // btn.setIcon(theme.getImage("writing.png"));
                                                            f2.add(label);
                                                            f2.add(ModReponse);
                                                            f2.add(FlowLayout.encloseCenter(btn));
                                                            f2.show();
                                                            f2.getToolbar().addCommandToRightBar("back", null, new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent evt) {
                                                                    f.showBack();
                                                                }
                                                            });
                                                            btn.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent evt) {
                                                                   ConnectionRequest conn = new ConnectionRequest();
   conn.setUrl("http://localhost/script_sarra/reponse/update.php?idReponse=" +reponse.getId()+ "&text=" +reponse.getReponse_sujet());//                                                                    NetworkManager.getInstance().addToQueue(conn);
                                                                   f.showBack();
//                                                                    MyCommentaire mc = new MyCommentaire(theme);
//                                                                    mc.getComment().show();
                                                               }
                                                          });

                                                        } else if (a == false) {/////////////supprimer
                                                            ConnectionRequest conn = new ConnectionRequest();
  conn.setUrl("http://localhost/script_sarra/reponse/delete.php?idReponse=" + reponse.getId());                                                            NetworkManager.getInstance().addToQueue(conn);
                                                 DetailSujet detail =new DetailSujet(theme, sujet, groupe, userConn);
                                detail.getF().show();
                                                        }
                                                    } else {
                                                        Dialog.show("Alert", "Vous ne pouvez pas supprimer ou mettre à jour la commentaire de  " + userRep.getNom(), "Annuler", null);
                                                    }
                            
                            }       });
                                          ctn8.setLabelForComponent(lbl2);
                                        }
                                    });
                                 
                              
     NetworkManager.getInstance().addToQueue(con4);   

}   
                            
                            }
                        }

                        );
                      NetworkManager.getInstance().addToQueue(con2);   

        ///////////////////Insertion de Commentaire
        
                }});
                          NetworkManager.getInstance ().addToQueue(con3);
    }
            public ArrayList<Reponse> getListReponses(String json) {

                ArrayList<Reponse> listeReponses = new ArrayList<>();

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) sujets.get("reponse");

                    for (Map<String, Object> obj : list) {

                        Reponse rep = new Reponse();
                        rep.setId(Integer.parseInt(obj.get("id").toString()));
                        rep.setReponse_sujet(obj.get("reponse_sujet").toString());
                        rep.setCreator(new Abonnes(Integer.parseInt(obj.get("id_userAB").toString())));
                        rep.setDate_reponse(obj.get("datePublication").toString());

                        //    rep.setDatePublication(obj.get("datePublication").toString());
                        // rep.setCreator(new Abonnes(Integer.parseInt(obj.get("idUser").toString())));
                        // rep.setPathFiles(obj.get("image_name").toString());
                        listeReponses.add(rep);
                    }

                    //   f.show();
                } catch (IOException ex) {
                }

                return listeReponses;

            }
  public Reponse getReponse(String json) {

               Reponse  r = new Reponse();

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));

                   Map<String, Object> list = (Map<String, Object>) sujets.get("reponse");

                 

                     
                        r.setId(Integer.parseInt(list.get("id").toString()));
                        r.setReponse_sujet(list.get("reponse_sujet").toString());
                        r.setCreator(new Abonnes(Integer.parseInt(list.get("id_userAB").toString())));
                        r.setDate_reponse(list.get("datePublication").toString());

                        //    rep.setDatePublication(obj.get("datePublication").toString());
                        // rep.setCreator(new Abonnes(Integer.parseInt(obj.get("idUser").toString())));
                        // rep.setPathFiles(obj.get("image_name").toString());
                    

                    //   f.show();
                } catch (IOException ex) {
                }

                return r;

            }
///////Recuperation de user
            public User getUserbyId(String json) {
                User user2 = null;

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    Map<String, Object> list = (Map<String, Object>) users.get("user");
                    System.out.println("listtttttttttttttttttttttttttt");     
                    System.out.println(list);
                    user2 = new User();
                 user2.setId(Integer.parseInt(list.get("id").toString()));
                    user2.setNom(list.get("nom").toString());
                    user2.setPhoto_Profil(list.get("photoProfil").toString());

                } catch (IOException ex) {
                }
                return user2;
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

            public Form getF() {
                return f;
            }

            public int getCountElement(String json) {

                int nb = -1;

                try {
                    JSONParser j = new JSONParser();
                    Map<String, Object> sujets = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
                    Map<String, Object> ab = (Map<String, Object>) sujets.get("reponse");

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
    public int getAbonnementbyIdGroupeandUser(String json) {

        int id = 0;

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
            Map<String, Object> ab = (Map<String, Object>) groupes.get("abonnees");

          

                id = Integer.parseInt(ab.get("id").toString());

                

//etat=Integer.parseInt(obj.get("etat").toString());
                //           System.out.println(etat);
            

            //   }
            //   f.show();
        } catch (IOException ex) {
        }

        //   return listeGroupes;
        return id;

    }
        }
