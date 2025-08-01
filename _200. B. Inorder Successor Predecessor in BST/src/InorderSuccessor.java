public class InorderSuccessor {

    // 🌳 BST Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔍 Function to find Inorder Successor
    public static Node findInorderSuccessor(Node root, int key) {
        Node successor = null;

        while (root != null) {
            if (key < root.data) {
                // ✅ Possible successor found
                successor = root;
                root = root.left;
            } else {
                // 🔽 Move right to find larger value
                root = root.right;
            }
        }

        return successor;
    }

    // 🔧 Build example BST
    /*
                20
               /  \
              10   30
             / \    \
            5  15    35
                 \
                 17
    */
    public static Node buildTestTree() {
        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(15);
        root.left.right.right = new Node(17);
        root.right.right = new Node(35);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTestTree();
        int key = 15;
        Node succ = findInorderSuccessor(root, key);

        if (succ != null)
            System.out.println("Inorder Successor of " + key + " is: " + succ.data);
        else
            System.out.println("No Inorder Successor exists for " + key);
    }
}


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔍 GOAL: Find the inorder successor of key = 15

BST:
                20
               /  \
              10   30
             / \     \
            5  15     35
                 \
                 17

🧾 Inorder Traversal: [5, 10, 15, 17, 20, 30, 35]
So, successor of 15 = 17 ✅

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN (Iterative Search)

Initial:
- root = 20
- successor = null

───────────────────────────────────────
🔁 Step 1:
- key = 15 < root.data = 20
✅ Successor candidate = 20
➡ Move left → root = 10

Tree:
                20 ←✅
               /
            [10]
            / \
           5  15

───────────────────────────────────────
🔁 Step 2:
- key = 15 > root.data = 10
❌ Not a successor
➡ Move right → root = 15

Tree:
                20
               /
             10 →➡
                 \
                [15]

───────────────────────────────────────
🔁 Step 3:
- key = 15 == root.data = 15
➡ Move right → root = 17

Tree:
                 15 →➡
                   \
                   [17]

───────────────────────────────────────
🔁 Step 4:
- key = 15 < root.data = 17
✅ Better successor = 17
➡ Move left → root = null

Tree:
                 15
                   \
                 17 ←✅

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🏁 End of loop → root == null

✅ Final Inorder Successor = 17

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 OUTPUT:
Inorder Successor of 15 is: 17

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

Time: O(H), where H = height of tree
 - Balanced BST: O(log N)
 - Skewed BST: O(N)

Space: O(1)
 - No extra space used
*/

 /*
    🔍 LOGIC: Inorder Successor in BST

    👉 Inorder Successor of a node is the node with the **smallest value greater than the given key**.
    👉 In a BST, all values in the right subtree are greater, and in the left are smaller.

    📌 STRATEGY:
    - Traverse from root down to the key.
    - If key < root.data:
        🔸 Current node could be successor, move left to find smaller successor.
    - Else:
        🔸 Move right to search for a larger value.

    🧠 WHY IT WORKS:
    - We're always moving toward potential successors (larger values),
      while also keeping track of the *smallest such candidate* we've seen.
    - The first node where we go left is a valid candidate for successor.

    📦 TIME COMPLEXITY: O(H), H = height of BST
    📦 SPACE: O(1) — no recursion or extra data structures used
    */