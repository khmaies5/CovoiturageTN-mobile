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
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entity.Annonce;
import entity.Commentaire;
import entity.Rate;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GUI builder created Container
 *
 * @author Momo
 */
public class AnnonceCommentaireRate extends com.codename1.ui.Container {

    int etat = 0;
    Form comment;
    float ff;
    Slider slider = new Slider();
    Resources theme2;
    Label labelStar = new Label();
    Command cmd1, cmd2, cmd4, cmdBack, cmd3;
    private ImageViewer image1;
    UIBuilder ui = new UIBuilder();
    Annonce ann1 = new Annonce();
//    User user = MyApplication.getE();
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);
    Container ctn10 = new Container(new BoxLayout(BoxLayout.X_AXIS));
    Container ctn3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

    public AnnonceCommentaireRate(Annonce ann) {
        this(com.codename1.ui.util.Resources.getGlobalResources(), ann);

    }

    public AnnonceCommentaireRate(com.codename1.ui.util.Resources resourceObjectInstance, Annonce ann3) {
        initGuiBuilderComponents(resourceObjectInstance);
        ann1 = ann3;
        theme2 = resourceObjectInstance;
        System.out.println("annonce id jjj " + ann1.getIdAnnonce());
        //theme2 = UIManager.initFirstTheme("/theme");
        //comment = new Form("Annonce Details", BoxLayout.y());
        //comment.setUIID("formComment");
        //image1 = new ImageViewer(theme.getImage("mah.jpg"));
        TextField tfComment = new TextField("", "Comment");
        Button btnOK = new Button();
        btnOK.setUIID("az");
        btnOK.setIcon(resourceObjectInstance.getImage("chat.png"));
        //Label annonceDetails = new Label("Annonce Details");
        //annonceDetails.setUIID("annoncedetails");
        //Label direction = new Label("Direction: Tunis Vers Mahdia");
        //Label vote = new Label("Rate de cette publication: ");
        labelStar.setUIID("note");
        //vote.setUIID("note");
        Label comments = new Label("Comments: ");
        //comments.setUIID("annoncedetails");
        Label remp = new Label("Votre commentaire:");
        //direction.getStyle().setPaddingBottom(150);
        //labelStar.getStyle().setPaddingBottom(60);
        //vote.getStyle().setPaddingBottom(5);
        //vote.getStyle().setPaddingTop(0);
        comments.getStyle().setPaddingBottom(2);
        //annonceDetails.getStyle().setPaddingTop(60);

        Container ctn9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        AfficherComm();
        //ctn9.add(FlowLayout.encloseCenter(vote));
//        ctn9.add(FlowLayout.encloseCenter(ctn10));
//        ctn9.add(FlowLayout.encloseCenter(labelStar));
//        ctn9.setUIID("DetailLabel");

        Container ctn8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctn8.add(FlowLayout.encloseCenter(comments));
        ctn8.add(remp);
        ctn8.add(tfComment);
        ctn8.add(FlowLayout.encloseCenter(btnOK));
        ctn8.setUIID("ctn2");

        //comment.add(FlowLayout.encloseCenter(annonceDetails));
        //comment.add(image1);
        //comment.add(direction);
        //gui_comment_Container.add(ctn9);
        gui_comment_Container.add(ctn8);
        //btnOK.getStyle().setPaddingBottom(10);
        //gui_comment_Container.show();

//        Toolbar.setGlobalToolbar(true);
//        cmd1 = new Command("Mon profil");
//        cmd2 = new Command("Mes alertes");
//        cmd3 = new Command("Mes Commentaires");
//        cmdBack = new Command("Back");
//        comment.getToolbar().addCommandToSideMenu(cmd1);
//        comment.getToolbar().addCommandToSideMenu(cmd2);
//        comment.getToolbar().addCommandToSideMenu("Mes Commentaire", null, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                comment.show();
//            }
//        });
//        AfficherRate();
//        if (etat == 0) {
//            slider = createStarRankSlider();
//            ctn10.add(slider);
//        }
        ////////////Affichage moy Rate
//        ConnectionRequest re = new ConnectionRequest();
//        re.setUrl("http://localhost/script/moyRate.php?annonce=" + ann1.getIdAnnonce());
//        re.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                String response = new String(re.getResponseData());
//                getListRate(new String(re.getResponseData()), re);
//                labelStar.setText("Note : " + ff + "/5");
//
//            }
//        });
//        NetworkManager.getInstance().addToQueue(re);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ("".equals(tfComment.getText())) {
                    Dialog.show("Alert", "Votre Commentaire est Vide", "ok", null);
                } else {
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/script/insert.php?description=" + tfComment.getText() + "&idAnnonce=" + ann1.getIdAnnonce() + "&idUser=" + LogIn.e.getId());

                    // AfficherComm();
//                    String accountSid = "ACc758aed9ef32a7f12dc759640d2c669f"; // Your Account SID from www.twilio.com/user/account
//                    String authToken = "0b32f95fd5f57a7286d05712d049dfc6"; // Your Auth Token from www.twilio.com/user/account
//                    
//                    Twilio.init(accountSid, authToken);
//                    Message message = Message.creator(
//                            new PhoneNumber("+21625093776"), 
//                            new PhoneNumber("+14244002035"), 
//                            user.getNom() + " a commenter votre annonce" 
//                    ).create();
//                    System.out.println(message.getSid());
                    //gui_comment_Container.addComponent(ctn3);
                    NetworkManager.getInstance().addToQueue(con);
                    //gui_comment_Container.removeAll();
//                    gui_comment_Container.addComponent(ctn3);
//                    gui_comment_Container.forceRevalidate();
                    SingleAnnonceView s = new SingleAnnonceView(ann1);
                    s.show();

                }
            }
        });

    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_comment_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setName("AnnonceCommentaireRate");
        addComponent(gui_comment_Container);
        gui_comment_Container.setName("comment_Container");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
