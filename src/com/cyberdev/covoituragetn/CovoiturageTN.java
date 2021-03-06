package com.cyberdev.covoituragetn;

import entity.Annonce;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.NavigationCommand;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Component;
import com.codename1.ui.Font;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.URLImage;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.util.Callback;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.cyberdev.covoituragetn.AnnoncesView;
import entity.Demande;
import entity.Reservation;
import entity.User;
import java.util.List;
/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class CovoiturageTN {
    private static final String HTML_API_KEY = "AIzaSyCTbLzGGtFx0vn093oLmljzIxHoZ9MjuaE";
    private Form current;
    private Resources theme;
      Form f6 ;
    Form f7 ;
    Form f3 ;
    Form f4 ;
    Label l ;
        public static  double montant ;
   public static  int id;
    public static int id_user ;
     public static int id_user1 ;
     public static String arrivee;
     public static  String depart ;
         public static    int nbreplaces;
           public static  int id_annonce;
          public static    int id_annonce1;
         public static    String username ;
         public static    int id_reserv;
        public static     int id_a ;
      public static        int nb_placess ;
     public static   int maxvalue ;
             Button o ;
         Button o1 ;
      SpanLabel sp ;
        int badgeNumber = 0;

    public static Form home;
    ArrayList<Annonce> annonces = new ArrayList<Annonce>();

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    









    public void start() {
        if (current != null) {
            current.show();
            return;
        }
   f4 = new Form("gererDemande",BoxLayout.y());
            Button btn4 = new Button("accepter");
        Button btn5 = new Button("refuser");
        Button back = new Button("back");
            Label l = new Label("","id");
                   //create and build the home Form
        home = new AnnoncesView();

    f4.add(btn4);
              f4.add(btn5);
   
         
        Form form1 = new LogIn();
       

        setBackCommand(form1);
        Form form2 = new AjouterAnnonce();
        setBackCommand(form2);
       

                            




        //Add navigation commands to the home Form
        NavigationCommand homeCommand = new NavigationCommand("Home");
        homeCommand.setNextForm(home);
        home.getToolbar().addCommandToSideMenu(homeCommand);
         

        NavigationCommand cmd1 = new NavigationCommand("Demandes liste");
       // cmd1.setNextForm(form1);
        home.getToolbar().addCommandToSideMenu(cmd1);

        NavigationCommand cmd2 = new NavigationCommand("Liste reservation");
        cmd2.setNextForm(form2);
                home.getToolbar().addCommandToSideMenu(cmd2);

        
        if(LogIn.isLoggedIn()){
            System.out.println("loggedin");
//            ProfilForm pform = new ProfilForm(theme);
//             Form profile = pform.getF();
//                                                setBackCommand(profile);
//            NavigationCommand profileCommand = new NavigationCommand("Profile");
//        profileCommand.setNextForm(profile);
//        home.getToolbar().addCommandToSideMenu(profileCommand);
        ///////////////////////////////
        ListeTousLesGroupes tsLesGroupes = new ListeTousLesGroupes(theme);
        
        Form groupe =  tsLesGroupes.getF();
                                                setBackCommand(groupe);
            NavigationCommand groupeCommand = new NavigationCommand("Groupe");
        groupeCommand.setNextForm(groupe);
        home.getToolbar().addCommandToSideMenu(groupeCommand);
        ///////////////////////////////
            
        } else {
                        System.out.println("not loggedin");

Form login = new LogIn();
                                                    setBackCommand(login);
            NavigationCommand loginCommand = new NavigationCommand("LogIn");
        loginCommand.setNextForm(login);
        home.getToolbar().addCommandToSideMenu(loginCommand);
        }

              home.addCommandListener(h->{
            if(h.getCommand()==cmd1){
                
ConnectionRequest con3 = new ConnectionRequest();
       

con3.setUrl("http://localhost/script2/affiche_demande.php?id_user="+LogIn.e.getId());
con3.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
              
             ArrayList<Demande> al=getListDemande(new String(con3.getResponseData()),theme);
               
             
        
           }
        });
        NetworkManager.getInstance().addToQueue(con3);                          

            }
        });
              
              
              
               home.addCommandListener(h->{
        if(h.getCommand()==cmd2){
        
               ConnectionRequest con9 = new ConnectionRequest();
        con9.setUrl("http://localhost/script2/afficher_reservation.php?id_user="+LogIn.e.getId());
        con9.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
              
             ArrayList<Reservation> al=getListreservation(new String(con9.getResponseData()),theme);
             
        
           }
        });
        NetworkManager.getInstance().addToQueue(con9);
                     

        
        }
        
        });       
              
   btn4.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
