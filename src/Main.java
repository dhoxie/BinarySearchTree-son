import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BST bst = new BST();
        Scanner in = new Scanner(System.in);
        bst.console(in);
        in.close();
    }
}

class BST{

    public void console(Scanner in){
        boolean done = false;
        while (!done && in.hasNextLine()){
            String line = in.nextLine();
            Scanner ls = new Scanner(line);
            String token = ls.next();
            Node node;
            switch (token.toUpperCase()){
                case "INSERT":
                    while (ls.hasNext()){
                        node = new Node(ls.nextInt(), ls.next().charAt(0));
                        insert(node);
                    }
                    break;
                case "DELETE":
                    while (ls.hasNext()){
                        int key = ls.nextInt();
                        Node z = treeSearch(root, key);
                        if (z != nil) delete(z);
                    }
                    break;
                case "PREORDER":
                    preorder();
                    break;
                case "INORDER":
                    inorder();
                    break;
                case "POSTORDER":
                    postorder();
                    break;
                case "EXIT":
                    ls.close();
                    done = true;
                    break;
            }
        }
    }

    private class Node{
        char data;
        int key;
        Node l = nil, r = nil, p = nil;

        Node(int key){ this.key = key;}
        Node(int key, char data){
            this.key = key;
            this.data = data;
        }
    }//end Node class

    private final Node nil = new Node(-1);
    private Node root = nil;

    private void insert(Node z) {
        Node y = nil;
        Node x = root;
        while (x != nil){
            y = x;
            if (z.key < x.key) x = x.l;
            else x = x.r;
        }
        z.p = y;
        if (y == nil) root = z;
        else if (z.key < y.key) y.l = z;
        else y.r = z;
    }

    private void delete(Node z){
        if (z.l == nil) transplant(z, z.r);
        else if (z.r == nil) transplant(z, z.l);
        else{
            Node y = min(z.r);
            if (y.p != z){
                transplant(y, y.r);
                y.r = z.r;
                y.r.p = y;
            }
            transplant(z, y);
            y.l = z.l;
            y.l.p = y;
        }
    }

    private Node successor(Node x){
        if (x.r != nil) return min(x.r);
        Node y = x.p;
        while (y != nil && x == y.r){
            x = y;
            y = y.p;
        }
        return y;
    }

    private Node min(Node x){
        while (x.l != nil)
            x = x.l;
        return x;
    }

    private void transplant(Node u, Node v){
        if (u.p == nil) root = v;
        else if (u == u.p.l) u.p.l = v;
        else u.p.r = v;
        if (v != nil) v.p = u.p;
    }

    private Node treeSearch(Node x, int key){
        if (x == nil || key == x.key) return x;
        if (key < x.key) return treeSearch(x.l, key);
        return treeSearch(x.r, key);
    }

    private void inorder(){
        inorder(root);
        System.out.println();
    }

    private void inorder(Node rt){
        if (rt == nil) return;
        inorder(rt.l);
        System.out.print(rt.data);
        inorder(rt.r);
    }

    private void postorder(){
        postorder(root);
        System.out.println();
    }

    private void postorder(Node rt){
        if (rt == nil) return;
        postorder(rt.l);
        postorder(rt.r);
        System.out.print(rt.data);
    }

    private void preorder(){
        preorder(root);
        System.out.println();
    }

    private void preorder(Node rt){
        if (rt == nil) return;
        System.out.print(rt.data);
        preorder(rt.l);
        preorder(rt.r);
    }

}
