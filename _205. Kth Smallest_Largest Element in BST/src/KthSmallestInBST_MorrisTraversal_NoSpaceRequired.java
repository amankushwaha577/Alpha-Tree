public class KthSmallestInBST_MorrisTraversal_NoSpaceRequired {
        // 🌳 Tree Node definition
        static class Node {
            int data;
            Node left, right;

            Node(int val) {
                this.data = val;
                this.left = this.right = null;
            }
        }

        // 🔍 Morris Traversal to find Kth smallest
        public static int kthSmallest(Node root, int k) {
            int count = 0;
            Node curr = root;

        /*
        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        📘 DRY RUN: Find 3rd smallest in the following BST

                          5
                        /   \
                      3       7
                     / \     / \
                    2   4   6   8

        ➤ Inorder sequence: [2, 3, 4, 5, 6, 7, 8]
        ➤ k = 3 → We want the 3rd node in this sequence = 4

        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        */

            while (curr != null) {
                if (curr.left == null) {
                /*
                ────────────────
                Case 1: No left child
                - Visit node
                - Go right
                ────────────────
                */
                    count++;
                    if (count == k) return curr.data;
                    curr = curr.right;

                } else {
                /*
                ────────────────
                Case 2: Has left child
                - Find inorder predecessor (rightmost node in left subtree)
                ────────────────
                */

                    Node pred = curr.left;
                    // Find rightmost node in left subtree (inorder predecessor)
                    while (pred.right != null && pred.right != curr) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                    /*
                    🧵 Create temporary thread (pred.right = curr)
                    🔁 Go left

                    Example at curr = 5:
                        5
                       /
                     3
                    / \
                   2   4

                    → pred = 4
                    → pred.right = 5 (temporary link)
                    */
                        pred.right = curr;
                        curr = curr.left;

                    } else {
                    /*
                    🔁 Thread already exists → We've returned from left subtree
                    ✅ Visit current node
                    🧹 Remove thread
                    ➡ Go right

                    Example at curr = 3 (after visiting left 2):
                        Thread: 2.right → 3

                        Now break 2.right
                        Visit 3 (count++)
                        Go to right (4)
                    */
                        pred.right = null;  // 🧹 remove thread
                        count++;
                        if (count == k) return curr.data;
                        curr = curr.right;
                    }
                }
            }

            return -1; // k > number of nodes
        }

        // 🔧 Build example BST
        public static Node buildTestTree() {
            Node root = new Node(5);
            root.left = new Node(3);
            root.right = new Node(7);
            root.left.left = new Node(2);
            root.left.right = new Node(4);
            root.right.left = new Node(6);
            root.right.right = new Node(8);
            return root;
        }

        public static void main(String[] args) {
            Node root = buildTestTree();
            int k = 3;
            int ans = kthSmallest(root, k);
            System.out.println("Kth Smallest Element = " + ans);
        }
    }


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📘 DRY RUN — Iteration Breakdown for k = 3
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🎯 GOAL: Find 3rd smallest in Inorder sequence [2, 3, 4, 5, 6, 7, 8]

────────────────────────────
Iteration 1: curr = 5
→ 5 has left child → find pred (rightmost of 3)

→ pred = 4 (rightmost of 3)
→ 4.right is null → set 4.right = 5 (temporary link)
→ Move curr = 3

Tree (with thread):
           5
         /   \
        3     7
       / \    / \
      2  4    6  8
           ↖
             5

────────────────────────────
Iteration 2: curr = 3
→ 3 has left child → pred = 2

→ 2.right is null → set 2.right = 3 (thread)
→ Move curr = 2

Tree:
      3
     / \
    2   4
     ↘
      3

────────────────────────────
Iteration 3: curr = 2
→ 2.left == null
→ ✅ Visit 2 → count = 1
→ Move curr = 2.right = 3 (threaded)

────────────────────────────
Iteration 4: curr = 3 (coming from thread)
→ pred = 2 again
→ 2.right == 3 → remove thread
→ ✅ Visit 3 → count = 2
→ Move curr = 4

────────────────────────────
Iteration 5: curr = 4
→ 4.left == null
→ ✅ Visit 4 → count = 3 🎯 FOUND

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ OUTPUT: Kth Smallest Element = 4
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 Final Visited Order (as per Morris): 2 → 3 → 4 → ...
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

⏱ TIME: O(N)
📦 SPACE: O(1) (No recursion or stack)
🎯 Tree structure restored after use

*/

/*
🚀 Morris Traversal (Inorder):
        - Traverse BST without recursion or stack
        - Use "threads" (temporary right links) to backtrack

        📌 How it works:
        1. If node has no left child → visit and move right
        2. If node has left child → find predecessor
        a. If predecessor’s right is null → create thread to current, move left
        b. If predecessor’s right is current → break thread, visit current, move right

        🟢 When we visit a node (after fully processing left subtree), we increment count
        🎯 When count == k, we've found the kth smallest
*/

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 INORDER PREDECESSOR: DEFINITION

🔹 In a BST, the **inorder predecessor** of a node is:
   → The node that appears **just before it in the inorder traversal**.

🔹 In Morris Traversal, we use the inorder predecessor to:
   → Find where to make a temporary thread (link back to the current node).

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔍 HOW TO FIND IT?

If a node has a **left subtree**, then its inorder predecessor is:
→ The **rightmost node** in that left subtree.

📌 Why? Because in inorder (Left → Node → Right),
→ The largest value from the left subtree comes just before the current node.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🌳 EXAMPLE TREE:

          5
         / \
        3   7
       / \
      2   4

Let’s say current node = 5

→ 5 has a left subtree → Go to 3
→ Then go to the **rightmost node** in 3's subtree → which is 4

✅ So, inorder predecessor of 5 = 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📘 INORDER TRAVERSAL: [2, 3, 4, 5, 7]

→ predecessor of 5 = 4
→ predecessor of 3 = 2
→ predecessor of 2 = (none)
→ predecessor of 7 = 5
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🛠 USE IN MORRIS TRAVERSAL:

When we are at node 5:
→ We find its predecessor = 4
→ We set 4.right = 5 (temporary thread)
→ Then we move to 5.left (which is 3)
→ So that when we finish left subtree, we can come back to 5 via the thread

👀 Visualizing thread:
          5
         / \
        3   7
       / \
      2   4
           \
            5 ← temporary thread created
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

*/


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🌳 What is Inorder Predecessor?

👉 Inorder Predecessor = Node that comes just before the current node in inorder traversal.

📘 Inorder Traversal = Left → Root → Right

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 RULE:
If a node has a left child:
→ Go left once
→ Then go right as far as possible

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔽 Example Tree:

         5
        / \
       3   7
      / \
     2   4

Inorder Traversal: [2, 3, 4, 5, 7]

🔎 Inorder Predecessors:
- 2 → None
- 3 → 2
- 4 → 3
- 5 → 4 ✅ ← our main example
- 7 → 5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
💡 So for node 5:
- Left child = 3
- Go to 3 → then go to rightmost → which is 4
✅ Inorder Predecessor of 5 = 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧰 Why important in Morris Traversal?

We make a **temporary thread** from predecessor (4) to current node (5) to return back later without recursion or stack.

Before threading:
         5
        / \
       3   7
      / \
     2   4

After threading:
         5
        / \
       3   7
      / \
     2   4
          \
           5  ← temporary thread to go back

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/
