public class Main {

    // 🌳 Binary Tree Node class
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    // ⬅️ Function to calculate depth of leftmost path from current node
    static int leftDepth(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;              // Increase depth as we go down
            node = node.left;     // Move to the left child
        }
        return depth;             // Return how deep we went
    }

    // ➡️ Function to calculate depth of rightmost path from current node
    static int rightDepth(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;              // Increase depth as we go down
            node = node.right;    // Move to the right child
        }
        return depth;             // Return how deep we went
    }

    // 🔁 Function to count nodes in complete binary tree
    static int countNodes(Node root) {
        if (root == null) return 0; // If tree is empty, return 0

        // 🔍 Get depth of leftmost and rightmost paths
        int left = leftDepth(root);
        int right = rightDepth(root);

        // ✅ If both depths are same, it's a perfect binary tree
        if (left == right) {
            // Total nodes in a perfect tree = 2^depth - 1
            return (1 << left) - 1;  // Same as Math.pow(2, left) - 1
        }

        // ⚠️ If not perfect, count nodes recursively in left and right
        return 1 + countNodes(root.left) + countNodes(root.right);
        // 1 for current node + left subtree nodes + right subtree nodes
    }

    // 🧪 MAIN FUNCTION TO RUN THE CODE
    public static void main(String[] args) {
        /*
                 1
               /   \
              2     3
             / \   /
            4   5 6
         */

        // 🧱 Create tree nodes
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);

        // 🎯 Count total nodes and print
        System.out.println("Total Nodes: " + countNodes(root)); // Output: 6
    }
}

/*
🧠 DRY RUN: Count Total Nodes in a Complete Binary Tree

Tree structure:
           1
         /   \
        2     3
       / \   /
      4   5 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ Step 1: At Node(1)
→ leftHeight(1) = 3 [path: 1 → 2 → 4]
→ rightHeight(1) = 2 [path: 1 → 3 → 6]
→ lh ≠ rh ⇒ Not a perfect tree ⇒ Recur on left and right

countNodes(1) = 1 + countNodes(2) + countNodes(3)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ Step 2: At Node(2)
→ leftHeight(2) = 2 [2 → 4]
→ rightHeight(2) = 2 [2 → 5]
→ lh == rh ⇒ Subtree rooted at 2 is perfect
→ Total nodes = 2^2 - 1 = 3 ✅
Return 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ Step 3: At Node(3)
→ leftHeight(3) = 2 [3 → 6]
→ rightHeight(3) = 1 [3 → null]
→ lh ≠ rh ⇒ Not perfect ⇒ Recur

countNodes(3) = 1 + countNodes(6) + countNodes(null)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ Step 4: At Node(6)
→ leftHeight(6) = 1
→ rightHeight(6) = 1
→ lh == rh ⇒ Perfect subtree ⇒ 2^1 - 1 = 1 ✅
Return 1

⏱️ Step 5: countNodes(null) = 0

→ countNodes(3) = 1 + 1 + 0 = 2

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Final Calculation:
countNodes(1) = 1 + 3 + 2 = 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📈 Time Complexity:
→ O(log² N)
Why? For each node we compute left and right height (O(log N)), and we do this up to log N times in a balanced tree.

📦 Space Complexity:
→ O(log N) recursion stack (height of tree)
*/


/*
Tree structure:
           1
         /   \
        2     3
       / \   /
      4   5 6

🔎 Why is leftHeight(1) = 3 and rightHeight(1) = 2?

We calculate height by going as far as possible:
- leftHeight → keep going left
- rightHeight → keep going right

━━━━━━━━━━━━━━━━━━━━━━━
🔍 leftHeight(1):
1 → 2 → 4 → null
✅ So, height = 3

🔍 rightHeight(1):
1 → 3 → 6 → null
✅ So, height = 2

━━━━━━━━━━━━━━━━━━━━━━━
🤔 Why different?

Because the tree is a **Complete Binary Tree**, not a **Perfect** one.

A **Perfect Binary Tree** has all levels fully filled — leftHeight == rightHeight.

But in a **Complete Binary Tree**, the last level may not be full,
and all nodes are filled from **left to right**.

In our case:
- Left subtree is fully filled
- Right subtree is missing a node (right child of node 3)

So, leftHeight ≠ rightHeight ⇒ we use recursive formula to count nodes:
→ totalNodes = 1 + count(root.left) + count(root.right)

━━━━━━━━━━━━━━━━━━━━━━━
✅ That's why:
leftHeight(1) = 3
rightHeight(1) = 2
→ So we go recursive instead of using formula for perfect tree.
*/
