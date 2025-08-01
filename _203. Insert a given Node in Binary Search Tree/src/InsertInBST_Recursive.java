public class InsertInBST_Recursive {

    // 🌳 Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔧 Recursive function to insert a node into BST
    public static Node insert(Node root, int key) {
        if (root == null) {
            // 🧱 Base case: create a new node when spot is found
            return new Node(key);
        }

        if (key < root.data) {
            // 🔽 Go left if key is smaller
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            // 🔼 Go right if key is greater
            root.right = insert(root.right, key);
        }
        // ❗ If key == root.data, we skip (no duplicates allowed in standard BST)
        return root;
    }

    // 🌿 Inorder traversal to verify structure after insertion
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🔨 Build initial BST manually
    /*
                8
              /   \
             4     12
            / \   /  \
           2   6 10  14
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

    public static void main(String[] args) {
        Node root = buildTree();

        int key = 5; // 🔑 Key to insert
        root = insert(root, key);

        System.out.print("Inorder after insertion: ");
        inorder(root);  // ✅ Should be: 2 4 5 6 8 10 12 14
    }
}


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Insert 5 into BST

Initial Tree:
            8
          /   \
         4     12
        / \   /  \
       2   6 10  14

Insert 5:

📍 Step 1: root = 8
→ 5 < 8 → Go left

📍 Step 2: root = 4
→ 5 > 4 → Go right

📍 Step 3: root = 6
→ 5 < 6 → Go left

📍 Step 4: root.left is null → Insert new Node(5)

Updated Tree:
            8
          /   \
         4     12
        / \   /  \
       2   6 10  14
          /
         5

✅ Inorder traversal now = 2 4 5 6 8 10 12 14

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱ Time Complexity: O(H)
   - H = height of tree
   - Balanced BST → O(log N)
   - Skewed BST   → O(N)

📦 Space Complexity:
   - Recursive: O(H) stack space (if recursion used)
   - Iterative: O(1)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 NOTE:
- Standard BSTs **do not allow duplicates**
- If you want duplicates:
  → You can allow them on either left or right consistently
*/
