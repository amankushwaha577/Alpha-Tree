public class KthSmallestInBST {

    // 🌳 Tree Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🧮 Counter to track kth visit
    static int count = 0;

    // 🔍 Function to find Kth smallest using Inorder Traversal
    public static int kthSmallest(Node root, int k) {
        count = 0; // Reset count before each call
        return inorder(root, k);
    }

    private static int inorder(Node node, int k) {
        if (node == null) return -1;

        // ➡ Recurse to left subtree
        int left = inorder(node.left, k);
        if (left != -1) return left; // If found in left, bubble it up

        // 🧮 Visit current node
        count++;
        if (count == k) return node.data; // 🎯 Found kth smallest

        // ➡ Recurse to right subtree
        return inorder(node.right, k);
    }

    // 🔧 Build example BST
    /*
                5
              /   \
             3     7
            / \   / \
           2   4 6   8
    */
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
🧠 DRY RUN: Find 3rd Smallest in BST

BST (Inorder = ascending):
                5
              /   \
             3     7
            / \   / \
           2   4 6   8

🔁 Inorder traversal sequence:
→ Left → Node → Right

🌿 Inorder: [2, 3, 4, 5, 6, 7, 8]

Let’s find k = 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 1: current = 5
→ Go left to 3

Step 2: current = 3
→ Go left to 2

Step 3: current = 2
→ Left = null
✅ Visit 2 → count = 1

→ Right = null → Return to 3

Tree view so far:
        5
       /
     [3]
     /
   [2] ← count = 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 4: current = 3
✅ Visit 3 → count = 2

→ Go right to 4

Step 5: current = 4
→ Left = null
✅ Visit 4 → count = 3 🎯 FOUND

✅ 3rd Smallest = 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🟩 OUTPUT: Kth Smallest Element = 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(H + K)
→ Worst case: Must explore up to height H + k nodes

📦 Space:
- Recursive: O(H) stack space
- Iterative: O(H) with stack

🎯 Bonus: Can also be done iteratively with explicit stack if needed
*/


// 🧠 ALGORITHM: K-th Smallest in BST
/*
💡 KEY IDEA:
- Inorder traversal of a Binary Search Tree (BST) always gives elements in **sorted order**.
- So, the K-th smallest element is just the **K-th node visited** in an inorder traversal.

📌 ALGORITHM STEPS:
1. Perform **inorder traversal** (Left → Node → Right).
2. Keep a global `count` variable to track how many nodes you've visited.
3. When `count == k`, that node is your answer.

📦 WHY IT WORKS:
- Because of BST property: left < root < right
- Inorder traversal visits nodes in increasing order
- So, the K-th node you visit is the K-th smallest

🎯 Example:
For BST → [2, 3, 4, 5, 6, 7, 8]
→ 3rd smallest = 4

🕒 TIME COMPLEXITY:
- Best case: O(H + K)
- H = height of BST (log N for balanced)
- Worst case: O(N) if unbalanced  | But still we are eliminating 0(NLogN) like other sorting algorithm

📦 SPACE COMPLEXITY:
- O(H) due to recursion (or O(H) with stack if iterative)
*/
