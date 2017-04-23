/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberdev.covoituragetn;

import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.util.Callback;
import static com.cyberdev.covoituragetn.CovoiturageTN.decode;
import static com.cyberdev.covoituragetn.CovoiturageTN.getRoutesEncoded;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GUI builder created Form
 *
 * @author khmai
 */
public class SingleAnnonceView extends com.codename1.ui.Form {
        private static final String HTML_API_KEY = "AIzaSyA1QNU9aG7GL7Kg8b6strSze0OBq3XTIbc";

        
    public SingleAnnonceView() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
     public SingleAnnonceView(Annonce ann) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        gui_From.startTicker();
        gui_From.setText(ann.getLieuDepart());
        gui_To.setText(ann.getLieuArriver());
        gui_Date.setText(ann.getTripDate().toString());
        gui_Distance.setText(ann.getDistance());
        gui_Prix.setText(""+ann.getPrix()+"Dt");
        gui_NbrPlace.setText(""+ann.getNbrPersonne());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        
        gui_MapContainer.add(cnt);
        String encoded = getRoutesEncoded(ann.getLieuDepart(), ann.getLieuArriver());
        // decode the routes in an arry of coords

        Coord[] coords = decode(encoded);
        cnt.zoom(coords[0], 5);
        

            System.out.println("cords "+coords);

        cnt.addPath(coords);
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
    private com.codename1.ui.Container gui_FromToLable = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_From = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Icon = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_To = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_DetailsContainer = new com.codename1.ui.Container(new com.codename1.ui.table.TableLayout(4, 2));
    private com.codename1.ui.Label gui_ = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Date = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Prix1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Prix = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_NbrPlace1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_NbrPlace = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Distance1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Distance = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_MapContainer = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("SingleAnnonceView");
        setName("SingleAnnonceView");
        addComponent(gui_FromToLable);
        gui_FromToLable.setName("FromToLable");
        gui_FromToLable.addComponent(gui_From);
        gui_FromToLable.addComponent(gui_Icon);
        gui_FromToLable.addComponent(gui_To);
        gui_From.setText("Label");
        gui_From.setName("From");
        gui_From.setGap(1);
        gui_From.setTextPosition(com.codename1.ui.Component.LEFT);
        gui_From.setPropertyValue("maskName", "");
        gui_Icon.setName("Icon");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Icon,'');
        gui_To.setText("Label");
        gui_To.setName("To");
        gui_To.setGap(1);
        gui_To.setTextPosition(com.codename1.ui.Component.LEFT);
        addComponent(gui_DetailsContainer);
        gui_DetailsContainer.setName("DetailsContainer");
        com.codename1.ui.table.TableLayout.Constraint Constraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(Constraint, gui_);
        com.codename1.ui.table.TableLayout.Constraint DateConstraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(DateConstraint, gui_Date);
        com.codename1.ui.table.TableLayout.Constraint Prix1Constraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(Prix1Constraint, gui_Prix1);
        com.codename1.ui.table.TableLayout.Constraint PrixConstraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(PrixConstraint, gui_Prix);
        com.codename1.ui.table.TableLayout.Constraint NbrPlace1Constraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(NbrPlace1Constraint, gui_NbrPlace1);
        com.codename1.ui.table.TableLayout.Constraint NbrPlaceConstraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(NbrPlaceConstraint, gui_NbrPlace);
        com.codename1.ui.table.TableLayout.Constraint Distance1Constraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(Distance1Constraint, gui_Distance1);
        com.codename1.ui.table.TableLayout.Constraint DistanceConstraint = ((com.codename1.ui.table.TableLayout)gui_DetailsContainer.getLayout()).createConstraint(-1, -1);
        gui_DetailsContainer.addComponent(DistanceConstraint, gui_Distance);
        gui_.setText("Date Depart:");
        gui_.setName("");
        com.codename1.ui.FontImage.setMaterialIcon(gui_,'');
        gui_Date.setText("Label");
        gui_Date.setName("Date");
        gui_Prix1.setText("Prix/Personne:");
        gui_Prix1.setName("Prix1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Prix1,'');
        gui_Prix.setText("Label");
        gui_Prix.setName("Prix");
        gui_NbrPlace1.setText("Nombre de place:");
        gui_NbrPlace1.setName("NbrPlace1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_NbrPlace1,'');
        gui_NbrPlace.setText("Label");
        gui_NbrPlace.setName("NbrPlace");
        gui_Distance1.setText("Distance:");
        gui_Distance1.setName("Distance1");
        gui_Distance1.setGap(2);
        gui_Distance1.setTextPosition(com.codename1.ui.Component.RIGHT);
        com.codename1.ui.FontImage.setMaterialIcon(gui_Distance1,'');
        gui_Distance.setText("Label");
        gui_Distance.setName("Distance");
        gui_Distance.setTextPosition(com.codename1.ui.Component.RIGHT);
        addComponent(gui_MapContainer);
        gui_FromToLable.setName("FromToLable");
        gui_DetailsContainer.setName("DetailsContainer");
        gui_MapContainer.setName("MapContainer");
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

                if (routes.size() > 0)

                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                
                    System.out.println("distance "+((LinkedHashMap) ((LinkedHashMap)((ArrayList) ((LinkedHashMap)((ArrayList) response.get("routes")).get(0)).get("legs")).get(0)).get("distance")).get("text").toString());
                                       
   
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

                    if (routes.size() > 0)

                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();

                }

                callback.onSucess(ret);

            }





        };

        request.addArgument("key", HTML_API_KEY);

        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());

        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());



        NetworkManager.getInstance().addToQueue(request);

    }

}
