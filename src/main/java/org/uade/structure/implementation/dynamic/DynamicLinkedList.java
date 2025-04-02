package org.uade.structure.implementation.dynamic;

import org.uade.exception.IndexOutOfBoundsException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.extras.Node;

public class DynamicLinkedList implements LinkedListADT {

    private Node head; // El primer "vagón" de la lista (puede ser null si la lista está vacía)
    private int size;  // La cantidad de elementos en la lista

    public DynamicLinkedList() {
        this.head = null; // Al principio, no hay ningún vagón
        this.size = 0;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value); // Creamos un nuevo "vagón" con el valor
        if (isEmpty()) {
            head = newNode; // Si la lista está vacía, este nuevo vagón es el primero
        } else {
            Node current = head; // Empezamos desde el primer vagón
            // Recorremos la lista hasta llegar al último vagón
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode; // Conectamos el último vagón con el nuevo
        }
        size++; // Aumentamos el número de vagones
    }

    @Override
    public void insert(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }

        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head; // El nuevo vagón se convierte en el primero y apunta al antiguo primero
            head = newNode;
        } else {
            Node current = head;
            // Nos movemos hasta el vagón que está *antes* del índice donde queremos insertar
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next; // El nuevo vagón apunta al vagón que estaba en el índice
            current.next = newNode;     // El vagón anterior ahora apunta al nuevo
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }

        if (index == 0) {
            head = head.next; // El segundo vagón se convierte en el primero
        } else {
            Node current = head;
            // Nos movemos hasta el vagón que está *antes* del índice que queremos eliminar
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            // "Saltamos" el vagón que queremos eliminar, conectando el anterior con el siguiente
            current.next = current.next.next;
        }
        size--;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }

        Node current = head;
        // Recorremos la lista hasta llegar al vagón del índice deseado
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data; // Devolvemos el valor que guarda ese vagón
    }

    @Override
    public int size() {
        return size; // Devolvemos la cantidad de vagones
    }

    @Override
    public boolean isEmpty() {
        return head == null; // La lista está vacía si no hay ningún vagón inicial
    }
}