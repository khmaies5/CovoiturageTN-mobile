package com.cyberdev.covoituragetn;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Calendar;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.StringUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AjouterAnnonce extends Form {

    private static final String HTML_API_KEY = "AIzaSyCTbLzGGtFx0vn093oLmljzIxHoZ9MjuaE";
    Annonce ann = new Annonce();
//NumericSpinner NbrPlace = new NumericSpinner();
    // DateTimeSpinner TripDate = new DateTimeSpinner();
    Picker TripDate = new Picker();
    Picker NbrPlace = new Picker();
    static int prix = 0;
    String critere = "";
    private String crit1, crit2, crit3, crit4;
    CheckBox cb1 = null;
    CheckBox cb2 = null;
    CheckBox cb3 = null;
    CheckBox cb4 = null;

    public AjouterAnnonce() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AjouterAnnonce(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        TripDate.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        NbrPlace.setType(Display.PICKER_TYPE_STRINGS);
        NbrPlace.setStrings("1", "2", "3", "4");
        NbrPlace.setSelectedString("1");

        cb1 = CheckBox.createToggle(resourceObjectInstance.getImage("animal.png"));
        cb2 = CheckBox.createToggle(resourceObjectInstance.getImage("music.png"));
        cb3 = CheckBox.createToggle(resourceObjectInstance.getImage("fun_talking.png"));
        cb4 = CheckBox.createToggle(resourceObjectInstance.getImage("smoking.png"));

        cb1.setPressedIcon(resourceObjectInstance.getImage("noanimal.png"));
        cb2.setPressedIcon(resourceObjectInstance.getImage("nomusic.png"));
        cb3.setPressedIcon(resourceObjectInstance.getImage("littletalking.png"));
        cb4.setPressedIcon(resourceObjectInstance.getImage("nosmoking.png"));
        cb1.setReleaseRadius(0);
       


        gui_Container_3.add(cb1).add(cb2).add(cb3).add(cb4);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //TripDate.setStartDate(new Date());
        TripDate.setDate(new Date());
        TripDate.setFormatter(formatter);
        //TripDate.addPointerPressedListener((t)->{System.out.println("date"+TripDate.getCurrentDate());});
        //TripDate.setEndDate(new Date());
//        NbrPlace.setPropertyValue("min", (double) 1.0);
//        NbrPlace.setPropertyValue("max", (double) 5.0);
//        NbrPlace.setPropertyValue("value", (double) 1.0);
//        NbrPlace.setPropertyValue("step", (double) 1.0);
        gui_NbrPlace.add(BorderLayout.CENTER, NbrPlace);
        gui_TripDateTime.add(BorderLayout.CENTER, TripDate);

        final DefaultListModel<String> options = new DefaultListModel<>();

        AutoCompleteTextField from = new AutoCompleteTextField(options) {

            @Override

            protected boolean filter(String text) {

                if (text.length() == 0) {

                    return false;

                }

                String[] l = searchLocations(text);

                if (l == null || l.length == 0) {

                    return false;

                }

                options.removeAll();

                for (String s : l) {

                    options.addItem(s);

                }

                return true;

            }

        };
        final DefaultListModel<String> options2 = new DefaultListModel<>();

        AutoCompleteTextField to = new AutoCompleteTextField(options2) {

            @Override

            protected boolean filter(String text) {

                if (text.length() == 0) {

                    return false;

                }

                String[] l = searchLocations(text);

                if (l == null || l.length == 0) {

                    return false;

                }

                options2.removeAll();

                for (String s : l) {

                    options2.addItem(s);

                }

                return true;

            }

        };

        gui_Container_1.add(BorderLayout.SOUTH,from);
        gui_Container_1_1_1.add(BorderLayout.SOUTH,to);
        //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
       cnt.setmapSize(200, 100);
        //cnt.setPreferredH(70);
        // gui_Container_Map.setHeight(cnt.getHeight());
        gui_Container_Map.add(cnt);
        Calendar cal = new Calendar();

        TripDate.addActionListener((evt) -> {
            if ((int) (TripDate.getDate().getTime() - new Date().getTime()) < 0) {
                Dialog.show("Error", "Date caanot be in the past", "Ok", null);
                TripDate.setDate(new Date(new Date().getTime() + 1 * 24 * 60 * 60 * 1000));
            }
        });

        Validator v = new Validator();
        v.addConstraint(from, new LengthConstraint(5, "you must choose from th autocomplete"));
        v.addConstraint(to, new LengthConstraint(5, "you must choose from th autocomplete"));
        v.addConstraint(NbrPlace, new LengthConstraint(1, "this cannot be empty"));
        v.addSubmitButtons(gui_SubmitButton);

        v.setShowErrorMessageForFocusedComponent(true);

//        automoveToNext(from, to);
//        automoveToNext(to, from);
        to.addActionListener((evt) -> {
            if (to.getText().length() > 0) {
                //annonce route
                cnt.calculateAndDisplayAnnonceRoute(from.getText(), to.getText());
                gui_DistanceLabel.setText(getRoutesEncoded(from.getText(), to.getText()));
                prix = calculatPrice(gui_DistanceLabel.getText());
                ToastBar.showMessage("Price calculated = " + prix + "Dt", FontImage.MATERIAL_EVENT_SEAT, 5000);

                //System.out.println("prix " + prix);
            } else {
                Dialog.show("Wrong destination", "you must choose your detination from autocomplete", "ok", null);
            }

        });
        Style s = UIManager.getInstance().getComponentStyle("M");
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        gui_SubmitButton.addActionListener((evt) -> {

            if (!v.isValid()) {
                Dialog.show("Error", "Some fields are not correct", "ok", null);
            }

            checkCrit();
            ann = new Annonce(TripDate.getDate(), from.getText(), to.getText(), Integer.parseInt(NbrPlace.getText()), prix, critere, gui_DistanceLabel.getText(), new User(LogIn.e.getId()));

            insertAnnonce();
            Form form3 = new SingleAnnonceView(ann);
            form3.show();

        });

        //gui_MapContainer.setPreferredH(200);
    }

    private void checkCrit() {
        if (cb1.isSelected()) {
            crit1 = ";noanimal";
        } else {
            crit1 = ";animal";
        }
        if (cb2.isSelected()) {
            crit2 = ";nomusic";
        } else {
            crit2 = ";music";
        }
        if (cb3.isSelected()) {
            crit3 = ";littletalking";
        } else {
            crit3 = ";fun_talking";
        }
        if (cb4.isSelected()) {
            crit4 = ";nosmoking";
        } else {
            crit4 = ";smoking";
        }
        critere = crit1 + crit2 + crit3 + crit4;
        System.out.println("critere " + critere);
    }

    private void automoveToNext(final TextField current, final TextField next) {
        current.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                if (current.getText().length() == 5) {
                    Display.getInstance().stopEditing(current);
                    String val = current.getText();
                    current.setText(val.substring(0, 4));
                    next.setText(val.substring(4));
                    Display.getInstance().editString(next, 5, current.getConstraint(), next.getText());
                }
            }
        });
    }

    private void insertAnnonce() {
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(new Date());
        String tripDate = sdf.format(ann.getTripDate());

        try {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://apicov.khstudio.tn/insert.php");
            r.addArgument("id_user", "" + ann.getCreator().getId());
            r.addArgument("trip_date", tripDate);
            r.addArgument("annonce_date", currentTime);
            r.addArgument("lieu_depart", ann.getLieuDepart());
            r.addArgument("lieu_arrive", ann.getLieuArriver());
            r.addArgument("nbr_personne", "" + ann.getNbrPersonne());
            r.addArgument("prix", "" + ann.getPrix());
            r.addArgument("critere", ann.getCritere());
            r.addArgument("distance", ann.getDistance());

            r.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        ToastBar.showMessage("good", FontImage.MATERIAL_CHECK, 5000);

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

    private int calculatPrice(String distance) {
        int price;
        try {
            List<String> d = StringUtil.tokenize(distance, " km");
            float price2 = (Float.parseFloat(d.get(0)) / 125 * 8);
            if (price2 < 1) {
                price = 2;
            } else {
                price = (int) price2;
            }

            System.out.println("d " + price);
        } catch (NumberFormatException err) {
            price = 100;
        }
        return price;

    }

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
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("legs")).get(0)).get("distance")).get("text").toString();
                }

                //  String d = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("distance")).get("text").toString();
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return ret;

    }

    String[] searchLocations(String text) {

        try {

            if (text.length() > 0) {

                ConnectionRequest r = new ConnectionRequest();

                r.setPost(false);

                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");

                r.addArgument("key", HTML_API_KEY);

                r.addArgument("input", text);

                NetworkManager.getInstance().addToQueueAndWait(r);

                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));

                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;

            }

        } catch (Exception err) {

            Log.e(err);

        }

        return null;

    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_FromTo = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Label gui_fromLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Label gui_toLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_Map = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_Container_Info = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_DistanceLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_NbrPlace = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_TripDateTime = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Button gui_SubmitButton = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setUIID("AjouterAnnonceForm");
        setTitle("Ajouter Annonce");
        setName("AjouterAnnonce");
        addComponent(gui_Container_FromTo);
        gui_Container_FromTo.setUIID("FromContainer");
        gui_Container_FromTo.setName("Container_FromTo");
        gui_Container_FromTo.addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_fromLabel);
        gui_fromLabel.setText("Lieu Depart:");
        gui_fromLabel.setUIID("FromLabel");
        gui_fromLabel.setName("fromLabel");
        gui_Container_FromTo.addComponent(gui_Container_1_1_1);
        gui_Container_1_1_1.setName("Container_1_1_1");
        gui_Container_1_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_toLabel);
        gui_toLabel.setText("Lieu d'arriver:");
        gui_toLabel.setUIID("ToLabel");
        gui_toLabel.setName("toLabel");
        gui_Container_1.setName("Container_1");
        gui_Container_1_1_1.setName("Container_1_1_1");
        addComponent(gui_Container_Map);
        addComponent(gui_Container_Info);
        gui_Container_Info.setUIID("FromContainer");
        gui_Container_Info.setName("Container_Info");
        gui_Container_Info.addComponent(gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(gui_Container_4);
        gui_Container_4.setName("Container_4");
        gui_Container_4.addComponent(gui_Label_4);
        gui_Container_4.addComponent(gui_DistanceLabel);
        gui_Label_4.setText("Distance:");
        gui_Label_4.setUIID("DistanceLabel");
        gui_Label_4.setName("Label_4");
        gui_DistanceLabel.setText("0 Km");
        gui_DistanceLabel.setUIID("DistanceLabel");
        gui_DistanceLabel.setName("DistanceLabel");
        gui_Container_2.addComponent(gui_Label_1);
        gui_Container_2.addComponent(gui_NbrPlace);
        gui_Container_2.addComponent(gui_Label_2);
        gui_Container_2.addComponent(gui_TripDateTime);
        gui_Container_4.setName("Container_4");
        gui_Label_1.setText("Nombre de Places:");
        gui_Label_1.setUIID("DistanceLabel");
        gui_Label_1.setName("Label_1");
        gui_NbrPlace.setName("NbrPlace");
        gui_Label_2.setText("Date depart:");
        gui_Label_2.setUIID("DistanceLabel");
        gui_Label_2.setName("Label_2");
        gui_TripDateTime.setName("TripDateTime");
        gui_Container_Info.addComponent(gui_Container_3);
        gui_Container_2.setName("Container_2");
        gui_Container_3.setName("Container_3");
        addComponent(gui_SubmitButton);
        gui_Container_FromTo.setUIID("FromContainer");
        gui_Container_FromTo.setName("Container_FromTo");
        gui_Container_Map.setUIID("MapContainer");
        gui_Container_Map.setName("Container_Map");
        gui_Container_Info.setUIID("FromContainer");
        gui_Container_Info.setName("Container_Info");
        gui_SubmitButton.setText("Ajouter");
        gui_SubmitButton.setName("SubmitButton");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
