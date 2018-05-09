package eu.pedu.adv17s._4_1100.kouo00_koutecky.main;


import eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.PotionGame;
import eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.Room;
import eu.pedu.adv17s._4_1100.kouo00_koutecky.logika.Thing;
import java.util.Observable;
import java.util.Observer;


import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
public class HomeController extends GridPane implements Observer, Initializable {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Object> seznamVeciMistnost = new ListView<>();
        @FXML private ListView<Object> seznamVeciBatoh = new ListView<>();
	@FXML private ListView<Object> seznamVychodu = new ListView<>();
	@FXML private ImageView uzivatel;
	
	private PotionGame hra;
        private ObservableList<Object> veciMistnost = FXCollections.observableArrayList();
        private ObservableList<Object> veciBatoh = FXCollections.observableArrayList();
        private ObservableList<Object> vychody = FXCollections.observableArrayList();
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() 
        {
		String vystupPrikazu = hra.executeCommand(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		if(hra.isAlive()) 
                {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
                hra.getWorld().notifyObservers();
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	@Override
        public void initialize(URL url, ResourceBundle rb)  
        {
                hra = new PotionGame();
		vystup.setText("Vaším úkolem je najít věci potřebné k uvaření lektvaru, poté ho"
        		+ " uvařit nad ohništěm, dát ho vypít blondýnce a na závěr ji "
        		+ "věnovat polibek. Pozor! Ne všechny předměty, které lze"
        		+ " najít, do lektvaru patří!");
		vystup.setEditable(false);
                
                
                seznamVeciMistnost.setItems(veciMistnost);
                seznamVeciBatoh.setItems(veciBatoh);
                seznamVychodu.setItems(vychody);
                
		hra.getWorld().addObserver(this);
                hra.getWorld().notifyObservers();
	}
        
        @FXML public void klikBatoh() 
        {
                    Collection<Thing> seznam;
                    seznam = hra.getBag().getItems();
                    int index = seznamVeciBatoh.getSelectionModel().getSelectedIndex();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (Thing vec : seznam) 
                    {
                       if(pomocna == index)
                       {
                           nazev = vec.getName();
                       }
                       pomocna++;
                    }

            if(!hra.isAlive())
            {
            vstupniText.setText("zahod " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void klikMistnost() 
        {
            String nazev = seznamVychodu.getSelectionModel().getSelectedItem().toString();
            if(!hra.isAlive())
            {
            vstupniText.setText("jdi " + nazev);
            odesliPrikaz();
            }
        }
        
        @FXML public void novaHra() 
        {
                hra = new PotionGame();
		vystup.setText("Vaším úkolem je najít věci potřebné k uvaření lektvaru, poté ho"
        		+ " uvařit nad ohništěm, dát ho vypít blondýnce a na závěr ji "
        		+ "věnovat polibek. Pozor! Ne všechny předměty, které lze"
        		+ " najít, do lektvaru patří!");
		vstupniText.setDisable(false);
                hra.getWorld().addObserver(this);
                hra.getWorld().notifyObservers();
        }
        
        @FXML public void konecHry() 
        {
            vstupniText.setText("konec");
            odesliPrikaz();
        }
        
         @FXML public void Napoveda() 
        {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            
            WebView webView = new WebView();               
            webView.getEngine().load(eu.pedu.adv17s._4_1100.kouo00_koutecky.main.GuiAdventura.class.getResource("/zdroje/napoveda.html").toExternalForm());
            
            stage.setScene(new Scene(webView, 1200, 650));
            stage.show();
        }
        
        @FXML public void klikVecMistnost() 
        {
                    Collection<Thing> seznam;
                    seznam = hra.getWorld().getCurrentPlace().getItems();
                    int index = seznamVeciMistnost.getSelectionModel().getSelectedIndex();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (Thing vec : seznam) 
                    {
                       if(pomocna == index)
                       {
                           nazev = vec.getName();
                       }
                       pomocna++;
                    }
            if(!hra.isAlive())
            {
            vstupniText.setText("seber " + nazev);
            odesliPrikaz();
            }
        }


	@Override
	public void update(Observable arg0, Object arg1) 
        {
		uzivatel.setX(hra.getWorld().getCurrentPlace().getX());
		uzivatel.setY(hra.getWorld().getCurrentPlace().getY());
            
                veciMistnost.clear();
                veciBatoh.clear();
                vychody.clear();
		
                String sVychody = hra.getWorld().getCurrentPlace().seznamVychodu();
                String[] oddeleneVychody = sVychody.split(" ");
                for (int i = 1; i < oddeleneVychody.length; i++) 
                {
                    vychody.add(oddeleneVychody[i]);
                }
                
                
                Collection<Thing> sBatoh = hra.getBag().getItems();
                for (Thing vec : sBatoh) 
                {
                    Thing pomocna = vec;
                    ImageView obrazek = new ImageView(new Image(eu.pedu.adv17s._4_1100.kouo00_koutecky.main.GuiAdventura.class.getResourceAsStream("/zdroje/"+pomocna.getName() +".jpg"), 100, 100, false, false));
                    veciBatoh.add(obrazek);
                }
                
               Collection<Thing> sVeci = hra.getWorld().getCurrentPlace().getItems();
                for (Thing vec : sVeci) 
                {
                    Thing pomocna = vec;
                    ImageView obrazek = new ImageView(new Image(eu.pedu.adv17s._4_1100.kouo00_koutecky.main.GuiAdventura.class.getResourceAsStream("/zdroje/"+pomocna.getName() +".jpg"), 100, 100, false, false));
                    veciMistnost.add(obrazek);
                }
	}

}
