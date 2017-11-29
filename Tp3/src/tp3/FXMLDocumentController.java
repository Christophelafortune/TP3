/*
 * 
 * 
 * 
 */
package tp3;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author gabrielaudette
 */
public class FXMLDocumentController implements Initializable {

    int tailleParDefault = 12;
    String elementComboBox[] = {"L'Étranger de Camus", "Tester Votre Cerveau", "Texte 3"};
    String couleurTexte;

    @FXML
    private ToggleGroup tglGroupeBtnCouleur;
    @FXML
    private TextArea txaTexte;
    @FXML
    private Button btnQuitter;
    @FXML
    private CheckBox chbConfirmationQuitter;
    @FXML
    private RadioButton rabAscendant;
    @FXML
    private RadioButton rabDescendant;
    @FXML
    private ToggleGroup tglRadioTri;
    @FXML
    private Button btnTaillePlus;
    @FXML
    private Button btnTailleMoin;
    @FXML
    private ComboBox cmbLettre;
    @FXML
    private ComboBox cmbTexte;
    @FXML
    private ColorPicker copSelecteurCouleur;
    @FXML
    private TextArea txaNombrePos;
    @FXML
    private TextField txaNbPos;
    @FXML
    private TextArea txaMot;
    @FXML
    private TextField txaNbMots;
    @FXML
    private TextField txtNbMin;
    @FXML
    private TextField txtNbMaj;
    @FXML
    private TextField txtNbLettreChoisi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txaTexte.setStyle("-fx-control-inner-background:#bfbfbf");

        copSelecteurCouleur.setValue(Color.BLACK);

        cmbTexte.getItems().addAll(elementComboBox);

        alphabet();

    }

    @FXML
    private void btnActionCouleur(ActionEvent event) {

        ToggleButton tglBtnCouleur = (ToggleButton) event.getSource();

        String couleurTexte = tglBtnCouleur.getTextFill().toString().substring(2, 8); // Code de couleur

        System.out.println(couleurTexte);

        txaTexte.setStyle(txaTexte.getStyle() + ";" + "-fx-control-inner-background:#" + couleurTexte);

    }

    @FXML
    private void btnActionTaille(ActionEvent event) {

        Button btnTaille = (Button) event.getSource();

        char symbole = btnTaille.getText().charAt(1);

        if (symbole == '+') {
            System.out.println("+");
            tailleParDefault++;
            txaTexte.setFont(Font.font(tailleParDefault));

        } else {
            System.out.println("-");
            tailleParDefault--;
            txaTexte.setFont(Font.font(tailleParDefault));
        }

    }

    @FXML
    private void btnQuitterAction(ActionEvent event) {

        boolean cocher = chbConfirmationQuitter.isSelected();

        if (cocher == true) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                System.exit(0);

            }

        } else {

            System.exit(0);

        }

    }

    @FXML
    private void cmbTexteAction(ActionEvent event) {

        ComboBox cmbTexte = (ComboBox) event.getSource();
        String items = cmbTexte.getValue().toString();
        System.out.println(items);
        if (items == elementComboBox[0]) {

            txaTexte.setText(Texte.etrangerCamus);

        } else if (items == elementComboBox[1]) {
            txaTexte.setText(Texte.testezVotreCerveay);

        } else {
            txaTexte.setText(Texte.testDuProf);

        }

    }

    @FXML
    private void copSelecteurCouleurAction(ActionEvent event) {
        ColorPicker copSelectionCouleur = (ColorPicker) event.getSource();
        String couleur = copSelectionCouleur.getValue().toString().substring(2, 8);

        txaTexte.setStyle(txaTexte.getStyle() + ";" + "-fx-text-fill:#" + couleur);

    }

    @FXML
    private void btnClearAction(ActionEvent event) {

        txaTexte.clear();

    }

    public void alphabet() {

        for (int i = 65; i < 91; i++) {

            char c = (char) i;
            String sLettre = Character.toString(c);
            cmbLettre.getItems().addAll(sLettre);

        }
        for (int i = 97; i < 123; i++) {

            char c = (char) i;
            String sLettre = Character.toString(c);
            cmbLettre.getItems().addAll(sLettre);

        }

    }

    @FXML
    private void btnActionAjouterNb(ActionEvent event) {

        String[] tab = txaTexte.getText().split("\n");
        int nbPos = 0; //Variable de contrôle
        //boolean verifLimite = false;

        for (int j = 0; j < tab.length; j++) {
            if (Integer.parseInt(tab[j]) >= 0 && Integer.parseInt(tab[j]) != Integer.MAX_VALUE) {

                txaNombrePos.setText(tab[j]);
                nbPos++;

            }
        }

    }

    @FXML
    private void btnActionEffacerNombre(ActionEvent event) {

        txaNbPos.clear();
    }

    @FXML
    private void btnMotFin(ActionEvent event) {

        String[] tab = txaTexte.getText().split(" ");
        StringBuilder sb = new StringBuilder("");
        String texte = "";

        for (int i = 0; i < tab.length; i++) {

            String[] tab2 = tab[i].toString().split("");
            String mot = Arrays.asList(tab2).toString();
            char lettre = mot.charAt(mot.length() - 2);

            if (lettre == 's') {

                tab[i] = tab[i].toUpperCase();
                System.out.println(tab[i]);

            }

            texte = texte + tab[i] + " ";
            txaTexte.setText(texte);

        }

    }

    @FXML
    private void btnActionEffacerMots(ActionEvent event) {

        txaNbMots.clear();
        txaMot.clear();
        txtNbLettreChoisi.clear();
        txtNbMaj.clear();
        txtNbMin.clear();
    }

    @FXML
    private void btnActionAjouterMot(ActionEvent event) {
        
        String[] tab = txaTexte.getText().split(" ");
        int nbMaj=0;
        int nbMin=0;
        
        for (int i = 0; i < tab.length; i++) {
            
            //ArrayList<String> list = new ArrayList<String>();
            
            String[] tab2 = tab[i].toString().split("");
            String mot = Arrays.asList(tab2).toString();
           
            char premiereLettre = mot.charAt(1);
            System.out.println(premiereLettre);

            if (premiereLettre >=65 && premiereLettre <= 90  ) 
            {
                nbMaj++;
            }
            else if ( premiereLettre >= 97 && premiereLettre <= 122){
                nbMin++;
            }
            
            txtNbMin.setText(Integer.toString(nbMin));
            txtNbMaj.setText(Integer.toString(nbMaj));
            
            txaMot.setText(txaMot.getText() + "\n"+(tab[i]));
            txaNbMots.setText(Integer.toString(i));
            
        }
        
        
        
        
    }

    @FXML
    private void cmbActionChoisirLettre(ActionEvent event) {
        
        int nbLettreChoisi=0;
        
        ComboBox cmbTexte = (ComboBox) event.getSource();
        String items = cmbTexte.getValue().toString();
        char lettreChoisi= items.charAt(0);
        
        ArrayList<String> liste = new ArrayList();
        ArrayList<String> liste2 = new ArrayList();
        
        
        String [] texte = txaTexte.getText().split("").toString;
        liste.add(texte);
        
        
        for (int i = 0; i < liste.size() ; i++) {
            
            
            liste2 = liste.get(i);
            char premiereLettre = mot.charAt(1);
            
            

            if (premiereLettre == lettreChoisi ) 
            {
                nbLettreChoisi++;
            }
            
            
            txtNbLettreChoisi.setText(Integer.toString(nbLettreChoisi));
        
        }
        
    }

}
