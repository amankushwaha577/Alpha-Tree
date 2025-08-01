public class CeilInBST {

    // 🌳 Tree node class
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔍 Function to find ceil in BST
    public static int findCeil(Node root, int key) {
        int ceil = -1;

        while (root != null) {
            if (root.data == key) {
                // 🎯 Exact match, it's the ceil
                return root.data;
            } else if (root.data < key) {
                // 🔽 Current node too small, go right
                root = root.right;
            } else {
                // 🔼 Possible ceil, but go left to find smaller possible value
                ceil = root.data;
                root = root.left;
            }
        }

        return ceil;
    }

    // 🔧 Build example BST
    /*
                8
              /   \
             4     12
            / \    / \
           2   6  10  14
    */
    public static Node buildTestTree() {
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
        Node root = buildTestTree();

        int key = 5;
        int ceil = findCeil(root, key);

        System.out.println("Ceil of " + key + " in BST is: " + ceil);
    }
}


/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Find Ceil of 5 in BST

Tree:
            8
          /   \
         4     12
        / \    / \
       2   6  10 14

Key = 5

📍 Step 1: root = 8
  → 8 > 5 → Possible ceil = 8
  → Move left to 4

📍 Step 2: root = 4
  → 4 < 5 → Too small → Move right to 6

📍 Step 3: root = 6
  → 6 > 5 → New possible ceil = 6
  → Move left → null

✅ Final Answer: 6

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱ Time Complexity: O(H)
   - H = height of BST
   - In balanced BST → O(log N)
   - In skewed BST   → O(N)

📦 Space Complexity: O(1)
   - No extra data structures used

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/

/*
🧠 CONCEPTUAL LOGIC: Ceil in Binary Search Tree (BST)

🎯 GOAL:
- Given a BST and a key `X`, we want to find the **smallest value in the tree that is greater than or equal to X**.
- This smallest value is known as the **Ceil** of X.
- If no such value exists, return -1.

🧱 KEY OBSERVATIONS:
1. In a BST:
   - All values in the **left subtree** of a node are **smaller** than the node.
   - All values in the **right subtree** of a node are **greater** than the node.

2. If we are at a node with value `curr`:
   - If `curr == X`, then we've found the ceil (exact match) → ✅ return `curr`
   - If `curr < X`, then we can **discard the left subtree** and this node,
     because all of them are smaller → Move to `curr.right`
   - If `curr > X`, then this node is a **potential ceil**, but maybe a smaller
     valid ceil exists in the **left subtree** → Save `ceil = curr`, then move to `curr.left`
*/
