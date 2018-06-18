package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    int counter = 0; //zmienna counter zlicza ilość kliknięć w danych button
    List<Button> buttonList = new ArrayList<>();
    int lastIndex; //zmienna  indeks kliknięytego przycisku, odnosi się do ButtonList

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Memory Game with Java FX");
        FlowPane flowPane = new FlowPane(); //kontener
        flowPane.setVgap(6); //ustawienie odległosći pomiędzy buttonami (px)
        flowPane.setHgap(4); //ustawienie odległosći pomiędzy buttonami (px) horizontal
        int imageId;


        for (int i = 1; i <= 8; i++) {
            Button button = new Button("");
            imageId = i <= 4 ? i : i - 4; //skrócony if ?-to powinno być... : w przeciwnym wypadku -> zamiast modulo
            button.setPrefHeight(120); //preferowana wysokość
            button.setPrefWidth(120); //preferowana szerokość
            button.setId(String.valueOf(imageId));//nadanie identyfikatora dla butona - odpowiednik liczbowy
            buttonList.add(button); //dodaenie buttona do listy
            button.setOnAction(event -> { // zmienna event, otrzymuję obrazek, który był kliknięty
                counter++;

                Button clickedButton = (Button) event.getSource(); /*rzutowanie sprawia, że w zmiennej clickedButton mamy
                button który był klikniety*/
                Image image = new Image(getClass().getResourceAsStream("/" + clickedButton.getId() + ".png")); //stworzenie obrazku
                clickedButton.setGraphic(new ImageView(image)); //ustawienia grafiki na dany obrazek
                if (counter % 2 == 0) {
                    if (buttonList.get(lastIndex).getId().equals(clickedButton.getId())) {
                        clickedButton.setDisable(true);
                        buttonList.get(lastIndex).setDisable(true); //blokada powtórnego kliknięcia
                    }
                } else {
                    buttonList.get(lastIndex).setGraphic(null);
                    counter = 1;
                }

                lastIndex = buttonList.indexOf(clickedButton);
                System.out.println("Last index:" + lastIndex);
            });


            flowPane.getChildren().add(button);
        }

        primaryStage.setScene(new Scene(flowPane, 500, 300));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
