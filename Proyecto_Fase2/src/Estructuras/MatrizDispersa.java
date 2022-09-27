package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MatrizDispersa {
    ListaEncabezado filas = new ListaEncabezado();
    ListaEncabezado columnas = new ListaEncabezado();

    public void insertar(int fila, int columna, String valor) {
        NodoDispersa nuevo = new NodoDispersa(valor, columna, fila);
        boolean existe = verificar(nuevo);
        if (!existe) {
            // Insercion de encabezado por filas
            NodoEncabezado eFila = filas.getEncabezado(fila);
            if (eFila == null) {
                eFila = new NodoEncabezado(fila);
                eFila.accesoNodo = nuevo;
                filas.setEncabezado(eFila);
            }else{
                if (nuevo.columna < eFila.accesoNodo.columna) {
                    nuevo.derecha = eFila.accesoNodo;
                    eFila.accesoNodo.izquierda = nuevo;
                    eFila.accesoNodo = nuevo;
                }else{
                    NodoDispersa actual = eFila.accesoNodo;
                    while (actual.derecha != null) {
                        if (nuevo.columna < actual.derecha.columna) {
                            nuevo.derecha = actual.derecha;
                            actual.derecha.izquierda = nuevo;
                            nuevo.izquierda = actual;
                            actual.derecha = nuevo;
                            break;
                        }
                        actual = actual.derecha;
                    }
                    if (actual.derecha == null) {
                        actual.derecha = nuevo;
                        nuevo.izquierda = actual;
                    }
                }
            }

            // Insercion de encabezado por columnas
            NodoEncabezado eColumna = columnas.getEncabezado(columna);
            if (eColumna == null) {
                eColumna = new NodoEncabezado(columna);
                eColumna.accesoNodo = nuevo;
                columnas.setEncabezado(eColumna);
            }else{
                if (nuevo.fila < eColumna.accesoNodo.fila) {
                    nuevo.abajo = eColumna.accesoNodo;
                    eColumna.accesoNodo.arriba = nuevo;
                    eColumna.accesoNodo = nuevo;
                }else{
                    NodoDispersa actual = eColumna.accesoNodo;
                    while (actual.abajo != null) {
                        if (nuevo.fila < actual.abajo.fila) {
                            nuevo.abajo = actual.abajo;
                            actual.abajo.arriba = nuevo;
                            nuevo.arriba = actual;
                            actual.abajo = nuevo;
                            break;
                        }
                        actual = actual.abajo;
                    }
                    if (actual.abajo == null) {
                        actual.abajo = nuevo;
                        nuevo.arriba = actual;
                    }
                }
            }
        }
    }

    public boolean esta_vacia() {
        if (filas.primero == null && columnas.primero == null) {
            return true;
        }
        return false;
    }

    public boolean verificar(NodoDispersa nuevo){
        boolean existencia = false;
        if (!esta_vacia()) {
            NodoEncabezado eFila = filas.primero;
            while (eFila != null) {
                NodoDispersa actual = eFila.accesoNodo;
                while (actual != null) {
                    if (actual.fila == nuevo.fila && actual.columna == nuevo.columna) {
                        existencia = true;
                        return existencia;
                    }
                    actual = actual.derecha;
                }
                eFila = eFila.siguiente;
            }
        }
        return existencia;
    }

    public void imprimir() {
        NodoEncabezado eFila = filas.primero;
        System.out.println("=========== FILAS ===========");
        while (eFila != null) {
            NodoDispersa actual = eFila.accesoNodo;
            System.out.println("\n Fila " + actual.fila);
            System.out.println("Columna\tValor");
            while (actual != null) {
                System.out.println(actual.columna + "\t" + actual.valor);
                actual = actual.derecha;
            }
            eFila = eFila.siguiente;
        }
        System.out.println("============= FIN =============");
        NodoEncabezado eColumna = columnas.primero;
        System.out.println("=========== COLUMNAS ===========");
        while (eColumna != null) {
            NodoDispersa actual = eColumna.accesoNodo;
            System.out.println("\n Columna " + actual.columna);
            System.out.println("Fila\tValor");
            while (actual != null) {
                System.out.println(actual.fila + "\t" + actual.valor);
                actual = actual.abajo;
            }
            eColumna = eColumna.siguiente;
        }
        System.out.println("============= FIN =============");
    }

    public ListaEncabezado getEncabezado() {
        return filas;
    }

    public int fila_mayor() {
        // Obtener fila mayor
        NodoEncabezado eFila = filas.primero;
        int fila_num = 0;
        while (eFila.siguiente != null) {
            if (eFila.id > eFila.siguiente.id) {
                fila_num = eFila.id;
            }else{
                fila_num = eFila.siguiente.id;
            }
            eFila = eFila.siguiente;
        }
        return fila_num;
    }

    public int columna_mayor() {
        // Obtener columna mayor
        NodoEncabezado eColumna = columnas.primero;
        int columna_num = 0;
        while (eColumna.siguiente != null) {
            if (eColumna.id > eColumna.siguiente.id) {
                columna_num = eColumna.id;
            }else{
                columna_num = eColumna.siguiente.id;
            }
            eColumna = eColumna.siguiente;
        }
        return columna_num;
    }

    public String delete_last(String nodos) {
        String nuevo = "";
        StringBuffer sb = new StringBuffer(nodos);
        sb.deleteCharAt(sb.length()-1);
        nuevo = sb.toString();
        return nuevo;
    }

    public void generar_graphviz(String num, String lugar) {
        String result = "digraph L{\n\trankdir=TB;\n\tnode[shape=box,style=filled,fillcolor=none];\n\tgraph[nodesep=0.5];\nedge[dir=both];\n\t\traiz[label=\"Fila/Columna\"];\n";
        int max_fila = fila_mayor();
        int max_columna = columna_mayor();
        String cols_rank = "";
        // Ciclo para crear las filas
        for (int i = 0; i <= max_fila; i++) {
            result += "\t\tFila"+i+"[label = \""+i+"\",group=0];\n";
        }
        // Ciclo para enlazar las filas
        for (int i = 0; i <= max_fila; i++) {
            if (i+1 <= max_fila) {
                result += "\t\tFila"+i+" -> Fila"+(i+1)+";\n";
            }
        }
        // Ciclo para crear las columnas
        for (int i = 0; i <= max_columna; i++) {
            if (i+1 <= max_columna) {
                cols_rank += "Columna"+i+";";                
            }else{
                cols_rank += "Columna"+i;
            }
            result += "\t\tColumna"+i+"[label = \""+i+"\",group="+(i+1)+"];\n";
        }
        // Ciclo para enlazar las columnas
        for (int i = 0; i <= max_columna; i++) {
            if (i+1 <= max_columna) {
                result += "\t\tColumna"+i+" -> Columna"+(i+1)+";\n";
            }
        }
        // Punto de enlace de la raiz con las filas y columnas
        result += "\t\traiz->Fila0;\n\t\traiz->Columna0;\n";
        result += "\t\t{rank=same raiz;"+cols_rank+"}\n";

        /* 
        Recorrido por filas para crear los nodos y enlazarlos de izquierda a derecha
        */
        NodoEncabezado eFila = filas.primero;
        while (eFila != null) {
            NodoDispersa actual = eFila.accesoNodo;
            NodoDispersa actual_enlaces = eFila.accesoNodo;
            String enlace = "\t\tFila"+eFila.id+"->nodo"+actual.columna+"_"+actual.fila+"[constraint=false];\n";
            String enlace_rank = "";
            while (actual != null) {
                enlace_rank += "nodo"+actual.columna+"_"+actual.fila+";";
                result += "\t\tnodo"+actual.columna+"_"+actual.fila+"[label=\"\", group="+(actual.columna+1)+", fillcolor=\""+actual.valor+"\"]\n";
                actual = actual.derecha;
            }
            result += enlace;
            while (actual_enlaces.derecha != null) {
                result += "\t\tnodo"+actual_enlaces.columna+"_"+actual_enlaces.fila+"->"+"nodo"+actual_enlaces.derecha.columna+"_"+actual_enlaces.derecha.fila+";\n";
                actual_enlaces = actual_enlaces.derecha;
            }
            enlace_rank = delete_last(enlace_rank);
            result += "\t\t{rank = same;Fila"+eFila.id+";"+enlace_rank+"}\n";
            eFila = eFila.siguiente;
        }
        NodoEncabezado eColumna = columnas.primero;
        while (eColumna != null) {
            NodoDispersa actual = eColumna.accesoNodo;
            result += "\t\tColumna"+eColumna.id+"->nodo"+actual.columna+"_"+actual.fila+";\n";
            while (actual.abajo != null) {
                result += "\t\tnodo"+actual.columna+"_"+actual.fila+"->"+"nodo"+actual.abajo.columna+"_"+actual.abajo.fila+";\n";
                actual = actual.abajo;
            }
            eColumna = eColumna.siguiente;
        }

        // Cierre de la grafica
        result += "\n}";
        archivo(lugar, num, result);
        // System.out.println(result);
    }

    public void generar_imagen_final(String num, String lugar) {
        String result = "digraph L{\n\trankdir=TB;\n\tnode[shape=plaintext style=filled fillcolor=none];\n\tsplines=none;\n\tgraph[nodesep=0 ranksep=0];\n\t\traiz[label=\"Fila/Columna\" style=\"invis\"];\n";
        int max_fila = fila_mayor();
        int max_columna = columna_mayor();
        String cols_rank = "";
        // Ciclo para crear las filas
        for (int i = 0; i <= max_fila; i++) {
            result += "\t\tFila"+i+"[label = \""+i+"\",group=0 style=\"invis\"];\n";
        }
        // Ciclo para enlazar las filas
        for (int i = 0; i <= max_fila; i++) {
            if (i+1 <= max_fila) {
                result += "\t\tFila"+i+" -> Fila"+(i+1)+";\n";
            }
        }
        // Ciclo para crear las columnas
        for (int i = 0; i <= max_columna; i++) {
            if (i+1 <= max_columna) {
                cols_rank += "Columna"+i+";";                
            }else{
                cols_rank += "Columna"+i;
            }
            result += "\t\tColumna"+i+"[label = \""+i+"\",group="+(i+1)+" style=\"invis\"];\n";
        }
        // Ciclo para enlazar las columnas
        for (int i = 0; i <= max_columna; i++) {
            if (i+1 <= max_columna) {
                result += "\t\tColumna"+i+" -> Columna"+(i+1)+";\n";
            }
        }
        // Punto de enlace de la raiz con las filas y columnas
        result += "\t\traiz->Fila0;\n\t\traiz->Columna0;\n";
        result += "\t\t{rank=same raiz;"+cols_rank+"}\n";

        /* 
        Recorrido por filas para crear los nodos y enlazarlos de izquierda a derecha
        */
        NodoEncabezado eFila = filas.primero;
        while (eFila != null) {
            NodoDispersa actual = eFila.accesoNodo;
            NodoDispersa actual_enlaces = eFila.accesoNodo;
            String enlace = "\t\tFila"+eFila.id+"->nodo"+actual.columna+"_"+actual.fila+"[constraint=false];\n";
            String enlace_rank = "";
            while (actual != null) {
                enlace_rank += "nodo"+actual.columna+"_"+actual.fila+";";
                result += "\t\tnodo"+actual.columna+"_"+actual.fila+"[label=\"\", group="+(actual.columna+1)+", fillcolor=\""+actual.valor+"\"]\n";
                actual = actual.derecha;
            }
            result += enlace;
            while (actual_enlaces.derecha != null) {
                result += "\t\tnodo"+actual_enlaces.columna+"_"+actual_enlaces.fila+"->"+"nodo"+actual_enlaces.derecha.columna+"_"+actual_enlaces.derecha.fila+";\n";
                actual_enlaces = actual_enlaces.derecha;
            }
            enlace_rank = delete_last(enlace_rank);
            result += "\t\t{rank = same;Fila"+eFila.id+";"+enlace_rank+"}\n";
            eFila = eFila.siguiente;
        }
        NodoEncabezado eColumna = columnas.primero;
        while (eColumna != null) {
            NodoDispersa actual = eColumna.accesoNodo;
            result += "\t\tColumna"+eColumna.id+"->nodo"+actual.columna+"_"+actual.fila+";\n";
            while (actual.abajo != null) {
                result += "\t\tnodo"+actual.columna+"_"+actual.fila+"->"+"nodo"+actual.abajo.columna+"_"+actual.abajo.fila+";\n";
                actual = actual.abajo;
            }
            eColumna = eColumna.siguiente;
        }

        // Cierre de la grafica
        result += "\n}";
        archivo(lugar, "Final"+num, result);
        // System.out.println(result);
    }

    public void archivo(String lugar, String name, String resultado) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Archivos_TXT\\" + name +".txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\"+lugar + name +".png";
        File dotFile = new File(ubicacion_dot);
        try {dotFile.createNewFile();} catch (Exception e) {}
        FileWriter writer;
        try {
            writer = new FileWriter(ubicacion_dot);
            writer.write(resultado);
            writer.close();
        } catch (Exception e) {}
        String comando = "dot -Gnslimit=6 -Tpng -Gsize=7,7\\! -Gdpi=100 " + ubicacion_dot + " -o " + ubicacion_img;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c"+comando);
        builder.redirectErrorStream(true);
        try {builder.start();} catch (IOException e) {}
    }
}