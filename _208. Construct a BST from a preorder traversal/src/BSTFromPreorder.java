public class BSTFromPreorder {

    // 🌳 Node class
    /*
    🔁 Traverse preorder array once, keeping track of [min, max] range:
    - Only insert elements that satisfy BST property
    - As we move, we update the range for left and right subtrees

    🔑 KEY: Preorder = [Root, Left, Right]
        So we build left subtree first with updated range,
        then right subtree with its new range.
      */
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔢 Global index to track preorder position
    static int index = 0;

    // 🔧 Build BST from preorder using [min, max] bounds
    public static Node buildBST(int[] preorder, int min, int max) {
        if (index >= preorder.length) return null;

        int val = preorder[index];

        // ❌ If val doesn't fit in current range, skip
        if (val < min || val > max) return null;

        // ✅ Create node and move index forward
        Node root = new Node(val);
        index++;

        // 🔽 Left subtree → [min, val)
        root.left = buildBST(preorder, min, val - 1);

        // 🔼 Right subtree → (val, max]
        root.right = buildBST(preorder, val + 1, max);

        return root;
    }

    // 🧾 Utility: Print inorder to verify correctness
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {10, 5, 1, 7, 40, 50};

        index = 0; // reset before use
        Node root = buildBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.print("Inorder of constructed BST: ");
        printInorder(root);  // Output should be sorted: 1 5 7 10 40 50
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📦 TIME: O(N)
- Each element is visited once

📦 SPACE:
- O(H) = recursion stack height
    - Balanced BST: O(log N)
    - Skewed BST: O(N)
- No extra structures used

🧠 More efficient than naive O(N^2) insert-based build
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/


/*
Step-by-step Build (using [min, max] bounds):

1. 10 fits in [-∞, ∞] → create root 10
    ├─ left subtree: bounds [-∞, 9]
    └─ right subtree: bounds [11, ∞]

2. 5 fits in [-∞, 9] → left of 10
    ├─ left: bounds [-∞, 4]
    └─ right: bounds [6, 9]

3. 1 fits in [-∞, 4] → left of 5
    → No left or right child

4. 7 fits in [6, 9] → right of 5
    → No left or right child

5. 40 fits in [11, ∞] → right of 10
    └─ right: bounds [41, ∞]

6. 50 fits in [41, ∞] → right of 40

Constructed BST:

              10
            /    \
          5       40
         / \         \
        1   7        50

 */