/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.components.MultiButton;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import static com.cyberdev.covoituragetn.CovoiturageTN.decode;
import static com.cyberdev.covoituragetn.CovoiturageTN.getRoutesEncoded;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 * GUI builder created Form
 *
 * @author khmai
 */
public class AnnoncesView extends com.codename1.ui.Form {

        private static final String HTML_API_KEY = "AIzaSyA1QNU9aG7GL7Kg8b6strSze0OBq3XTIbc";
   public ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false);

    
    
    public AnnoncesView() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public AnnoncesView(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
this.add(BorderLayout.CENTER,list);
    }
    
      public void showSingleAnnonce(Annonce ann){
          
          
        Form form3 = new SingleAnnonceView(ann);
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
        r.setUrl("http://localhost/covoituragetn-api/selectPaginator.php");
        
        r.addArgument("page", "" +pageNumber);
        pageNumber++;
        System.out.println("page num "+pageNumber);
        NetworkManager.getInstance().addToQueueAndWait(r);
        Map<String,Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            
           try {
            for (int i = 0; i < ((ArrayList) response.get("annonce")).size(); i++) {
           Map<String,Object> ann = (Map<String,Object>) ((ArrayList) response.get("annonce")).get(i);
            annonces.add(new Annonce(Integer.parseInt(ann.get("id_annonce").toString()),formatter.parse(ann.get("trip_date").toString()),formatter.parse(ann.get("annonce_date").toString()),ann.get("lieu_depart").toString(),ann.get("lieu_arrive").toString(),Integer.parseInt(ann.get("nbr_personne").toString()),Float.parseFloat(ann.get("prix").toString()),ann.get("critere").toString(),ann.get("distance").toString(),new User(Integer.parseInt(ann.get("id_user").toString())),ann.get("photo_profil").toString()));
            

  
      }
                    
           } catch (ParseException e) {
            e.printStackTrace();
        }

        return (java.util.List<Map<String, Object>>)response.get("annonce");
    } catch(Exception err) {
        //System.out.println("cuase "+err.getMessage());
        Log.e(err);
        return null;
    }
}
    

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("AnnoncesView");
        setName("AnnoncesView");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    
    
    InfiniteContainer list = new InfiniteContainer() {
        @Override
        public Component[] fetchComponents(int index, int amount) {
                       // getAnnonces();

            java.util.List<Map<String, Object>> data = fetchPropertyData();
                        System.out.println("data "+data.size());

             MultiButton[] cmps = new MultiButton[data.size()];
            for(int iter = 0 ; iter < cmps.length ; iter++) {
                Map<String, Object> currentListing = data.get(iter);
                if(currentListing == null) {
                    return null;
                }
                
                String thumb_url = "http://localhost/covoituragetn/"+(String)currentListing.get("photo_profil");
                String guid = (String)currentListing.get("lieu_arrive");
                String summary = (String)currentListing.get("lieu_depart");
                cmps[iter] = new MultiButton(summary);
                cmps[iter].setIcon(URLImage.createToStorage(placeholder, guid, thumb_url));
                                    Annonce a = annonces.get(iter);

                cmps[iter].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
              showSingleAnnonce(a);
              
              System.out.println("test "+a.getLieuDepart());
          }
      });
            }
         /*   for(Annonce a : annonces) {
    createAnnonceContainer(a);
}*/
            return cmps;
        }
    };
    
}
