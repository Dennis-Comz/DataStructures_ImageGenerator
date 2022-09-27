package Estructuras;

public class B_Branch {
    boolean leaf;
    int count;
    ArbolB.Nodo primero;

    public B_Branch() {
        this.primero = null;
        this.leaf = true;
        this.count = 0;
    }

    public boolean insertar(ArbolB.Nodo newNode) {
        boolean existencia = false;
        if (primero == null) {
            primero = newNode;
            count++;
        }else{
            ArbolB.Nodo aux = primero;
            while (aux != null) {
                if (aux.dpi == newNode.dpi) {
                    existencia = true;
                    // System.out.println("ya existe");
                    break;
                }else{
                    if (aux.dpi > newNode.dpi) {
                        if (aux == primero) {
                            aux.anterior = newNode;
                            newNode.siguiente = aux;

                            aux.izquierda = newNode.derecha;
                            newNode.derecha = null;
                            
                            primero = newNode;
                            count++;
                            break;
                        }else{
                            newNode.siguiente = aux;
                            aux.izquierda = newNode.derecha;
                            newNode.derecha = null;

                            newNode.anterior = aux.anterior;
                            aux.anterior.siguiente = newNode;
                            aux.anterior = newNode;
                            count++;
                            break;
                        }
                    }else if(aux.siguiente == null){
                        aux.siguiente = newNode;
                        newNode.anterior = aux;
                        count++;
                        break;
                    }
                }
                aux = aux.siguiente;
            }
        }
        return existencia;
    }
}