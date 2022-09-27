package Estructuras;

public class NodoDispersa{
    public String valor;
    public int columna;
    public int fila;
    public NodoDispersa derecha;
    NodoDispersa izquierda;
    NodoDispersa arriba;
    NodoDispersa abajo;
    NodoDispersa siguiente;
    NodoDispersa anterior;


    public NodoDispersa(String valor, int columna, int fila) {
        this.valor = valor;
        this.columna = columna;
        this.fila = fila;
        this.derecha = null;
        this.izquierda = null;
        this.arriba = null;
        this.abajo = null;
        this.siguiente = null;
        this.anterior = null;
    }
    

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getColumna() {
        return this.columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return this.fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public NodoDispersa getDerecha() {
        return this.derecha;
    }

    public void setDerecha(NodoDispersa derecha) {
        this.derecha = derecha;
    }

    public NodoDispersa getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(NodoDispersa izquierda) {
        this.izquierda = izquierda;
    }

    public NodoDispersa getArriba() {
        return this.arriba;
    }

    public void setArriba(NodoDispersa arriba) {
        this.arriba = arriba;
    }

    public NodoDispersa getAbajo() {
        return this.abajo;
    }

    public void setAbajo(NodoDispersa abajo) {
        this.abajo = abajo;
    }

    public NodoDispersa getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoDispersa siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDispersa getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoDispersa anterior) {
        this.anterior = anterior;
    }

}