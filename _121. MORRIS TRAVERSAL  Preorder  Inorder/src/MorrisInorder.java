
public class MorrisInorder {

    // 🌳 Define the structure of a binary tree node
    static class Node {
        int data;
        Node left, right;

        // Constructor to initialize node value
        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    // 🚶‍♂️ Morris Inorder Traversal Function
    public static void morrisInorder(Node root) {
        Node current = root;  // Start from the root

        // 🌀 Loop until all nodes are visited
        while (current != null) {

            // 📌 CASE 1: If there is NO left child
            if (current.left == null) {
                // ✅ Visit the node (print its value)
                System.out.print(current.data + " ");
                // ➡ Move to right child
                current = current.right;
            }

            // 📌 CASE 2: If there IS a left child
            else {
                // 🔍 Find the **inorder predecessor** of current
                Node predecessor = current.left;

                // 📦 Go to the rightmost node in left subtree
                // (i.e., the node just before current in inorder)
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                // 🪝 FIRST TIME visiting current: Thread the tree
                if (predecessor.right == null) {
                    // 🔗 Make a temporary thread from predecessor to current
                    predecessor.right = current;
                    // ⬅ Move to the left child
                    current = current.left;
                }

                // ❌ SECOND TIME visiting current: Break thread and visit node
                else {
                    // 🔓 Remove the temporary thread
                    predecessor.right = null;
                    // ✅ Visit the node (print its value)
                    System.out.print(current.data + " ");
                    // ➡ Move to the right child
                    current = current.right;
                }
            }
        }
    }

    // 🔧 Utility function to build this example tree:
    //             1
    //           /   \
    //          2     3
    //         / \   / \
    //        4   5 6   7
    public static Node buildTestTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        return root;
    }

    // 🏁 Main function to test Morris traversal
    public static void main(String[] args) {
        Node root = buildTestTree();  // 🔨 Build test tree

        System.out.print("Morris Inorder Traversal: ");
        morrisInorder(root);  // 🔁 Perform Morris Inorder Traversal
        // ✅ Expected Output: 4 2 5 1 6 3 7
    }
}


/*
In-order Morris Traversal:
🌳 1st case: if left is null, print current node and go right
🌳 2nd case: before going left, make right most node on left subtree connected to current node, then go left
🌳 3rd case: if thread is already pointed to current node, then remove the thread
*/

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Morris Inorder Traversal

Tree Structure:
        1
      /   \
     2     3
    / \
   4   5

Goal: Inorder = [4, 2, 5, 1, 3]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 1: curr = 1
↳ Has left child → Find inorder predecessor of 1 (rightmost of 2 → 5)
↳ Create thread: 5.right = 1
↳ Move curr = 2

Tree with thread:
        1
      /   \
     2     3
    / \
   4   5
        \
         ⟳ 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 2: curr = 2
↳ Has left → Find predecessor (4)
↳ Create thread: 4.right = 2
↳ Move curr = 4

Tree with threads:
        1
      /   \
     2     3
    / \
   4   5
    \    \
     ⟳ 2   ⟳ 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 3: curr = 4
↳ No left → Visit 4 ✅
↳ curr = 4.right → threaded to 2

Output: [4]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 4: curr = 2
↳ Thread exists (from 4), remove it
↳ Visit 2 ✅
↳ curr = 2.right → 5

        1
      /   \
     2     3
    / \
   4   5
        \
          ⟳ 1

Output: [4, 2]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 5: curr = 5
↳ No left → Visit 5 ✅
↳ curr = 5.right → threaded to 1

Output: [4, 2, 5]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 6: curr = 1
↳ Thread exists (from 5), remove it
↳ Visit 1 ✅
↳ curr = 1.right → 3

        1
      /   \
     2     3
    / \
   4   5

Output: [4, 2, 5, 1]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 7: curr = 3
↳ No left → Visit 3 ✅
↳ curr = null → END

Output: [4, 2, 5, 1, 3]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ FINAL OUTPUT:
Morris Inorder Traversal: 4 2 5 1 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(N)
→ Every edge/thread is visited twice max

📦 Space: O(1)
→ No stack, no recursion — just pointers
*/