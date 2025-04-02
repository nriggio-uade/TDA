package org.uade.structure.implementation.fixed;

import org.uade.exception.IndexOutOfBoundsException;
import org.uade.structure.definition.LinkedListADT;

public class StaticLinkedList implements LinkedListADT {

    private int[] elements; // Nuestro "estante" de tamaño fijo
    private int size;       // La cantidad de elementos que realmente tenemos en el estante

    // Constructor: Cuando creamos la lista, decidimos su tamaño máximo
    public StaticLinkedList(int capacity) {
        this.elements = new int[capacity]; // Creamos el estante con 'capacity' espacios
        this.size = 0;                     // Al principio, no hay nada en el estante
    }

    @Override
    public void add(int value) {
        // Si hay espacio en el estante...
        if (size < elements.length) {
            elements[size] = value; // Ponemos el nuevo valor en el siguiente espacio libre
            size++;                 // Aumentamos la cantidad de elementos en el estante
        } else {
            // Si el estante está lleno, no podemos agregar más
            throw new IllegalStateException("La lista está llena. No se puede agregar más elementos.");
        }
    }

    @Override
    public void insert(int index, int value) {
        // Primero, verificamos si el índice es válido (dentro de los límites del estante y ocupado)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }
        // También verificamos si hay espacio para insertar
        if (size == elements.length) {
            throw new IllegalStateException("La lista está llena. No se puede insertar más elementos.");
        }

        // Para insertar en el medio, necesitamos mover los elementos existentes hacia la derecha
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1]; // Movemos el elemento de la derecha a la posición actual
        }
        elements[index] = value; // Colocamos el nuevo valor en el índice deseado
        size++;                 // Aumentamos el tamaño de la lista
    }

    @Override
    public void remove(int index) {
        // Verificamos si el índice es válido y si hay algo que eliminar en ese índice
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }

        // Para eliminar, movemos los elementos a la izquierda para "llenar" el hueco
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1]; // El elemento de la derecha se mueve a la posición actual
        }
        elements[size - 1] = 0; // Opcional: Limpiamos la última posición (no es estrictamente necesario)
        size--;                 // Disminuimos el tamaño de la lista
    }

    @Override
    public int get(int index) {
        // Verificamos si el índice es válido y si hay un elemento en ese índice
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }
        return elements[index]; // Devolvemos el valor que está en ese espacio del estante
    }

    @Override
    public int size() {
        return size; // Devolvemos cuántos elementos hay actualmente en el estante
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // El estante está vacío si no hay ningún elemento
    }
}