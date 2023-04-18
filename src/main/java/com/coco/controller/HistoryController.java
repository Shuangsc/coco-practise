package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.common.OrderNumber;
import com.coco.config.Config;
import com.coco.pojo.OrderPojo;
import com.coco.service.OrderService;
import com.coco.util.SpringContextUtil;
import com.coco.view.BookkeepingView;
import com.coco.view.ChartPageView;
import com.coco.view.ExpenditurePageView;
import com.coco.view.SummaryView;
import de.felixroske.jfxsupport.FXMLController;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import jdk.javadoc.internal.doclets.formats.html.markup.Text;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;


@FXMLController

public class HistoryController implements Initializable {

    @FXML
    private Button jumpSumButton;

    @FXML
    private Button jumpBokButton;

    @FXML
    private ListView<?> listView;

    @FXML
    private Text user;

    @FXML
    private Label expenseTxt;

    @FXML
    private Label incomeTxt;


    @FXML
    private Label totalTxt;

    @FXML
    private TableView<OrderPojo> tableView;

    @FXML
    private TableColumn<OrderPojo, DoubleProperty> amountColumn;

    @FXML
    private TableColumn<OrderPojo, StringProperty> dateColumn;

    @FXML
    private TableColumn<OrderPojo, StringProperty> descriptColumn;

    @FXML
    private TableColumn<OrderPojo, StringProperty> typeColumn;


    /*
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
     */

    /*
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
     */

    @FXML
    void onJumpSum(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(ChartPageView.class);

    }

    @FXML
    void onJumpBok(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
//        stage.close();
        CocoPractiseApplication.showView(ExpenditurePageView.class);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources){

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeProperty"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateProperty"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountProperty"));
        descriptColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionProperty"));
        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
        List<OrderPojo> list = orderService.findByUserId(Config.loginUser.getUserId());
        double sum =0;
        for (int i = 0; i <list.size() ; i++) {
            sum-= list.get(i).getAmount();
        }

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        String formatted = nf.format(sum);
        totalTxt.setText(formatted+"");

        ObservableList<OrderPojo> observableList  = FXCollections.observableList(list);
        tableView.setItems(observableList);

        user.setText(Config.loginUser.getUserName());

        List<OrderPojo> inComeList = orderService.findIncomeList(Config.loginUser.getUserId());
        double income =0;
        for (int i = 0; i <inComeList.size() ; i++) {
            income+= inComeList.get(i).getAmount();
        }
//
        String incomeStr = nf.format(income);
        expenseTxt.setText(incomeStr+"");


        List<OrderPojo> outComeList = orderService.findoutComeList(Config.loginUser.getUserId());
        double outCome =0;
        for (int i = 0; i <outComeList.size() ; i++) {
            outCome+= outComeList.get(i).getAmount();
        }

        String outComeStr = nf.format(outCome);
        incomeTxt.setText(outComeStr+"");

    }

//    public void reFreshTable(){
//        OrderService orderService = SpringContextUtil.getBean(OrderService.class);
//        List<OrderPojo> list = orderService.findAll();
//        ObservableList<OrderPojo> observableList  = FXCollections.observableList(list);
//        tableView.setItems(observableList);
//    }
//     */
}


