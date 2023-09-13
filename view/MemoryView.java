package view;

import model.*;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MemoryView extends VBox{
    private ArrayList<StackPane> cards;
    private final MemoryController controller;
    private final MemoryLogic model;
    private Text text;
    private Stage stage;

    public MemoryView(int noOfPairs,Stage stage){
        model = new MemoryLogic();
        controller = new MemoryController(this, model);
        initView(noOfPairs);
        this.stage=stage;
    }

    public void closeApp(){
        stage.close();
    }

    public void setText(int p1points,int p2points, String gameState){
        text.setText(gameState + "\nPlayer 1 points: " + p1points + "\nPlayer 2 Points: " + p2points);
    }

    public void openCard(int index, int id){
        if(cards.get(index).getChildren().size()<2) {
            Image img = new Image("view/" + id + ".jpeg",60,90,false,false);
            cards.get(index).getChildren().add(new ImageView(img));
        }
    }

    public void closeCard(int index){
        if(cards.get(index).getChildren().size()==2) {
            cards.get(index).getChildren().remove(1);
        }
    }

    public void closeAllCards(int noOfPairs){
        for(int i=0; i<noOfPairs*2;i++) {
            if (cards.get(i).getChildren().size() == 2) {
                cards.get(i).getChildren().remove(1);
            }
        }
    }

    public void initView(int noOfPairs){
        this.getChildren().clear();
        cards = new ArrayList<StackPane>();
        BorderPane bp = new BorderPane();
        MenuBar menuBar = new MenuBar();

        Menu menu1 = new Menu("Menu");
        Menu menu2 = new Menu("Size");

        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu2);
        MenuItem m1 = new MenuItem("New game");
        m1.setOnAction(controller::newGame);
        MenuItem m2 = new MenuItem("Save");
        m2.setOnAction(controller::save);
        MenuItem m3 = new MenuItem("Load");
        m3.setOnAction(controller::load);
        MenuItem m4 = new MenuItem("Rules");
        m4.setOnAction(controller::rules);
        MenuItem m5 = new MenuItem("2x2");
        m5.setOnAction(controller::twoPairs);
        MenuItem m6 = new MenuItem("4x4");
        m6.setOnAction(controller::eightPairs);
        MenuItem m7 = new MenuItem("6x6");
        m7.setOnAction(controller::eighteenPairs);
        MenuItem m8 = new MenuItem("Exit");
        m8.setOnAction(controller::exit);

        menu1.getItems().add(m1);
        menu1.getItems().add(m2);
        menu1.getItems().add(m3);
        menu1.getItems().add(m4);
        menu1.getItems().add(m8);
        menu2.getItems().add(m5);
        menu2.getItems().add(m6);
        menu2.getItems().add(m7);

        GridPane gridPane = new GridPane();
        bp.setCenter(gridPane);
        Image img = new Image("view/0.jpeg",60,90,false,false);
        for(int i=0; i<noOfPairs*2;i++){
            StackPane card1 = new StackPane();
            card1.getChildren().add(new ImageView(img));
            card1.setId(String.valueOf(i));
            cards.add(card1);
            card1.setOnMouseClicked(controller::handleCardClick);
        }

        gridPane.setMinSize(800, 600);
        text = new Text("PlayerOnesTurn\nPlayer 1 points: 0\nPlayer 2 Points: 0");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        int i=0;
        for(int row=0; row<Math.sqrt(noOfPairs*2);row++){
            for(int col=0;col<Math.sqrt(noOfPairs*2);col++){
                gridPane.add(cards.get(i),col,row);
                i++;
            }
        }

        bp.setCenter(text);
        gridPane.setAlignment(Pos.CENTER);

        this.getChildren().add(menuBar);
        this.getChildren().add(bp);
        this.getChildren().add(gridPane);
        closeAllCards(noOfPairs);
    }

    public void showAlert(String msg,String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,msg);
        alert.setHeaderText(header);
        alert.show();
    }

}
