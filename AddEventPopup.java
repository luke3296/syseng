package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;

public class AddEventPopup {
   static eventContainer event;
    public static String display() {
        TextField discription = new TextField("discription");
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("add event");



     //   Label label1= new Label("Pop up window now displayed");


        Button button2 = new Button("sumbit");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                actionEvent.getSource().getClass();

               // event =  new eventContainer(new Date(), discription.getText());
                //Controller.events.put(new Date(), event);
                popupwindow.close();
            }
        });

        VBox layout= new VBox(10);

        layout.getChildren().add(discription);
        layout.getChildren().add(button2);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
        return discription.getText();

    }
}
