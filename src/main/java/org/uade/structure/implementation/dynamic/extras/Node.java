package org.uade.structure.implementation.dynamic.extras;

public class Node {
    int data;    // El valor que guarda este vagón
    Node next;   // El siguiente vagón en la lista (puede ser null si es el último)

    public Node(int data) {
        this.data = data;
        this.next = null; // Al principio, no está conectado a ningún otro vagón
    }
}