package aplicaciones.pruebasOrdenacion;

import librerias.util.Ordenacion;
import java.util.Arrays;
import java.util.Locale;

/** 
 *  La clase TestOrdenacion permite probar y temporizar los  
 *  métodos de ordenacion de arrays genéricos definidos en
 *  la clase librerias.util.Ordenacion.
 *  
 *  @author (EDA) 
 *  @version (Curso 2019-2020)
 */

public class TestOrdenacion {

    /**
     * Comprueba la corrección del método mergeSort2 de
     * la clase Ordenacion, basándose en la corrección
     * del método quickSort.
     */
    public static boolean comprobar() {
        Integer[] a1 = crearAleatorioInteger(1000);
        Integer[] a2 = Arrays.copyOf(a1, a1.length);
        // Ordenacion por Quick Sort de a1:
        Ordenacion.quickSort(a1);

        // Ordenacion por Merge Sort (version 2) de a2:
        Ordenacion.mergeSort2(a2);
		
		// Son iguales a1 (quickSort) y a2 (mergeSort2)?
		return Ordenacion.sonIguales(a1, a2);
    }

    /**
     * Temporización sobre Integer de los métodos mergeSort1,
     * mergeSort2 y quickSort de la clase Ordenacion.
     */
    public static void temporizar() {
        final int INI = 10000;
        final int FI = 100000;
        final int numRep = 200;
        double t1, t2, tacum1, tacum2, tacum3;
        Integer[] aux1, aux2, aux3;
        
        System.out.println("#----------------------------------------------");        
        System.out.println("# Comparación entre quickSort y mergeSort: ");
        System.out.println("# Tiempos en milisegs para Integers.");
        System.out.println("#----------------------------------------------");
        System.out.println("#  Talla    mergeSort1   mergeSort2   quickSort");
        System.out.println("#----------------------------------------------");
        for (int k = INI; k <= FI; k = k + INI) {
            tacum1 = 0; tacum2 = 0; tacum3 = 0;
            for (int i = 1; i <= numRep; i++) {
                aux1 = crearAleatorioInteger(k);
                aux2 = Arrays.copyOf(aux1, aux1.length);
                aux3 = Arrays.copyOf(aux1, aux1.length);
                                             
                t1 = System.nanoTime();
                Ordenacion.mergeSort1(aux1);
                t2 = System.nanoTime();
                tacum1 += t2 - t1;

                t1 = System.nanoTime();
                Ordenacion.mergeSort2(aux2);
                t2 = System.nanoTime();
                tacum2 += t2 - t1;

                t1 = System.nanoTime();
                Ordenacion.quickSort(aux3);
                t2 = System.nanoTime();
                tacum3 += t2 - t1;                                                                                                             
            }
                      
            System.out.printf(Locale.US,
                              "%1$8d %2$12.4f %3$12.4f %4$12.4f\n",
                    k,
                              tacum1 / numRep / 1e6,
                              tacum2 / numRep / 1e6,
                              tacum3 / numRep / 1e6);
        }
    }
           
    /**
     * Devuelve un array de talla Integer generados aleatoriamente.
     * 
     * @param talla  Talla del array resultado
     * @return Integer[]
     */
    public static Integer[] crearAleatorioInteger(int talla) {
        Integer[] aux = new Integer[talla];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = (int) (Math.random() * (10 * talla));
        }
        return aux;
    }

    /**
     * Temporización sobre String de los métodos mergeSort1,
     * mergeSort2 y quickSort de la clase Ordenacion.
     */
    public static void temporizarString() {
        final int INI = 10000;
        final int FI = 100000;
        final int numRep = 200;
        final int charIgual = 50;
        double t1, t2, tacum1, tacum2, tacum3;
        String[] aux1, aux2, aux3;
        
        System.out.println("#----------------------------------------------");        
        System.out.println("# Comparación entre quickSort y mergeSort: ");
        System.out.println("# Tiempos en milisegs para Strings - " + charIgual);
        System.out.println("#----------------------------------------------");
        System.out.println("#  Talla    mergeSort1   mergeSort2   quickSort");
        System.out.println("#----------------------------------------------");
        for (int k = INI; k <= FI; k = k + INI) {
            tacum1 = 0; tacum2 = 0; tacum3 = 0;
            for (int i = 1; i <= numRep; i++) {
                aux1 = crearAleatorioString(k, charIgual);
                aux2 = Arrays.copyOf(aux1, aux1.length);
                aux3 = Arrays.copyOf(aux1, aux1.length);
                                             
                t1 = System.nanoTime();
                Ordenacion.mergeSort1(aux1);
                t2 = System.nanoTime();
                tacum1 += t2 - t1;

                t1 = System.nanoTime();
                Ordenacion.mergeSort1(aux2);
                t2 = System.nanoTime();
                tacum2 += t2 - t1;

                t1 = System.nanoTime();
                Ordenacion.quickSort(aux3);
                t2 = System.nanoTime();
                tacum3 += t2 - t1;                                                                                                      
            }
                      
            System.out.printf(Locale.US,
                              "%1$8d %2$12.4f %3$12.4f %4$12.4f\n",
                    k,
                              tacum1 / numRep / 1e6, 
                              tacum2 / numRep / 1e6,
                              tacum3 / numRep / 1e6);
        }
    } 
    
    /**
     * Devuelve un array de talla String aleatorios con los primeros n 
     * caracteres iguales.
     * 
     * @param talla  Talla del array resultado
     * @param n  Número de caracteres iniciales iguales
     * @return String[]
     */    
    public static String[] crearAleatorioString(int talla, int n) {
        GeneradorDeString g = new GeneradorDeString(n);
        String[] res = new String[talla];
        for (int i = 0; i < talla; i++) res[i] = g.generar();
        return res;
    }
    
    public static void main(String[] args) {
        boolean okMS2 = comprobar();
        if (okMS2) {
            temporizar();
            temporizarString();
        }
        else {
            System.out.println("ERROR en mergeSort2: no ordena correctamente\n"
                               + "IMPOSIBLE temporizar hasta que no lo haga");
        }
    }                   
}

