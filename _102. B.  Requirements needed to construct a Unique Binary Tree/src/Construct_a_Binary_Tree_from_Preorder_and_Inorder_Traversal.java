
import java.util.*;

public class Construct_a_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    // 🌳 Basic Node definition
    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            this.data = val;
        }
    }

    // 🧠 Index to keep track of current element in preorder array
    static int preIndex = 0;

    public static Node buildTree(int[] preorder, int[] inorder) {
        // 🗺️ Map to store inorder indices for quick lookup
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1, inMap);
    }

    private static Node build(int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // ⛔ Base condition: if inorder range is invalid
        if (inStart > inEnd) return null;

        // 🎯 Pick current node from preorder
        int rootVal = preorder[preIndex++];
        Node root = new Node(rootVal);

        // 📍 Get inorder index of root
        int inIndex = inMap.get(rootVal);

        // 🔁 Recursively build left and right subtrees
        root.left = build(preorder, inStart, inIndex - 1, inMap);
        root.right = build(preorder, inIndex + 1, inEnd, inMap);

        return root;
    }

    // 📤 Print the tree in postorder to verify
    public static void printPostorder(Node root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        Node root = buildTree(preorder, inorder);

        System.out.print("Postorder of constructed tree: ");
        printPostorder(root);  // Expected Output: 9 15 7 20 3
    }
}

/*
🧠 DRY RUN: Build Binary Tree from Preorder + Inorder

Goal: Construct the binary tree using:
➡ Preorder: [3, 9, 20, 15, 7] → (Root, Left, Right)
➡ Inorder:  [9, 3, 15, 20, 7] → (Left, Root, Right)

🛠 Data Structures Used:
🔹 preIndex → tracks current root from preorder array
🔹 inorderMap → maps value → index for fast lookup
🔹 Recursion stack → simulates recursive calls for subtree construction

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 INITIAL STATE:

preIndex = 0
inorderMap = {
  9: 0,
  3: 1,
  15: 2,
  20: 3,
  7: 4
}

Call: buildTree(preorder, inorder)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Iteration 1: build subtree from inorder[0:4]
preIndex = 0 → preorder[0] = 3 → Root = 3

inorderMap[3] = 1
➡ Left Subtree → inorder[0:0]   [9]
➡ Right Subtree → inorder[2:4] [15, 20, 7]

Stack: buildSubTree(0, 4)
Next preIndex = 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Iteration 2: build subtree from inorder[0:0]
preIndex = 1 → preorder[1] = 9 → Root = 9

inorderMap[9] = 0
➡ Left Subtree → invalid (end < start)
➡ Right Subtree → invalid (end < start)

Stack: buildSubTree(0, 0)
Next preIndex = 2

🟩 Node 9 becomes left of 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Iteration 3: build right subtree from inorder[2:4]
preIndex = 2 → preorder[2] = 20 → Root = 20

inorderMap[20] = 3
➡ Left Subtree → inorder[2:2] [15]
➡ Right Subtree → inorder[4:4] [7]

Stack: buildSubTree(2, 4)
Next preIndex = 3

🟩 Node 20 becomes right of 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Iteration 4: build left of 20 from inorder[2:2]
preIndex = 3 → preorder[3] = 15 → Root = 15

inorderMap[15] = 2
➡ No left/right subtree (single node)

Stack: buildSubTree(2, 2)
Next preIndex = 4

🟩 Node 15 becomes left of 20

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Iteration 5: build right of 20 from inorder[4:4]
preIndex = 4 → preorder[4] = 7 → Root = 7

inorderMap[7] = 4
➡ No left/right subtree

Stack: buildSubTree(4, 4)
Next preIndex = 5

🟩 Node 7 becomes right of 20

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ FINAL CONSTRUCTED TREE:

         3
       /   \
      9     20
           /  \
         15    7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 FINAL STATES:

preIndex = 5 → All nodes processed
Stack = empty (all recursive calls returned)
inorderMap stays same

🕒 Time Complexity: O(N)
→ Each node is visited once and hashmap gives O(1) index lookup

📦 Space Complexity: O(N)
→ inorderMap + recursion stack in worst case (skewed tree)
*/



/*
🧠 Goal: Reconstruct Binary Tree from:
Preorder = [3, 9, 20, 15, 7]
Inorder  = [9, 3, 15, 20, 7]

📍 Step-by-step Construction:

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 1: Build root node from preorder[0] = 3
inorderIndex(3) = 1

➡ Left Subtree: inorder[0 to 0] → [9]
➡ Right Subtree: inorder[2 to 4] → [15, 20, 7]

Tree so far:
       3
      / \
  ( ? )  ( ? )

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 2: Build left child of 3 using preorder[1] = 9
inorderIndex(9) = 0 → no left/right children

Tree so far:
       3
      / \
     9   ?

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 3: Build right child of 3 using preorder[2] = 20
inorderIndex(20) = 3

➡ Left Subtree: inorder[2 to 2] → [15]
➡ Right Subtree: inorder[4 to 4] → [7]

Tree so far:
       3
      / \
     9   20
         / \
     ( ? ) ( ? )

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 4: Build left child of 20 using preorder[3] = 15
inorderIndex(15) = 2 → no children

Tree so far:
       3
      / \
     9   20
         / \
       15   ?

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔸 Step 5: Build right child of 20 using preorder[4] = 7
inorderIndex(7) = 4 → no children

✅ Final Constructed Tree:
       3
      / \
     9   20
         / \
       15   7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 Index Tracking:
preIndex at each step → [0, 1, 2, 3, 4]
Each index corresponds to a root built at that stage

🕒 Time Complexity: O(N)
→ Each node is created once and map lookup is O(1)

📦 Space Complexity: O(N)
→ Recursion stack + HashMap storing inorder indices

*/
