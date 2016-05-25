package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ThemeResource;
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
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.shared.ui.label.ContentMode;


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
        final VerticalLayout popupContent = new VerticalLayout();
        final VerticalLayout layoutPrincipal = new VerticalLayout();
        final GridLayout layout1 = new GridLayout(3,1);
        final Label titre = new Label("<h1>Friendly Fish</h1>", ContentMode.HTML);
        final PopupView popup = new PopupView("Mon compte Utilisateur", popupContent);
        final Button  connecter = new Button("Se connecter");
        final HorizontalLayout layout2 = new HorizontalLayout();
        final TextField barreRecherche = new TextField();
        final Button boutonRecherche = new Button("Recherche") ;
        final Button boutonRechercheAvancee = new Button("Recherche avancée") ;
        final HorizontalLayout layout3 = new HorizontalLayout();
        final Button boutonComparateur = new Button("<br>Comparateur</br> <br><b>Poisson/Poisson</b></br> <br></br>") ;
        final Button boutonListePoissons = new Button("<br>Rechercher</br> <br>la <b>liste des poissons compatibles</br> <br>avec le poisson</b></br>") ;
        final Button boutonEau = new Button("<br>Rechercher</br> <br>la liste des poissons compatibles</br> <br>avec un <b>type d'eau</b></br>") ;



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

        titre.setWidth("100%");
        popup.setIcon(FontAwesome.USER);

        layout1.addComponent(titre,1,0);
        layout1.addComponent(popup,2,0);

        popupContent.addComponent(new TextField("Login"));
        popupContent.addComponent(new TextField("Mot de passe"));
        popupContent.addComponent(connecter);

        layout1.setSizeFull();
        layout1.setMargin(true);
        layout1.setSpacing(true);
        setContent(layout1);

        titre.setSizeUndefined();
        popup.setSizeUndefined();
        layout1.setComponentAlignment(titre, Alignment.MIDDLE_CENTER);
        layout1.setComponentAlignment(popup, Alignment.MIDDLE_CENTER);

        barreRecherche.setIcon(FontAwesome.SEARCH);
        barreRecherche.setInputPrompt("Recherchez votre poisson");
        barreRecherche.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        barreRecherche.setColumns(50);
        boutonRechercheAvancee.setIcon(FontAwesome.SEARCH_PLUS);
        boutonRechercheAvancee.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        layout2.addComponents(barreRecherche, boutonRecherche, boutonRechercheAvancee);
        layout2.setMargin(true);
        layout2.setSpacing(true);
        setContent(layout2);

        boutonComparateur.setHtmlContentAllowed(true);
        boutonComparateur.setIcon(FontAwesome.HEART_O);
        boutonComparateur.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        boutonListePoissons.setHtmlContentAllowed(true);
        boutonListePoissons.setIcon(FontAwesome.LIST);
        boutonListePoissons.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        boutonEau.setHtmlContentAllowed(true);
        boutonEau.setIcon(FontAwesome.TINT);
        boutonEau.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);

        layout3.addComponents(boutonComparateur, boutonListePoissons, boutonEau);
        //layout3.setMargin(true);
        layout3.setSpacing(true);
        setContent(layout3);

        layoutPrincipal.setMargin(true);
        layoutPrincipal.setSpacing(true);
        layoutPrincipal.addComponents(layout1, layout2, layout3);
        setContent(layoutPrincipal);

        layoutPrincipal.setComponentAlignment(layout1, Alignment.TOP_CENTER);
        layoutPrincipal.setComponentAlignment(layout2, Alignment.BOTTOM_CENTER);
        layoutPrincipal.setComponentAlignment(layout3, Alignment.TOP_CENTER);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