ConnectionRequest con4 = new ConnectionRequest();
        con4.setUrl("http://localhost/script2/accepter_demande.php?id_demande="+id);
        con4.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                     String response1 = new String(con4.getResponseData()) ;
                      if (response1.equals("success")){
                      Dialog.show("Demande", "Demande accepter", "ok",null);
                        ConnectionRequest con14 = new ConnectionRequest();
                                          System.out.println("hi it's :"+id_annonce+" nbr:"+nbreplaces);
        con14.setUrl("http://localhost/script2/decrementer_nombre_de_place.php?idannonce="+id_annonce+"&nbplace="+nbreplaces);
                
                
  NetworkManager.getInstance().addToQueue(con14);
  
// f.revalidate();
// f.forceRevalidate();
//                      f.show();
home.removeAll();
//LoginForm sh = new LoginForm(theme);
//sh.getF().show();

home = new AnnoncesView(theme);
                      
                      }
             
        
           }
        });
        NetworkManager.getInstance().addToQueue(con4);
          ConnectionRequest con10 = new ConnectionRequest();

                       System.out.println("hhhhhh"+id);
                                         con10.setUrl("http://localhost/script2/ajouter_reservation.php?id_user="+id_user+"&id_annonce="+id_annonce+"&date_reservation=2017-02-01&etat_reservation=tt&montant="+montant+"&type_payement=cheque&nbplace="+nbreplaces);
                   con10.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) {
                           String response10 = new String(con10.getResponseData()) ;
                      if (response10.equals("success")){
                          System.out.println("yessss");
                      }
                         
                     }
                 });
                                         NetworkManager.getInstance().addToQueue(con10);
                                         
                                       
             }
         });
btn5.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
ConnectionRequest con5 = new ConnectionRequest();
        con5.setUrl("http://localhost/script2/refuser_demande.php?id_demande="+id);
        con5.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                     String response1 = new String(con5.getResponseData()) ;
                      if (response1.equals("success")){
                      Dialog.show("Demande", "Demande refuser", "ok",null);
                      home.show();
                      }
             
        
           }
        });
        NetworkManager.getInstance().addToQueue(con5);
             }
         });
