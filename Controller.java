package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;

public class Controller {
    public static HashMap<Date, eventContainer> events;
    int weekRelative = 0; // e.g 0 is the current week

    ArrayList<Text> headingTextList;

    @FXML
    Text monHeading;
    @FXML
    Text tueHeading;
    @FXML
    Text wedHeading;
    @FXML
    Text thursHeading;
    @FXML
    Text friHeading;
    @FXML
    Text satHeading;
    @FXML
    Text sunHeading;
    @FXML
    Button changeHeading;
    @FXML
    VBox monVbox;
    @FXML
    VBox tueVbox;
    @FXML
    VBox wedVbox;
    @FXML
    VBox thursVbox;
    @FXML
    VBox friVbox;
    @FXML
    VBox satVbox;
    @FXML
    VBox sunVbox;
    @FXML
    Pane monPane;
    @FXML
    Pane tuePane;
    @FXML
    Pane wedPane;
    @FXML
    Pane thursPane;
    @FXML
    Pane friPane;
    @FXML
    Pane satPane;
    @FXML
    Pane sunPane;
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }

   // Date today;
    Instant today;
    Calendar cal;
  //  DateFormat date;
    DateTimeFormatter date;
    //the week offset
    static  int dayConst = 0;

    @FXML
    private void initialize() {
        date = DateTimeFormatter.ofPattern("EE : dd/MM/yyyy");
        events = new HashMap<Date,eventContainer>();
        cal = Calendar.getInstance();

        LocalDateTime dateTime =  LocalDateTime.now();
        today = LocalDateTime.now().toInstant(ZoneOffset.UTC);
       // cal.setTime(today);
       // cal.add(Calendar.DAY_OF_WEEK, 1);

        updateHeadings();
    }
    public void updateDisplay(){
        monPane.getChildren().clear();
        tuePane.getChildren().clear();
        wedPane.getChildren().clear();
        thursPane.getChildren().clear();
        friPane.getChildren().clear();
        satPane.getChildren().clear();
        sunPane.getChildren().clear();

        events.forEach(new BiConsumer<Date, eventContainer>() {

            @Override
            public void accept(Date date, eventContainer eventContainer) {
                // Dates to sort the contents of events HashMap into the panes they should added to
                LocalDateTime monStart ;
                LocalDateTime tueStart ;
                LocalDateTime wedStart ;
                LocalDateTime thuStart ;
                LocalDateTime friStart ;
                LocalDateTime satStart ;
                LocalDateTime sunStart ;
                LocalDateTime sunEnd;

                System.out.println("date made " + date.toString() + " Date for " + eventContainer.date.toString() + "  discrip " + eventContainer.discription);
                Date td = new Date();
                //The start of the current day
                LocalDateTime now =  LocalDate.now().atStartOfDay();
                //set the week offset
                dayConst = 7 * weekRelative;
                //what day of the week it is effects the day that represents cols
                switch (td.getDay()){
                    case 1: //if it's monday
                        monStart = now.plusDays(dayConst);
                        tueStart = now.plusDays(1+dayConst);
                        wedStart = now.plusDays(2+dayConst);
                        thuStart = now.plusDays(3+dayConst);
                        friStart = now.plusDays(4+dayConst);
                        satStart = now.plusDays(5+dayConst);
                        sunStart = now.plusDays(6+dayConst);
                        sunEnd = now.plusDays(dayConst + 7);
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane,eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            //Add to 2nd col
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(satPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }
                        break;
                    case 2: //if its tuesday
                        monStart = now.plusDays(-1+dayConst);
                        tueStart = now.plusDays(dayConst)  ;
                        wedStart = now.plusDays(1+dayConst);
                        thuStart = now.plusDays(2+dayConst);
                        friStart = now.plusDays(3+dayConst);
                        satStart = now.plusDays(4+dayConst);
                        sunStart = now.plusDays(5+dayConst);
                        sunEnd = now.plusDays(6+dayConst);
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }
                        break;
                    case 3: //if its wednesday
                        monStart  = now.plusDays(-2+dayConst);
                        tueStart  = now.plusDays(-1+dayConst );
                        wedStart  = now.plusDays(dayConst);
                        thuStart  = now.plusDays(1+dayConst);
                        friStart  = now.plusDays(2+dayConst);
                        satStart  = now.plusDays(3+dayConst);
                        sunStart  = now.plusDays(4+dayConst);
                        sunEnd    = now.plusDays(5+dayConst);
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            //Add to 2nd col
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(satPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }
                        break;
                    case 4: //if its thursday
                        monStart = now.plusDays(-3+dayConst);
                        tueStart = now.plusDays(-2+dayConst);
                        wedStart = now.plusDays(-1+dayConst);
                        thuStart = (now.plusDays(dayConst));
                        friStart = now.plusDays(1+dayConst);
                        satStart = now.plusDays(2+dayConst);
                        sunStart = now.plusDays(3+dayConst);
                        sunEnd = now.plusDays(4+dayConst);
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            //Add to 2nd col
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(satPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }
                        break;
                    case 5: //if its friday
                        monStart  = now.plusDays(-4+dayConst);
                        tueStart  = now.plusDays(-3+dayConst);
                        wedStart  = now.plusDays(-2+dayConst);
                        thuStart  = now.plusDays(-1+dayConst);
                        friStart  = now.plusDays(dayConst);
                        satStart  = now.plusDays(1+dayConst);
                        sunStart  = now.plusDays(2+dayConst);
                        sunEnd = now.plusDays(3+dayConst);
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            //Add to 2nd col
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(satPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }
                        break;
                    case 6: //if its saterday
                        monStart = now.plusDays(-5+dayConst);
                        tueStart = now.plusDays(-4+dayConst);
                        wedStart = now.plusDays(-3+dayConst);
                        thuStart = now.plusDays(-2+dayConst);
                        friStart = now.plusDays(-1+dayConst);
                        satStart = now.plusDays(dayConst);
                        sunStart = now.plusDays(1+dayConst);
                        sunEnd = now.plusDays(2+dayConst);
                        System.out.println(monStart.toString() + " " + tueStart.toString() + " " + wedStart.toString() + " " + thuStart.toString() + " " + friStart.toString() + " " + satStart.toString() + " " + sunStart.toString() +" "+ eventContainer.date.toString());
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            //Add to 2nd col
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(satPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }

                        break;
                    case 0: //if its sunday
                        monStart = now.plusDays(-6+dayConst);
                        tueStart = now.plusDays(-5+dayConst);
                        wedStart = now.plusDays(-4+dayConst);
                        thuStart = now.plusDays(-3+dayConst);
                        friStart = now.plusDays(-2+dayConst);
                        satStart = now.plusDays(-1+dayConst);
                        sunStart = now.plusDays(dayConst);
                        sunEnd = now.plusDays(1+dayConst);
                        if(eventContainer.date.isAfter(monStart) && eventContainer.date.isBefore(tueStart) || eventContainer.date.isEqual(monStart)){
                            //Add to 1st col
                            addToPane(monPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(tueStart) && eventContainer.date.isBefore(wedStart) || eventContainer.date.isEqual(tueStart)){
                            //Add to 2nd col
                            addToPane(tuePane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(wedStart) && eventContainer.date.isBefore(thuStart) || eventContainer.date.isEqual(wedStart)){
                            addToPane(wedPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(thuStart) && eventContainer.date.isBefore(friStart) || eventContainer.date.isEqual(thuStart)){
                            addToPane(thursPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(friStart) && eventContainer.date.isBefore(satStart) || eventContainer.date.isEqual(friStart)){
                            addToPane(friPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(satStart) && eventContainer.date.isBefore(sunStart) || eventContainer.date.isEqual(satStart)){
                            addToPane(satPane, eventContainer.discription);
                        }else if(eventContainer.date.isAfter(sunStart) && eventContainer.date.isBefore(sunEnd) || eventContainer.date.isEqual(sunStart)){
                            addToPane(sunPane, eventContainer.discription);
                        }else{
                            System.out.println("event not added");
                        }

                        break;
                }
            }
        });

    }
    public void updateHeadings(){
        System.out.println("heading update");
       // LocalDateTime now = LocalDateTime.now();
        LocalDateTime now =  LocalDate.now().atStartOfDay();
       // headingTextList.clear();
        dayConst = 7 * weekRelative;
        Date td = new Date();
        System.out.println(td.getDay());
           switch (td.getDay()){
               case 1:
                   monHeading.setText(now.plusDays(dayConst).format(date));
                   tueHeading.setText(now.plusDays(1+dayConst).format(date));
                   wedHeading.setText(now.plusDays(2+dayConst).format(date));
                   thursHeading.setText(now.plusDays(3+dayConst).format(date));
                   friHeading.setText(now.plusDays(4+dayConst).format(date));
                   satHeading.setText(now.plusDays(5+dayConst).format(date));
                   sunHeading.setText(now.plusDays(6+dayConst).format(date));
                   break;
               case 2:
                   monHeading.setText(now.plusDays(-1+dayConst).format(date));
                   tueHeading.setText(now.plusDays(dayConst).format(date));
                   wedHeading.setText(now.plusDays(1+dayConst).format(date));
                   thursHeading.setText(now.plusDays(2+dayConst).format(date));
                   friHeading.setText(now.plusDays(3+dayConst).format(date));
                   satHeading.setText(now.plusDays(4+dayConst).format(date));
                   sunHeading.setText(now.plusDays(5+dayConst).format(date));
                   break;
               case 3:
                   monHeading.setText(now.plusDays(-2+dayConst).format(date));
                   tueHeading.setText(now.plusDays(-1+dayConst).format(date));
                   wedHeading.setText(now.plusDays(dayConst).format(date));
                   thursHeading.setText(now.plusDays(1+dayConst).format(date));
                   friHeading.setText(now.plusDays(2+dayConst).format(date));
                   satHeading.setText(now.plusDays(3+dayConst).format(date));
                   sunHeading.setText(now.plusDays(4+dayConst).format(date));
                   break;
               case 4:
                   monHeading.setText(now.plusDays(-3+dayConst).format(date));
                   tueHeading.setText(now.plusDays(-2+dayConst).format(date));
                   wedHeading.setText(now.plusDays(-1+dayConst).format(date));
                   thursHeading.setText(now.plusDays(dayConst).format(date));
                   friHeading.setText(now.plusDays(1+dayConst).format(date));
                   satHeading.setText(now.plusDays(2+dayConst).format(date));
                   sunHeading.setText(now.plusDays(3+dayConst).format(date));
                   break;
               case 5:
                   monHeading.setText(now.plusDays(-4+dayConst).format(date));
                   tueHeading.setText(now.plusDays(-3+dayConst).format(date));
                   wedHeading.setText(now.plusDays(-2+dayConst).format(date));
                   thursHeading.setText(now.plusDays(-1+dayConst).format(date));
                   friHeading.setText(now.plusDays(dayConst).format(date));
                   satHeading.setText(now.plusDays(1+dayConst).format(date));
                   sunHeading.setText(now.plusDays(2+dayConst).format(date));
                   break;
               case 6:
                   monHeading.setText(now.plusDays(-5+dayConst).format(date));
                   tueHeading.setText(now.plusDays(-4+dayConst).format(date));
                   wedHeading.setText(now.plusDays(-3+dayConst).format(date));
                   thursHeading.setText(now.plusDays(-2+dayConst).format(date));
                   friHeading.setText(now.plusDays(-1+dayConst).format(date));
                   satHeading.setText(now.plusDays(dayConst).format(date));
                   sunHeading.setText(now.plusDays(1+dayConst).format(date));
                   break;
               case 0:
                   monHeading.setText(now.plusDays(-6+dayConst).format(date));
                   tueHeading.setText(now.plusDays(-5+dayConst).format(date));
                   wedHeading.setText(now.plusDays(-4+dayConst).format(date));
                   thursHeading.setText(now.plusDays(-3+dayConst).format(date));
                   friHeading.setText(now.plusDays(-2+dayConst).format(date));
                   satHeading.setText(now.plusDays(-1+dayConst).format(date));
                   sunHeading.setText(now.plusDays(dayConst).format(date));
                   break;
               default:
                   System.out.println("headings not added");
                   break;
           }
    }

    public void nextWeek(){
        weekRelative += 1;
        dayConst = 7* weekRelative;
        System.out.println(weekRelative);
        updateHeadings();
        updateDisplay();
    }
    public void prevWeek(){
        weekRelative -=1;
        dayConst = 7* weekRelative;
        System.out.println(weekRelative);
        updateHeadings();
        updateDisplay();
    }

    public void on1ColClick(Event e){
        //when you click on colum 1
        //get the current date
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date(System.nanoTime());
        //switch the current day
        switch (td.getDay()){ // 0 = sunday , 1 = monday
            case 1: // its monday and you're adding to mondays col
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst-1), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst-2), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst-3), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst-4), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst-5), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst-6), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    public void on2ColClick(Event e){
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date();
        switch (td.getDay()){
            case 1:
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst-1), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst-2), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst-3), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst-4), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst-5), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    public void on3ColClick(Event e){
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date();
        switch (td.getDay()){
            case 1:
                event = new eventContainer(now.plusDays(dayConst+2), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst-1), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst-2), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst-3), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst-4), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    public void on4ColClick(Event e){
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date();
        switch (td.getDay()){
            case 1:
                event = new eventContainer(now.plusDays(dayConst+3), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst+2), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst-1), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst-2), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst-3), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    public void on5ColClick(Event e){
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date();
        switch (td.getDay()){
            case 1:
                event = new eventContainer(now.plusDays(dayConst+4), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst+3), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst+2), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst-1), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst-2), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    public void on6ColClick(Event e){
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date();
        switch (td.getDay()){
            case 1:
                event = new eventContainer(now.plusDays(dayConst+5), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst+4), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst+3), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst+2), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst-1), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    public void on7ColClick(Event e){
        LocalDateTime now =  LocalDate.now().atStartOfDay();
        String discription = AddEventPopup.display();
        eventContainer event = null;
        Date td = new Date();
        switch (td.getDay()){
            case 1:
                event = new eventContainer(now.plusDays(dayConst+6), discription);
                break;
            case 2:  //e.g its tuesday but ur addming to a previous col
                event = new eventContainer(now.plusDays(dayConst+5), discription);
                break;
            case 3:
                event = new eventContainer(now.plusDays(dayConst+4), discription);
                break;
            case 4:
                event = new eventContainer(now.plusDays(dayConst+3), discription);
                break;
            case 5:
                event = new eventContainer(now.plusDays(dayConst+2), discription);
                break;
            case 6:
                event = new eventContainer(now.plusDays(dayConst+1), discription);
                break;
            case 0:
                event = new eventContainer(now.plusDays(dayConst), discription);
                break;
        }
        events.put(new Date(), event);
        updateDisplay();
    }
    static  void addEvent(Date date, eventContainer container){
        events.put(date,container);
    }

    public void addToPane(Pane p, String discription){
        //here change what to add to the pane
        TextArea text = new TextArea(discription);
        text.setPrefWidth(150);
        text.setWrapText(true);
        p.getChildren().add(text);
    }
}
