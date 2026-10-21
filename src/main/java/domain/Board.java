package domain;

import interfaces.BoardInitializer;

import java.util.HashMap;
import java.util.Map;

import static domain.PieceType.KING;

public class Board implements BoardInitializer {
    private final Map<Square, Piece> pieces;

    public Board(){
        this.pieces= initializaBoard();
    }
    public Board(Map<Square, Piece> pieces) {
        this.pieces = Map.copyOf(pieces);
    }

    public Board move(Piece piece, Square square){
        Move movement = Move.movement(piece);
        Map<Square, Piece> tablero = new HashMap<>(pieces);
        if (pieces.containsKey(new Square(square.x() + movement.getX(), square.y()+ movement.getY()))){
            throw new IllegalArgumentException("la casilla es ya tiene una pieza");
        }
        tablero.remove(square);
        tablero.put(new Square(square.x() + movement.getX(), square.y() + movement.getY()), piece);
        return new Board(tablero);
    }
    public void printBoard() {
        for (int row = 7; row >= 0; row--) {
            System.out.print(row + " ");
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);
                Piece piece = pieces.get(square);

                if (piece == null) {
                    System.out.print(". ");
                } else {
                    char symbol = getPieceSymbol(piece);
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H"); // Letras de las columnas
    }

    private char getPieceSymbol(Piece piece) {
        char symbol = switch (piece.type()) {
            case PAWN -> 'P';
            case ROOK -> 'R';
            case KNIGHT -> 'N';
            case BISHOP -> 'B';
            case QUEEN -> 'Q';
            case KING -> 'K';
        };

        return piece.color() == Color.white ? symbol : Character.toLowerCase(symbol);
    }
}
