# MemoryStackPane

This is a simple game built with Java and javafx. 
The game is played by two players taking turns to flip cards in a memory game. 
The goal is to obtain a higher score than your opponent.

## 🕹️ How to play
    Click the "New Game" button or chose a stack size to start a new game. 
    This will reset the scores and the active player.

    Each turn, a player flips two cards by clicking on the desired card. 

    If a player1 flips two identical cards, a point will be added to that player
    and they will also play next turn. 
    When player1 choses two cards that are not identical, the turn goes to player2.

    The game ends when all cards are open. The winner is then declared, and the game is over.

## 🎥 DEMO
![Memory](https://github.com/DeluxeRaho/MemoryStackPane/assets/93629524/fc109da7-15a7-4ca6-8520-647c002b62e8)




### 👨‍💻 Code Structure
#### Java and JavaFX:

Java is a versatile and robust programming language that provides a solid foundation for building complex applications like games. 
JavaFX, on the other hand, is a powerful framework for creating graphical user interfaces (GUIs) and is particularly well-suited for game development due to its rich set of UI components and graphics capabilities. 
With JavaFX, I can create engaging and immersive user interfaces for games, complete with animations, transitions, and custom controls.

#### MVC (Model-View-Controller) Architecture:

I am well-versed in the MVC architectural pattern, which is a fundamental design principle for organizing the codebase of a game. In the context of game development:

    Model: 
    
    I design and implement the model component
    which represents the underlying data and logic of the game. 
    This includes managing game state, handling game mechanics
    and storing essential game data such as player scores, progress, and game objects' properties.

    View: 
    
    I create the view component responsible for rendering the game's graphical elements
    and presenting them to the player. 
    Using JavaFX, I design and customize the game's user interface
    ensuring that it is visually appealing and intuitive for players to interact with.

    Controller: 
    
    I develop the controller component
    which acts as an intermediary between the model and view. 
    It handles user input, processes player actions
    and updates the model and view accordingly. 
    By separating concerns, the MVC architecture enhances code maintainability
    and allows for easier debugging and testing.
