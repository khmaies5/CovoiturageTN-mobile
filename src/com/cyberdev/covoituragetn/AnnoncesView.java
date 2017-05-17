/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import entity.User;
import entity.Annonce;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import org.littlemonkey.connectivity.Connectivity;

/**
 * GUI builder created Form
 *
 * @author khmai
 */
public class AnnoncesView extends com.codename1.ui.Form {

        private static final String HTML_API_KEY = "AIzaSyCTbLzGGtFx0vn093oLmljzIxHoZ9MjuaE";
   public ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    Style s = UIManager.getInstance().getComponentStyle("M");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

    
    
    public AnnoncesView() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public AnnoncesView(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
         if (Connectivity.isConnected()) {
        FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        nextForm.addActionListener(e->{
            if(LogIn.isLoggedIn()){
            Form ajouter = new AjouterAnnonce();
            setBackCommand(ajouter);
            ajouter.show();
            } else {
                Form logIn = new LogIn();
                setBackCommand(logIn);
                logIn.show();
            }

        });
        
         Container root = LayeredLayout.encloseIn(

                BorderLayout.center(nextForm.bindFabToContainer(list))

               

        );
        
this.add(BorderLayout.CENTER,root);
         } else Dialog.show("Connection Error", "please check your connection and try again", "ok", null);
//this.add(BorderLayout.SOUTH,nextForm);
    }
    
    

    
      public void showSingleAnnonce(String id){
          Annonce ann = new Annonce();
          int idAnn = Integer.parseInt(id);
          for (int i = 0; i < annonces.size(); i++) {
              Annonce get = annonces.get(i);
              if (get.getIdAnnonce() == idAnn){
                  ann = get;
              }
              
          }
          
        Form form3 = new SingleAnnonceView(ann);
          setBackCommand(form3);
       // form3.setLayout(new BorderLayout());
       
       // setBackCommand(this);
        
        Container mapContainer = new Container();
        mapContainer.setHeight(100);
        mapContainer.setShouldCalcPreferredSize(true);
      
         
      
        form3.show();
    }
    
    
        int pageNumber = 1;
java.util.List<Map<String, Object>> fetchPropertyData() {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        ConnectionRequest r = new ConnectionRequest();
        r.setPost(false);
        r.setUrl("http://localhost/CovoiturageTN-API/selectPaginator.php");
        
        r.addArgument("page", "" +pageNumber);
        pageNumber++;
        System.out.println("page num "+pageNumber);
        NetworkManager.getInstance().addToQueueAndWait(r);
        Map<String,Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            System.out.println("response "+response);
           try {
            for (int i = 0; i < ((ArrayList) response.get("annonce")).size(); i++) {
           Map<String,Object> ann = (Map<String,Object>) ((ArrayList) response.get("annonce")).get(i);
            annonces.add(new Annonce(Integer.parseInt(ann.get("id_annonce").toString()),formatter.parse(ann.get("trip_date").toString()),formatter.parse(ann.get("annonce_date").toString()),ann.get("lieu_depart").toString(),ann.get("lieu_arrive").toString(),Integer.parseInt(ann.get("nbr_personne").toString()),Float.parseFloat(ann.get("prix").toString()),ann.get("critere").toString(),ann.get("distance").toString(),new User(Integer.parseInt(ann.get("id_user").toString())),ann.get("photo_profil").toString()));
            

  
      }
                    
           } catch (ParseException e) {
Dialog.show("Erreur", "erreur check your connection", "Ok", null);           
// e.printStackTrace();
        }

        return (java.util.List<Map<String, Object>>)response.get("annonce");
    } catch(Exception err) {
        //System.out.println("cuase "+err.getMessage());
        Dialog.show("Erreur", "erreur check your connection", "Ok", null);
        //Log.e(err);
        return null;
    }
}
    

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setUIID("AnnoncesForm");
        setTitle("Annonces View");
        setName("AnnoncesView");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
    
    InfiniteContainer list = new InfiniteContainer() {
        
        
       

        
        @Override
        public Component[] fetchComponents(int index, int amount) {
                       // getAnnonces();

            java.util.List<Map<String, Object>> data = fetchPropertyData();
                        System.out.println("data "+data.size());
                        if(data.size()==0){
                            pageNumber=1;
                        }

             MultiButton[] cmps = new MultiButton[data.size()];
            for(int iter = 0 ; iter < cmps.length ; iter++) {
                Map<String, Object> currentListing = data.get(iter);
                if(currentListing == null) {
                    return null;
                }
                
                String thumb_url = "http://localhost/covoituragetn/"+(String)currentListing.get("photo_profil");
                String lieuArriver = (String)currentListing.get("lieu_arrive");
                String username =(String)currentListing.get("username");
                System.out.println("username"+username);
                String lieuDepart = (String)currentListing.get("lieu_depart");
                cmps[iter] = new MultiButton(username);
                cmps[iter].setTextLine2(lieuDepart+"->"+lieuArriver);
                cmps[iter].setTextLine3((String)currentListing.get("trip_date"));
                cmps[iter].setIcon(URLImage.createToStorage(placeholder,username, thumb_url));
                

                cmps[iter].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
              showSingleAnnonce(currentListing.get("id_annonce").toString());
              
              //System.out.println("test "+a.getLieuDepart());
          }
      });
            }
            
         /*   for(Annonce a : annonces) {
    createAnnonceContainer(a);
}*/
            return cmps;
        }

    };
    
          protected void setBackCommand(Form f) {
        Command back = new Command("") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                CovoiturageTN.home.showBack();
            }

        };
        Image img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
        back.setIcon(img);
        f.getToolbar().addCommandToLeftBar(back);
        f.getToolbar().setTitleCentered(true);
        f.setBackCommand(back);
    }
    
}
