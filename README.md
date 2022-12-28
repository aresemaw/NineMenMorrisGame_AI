# Nine Men Morris Game:Artificial Intelligence 
## Game Rules 
### The Morris Game, Variant-D, is a board game between two players: White and Black. Each
player has 9 pieces, and the game board is as shown below. Pieces can be placed on
intersections of lines. There are 23 locations for pieces, and they can be indexed as Table 1. The
goal is to capture (remove) opponents’ pieces by getting three pieces on a single line (a mill).
The winner is the first player to reduce the opponent to only 2 pieces, or block the
opponent from any further moves. If one player has repeated moves (repeated moves
without removing the opponent’s piece), this player will be judged as lost. The game has three
distinct phases: opening, midgame, and endgame
### One way of representing a board position is by an array of length 23, containing the pieces as
the letters W, B, or x

### Two program that behave exactly the same as the programs in Part I, but
implement the ALPHA-BETA pruning algorithm instead of the MINIMAX. 

## To run the programs 
* javac ABOpening.java *
* java ABopening *
* javac ABGame.java *
* java ABGame *
