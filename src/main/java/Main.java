import domain.*;

public class Main {
    static void main() {
        Board tablero = new Board();
        tablero.printBoard();
        tablero = tablero.move(new Move(new Square(1, 4), new Square(3, 4)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
        tablero = tablero.move(new Move(new Square(6, 0), new Square(4, 0)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
        tablero = tablero.move(new Move(new Square(4, 0), new Square(3, 0)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
        tablero = tablero.move(new Move(new Square(3, 4), new Square(4, 4)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
        tablero = tablero.move(new Move(new Square(6, 3), new Square(5, 3)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
        tablero = tablero.move(new Move(new Square(4, 4), new Square(5, 3)));
        System.out.println("---------------");
        tablero.printBoard();
        System.out.println("---------------");
        tablero = tablero.move(new Move(new Square(3, 0), new Square(2, 0)));
        tablero = tablero.move(new Move(new Square(2, 0), new Square(1, 0)));
        System.out.println("---------------");
        tablero.printBoard();
    }
}
