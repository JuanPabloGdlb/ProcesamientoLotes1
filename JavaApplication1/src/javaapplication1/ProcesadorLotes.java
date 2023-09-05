package javaapplication1;

import java.io.FileReader;//Se usa para leer caracteres de un archivo
import java.io.FileWriter;//Se usa para abrir un archivo de texto para su escritura posterior.
import java.io.IOException;//Se utiliza para manejar errores relacionados con operaciones de lectura y escritura en archivos.
import java.io.BufferedReader;//Se usa para leer líneas completas de un archivo de texto y para almacenar temporalmente las líneas en un búfer.
import java.io.BufferedWriter;//Se usa para escribir datos y almacenar temporalmente los datos en un búfer antes de escribirlos en el archivo.

public class ProcesadorLotes {
    public static void main(String[] args) {
        //Definición de nombres de archivos de entrada y salida, junto con sus rutas
        String archivoEntrada = "D:\\tareas\\tareas2023B\\sem de so\\tarea 1\\py\\prueba2.txt";
        String archivoSalida = "D:\\tareas\\tareas2023B\\sem de so\\tarea 1\\py\\copia.txt";
        //Todo está en el Try-Catch, ya que el codigo es propenso a fallar por el archivo.
        //Se abre el archivo de entrada para lectura y el archivo de salida para escritura.
        //En el catch está IOException en caso de cualquier error con el archivo.
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))) {
            //Variable para almacenar cada línea del archivo de entrada
            String linea;
            //El bucle hace que se lea cada línea del archivo de entrada
            while ((linea = lector.readLine()) != null) {
                //Arreglo que con la función liena.split va a dividir la línea en partes separadas por comas ","
                String[] partes = linea.split(",");
                //Se define si hay 6 partes de la linea, continua. Debe haber 6 partes en el archivo.
                if (partes.length == 6) {
                    //En la parte 0, se devide por el /, eliminandolo.
                    String primerParte = partes[0].split("/")[0];
                    //Se dividen los valores hexadecimales en la primera parte en partes separadas por ":"
                    String[] valoresHexadecimales = primerParte.split(":");
                    // Se crea un StringBuilder para almacenar los valores decimales.
                    //Permite modificar una cadena sin crear una nueva cada vez que se realiza una modificación.
                    StringBuilder valoresDecimales = new StringBuilder();
                    //Se dividen la parte de los números decimales en la sexta parte en partes separadas por "."
                    //El punto es un caracter especial y puede ocasionar poblemas
                    //Las diagonales invertidas son para que se tome literalmente el punto como delimitador.
                    String[] valoresDecimalesString = partes[5].split("\\.");
                    //Se crea un StringBuilder para almacenar los valores decimales.
                    StringBuilder valoresHexadecimalesString = new StringBuilder();
                    //Bucle para convertir y agregar los valores hexadecimales
                    for (String hexadecimal : valoresHexadecimales) {
                        //Se convierte el valor hexadecimal en decimal
                        int valorDecimal = Integer.parseInt(hexadecimal, 16);
                         // Se agrega al StringBuilder decimalValues y se divide mediente los :
                        valoresDecimales.append(valorDecimal).append(":");
                    }
                    //Bucle para convertir y agregar los valores decimales
                    for (String decimal : valoresDecimalesString) {
                        //Se convierte el valor hexadecimal en decimal
                        int valorHexadecimal = Integer.parseInt(decimal);
                        //Se obtiene el valor hexadecimal como cadena
                        String valorHexadecimalString = Integer.toHexString(valorHexadecimal);
                        //Se agrega al StringBuilder hexadecimalValues y se divide por el punto
                        valoresHexadecimalesString.append(valorHexadecimalString).append(".");
                    }
                    //Se imprimen los valores en pantalla, con los valores hexadecimales en mayúsculas(.toUpperCase())
                    System.out.println(primerParte + "," + partes[2] + "," + valoresDecimales.toString() + ","
                            + valoresHexadecimalesString.toString().toUpperCase());
                    //Se escriben los valores en el archivo de salida, con los valores hexadecimales en mayúsculas
                    escritor.write(partes[2] + ":" + valoresDecimales.toString()
                            + valoresHexadecimalesString.toString().toUpperCase());
                    escritor.newLine();
                }
            }
        } catch (IOException e) {
            // Manejo de excepciones de E/S (lectura/escritura de archivos)
        }
    }
}