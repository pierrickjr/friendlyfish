package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import java.io.Serializable;
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
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

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

final GridLayout layout1 = new GridLayout(3,1);
final Label titreComparateur = new Label("Compatibilité <b>Poisson/Poisson</b>", ContentMode.HTML);
final Label titre = new Label("<h1>Friendly Fish</h1>", ContentMode.HTML);
final VerticalLayout popupContent = new VerticalLayout();
final PopupView popup = new PopupView("Mon compte Utilisateur", popupContent);
final Button connecter = new Button("Se connecter");

final GridLayout layout2 = new GridLayout(5,1);
final VerticalLayout layout3 = new VerticalLayout();
final Button boutonListePoissons = new Button("<br>Rechercher</br> <br>la <b>liste des poissons compatibles</br> <br>avec le poisson</b></br>");
final Button boutonEau = new Button("<br>Rechercher</br> <br>la liste des poissons compatibles</br> <br>avec un <b>type d'eau</b></br>");

final VerticalLayout layout4 = new VerticalLayout();
final ComboBox choixPoisson = new ComboBox("Poisson 1");
final Button boutonAjouter = new Button("Ajouter dans l'aquarium");
final Label description = new Label("Description du poisson 1");
final Label compatibilite = new Label("Caractéristiques du poisson 1");
final Button faireCompatibilite = new Button("Calculer la compatibilite entre les deux poissons");
private String poisson1=null;
private String poisson2=null;
final Label resFaireCompatibilite = new Label("");
private ComparaisonPoisson maComparaison = new ComparaisonPoisson(true);

final VerticalLayout layout5 = new VerticalLayout();

final VerticalLayout layout6 = new VerticalLayout();

final VerticalLayout layout7 = new VerticalLayout();
final ComboBox choixPoisson2 = new ComboBox("Poisson 2");
final Button boutonAjouter2 = new Button("Ajouter dans l'aquarium");
final Label description2 = new Label("Description du poisson 2");
final Label compatibilite2 = new Label("Caractéristiques du poisson 2");

/* explicit callback */
/* https://vaadin.com/docs/-/part/framework/application/application-events.html */

//controleur
/*public class IdPoisson extends Object implements Serializable {
String nomPoisson;
IdPoisson (String nom) {
        nomPoisson = nom;
}

public String toString () {
        return nomPoisson;
}
}*/

