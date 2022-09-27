package Estructuras;
// public class Nodos {}

public class NodoEncabezado{
    int id;
    public NodoEncabezado siguiente;
    NodoEncabezado anterior;
    public NodoDispersa accesoNodo;
    public NodoEncabezado(int id) {
        this.id = id;
        this.siguiente = null;
        this.anterior = null;
        this.accesoNodo = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodoEncabezado getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoEncabezado siguiente) {
        this.siguiente = siguiente;
    }

    public NodoEncabezado getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoEncabezado anterior) {
        this.anterior = anterior;
    }

    public NodoDispersa getAccesoNodo() {
        return this.accesoNodo;
    }

    public void setAccesoNodo(NodoDispersa accesoNodo) {
        this.accesoNodo = accesoNodo;
    }
}
