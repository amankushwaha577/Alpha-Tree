public class Optimal {

    // 🌿 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static class BinaryTree {

        // 🌟 Main function to check if tree is balanced
        public static boolean isBalanced(Node root) {
            return checkHeight(root) != -1; // If height is -1, unbalanced
        }

        // 🔁 Helper: Returns height if balanced, else -1
        private static int checkHeight(Node root) {
            if (root == null) return 0; // ✅ Null node has height 0

            // 🌱 Check left subtree
            int leftHeight = checkHeight(root.left);
            if (leftHeight == -1) return -1; // ❌ Left subtree unbalanced

            // 🌳 Check right subtree
            int rightHeight = checkHeight(root.right);
            if (rightHeight == -1) return -1; // ❌ Right subtree unbalanced

            // 📏 Check current node balance
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1; // ❌ Unbalanced node
            }

            // ✅ Return current height
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 🧪 Test driver
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(5); // ⛔ Makes tree unbalanced

        System.out.println("Is Balanced? " + BinaryTree.isBalanced(root)); // false
    }
}


/*
🧠 LOGIC: Optimal Check for Balanced Binary Tree (O(n))

📘 A Binary Tree is balanced if:
   - At every node, height(left) - height(right) <= 1
   - AND all subtrees must also be balanced

🔁 Naive = Recompute height every time → O(n^2)
✅ Optimal = Check while computing height → O(n)

🧪 Strategy:
- Return height if subtree is balanced
- Return -1 immediately if any subtree is unbalanced

📦 Steps:
1. Postorder traversal: Left → Right → Node
2. For each node:
   - Check left subtree height
   - Check right subtree height
   - If either is -1, propagate -1 up (tree is unbalanced)
   - Else check if current node is balanced (diff ≤ 1)
   - If so, return height = 1 + max(left, right)
3. Final result: If root returns -1 → unbalanced else balanced ✅

📌 Time: O(n)
📌 Space: O(h) recursion stack

🧪 DRY RUN:

         1
        / \
       2   3
      /
     4
    /
   5

- Visit node 5: height = 1
- Node 4: left = 1, right = 0 → height = 2
- Node 2: left = 2, right = 0 → height = 3
- Node 1: left = 3, right = 1 → ❌ diff = 2 → return -1

✔️ Unbalanced
*/