@Override
protected void init(VaadinRequest vaadinRequest) {

        //contoleur
        String ensemblePoissons[] = {"Truite", "Saumon", "Poisson Rouge", "Étoile de Mer"};
        for (int i=0; i<ensemblePoissons.length; i++) {
                choixPoisson.addItem(ensemblePoissons[i]);
                choixPoisson2.addItem(ensemblePoissons[i]);
        }

        boutonEau.addClickListener( e ->
            layoutPrincipal.addComponent(new Label("Le bouton Eau marche")));
        boutonListePoissons.addClickListener( e ->
            layoutPrincipal.addComponent(new Label("Le bouton Liste Poissons marche")));
        boutonAjouter.addClickListener( e ->
            layoutPrincipal.addComponent(new Label("Le bouton Ajouter1 marche")));
        boutonAjouter2.addClickListener( e ->
            layoutPrincipal.addComponent(new Label("Le bouton Ajouter2 marche")));
        faireCompatibilite.addClickListener( e ->
                resFaireCompatibilite.setValue(maComparaison.calculerComparaison()));

        titre.setWidth("100%");
        titre.setSizeUndefined();
        layout1.addComponent(titre,1,0);
        layout1.setComponentAlignment(titre, Alignment.MIDDLE_CENTER);

        titreComparateur.setIcon(FontAwesome.HEART_O);
        titreComparateur.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        layout1.addComponent(titreComparateur,0,0);

        popupContent.addComponent(new TextField("Login"));
        popupContent.addComponent(new TextField("Mot de passe"));
        popupContent.addComponent(connecter);
        popup.setSizeUndefined();
        layout1.addComponent(popup,2,0);
        layout1.setComponentAlignment(popup, Alignment.MIDDLE_CENTER);

        layout1.setSizeFull();
        //layout1.setMargin(true);
        layout1.setSpacing(true);
        setContent(layout1);

        boutonListePoissons.setHtmlContentAllowed(true);
        boutonListePoissons.setIcon(FontAwesome.LIST);
        boutonListePoissons.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        boutonEau.setHtmlContentAllowed(true);
        boutonEau.setIcon(FontAwesome.TINT);
        boutonEau.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);

        layout3.addComponents(boutonListePoissons, boutonEau);
        layout3.setSpacing(true);
        setContent(layout3);
        //avant le clic bouton créer new controller avant event
        layout4.addComponents(choixPoisson,boutonAjouter,description,compatibilite,faireCompatibilite,resFaireCompatibilite);
        choixPoisson.setItemCaptionMode(ItemCaptionMode.ID);

        choixPoisson.addValueChangeListener(event -> { //prendre le controleur c, appeler getpoisson(event...)
                                                        //si getCOmpa != null retourn compa
                                                        ////en dur dns controleur
                                                        ///// liste carac string
                                                        ///laytou.addComponent(listCarac.get(1))
                                                        ///listCarac.remove(1) pour la suite des carac
                                                        ////: for each pour afficher chac carac
                                            layoutPrincipal.addComponent(new Label("Le poisson 1 choisi : " + event.getProperty().getValue()));
                                            description.setValue("Description du poisson 1 : " + event.getProperty().getValue());
                                            compatibilite.setValue("Caractéristiques du poisson 1 : " + event.getProperty().getValue());
                                            poisson1=(String)choixPoisson.getValue();
                                            maComparaison.setPoisson1(poisson1);
                                                    });

        layout4.setMargin(true);
        layout4.setSpacing(true);
        setContent(layout4);

        layout5.addComponent(compatibilite);
        layout5.setMargin(true);
        layout5.setSpacing(true);
        setContent(layout5);

        layout6.addComponent(compatibilite2);
        layout6.setMargin(true);
        layout6.setSpacing(true);
        setContent(layout6);

        layout7.addComponents(choixPoisson2,boutonAjouter2,description2);
        choixPoisson2.setItemCaptionMode(ItemCaptionMode.ID);
        choixPoisson2.addValueChangeListener(event ->{
                                             layoutPrincipal.addComponent(new Label("Le poisson 2 choisi : " + event.getProperty().getValue()));
                                             description2.setValue("Description du poisson 2 : " + event.getProperty().getValue());
                                             compatibilite2.setValue("Caractéristiques du poisson 2 : " + event.getProperty().getValue());
                                             poisson2=(String)choixPoisson2.getValue();
                                             maComparaison.setPoisson2(poisson2);
                                             // compatibilite pourcentage en plus
                                                });
        layout7.setMargin(true);
        layout7.setSpacing(true);
        setContent(layout7);

        layout2.addComponent(layout3,0,0);
        layout2.addComponent(layout4,1,0);
        layout2.addComponent(layout5,2,0);
        layout2.addComponent(layout6,3,0);
        layout2.addComponent(layout7,4,0);
        layout2.setComponentAlignment(layout3, Alignment.TOP_CENTER);
        layout2.setComponentAlignment(layout4, Alignment.TOP_CENTER);
        layout2.setComponentAlignment(layout5, Alignment.TOP_CENTER);
        layout2.setComponentAlignment(layout6, Alignment.TOP_CENTER);
        layout2.setComponentAlignment(layout7, Alignment.TOP_CENTER);
        //layout2.setMargin(true);
        layout2.setSizeFull();
        layout2.setSpacing(true);
        setContent(layout2);

        layoutPrincipal.setMargin(true);
        layoutPrincipal.setSpacing(true);
        layoutPrincipal.addComponents(layout1, layout2);
        setContent(layoutPrincipal);

        layoutPrincipal.setComponentAlignment(layout1, Alignment.TOP_CENTER);
        layoutPrincipal.setComponentAlignment(layout2, Alignment.MIDDLE_CENTER);
        //layoutPrincipal.setComponentAlignment(layout3, Alignment.TOP_CENTER);
}

@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
public static class MyUIServlet extends VaadinServlet {
}
}

class ComparaisonPoisson{
    private String poisson1;
    private String poisson2;
    private Boolean bouchon;

    public ComparaisonPoisson(Boolean bol){
        poisson1="";
        poisson2="";
        bouchon=bol;
    }


    public void setPoisson1(String res){
        poisson1=res;
    }

    public void setPoisson2(String res){
        poisson2=res;
    }

    public String calculerComparaison(){
        if(bouchon && (poisson1!=null) && (poisson2!=null)){
            if(poisson1==poisson2){
                return "100%";
            }else{
                return "50%";
            }
        }else{
            return "";
        }
    }




}
