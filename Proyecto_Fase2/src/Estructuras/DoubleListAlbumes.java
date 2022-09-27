package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DoubleListAlbumes {
    public class Nodo{
        String nombre;
        SimpleListImagenes imagenes;
        Nodo next;
        Nodo prev;
        public Nodo(String nombre, SimpleListImagenes imagenes){
            this.nombre = nombre;
            this.imagenes = imagenes;
        }
    }

    Nodo head;

    public void append(String nombre, SimpleListImagenes imagenes){
        Nodo newNode = new Nodo(nombre, imagenes);
        Nodo last = head;
        newNode.next = null;
        if (head == null) {
            newNode.prev = null;
            head = newNode;
            return;
        }else{
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
            newNode.prev = last;
        }
    }

    public void generar_graphviz(String dpi) {
        String result = "digraph G{\nlabelloc=\"t\";\nlabel=<<B>Albumes</B>>;\n"+
            "graph[splines=ortho, nodesep=1];\nnode[shape=box style=filled];\n"+
            "edge[arrowsize=0.6];\nsubgraph clushter{\nstyle=invis;\n";
        String conexiones = "";
        String nodos = "";
        Nodo aux = head;
        while (aux != null) {
            String conexion_auxiliar = "";
            nodos += "A"+aux.hashCode()+"[label=\""+aux.nombre+"\"];\n";
            SimpleListImagenes.Nodo nodo_img = aux.imagenes.head;
            if (nodo_img != null) {
                conexion_auxiliar += "A"+aux.hashCode()+" -> "+"I"+nodo_img.hashCode()+";\n";
                while (nodo_img != null) {
                    nodos += "I"+nodo_img.hashCode()+"[label=\"Imagen "+nodo_img.num+"\"];\n";            
                    if (nodo_img.next != null) {
                        conexiones += "I"+nodo_img.hashCode()+" -> "+"I"+nodo_img.next.hashCode()+";\n";
                    }
                    nodo_img = nodo_img.next;
                }
            }
            if (aux.next != null) {
                conexiones += "{ rank = same;\n";
                conexiones += "A"+aux.hashCode()+" -> "+"A"+aux.next.hashCode()+";\n";
                conexiones += "};\n";
                conexiones += "{ rank = same;\n";
                conexiones += "A"+aux.hashCode()+" -> "+"A"+aux.next.hashCode()+"[dir=back];\n";
                conexiones += "};\n";
            }
            conexiones += conexion_auxiliar;
            aux = aux.next;
        }
        result += nodos+"\n"+ conexiones+"\n}\n}";
        archivo(result, dpi);
    }

    // Generacion del arbol utilizando graphviz
    private void archivo(String resultado, String dpi) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Archivos_TXT\\Album.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Albumes\\"+dpi+"Album.png";
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