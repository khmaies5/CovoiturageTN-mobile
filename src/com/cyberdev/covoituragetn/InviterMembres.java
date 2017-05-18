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
public class InviterMembres {
    Abonnes abonnes=new Abonnes(); 
Form f;
       Command cmd1, cmd2,cmd4, cmdBack,cmd3;
       ImageViewer img;
     private ConnectionRequest connectionRequest;
     UIBuilder ui = new UIBuilder();
       Groupe groupe = new Groupe();
       User userConn=LogIn.e;
      // Button btnAjouter = new Button("Ajouter");
      

Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *3, p.getHeight() * 3), false);
   public  InviterMembres(Resources theme,Groupe groupeSelectionnèe){
     f =new Form("Inviter des membres",BoxLayout.y());
     f.setUIID("log");
     groupe=groupeSelectionnèe;
   Toolbar.setGlobalToolbar(true);
     ArrayList<User> listusers = new ArrayList<>();

   Tabs tab=new Tabs();
Container ctnLstUers= new Container(new BoxLayout(BoxLayout.Y_AXIS));
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
    
        con4.setUrl("http://localhost/script_sarra/abonnement/CountUserNonAbonneByIdGroupe.php?idGroupe="+groupe.getId());
        con4.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
int nb=getCountElement(new String(con4.getResponseData()));
      
         ConnectionRequest con = new ConnectionRequest();
    
        con.setUrl("http://localhost/script_sarra/abonnement/SelectUserNonAbonneByIdGroupe.php?idGroupe="+groupe.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                          if(nb>1)  {
    ArrayList<User>   listusers=getlistUserNonAbonnByIdGroupe(new String(con.getResponseData()));
for(User user:listusers)
{
  Button btnOk = new Button("Inviter");
  ConnectionRequest con2 = new ConnectionRequest();
    
        con2.setUrl("http://localhost/script_sarra/invitation/SelectInvitationEnvByUserConnAndUerInVAndIdGroupe.php?idGroupe="+groupe.getId()+"&userConn="+userConn.getId()+"&userInve="+user.getId());
        con2.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                       int  invitation=getInvitations(new String(con2.getResponseData()));  
                       System.out.println(invitation);
                         if(invitation!=-1){btnOk.setText("User deja invitè");
                         btnOk.setEnabled(false);
                         }
                           else{btnOk.setText("Inviter");
                         btnOk.setEnabled(true);
                         }
                           
                           
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
         { cadrephoto.setIcon(URLImage.createToStorage(placeholder, "woman.jpg", "http://localhost/Covoiturage/web/FO/images/woman.png"));}
                else
         { cadrephoto.setIcon(URLImage.createToStorage(placeholder, "men.jpg", "http://localhost/Covoiturage/web/FO/images/woman.png"));}
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
        ConnectionRequest con3 = new ConnectionRequest();
        con3.setUrl("http://localhost/script_sarra/invitation/insert.php?idGroupe="+groupe.getId()+"&userConn="+userConn.getId()+"&userInve="+user.getId());
    
        
     con3.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Invitation envoyè avec succèe", "Ok", null);
                            InviterMembres i = new InviterMembres(theme, groupe);
                            i.getF().show();
                            
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
        
        NetworkManager.getInstance().addToQueue(con3);
    }
                                        });  
 
            }                   });
          NetworkManager.getInstance().addToQueue(con2);
    }}
                                                        else    if(nb==1)  {
User   user=getUserNonAbonnByIdGroupe(new String(con.getResponseData()));

  Button btnOk = new Button("Inviter");
  ConnectionRequest con2 = new ConnectionRequest();
    
        con2.setUrl("http://localhost/script_sarra/invitation/SelectInvitationEnvByUserConnAndUerInVAndIdGroupe.php?idGroupe="+groupe.getId()+"&userConn="+userConn.getId()+"&userInve="+user.getId());
        con2.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                       int  invitation=getInvitations(new String(con2.getResponseData()));  
                       System.out.println(invitation);
                         if(invitation!=-1){btnOk.setText("User deja invitè");
                         btnOk.setEnabled(false);
                         }
                           else{btnOk.setText("Inviter");
                         btnOk.setEnabled(true);
                         }
                           
                           
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                            
       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 
    //   Label lbl2 = new Label("Sexe:"+user.getSexe());
       Label lbl1 = new Label(user.getNom()+" "+user.getPrenom());
     Label lbl3 = new Label("Gouvernerat: "+user.getGouvernorat());
//       Label lbl3=new Label(sujet.getDatePublication().toString());
        
   Label cadrephoto=new Label();
     if( "null".equals(user.getPhoto_Profil()))   {
         if("femme".equals(user.getSexe()))
         { cadrephoto.setIcon(URLImage.createToStorage(placeholder, "woman.jpg", "http://localhost/Covoiturage/web/FO/images/woman.png"));}
                else
         { cadrephoto.setIcon(URLImage.createToStorage(placeholder, "man.jpg", "http://localhost/Covoiturage/web/FO/images/men.png"));}
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
        ConnectionRequest con3 = new ConnectionRequest();
        con3.setUrl("http://localhost/script_sarra/invitation/insert.php?idGroupe="+groupe.getId()+"&userConn="+userConn.getId()+"&userInve="+user.getId());
    
        
     con3.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Invitation envoyè avec succèe", "Ok", null);
                            InviterMembres i = new InviterMembres(theme, groupe);
                            i.getF().show();
                            
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
        
        NetworkManager.getInstance().addToQueue(con3);
    }
                                    });  
 
            }               });
          NetworkManager.getInstance().addToQueue(con2);
   }
                else    if(nb==01)  {

  
                            
       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 
    //   Label lbl2 = new Label("Sexe:"+user.getSexe());
       Label lbl1 = new Label("Aucun user à inviter");
   ctn2.add(lbl1);
    //
   
    
       ctn1.add(ctn2);
       ctn3.add(ctn1);

ctnLstUers.add(ctn3);

 
    }

   
               
   }
        });
               NetworkManager.getInstance().addToQueue(con);
   }    });  NetworkManager.getInstance().addToQueue(con4);
               f.add(ctnLstUers);
        f.show();

 }
 public ArrayList<User> getlistUserNonAbonnByIdGroupe(String json) {
       
        ArrayList<User> listUsers = new ArrayList<>();
        
   
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
             
           
      List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("user");
          for (Map<String, Object> obj : list) {
          User user =new User();
  user.setId(Integer.parseInt(obj.get("id").toString())); 
         user.setNom(obj.get("nom").toString()); 
          user.setPrenom(obj.get("prenom").toString()); 
          
             user.setGouvernorat(obj.get("gv").toString()); 
           user.setSexe(obj.get("sexe").toString()); 
          if(obj.get("photo").toString()=="");
          user.setPhoto_Profil(obj.get("photo_profil").toString()); 
 listUsers.add(user);

         }
 
     
           //   f.show();
        } catch (IOException ex) {
         }
     
      return listUsers;
     

    }
 public User getUserNonAbonnByIdGroupe(String json) {
       
      
        
   
   
      
           User user =new User();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
             
           
   Map<String, Object> list = (Map<String, Object>) users.get("user");
          
        
  user.setId(Integer.parseInt(list.get("id").toString())); 
         user.setNom(list.get("nom").toString()); 
          user.setPrenom(list.get("prenom").toString()); 
          
             user.setGouvernorat(list.get("gv").toString()); 
           user.setSexe(list.get("sexe").toString()); 
          user.setPhoto_Profil(list.get("photo_profil").toString()); 


      
 
     
           //   f.show();
        } catch (IOException ex) {
         }
     
      return user;
     

    }
   public int getInvitations(String json) {
        
     int etat = -1;
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> invitations = j.parseJSON(new CharArrayReader(json.toCharArray()));
        
          
           
 //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
        Map<String, Object> in = (Map<String, Object>) invitations.get("invitation");
      System.out.println(in );
 if(in==null){return etat;}
  else {
       //  for (Map<String, Object> obj : list) {
              
       etat=1;   
          
return etat;

//etat=Integer.parseInt(obj.get("etat").toString());
  //           System.out.println(etat);
}
      
        //   }
         
           //   f.show();
        } catch (IOException ex) {
         }
     
   //   return listeGroupes;
     return etat;

    }
public int getCountElement(String json) {

        int nb = -1;

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            //List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("abonnees");
            Map<String, Object> ab = (Map<String, Object>) groupes.get("user");

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
