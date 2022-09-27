package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArbolB {
    public class Nodo{
        public long dpi;
        public String name, password;
        public ArbolABB arbol_binario;
        public ArbolAVL arbol_avl;
        public DoubleListAlbumes albumes;
        Nodo siguiente, anterior;
        B_Branch derecha, izquierda;
        public Nodo(long dpi, String name, String password){
            this.dpi = dpi;
            this.name = name;
            this.password = password;
            this.anterior = null;
            this.siguiente = null;
            this.derecha = null;
            this.izquierda = null;    
        }
    }
    int orden_arbol = 5;
    B_Branch raiz;

    public ArbolB() {
        this.raiz = null;
    }

    public boolean insertar(long dpi, String name, String password) {
        boolean existe = false;
        Nodo nodo = new Nodo(dpi, name, password);
        if (raiz == null) {
            raiz = new B_Branch();
            raiz.insertar(nodo);
        } else {
            Nodo obj = insertar_en_rama(nodo, raiz);
            if (obj != null) {
                //si devuelve algo el metodo de insertar en rama quiere decir que creo una nueva rama, y se debe insertar en el arbol
                raiz = new B_Branch();
                existe = raiz.insertar(obj);
                raiz.leaf = false;
            }
        }
        return existe;
    }

    private Nodo insertar_en_rama(Nodo nodo, B_Branch rama) {
        if (rama.leaf) {
            rama.insertar(nodo);
            if (rama.count == orden_arbol) {
                //si ya se insertaron todos los elementos posibles se debe dividir la rama
                return dividir(rama);
            } else {
                return null;
            }
        } else {
            Nodo temp = rama.primero;
            do {
                if (nodo.dpi == temp.dpi) {
                    return null;
                } else if (nodo.dpi < temp.dpi) {
                    Nodo obj = insertar_en_rama(nodo, temp.izquierda);
                    if (obj instanceof Nodo) {
                        rama.insertar((Nodo) obj);
                        if (rama.count == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.siguiente == null) {
                    Nodo obj = insertar_en_rama(nodo, temp.derecha);
                    if (obj instanceof Nodo) {
                        rama.insertar((Nodo) obj);
                        if (rama.count == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (Nodo) temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    private Nodo dividir(B_Branch rama) {
        long val = -999;
        String name = " ", password = "";
        Nodo temp, Nuevito;
        Nodo aux = rama.primero;
        B_Branch rderecha = new B_Branch();
        B_Branch rizquierda = new B_Branch();

        int cont = 0;
        while (aux != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) {
                temp = new Nodo(aux.dpi, aux.name, aux.password);
                temp.izquierda = aux.izquierda;
                if (cont == 2) {
                    temp.derecha = aux.siguiente.izquierda;
                } else {
                    temp.derecha = aux.derecha;
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rizquierda.leaf = false;
                }

                rizquierda.insertar(temp);

            } else if (cont == 3) {
                val = aux.dpi;
                name = aux.name;
                password = aux.password;
            } else {
                temp = new Nodo(aux.dpi, aux.name, aux.password);
                temp.izquierda = aux.izquierda;
                temp.derecha = aux.derecha;
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rderecha.leaf = false;
                }
                rderecha.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new Nodo(val, name, password);
        Nuevito.derecha = rderecha;
        Nuevito.izquierda = rizquierda;
        return Nuevito;
    }

    // EN ESTE RECORRIDO SE USAN LAS VARIABLES Y LOS METODOS DE ABAJO PARA
    // GENERAR EL ARBOL USANDO GRAPHVIZ
    String resultado;
    int c = 0, h = 0, r = 0;
    private void trav(B_Branch raiz){
        if (raiz != null) {
            if (!raiz.leaf) {
                Nodo aux = raiz.primero;
                String label = "";
                int contador = 0;
                while (aux != null) {
                    label += "<f"+contador+"> |"+aux.dpi+"\\n"+aux.name+"\\nImagenes"+"|";
                    contador++;
                    aux = aux.siguiente;
                }
                String nodo = "\traiz"+r+"[label=\""+label+ "<f"+(contador)+">" +"\"];\n";
                resultado += nodo;
                r++;
                trav(raiz.primero.izquierda);
                if (raiz.primero.siguiente != null) {
                    trav(raiz.primero.siguiente.izquierda);
                    trav(raiz.primero.siguiente.derecha);                        
                }else{
                    trav(raiz.primero.derecha);
                }
                Nodo temp = raiz.primero;
                String label2 = "";
                int contador2 = 0;
                while (temp != null) {
                    label2 += "<f"+contador2+"> |"+temp.dpi+"\\n"+temp.name+"\\nImagenes"+"|";
                    contador2++;
                    temp = temp.siguiente;
                }
                String nodo2 = "\tnodo"+c+"[label=\""+label2+ "<f"+(contador2)+">" +"\"];\n";
                String relacion = "\traiz"+(r-1)+":f"+(h)+"->nodo"+c+";\n";
                resultado += nodo2 + relacion;
                c++; h++;
            }else{
                Nodo temp = raiz.primero;
                String label = "";
                int contador = 0;

                while (temp != null) {
                    label += "<f"+contador+"> |"+temp.dpi+"\\n"+temp.name+"\\nImagenes"+"|";
                    contador++;
                    temp = temp.siguiente;
                }
                String nodo = "\tnodo"+c+"[label=\""+label+ "<f"+(contador)+">" +"\"];\n";
                String relacion = "\traiz"+(r-1)+":f"+(h)+"->nodo"+c+";\n";
                resultado += nodo + relacion;
                c++; h++;
            }
        }
    }
    public void traversal() {
        resultado = "digraph G{\n\tlabelloc=\"t\";\nlabel=<<B>Arbol ABB</B>>;\n\tnode[shape=record,height=.1];\n\tgraph[splines=false];\n";
        c = 0; h = 0; r = 0;
        trav(raiz);
        String raices = "";
        if (r > 0) {
            for (int i = 1; i < r; i++) {
                raices += "\traiz0"+"->"+"raiz"+i+";\n";
            }
        }
        resultado += raices;
        resultado += "\n}";
        generar_graphviz();
    }


    // DIFERENTES BUSQUEDAS EN EL ARBOL SEGUN NECESIDAD
    boolean encontrado = false;
    private boolean busqueda_para_login(Long dpi, String password, B_Branch root) {
        if (root != null) {
            if (!raiz.leaf) {
                busqueda_para_login(dpi, password,root.primero.izquierda);
                if (root.primero.siguiente != null) {
                    busqueda_para_login(dpi, password,root.primero.siguiente.izquierda);
                    busqueda_para_login(dpi, password,root.primero.siguiente.derecha);                        
                }else{
                    busqueda_para_login(dpi, password,root.primero.derecha);
                }
                Nodo aux = root.primero;
                while (aux != null) {
                    if (dpi.equals(aux.dpi) && password.equals(aux.password)) {
                        encontrado = true;
                    }                    
                    aux = aux.siguiente;
                }
            }else{
                Nodo aux = root.primero;
                while (aux != null) {
                    if (dpi.equals(aux.dpi) && password.equals(aux.password)) {
                        encontrado = true;
                    }                    
                    aux = aux.siguiente;
                }
            }
        }
        return encontrado;
    }
    public boolean realizar_login(Long dpi, String password) {
        return busqueda_para_login(dpi, password, raiz);
    }
    
    boolean modificado = false;
    private void busqueda_para_modificar(Long dpi, String name, String password,B_Branch root) {
        if (root != null) {
            if (!raiz.leaf) {
                busqueda_para_modificar(dpi, name, password, root.primero.izquierda);
                if (root.primero.siguiente != null) {
                    busqueda_para_modificar(dpi, name, password,root.primero.siguiente.izquierda);
                    busqueda_para_modificar(dpi, name, password,root.primero.siguiente.derecha);                        
                }else{
                    busqueda_para_modificar(dpi, name, password,root.primero.derecha);
                }
                Nodo aux = root.primero;
                while (aux != null) {
                    if (dpi.equals(aux.dpi)) {
                        aux.name = name;
                        aux.password = password;
                        modificado = true;
                    }                        
                    aux = aux.siguiente;
                }
            }else{
                Nodo aux = root.primero;
                while (aux != null) {
                    if (dpi.equals(aux.dpi)) {
                        aux.name = name;
                        aux.password = password;
                        modificado = true;
                    }                        
                    aux = aux.siguiente;
                }
            }
        }
    }
    public boolean realizar_modificar(Long dpi, String name, String password) {
        modificado = false;
        busqueda_para_modificar(dpi, name, password, raiz);
        return modificado;
    }

    Nodo temp = null;
    private void busqueda_para_datos(Long dpi,B_Branch root) {
        if (root != null) {
            if (!raiz.leaf) {
                busqueda_para_datos(dpi,root.primero.izquierda);
                if (root.primero.siguiente != null) {
                    busqueda_para_datos(dpi,root.primero.siguiente.izquierda);
                    busqueda_para_datos(dpi,root.primero.siguiente.derecha);                        
                }else{
                    busqueda_para_datos(dpi,root.primero.derecha);
                }
                Nodo aux = root.primero;
                while (aux != null) {
                    if (dpi.equals(aux.dpi)) {
                        temp = aux;
                    }                        
                    aux = aux.siguiente;
                }
            }else{
                Nodo aux = root.primero;
                while (aux != null) {
                    if (dpi.equals(aux.dpi)) {
                        temp = aux;
                    }                        
                    aux = aux.siguiente;
                }
            }
        }
    }
    public Nodo realizar_visualizacion(Long dpi) {
        temp = null;
        busqueda_para_datos(dpi, raiz);
        return temp;
    }

    // METODO UNICAMENTE UTILIZADO PARA LLENAR LA TABLA QUE CONTENDRA A LOS USUARIOS
    // LA TABLA ES UN COMPONENTE EXTRA DE LA INTERFAZ
    // DEVUELVE UN ARREGLO QUE SOLO SE UTILIZA EN ESA INSTANCIA
    ArrayList<Nodo> clientes = new ArrayList<Nodo>();
    private ArrayList<Nodo> recorrer_para_tabla(B_Branch root) {
        if (root != null) {
            if (!raiz.leaf) {
                recorrer_para_tabla(root.primero.izquierda);
                if (root.primero.siguiente != null) {
                    recorrer_para_tabla(root.primero.siguiente.izquierda);
                    recorrer_para_tabla(root.primero.siguiente.derecha);                        
                }else{
                    recorrer_para_tabla(root.primero.derecha);
                }
                Nodo aux = root.primero;
                while (aux != null) {
                    clientes.add(aux);                
                    aux = aux.siguiente;
                }                    
            }else{
                Nodo aux = root.primero;
                while (aux != null) {
                    clientes.add(aux);                
                    aux = aux.siguiente;
                }    
            }
        }
        return clientes;
    }
    public ArrayList<Nodo> get_clientes_array() {
        clientes = new ArrayList<Nodo>();
        clientes = recorrer_para_tabla(raiz);
        return clientes;
    }

    // Generacion del arbol utilizando graphviz
    private void generar_graphviz() {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Archivos_TXT\\ArbolB.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\B\\ArbolB.png";
        File dotFile = new File(ubicacion_dot);
        try {dotFile.createNewFile();} catch (Exception e) {}
        FileWriter writer;
        try {
            writer = new FileWriter(ubicacion_dot);
            writer.write(resultado);
            writer.close();
        } catch (Exception e) {}
        String comando = "dot -Tpng " + ubicacion_dot + " -o " + ubicacion_img;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c"+comando);
        builder.redirectErrorStream(true);
        try {builder.start();} catch (IOException e) {}
    }
}