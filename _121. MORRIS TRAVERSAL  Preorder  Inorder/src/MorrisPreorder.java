public class MorrisPreorder {

    // 🌳 Define the structure of a binary tree node
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    // 🚶‍♂️ Morris Preorder Traversal Function
    public static void morrisPreorder(Node root) {
        Node current = root;

        while (current != null) {
            // 📌 CASE 1: No left child → Visit and go right
            if (current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            }

            // 📌 CASE 2: Has left child → Find predecessor
            else {
                Node predecessor = current.left;

                // 🧭 Go to the rightmost node in left subtree
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                // 🪝 FIRST TIME → Make thread & VISIT node
                if (predecessor.right == null) {
                    System.out.print(current.data + " ");  // ✅ Preorder visit
                    predecessor.right = current;
                    current = current.left;
                }
                // ❌ SECOND TIME → Remove thread and go right
                else {
                    predecessor.right = null;
                    // Here it was for Inorder -> System.out.print(current.data + " ");
                    current = current.right;
                }
            }
        }
    }

    // 🔧 Build the same test tree:
    //            1
    //          /   \
    //         2     3
    //        / \   / \
    //       4   5 6   7
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

    // 🏁 Test function
    public static void main(String[] args) {
        Node root = buildTestTree();

        System.out.print("Morris Preorder Traversal: ");
        morrisPreorder(root);  // ✅ Expected: 1 2 4 5 3 6 7
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Tree Structure:
           1
         /   \
        2     3
       / \   / \
      4   5 6   7

Goal: Preorder = [1, 2, 4, 5, 3, 6, 7]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🔹 Step 1: cur = 1
- Has left → predecessor = 5
- 🔗 Create thread: 5.right = 1
- ✅ Visit 1
→ Move cur = 2

Tree (threaded):
     1
    / \
   2   3
  / \
 4   5
      ↘
        ⟳ 1

Output: [1]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 2: cur = 2
- Has left → predecessor = 4
- 🔗 Create thread: 4.right = 2
- ✅ Visit 2
→ Move cur = 4

Tree:
     1
    / \
   2   3
  / \
 4   5
  ↘    ↘
   ⟳ 2   ⟳ 1

Output: [1, 2]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 3: cur = 4
- No left → ✅ Visit 4
→ Follow thread: cur = 2

Output: [1, 2, 4]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 4: cur = 2
- Thread exists → remove it
→ Move cur = 5

Output: [1, 2, 4] (unchanged here)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 5: cur = 5
- No left → ✅ Visit 5
→ Follow thread: cur = 1

Output: [1, 2, 4, 5]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 6: cur = 1
- Thread exists → remove it
→ Move cur = 3

Output: [1, 2, 4, 5] (unchanged here)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 7: cur = 3
- Has left → predecessor = 6
- 🔗 Create thread: 6.right = 3
- ✅ Visit 3
→ Move cur = 6

Output: [1, 2, 4, 5, 3]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 8: cur = 6
- No left → ✅ Visit 6
→ Follow thread to 3

Output: [1, 2, 4, 5, 3, 6]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 9: cur = 3
- Thread exists → remove it
→ Move cur = 7

Output: [1, 2, 4, 5, 3, 6] (unchanged here)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 10: cur = 7
- No left → ✅ Visit 7
→ cur = null → DONE

✅ Final Output: [1, 2, 4, 5, 3, 6, 7]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(N)
→ Each edge/thread is visited max twice

📦 Space: O(1)
→ No recursion or stack used
*/

