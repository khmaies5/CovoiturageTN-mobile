/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.littlemonkey.connectivity.Connectivity;


/**
 * GUI builder created Form
 *
 * @author khmai
 */
public class LogIn extends com.codename1.ui.Form {

              static User e = new User();
               static String s = "";
               AnnoncesView annoncesView = new AnnoncesView();

    public LogIn() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public LogIn(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
        
            //e = new User();
// Form f =new Form("login",BoxLayout.y());
  
gui_Label_1.setIcon(resourceObjectInstance.getImage("LogoCovTN.png"));
   ConnectionRequest con = new ConnectionRequest();

        gui_Button_1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 if (Connectivity.isConnected()){
                 if((gui_tfUserName.getText().length()!=0 )||( gui_tfPassword.getText().length()!= 0)){
                 con.setUrl("http://localhost/CovoiturageTN-API/login.php?login="+gui_tfUserName.getText()+"&password="+gui_tfPassword.getText());
              // f.show();
               //Map<String,Object> kh = (Map<String, Ob;
                 con.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) {
                           String response = new String(con.getResponseData()) ;
                           getListuser(new String(con.getResponseData()), con);
                      if (s.equals("sucess")){
                          
                      
                                   ToastBar.showMessage("Welcome " + gui_tfUserName.getText(), FontImage.MATERIAL_MOOD, 5000);
                                   CovoiturageTN cov = new CovoiturageTN();
                        cov.start();
                                   
                                   //  Dialog.show("login", "login", "ok",null);
                                   
                                   //  l.setText(e.getNom());
                                   //  f1.show();
                              
                      } else Dialog.show("Error", "username or password incorrect", "ok",null);
                         
                     }
                 });
               NetworkManager.getInstance().addToQueue(con);
                 } else Dialog.show("Error", "username or password incorrect", "ok",null);
                 
             } else Dialog.show("Connection Error", "please check your connection and try again", "ok", null);
             }
         });
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.TextField gui_tfUserName = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_tfPassword = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setUIID("LogInForm");
        setTitle("");
        setName("LogIn");
        addComponent(gui_Container_2);
        gui_Container_2.setUIID("LogoContainer");
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Label_1);
        gui_Label_1.setUIID("LogoLabel");
        gui_Label_1.setName("Label_1");
        addComponent(gui_Container_1);
        gui_Container_1.setUIID("ContainerLogIn");
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_tfUserName);
        gui_Container_1.addComponent(gui_tfPassword);
        gui_Container_1.addComponent(gui_Button_1);
        gui_tfUserName.setHint("Username");
        gui_tfUserName.setName("tfUserName");
        gui_tfUserName.setHintIcon(com.codename1.ui.FontImage.createMaterial('', gui_tfUserName.getUnselectedStyle()));
        gui_tfPassword.setHint("Password");
        gui_tfPassword.setName("tfPassword");
        gui_tfPassword.setHintIcon(com.codename1.ui.FontImage.createMaterial('', gui_tfPassword.getUnselectedStyle()));
        gui_tfPassword.setConstraint(com.codename1.ui.TextArea.PASSWORD);
        gui_Button_1.setText("Log In ");
        gui_Button_1.setUIID("LogInButton");
        gui_Button_1.setName("Button_1");
        gui_Button_1.setTextPosition(com.codename1.ui.Component.LEFT);
        gui_Container_2.setUIID("LogoContainer");
        gui_Container_2.setName("Container_2");
        gui_Container_1.setUIID("ContainerLogIn");
        gui_Container_1.setName("Container_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
    
    
     public void getListuser(String json,ConnectionRequest con) {
        ArrayList<User> listEtudiants = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants =  j.parseJSON(new CharArrayReader(json.toCharArray()));
           //Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(con.getResponseData()), "UTF-8"));
                       // System.out.println("list "+etudiants);
                       // System.out.println("ann "+((ArrayList) etudiants.get("euser")).get(0));

Map<String,Object> ann = (Map<String,Object>) ( etudiants.get("user"));

            System.out.println("ann "+ann);
             e.setId(Integer.parseInt(ann.get("id").toString()));
                         System.out.println("id "+ann.get("id").toString());
                         

             e.setNom(ann.get("username").toString());
             e.setEmail(ann.get("email").toString());
             
             s=ann.get("sucess").toString();
             listEtudiants.add(e);
           /* System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("euser");
            for (Map<String, Object> obj : list) {
                User e = new User();
             e.setId(Integer.parseInt(obj.get("id").toString()));
             e.setNom(obj.get("username").toString());
                listEtudiants.add(e);

            }*/

        } catch (IOException ex) {
         }

    }
     
     public static boolean isLoggedIn(int id){
         
         if(s.equals("sucess")&&e.getId() == id){
             return true;
         } else return false;
     }
     
     public static boolean isLoggedIn(){
         if(s.equals("sucess")){
             return true;
         } else return false;
     }
}


