public class Bruteforce {

    // 🌿 Node class representing each node in the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static class BinaryTree {

        // 📏 Brute-force function to calculate the diameter of the binary tree
        public static int diameter(Node root) {
            // 🛑 Base case: If tree is empty
            if (root == null) return 0;

            // 🔁 Step 1: Find height of left and right subtrees
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            // 🔁 Step 2: Find diameter of left and right subtrees recursively
            int leftDiameter = diameter(root.left);
            int rightDiameter = diameter(root.right);

            // 🧮 Step 3: Diameter passing through the current root
            int selfDiameter = leftHeight + rightHeight + 1;

            // 📌 Step 4: Return max of all three
            return Math.max(selfDiameter, Math.max(leftDiameter, rightDiameter));
        }

        // 📏 Helper function to calculate height of a subtree
        private static int height(Node root) {
            if (root == null) return 0;

            // Recursively find height of left and right subtrees and add 1 for current node
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    // 🚀 Main function to test the diameter calculation
    public static void main(String[] args) {
        // 🧪 Example tree:
        /*
                   🔵1
                 /     \
              🔵2       🔵3
             /   \
           🔵4   🔵5
          /
        🔵6
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(6);

        // 🖨️ Print the diameter of the binary tree
        System.out.println("Diameter (Brute Force): " + BinaryTree.diameter(root)); // ✅ Output: 5
    }
}

/*
🧠 LOGIC: Brute-force Diameter of Binary Tree

🌳 Diameter = Longest path between any two nodes in the tree (can go through or bypass root)

🔁 Approach:
- For each node, we calculate:
  1. Left subtree height
  2. Right subtree height
  3. Diameter of left subtree
  4. Diameter of right subtree

- Then, for that node:
  ➤ Local Diameter = leftHeight + rightHeight + 1 (passes through current node)
  ➤ Global Diameter = max(localDiameter, leftDiameter, rightDiameter)

⚠️ But here's the issue:
  ➤ height() function is called multiple times for the same nodes
  ➤ Redundant work leads to higher time complexity

------------------------------------------------
🧪 DRY RUN (Example Tree):

              🔵1
            /     \
         🔵2       🔵3
       /    \         \
    🔵4     🔵5       🔵6

For node 1:
- height(2) = 2 → height(4)=1, height(5)=1
- height(3) = 2 → height(6)=1
- diameter(2) = 3
- diameter(3) = 2
→ max(2+2+1, 3, 2) = 5 ✅

📌 Answer = 5 (Path: 4 → 2 → 1 → 3 → 6)

------------------------------------------------
⏱️ TIME COMPLEXITY:

- diameter() is called for every node → O(n)
- height() is also called in every diameter() call and visits up to O(n) nodes
→ Total: O(n) × O(n) = O(n²)

⏳ SPACE COMPLEXITY:
- Recursion stack = O(h)
  ➤ h = height of tree
  ➤ Best case (balanced): O(log n)
  ➤ Worst case (skewed): O(n)
*/

