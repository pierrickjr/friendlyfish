package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* import for explicit declaration of callback */
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("prof.prodageo.org.MyAppWidgetset")
public class MyUI extends UI {

        private static final Logger log = LoggerFactory.getLogger(MyUIServlet.class);

    /* explicit declaration as attributes of graphical components for GenMyModel */
        final VerticalLayout layoutPrincipal = new VerticalLayout();
        final HorizontalLayout layout1 = new HorizontalLayout();
        final Label titre = new Label("Friendly Fish");
        final Link linkUtilisateur = new Link("Mon compte Utilisateur!", new ExternalResource("http://tacospin.com/"));
        final HorizontalLayout layout2 = new HorizontalLayout();
        final TextField name2 = new TextField();
        Button button2 = new Button("Touch Me") ;
        final HorizontalLayout layout3 = new HorizontalLayout();
        final TextField name3 = new TextField();
        Button button3 = new Button("Caress Me") ;




    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */

    /*public class ClickMeClass implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event)
        {
            layoutPrincipal.addComponent(new Label("Thanks " + name1.getValue() + ", it works!"));
            log.info("Button clicked with value : " + name1.getValue());
        }
    }*/



    @Override
    protected void init(VaadinRequest vaadinRequest) {


        // final VerticalLayout layout = new VerticalLayout();

        // final TextField name = new TextField();


        /*
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
            log.info("Button clicked with value : " + name.getValue());
        });
        */

        /*ClickMeClass callback = new ClickMeClass() ;
        button2.addClickListener( callback ) ;
        ClickMeClass callback = new ClickMeClass() ;
        button3.addClickListener( callback ) ;*/


        layout1.addComponents(titre,linkUtilisateur);
        layout1.setMargin(true);
        layout1.setSpacing(true);
        setContent(layout1);

        layout2.addComponents(name2, button2);
        layout2.setMargin(true);
        layout2.setSpacing(true);
        setContent(layout2);

        layout3.addComponents(name3, button3);
        layout3.setMargin(true);
        layout3.setSpacing(true);
        setContent(layout3);

        layoutPrincipal.setMargin(true);
        layoutPrincipal.setSpacing(true);
        layoutPrincipal.addComponents(layout1, layout2, layout3);
        setContent(layoutPrincipal);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
