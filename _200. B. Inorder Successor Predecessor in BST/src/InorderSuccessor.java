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
                successor = root; // we found one successor
                root = root.left; // but we can also find smaller successor one in left.
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
🔍 INORDER SUCCESSOR IN BST — SIMPLE EXPLANATION

👉 What is an Inorder Successor?
- It’s the node that comes **right after a given key** in the inorder traversal of a BST.
- In other words, it's the **smallest value that is greater than the key**.

🧠 How does BST help?
- In a Binary Search Tree (BST):
   - Left < Node < Right
   - So larger values are always on the **right** side.

📌 SIMPLE STRATEGY:
We want to find the smallest number that is still **greater** than the key.

1. Start from the root.
2. If key < root.data:
   ✅ This node could be the successor (it's greater than key).
   👉 But maybe there’s a smaller one on the left → So go left.
3. Else:
   ❌ This node is too small or equal → Go right to find something larger.

4. Keep track of the **best (smallest so far) valid successor**.
5. When you reach null, return the best candidate.

✅ This works because we always look for values **just bigger than the key**, and try to improve our answer as we go.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(H)
- H = height of the tree
- Balanced: O(log N)
- Skewed: O(N)

📦 Space: O(1)
- No recursion or stack — just moving through the tree

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

If key = 15 → successor = 17 (the number just after 15)

*/


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
