public class Bruteforce {

    // 🌿 Node class for Binary Tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // 📏 Utility method to get height of a subtree
    public static int height(Node root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 🔍 Brute-force function to check if tree is balanced
    public static boolean isBalanced(Node root) {
        if (root == null) return true;

        // ✅ Check height difference at current node
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;  // ❌ Not balanced
        }

        // 🔁 Recursively check left and right subtrees
        boolean isLeftBalanced = isBalanced(root.left);
        boolean isRightBalanced = isBalanced(root.right);

        return isLeftBalanced && isRightBalanced;
    }

    public static void main(String[] args) {
        /*
               1
             /   \
            2     3
           /
          4
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);

        boolean result = isBalanced(root);
        System.out.println("Is the tree balanced? " + result); // ❌ Output: false
    }
}

/*

🧠 LOGIC: Brute-force Balanced Binary Tree Check

✔️ A tree is balanced if:
   → For every node: |height(left) - height(right)| <= 1

🔁 Brute-force idea:
- For each node:
    1. Compute height of left subtree
    2. Compute height of right subtree
    3. Check if abs difference <= 1
    4. Recursively check left and right subtrees

📌 Time Complexity: O(n^2)
    → Because height() is O(n), and isBalanced() is called for every node

📌 Space Complexity: O(h) due to recursion stack

🧪 DRY RUN:

Tree:
         1
       /   \
      2     3
     /
    4

Step-by-step:

→ isBalanced(1):
   height(2) = 2, height(3) = 1 → diff = 1 ✅
   isBalanced(2):
      height(4) = 1, height(null) = 0 → diff = 1 ✅
      isBalanced(4):
         height(null) = 0, height(null) = 0 → diff = 0 ✅
   isBalanced(3): ✅ (both children null)

✅ All nodes return true → Tree is balanced

If we added one more node like: `root.left.left.left = new Node(5);`, then:

→ height(2) becomes 3, height(3) = 1 → diff = 2 ❌

*/

