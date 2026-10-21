import domain.*;

public class Main {
    static void main() {
        Board tablero = new Board();
        tablero.printBoard();
        tablero = tablero.move(new Piece(PieceType.KNIGHT, Color.white), new Square(0, 1));
        System.out.println("---------------");
        tablero.printBoard();
        tablero= tablero.move(new Piece(PieceType.PAWN, Color.black), new Square(6, 2));
        try {
            tablero = tablero.move(new Piece(PieceType.PAWN, Color.white), new Square(1, 2));
        }catch (IllegalArgumentException e){}
        System.out.println("---------------");
        tablero.printBoard();
        tablero = tablero.move(new Piece(PieceType.PAWN, Color.white), new Square(1, 3));
        System.out.println("---------------");
        tablero.printBoard();
    }
}
