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
import com.codename1.ui.TextField;
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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sarra
 */
public class InviterMembres1 {
    Abonnes abonnes=new Abonnes(); 
Form f;
       Command cmd1, cmd2,cmd4, cmdBack,cmd3;
       ImageViewer img;
     private ConnectionRequest connectionRequest;
     UIBuilder ui = new UIBuilder();
       Groupe groupe = new Groupe();
       User userConn=LogIn.e;
       Button btnAjouter = new Button("Ajouter");
      

Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *3, p.getHeight() * 3), false);
   public  InviterMembres1(Resources theme,Groupe groupeSelectionnèe){
     f =new Form("Inviter des membres",BoxLayout.y());
     groupe=groupeSelectionnèe;
   Toolbar.setGlobalToolbar(true);
     ArrayList<User> listusers = new ArrayList<>();
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
   Tabs tab=new Tabs();
Container ctnLstUers= new Container(new BoxLayout(BoxLayout.Y_AXIS));
         f.getToolbar().addCommandToSideMenu("Mes groupes", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           MesGroupes mesGroupes = new MesGroupes(theme);
           mesGroupes.getF().show();
            }
        });
     
    

        Button btnOk = new Button("Inviter");

      
         ConnectionRequest con = new ConnectionRequest();
    
        con.setUrl("http://localhost/script_sarra/abonnement/SelectUserNonAbonneByIdGroupe.php?idGroupe="+groupe.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
              
    ArrayList<User>   listusers=getUserNonAbonnByIdGroupe(new String(con.getResponseData()));
for(User user:listusers)
{


                            
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                            
       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 
    //   Label lbl2 = new Label("Sexe:"+user.getSexe());
       Label lbl1 = new Label(user.getNom()+" "+user.getPrenom());
     Label lbl3 = new Label("Gouvernerat: "+user.getGouvernorat());
//       Label lbl3=new Label(sujet.getDatePublication().toString());
        
   Label cadrephoto=new Label();
     if(user.getPhoto_Profil()==null)   {
         if(user.getSexe()=="femme")
         { cadrephoto.setIcon(URLImage.createToStorage(placeholder, "woman.jpg", "http://localhost/Covoiturage/web/FO/images/woman.jpg"));}
                else
         { cadrephoto.setIcon(URLImage.createToStorage(placeholder, "men.jpg", "http://localhost/Covoiturage/web/FO/images/men.jpg"));}
     }
       else  {
       
         cadrephoto.setIcon(URLImage.createToStorage(placeholder, user.getPhoto_Profil(), "http://localhost/Covoiturage/web/FO/images/user/"+user.getPhoto_Profil()));
     }
       ctn2.add(lbl1);
    //   ctn2.add(lbl2);
        ctn2.add(lbl3);
  ctn2.add(btnOk);
   
      ctn1.add(cadrephoto);
       ctn1.add(ctn2);
       ctn3.add(ctn1);

ctnLstUers.add(ctn3);
      // f.add(ctn3);
      System.out.println(user);
               btnOk.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/script_sarra/invitation/insert.php?idGroupe="+groupe.getId()+"&userConn="+userConn.getId()+"&userInve="+user.getId());
    
        
     con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Invitation envoyè avec succèe", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
        
        NetworkManager.getInstance().addToQueue(con);
    }
});  
 

    }

   
               
   }
        });
               NetworkManager.getInstance().addToQueue(con);
               f.add(ctnLstUers);
        f.show();

 }
 public ArrayList<User> getUserNonAbonnByIdGroupe(String json) {
       
        ArrayList<User> listUsers = new ArrayList<>();
        
   
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
              System.out.println(users.size());

         
      if(users.size()>1)
      {  List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("user");
          for (Map<String, Object> obj : list) {
          User user =new User();
  user.setId(Integer.parseInt(obj.get("id").toString())); 
         user.setNom(obj.get("nom").toString()); 
          user.setPrenom(obj.get("prenom").toString()); 
          
             user.setGouvernorat(obj.get("gv").toString()); 
           user.setSexe(obj.get("sexe").toString()); 
          if(obj.get("photo").toString()=="");
          { user.setPhoto_Profil(null); }
 listUsers.add(user);

         }}
      else if(users.size()==1)
      {          User user =new User();
               Map<String, Object> u = (Map<String, Object>) users.get("user");    
        user.setId(Integer.parseInt(u.get("id").toString())); 
         user.setNom(u.get("nom").toString()); 
          user.setPrenom(u.get("prenom").toString()); 
          
             user.setGouvernorat(u.get("gv").toString()); 
           user.setSexe(u.get("sexe").toString()); 
          if(u.get("photo").toString()=="");
          { user.setPhoto_Profil(null); }
 listUsers.add(user);
      }
     
           //   f.show();
        } catch (IOException ex) {
         }
     
      return listUsers;
     

    }
  public Form getF() {
        return f;
    }   
}
