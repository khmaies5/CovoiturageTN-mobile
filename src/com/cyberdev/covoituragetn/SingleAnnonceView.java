/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import entity.Annonce;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.util.Callback;
import com.codename1.util.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * GUI builder created Form
 *
 * @author khmai
 */
public class SingleAnnonceView extends com.codename1.ui.Form {

    private static final String HTML_API_KEY = "AIzaSyCTbLzGGtFx0vn093oLmljzIxHoZ9MjuaE";
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    String lieuArriver;

    public SingleAnnonceView() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public SingleAnnonceView(Annonce ann) {
        this(com.codename1.ui.util.Resources.getGlobalResources());

        //toolbar
        //Add Edit, Add and Delete Commands to the this Form context Menu
        Image im = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, UIManager.getInstance().getComponentStyle("Command"));
//        Command edit = new Command("Edit", im) {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                System.out.println("Editing");
//            }
//        };
//        this.getToolbar().addCommandToOverflowMenu(edit);
//
//        im = FontImage.createMaterial(FontImage.MATERIAL_LIBRARY_ADD, UIManager.getInstance().getComponentStyle("Command"));
//        Command add = new Command("Add", im) {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                System.out.println("Adding");
//            }
//        };
//        this.getToolbar().addCommandToOverflowMenu(add);

        if(LogIn.e.getId()==ann.getCreator().getId()){
        im = FontImage.createMaterial(FontImage.MATERIAL_DELETE, UIManager.getInstance().getComponentStyle("Command"));
        Command delete = new Command("Delete", im) {

            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Deleting");
                
                 try {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://localhost/CovoiturageTN-API/delete.php");
            r.addArgument("id", "" + ann.getIdAnnonce());
            

            r.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        ToastBar.showMessage("deleted", FontImage.MATERIAL_DELETE, 5000);
                        CovoiturageTN cov = new CovoiturageTN();
                        cov.start();

                    } else {
                        Dialog.show("Erreur", "erreur check your connection", "Ok", null);
                    }
                }
            });
            NetworkManager.getInstance().addToQueue(r);
        } catch (Exception err) {
            Log.e(err);

        }
            }

        };
        this.getToolbar().addCommandToOverflowMenu(delete);
        }
        Label destinationLabel = new Label("Destination");
        //l.setAlignment(CENTER);
        destinationLabel.setUIID("DestinationLabel");
        Label From = new Label("Tunis");
        From.setUIID("DestinationLabel");

        Label Depart = new Label("Depart:");
        Depart.setUIID("LeftLabel");
        Label To = new Label();
        To.setUIID("LeftLabel");
        Label Date = new Label();
        Date.setUIID("LeftLabel");
        Label DistanceLabel = new Label("Distance: ");
        DistanceLabel.setUIID("RighttLabel");
        Label Distance = new Label();
        Distance.setUIID("RighttLabel");
        Label PrixLabel = new Label("Prix:");
        PrixLabel.setUIID("LeftLabel");
        Label Prix = new Label();
        Prix.setUIID("LeftLabel");
        Label NbrLabel = new Label("Nombre de place:");
        NbrLabel.setUIID("RightLabel");
        Label nbrPlace = new Label();
        nbrPlace.setUIID("RightLabel");

        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("YY-MM-dd HH:mm");

        String tripDate = sdf.format(ann.getTripDate());
        // Date.setTextBlockAlign(CENTER);
        From.setText(ann.getLieuDepart());
        To.setText(ann.getLieuArriver());
        Date.setText(tripDate);

        Distance.setText(ann.getDistance());
        Prix.setText("" + ann.getPrix() + "Dt");
        nbrPlace.setText("" + ann.getNbrPersonne());
        List<String> parts = StringUtil.tokenize(ann.getCritere(), ";");
        String crit1 = "http://localhost/covoituragetn/" + parts.get(0) + ".png";
        EncodedImage placeholder1 = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

        String crit2 = "http://localhost/covoituragetn/" + parts.get(1) + ".png";
        EncodedImage placeholder2 = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

        String crit3 = "http://localhost/covoituragetn/" + parts.get(2) + ".png";
        EncodedImage placeholder3 = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

        String crit4 = "http://localhost/covoituragetn/" + parts.get(3) + ".png";
        EncodedImage placeholder4 = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

        Image image1 = URLImage.createToStorage(placeholder1, parts.get(0), crit1);
        Image image2 = URLImage.createToStorage(placeholder2, parts.get(1), crit2);
        Image image3 = URLImage.createToStorage(placeholder3, parts.get(2), crit3);
        Image image4 = URLImage.createToStorage(placeholder4, parts.get(3), crit4);

        Label Crit_1 = new Label();
        Label Crit_2 = new Label();
        Label Crit_3 = new Label();
        Label Crit_4 = new Label();

        Crit_1.setIcon(image1.scaled(24, 24));

        Crit_2.setIcon(image2.scaled(24, 24));
        Crit_3.setIcon(image3.scaled(24, 24));
        Crit_4.setIcon(image4.scaled(24, 24));
        final MapContainer cnt = new MapContainer(HTML_API_KEY);

       cnt.setmapSize(200, 100);

        Container gui_Container_1 = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        BoxLayout.y().encloseY(destinationLabel, From)
                )
        );
        gui_MapContainer.add(BorderLayout.CENTER, gui_Container_1);

        Container info1 = LayeredLayout.encloseIn(
                BorderLayout.west(
                        BoxLayout.y().encloseY(Depart, To, Date)
                ),
                BorderLayout.east(
                        BoxLayout.y().encloseY(DistanceLabel, Distance)
                )
        );
        gui_Container_5.add(BorderLayout.SOUTH, info1);

        Container info2 = LayeredLayout.encloseIn(
                BorderLayout.west(
                        BoxLayout.y().encloseY(PrixLabel, Prix)
                ),
                BorderLayout.east(
                        BoxLayout.y().encloseY(NbrLabel, nbrPlace)
                )
        );
        gui_Container_6.add(BorderLayout.SOUTH, info2);

        Container c = new Container();

        gui_Container_9.add(FlowLayout.encloseCenter(Crit_1, Crit_2, Crit_3, Crit_4));

