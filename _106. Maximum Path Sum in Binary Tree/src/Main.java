public class Main {

    // 🌿 Node class representing each element in the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 🌟 Class to hold the result (global max)
    static class MaxPathSum {
        int max = Integer.MIN_VALUE;
    }

    // 🔍 Function to compute max path sum from a given root
    public static int findMaxPath(Node root, MaxPathSum result) {
        if (root == null) return 0;

        // 🔽 Recursively find max path sum on left and right subtrees
        int left = Math.max(0, findMaxPath(root.left, result));  // Ignore negative paths
        int right = Math.max(0, findMaxPath(root.right, result));

        // 🌈 Calculate the current path passing through this node
        int currentPath = left + right + root.data;

        // 💾 Update global max if current path is greater
        result.max = Math.max(result.max, currentPath);

        // ↩️ Return max sum of path including one child + current node
        return Math.max(left, right) + root.data;
    }

    // 🧮 Driver function to return max path sum
    public static int maxPathSum(Node root) {
        MaxPathSum result = new MaxPathSum();
        findMaxPath(root, result);
        return result.max;
    }

    // 🧪 Example usage
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        System.out.println("Maximum Path Sum: " + maxPathSum(root));  // ➤ Output: 17
    }
}


/*
🧠 LOGIC: Maximum Path Sum in Binary Tree (Optimal Approach)

📘 Problem:
- A **path** is any sequence of nodes connected by edges (must be continuous).
- It may start and end at any node.
- Each node can appear **only once**.
- Goal: Return the maximum **sum** of such a path.

✅ Allowed path types:
- Can go through root or NOT
- Can span across left → root → right
- Can just be left subtree, right subtree, or a single node

🛠️ Optimal Strategy:
1. Use postorder traversal (bottom-up)
2. At each node:
   - Compute max path from left child
   - Compute max path from right child
   - Ignore negative paths (use 0 if path < 0)
   - Local path sum = left + right + root.data
   - Update global max

3. Return to parent the max single-side path:
   ➤ Math.max(left, right) + root.data

---------------------------------------------------
📌 Time Complexity: O(n)
- Each node is visited exactly once

📌 Space Complexity:
- O(h) = height of the tree (recursion stack)
  ➤ Best case: O(log n)
  ➤ Worst case: O(n) (skewed tree)

---------------------------------------------------
🧪 DRY RUN:

Tree:
              🔵1
            /     \
         🔵2       🔵3
       /    \         \
    🔵4     🔵5       🔵6

Traversal from bottom:

🔸 At node 4:
- left = 0, right = 0
- path = 0 + 0 + 4 = 4
- return 4 to parent

🔸 At node 5:
- left = 0, right = 0
- path = 5
- return 5

🔸 At node 2:
- left = 4, right = 5
- path = 4 + 5 + 2 = 11
- update max = 11
- return 2 + max(4, 5) = 7

🔸 At node 6:
- return 6

🔸 At node 3:
- left = 0, right = 6
- path = 9
- return 9

🔸 At root 1:
- left = 7, right = 9
- path = 7 + 9 + 1 = 17 ✅
- final max = 17

🖨️ Output = 17
*/
