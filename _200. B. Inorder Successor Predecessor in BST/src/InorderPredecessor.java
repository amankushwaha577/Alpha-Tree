public class InorderPredecessor {

    // 🌳 BST Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔍 Function to find Inorder Predecessor
    public static Node findInorderPredecessor(Node root, int key) {
        Node predecessor = null;

        while (root != null) {
            if (key > root.data) {
                // ✅ Possible predecessor found
                predecessor = root;
                root = root.right;
            } else {
                // 🔽 Move left to find smaller value
                root = root.left;
            }
        }

        return predecessor;
    }

    // 🔧 Build example BST
    /*
                20
               /  \
              10   30
             / \     \
            5  15     35
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
        int key = 17;
        Node pred = findInorderPredecessor(root, key);

        if (pred != null)
            System.out.println("Inorder Predecessor of " + key + " is: " + pred.data);
        else
            System.out.println("No Inorder Predecessor exists for " + key);
    }
}
/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔍 INORDER PREDECESSOR IN BST — SIMPLE EXPLANATION

👉 What is an Inorder Predecessor?
- It's the node that comes **just before a given key** in the inorder traversal of a BST.
- In other words, it's the **largest value that is smaller than the key**.

🧠 How does BST help?
- In a BST:
   - Left child < Parent < Right child
   - So smaller values are always on the **left**.

📌 SIMPLE STRATEGY:
We want to find the biggest number that is still smaller than the key.

1. Start from the root.
2. If key > root.data:
   ✅ This node could be our answer (because it's smaller than the key).
   👉 But maybe there's an even closer one in the right subtree → So move right.
3. Else:
   ❌ This node is too big or equal → move left to find smaller values.

4. Keep track of the **last valid candidate** as we go.
5. When you reach null, that last candidate is the answer.

✅ This works because we keep moving **closer to the key from below**, storing the best so far.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(H)
- H = height of the tree
- Balanced tree: log N
- Skewed tree: up to N

📦 Space: O(1)
- No recursion, no extra data structures used

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🌳 Example:

      20
     /  \
   10   30
   / \     \
  5  15    35
       \
       17

Inorder: [5, 10, 15, 17, 20, 30, 35]

If key = 17 → predecessor = 15 (the number just before 17)

*/



/*
BST:
                20
               /  \
              10   30
             / \     \
            5  15     35
                 \
                 17

🧾 Inorder Traversal: [5, 10, 15, 17, 20, 30, 35]
So, predecessor of 17 = 15 ✅

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Dry Run (Iterative Search)

Initial:
- root = 20
- predecessor = null

───────────────────────────────────────
🔁 Step 1:
- key = 17 < root = 20
❌ Not a predecessor → go left
➡ root = 10

Tree:
                20
               /
             [10] ←➡

───────────────────────────────────────
🔁 Step 2:
- key = 17 > root = 10
✅ Predecessor = 10
➡ Move right → root = 15

Tree:
               10
                 \
                [15] ←➡

───────────────────────────────────────
🔁 Step 3:
- key = 17 > root = 15
✅ Better Predecessor = 15
➡ Move right → root = 17

Tree:
               15
                 \
                [17] ←➡

───────────────────────────────────────
🔁 Step 4:
- key = 17 == root = 17
➡ Move left → root = null

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🏁 End of loop → root == null

✅ Final Inorder Predecessor = 15

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 OUTPUT:
Inorder Predecessor of 17 is: 15

 */