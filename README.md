# Nine Men Morris Game:Artificial Intelligence 
## Game Rules 
The Morris Game, also known as Nine Men's Morris, is a two-player board game that has been played for centuries. The game is played on a board consisting of 23 intersections, and each player starts with 9 pieces that can be placed on the intersections. The objective of the game is to capture your opponent's pieces by forming a line of three pieces on the board, known as a mill. The first player to reduce their opponent to only 2 pieces, or block their opponent from making any further moves, wins the game. </br>

There are three phases to the game: the opening phase, the midgame phase, and the endgame phase. During the opening phase, players place their pieces on the board and try to form mills to capture their opponent's pieces. In the midgame phase, players continue to place and move their pieces in an attempt to form mills and capture their opponent's pieces. The endgame phase is reached when one player has been reduced to only 3 pieces, and the objective shifts to blocking the opponent's moves in order to force a win. </br> 

One way of representing a board position in the Morris Game is by using an array of length 23, with each element representing a piece on the board. The elements of the array can contain the letters W for a White piece, B for a Black piece, or x for an empty intersection. By tracking the positions of the pieces on the board using this representation, it is possible to determine the current state of the game and make decisions about the next move to make.</br>

### Two program that behave exactly the same as the programs in Part I, but
implement the ALPHA-BETA pruning algorithm instead of the MINIMAX. 

## To run the programs 
### *javac ABOpening.java*
### *java ABopening*
### *javac ABGame.java*
### *java ABGame*
