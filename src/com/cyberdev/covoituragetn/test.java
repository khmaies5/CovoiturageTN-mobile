package com.cyberdev.covoituragetn;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class test extends Container  {
    public test(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.components.WebBrowser gui_Web_View_1 = new com.codename1.components.WebBrowser();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setName("test");
        addComponent(gui_Web_View_1);
        gui_Web_View_1.setName("Web_View_1");
        gui_Web_View_1.setPropertyValue("url", "https://www.codenameone.com/");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
