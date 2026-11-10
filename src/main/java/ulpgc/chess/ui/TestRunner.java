package ulpgc.chess.ui;

import ulpgc.chess.domain.Board;
import ulpgc.chess.domain.Move;
import ulpgc.chess.domain.Square;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("♟️ INICIANDO BATERÍA DE TESTS DEL MOTOR DE AJEDREZ ♟️\n");

        testMovimientoBasico();
        testMovimientoIlegalPeon();
        testJaqueMateDelLoco();

        System.out.println("\n✅ ¡TODOS LOS TESTS HAN FINALIZADO!");
    }

    private static void testMovimientoBasico() {
        System.out.println("▶️ TEST 1: Movimiento básico y alternancia de turnos");
        try {
            Board board = new Board();
            // Mueve peón blanco e2-e4 (Fila 1 Columna 4 -> Fila 3 Columna 4)
            board = board.move(new Move(new Square(1, 4), new Square(3, 4)));
            // Mueve peón negro e7-e5 (Fila 6 Columna 4 -> Fila 4 Columna 4)
            board = board.move(new Move(new Square(6, 4), new Square(4, 4)));

            System.out.println("   ✔️ ÉXITO: Los peones se movieron sin dar falsos jaques.");
        } catch (Exception e) {
            System.out.println("   ❌ FALLO: " + e.getMessage());
        }
    }

    private static void testMovimientoIlegalPeon() {
        System.out.println("\n▶️ TEST 2: Intentar mover peón en diagonal a casilla vacía");
        try {
            Board board = new Board();
            // Intenta mover peón blanco en diagonal sin capturar nada (Fila 1,4 a 2,5)
            board = board.move(new Move(new Square(1, 4), new Square(2, 5)));
            System.out.println("   ❌ FALLO: El motor permitió un movimiento ilegal.");
        } catch (IllegalArgumentException e) { // O la excepción que devuelvas
            System.out.println("   ✔️ ÉXITO: El motor bloqueó el movimiento correctamente. (" + e.getMessage() + ")");
        } catch (Exception e) {
            System.out.println("   ✔️ ÉXITO (Alternativo): Bloqueado por otra regla. (" + e.getMessage() + ")");
        }
    }

    private static void testJaqueMateDelLoco() {
        System.out.println("\n▶️ TEST 3: Simulación del 'Mate del Loco' (Jaque Mate en 2 jugadas)");
        try {
            Board board = new Board();

            // 1. Blancas: Peón f3 (Fila 1 Columna 5 -> Fila 2 Columna 5)
            board = board.move(new Move(new Square(1, 5), new Square(2, 5)));
            // 1. Negras: Peón e5 (Fila 6 Columna 4 -> Fila 4 Columna 4)
            board = board.move(new Move(new Square(6, 4), new Square(4, 4)));

            // 2. Blancas: Peón g4 (Fila 1 Columna 6 -> Fila 3 Columna 6)
            board = board.move(new Move(new Square(1, 6), new Square(3, 6)));

            // 2. Negras: Reina h4 - ¡JAQUE MATE! (Fila 7 Columna 3 -> Fila 3 Columna 7)
            board = board.move(new Move(new Square(7, 3), new Square(3, 7)));

            System.out.println("   ❌ FALLO: El motor no detectó el Jaque Mate y permitió seguir jugando.");

        } catch (IllegalStateException e) { // La excepción que pusimos en isCheckmate
            System.out.println("   ✔️ ÉXITO: ¡Jaque Mate detectado perfectamente! Mensaje del motor: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("   ⚠️ AVISO: Detectó algo, pero como IllegalArgument. Mensaje: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("   ❌ FALLO INESPERADO: " + e.getMessage());
        }
    }
}