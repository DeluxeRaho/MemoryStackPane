package model;

import java.io.Serializable;


/**
 * The class Card represents a card with an ID and a cardstate. An ID is the number of the image that is matches.
 */
public class Card implements Serializable {
    private int id;
    private CardState state;

    /**
     * Creates a card with an indicated value of id and a cardstate.
     *
     * @param id of the card.
     * @param state state of the card (OPEN, CLOSED or PAIRED)
     */
    public Card(int id, CardState state){
        this.id=id;
        this.state=state;
    }

    /**
     * Returns the id of the card.
     *
     * @return the id of the card.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the state of the card.
     *
     * @return the state of the card.
     */
    public CardState getState() {
        return state;
    }

    /**
     * Sets the state of this card.
     *
     * @param state is the state of the card.
     */
    public void setState(CardState state) {
        this.state = state;
    }

    /**
     * Returns a string presenting a card.
     *
     * @return the String.
     */
    @Override
    public String toString(){
        return id + ": " + state;
    }
}
