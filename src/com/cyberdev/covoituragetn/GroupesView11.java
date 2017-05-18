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
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entity.Groupe;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;





/**
 *
 * @author esprit
 */
public class GroupesView11 {
    
 Form f;
       Command cmd1, cmd2,cmd4, cmdBack,cmd3;
       ImageViewer img;
     private ConnectionRequest connectionRequest;
     UIBuilder ui = new UIBuilder();
       Groupe groupe = new Groupe();
       User user=LogIn.e;
       Image  img1;
Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);
   public  GroupesView11(Resources theme){
     f =new Form("Liste des Groupes",BoxLayout.y());
   Toolbar.setGlobalToolbar(true);
ContainerList CntList=new ContainerList();
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
         cmd1 = new Command("Mon profil");
        cmd2 = new Command("Mes alertes");
  cmd3 = new Command("Mes groupes");
        cmd4 = new Command("Page 4");
        cmdBack = new Command("Back");
         f.getToolbar().addCommandToSideMenu("Mes groupes", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           MesGroupes mesGroupes = new MesGroupes(theme);
           mesGroupes.getF().show();
            }
        });
       ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/script_sarra/groupes/selectAll.php");
   
      
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
           ArrayList<Groupe> groupes=getListGroupes(new String(con.getResponseData()));

    for(Groupe gr : groupes ){
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
       
       Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Label lbl1 = new Label(gr.getNom());
         lbl1.getAllStyles().setBgTransparency(1);
    lbl1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
       Label lbl2 = new Label(gr.getDescription());
  final Label cadrephoto=new Label();
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
      
    }
    f.show();

            }
        });
        NetworkManager.getInstance().addToQueue(con);

     
     
 }
     public ArrayList<Groupe> getListGroupes(String json) {
        
        ArrayList<Groupe> listeGroupes = new ArrayList<>();
   
      
         
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> groupes = j.parseJSON(new CharArrayReader(json.toCharArray()));
  
 List<Map<String, Object>> list = (List<Map<String, Object>>) groupes.get("groupe");

         for (Map<String, Object> obj : list) {
              
              Groupe groupe=new Groupe();
          
groupe.setNom(obj.get("nom").toString());
groupe.setDescription(obj.get("description").toString());
 listeGroupes.add(groupe);

       //      MultiButton Multibtn=new MultiButton();
            // Multibtn.setTextLine1(groupe.getNom());
           //  Multibtn.setEmblem(emblem);
            // Multibtn.setIcon(icone);
         //    Multibtn.setTextLine2(groupe.getNom());
           //  sp.setText("  jh,kop^ùioyutrg");
        
          //   Multibtn.setTextLine3(groupe.getDescription());
             
      //  Multibtn.setIconName("");
         //    f.add(Multibtn);
     //     System.out.println(listeGroupes);
         }
           //   f.show();
        } catch (IOException ex) {
         }
     
      return listeGroupes;
     

    }
  public Form getF() {
        return f;
    }   
}
