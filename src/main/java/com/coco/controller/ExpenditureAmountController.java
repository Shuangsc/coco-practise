package com.coco.controller;

import java.io.IOException;
import java.util.Random;

import com.coco.CocoPractiseApplication;
import com.coco.common.OrderNumber;
import com.coco.common.UserHolder;
import com.coco.view.ChartPageView;
import com.coco.view.ExpenditurePageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import com.coco.service.OrderService;
import com.coco.util.SpringContextUtil;
import com.coco.common.ConsumptionTypeHolder;
import javafx.stage.Stage;
import com.coco.view.HistoryView;
import javafx.scene.control.Hyperlink;

@FXMLController
public class ExpenditureAmountController {
	@FXML
	private AnchorPane AmountPagePane;

	@FXML
 	private TextField Type;

	@FXML
	private DatePicker datepicker;

	@FXML
	private TextField AmountText;

	@FXML
	private TextArea NoteText;

	@FXML
	private Button AddRecord;

	@FXML
	private Alert alert;

	@FXML
	private Hyperlink backToHistory;

	@FXML
	public void initTypeValue() {
		ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
		String consumptionType = consumptionTypeHolder.getConsumptionType();
		Type.setPromptText("--"+consumptionType+"--");
		Type.setEditable(false);
		Type.setFocusTraversable(false);
		Type.setDisable(true);

	}

	@FXML
	void onJumpHistory(ActionEvent event) {
		Stage stage = CocoPractiseApplication.getStage();
		stage.close();
		CocoPractiseApplication.showView(ExpenditurePageView.class);

	}

	Random r=new Random();

	@FXML
	public void AddRecordPrompt(ActionEvent actionevent) throws IOException{


		if(AmountText.getText().toString().trim().equals("")||datepicker.getValue()==null) {
			System.out.println("error");
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record cannot be import! consumption amount and date cannot be null!");
			alert.show();
		}else {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record added successfully!");
			alert.show();



            OrderService orderService = SpringContextUtil.getBean(OrderService.class);
			UserHolder userHolder = UserHolder.getInstance();
			int userId =userHolder.getUserId();
			ConsumptionTypeHolder consumptionTypeHolder = ConsumptionTypeHolder.getInstance();
			String type = consumptionTypeHolder.getConsumptionType();
            double amount = Double.parseDouble(AmountText.getText());
            String date =datepicker.getValue().toString();
            String description = NoteText.getText();
            int orderId =OrderNumber.getOrderNumber(11);
            orderService.orderInsert(orderId,userId,type,amount,date,description);
		}
	}

}
