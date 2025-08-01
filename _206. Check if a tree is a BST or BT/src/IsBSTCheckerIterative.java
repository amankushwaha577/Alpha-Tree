import java.util.*;

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ LOGIC: Iterative Check for BST using Level Order

→ Instead of recursion, use a queue to track:
    ⎯ Node
    ⎯ Its valid [min, max] range

→ Traverse each node and:
    ✅ Ensure node.data ∈ (min, max)
    ✅ Push left with updated max
    ✅ Push right with updated min

→ Any invalid node → return false
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/

public class IsBSTCheckerIterative {

    // 🌳 Node structure
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 📦 Node + valid range holder
    static class NodeWithRange {
        Node node;
        long min, max;

        NodeWithRange(Node n, long min, long max) {
            this.node = n;
            this.min = min;
            this.max = max;
        }
    }

    // 🔍 Iterative BST check
    public static boolean isBST(Node root) {
        if (root == null) return true;

        Queue<NodeWithRange> queue = new LinkedList<>();
        queue.offer(new NodeWithRange(root, Long.MIN_VALUE, Long.MAX_VALUE));

        while (!queue.isEmpty()) {
            NodeWithRange current = queue.poll();
            Node node = current.node;
            long min = current.min;
            long max = current.max;

            // ❌ Violation
            if (node.data <= min || node.data >= max) {
                return false;
            }

            // 👈 Left child: valid range = [min, node.data)
            if (node.left != null) {
                queue.offer(new NodeWithRange(node.left, min, node.data));
            }

            // 👉 Right child: valid range = (node.data, max]
            if (node.right != null) {
                queue.offer(new NodeWithRange(node.right, node.data, max));
            }
        }

        return true;  // ✅ All nodes valid
    }

    // 🔧 Sample Tree Builder
    /*
               13
              /  \
            10    15
           / \    / \
          7  12  14 17
               \      /
               9    16
              /
             8
    */
    public static Node buildTree() {
        Node root = new Node(13);
        root.left = new Node(10);
        root.right = new Node(15);
        root.left.left = new Node(7);
        root.left.right = new Node(12);
        root.left.left.right = new Node(9);
        root.left.left.right.left = new Node(8);
        root.right.left = new Node(14);
        root.right.right = new Node(17);
        root.right.right.left = new Node(16);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();
        System.out.println("Is BST? → " + isBST(root));  // ✅ true
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 BST Structure with [min, max] at each node:

                             13 [-∞, ∞]
                            /           \
              10 [-∞, 13]                  15 [13, ∞]
              /        \                     /       \
   7 [-∞,10]         12 [10,13]      14 [13,15]     17 [15, ∞]
        \                                              /
    9 [7,10]                                     16 [15,17]
     /
  8 [7,9]

✅ All nodes fall within their correct min-max ranges.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 */

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY ANALYSIS
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

✅ TIME COMPLEXITY: O(N)
- Every node is visited exactly once.
- For each node:
    → We check if its value lies within a valid range.
    → Then we enqueue left and right children (if any).
- So total operations are proportional to the number of nodes (N).

✅ SPACE COMPLEXITY: O(N)
- We use a queue to perform iterative level-order traversal.
- In the worst case (complete binary tree), the queue can hold up to N/2 nodes.
- Thus, space required for the queue is O(N) in the worst case.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 SUMMARY:

    Metric             | Complexity
    -------------------|------------
    ⏱️ Time            | O(N)
    💾 Space           | O(N)
    🚫 No recursion    | ✅
    🔁 Iterative       | ✅
    🌳 Handles Skewed  | ✅

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/
