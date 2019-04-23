import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        boolean done = false;
        while (!done && in.hasNextLine()){
            String line = in.nextLine();
            Scanner ls = new Scanner(line);
            String token = ls.next();
            switch (token.toUpperCase()){
                case "INSERT":
                    while (ls.hasNext()){
                        tree.insert(ls.nextInt(), ls.next().charAt(0));
                    }
                    break;
                case "DELETE":
                    while (ls.hasNextInt()){
                        tree.delete(ls.nextInt());
                    }
                    break;
                case "PREORDER":
                    tree.preorder();
                    break;
                case "INORDER":
                    tree.inorder();
                    break;
                case "POSTORDER":
                    tree.postorder();
                    break;
                case "EXIT":
                    ls.close();
                    done = true;
                    break;
            }
        }
        in.close();
    }
}

class BinarySearchTree{
    class Node{
        private char data;
        private int key;
        Node left, right, p;

        Node(int k, char d){
            key = k;
            data = d;
        }
    }

    private Node root;

    void insert(int key, char data){
        this.root = insert(root, key, data);
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

    private Node delete(Node rt, int key){
        if (rt == null) return null;
        if (rt.key > key)
            rt.left = delete(rt.left, key);
        else if (rt.key < key)
            rt.right = delete(rt.right, key);
        else{ //found node to remove
            if (rt.left == null) return rt.right;
            else if (rt.right == null) return rt.left;
            //two children
            else {
                Node temp = getMax(rt.left);
                rt.key = temp.key;
                rt.left = deleteMax(rt.left);
            }
        }
        return rt;
    }

    private Node getMax(Node root){
        if (root.right == null) return root;
        return getMax(root.right);
    }

    private Node deleteMax(Node root){
        if (root.right == null) return root.left;
        root.right = deleteMax(root.right);
        return root;
    }

    //need to find z's node first
    void treeDelete(int key){
        Node z = treeSearch(root, key);
        if (z.left == null && z.right != null) transplant(z, z.right);
        else if (z.right == null && z.left != null) transplant(z, z.left);
        else{
            Node y = treeMinimum(z.right); // y is z's successor
            if (y.p != z){
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
        }
    }

    private Node treeMinimum(Node x){
        while (x.left != null) x = x.left;
        return x;
    }

    private Node treeSearch(Node root, int key){
        if (root == null || key == root.key) return root;
        if (key < root.key) return treeSearch(root.left, key);
        return treeSearch(root.right, key);
    }

    private void transplant(Node u, Node v){
        if (u.p == null) root = v;
        else if (u == u.p.left) u.p.left = v;
        else u.p.right = v;
        if (v != null) v.p = u.p;
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
        preorder(root);
        System.out.println();
    }

    private void preorder(Node root){
        if (root == null) return;
        System.out.print(root.data);
        preorder(root.left);
        preorder(root.right);
    }
}