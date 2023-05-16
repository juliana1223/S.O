
package com.mycompany.proyectorr;

import javax.swing.JOptionPane;

/**
 * @author Juliana y Santiago 
 */
public class RoundRobin {
    public static void main(String[] args) {

        int n = 0;
        int quantum = 0;
        String cadena;

        // El usuario digita la cantidad de procesos que requiere
        cadena = JOptionPane.showInputDialog("Ingrese el número de procesos: ");

        // Invocación de la función en un while y se verIfica que el dato ingresado si sea n número
        while (isNumeric(cadena) == false) {
            cadena = JOptionPane.showInputDialog("Valor inválido, por favor vuelva a ingresar un número");
        }
        // En caso de que si sea un número se le da el valor de cadena a N
        if (isNumeric(cadena) == true) {
            n = Integer.parseInt(cadena);
        }

        // Estos 3 arrays almacenan tiempos de ráfaga, espera y retorno
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];  

        // El usuario ingresa el tiempo de ráfaga para cada proceso  
        for (int i = 0; i < n; i++) {
            JOptionPane.showMessageDialog(null, "Proceso : " + (i + 1));
            try {
                burstTime[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el tiempo de ráfaga de cada proceso: "));
            } catch (Exception e) {
                //En caso de que el usuario digite un dato incorrecto como una letra se tomara como valor 0;
                 burstTime[i] = 0;
            }
        }

        // El usuario ingresa el tamaño del quantum
        cadena = JOptionPane.showInputDialog("Ingresa el tamaño del quantum:");
        
         while (isNumeric(cadena) == false) {
            cadena = JOptionPane.showInputDialog("Valor inválido, por favor vuelva a ingresar un número");
        }

        if (isNumeric(cadena) == true) {
             quantum = Integer.parseInt(cadena);
        }
              
        // Se verifica que el número de procesos y el tamaño del quantum sean mayores que cero
        if (n <= 0 || quantum <= 0) {
            JOptionPane.showMessageDialog(null, "El número de procesos y el tamaño del quantum deben ser mayores a cero");
            main(args);
        }

        // Este array almacena el tiempo restante de cada proceso
        int[] remainingTime = new int[n];
        for (int i = 0; i < n; i++) {
            remainingTime[i] = burstTime[i];
        }

        int time = 0;
        while (true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0) {
                    done = false;
                    if (remainingTime[i] > quantum) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        time += remainingTime[i];
                        waitingTime[i] = time - burstTime[i];
                        remainingTime[i] = 0;
                    }
                }
            }
            if (done == true) {
                break;
            }
        }

        // Para calcular el tiempo de retorno para cada proceso
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
        }

        // Para la mpresión de tiempos de ráfaga, espera y retorno de cada proceso
        for (int i = 0; i < n; i++) {
            JOptionPane.showMessageDialog(null, "Proceso: " + (i + 1) + 
                                                             "\n Tiempo de rafaga: " + burstTime[i] +
                                                             "\n Tiempo de espera: " + waitingTime[i] + 
                                                             "\n Tiempo de retorno: " + turnaroundTime[i]);
        }

        // Para calcular y mostrar el tiempo promedio de espera y retorno
        float totalWaitingTime = 0;
        float totalTurnaroundTime = 0;

        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        JOptionPane.showMessageDialog(null, "Tiempo promedio de espera: " + (totalWaitingTime / n));
        JOptionPane.showMessageDialog(null, "Tiempo promedio de retorno: " + (totalTurnaroundTime / n));

    }

    
    // Método para controlar la excepcion de los números
    public static boolean isNumeric(String cadena) {

        boolean verdad;
        try {
            Integer.parseInt(cadena);
            verdad = true;
        } catch (NumberFormatException excepcion) {
            verdad = false;
        }

        return verdad;
    }

}



