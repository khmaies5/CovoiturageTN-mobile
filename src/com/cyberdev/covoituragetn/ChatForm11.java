/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.AccessToken;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.push.Push;
import com.codename1.push.PushCallback;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.MorphTransition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.SwipeBackSupport;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.util.WeakHashMap;
import com.codename1.util.CaseInsensitiveOrder;


import entity.Groupe;
import entity.Message;
import entity.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Sarra
 */
public class ChatForm11 {
     Form f;
//         private static final String PUSH_TOKEN = "-------------------------------";
//    private static final String GCM_SENDER_ID = "-----------------";
//    private static final boolean ITUNES_PRODUCTION_PUSH = false;
//    private static final String GCM_SERVER_API_KEY = "------------------";
//    private static final String ITUNES_PRODUCTION_PUSH_CERT = "--------------";
//    private static final String ITUNES_PRODUCTION_PUSH_CERT_PASSWORD = "-------";
//    private static final String ITUNES_DEVELOPMENT_PUSH_CERT = "---------------";
//    private static final String ITUNES_DEVELOPMENT_PUSH_CERT_PASSWORD = "---------";
//    private static final String PUBNUB_SUB_KEY = "--------------------";
//    private static final String PUBNUB_PUB_KEY = "--------------------";
//    
    private Form current;
    private Resources theme;

    private String fullName;
    private String uniqueId;
    private String imageURL;
    private static EncodedImage userPlaceholder; 
    private EncodedImage roundPlaceholder;
    private Image mask;
    private static String tokenPrefix;
    private java.util.List<String> recentContacts;
    private   ArrayList<User> contacts;
   // private Pubnub pb;
    private Image roundedMeImage;
    private final WeakHashMap<String, EncodedImage> roundedImagesOfFriends = new WeakHashMap<>();
    Groupe groupe=new Groupe();
         User userConn = LogIn.e;
         User user;
    /**
     * Includes messages that received ACK notices from the receiver
     */
//    private ArrayList<String> pendingAck = new ArrayList<>();
      
 
    public  ChatForm11(Resources theme,Groupe groupeSelectionnèe,User u) throws IOException{
      user=u;
       groupe=groupeSelectionnèe;
        System.out.println("uuuuuu");
           System.out.println(u);
              System.out.println("gggg");
                 System.out.println(groupe);
         theme = UIManager.initFirstTheme("/theme");
        f =new Form(user.getNom()+" "+user.getPrenom(),BoxLayout.y());
 f.setUIID("log");
        showChatForm(u);
//f.show();
    }

 

 
     
   void showChatForm(User d) {
       // Form chatForm = new Form(d.getNom()+"1111");

        // this identifies the person we are chatting with, so an incoming message will know if this is the right person...
        //chatForm.putClientProperty("cid", tokenPrefix + d.getId());
        f.setLayout(new BorderLayout());
        Toolbar tb = new Toolbar();
        final Container chatArea = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        chatArea.setScrollableY(true);
          
        chatArea.setName("ChatArea");
                      f.getToolbar().addCommandToRightBar("Back",null, new ActionListener() {
   @Override
            public void actionPerformed(ActionEvent evt) {
                ListeAmis listeAmis = new ListeAmis(theme,groupe);
         listeAmis.getF().show();
            }});
      
      
     

  
        // we type the message to the chat partner in the text field on the south side
        TextField write = new TextField(30);
        write.setHint("Write to " + d.getNom());
        
        Container ct=new  Container(new BoxLayout(BoxLayout.Y_AXIS));
        f.addComponent(BorderLayout.CENTER, ct);
      
        
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(5000);
                                ConnectionRequest con2 = new ConnectionRequest();
//                            Image imgServer = URLImage.createToStorage(placeholder, u.getNom(), "http://localhost/Covoiturage/web/FO/images/user/" + u.getPhotoProfil());
                                con2.setUrl("http://localhost/script_sarra/Messages/selectallmessages.php?author_id=" + userConn.getId() + "&addressee_id=" + user.getId()+"&idGroupe="+groupe.getId());
                                con2.addResponseListener(new ActionListener<NetworkEvent>() {

                                    @Override
                                    public void actionPerformed(NetworkEvent evt) {

                                        List<Message> listmes =getallmess(new String(con2.getResponseData()));
                                        ct.removeAll();
                                        if (listmes.isEmpty()) {
                                            Label lbl = new Label("envoyer votre premier message à " + user.getNom());
                                            Label l = new Label(" ");
                                            ct.add(l);
                                            ct.revalidate();
                                        } else {
                                            for (Message m : listmes) {

                                                if (Integer.parseInt( m.getSenderId())== user.getId()) {
                                                    Label lbl = new Label(user.getNom() + " " + m.getMessage());
                                                    Label l = new Label(" ");
                                                     l.getStyle().setPaddingTop(1);
                                                    ct.add(lbl);
                                                    ct.add(l);
                                                    ct.revalidate();
                                                } else {
                                                    Label lbl = new Label(userConn.getNom() + " " + m.getMessage());
                                                    Label l = new Label(" ");
                                                         l.getStyle().setPaddingTop(1);
                                                    ct.add(lbl);
                                                    ct.add(l);
                                                    ct.revalidate();
                                                }
                                            }
                                        }
                                    }

                                });
                                NetworkManager.getInstance().addToQueue(con2);
                            } catch (InterruptedException ex) {
                                Log.getReportingLevel();
                            }
                            System.out.println("Server is running.....");
                        }
                    }
                });
            t1.start(); 
         Button b1 = new Button("send");
             Container ctn=new  Container(new BoxLayout(BoxLayout.X_AXIS)); 
             ctn.add(write);
