import java.util.*;

public class BFS_Level_Order_Traversal {

    // 🌿 Node class representing each tree element
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        // 🏗️ Build tree from preorder array (-1 = null)
        public static Node buildTree(int[] nodes) {
            idx++;
            if (nodes[idx] == -1) return null;

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        // 🌐 Level-order traversal (your version): line-by-line using null marker
        public static void levelOrder(Node root) {
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null); // 🚧 End of level marker

            while (!q.isEmpty()) {
                Node currNode = q.remove();

                if (currNode == null) {
                    // ✅ End of one level
                    System.out.println();
                    if (q.isEmpty()) {
                        break; // 🚪 Exit if nothing more
                    } else {
                        q.add(null); // 🚧 Mark next level
                    }
                } else {
                    // 🖨️ Print current node
                    System.out.print(currNode.data + " ");

                    // 🔽 Add left child
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }

                    // 🔼 Add right child
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 🌱 Preorder array representing tree
        int[] preorder = {
                1,
                2,
                4, -1, -1,
                5, -1, -1,
                3, -1,
                6, -1, -1
        };

        // 🛠️ Build the binary tree
        Node root = BinaryTree.buildTree(preorder);

        // 🧪 Run level-order traversal (line-by-line)
        System.out.println("Level Order Traversal (line-by-line):");
        BinaryTree.levelOrder(root);
    }
}

 /*
        🔍 DRY RUN: Level Order Traversal with Queue

        Preorder array used to build tree:
        ➝ {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1}

        ✅ Built Tree:
                   🔵1
                 /     \
              🔵2       🔵3
             /   \         \
           🔵4   🔵5       🔵6

        👇 Dry Run using Queue + Null markers:

        Initial Queue: [1, null]

        1 → print(1) → add(2), add(3) → Queue: [2, 3, null]
        null → print new line

        2 → print(2) → add(4), add(5)
        3 → print(3) → add(6) → Queue: [4, 5, 6, null]
        null → print new line

        4 → print(4) → no children
        5 → print(5) → no children
        6 → print(6) → no children
        null → print new line → queue empty → done ✅

        🖨️ Output:
        1
        2 3
        4 5 6
        */