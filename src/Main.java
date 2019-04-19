public class Main {

    public static void main(String[] args) {
	// input schtuff
    }
}

class BinarySearchTree{
    class Node{
        private char data;
        private int key;
        Node left, right;

        Node(int k, char d){
            key = k;
            data = d;
        }
    }

    Node root;

    void insert(int key, char data){
        root = insert(root, key, data);
    }

    private Node insert(Node root, int key, char data){
        if (root == null) return new Node(key, data);
        if (root.key - key >= 0)
            root.left = insert(root.left, key, data);
        else
            root.right = insert(root.right, key, data);
        return root;
    }

    void delete(int key){
        root = delete(root, key);
    }

    private Node delete(Node root, int key){
        if (root == null) return null;
        if (root.key - key > 0)
            root.left = remove(root.left, key);
        if (root.key - key < 0)
            root.right = remove(root.right, key);
        else{ //found node to remove
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            //two children
            root.key = getMax(root.left);
            root.left = delete(root.left, root.key);
        }
        return root;
    }

    private int getMax(Node root){
        if (root.right == null) return root.key;
        return getMax(root.right);
    }

    void inorder(){
        inorder(root);
        System.out.println();
    }

    private void inorder(Node root){
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);
    }

    void postorder(){
        postorder(root);
        System.out.println();
    }

    private void postorder(Node root){
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data);
    }

    void preorder(){
        System.out.println();
    }

    private void preorder(Node root){
        if (root == null) return;
        System.out.print(root.data);
        preorder(root.left);
        preorder(root.right);
    }
}