//         Container sp = new Container();
//         sp = gui_Container_5_1_1;
        // this.add(sp);
        //this.add();
        //gui_MapContainer.setPreferredH(200);
        //annonce route
       cnt.calculateAndDisplayAnnonceRoute(ann.getLieuDepart(), ann.getLieuArriver());
        lieuArriver = ann.getLieuArriver();
          AnnonceCommentaireRate comment = new AnnonceCommentaireRate(ann);
        this.add(comment);
       
        gui_reserver_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
  Form f2 =new Form("Demande",BoxLayout.y());
           Slider sl=         new Slider();
            Label l1=new Label("","choisir le nombre de places");
       // ProfilForm m =  new ProfilForm(theme);
         Button btn2 = new Button("Demande ");
               Button mail= new Button("envoyer mail au conducteur");
               Button back = new Button("back");
              f2.add(sl);
            f2.add(l1);
           f2.add(btn2);
          f2.add(mail);
          f2.add(back);  
          f2.show();
          
                     sl.setEditable(true);
                     sl.setMinValue(1);
                     sl.setMaxValue(5);
                   sl.addActionListener(event -> {
        l1.setText("nombre de palce a reserver  :"+sl.getProgress());});
                                                 //  m.getF().removeAll();

         
            l1.setText("hello");
                     sl.setEditable(true);
                     sl.setMinValue(1);
                     sl.setMaxValue(ann.getNbrPersonne());
                   sl.addActionListener(event -> {
        l1.setText("nombre de palce a reserver  :"+sl.getProgress());
       
        mail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Message m = new Message("bonjour monsieur je veux reserver "+sl.getProgress() +"pour votre annonce de covoiturage");
Display.getInstance().sendMessage(new String[] {LogIn.e.getEmail()}, "demande de covoiturage", m);


            }
        });
        
        btn2.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                       
                         System.out.println("gdgdgd"+LogIn.e.getId()+"&id_conduc="+ann.getCreator().getId()+"&id_ann="+ann.getIdAnnonce()+"&nbrplaces="+sl.getProgress()+"&etat_approbation="+LogIn.e.getId());
                         
                         
                                                  ConnectionRequest con1 = new ConnectionRequest();

                         Annonce ann1 =  new Annonce(1);
                                          con1.setUrl("http://localhost/script2/ajoutDemande.php?id_user="+LogIn.e.getId()+"&id_conduc="+ann.getCreator().getId()+"&id_ann="+ann.getIdAnnonce()+"&nbrplaces="+sl.getProgress()+"&etat_approbation="+LogIn.e.getId());

                          con1.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) {
                           String response1 = new String(con1.getResponseData()) ;
                      if (response1.equals("success")){
                      Dialog.show("Demande", "Demande envoyée", "ok",null);
                      CovoiturageTN.home.show();
                      }
                         
                     }
                 });
                                         NetworkManager.getInstance().addToQueue(con1);

//                         else {
//                         
//                           Dialog.show("Demande", "vous ne pouvez pas faire une demande sur votre propre annonce", "ok",null);
//                      CovoiturageTN.home.show();
//                         }
                     }
                 });
        
        });

            }
            
            
        });
        
        
