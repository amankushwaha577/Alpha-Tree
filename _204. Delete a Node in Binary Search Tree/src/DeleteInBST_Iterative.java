 public class DeleteInBST_Iterative {

    // 🌳 BST Node structure
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            left = right = null;
        }
    }

    // 🔧 Iterative delete function
    public static Node deleteNode(Node root, int key) {
        Node parent = null;
        Node curr = root;

        // 🔍 1. Search for the node and keep track of parent
        while (curr != null && curr.data != key) {
            parent = curr;
            if (key < curr.data) curr = curr.left;
            else curr = curr.right;
        }

        if (curr == null) return root; // ❌ Key not found

        // 🧍‍♂️ 2. Case 1: Node has no children
        if (curr.left == null && curr.right == null) {
            if (curr == root) return null;
            if (parent.left == curr) parent.left = null;
            else parent.right = null;
        }

        // 🌿 3. Case 2: Node has only one child
        else if (curr.left == null || curr.right == null) {
            Node child = (curr.left != null) ? curr.left : curr.right;
            if (curr == root) return child;
            if (parent.left == curr) parent.left = child;
            else parent.right = child;
        }

        // 🌳 4. Case 3: Node has two children
        else {
            // 🧮 Find inorder successor and its parent
            Node succParent = curr;
            Node succ = curr.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // 🔁 Replace value
            curr.data = succ.data;

            // ❌ Delete successor node
            if (succParent.left == succ)
                succParent.left = succ.right;
            else
                succParent.right = succ.right;
        }

        return root;
    }

    // 🧾 Inorder Traversal
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🔨 Build sample BST
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
        root = deleteNode(root, key);  // ❌ Delete node with key = 3

        System.out.println("\nInorder after deleting " + key + ": ");
        inorder(root);
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Delete 3 from BST (Iteratively)

Original Tree:
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \     /
           4   7   13

🎯 Delete key = 3
→ Found 3 under parent 8

✔ 3 has two children (1 and 6)
→ Inorder Successor = 4 (leftmost in right subtree of 3)
→ Replace 3 with 4

🌿 Now delete node 4 (which has no left child)

Resulting Tree:
              8
            /   \
           4     10
          / \      \
         1   6      14
              \     /
               7   13

✅ Inorder After: 1 4 6 7 8 10 13 14

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 Deletion Cases Summary (Iterative):

1️⃣ No child → Just disconnect
2️⃣ One child → Link parent to child
3️⃣ Two children → Replace with inorder successor, then delete successor

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱ TIME COMPLEXITY:
- Average: O(log N)
- Worst case (skewed tree): O(N)

📦 SPACE COMPLEXITY:
- O(1) → Fully iterative, no recursion stack

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/
