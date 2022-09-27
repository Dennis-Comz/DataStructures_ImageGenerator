package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ArbolAVL {
    public class Nodo implements Comparable<Nodo>{
        public int key;
        public ArbolABB capas;
        public LinkedListCapas list_capas;
        int alt;
        Nodo left, right;
        public Nodo(int key, ArbolABB capas, LinkedListCapas list){
            this.key = key;
            this.capas = capas;
            this.list_capas = list;
            left = right = null;
            alt = 0;
        }
        @Override
        public int compareTo(Nodo comparestu) {
            int compareCapas = ((Nodo)comparestu).capas.cantidad;
            return this.capas.cantidad-compareCapas;
        }
    }

    Nodo root = null;
    String resultado = "";

    public ArbolAVL(){
        resultado = "digraph G{\nlabelloc=\"t\";\nlabel=<<B>Arbol AVL</B>>;\nnodesep=0.4;\nranksep=0.5;\n";
    }
    // Insercion de los nodos al arbol y todos los metodos necesarios para esto
    public void add(int key, ArbolABB capas, LinkedListCapas list) {
        root = add(key, capas, list, root);
    }

    private Nodo add(int key, ArbolABB capas, LinkedListCapas list, Nodo tmp) {
        if (tmp == null) {
            tmp = new Nodo(key, capas, list);
        }else if(key < tmp.key){
            tmp.left = add(key,capas, list, tmp.left);
            if ((altura(tmp.left)-altura(tmp.right)) == 2) {
                if (key < tmp.left.key) {
                    tmp = srl(tmp);
                }else{
                    tmp = drl(tmp);
                }
            }
        }else{
            tmp.right = add(key, capas, list, tmp.right);
            if ((altura(tmp.right)-altura(tmp.left)) == 2) {
                if (key > tmp.right.key) {
                    tmp = srr(tmp);
                }else{
                    tmp = drr(tmp);
                }
            }
        }
        int d, i, m;
        d = altura(tmp.right);
        i = altura(tmp.left);
        m = maxi(d, i);
        tmp.alt = m + 1;
        return tmp;
    }

    private int altura(Nodo tmp) {
        if (tmp == null) {
            return -1;
        }else{
            return tmp.alt;
        }
    }

    private int maxi(int val1, int val2){
        return ((val1 > val2) ? val1:val2);
    }

    private Nodo srl(Nodo t1) {
        Nodo t2;
        t2 = t1.left;
        t1.left = t2.right;
        t2.right = t1;
        t1.alt = maxi(altura(t1.left), altura(t1.right))+1;
        t2.alt = maxi(altura(t2.left), t1.alt)+1;
        return t2;
    }

    private Nodo srr(Nodo t1){
        Nodo t2;
        t2 = t1.right;
        t1.right = t2.left;
        t2.left = t1;
        t1.alt = maxi(altura(t1.left), altura(t1.right))+1;
        t2.alt = maxi(altura(t2.right), t1.alt)+1;
        return t2;
    }

    private Nodo drl(Nodo tmp){
        tmp.left = srr(tmp.left);
        return srl(tmp);
    }

    private Nodo drr(Nodo tmp){
        tmp.right = srl(tmp.right);
        return srr(tmp);
    }

/* RECORRIDOS EN EL ARBOL */
    // Recorrido postorder - left:right:rootNode
    private void postOrder(Nodo root, String valor) {
        if (root == null) {
            return;
        }
        postOrder(root.left, valor);
        postOrder(root.right, valor);
        valor += root.key + "-";
        // System.out.print(root.key + " ");
    }

    // Recorrido para generar el arbol en graphviz
    // Recorrido inOrder - left:rootNode:right
    private void inOrder_image(Nodo root) {
        if (root == null) {
            return;
        }
        if (root.left != null){resultado += "Imagen" + root.key + " -> " + "Imagen" + root.left.key+"\n";}
        inOrder_image(root.left);
        if (root.right != null){resultado += "Imagen" + root.key + " -> " + "Imagen" + root.right.key+"\n";}
        // System.out.print(root.key + " ");
        inOrder_image(root.right);
    }

    // RECORRIDO UNICAMENTE UTILIZADO PARA LLENAR LA TABLA DE JAVA QUE MOSTRARA LAS IMAGENES
    // CON MAYOR CANTIDAD DE CAPAS
    ArrayList<Nodo> imagenes = new ArrayList<Nodo>();
    private void recorrido_top(Nodo root) {
        if (root == null) {
            return;
        }
        recorrido_top(root.left);
        imagenes.add(root);
        // valor += root.key + "-";
        recorrido_top(root.right);
    }

    public ArrayList<Nodo> getTop() {
        imagenes = new ArrayList<Nodo>();
        recorrido_top(root);
        Collections.sort(imagenes);
        return imagenes;
    }

    private void inOrder(Nodo root, String valor) {
        if (root == null) {
            return;
        }
        inOrder(root.left, valor);
        valor += root.key + "-";
        inOrder(root.right, valor);
    }

    // Recorrido PreOrder - rootNode:left:right
    private void preOrder(Nodo root, String valor) {
        if (root == null) {
            return;
        }
        valor += root.key + "-";
        preOrder(root.left, valor);
        preOrder(root.right, valor);
    }

    // Metodos para hacer los recorridos sin enviar el nodo root
    public String postOrder_traversal() {
        String valor = "";
        postOrder(root, valor);
        return valor;
    }

    public void inOrder_traversal_image(String dpi) {
        resultado = "digraph G{\nlabelloc=\"t\";\nlabel=<<B>Arbol AVL</B>>;\nnodesep=0.4;\nranksep=0.5;\n";
        inOrder_image(root);
        resultado += "\n}";
        generar_graphviz(dpi);
    }
    
    public String inOrder_traversal() {
        String valor = "";
        inOrder(root, valor);
        return valor;
    }

    public String preOrder_traversal() {
        String valor = "";
        preOrder(root, valor);
        return valor;
    }
    
    public Nodo buscar_imagen(int id) {
        encontrado = null;
        imagenes(root, id);
        return encontrado;
    }

    // Generar imagenes que contiene el arbol
    Nodo encontrado = null;
    private void imagenes(Nodo root, int id) {
        if (root == null) {
            return;
        }
        if (id == root.key) {
            encontrado = root;
        }
        // root.capas.generateImage(root.key, arbol);
        imagenes(root.left, id);
        if (id == root.key) {
            encontrado = root;
        }
        // root.capas.gene(root.key, arbol);
        imagenes(root.right, id);
    }

    // public void generar_imagenes(int id) {
    //     imagenes(root, id);
    // }

    // Generacion del arbol utilizando graphviz
    public void generar_graphviz(String dpi) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Archivos_TXT\\ArbolAVL.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\AVL\\"+dpi+"ArbolAVL.png";
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