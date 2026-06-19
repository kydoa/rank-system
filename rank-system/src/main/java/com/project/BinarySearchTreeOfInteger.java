package com.project;

public class BinarySearchTreeOfInteger {

    private static final class Node {
        public Node father;
        public Node left;
        public Node right;
        public Integer element;

        public Node(Integer element) {
            this.father = null;
            this.left = null;
            this.right = null;
            this.element = element;
        }
    }

    // Attributes
    private int count; // count of nodes
    private Node root; // reference to the root node

    public BinarySearchTreeOfInteger() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException("Tree is empty");
        }
        return root.element;
    }

    // --- CORE OPERATIONS: ADD & REMOVE ---

    public void add(Integer element) {
        root = add(root, element, null);
        count++;
    }

    private Node add(Node n, Integer e, Node father) {
        if (n == null) { // insertion point found
            Node aux = new Node(e);
            aux.father = father;
            return aux;
        }
        // Recursive insertion logic
        if (e.compareTo(n.element) > 0) {
            n.right = add(n.right, e, n);
        } else {
            n.left = add(n.left, e, n);
        }
        return n;
    }

    public boolean remove(Integer element) {
        if (element == null || root == null) return false;

        Node aux = searchNodeRef(element, root);
        if (aux == null) return false;

        remove(aux);
        count--;
        return true;
    }

    private void remove(Node n) {
        Node father = n.father;
        if (n.left == null && n.right == null) { // Case 1: Leaf node
            if (root == n) {
                root = null;
                return;
            }
            if (father.left == n) father.left = null;
            else father.right = null;
        } else if (n.left == null && n.right != null) { // Case 2: Only right child
            if (root == n) {
                root = n.right;
                root.father = null;
                return;
            }
            if (father.left == n) father.left = n.right;
            else father.right = n.right;
            n.right.father = father;
        } else if (n.left != null && n.right == null) { // Case 3: Only left child
            if (root == n) {
                root = n.left;
                root.father = null;
                return;
            }
            if (father.left == n) father.left = n.left;
            else father.right = n.left;
            n.left.father = father;
        } else { // Case 4: Two children
            Node menor = smallest(n.right);
            n.element = menor.element;
            remove(menor);
        }
    }

    // --- VISUALIZATIONS (TRAVERSALS) ---

    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            res.add(n.element); // Visit node
            positionsPreAux(n.left, res); // Left subtree
            positionsPreAux(n.right, res); // Right subtree
        }
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPosAux(n.left, res);
            positionsPosAux(n.right, res);
            res.add(n.element); // Visit node
        }
    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsCentralAux(n.left, res);
            res.add(n.element); // Visit node
            positionsCentralAux(n.right, res);
        }
    }

    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        LinkedListOfInteger res = new LinkedListOfInteger();
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                Node atual = fila.dequeue();
                res.add(atual.element);
                if (atual.left != null) fila.enqueue(atual.left);
                if (atual.right != null) fila.enqueue(atual.right);
            }
        }
        return res;
    }

    // --- QUICK QUERIES & UTILITIES ---

    public boolean contains(Integer element) {
        return searchNodeRef(element, root) != null;
    }

    private Node searchNodeRef(Integer element, Node n) {
        if (element == null || n == null) return null;
        int c = element.compareTo(n.element);
        if (c == 0) return n;
        return (c > 0) ? searchNodeRef(element, n.right) : searchNodeRef(element, n.left);
    }

    public Integer getSmallest() {
        Node n = smallest(root);
        return (n == null) ? null : n.element;
    }

    private Node smallest(Node n) {
        if (n == null) return null;
        while (n.left != null) n = n.left;
        return n;
    }

    public Integer getRight(Integer element) {
        Node aux = searchNodeRef(element, root);
        return (aux != null && aux.right != null) ? aux.right.element : null;
    }

    public Integer getLeft(Integer element) {
        Node aux = searchNodeRef(element, root);
        return (aux != null && aux.left != null) ? aux.left.element : null;
    }

    public Integer getParent(Integer element) {
        Node aux = searchNodeRef(element, root);
        return (aux != null && aux.father != null) ? aux.father.element : null;
    }

    public boolean isExternal(int element) {
        Node aux = searchNodeRef(element, root);
        return aux != null && aux.left == null && aux.right == null;
    }

    public boolean isInternal(int element) {
        Node aux = searchNode(element); // helper logic from snippet
        if (aux == null) return false;
        return aux.left != null || aux.right != null;
    }

    // Helper for internal check to avoid duplication
    private Node searchNode(Integer element) {
        return searchNodeRef(element, root);
    }

    public int level(Integer element) {
        if (!contains(element)) return -1;
        Node aux = searchNodeRef(element, root);
        int level = 0;
        while (aux.father != null) {
            aux = aux.father;
            level++;
        }
        return level;
    }

    // --- DOT GENERATION (GraphViz) ---

    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n");
        GeraNodosDOT(root);
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) return;
        GeraNodosDOT(nodo.left);
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) return;
        GeraConexoesDOT(nodo.left);
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\"");
        }
        GeraConexoesDOT(nodo.right);
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\"");
        }
    }

    // --- NON-RECURSIVE IMPLEMENTATIONS ---

    public void addNoRec(Integer e) {
        Node n = new Node(e);
        count++;
        if (root == null) {
            root = n;
            return;
        }
        Node aux = root;
        while (true) {
            if (e.compareTo(aux.element) > 0) {
                if (aux.right == null) {
                    aux.right = n;
                    n.father = aux;
                    break;
                }
                aux = aux.right;
            } else {
                if (aux.left == null) {
                    aux.left = n;
                    n.father = aux;
                    break;
                }
                aux = aux.left;
            }
        }
    }

    public Node searchNodeRefNoRec(Integer element, Node n) {
        if (element == null || n == null) return null;
        Node aux = n;
        while (aux != null && !element.equals(aux.element)) {
            aux = (element.compareTo(aux.element) > 0) ? aux.right : aux.left;
        }
        return aux;
    }
}