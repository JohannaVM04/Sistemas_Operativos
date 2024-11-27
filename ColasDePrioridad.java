package com.mycompany.colasdeprioridad;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

class Emergencia {
    private int prioridad;
    private int orden;

    public Emergencia(int prioridad, int orden) {
        this.prioridad = prioridad;
        this.orden = orden;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getOrden() {
        return orden;
    }
}

class SalaEmergencias {
    private PriorityQueue<Emergencia> cola;
    private int contador;
    private Random random;

    public SalaEmergencias() {
        // Creamos una cola de prioridad con un comparador personalizado
        this.cola = new PriorityQueue<>((e1, e2) -> {
            // Primero comparamos por prioridad (de mayor a menor)
            int comparacionPrioridad = Integer.compare(e2.getPrioridad(), e1.getPrioridad());
            if (comparacionPrioridad != 0) {
                return comparacionPrioridad;
            }
            // Si las prioridades son iguales, mantenemos el orden de llegada
            return Integer.compare(e1.getOrden(), e2.getOrden());
        });
        this.contador = 0;
        this.random = new Random();
    }

    public void agregarEmergencia() {
        // Generar prioridad aleatoria (1: baja, 2: media, 3: alta)
        int prioridad = random.nextInt(3) + 1;
        
        Emergencia emergencia = new Emergencia(prioridad, contador++);
        cola.offer(emergencia);
        
        System.out.println("\n--- Nueva Emergencia Agregada ---");
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Orden de llegada: " + emergencia.getOrden());
        mostrarEmergencias();
    }

    public void atenderEmergencia() {
        if (cola.isEmpty()) {
            System.out.println("\n¡La cola de emergencias está vacía!");
            return;
        }
        
        Emergencia emergencia = cola.poll();
        System.out.println("\n--- Emergencia Atendida ---");
        System.out.println("Prioridad: " + emergencia.getPrioridad());
        System.out.println("Orden de llegada: " + emergencia.getOrden());
        
        mostrarEmergencias();
    }

    public void mostrarEmergencias() {
        if (cola.isEmpty()) {
            System.out.println("No hay emergencias en la cola.");
            return;
        }
        
        System.out.println("\nEmergencias en cola:");
        // Creamos una cola temporal para no modificar la original
        PriorityQueue<Emergencia> colaTemp = new PriorityQueue<>(cola);
        int i = 1;
        while (!colaTemp.isEmpty()) {
            Emergencia emergencia = colaTemp.poll();
            System.out.println(i++ + ". Prioridad " + emergencia.getPrioridad() + 
                               " (Orden: " + emergencia.getOrden() + ")");
        }
    }
}

public class ColasDePrioridad {
    public static void main(String[] args) {
        SalaEmergencias sala = new SalaEmergencias();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Sistema de Emergencias Hospitalarias ---");
            System.out.println("1. Agregar nueva emergencia");
            System.out.println("2. Atender emergencia");
            System.out.println("3. Salir");
            
            try {
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (opcion) {
                    case 1:
                        sala.agregarEmergencia();
                        break;
                    case 2:
                        sala.atenderEmergencia();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); 
            }
        }
    }
}