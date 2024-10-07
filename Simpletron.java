
//OLVERA PADILLA JOSE ESTEBAN
//SANCHEZ PEREZ JESUS ALEJANDRO

public class Simpletron {
    private static final int TAMANIO_MEMORIA = 1000; // Tamaño de la memoria del Simpletron
    private int[] memoria = new int[TAMANIO_MEMORIA]; // Memoria del Simpletron
    private int acumulador = 0; // Acumulador
    private int contadorInstruccion = 0; // Contador de instrucciones
    private int registroInstruccion = 0; // Registro de la instrucción actual
    private int codigoOperacion = 0; // Código de operación
    private int operando = 0; // Operando

    // Método para cargar un programa en la memoria
    public void cargarPrograma(int[] programa) {
        if (programa.length > TAMANIO_MEMORIA) {
            throw new IllegalArgumentException("El programa es demasiado grande para la memoria.");
        }
        System.arraycopy(programa, 0, memoria, 0, programa.length);
    }

    // Método para ejecutar el programa cargado en la memoria
    public void ejecutarPrograma() {
        while (true) {
            // En lugar de usar el valor decimal de la instrucción, lo convertimos de hexadecimal
            registroInstruccion = memoria[contadorInstruccion];
            codigoOperacion = registroInstruccion / 0x100; // División hexadecimal para obtener el código de operación
            operando = registroInstruccion % 0x100; // Resto hexadecimal para obtener el operando

            switch (codigoOperacion) {
                case 20: // CARGAR (LOAD)
                    if (operando >= 0 && operando < TAMANIO_MEMORIA) {
                        acumulador = memoria[operando];
                    } else {
                        throw new IllegalArgumentException("Error: Dirección de memoria inválida.");
                    }
                    break;

                case 21: // ALMACENAR (STORE)
                    if (operando >= 0 && operando < TAMANIO_MEMORIA) {
                        memoria[operando] = acumulador;
                    } else {
                        throw new IllegalArgumentException("Error: Dirección de memoria inválida.");
                    }
                    break;

                case 30: // SUMA
                    acumulador += memoria[operando];
                    break;

                case 31: // RESTA
                    acumulador -= memoria[operando];
                    break;

                case 32: // DIVISIÓN
                    if (memoria[operando] != 0) {
                        acumulador /= memoria[operando];
                    } else {
                        throw new ArithmeticException("Error: División por cero.");
                    }
                    break;

                case 33: // MULTIPLICACIÓN
                    acumulador *= memoria[operando];
                    break;

                // Nueva instrucción para calcular el resto (módulo)
                case 34: // RESTO (MODULO)
                    if (memoria[operando] != 0) {
                        acumulador %= memoria[operando];
                    } else {
                        throw new ArithmeticException("Error: División por cero en módulo.");
                    }
                    break;

                // Nueva instrucción para exponenciación
                case 35: // EXPONENCIACIÓN
                    acumulador = (int) Math.pow(acumulador, memoria[operando]);
                    break;

                case 43: // FIN
                    System.out.println("Fin de la ejecución.");
                    return;

                default:
                    throw new IllegalArgumentException("Error: Instrucción no válida.");
            }
            contadorInstruccion++;
        }
    }
}