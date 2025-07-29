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
            idx++; // Move to next element in the array

            // If current value is -1, it means this node is null
            if (nodes[idx] == -1) return null;

            // 🌱 Create a node with the current value
            Node newNode = new Node(nodes[idx]);

            // Recursively build the left and right subtrees
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode; // Return constructed subtree to parent
        }

        // 🌐 Level-order traversal (line-by-line) using queue and null markers
        public static void levelOrder(Node root) {
            Queue<Node> q = new LinkedList<>();  // Queue for BFS
            q.add(root);                         // Add root node
            q.add(null);                         // ⛔ Add null marker to mark the end of the current level

            while (!q.isEmpty()) {
                Node currNode = q.remove();  // 🚶 Take the front node from queue

                if (currNode == null) {
                    // ⚠️ End of one level reached
                    System.out.println();  // Print newline to separate levels

                    if (q.isEmpty()) {
                        // ✅ No more nodes left in queue — traversal complete
                        break;
                    } else {
                        // 🟢 Add another null to mark the end of the next level
                        q.add(null);
                    }
                } else {
                    // 📌 Print the current node
                    System.out.print(currNode.data + " ");

                    // 👶 Add left child if it exists
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }

                    // 👶 Add right child if it exists
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 🌱 Preorder array representing tree nodes (-1 is null)
        int[] preorder = {
                1,
                2,
                4, -1, -1,
                5, -1, -1,
                3, -1,
                6, -1, -1
        };

        // 🛠️ Step 1: Build the binary tree from array
        Node root = BinaryTree.buildTree(preorder);

        // 🧪 Step 2: Print nodes level-by-level
        System.out.println("Level Order Traversal (line-by-line):");
        BinaryTree.levelOrder(root);
    }
}


 /*
🔍 Level by level traversal using Queue + null marker
======================================================

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