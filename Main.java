//OLVERA PADILLA JOSE ESTEBAN
//SANCHEZ PEREZ JESUS ALEJANDRO

public class Main {
    public static void main(String[] args) {
        Simpletron simpletron = new Simpletron();

        // Programa SML en hexadecimal (cargar, almacenar, sumar, etc.)
        int[] programaSML = {
            0x2005, // Cargar en el acumulador el valor de la dirección 05 (LOAD)
            0x3106, // Restar el valor de la dirección 06 (SUBTRACT)
            0x2107, // Almacenar el resultado en la dirección 07 (STORE)
            0x3506, // Elevar a la potencia de lo que esté en la dirección 06 (EXPONENCIACIÓN)
            0x3406, // Calcular el resto con el valor en la dirección 06 (MODULO)
            0x4300  // Fin del programa (HALT)
        };

        // Inicializar la memoria con algunos valores
        simpletron.cargarPrograma(new int[] {
            0x0008, // Dirección 05: Valor 8
            0x0003, // Dirección 06: Valor 3
            0x0000  // Dirección 07: Almacenará el resultado
        });

        // Cargar el programa en la memoria del Simpletron
        simpletron.cargarPrograma(programaSML);

        // Ejecutar el programa
        simpletron.ejecutarPrograma();
    }
}