btn5.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
ConnectionRequest con5 = new ConnectionRequest();
        con5.setUrl("http://localhost/script2/refuser_demande.php?id_demande="+id);
        con5.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                     String response1 = new String(con5.getResponseData()) ;
                      if (response1.equals("success")){
                      Dialog.show("Demande", "Demande refuser", "ok",null);
                      home.show();
                      }
             
        
           }
        });
        NetworkManager.getInstance().addToQueue(con5);
             }
         });
        home.show();
    }
    
    
    
   public ArrayList<Demande> getListDemande(String json,Resources theme) {
        ArrayList<Demande> listAlertes = new ArrayList<>();
     
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> alertes = j.parseJSON(new CharArrayReader(json.toCharArray()));
   List<Map<String, Object>> list = (List<Map<String, Object>>) alertes.get("demande");
            System.out.println("hello : "+list);
     f3 =new Form("MesDemandes",BoxLayout.y());
             sp = new SpanLabel();
        f3.add(sp);

              for (Map<String, Object> obj : list) {
                  Demande a = new Demande();
                  Annonce p = new Annonce();
                  p.setLieuDepart(obj.get("lieu_depart").toString());
                  p.setLieuArriver(obj.get("lieu_arrive").toString());
                  a.setAnnonce(p);
          
            User o=new User();
            o.setNom(obj.get("username").toString());
            a.setUser(o);
        
              a.setNbrPlaces(Integer.parseInt(obj.get("nbr_places").toString()));
              a.setId_demande(Integer.parseInt(obj.get("id_demande").toString()));
                  System.out.println("dsfsfsf"+a.getAnnonce());

         
               
           
             listAlertes.add(a);
            }
              MultiButton[] cmps = new MultiButton[list.size()];
            for(int iter = 0 ; iter < cmps.length ; iter++) {
                Map<String, Object> currentListing = list.get(iter);
                if(currentListing == null) {
                    return null;
                }
                int mm = Display.getInstance().convertToPixels(3);
 Style s = UIManager.getInstance().getComponentStyle("M");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);
                Image icon3 = URLImage.createToStorage(placeholder, "kk", "https://simultaneous-hyphen.000webhostapp.com/1.png" );
              
                  //  String imgpp="https://simultaneous-hyphen.000webhostapp.com/img1.png";
                    String thumb_url = (String)currentListing.get("lieu_depart");
                String guid = (String)currentListing.get("lieu_arrive");
                String summary = (String)currentListing.get("nbr_places");
                cmps[iter] = new MultiButton("");
                cmps[iter].setTextLine2(thumb_url+"------>"+guid);
                cmps[iter].setTextLine3(" \n "+"nombre de places"+summary);
              cmps[iter].setIcon(icon3);
           f3.add(cmps[iter]);
                      cmps[iter].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
          id = Integer.parseInt((String)currentListing.get("id_demande"));
              id_annonce=Integer.parseInt((String)currentListing.get("id_annonce"));
             arrivee = (String)currentListing.get("lieu_arrive");
            depart = (String)currentListing.get("lieu_depart");
              nbreplaces= Integer.parseInt((String)currentListing.get("nbr_places"));
              id_user=Integer.parseInt((String)currentListing.get("id_user"));
              montant=Double.parseDouble((String)currentListing.get("montant"));
              username=(String)currentListing.get("username");
              System.out.println("babav : "+id+"  "+arrivee+"   "+depart+"   "+nbreplaces+"    "+id_user+" montant : "+montant);
              
              f4.show();
        
              
              
              
          }
      });
            }
                  setBackCommand(f3);

              f3.refreshTheme();
              f3.show();

        } catch (IOException ex) {
         }
          
      return listAlertes;
     

    }
   
   
      public ArrayList<Reservation> getListreservation(String json,Resources theme) {
        ArrayList<Reservation> listAlertes = new ArrayList<>();
     
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> alertes = j.parseJSON(new CharArrayReader(json.toCharArray()));
   List<Map<String, Object>> list = (List<Map<String, Object>>) alertes.get("reservation");
            System.out.println("hello : "+list);
     f6 =new Form("MesReservation",BoxLayout.y());
             sp = new SpanLabel();
        f6.add(sp);

              for (Map<String, Object> obj : list) {
                 Reservation a = new Reservation();
                  Annonce p = new Annonce();
                 p.setIdAnnonce(Integer.parseInt(obj.get("id_annonce").toString()));
                  a.setAnnonce(p);
               User o=new User();
            o.setId(Integer.parseInt(obj.get("id_user").toString()));
          
            a.setCreator(o);
         a.setDate_reservation(obj.get("date_reservation").toString());
           a.setMontant(Integer.parseInt(obj.get("montant").toString()));
           a.setNbr_place(Integer.parseInt(obj.get("nbplace").toString()));
          
                  System.out.println("dsfsfsf"+a.getAnnonce());

         
               
           
            listAlertes.add(a);
            // sp.setText(sp.getText()+" "+a.getAnnonce().getLieuDepart()+"-->"+a.getAnnonce().getLieuArriver()+"\n date depart: "+a.getUser().getNom()+" \n heure :"+a.getNbrPlaces()+" \n ------- \n");
           
   
     

         }
              MultiButton[] cmps = new MultiButton[list.size()];
            for(int iter = 0 ; iter < cmps.length ; iter++) {
                Map<String, Object> currentListing = list.get(iter);
                if(currentListing == null) {
                    return null;
                }
                
                   int mm = Display.getInstance().convertToPixels(3);
                    EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    Image icon3 = URLImage.createToStorage(placeholder, "hello", "https://simultaneous-hyphen.000webhostapp.com/3.jpg" );
                    String etat=(String)currentListing.get("etat_reservation");
                String thumb_url = (String)currentListing.get("lieu_depart");
                String guid = (String)currentListing.get("lieu_arrive");
                String summary = (String)currentListing.get("nbplace");
                System.out.println("sysdsvsvsdv"+summary+"hhhhhh"+thumb_url+"uuuuu"+guid);
                cmps[iter] = new MultiButton("");
                cmps[iter].setTextLine2("etat reservation :"+(String)currentListing.get("etat_reservation"));
                cmps[iter].setTextLine3(thumb_url+"------>"+guid);
                                cmps[iter].setTextLine4(" nombre de places :"+summary);

                  cmps[iter].setIcon(icon3);
             //  cmps[iter].setIcon(URLImage.createToStorage(placeholder, guid, thumb_url));
             f6.add(cmps[iter]);
                      cmps[iter].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
          id_reserv=Integer.parseInt((String)currentListing.get("id"));
              id_a = Integer.parseInt((String)currentListing.get("id_annonce"));
              nb_placess = Integer.parseInt((String)currentListing.get("nbplace"));

              System.out.println("id reservation :"+id_reserv);
              f7= new Form("gererReservation",BoxLayout.y());
               o=new Button("confirmer reservation ");
               o1=new Button("annuler reservation");
               setBackCommand(f7);
               if(etat.equals("payer")){
               f7.add(o1);
               
                 o1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {


  ConnectionRequest con30 = new ConnectionRequest();
                 con30.setUrl("http://localhost/script2/annuler_reservation.php?id_reservation="+id_reserv);
                  NetworkManager.getInstance().addToQueue(con30);
                   home.removeAll();

                  ConnectionRequest con31 = new ConnectionRequest();
                    con31.setUrl("http://localhost/script2/augmenter_nombre_de_place.php?idannonce="+id_a+"&nbplace="+nb_placess); 
                                    
  NetworkManager.getInstance().addToQueue(con31);
    home = new AnnoncesView(theme);
 

                  }
                  
              });
               
               
               }else{
              f7.add(o);
              f7.add(o1);
              o.addActionListener(new ActionListener() {
                  private Object tfpassword;
                  @Override
                  public void actionPerformed(ActionEvent evt) {
  LocalNotification n = new LocalNotification();
           
                n.setAlertBody("reservation enregistrer");
                n.setAlertTitle("reservation effectuer pour le covoiturage");
                n.setId("1");
                n.setAlertImage("https://simultaneous-hyphen.000webhostapp.com/covoiturage.png");
               n.setBadgeNumber(badgeNumber++);
               

              

                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                 ConnectionRequest con15 = new ConnectionRequest();
                 con15.setUrl("http://localhost/script2/payement.php?id_reservation="+id_reserv);
                  NetworkManager.getInstance().addToQueue(con15);
                 home.removeAll();
                  home = new AnnoncesView(theme);
                  }
              });
              o1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {


  ConnectionRequest con30 = new ConnectionRequest();
                 con30.setUrl("http://localhost/script2/annuler_reservation.php?id_reservation="+id_reserv);
                  NetworkManager.getInstance().addToQueue(con30);
                   home.removeAll();
                  ConnectionRequest con31 = new ConnectionRequest();
                    con31.setUrl("http://localhost/script2/augmenter_nombre_de_place.php?idannonce="+id_a+"&nbplace="+nb_placess); 
                                    
  NetworkManager.getInstance().addToQueue(con31);
   //annonceForm gg = new annonceForm(theme);
home=new AnnoncesView(theme);
                  }
              });
               }
              
              f7.show();
              
              
              
          }
      });
            }
            setBackCommand(f6);
              f6.refreshTheme();
              f6.show();


        } catch (IOException ex) {
         }
          
      return listAlertes;
     

    }
    protected void setBackCommand(Form f) {
        Command back = new Command("") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                start();
                //home.showBack();
            }

        };
        Image img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
        back.setIcon(img);
        
        f.getToolbar().addCommandToLeftBar(back);
        f.getToolbar().setTitleCentered(true);
        f.setBackCommand(back);
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }
    

}
