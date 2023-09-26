package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ListaConPI;

public class LEGListaConPI<E> implements ListaConPI<E> {
    protected NodoLEG<E> pri = new NodoLEG((Object)null);
    protected NodoLEG<E> ant;
    protected NodoLEG<E> ult;
    protected int talla;

    public LEGListaConPI() {
        this.ult = this.pri;
        this.ant = this.pri;
        this.talla = 0;
    }

    public void insertar(E e) {
        NodoLEG<E> nuevo = new NodoLEG(e);
        ++this.talla;
        nuevo.siguiente = this.ant.siguiente;
        this.ant.siguiente = nuevo;
        if (this.ant == this.ult) {
            this.ult = nuevo;
        }

        this.ant = nuevo;
    }

    public void eliminar() {
        --this.talla;
        if (this.ant.siguiente == this.ult) {
            this.ult = this.ant;
        }

        this.ant.siguiente = this.ant.siguiente.siguiente;
    }

    public void inicio() {
        this.ant = this.pri;
    }

    public void siguiente() {
        this.ant = this.ant.siguiente;
    }

    public void fin() {
        this.ant = this.ult;
    }

    public E recuperar() {
        return this.ant.siguiente.dato;
    }

    public boolean esFin() {
        return this.ant == this.ult;
    }

    public boolean esVacia() {
        return this.pri == this.ult;
    }

    public int talla() {
        return this.talla;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        NodoLEG<E> aux = this.pri.siguiente;
        int i = 0;

        for(int j = this.talla - 1; i < j; aux = aux.siguiente) {
            s.append(aux.dato.toString() + ", ");
            ++i;
        }

        if (this.talla != 0) {
            s.append(aux.dato.toString() + "]");
        } else {
            s.append("]");
        }

        return s.toString();
    }
}