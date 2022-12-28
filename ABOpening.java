import java.io.*;
import java.util.*;

public class ABOpening {
    static int[] array;
    static int[] n;

    public static void main(String[] args) throws FileNotFoundException {
        // Read input and output file names and search depth from command line arguments
        String inputFile = args[0];
        String outputFile = args[1];
        int depth = Integer.parseInt(args[2]);

        // Read input board position from input file
        Scanner inputScanner = new Scanner(new File(inputFile));
        String inputBoard = inputScanner.nextLine();
        inputScanner.close();

        // Initialize alpha and beta values for alpha-beta pruning
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        // Call MINIMAX with alpha-beta pruning to determine best move
        char[] outputBoard = minimaxOpening(inputBoard.toCharArray(), depth, true, alpha, beta);

        // Write output board position to output file
        PrintWriter outputWriter = new PrintWriter(outputFile);
        outputWriter.println(outputBoard);
        outputWriter.close();
    }

    static char[] minimaxOpening(char[] sboard, int depth, Boolean maximizingPlayer, int alpha, int beta) {
        // Check if we have reached the maximum depth or if the game is over
        if (depth == 0 || gameOver(sboard)) {
            // Return the current board position and the MINIMAX estimate
            // using the static estimation function
            return new char[] {sboard[0], (char) staticEstimation(sboard)};
        }

        // Initialize best move and MINIMAX estimate
        char[] bestMove = new char[2];
        int bestEstimate = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Generate possible moves for the current board position
        ArrayList<char[]> moves = GenerateAdd(sboard);

        // Iterate through possible moves
        for (char[] move : moves) {
            // Call MINIMAX with alpha-beta pruning on the next board position
            char[] result = minimaxOpening(move, depth - 1, !maximizingPlayer, alpha, beta);

            // Extract MINIMAX estimate from result
            int estimate = result[1];

            // Update best move and MINIMAX estimate if necessary
            if (maximizingPlayer && estimate > bestEstimate) {
                bestMove = move;
                bestEstimate = estimate;
                alpha = Math.max(alpha, bestEstimate);
            } else if (!maximizingPlayer && estimate < bestEstimate) {
                bestMove = move;
                bestEstimate = estimate;
                beta = Math.min(beta, bestEstimate);
            }

            // Check for alpha-beta pruning
            if (beta <= alpha) {
                break;
            }
        }

        // Return the best move and MINIMAX estimate
        return new char[] {bestMove[0], (char) bestEstimate};
    }

    static int staticEstimation(char[] sboard) {
        // Implement the provided static estimation function here
        // (You may need to modify this to match the exact function provided in the project description)
        int numWhitePieces = 0;
        int numBlackPieces = 0;
        int numBlackMoves = 0;
        ArrayList<char[]> nbmList = new ArrayList<char[]>();
        nbmList = GenerateBlackMoves(sboard);
        int bmovecount = nbmList.size();

        for (int i = 0; i < sboard.length; i++) {
            if (sboard[i] == 'W') {
                numWhitePieces++;
            } else if (sboard[i] == 'B') {
                numBlackPieces++;
            }
        }

        if (numBlackPieces <= 2) {
            return 1000;
        } else if (numWhitePieces < -2) {
            return 1000;
        } else if (numBlackMoves == 0) {
            return 1000;
        } else {
            return 1000 * (numWhitePieces - numBlackPieces) -
                    numBlackMoves;
        }
    }

    public static ArrayList GenerateAdd(char[] location) {
        // Implement the GenerateAdd method here
        // (You may need to modify this to generate the correct possible moves for the current board position)
        ArrayList<char[]> list = new ArrayList<char[]>(); //emptyList

        char b[] = new char[location.length];

        for (int i = 0; i < location.length; i++) {
            if (location[i] == 'x') {
                b = location; // copy of b
                b[i] = 'W'; //

                if (closeMill(i, location)) {
                    list = GenerateRemove(b, list);
                } else {
                    list.add(location);
                }
            }
        }

        return list;
    }

    static ArrayList GenerateBlackMoves(char[] location) {
        // Implement the GenerateBlackMoves method here
        // (You may need to modify this to generate the correct possible moves for the current board position)
        ArrayList<char[]> list = new ArrayList<char[]>();
        char b[] = new char[location.length];

        for (int i = 0; i < location.length; i++) {
            if (location[i] == 'X') {
                for (int j : n) {
                    if (location[j] == 'x') {
                        location[i] = b[i];
                        b[i] = 'x';
                        b[j] = 'B';

                        if (closeMill(j, b)) {
                            GenerateRemove(b, list);
                        } else {
                            list.add(b);
                        }
                    }
                }
            }
        }

        return list;
    }

