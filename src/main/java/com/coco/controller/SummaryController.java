package com.coco.controller;

import com.coco.CocoPractiseApplication;
import com.coco.view.HistoryView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

@FXMLController
public class SummaryController {
    @FXML
    void onBack(ActionEvent event) {
        Stage stage = CocoPractiseApplication.getStage();
        stage.close();
        CocoPractiseApplication.showView(HistoryView.class);

    }
}
