import java.util.*;

public class Main {

    // 🌿 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // 🔍 Function to check if a tree is symmetric
    public static boolean isSymmetric(Node root) {
        if (root == null) return true;  // Empty tree is symmetric
        return isMirror(root.left, root.right);  // Start recursive check
    }

    // 🔁 Helper function to check if two trees are mirror images
    public static boolean isMirror(Node t1, Node t2) {
        // 🔚 Base case: both are null
        if (t1 == null && t2 == null) return true;

        // ❌ One is null, the other isn't
        if (t1 == null || t2 == null) return false;

        // 🧠 Recursive check:
        // 1️⃣ Current values must match
        // 2️⃣ Left subtree of t1 vs Right subtree of t2
        // 3️⃣ Right subtree of t1 vs Left subtree of t2
        return (t1.data == t2.data)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        /*
                  1
                 / \
                2   2
               / \ / \
              3  4 4  3   ✅ Symmetric

               But if:
               right.right = 5 ❌ not symmetric
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(4);
        root.right.right = new Node(3);

        if (isSymmetric(root)) {
            System.out.println("✅ Tree is Symmetric");
        } else {
            System.out.println("❌ Tree is NOT Symmetric");
        }
    }
}


/*
🧪 DRY RUN: Check for Symmetric Tree

Tree:
              1
             / \
            2   2
           / \ / \
          3  4 4  3

Step-by-step recursion:

isMirror(2, 2)
  ↳ 2 == 2 ✅
  ↳ isMirror(3, 3)
        ↳ 3 == 3 ✅
        ↳ isMirror(null, null) ✅
        ↳ isMirror(null, null) ✅
  ↳ isMirror(4, 4)
        ↳ 4 == 4 ✅
        ↳ isMirror(null, null) ✅
        ↳ isMirror(null, null) ✅

✅ All recursive mirror checks return true → Tree is symmetric.

-------------------------------------------------------

🧠 SHORT NOTES:

📌 Symmetric Tree = Left and Right subtrees are mirror images.

🛠️ Logic:
- Base case: if both nodes are null → symmetric ✅
- If one is null and one is not → asymmetric ❌
- Check:
  1. t1.data == t2.data
  2. isMirror(t1.left, t2.right)
  3. isMirror(t1.right, t2.left)

🧪 Example:
Left:  [2 → 3, 4]
Right: [2 → 4, 3] ← mirror match ✅

🕒 Time Complexity: O(N) → visits all nodes
🧠 Space Complexity: O(H) → height of recursion stack (H = height of tree)

🎯 Tip to Remember:
Mirror symmetry is like folding the tree vertically and checking both sides match.
*/