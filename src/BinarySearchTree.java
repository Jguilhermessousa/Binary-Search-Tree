public class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        Node z = new Node(key);
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (z.key < x.key)
                x = x.left;
            else
                x = x.right;
        }

        z.parent = y;
        if (y == null)
            root = z;
        else if (z.key < y.key)
            y.left = z;
        else
            y.right = z;
    }

    Node recursiveTreeSearch (Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root;

        // Key is smaller than root's key
        if (key < root.key)
            return recursiveTreeSearch(root.left, key);

        // Key is larger than root's key
        return recursiveTreeSearch(root.right, key);
    }

    Node iterativeTreeSearch (Node root, int key) {
        // Base Cases: root is null or key is present at root
        while (root != null && root.key != key) {
            // Key is smaller than root's key
            if (key < root.key) {
                root = root.left;
            } else {
                // Key is larger than root's key
                root = root.right;
            }
        }
        return root;
    }

    void inorder() {
        inorderTreeWalk(root);
    }

    void inorderTreeWalk(Node x) {
        if (x != null) {
            inorderTreeWalk(x.left);
            System.out.print(x.key + " ");
            inorderTreeWalk(x.right);
        }
    }

    void preorder() {
        preorderTreeWalk(root);
    }

    void preorderTreeWalk(Node x) {
        if (x != null) {
            System.out.println(x.key + " ");
            preorderTreeWalk(x.left);
            preorderTreeWalk(x.right);
        }
    }

    void postorder() {
        postorderTreeWalk(root);
    }

    void  postorderTreeWalk(Node x) {
        if (x != null) {
            postorderTreeWalk(x.left);
            postorderTreeWalk(x.right);
            System.out.println(x.key + " ");
        }
    }

    Node delete(int key) {
        Node nodeToDelete = recursiveTreeSearch(root, key);
        if (nodeToDelete != null) {
            deleteNode(this, nodeToDelete);
        }
        return nodeToDelete;
    }

    Node successor(Node x) {
        if (x.right != null) {
            return minimum(x.right);
        }

        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return  y;
    }

    Node predecessor(Node x) {
        if (x.left != null) {
            return maximum(x.left);
        }

        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return  y;
    }

    Node maximum() {
        return maximum(root);
    }

    Node maximum(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    Node minimum() {
        return minimum(root);
    }

    Node minimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    void shiftNodes(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        if (v != null) {
            v.parent = u.parent;
        }
    }

    void deleteNode(BinarySearchTree bst, Node z) {
        Node x, y;

        if (z.left == null || z.right == null) {
            y = z;
        } else {
            y = successor(z);
        }

        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }

        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null) {
            bst.root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else  {
                y.parent.right = x;
            }
        }

        if (y != z) {
            z.key = y.key;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(10);
        tree.insert(5);
        tree.insert(1);
        tree.insert(2);
        tree.insert(7);
        tree.insert(20);
        tree.insert(33);
        tree.insert(30);

        System.out.println("Inorder traversal:");
        tree.inorder();

        System.out.println("\nPreorder traversal:");
        tree.preorder();

        System.out.println("\nPostorder traversal:");
        tree.postorder();

        int keyToDelete = 33;
        Node deletedNode = tree.delete(keyToDelete);

        if (deletedNode != null) {
            System.out.println("\nDeleted node with key " + keyToDelete);
        } else {
            System.out.println("\nNode with key " + keyToDelete + " not found in the tree");
        }

        System.out.println("Inorder transversal after deletion:");
        tree.inorder();

        System.out.println("\nPreorder traversal after deletion:");
        tree.preorder();

        System.out.println("\nPostorder traversal after deletion:");
        tree.postorder();

        int keyToFindSuccessor = 20;
        Node nodeToFindSuccessor = tree.recursiveTreeSearch(tree.root, keyToFindSuccessor);

        if (nodeToFindSuccessor != null) {
            Node successorNode = tree.successor(nodeToFindSuccessor);

            if (successorNode != null) {
                System.out.println("\nSuccessor of key " + keyToFindSuccessor + ": " + successorNode.key);
            } else {
                System.out.println("\nNo successor found for key " + keyToFindSuccessor);
            }
        } else {
            System.out.println("\nNode with key " + keyToFindSuccessor + " not found in the tree");
        }

        int keyToFindPredecessor = 33;
        Node nodeToFindPredecessor = tree.recursiveTreeSearch(tree.root, keyToFindPredecessor);

        if (nodeToFindPredecessor != null) {
            Node predecessorNode = tree.predecessor(nodeToFindPredecessor);

            if (predecessorNode != null) {
                System.out.println("\nPredecessor of key " + keyToFindPredecessor + ": " + predecessorNode.key);
            } else {
                System.out.println("\nNo predecessor found for key " + keyToFindPredecessor);
            }
        } else {
            System.out.println("\nNode with key " + keyToFindPredecessor + " not found in the tree");
        }

        Node maxNode = tree.maximum();
        System.out.println("\nMaximum key in the tree: " + maxNode.key);

        Node minNode = tree.minimum();
        System.out.println("\nMinimum key in the tree: " + minNode.key);

        // Example usage of recursive search
        int keyToSearch = 60;
        Node result = tree.recursiveTreeSearch(tree.root, keyToSearch);
        if (result != null) {
            System.out.println("\nKey " + keyToSearch + " found in the tree.");
        } else {
            System.out.println("\nKey " + keyToSearch + " not found in the tree.");
        }

        // Example usage of iterative search
        int key = 50;
        Node res = tree.iterativeTreeSearch(tree.root, key);
        if (res != null) {
            System.out.println("\nKey " + key + " found in the tree.");
        } else {
            System.out.println("\nKey " + key + " not found in the tree.");
        }
    }
}
