import domain.*;

public class Main {
    static void main() {
        Board tablero = new Board();
        tablero.printBoard();
        tablero = tablero.move(new Move(new Square(1, 1), new Square(3, 1)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
    }
}
