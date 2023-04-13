package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.common.OrderNumber;
import com.coco.pojo.OrderPojo;
import com.coco.service.OrderService;
import com.coco.util.SpringContextUtil;
import com.coco.view.BookkeepingView;
import com.coco.view.SummaryView;
import de.felixroske.jfxsupport.FXMLController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@FXMLController

public class HistoryController implements Initializable{

    @FXML
    private Button addButton;

    @FXML
    private Button delButton;

    @FXML
    private TextField orderIdField;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TableView<OrderPojo> tableView;

    @FXML
    private TableColumn<OrderPojo, String> orderIdCol;

    @FXML
    private TableColumn<OrderPojo, String> userIdCol;

    @FXML
    private TableColumn<OrderPojo, String> typeCol;

    @FXML
    private TableColumn<OrderPojo, String> amountCol;

    @FXML
    private TableColumn<OrderPojo, String> dateCol;

    @FXML
    private TableColumn<OrderPojo, String> descriptionCol;

    @FXML
    void onAdd(ActionEvent event) {
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        int userId = Integer.parseInt(userIdField.getText());
        String type = typeField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String date = dateField.getText();
        String description = descriptionField.getText();
        int orderId =OrderNumber.getOrderNumber(11);

        orderService.orderInsert(orderId,userId,type,amount,date,description);
        reFreshTable();

    }

    @FXML
    void onDel(ActionEvent event) {
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        orderService.orderDelete(Integer.parseInt(orderIdField.getText()));
        reFreshTable();
    }

    @FXML
    void onUpd(ActionEvent event) {
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        int userId = Integer.parseInt(userIdField.getText());
        String type = typeField.getText();
        Double amount = Double.parseDouble(amountField.getText());
        String date = dateField.getText();
        String descripton = descriptionField.getText();

        int orderId = Integer.parseInt(orderIdField.getText());

        orderService.orderUpdate(orderId,userId,type,amount,date,descripton);
        reFreshTable();

    }

    @FXML
    void onJumpSum(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(SummaryView.class);

    }

    @FXML
    void onJumpBok(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(BookkeepingView.class);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderIdProperty"));

        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userIdProperty"));

        typeCol.setCellValueFactory(new PropertyValueFactory<>("typeProperty"));

        amountCol.setCellValueFactory(new PropertyValueFactory<>("amountProperty"));

        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateProperty"));

        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("descriptionProperty"));

        reFreshTable();
    }

    public void reFreshTable(){
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        List<OrderPojo> list = orderService.findAll();
        ObservableList<OrderPojo> observableList  = FXCollections.observableList(list);
        tableView.setItems(observableList);
    }
}


