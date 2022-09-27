package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArbolABB {
    public class Nodo{
        public int key;
        public MatrizDispersa matriz;
        Nodo left, right;
        public Nodo(int key, MatrizDispersa matriz){
            this.key = key;
            this.matriz = matriz;
            left = right = null;
        }        
    }

    public int cantidad;
    Nodo root;
    String resultado = "";

    public ArbolABB() {
        resultado = "digraph G{\nlabelloc=\"t\";\nlabel=<<B>Arbol ABB</B>>;\nnodesep=0.4;\nranksep=0.5;\n";
        root = null;
    }

    // Insertar un nodo en el arbol
    public void insertar(int key, MatrizDispersa matriz) {
        cantidad++;
        root = insercion_recursiva(root, key, matriz);
    }

    private Nodo insercion_recursiva(Nodo root, int key, MatrizDispersa matriz) {
        if (root == null) {
            root = new Nodo(key, matriz);
            return root;
        }
        if (key < root.key) {
            root.left = insercion_recursiva(root.left, key, matriz);
        }else if(key > root.key){
            root.right = insercion_recursiva(root.right, key, matriz);
        }
        return root;
    }

    // Eliminar un nodo del arbol
    public void deleteNodo(int key) {
        root = delete_recursive(root, key);
    }

    private Nodo delete_recursive(Nodo root, int key) {
        // Si el arbol esta vacio
        if (root == null) {
            return root;
        }
        // Se recorre el arbol 
        if (key < root.key) { // Se recorre el subarbol del lado izquierdo
            root.left = delete_recursive(root.left, key);
        }else if(key > root.key){ // Se recorre el subarbol del lado derecho
            root.right = delete_recursive(root.right, key);
        }else{
            // Si el nodo contiene solo un hijo
            if (root.left == null) {
                return root.right;
            }else if(root.right == null){
                return root.left;
            }
            // Si el nodo tiene 2 hijos
            // Obtiene el sucesor en inorder (valor minuto el subarbol derecho)
            root.key = minValue(root.right);
            // Elimina el sucesor en inorder
            root.right = delete_recursive(root.right, root.key);
        }
        return root;
    }

    private int minValue(Nodo root) {
        int minVal = root.key;
        while (root.left != null) {
            minVal = root.left.key;
            root = root.left;
        }
        return minVal;
    }

    int contador = 0;
    int limite = 0;
    String recorrido_post = "";
    String recorrido_pre = "";
    String recorrido_in = "";
    /* RECORRIDOS EN EL ARBOL */
    // Recorrido postorder - left:right:rootNode
    private void postOrder_visualizar(Nodo root) {
        if (root == null) {
            return;
        }
        postOrder_visualizar(root.left);
        postOrder_visualizar(root.right);
        if (contador == (limite)) {
            return;
        }else{
            recorrido_post += root.key + "-";
            contador++;
        }
        // System.out.println(root.key + " ");
    }

    // Recorrido para generar el arbol en graphviz
    // Recorrido inOrder - left:rootNode:right
    private void inOrder_image(Nodo root) {
        if (root == null) {
            return;
        }
        if (root.left != null){resultado += "Capa" + root.key + " -> " + "Capa" + root.left.key+"\n";}
        inOrder_image(root.left);
        if (root.right != null){resultado += "Capa" + root.key + " -> " + "Capa" + root.right.key+"\n";}
        // System.out.println(root.key + " ");
        inOrder_image(root.right);
    }
    
    private void inOrder_visualizar(Nodo root) {
        if (root == null) {
            return;
        }
        inOrder_visualizar(root.left);
        if (contador == (limite)) {
            return;
        }else{
            recorrido_in += root.key + "-";
            contador++;
        }
        // System.out.println(root.key + " ");
        inOrder_visualizar(root.right);
    }

    // Recorrido PreOrder - rootNode:left:right
    private void preOrder_visualizar(Nodo root) {
        if (root == null) {
            return;
        }
        if (contador == (limite)) {
            return;
        }else{
            recorrido_pre += root.key + "-";
            contador++;
        }
        // System.out.println(root.key + " ");
        preOrder_visualizar(root.left);
        preOrder_visualizar(root.right);
    }

    // Metodos para hacer los recorridos sin enviar el nodo root
    public void inOrder_traversal_image(String user) {
        inOrder_image(root);
        resultado += "\n}";
        generar_graphviz(user);
    }
    
    public void inOrder_traversal_image_deImagen(String user, String num) {
        inOrder_image(root);
        resultado += "\n}";
        generar_graphviz_deImagen(user, num);
    }
    
    String capas = "";
    public String capas_hojas() {
        capas = "";
        capas_hojas_visualizar(root);
        return capas;
    }
    
    private void capas_hojas_visualizar(Nodo root) {
        if (root == null) {
            return;
        }
        capas_hojas_visualizar(root.left);
        if (root.left == null && root.right == null) {
            capas += root.key + " - ";
        }
        // System.out.println(root.key + " ");
        capas_hojas_visualizar(root.right);
    }

    public String postOrder_traversal(int val) {
        recorrido_post = "";
        contador = 0;
        limite = val;
        postOrder_visualizar(root);
        return recorrido_post;
    }
    
    public String inOrder_traversal(int val) {
        recorrido_in = "";
        contador = 0;
        limite = val;
        inOrder_visualizar(root);
        return recorrido_in;
    }

    public String preOrder_traversal(int val) {
        recorrido_pre = "";
        contador = 0;
        limite = val;
        preOrder_visualizar(root);
        return recorrido_pre;
    }

    // Generar todas las capas que contiene el arbol
    public void imagenes_traversal(String user, String lugar) {
        generar_imagenes(root, user, lugar);
    }

    private void generar_imagenes(Nodo root, String user, String lugar) {
        if (root == null) {
            return;
        }
        generar_imagenes(root.left, user, lugar);
        root.matriz.generar_graphviz(user+"Capa"+root.key+"Logico", lugar);
        generar_imagenes(root.right, user, lugar);
    }

    // Busqueda de si alguna capa existe en el arbol por medio del key de la capa
    public Nodo search(int key) {
        Nodo encontrado = busqueda_recursiva(root, key);
        if (encontrado != null) {
            return encontrado;
        }else{
            return encontrado;
        }
    }

    private Nodo busqueda_recursiva(Nodo root, int key) {
        // casos base: root es nulo o key esta en el root
        if (root == null || root.key == key) {
            return root;
        }
        // el valor es mayor a la llave
        if (root.key > key) {
            return busqueda_recursiva(root.left, key);
        }
        return busqueda_recursiva(root.right, key);
    }

    // Generacion del arbol utilizando graphviz
    public void generar_graphviz(String user) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Archivos_TXT\\"+user+"ArbolABB.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\ABB\\"+user+"ArbolABB.png";
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
    
    // Generacion del arbol utilizando graphviz
    public void generar_graphviz_deImagen(String user, String num_img) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Archivos_TXT\\"+user+"ArbolABB.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_2\\Graphviz\\Imagenes\\Arboles\\ABB_Imagenes\\"+user+"ArbolABB"+num_img+".png";
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