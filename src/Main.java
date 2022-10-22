public class Main {

    public static void main(String[] args) {

        AVL avl = new AVL();

        avl.insert(1);
        avl.insert(3);
        avl.insert(4);
        avl.insert(6);
        avl.insert(7);
        avl.insert(10);
        avl.insert(7);
        avl.insert(0);
        avl.insert(2);
        avl.delete(6);
        avl.inorder();
    }
}
