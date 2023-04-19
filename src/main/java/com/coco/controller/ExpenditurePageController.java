package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.common.ConsumptionTypeHolder;
import com.coco.view.ExpenditureAmountView;
import com.coco.view.HistoryView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
@FXMLController
public class ExpenditurePageController {
	
//	@FXML
//	private Stage MainStage;
	
	@FXML
	private AnchorPane ExpenditurePagePane;
	
	@FXML
	private Button ButtonFood;
	
	@FXML
	private Button ButtonRent;
	
	@FXML
	private Button ButtonTraffic;
	
	@FXML
	private Button ButtonPharmacy;
	
	@FXML
	private Button ButtonEntertainment;
	
	@FXML
	private Button ButtonShopping;
	
	@FXML
	private Button ButtonNecessity;
	
	@FXML
	private Button ButtonOthers;
	
	@FXML
	private Button ButtonHome;
	
	@FXML
	public void ClickHome(ActionEvent event) throws IOException {
		System.out.println("Button Home has been Click!");
		CloseExpenditurePage();
		CocoPractiseApplication.showView(HistoryView.class);

	}

	public void CloseExpenditurePage() {
        Stage stage = (Stage)ButtonHome.getScene().getWindow();
        stage.close();
    }
	
	@FXML
	public void ClickButtonFood(ActionEvent event) throws IOException {
		System.out.println("Button Food has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Food");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	
	@FXML
	public void ClickButtonRent(ActionEvent event) throws IOException {
		System.out.println("Button Rent has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Rent");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	@FXML
	public void ClickButtonTraffic(ActionEvent event) throws IOException {
		System.out.println("Button Traffic has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Traffic");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	@FXML
	public void ClickButtonNecessity(ActionEvent event) throws IOException {
		System.out.println("Button Necessity has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Necessity");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	@FXML
	public void ClickButtonOthers(ActionEvent event) throws IOException {
		System.out.println("Button Others has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Others");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	@FXML
	public void ClickButtonPharmacy(ActionEvent event) throws IOException {
		System.out.println("Button Pharmacy has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Pharmacy");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	@FXML
	public void ClickButtonShopping(ActionEvent event) throws IOException {
		System.out.println("Button Shopping has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Shopping");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
	@FXML
	public void ClickButtonEntertainment(ActionEvent event) throws IOException {
		System.out.println("Button Entertainment has been Click!");
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		consumptionTypeHolder.setConsumptionType("Entertainment");
		CocoPractiseApplication.showView(ExpenditureAmountView.class);
	}
}
