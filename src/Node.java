public class Node {
    int key;
    Node left, right, parent;

    public Node(int item) {
        key = item;
        left = right = parent = null;
    }
}