    static ArrayList GenerateRemove(char[] location, ArrayList<char[]> list) {
        // Implement the GenerateRemove method here
        // (You may need to modify this to generate the correct possible moves for the current board position)
        char b[] = new char[location.length];

        for (int i = 0; i < location.length; i++) {
            if (location[i] == 'B' && !closeMill(i, location)) {
                b = location;
                b[i] = 'x';
                list.add(b);
            }
        }
        return list;
    }
    static boolean closeMill(int location, char[] board) {
        // Implement the closeMill method here
        // (You may need to modify this to check if a mill has been formed on the current board position)
        if (board[location] == 'B') {
            if (board[0] == 'B' && board[1] == 'B' && board[2] == 'B')
                return true;
            else if (board[3] == 'B' && board[4] == 'B' && board[5] == 'B')
                return true;
            else if (board[6] == 'B' && board[7] == 'B' && board[8] == 'B')
                return true;
            else if (board[0] == 'B' && board[9] == 'B' && board[21] == 'B')
                return true;
            else if (board[3] == 'B' && board[10] == 'B' && board[18] == 'B')
                return true;
            else if (board[6] == 'B' && board[11] == 'B' && board[15] == 'B')
                return true;
            else if (board[1] == 'B' && board[4] == 'B' && board[7] == 'B')
                return true;
            else if (board[2] == 'B' && board[5] == 'B' && board[8] == 'B')
             return true;
            else if (board[9] == 'B' && board[10] == 'B' && board[11] == 'B')
            return true;
        else if (board[12] == 'B' && board[13] == 'B' && board[14] == 'B')
            return true;
        else if (board[15] == 'B' && board[16] == 'B' && board[17] == 'B')
            return true;
        else if (board[18] == 'B' && board[19] == 'B' && board[20] == 'B')
            return true;
        else if (board[21] == 'B' && board[22] == 'B' && board[23] == 'B')
            return true;
        else if (board[9] == 'B' && board[12] == 'B' && board[15] == 'B')
            return true;
        else if (board[10] == 'B' && board[13] == 'B' && board[16] == 'B')
            return true;
        else if (board[11] == 'B' && board[14] == 'B' && board[17] == 'B')
            return true;
        else if (board[12] == 'B' && board[16] == 'B' && board[20] == 'B')
            return true;
        else if (board[13] == 'B' && board[17] == 'B' && board[21] == 'B')
            return true;
        else if (board[14] == 'B' && board[18] == 'B' && board[22] == 'B')
            return true;
        else if (board[15] == 'B' && board[19] == 'B' && board[23] == 'B')
            return true;
    }
    return false;
}
static boolean gameOver(char[] board) {
    // Implement the gameOver method here
    // (You may need to modify this to check if the game has ended on the current board position)
    int numWhitePieces = 0;
    int numBlackPieces = 0;
    int numWhiteMoves = 0;
    int numBlackMoves = 0;
    ArrayList<char[]> wbmList = new ArrayList<char[]>();
    wbmList = GenerateWhiteMoves(board);
    int wmovecount = wbmList.size();
    ArrayList<char[]> nbmList = new ArrayList<char[]>();
    nbmList = GenerateBlackMoves(board);
    int bmovecount = nbmList.size();

    for (int i = 0; i < board.length; i++) {
        if (board[i] == 'W') {
            numWhitePieces++;
        } else if (board[i] == 'B') {
            numBlackPieces++;
        }
    }

    if (numWhitePieces <= 2) {
        return true;
    } else if (numBlackPieces <= 2) {
        return true;
    } else if (wmovecount == 0) {
        return true;
    } else if (bmovecount == 0) {
        return true;
    } else {
        return false;
    }
}

static ArrayList<char[]> GenerateWhiteMoves(char[] board) {
    ArrayList<char[]> list = new ArrayList<char[]>();
    char b[] = new char[board.length];

    for (int i = 0; i < board.length; i++) {
        if (board[i] == 'W') {
            for (int j : n) {
                if (board[j] == 'x') {
                    board[i] = b[i];
                    b[i] = 'x';
                    b[j] = 'W';
                    if (closeMill(j, b)) {
                        GenerateRemove(b, list);
                    } else {
                        list.add(b);
                    }
                }
            }
        }
    }

    return list;
}



}



