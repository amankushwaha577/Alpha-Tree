public class Main {

    // Node class to represent each tree node
    static class Node {
        int data;
        Node left, right;

        // Constructor to initialize node with data
        public Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        // Step 1: Create the root node
        Node root = new Node(1);

        /*
        Tree after Step 1:

            🔵1
           /   \
        ❌     ❌
        */

        // Step 2: Add left child to root
        root.left = new Node(2);

        /*
        Tree after Step 2:

            🔵1
           /   \
        🔵2     ❌
       /   \
    ❌     ❌
        */

        // Step 3: Add right child to root
        root.right = new Node(3);

        /*
        Tree after Step 3:

            🔵1
           /   \
        🔵2     🔵3
       /  \    /   \
    ❌   ❌  ❌   ❌
        */

        // Step 4: Add left child to node 3
        root.right.left = new Node(5);

        /*
        Final Tree after Step 4:

              🔵1
            /     \
         🔵2       🔵3
        /  \     /    \
      ❌  ❌   🔵5     ❌
               / \
             ❌   ❌
        */

        // Displaying the tree structure in text
        System.out.println("Root: " + root.data);
        System.out.println("Left Child of Root: " + root.left.data);
        System.out.println("Right Child of Root: " + root.right.data);
        System.out.println("Left Child of Right Node: " + root.right.left.data);
    }
}


/*
==========================
📘 1. FULL BINARY TREE
==========================
Definition:
- Every node has 0 or 2 children — never just 1 child.

✅ Example of Full Binary Tree:
          🔵1
         /   \
      🔵2     🔵3
     / \     / \
   🔵4 🔵5  🔵6 🔵7

❌ Not a Full Binary Tree:
          🔵1
         /   \
      🔵2     🔵3
     /        / \
   🔵4      🔵6 🔵7

Reason: 🔵2 has only one child 🔵4
*/


/*
==========================
📘 2. COMPLETE BINARY TREE
==========================
Definition:
- All levels are completely filled **except possibly the last**.
- Last level is filled **from left to right** (no gaps).

✅ Example of Complete Binary Tree:
          🔵1
         /   \
      🔵2     🔵3
     / \     /
   🔵4 🔵5 🔵6

❌ Not a Complete Binary Tree:
          🔵1
         /   \
      🔵2     🔵3
     /   \      \
   🔵4  🔵5     🔵7

Reason: Last level has a right node (🔵7) without filling left first.
*/


/*
==========================
📘 3. PERFECT BINARY TREE
==========================
Definition:
- All internal nodes have exactly 2 children.
- All leaf nodes are at the same level.

✅ Example of Perfect Binary Tree:
          🔵1
         /   \
      🔵2     🔵3
     / \     / \
   🔵4 🔵5  🔵6 🔵7

❌ Not a Perfect Binary Tree:
          🔵1
         /   \
      🔵2     🔵3
     /
   🔵4

Reason: Leaf nodes are at different levels and internal nodes don’t all have 2 children.
*/



/*
==========================
📘 4. BALANCED BINARY TREE
==========================

Definition:
A binary tree is considered BALANCED if:
  - For every node, the height of the left and right subtrees differs by **at most 1**
  - The height of the tree is **O(log₂(n))**, where `n` is the total number of nodes

In other words:
- No branch of the tree should be "too deep" compared to others.
- The tree shouldn't lean too much to one side.
- Ensures good performance for operations like insert/search/delete in O(log n) time.

💡 log₂(n) means: if there are n nodes, the height should be around log base 2 of n.
Example:
  If n = 7, then log₂(7) ≈ 2.8 → height should be about 3 (acceptable).

✅ Balanced Binary Tree Example:
          🔵1
         /   \
      🔵2     🔵3
     /         \
   🔵4         🔵5

Explanation:
- 🔵1: left height = 2, right height = 2 → ✅
- 🔵2 and 🔵3: both subtrees are balanced → ✅
- Total height = 3 → close to log₂(5) ≈ 2.3 → ✅

❌ Unbalanced Binary Tree Example:
          🔵1
         /
      🔵2
     /
   🔵3
  /
🔵4

Explanation:
- 🔵1's left height = 3, right height = 0 → difference = 3 → ❌
- Total height = 4, n = 4 → log₂(4) = 2 → but height = 4 → ❌ too deep
- This is a left-skewed tree → **not balanced**

📌 Summary:
Balanced Tree:
- Height ≈ log₂(n)
- Left and right subtree heights differ by ≤ 1 at every node

Unbalanced Tree:
- Height much greater than log₂(n)
- Subtree heights differ too much (leaning to one side)
*/


/*
==========================
📘 5. DEGENERATE TREE (Skewed Tree)
==========================

Definition:
- A **Degenerate Tree** is a binary tree where **each parent node has only one child**.
- It behaves like a linked list (either all to the left or all to the right).
- Worst-case scenario for binary trees.
- Height becomes **O(n)** instead of **O(log₂(n))**

👎 Problem:
- All operations (search, insert, delete) become slow: O(n) instead of O(log n)
- Completely defeats the purpose of using a binary tree

✅ Example: Left-Skewed Degenerate Tree

          🔵1
         /
       🔵2
       /
     🔵3
     /
   🔵4

Explanation:
- Every node has only a **left child**
- Height = 4, Nodes = 4 → log₂(4) = 2 → Height ≠ log₂(n) → ❌ Not balanced

✅ Example: Right-Skewed Degenerate Tree

          🔵1
             \
             🔵2
                \
                🔵3
                   \
                   🔵4

Explanation:
- Every node has only a **right child**
- Still height = 4, but log₂(4) = 2 → ❌ Not balanced

📌 Summary:
Degenerate Tree:
- Height = n
- log₂(n) ≪ n → violates balanced tree property
- Acts like a linked list → poor performance

Balanced Tree:
- Height ≈ log₂(n)
- Fast operations → O(log n)
*/
