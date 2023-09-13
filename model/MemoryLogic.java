package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

/**
 *The MemoryLogic class represents the logic for the Memory game.
 * The class stores an ArrayList of cards.
 */

public class MemoryLogic implements Serializable {
    private int NUM_OF_PAIRS;
    private ArrayList<Card> cards;
    private int p1Points;
    private int p2Points;
    private MemoryState gameState;
    private static int noOfCardsSelected;

    /**
     * Creates a MemoryLogic with 8 pairs.
     */
    public MemoryLogic() {
        NUM_OF_PAIRS=8;
        initGame(NUM_OF_PAIRS);
    }

    /**
     * Creates a MemoryLogic with the indicated value for nrOfPairs.
     * @param nrOfPairs number of pairs the game will have.
     */
    public MemoryLogic(int nrOfPairs) {
        NUM_OF_PAIRS=nrOfPairs;
        initGame(nrOfPairs);
    }

    /**
     * Initiates a new game, resets player points and game state. And initiates a new ArrayList.
     *
     * @param nrOfPairs number of pairs the game will have.
     */
    public void initGame(int nrOfPairs) {
        p1Points=0;
        p2Points=0;
        gameState = MemoryState.PlayerOnesTurn;
        noOfCardsSelected=0;
        cards = new ArrayList<Card>();
        fillCards();
    }

    /**
     * Fills the ArrayList with cards. And shuffles them.
     *
     */
    private void fillCards() {
        for(int id=6; id<6+NUM_OF_PAIRS;id++){
            cards.add(new Card(id, CardState.CLOSED));
            cards.add(new Card(id, CardState.CLOSED));
        }
        Collections.shuffle(cards);
    }

    /**
     * Returns number of pairs for this game.
     * @return number of pairs.
     */
    public int getNumOfPairs(){
        return NUM_OF_PAIRS;
    }

    /**
     * Returns the ArrayList with cards.
     *
     * @return they list of cards.
     */
    public ArrayList<Card> getCards(){
        return cards;
    }

    /**
     * Sets the cardstate of the indicated index to OPENED.
     *
     * @param index of the card in the list.
     */
    public void cardSelected(String index){
        noOfCardsSelected++;
        cards.get(Integer.parseInt(index)).setState(CardState.OPENED);
    }

    /**
     * Checks if two cards have been selected.
     *
     * @return true if true, false if false.
     */
    public boolean isTwoCardSelected(){
        if(noOfCardsSelected==2){
            noOfCardsSelected=0;
            return true;
        }else
            return false;
    }

    /**
     * Returns player 1 points.
     *
     * @return player 1 points.
     */
    public int getP1Points() {
        return p1Points;
    }

    /**
     * Returns player 2 points.
     *
     * @return player 2 points.
     */
    public int getP2Points() {
        return p2Points;
    }

    /**
     * Returns the current gamestate.
     *
     * @return the gamestate.
     */
    public MemoryState getGameState() {
        return gameState;
    }

    /**
     * Compares the two selected cards. If they match, both cards will be set to PAIRED, if not, they will be set to CLOSED.
     * If the two cards does not match, MemoryState will be set to the other players turn.
     * If they match, the same player can play again.
     *
     */
    public void compareCards() {
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getState()== CardState.OPENED){
                for(int j=(i+1);j<cards.size();j++){
                    if(cards.get(j).getState()== CardState.OPENED){
                        if(cards.get(i).getId() == cards.get(j).getId()){
                            cards.get(i).setState(CardState.PAIRED);
                            cards.get(j).setState(CardState.PAIRED);
                            if(gameState==MemoryState.PlayerOnesTurn){
                                p1Points++;
                            }else{
                                p2Points++;
                            }
                        }else{
                            cards.get(i).setState(CardState.CLOSED);
                            cards.get(j).setState(CardState.CLOSED);

                            if(gameState==MemoryState.PlayerOnesTurn){
                                gameState=MemoryState.PlayerTwosTurn;
                            }else{
                                gameState=MemoryState.PlayerOnesTurn;
                            }
                        }
                    }
                }
            }
        }
        if(p1Points + p2Points == NUM_OF_PAIRS){
            gameState=MemoryState.GAMEOVER;
        }
    }

    /**
     * Checks if the indicated index in the list is paired or not.
     *
     * @param index of the card in the list.
     * @return true if paired, false if not.
     */
    public boolean isPaired(int index){
        return cards.get(index).getState()== CardState.PAIRED;
    }

}