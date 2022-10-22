public class AVL {

    private Node root;

    public AVL() {

    }

    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int value, Node node) {

        if (node == null) {
            return new Node(value);
        }
        if ((value - node.getValue()) < 0) {
            node.setLeft(insert(value, node.getLeft()));
        } else if ((value - node.getValue()) > 0) {
            node.setRight(insert(value, node.getRight()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public void updateHeight(Node node) {
        int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(maxHeight + 1);
    }

    private int height(Node node) {

        if (node != null) {
            return node.getHeight();
        } else {
            return 0;
        }
    }

    private Node applyRotation(Node node) {
        int balance = balance(node);

        if (balance > 1) {

            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.getRight();
        Node rightLeft = right.getLeft();
        right.setLeft(node);
        node.setRight(rightLeft);
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.getLeft();
        Node leftRight = left.getRight();
        left.setRight(node);
        node.setLeft(leftRight);
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private int balance(Node node) {

        if (node != null) {
            return height(node.getLeft()) - height(node.getRight());
        } else {
            return 0;
        }
    }

    public void inorder() {

        inorder(root);

    }

    private void inorder(Node current) {

        if (current == null) {
            return;
        }
        inorder(current.getLeft());
        System.out.println(current.getValue());
        inorder(current.getRight());
    }

    public void delete(int value){
        root = delete(value, root);
    }

    private Node delete(int value, Node node) {

        if (node == null) {
            return null;
        }

        if ((value - node.getValue()) < 0) {
            node.setLeft(delete(value, node.getLeft()));
        } else if ((value - node.getValue()) > 0) {
            node.setRight(delete(value, node.getRight()));
        } else {

            //no tiene hijos o solo un hijo
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            //tiene dos hijos
            node.setValue(getMax(node.getLeft()));
            node.setLeft(delete(node.getValue(), node.getLeft()));

        }
        updateHeight(node);
        return applyRotation(node);
    }

    public int getMax(Node node){
        if(node.getRight() == null){
            return node.getValue();
        }
        return getMax(node.getRight());
    }
}
