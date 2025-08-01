public class IsBSTChecker {

    // 🌳 Binary Tree Node
    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔍 Main function to check if tree is BST
    public static boolean isBST(Node root) {
        return isBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // ✅ Utility function using range limits [min, max]
    private static boolean isBSTUtil(Node node, long min, long max) {
        if (node == null) return true;

        // ❌ If current node breaks the min/max rule → not BST
        if (node.data <= min || node.data >= max)
            return false;

        // ✅ Recur for left and right subtrees with updated ranges
        return isBSTUtil(node.left, min, node.data) &&
                isBSTUtil(node.right, node.data, max);
    }

    // 🔧 Sample Tree Builder
    /*
               10
              /  \
             5    15
                 /  \
                12   20
    */
    public static Node buildTree() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(20);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();
        System.out.println("Is BST? → " + isBST(root));  // ✅ true
    }
}

/*
LOGIC:
- Every node in a BST must lie within a valid range:
    → For left child: [min, node.data)
    → For right child: (node.data, max)

- We check this recursively.
- If at any node, value doesn't lie in its allowed range → not a BST

EXAMPLE:
- Root 10 → allowed range = (-∞, ∞)
    - Left child 5 → allowed = (-∞, 10)
    - Right child 15 → allowed = (10, ∞)
        - 12 → (10, 15)
        - 20 → (15, ∞)
*/

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ DRY RUN: Check if the following tree is a BST
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Structure of the tree with allowed range at each node:
(Format: Node_Value [min, max])

                             13 [-∞, ∞]
                            /           \
              10 [-∞, 13]                  15 [13, ∞]
              /        \                     /       \
   7 [-∞,10]         12 [10,13]      14 [13,15]     17 [15, ∞]
        \                                              /
    9 [7,10]                                     16 [15,17]
     /
  8 [7,9]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔍 STEP-BY-STEP VERIFICATION

1️⃣ Root 13 is in [-∞, ∞] ✅

2️⃣ Left of 13 → 10 is in [-∞, 13] ✅
      └─ Left of 10 → 7 is in [-∞, 10] ✅
             └─ Right of 7 → 9 is in [7, 10] ✅
                   └─ Left of 9 → 8 is in [7, 9] ✅

      └─ Right of 10 → 12 is in [10, 13] ✅

3️⃣ Right of 13 → 15 is in [13, ∞] ✅
      └─ Left of 15 → 14 is in [13, 15] ✅
      └─ Right of 15 → 17 is in [15, ∞] ✅
             └─ Left of 17 → 16 is in [15, 17] ✅

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ All nodes satisfy their valid range.

👉 Final Verdict: ✅ This tree **is a valid BST**
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/