ctn.add(b1);

  f.addComponent(BorderLayout.SOUTH, ctn);
        b1.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SEND, b1.getUnselectedStyle()));

//        f.getToolbar().addMaterialCommandToRightBar(user.getNom() + "  " + user.getPrenom(), FontImage.MATERIAL_PERSON, e -> {
//        });
        b1.addActionListener(x -> {

            ConnectionRequest con1 = new ConnectionRequest();
            con1.setUrl("http://localhost/script_sarra/Messages/sendmessage.php?author_id=" + userConn.getId() + "&addressee_id=" + user.getId() + "&messageText=" + write.getText()+"&idGroupe="+groupe.getId());
            con1.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        Label l = new Label(" ");
                        Label lbl = new Label(userConn.getNom() + "   :" + write.getText());
                        ct.add(lbl);
                        ct.add(l);
                        f.refreshTheme();
                    } else {
                        Dialog.show("Erreur", "erreur", "Ok", null);
                    }

                }
            });
            NetworkManager.getInstance().addToQueue(con1);

        });
        f.show();
    }
    
    private Component say(Container chatArea, String text) {
        Component t = sayNoLayout(chatArea, text);
        t.setY(chatArea.getHeight());
        t.setWidth(chatArea.getWidth());
        t.setHeight(40);
        chatArea.animateLayoutAndWait(300);        
        chatArea.scrollComponentToVisible(t);
        return t;
    }

    private Component sayNoLayout(Container chatArea, String text) {
        SpanLabel t = new SpanLabel(text);
      //  t.setIcon(roundedMeImage);
        t.setTextBlockAlign(Component.LEFT);
        t.setTextUIID("BubbleMe");
        chatArea.addComponent(t);
        return t;
    }

    private Container getChatArea(Container cnt) {
        String n = cnt.getName();
        if(n != null && n.equals("ChatArea")) {
            return cnt;
        }
        
        for(Component cmp : cnt) {
            if(cmp instanceof Container) {
                Container cur = getChatArea((Container)cmp);
                if(cur != null) {
                    return cur;
                }
            }
        }
        return null;
    }

 
                
         public Form getF() {
        return f;
    }
         public List<Message> getallmess(String json){
        ArrayList<Message> messagess = new ArrayList<Message>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> publications = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) publications.get("messages");
            System.out.println(list);
                  
            for (Map<String, Object> obj : list) {
                
               Message p  = new Message();
                
                p.setSenderId(obj.get("author_id").toString());
                p.setRecepientId(obj.get("addressee_id").toString());
                p.setMessage(obj.get("messageText").toString());
               
    

   
                messagess.add(p);

            }

        } catch (IOException ex) {
         }
        return messagess;
 }
         
}
