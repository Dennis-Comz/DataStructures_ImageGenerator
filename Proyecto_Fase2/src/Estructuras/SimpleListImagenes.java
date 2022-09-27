package Estructuras;

public class SimpleListImagenes {
    public class Nodo{
        int num;
        Nodo next;
        public Nodo(int num){
            this.num = num;
            this.next = null;
        }
    }

    public Nodo head;

    public void append(int num){
        Nodo newNode = new Nodo(num);
        if (head == null) {
            head = newNode;
        }else{
            Nodo last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
    }
}