//        gui_MapContainer.add(cnt);

        /* String encoded = getRoutesEncoded(ann.getLieuDepart(), ann.getLieuArriver());
        // decode the routes in an arry of coords
        Coord[] coords = decode(encoded);
        cnt.setCameraPosition(coords[0]);
         System.out.println("latlong "+coords[0]);
        

            System.out.println("cords "+coords);

        cnt.addPath(coords);
       
         */
    }

    public SingleAnnonceView(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    public static Coord[] decode(final String encodedPath) {

        int len = encodedPath.length();

        final ArrayList<Coord> path = new ArrayList<Coord>();

        int index = 0;

        int lat = 0;

        int lng = 0;

        while (index < len) {

            int result = 1;

            int shift = 0;

            int b;

            do {

                b = encodedPath.charAt(index++) - 63 - 1;

                result += b << shift;

                shift += 5;

            } while (b >= 0x1f);

            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;

            shift = 0;

            do {

                b = encodedPath.charAt(index++) - 63 - 1;

                result += b << shift;

                shift += 5;

            } while (b >= 0x1f);

            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new Coord(lat * 1e-5, lng * 1e-5));

        }

        Coord[] p = new Coord[path.size()];

        for (int i = 0; i < path.size(); i++) {

            p[i] = path.get(i);

        }

        return p;

    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_MapContainer = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.GridLayout(1, 1));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_5 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_6_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.GridLayout(1, 1));
    private com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_6 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_5_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.GridLayout(1, 1));
    private com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_9 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Button gui_reserver_btn = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setUIID("AnnonceViewForm");
        setTitle("Annonce View");
        setName("SingleAnnonceView");
        addComponent(gui_MapContainer);
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Label_1.setText("Annonce Details");
        gui_Label_1.setUIID("DetailLabel");
        gui_Label_1.setName("Label_1");
        addComponent(gui_Container_5);
        addComponent(gui_Container_6_1);
        gui_Container_6_1.setName("Container_6_1");
        gui_Container_6_1.addComponent(gui_Label_3);
        gui_Label_3.setUIID("DividerLabel");
        gui_Label_3.setName("Label_3");
        addComponent(gui_Container_6);
        addComponent(gui_Container_5_1_1);
        gui_Container_5_1_1.setName("Container_5_1_1");
        gui_Container_5_1_1.addComponent(gui_Label_2);
        gui_Label_2.setUIID("DividerLabel");
        gui_Label_2.setName("Label_2");
        addComponent(gui_Container_9);
        addComponent(gui_reserver_btn);
        addComponent(gui_Button_1);
        gui_MapContainer.setName("MapContainer");
        gui_Container_1.setName("Container_1");
        gui_Container_5.setUIID("AnnonceInfoContainer");
        gui_Container_5.setName("Container_5");
        gui_Container_6_1.setName("Container_6_1");
        gui_Container_6.setUIID("AnnonceInfoContainer");
        gui_Container_6.setName("Container_6");
        gui_Container_5_1_1.setName("Container_5_1_1");
        gui_Container_9.setUIID("CritContainer");
        gui_Container_9.setName("Container_9");
        gui_reserver_btn.setText("Reserver");
        gui_reserver_btn.setName("reserver_btn");
        com.codename1.ui.FontImage.setMaterialIcon(gui_reserver_btn,'');
        gui_Button_1.setText("Navigate");
        gui_Button_1.setName("Button_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,'');
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public static String getRoutesEncoded(String src, String dest) {

        String ret = "";

        try {

            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false);

            request.addArgument("key", HTML_API_KEY);

            request.addArgument("origin", src);

            request.addArgument("destination", dest);

            NetworkManager.getInstance().addToQueueAndWait(request);

            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
//            System.out.println("response "+response);
            if (response.get("routes") != null) {

                ArrayList routes = (ArrayList) response.get("routes");

                if (routes.size() > 0) {
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                }

                System.out.println("distance " + ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("legs")).get(0)).get("distance")).get("text").toString());

                //  String d = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("distance")).get("text").toString();
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return ret;

    }

    public static void getRoutesEncodedAsync(Coord src, Coord dest, Callback callback) {

        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false) {

            @Override

            protected void readResponse(InputStream input) throws IOException {

                String ret = "";

                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));

                if (response.get("routes") != null) {

                    ArrayList routes = (ArrayList) response.get("routes");

                    if (routes.size() > 0) {
                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                    }

                }

                callback.onSucess(ret);

            }

        };

        request.addArgument("key", HTML_API_KEY);

        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());

        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

        NetworkManager.getInstance().addToQueue(request);

    }

    public com.codename1.ui.Component create_Custom_1() {
        //return your own custom component from this method
        return new com.codename1.ui.Label("Custom Component");
    }

    public void ontestCommand(com.codename1.ui.events.ActionEvent ev, com.codename1.ui.Command cmd) {
        Display.getInstance().openNativeNavigationApp(lieuArriver);

    }

}
