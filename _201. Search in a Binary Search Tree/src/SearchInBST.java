// 📘 Program: Search in a Binary Search Tree (BST)
public class SearchInBST {

    // 🌳 Node structure of BST
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    // 🔍 SEARCH FUNCTION in BST
    public static boolean search(Node root, int target) {
        // 🔁 Loop until node is null or target is found
        while (root != null) {
            if (root.data == target) {
                return true; // ✅ Found
            } else if (target < root.data) {
                root = root.left; // ⬅ Move left
            } else {
                root = root.right; // ➡ Move right
            }
        }
        return false; // ❌ Not found
    }

    // 🔧 Build example BST:
    /*
           8
         /   \
        4     12
       / \    / \
      2   6  10 14
    */
    public static Node buildTree() {
        Node root = new Node(8);
        root.left = new Node(4);
        root.right = new Node(12);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.right.left = new Node(10);
        root.right.right = new Node(14);
        return root;
    }

    // 🏁 Main Method
    public static void main(String[] args) {
        Node root = buildTree();

        int target = 10;
        if (search(root, target)) {
            System.out.println("✅ Found " + target + " in BST");
        } else {
            System.out.println("❌ " + target + " not found in BST");
        }
    }
}


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Search for target = 10 in BST

Initial BST:
            8
          /   \
         4     12
        / \    / \
       2   6  10  14


🔎 Step 1:
→ current = 8
→ 10 > 8 → move to right subtree

Tree view:
            8
               \
               [12]
               /  \
             10   14


🔎 Step 2:
→ current = 12
→ 10 < 12 → move to left subtree

Tree view:
            12
           /
         [10]
             \
             14


🔎 Step 3:
→ current = 10
→ 10 == 10 ✅ FOUND

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🟩 FINAL RESULT:
✅ Found 10 in BST

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 TIME & SPACE COMPLEXITY:

⏱ Time Complexity:
→ Best Case (Balanced Tree): O(log N)
→ Worst Case (Skewed Tree):  O(N)

📦 Space Complexity:
→ Iterative Version: O(1)
→ Recursive Version: O(H), where H = height of tree

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 NOTE:
A Binary Search Tree (BST) has this key property:
→ All left children < current node < all right children
→ So we can **skip half the tree** every step
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/