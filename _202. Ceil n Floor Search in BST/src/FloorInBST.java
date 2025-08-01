public class FloorInBST {

    // 🌳 Tree node class
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = this.right = null;
        }
    }

    // 🔍 Function to find Floor in BST
    public static int findFloor(Node root, int key) {
        int floor = -1;

        while (root != null) {
            if (root.data == key) {
                // 🎯 Exact match — it is the floor
                return root.data;
            } else if (root.data > key) {
                // 🔼 Too big → move left
                root = root.left;
            } else {
                // 🔽 Possible floor → move right
                floor = root.data;
                root = root.right;
            }
        }

        return floor;
    }

    // 🔧 Build example BST:
    /*
                8
              /   \
             4     12
            / \    / \
           2   6  10 14
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

        int key = 9;
        int floor = findFloor(root, key);

        System.out.println("Floor of " + key + " in BST is: " + floor);
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 DRY RUN: Find Floor of 9 in BST

Tree:
            8
          /   \
         4     12
        / \    / \
       2   6  10 14

Key = 9

📍 Step 1: root = 8
  → 8 < 9 → Possible floor = 8
  → Move right to 12

📍 Step 2: root = 12
  → 12 > 9 → Too big → Move left to 10

📍 Step 3: root = 10
  → 10 > 9 → Still too big → Move left → null

✅ Final Answer: 8

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
🧠 CONCEPTUAL LOGIC: Floor in Binary Search Tree (BST)

🎯 GOAL:
- Given a BST and a key `X`, we want to find the **greatest value in the tree that is less than or equal to X**.
- This greatest value is called the **Floor** of X.
- If no such value exists, return -1.

🧱 KEY OBSERVATIONS:
1. In a BST:
   - All values in the **left subtree** of a node are **smaller**
   - All values in the **right subtree** are **greater**

2. If we are at a node with value `curr`:
   - If `curr == X`, then we've found the floor → ✅ return `curr`
   - If `curr > X`, then this node and its right subtree are **too big** → Move to `curr.left`
   - If `curr < X`, then this node is a **potential floor**, but maybe a bigger
     valid floor exists in the **right subtree** → Save `floor = curr`, then move to `curr.right`
*/
