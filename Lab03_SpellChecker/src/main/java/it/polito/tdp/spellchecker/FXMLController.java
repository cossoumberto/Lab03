package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary dictionary;
	String language = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label lblErrors;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;

    @FXML
    void chooseLanguage(ActionEvent event) {
    	language = comboBox.getValue();
    }

    @FXML
    void doClearText(ActionEvent event) {
    	txtInsert.clear();
    	txtResult.clear();
    	lblErrors.setText("");
    	lblTime.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	long startTime = System.nanoTime();
    	
    	txtResult.clear();
    	int numWrong = 0;
    	dictionary  = new Dictionary ();
    	if(language==null)
    		txtResult.setText("Select language");
    	else {
	    	dictionary.loadDictionary(language);
	    	String testoInserito = txtInsert.getText().toLowerCase().replace("\n"," ").replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
	    	String array [] = testoInserito.split(" ");
	    	List<String> paroleInserite = new ArrayList<>();
		    	for(String s : array)
		    		if(s.equals("")==false)
		    			paroleInserite.add(s);
	    	List<RichWord> listaRW = dictionary.spellCheckText(paroleInserite);
		    	for(RichWord rw : listaRW)
		    		if(rw.isCorrect()==false) {
		    			numWrong++;
		    			txtResult.appendText(rw.getWord()+"\n");
    		}
	    	long elapsedNanos = System.nanoTime() - startTime;
	    	lblTime.setText("Spell check completed in " + elapsedNanos + " nenoseconds");
	    	
	    	lblErrors.setText("The text contains " + numWrong + " errors");
    	}
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
        comboBox.getItems().addAll("Italiano", "English");
    }
}

