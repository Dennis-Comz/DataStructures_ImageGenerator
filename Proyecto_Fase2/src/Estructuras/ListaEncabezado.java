package Estructuras;

public class ListaEncabezado{
    public NodoEncabezado primero;

    public void setEncabezado(NodoEncabezado nuevo) {
        if (primero == null) {
            primero = nuevo;
        }else if(nuevo.id < primero.id){
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }else{
            NodoEncabezado actual = primero;
            while (actual.siguiente != null) {
                if (nuevo.id < actual.siguiente.id) {
                    nuevo.siguiente = actual.siguiente;
                    actual.siguiente.anterior = nuevo;
                    nuevo.anterior = actual;
                    actual.siguiente = nuevo;
                    break;
                }
                actual = actual.siguiente;
            }
            if (actual.siguiente == null) {
                actual.siguiente = nuevo;
                nuevo.anterior = actual;
            }
        }
    }

    public NodoEncabezado getEncabezado(int id) {
        NodoEncabezado actual = primero;
        while (actual != null) {
            if (actual.id == id) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}