//    public void AfficherRate() {
//
//        ConnectionRequest con4 = new ConnectionRequest();
//        con4.setUrl("http://localhost/script/SelectRateByIdAnnonce.php?idAnnonce=" + ann1.getIdAnnonce());
//        con4.addResponseListener(new ActionListener<NetworkEvent>() {
//
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                ArrayList<Rate> rates = getAllRate(new String(con4.getResponseData()));
//                for (Rate r : rates) {
//                    if (LogIn.e.getId() == r.getIdUser().getId()) {
//                        etat = 1;
//                        System.out.println(etat);
//                    }
//
//                }
//
//            }
//        });
//        NetworkManager.getInstance().addToQueue(con4);
//    }

//    public void getListRate(String json, ConnectionRequest con) {
//        ArrayList<User> listrate = new ArrayList<>();
//        try {
//            JSONParser j = new JSONParser();
//            Map<String, Object> rate = j.parseJSON(new CharArrayReader(json.toCharArray()));
//            Map<String, Object> ann = (Map<String, Object>) (rate.get("rate"));
//            System.out.println("hi it's " + ann.get("moyRate").toString());
//            ff = Float.parseFloat(ann.get("moyRate").toString());
//            listrate.add(LogIn.e);
//        } catch (IOException ex) {
//        }
//    }

    public void AfficherComm() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/script/select.php?annonce=" + ann1.getIdAnnonce());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                ArrayList<Commentaire> al = getListCommentaire(new String(con.getResponseData()));
                for (Commentaire cm : al) {
                    ConnectionRequest con2 = new ConnectionRequest();
                    con2.setUrl("http://localhost/script/SelectUserById.php?idUser=" + cm.getIdUser().getId());
                    con2.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            User userComm = getUserbyId(new String(con2.getResponseData()));
                            UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
                            Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            ctn3.setUIID("DetailLabel");
                            Label lbl1 = new Label(userComm.getNom());
                            Label lbl2 = new Label(cm.getDescription());
                            Label cadrephoto = new Label();
                            String lien = "http://localhost/Covoiturage2/Covoiturage/web/user/" + userComm.getPhoto_Profil();
                            cadrephoto.setIcon(URLImage.createToStorage(placeholder, userComm.getPhoto_Profil(), lien));
                            ctn2.add(lbl1);
                            ctn2.add(lbl2);
                            ctn1.add(cadrephoto);
                            ctn1.add(ctn2);
                            ctn3.add(ctn1);
                            ctn3.setTactileTouch(true);
                            ctn3.setFocusable(true);
                            gui_comment_Container.add(ctn3);

////////////////////////////////////Suprimer Modifier
                            ctn3.addPointerReleasedListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (cm.getIdUser().getId() == LogIn.e.getId()) {
                                        boolean a = Dialog.show("Commentaire: " + userComm.getNom(), cm.getDescription(), "Modifier", "Supprimer");

                                        if (a == true) {
                                            Form f2 = new Form("Modifier la commentaire", BoxLayout.y());
                                            f2.setUIID("AjouterAnnonceForm");
                                            TextField ModComment = new TextField();
                                            Button btn = new Button("validate");
                                            btn.setUIID("aze");
                                            Label label = new Label("Veillez Modifier Votre Commentaire:");
                                            btn.setIcon(theme2.getImage("writing.png"));
                                            f2.add(label);
                                            label.getStyle().setPaddingTop(70);
                                            f2.add(ModComment);
                                            f2.add(FlowLayout.encloseCenter(btn));
                                            f2.show();
                                            f2.getToolbar().addCommandToRightBar("back", null, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    SingleAnnonceView s = new SingleAnnonceView(ann1);
                                                    s.showBack();
                                                }
                                            });
                                            btn.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    ConnectionRequest conn = new ConnectionRequest();
                                                    conn.setUrl("http://localhost/script/updateCommentaire.php?idCommentaire=" + cm.getIdCommentaire() + "&description=" + ModComment.getText());
                                                    NetworkManager.getInstance().addToQueue(conn);
                                                    SingleAnnonceView s = new SingleAnnonceView(ann1);
                                                    s.show();
//                                                    MyCommentaire mc = new MyCommentaire(theme2);
//                                                    mc.getComment().show();

                                                }
                                            });

                                        } else if (a == false) {
                                            ConnectionRequest conn = new ConnectionRequest();
                                            conn.setUrl("http://localhost/script/supprimerCommentaire.php?idCommentaire=" + cm.getIdCommentaire());
                                            NetworkManager.getInstance().addToQueue(conn);
                                            //  AfficherComm();
                                            gui_comment_Container.removeComponent(ctn3);
                                            gui_comment_Container.revalidate();
                                        }
                                    } else {
                                        Dialog.show("Alert", "Vous ne pouvez pas supprimer ou mettre à jour la commentaire de  " + userComm.getNom(), "Annuler", null);
                                    }
                                }
                            });
                        }
                    });
                    NetworkManager.getInstance().addToQueue(con2);
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);

    }

    public ArrayList<Rate> getAllRate(String json) {
        ArrayList<Rate> listRates = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> Commentaires = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("rabek1" + Commentaires);
            System.out.println("rabek2" + Commentaires.size());

            List<Map<String, Object>> list = (List<Map<String, Object>>) Commentaires.get("rate");
            System.out.println("rate list " + list);
            System.out.println("rate list 22 " + list);

            for (Map<String, Object> obj : list) {
                Rate r = new Rate();
                r.setIdUser(new User(Integer.parseInt(obj.get("idUser").toString())));
                listRates.add(r);
                System.out.println(listRates);
            }
        } catch (IOException ex) {
        }
        return listRates;
    }

    public ArrayList<Commentaire> getListCommentaire(String json) {
        ArrayList<Commentaire> listCommentaires = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> Commentaires = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) Commentaires.get("commentaire");
            System.out.println("comment list " + list);
            for (Map<String, Object> obj : list) {
                Commentaire comm = new Commentaire();
                comm.setDescription(obj.get("description").toString());
                comm.setIdCommentaire(Integer.parseInt(obj.get("idCommentaire").toString()));
                comm.setIdUser(new User(Integer.parseInt(obj.get("idUser").toString())));
                comm.setIdAnnonce(new Annonce(Integer.parseInt(obj.get("idAnnonce").toString())));
                listCommentaires.add(comm);
            }
        } catch (IOException ex) {
        }
        return listCommentaires;
    }

    public User getUserbyId(String json) {
        User user2 = null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Map<String, Object> list = (Map<String, Object>) users.get("user");
            user2 = new User();
            user2.setId(Integer.parseInt(list.get("id").toString()));
            user2.setNom(list.get("nom").toString());
            user2.setPhoto_Profil(list.get("photoProfil").toString());
        } catch (IOException ex) {
        }
        return user2;
    }

