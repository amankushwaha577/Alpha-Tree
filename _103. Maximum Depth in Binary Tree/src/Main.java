public class Main {

    // 🌿 Node class representing each element in the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // 📏 Function to calculate the maximum depth (height) of the tree
    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;  // 🛑 Base Case: Empty subtree has depth 0
        }

        // 🔽 Recursively calculate depth of left subtree
        int leftDepth = maxDepth(root.left);

        // 🔼 Recursively calculate depth of right subtree
        int rightDepth = maxDepth(root.right);

        // 🧮 Max of left & right depth + 1 for current node
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        /*
                 1
               /   \
              2     3
             / \     \
            4   5     6
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        int depth = maxDepth(root);
        System.out.println("Maximum Depth of Binary Tree: " + depth); // ➡️ Output: 3
    }
}

/*

🧠 LOGIC: Maximum Depth / Height of Binary Tree (Recursive)

📘 What is Maximum Depth?
→ Maximum number of nodes along the path from root node to the farthest leaf node.

🔍 Strategy:
- Use postorder traversal (left → right → root).
- For each node:
   - Recursively get max depth of left and right subtrees.
   - Return 1 + max(leftDepth, rightDepth)

🔁 Time Complexity: O(n)
🗃️ Space Complexity: O(h), where h is tree height (recursion stack)

🌳 Tree:
         1
       /   \
      2     3
     / \     \
    4   5     6

🧪 DRY RUN:

maxDepth(1)
    → left = maxDepth(2)
        → left = maxDepth(4) → 0 + 0 + 1 = 1
        → right = maxDepth(5) → 0 + 0 + 1 = 1
        → return 1 + max(1, 1) = 2
    → right = maxDepth(3)
        → left = null → 0
        → right = maxDepth(6) → 0 + 0 + 1 = 1
        → return 1 + max(0, 1) = 2
    → return 1 + max(2, 2) = 3 ✅

Final Answer: 3

*/
