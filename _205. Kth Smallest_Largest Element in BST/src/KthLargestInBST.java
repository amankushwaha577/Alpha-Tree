public class KthLargestInBST {

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

    // 🔍 Function to find Kth largest using **Reverse Inorder** Traversal
    public static int kthLargest(Node root, int k) {
        count = 0; // Reset before each call
        return reverseInorder(root, k);
    }

    private static int reverseInorder(Node node, int k) {
        if (node == null) return -1;

        // ➡ Go right first for largest elements
        int right = reverseInorder(node.right, k);
        if (right != -1) return right;

        // 🧮 Visit current node
        count++;
        if (count == k) return node.data;

        // ➡ Then go to left subtree
        return reverseInorder(node.left, k);
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
        int ans = kthLargest(root, k);
        System.out.println("Kth Largest Element = " + ans);
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Find 3rd Largest in BST

BST (Inorder = ascending):
                5
              /   \
             3     7
            / \   / \
           2   4 6   8

🌿 Inorder:        [2, 3, 4, 5, 6, 7, 8]
🌿 Reverse Inorder: [8, 7, 6, 5, 4, 3, 2]

Goal: Find 3rd largest = 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 1: current = 5
→ Go right to 7

Step 2: current = 7
→ Go right to 8

Step 3: current = 8
→ Right = null
✅ Visit 8 → count = 1

→ Left = null → Return to 7

Tree view so far:
        5
           \
           [7]
              \
              [8] ← count = 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 4: current = 7
✅ Visit 7 → count = 2

→ Go left to 6

Step 5: current = 6
→ Right = null
✅ Visit 6 → count = 3 🎯 FOUND

✅ 3rd Largest = 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🟩 OUTPUT: Kth Largest Element = 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY

⏱ Time: O(H + K)
→ May traverse up to height H + k nodes

📦 Space:
- Recursive: O(H) stack space
- Iterative: O(H) with stack

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/


// 🧠 ALGORITHM: K-th Largest in BST
/*
💡 KEY IDEA:
- Reverse inorder traversal of a BST (Right → Node → Left) gives elements in **descending order**.
- So, the K-th **largest** element is the **K-th node** visited in reverse inorder.

📌 ALGORITHM STEPS:
1. Do **Reverse Inorder Traversal**: Right → Node → Left
2. Use a `count` variable to track number of visited nodes.
3. When `count == k`, you've found the answer.

📦 WHY IT WORKS:
- In BST, left < root < right
- Rightmost nodes are larger → So reverse inorder = descending

🎯 Example:
BST inorder: [2, 3, 4, 5, 6, 7, 8]
→ Reverse: [8, 7, 6, 5, 4, 3, 2]
→ 3rd largest = 6

🕒 TIME COMPLEXITY:
- Best case: O(H + K)
- H = height of BST
- Worst case: O(N)

📦 SPACE COMPLEXITY:
- O(H) due to recursion stack

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/