//    private void initStarRankStyle(Style s, Image star) {
//        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
//        s.setBorder(Border.createEmpty());
//        s.setBgImage(star);
//        s.setBgTransparency(0);
//    }
//
//    private Slider createStarRankSlider() {
//
//        Slider starRank = new Slider();
//        starRank.setEditable(true);
//        starRank.setMinValue(0);
//        starRank.setMaxValue(5);
//        Font fnt = Font.createTrueTypeFont("native:MainThin", "native:MainThin").
//                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
//        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
//        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//        s.setOpacity(100);
//        s.setFgColor(0);
//        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
//        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
//        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
//        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
//        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
//        starRank.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if (starRank.getProgress() == 0) {
//                    Dialog.show("Alert", "Votre evaluation est nul, pouvez-vous répéter?", "ok", null);
//                } else {
//                    System.out.println(starRank.getProgress());
//                    ConnectionRequest con = new ConnectionRequest();
//                    con.setUrl("http://localhost/script/insertRank.php?nbrRate=" + starRank.getProgress() + "&idUser=" + LogIn.e.getId() + "&idAnnonce=" + ann1.getIdAnnonce());
//                    NetworkManager.getInstance().addToQueue(con);
//
//                    ConnectionRequest re = new ConnectionRequest();
//                    re.setUrl("http://localhost/script/moyRate.php?annonce=" + ann1.getIdAnnonce());
//                    re.addResponseListener(new ActionListener<NetworkEvent>() {
//                        @Override
//                        public void actionPerformed(NetworkEvent evt) {
//                            String response = new String(re.getResponseData());
//                            getListRate(new String(re.getResponseData()), re);
//                            labelStar.setText("Note : " + ff + "/5");
//                        }
//                    });
//                    etat = 1;
//                    ctn10.setVisible(false);
//                    gui_comment_Container.revalidate();
//                    NetworkManager.getInstance().addToQueue(re);
//                }
//            }
//        });
//
//        return starRank;
//    }

}
