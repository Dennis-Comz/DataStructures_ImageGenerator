package Estructuras;

public class LinkedListCapas {
    public class Node{
        int num;
        Node next;
        public Node(int num){
            this.num = num;
        }
    }

    Node root;

    public LinkedListCapas(){
        root = null;
    }

    public void insertar(int num) {
        if (root == null) {
            root = new Node(num);
        }else{
            Node last = root;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node(num);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void generateImage(String dpi, int key, ArbolABB capas) {
        Node last = root;
        MatrizDispersa img_matriz = new MatrizDispersa();
        while (last != null) {
            ArbolABB.Nodo capa = capas.search(last.num);
            if (capa != null) {
                ListaEncabezado filas = capa.matriz.getEncabezado();
                NodoEncabezado eFila = filas.primero;
                while (eFila != null) {
                    NodoDispersa actual = eFila.accesoNodo;
                    while (actual != null) {
                        img_matriz.insertar(actual.fila, actual.columna, actual.valor);                        
                        actual = actual.derecha;
                    }
                    eFila = eFila.siguiente;
                }
            }else{
                System.out.println("Null en "+last.num);
            }
            last = last.next;                    
        }
        if (!img_matriz.esta_vacia()) {
            img_matriz.generar_imagen_final(dpi+"Amplitud"+key, "\\Imagenes\\");
            img_matriz.generar_graphviz(dpi+"Amplitud"+key, "\\Imagenes\\");            
        }
    }
    
    public void generateImage_Capas(String dpi, int key, ArbolABB capas) {
        Node last = root;
        MatrizDispersa img_matriz = new MatrizDispersa();
        while (last != null) {
            ArbolABB.Nodo capa = capas.search(last.num);
            if (capa != null) {
                ListaEncabezado filas = capa.matriz.getEncabezado();
                NodoEncabezado eFila = filas.primero;
                while (eFila != null) {
                    NodoDispersa actual = eFila.accesoNodo;
                    while (actual != null) {
                        img_matriz.insertar(actual.fila, actual.columna, actual.valor);                        
                        actual = actual.derecha;
                    }
                    eFila = eFila.siguiente;
                }
            }else{
                System.out.println("Null en "+last.num);
            }
            last = last.next;                    
        }
        if (!img_matriz.esta_vacia()) {
            img_matriz.generar_imagen_final(dpi+"PorCapas"+key, "\\Imagenes\\");
            img_matriz.generar_graphviz(dpi+"PorCapas"+key, "\\Imagenes\\");            
        }
    }
}
