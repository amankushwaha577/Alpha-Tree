public class LCAinBST {

    // 🌳 Node structure
    /*
    LCA in BST:
        - If both p and q are smaller → LCA lies in left subtree
        - If both p and q are greater → LCA lies in right subtree
        - If they split (one smaller, one greater OR one equals current) → current node is LCA
     */
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            left = right = null;
        }
    }

    // 🔍 Iterative LCA in BST
    public static Node findLCA(Node root, int p, int q) {
        while (root != null) {
            if (p < root.data && q < root.data) {
                root = root.left;  // Both nodes in left subtree
            } else if (p > root.data && q > root.data) {
                root = root.right; // Both nodes in right subtree
            } else {
                // LCA found (p and q split at current node)
                return root;
            }
        }
        return null;
    }

    // 🔧 Example Tree Builder
    /*
                20
               /  \
             10    30
            / \    / \
           5  15  25  35
    */
    public static Node buildBST() {
        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(15);
        root.right.left = new Node(25);
        root.right.right = new Node(35);
        return root;
    }

    public static void main(String[] args) {
        Node root = buildBST();
        int p = 5, q = 15;

        Node lca = findLCA(root, p, q);
        if (lca != null)
            System.out.println("LCA of " + p + " and " + q + " is: " + lca.data);
        else
            System.out.println("LCA does not exist");
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 COMPLEXITY:

✅ TIME: O(H)
    - H = height of tree
    - Balanced BST → O(log N)
    - Skewed BST   → O(N)

✅ SPACE:
    - Iterative → O(1) (no recursion or stack)
    - Recursive version would use O(H) stack

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/

/*
INPUT: p = 5, q = 15

BST:
                20
               /  \
             10    30
            / \    / \
           5  15  25  35

Inorder Traversal: [5, 10, 15, 20, 25, 30, 35]

🎯 Step-by-step:
1️⃣ root = 20
    - p = 5, q = 15 → both < 20 → move LEFT

2️⃣ root = 10
    - p = 5, q = 15
        → 5 < 10 and 15 > 10 → p and q split across left and right
    ✅ LCA = 10

 */