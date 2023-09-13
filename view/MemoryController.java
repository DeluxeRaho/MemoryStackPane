package view;

import model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MemoryController{
    MemoryView view;
    MemoryLogic model;
    MemoryIO IO;

    public MemoryController(MemoryView view, MemoryLogic model) {
        this.view = view;
        this.model = model;
        this.IO=new MemoryIO();
    }

    public void handleCardClick(MouseEvent event) {
        if (model.getGameState() == MemoryState.GAMEOVER) {
            return;
        }
        StackPane card = (StackPane) event.getSource();
        if(model.isPaired(Integer.parseInt(card.getId()))){
            return;
        }
        model.cardSelected(card.getId());
        updateBoard();

        if (model.isTwoCardSelected()) {
            model.compareCards();
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(this::updateBoard);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        updateText();
    }

    private void updateText() {
        int p1Points = model.getP1Points();
        int p2Points = model.getP2Points();
        String gameState = model.getGameState().toString();

        view.setText(p1Points, p2Points, gameState);
    }

    private void updateBoard() {
        ArrayList<Card> tmpCards = model.getCards();

        for (int i = 0; i < tmpCards.size(); i++) {
            if (tmpCards.get(i).getState() == CardState.OPENED || tmpCards.get(i).getState() == CardState.PAIRED) {
                view.openCard(i, tmpCards.get(i).getId());
            } else if (tmpCards.get(i).getState() == CardState.CLOSED) {
                view.closeCard(i);
            }
        }
    }

    public void newGame(ActionEvent e) {
        model = new MemoryLogic(8);
        view.initView(8);
    }

    public void twoPairs(ActionEvent e){
        view.initView(2);
        model = new MemoryLogic(2);
    }

    public void eightPairs(ActionEvent e){
        view.initView(8);
        model = new MemoryLogic(8);
    }

    public void eighteenPairs(ActionEvent e){
        view.initView(18);
        model = new MemoryLogic(18);
    }

    public void save(ActionEvent e){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        try {
            File file = (File) fileChooser.showSaveDialog(new Stage());
            IO.serializeToFile(model, file.getAbsolutePath());
        }catch(IOException e1){
            view.showAlert("Could not save file!","EXCEPTION");
        }
    }

    public void load(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        try {
            File file = fileChooser.showOpenDialog(new Stage());
            model = IO.deSerializeFromFile(file.getAbsolutePath());
            view.initView(model.getNumOfPairs());
            updateBoard();
            updateText();
        }catch(Exception e1){
            view.showAlert("Could not load file!","EXCEPTION");
        }
    }

    public void exit(ActionEvent e){
       view.closeApp();
    }

    public void rules(ActionEvent e){
        view.showAlert("Player starts to pick two cards, if they match, they will stay open and same player play again until two cards dont match.\nWhen two cards doesnt match, they close and its the next players turn.\nThe player with most points when game is over, wins!","RULES");
    }

}
