package domain;

import interfaces.BoardInitializer;

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
    public void printBoard() {
        for (int row = 7; row >= 0; row--) { // Del 7 al 0 para que las piezas blancas salgan abajo y las negras arriba
            System.out.print(row + " "); // Muestra el número de fila
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);
                Piece piece = pieces.get(square);

                if (piece == null) {
                    System.out.print(". "); // Casilla vacía
                } else {
                    // Aquí imprimes una letra según el tipo de pieza (ej. P para Peón, T para Torre, etc.)
                    // Y puedes diferenciar mayúsculas (blancas) y minúsculas (negras)
                    char symbol = getPieceSymbol(piece);
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H"); // Letras de las columnas
    }

    // Método auxiliar rápido para los símbolos
    private char getPieceSymbol(Piece piece) {
        char symbol = switch (piece.type()) {
            case PAWN -> 'P';
            case ROOK -> 'R';
            case KNIGHT -> 'N';
            case BISHOP -> 'B';
            case QUEEN -> 'Q';
            case KING -> 'K';
        };
        // Si es negra, la pasamos a minúscula para distinguirlas visualmente
        return piece.color() == Color.white ? symbol : Character.toLowerCase(symbol);
    }
}
