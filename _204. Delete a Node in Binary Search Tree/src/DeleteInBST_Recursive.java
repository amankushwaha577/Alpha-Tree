public class DeleteInBST_Recursive {

    // 🌳 Binary Tree Node
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔧 Delete a node with given key from BST
    public static Node deleteNode(Node root, int key) {
        if (root == null) return null;

        if (key < root.data) {
            root.left = deleteNode(root.left, key); // 🔽 Go left
        } else if (key > root.data) {
            root.right = deleteNode(root.right, key); // 🔼 Go right
        } else {
            // 🎯 Node to delete found

            // 🧍‍♂️ Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // 🌿 Case 2: One child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // 🌳 Case 3: Two children
            // ✅ Replace with inorder successor (smallest in right subtree)
            int successorVal = findMin(root.right);
            root.data = successorVal;

            // ❌ Delete the successor node from right subtree
            root.right = deleteNode(root.right, successorVal);
        }

        return root;
    }

    // 🔍 Find minimum value in BST (used to find successor)
    private static int findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    // 🌿 Inorder Traversal
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🏗️ Build Sample Tree:
    /*
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \     /
           4   7   13
    */
    public static Node buildTree() {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildTree();

        System.out.print("Inorder before deletion: ");
        inorder(root);

        int key = 3;
        root = deleteNode(root, key); // ❌ Delete key

        System.out.println("\nInorder after deleting " + key + ": ");
        inorder(root); // ✅ Validate new tree structure
    }
}


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Delete 3 from BST

Initial Tree:
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \     /
           4   7   13

🎯 Node to Delete = 3 (Has 2 children)

1️⃣ Find inorder successor of 3 (min in right subtree of 6):
   → 6 → 4 → ✅ Successor = 4

2️⃣ Replace 3 with 4:
              8
            /   \
           4     10
          / \      \
         1   6      14
            / \     /
               7   13

3️⃣ Now delete 4 from right subtree of 6
   → 6.left = null (removed 4)

✅ Final Tree:
              8
            /   \
           4     10
          / \      \
         1   6      14
              \     /
               7   13

🧾 Inorder After: 1 4 6 7 8 10 13 14

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱ TIME COMPLEXITY:
- Best/Average: O(log N) → Balanced tree
- Worst Case : O(N)     → Skewed tree

📦 SPACE COMPLEXITY:
- O(H) due to recursion stack (H = height of tree)
- In balanced → O(log N)
- In skewed   → O(N)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 KEY CONCEPT:
3 Cases for deletion in BST:

1️⃣ No children → Just remove node
2️⃣ One child   → Replace with child
3️⃣ Two children → Replace with inorder successor, delete successor

*/
