package com.coco;

//import com.coco.view.HistoryView;
import com.coco.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocoPractiseApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(CocoPractiseApplication.class, LoginView.class,args);

    }

}