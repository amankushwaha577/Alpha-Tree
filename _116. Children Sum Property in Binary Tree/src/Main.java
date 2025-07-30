// 🌳 Node class for Binary Tree
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class Main {

    // 🔁 Recursively modifies tree to satisfy Children Sum Property
    public static void changeTree(Node root) {
        if (root == null) return;

        // 🧮 Step 1: Calculate sum of left and right child data
        int child = 0;
        if (root.left != null) child += root.left.data;
        if (root.right != null) child += root.right.data;

        // 📏 Step 2: If child sum is greater or equal, update root
        if (child >= root.data) {
            root.data = child;
        } else {
            // ↘️ Step 3: If root is greater, push root data to child
            if (root.left != null) root.left.data = root.data;
            else if (root.right != null) root.right.data = root.data;
        }

        // 🔄 Step 4: Recur for left and right children
        changeTree(root.left);
        changeTree(root.right);

        // 🔁 Step 5: After recursion, update current root to sum of children's data
        int tot = 0;
        if (root.left != null) tot += root.left.data;
        if (root.right != null) tot += root.right.data;

        if (root.left != null || root.right != null) root.data = tot;
    }

    // 📤 Inorder Traversal to display the tree
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        /*
            Tree before change:

              100
            /     \
          40       20
         /  \     /   \
        2    5   30    40


            Expected tree after applying Children Sum Property:

              *120*
            /       \
         50          70
       /   \       /    \
     25     25   30      40
        */

        // 🧱 Constructing the Tree
        Node root = new Node(50);
        root.left = new Node(7);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.right = new Node(1);

        // 📌 Before Change
        System.out.println("Inorder before applying Children Sum Property:");
        printInorder(root);
        System.out.println();

        // 🛠️ Change Tree
        changeTree(root);

        // 📌 After Change
        System.out.println("Inorder after applying Children Sum Property:");
        printInorder(root);
        System.out.println();
    }
}

/*
🔍 DRY RUN – Using Diagram from Image (Children Sum Property)

📌 Initial Tree:
              100
            /     \
          40       20
         /  \     /   \
        2    5   30    40

------------------------------------------
➡ Step 1: Node(100)
    child sum = 40 + 20 = 60
    → 60 < 100 ⇒ push root value (100) down to children

    → Left child = 50, Right child = 50 (so they sum to 100)

🆕 Updated:
              100
            /     \
         *50*     *50*
         /  \     /   \
        2    5   30    40

------------------------------------------
➡ Step 2: Node(50) (left subtree)
    child sum = 2 + 5 = 7
    → 7 < 50 ⇒ push 50 down to children equally or maintain structure

    → Left = 25, Right = 25 (just to match total sum)

🆕 Updated:
              100
            /     \
         *50*     50
         /  \     /   \
      *25*  *25* 30    40

------------------------------------------
➡ Step 3: Node(50) (right subtree)
    child sum = 30 + 40 = 70
    → 70 > 50 ⇒ set root = 70

🆕 Updated:
              100
            /     \
          50     *70*
         /  \     /   \
       25   25  30    40

------------------------------------------
➡ Now start backtracking (post-order) and adjust root to child sums

✓ Node(50 left): 25 + 25 = 50 → OK
✓ Node(70 right): 30 + 40 = 70 → OK
✓ Root(100): 50 + 70 = *120* → so update root = 120

✅ Final Tree:
              *120*
            /       \
         50          70
       /   \       /    \
     25     25   30      40

------------------------------------------
🎯 Summary:
• Nodes are updated to ensure each parent = left + right
• When child sum < parent → push value down
• When child sum ≥ parent → update parent

🕒 Time Complexity: O(N)
🧠 Space Complexity: O(H)

*/
