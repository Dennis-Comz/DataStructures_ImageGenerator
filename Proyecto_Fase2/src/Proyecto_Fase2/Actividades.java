package Proyecto_Fase2;

import Estructuras.*;

public class Actividades {
    public static Actividades objetos;
    // ArbolABB binary_tree;
    public static ArbolB arbol_b = new ArbolB();
    // ArbolAVL arbol_avl;

    public void initialize() {
        try {
            arbol_b = new ArbolB();
        } catch (Exception e) {}
    }

    public static Actividades getInstance() {
        if (objetos == null) {
            arbol_b = new ArbolB();
            objetos = new Actividades();
        }
        return objetos;
    }
}
