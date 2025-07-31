import java.util.*;

public class Construct_the_Binary_Tree_from_Postorder_and_Inorder_Traversal  {
    // 🌳 Node structure
    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            this.data = val;
        }
    }

    // ⏳ Global index for postorder array (starts from end)
    static int postIndex;

    public static Node buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;

        // 🗺️ Map to store inorder value -> index for O(1) lookup
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return build(postorder, 0, inorder.length - 1, inMap);
    }

    private static Node build(int[] postorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // ⛔ Base case
        if (inStart > inEnd) return null;

        // 🎯 Current root from postorder
        int rootVal = postorder[postIndex--];
        Node root = new Node(rootVal);

        // 📍 Get index of root in inorder
        int inIndex = inMap.get(rootVal);

        // ⚠️ Build right subtree first (postorder goes left-right-root, so we go reverse)
        root.right = build(postorder, inIndex + 1, inEnd, inMap);
        root.left = build(postorder, inStart, inIndex - 1, inMap);

        return root;
    }

    // 🧪 Inorder traversal to verify structure
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] inorder =  {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        Node root = buildTree(inorder, postorder);

        System.out.print("Inorder of constructed tree: ");
        printInorder(root);  // Expected: 9 3 15 20 7
    }
}


/*
🧠 DRY RUN: Build Binary Tree from Postorder + Inorder

Given:
➡ Postorder: [9, 15, 7, 20, 3] → (Left, Right, Root)
➡ Inorder:   [9, 3, 15, 20, 7] → (Left, Root, Right)

📦 Supporting Data Structures:
- `postIndex` = 4 (starts from end of postorder)
- `inorderMap` = {9:0, 3:1, 15:2, 20:3, 7:4}
- Recurse using postIndex and inorder bounds

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 1: buildSubTree(0, 4)

postIndex = 4 → postorder[4] = 3 → 🪵 root = 3
inorderMap[3] = 1

↘ Right Inorder: inorder[2 to 4] → [15, 20, 7]
↙ Left Inorder:  inorder[0 to 0] → [9]

🌳 Tree so far:
       3
     /   \
 ( ? )   ( ? )

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 2: buildSubTree(2, 4)

postIndex = 3 → postorder[3] = 20 → 🪵 root = 20
inorderMap[20] = 3

↘ Right Inorder: inorder[4 to 4] → [7]
↙ Left Inorder:  inorder[2 to 2] → [15]

🌳 Tree so far:
       3
     /   \
    ?     20
         /  \
     ( ? ) ( ? )

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 3: buildSubTree(4, 4)

postIndex = 2 → postorder[2] = 7 → 🪵 root = 7
inorderMap[7] = 4

🛑 No children (single element)

🌳 Tree so far:
       3
     /   \
    ?     20
         /  \
       ?     7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 4: buildSubTree(2, 2)

postIndex = 1 → postorder[1] = 15 → 🪵 root = 15
inorderMap[15] = 2

🛑 No children

🌳 Tree so far:
       3
     /   \
    ?     20
         /  \
       15     7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔹 Step 5: buildSubTree(0, 0)

postIndex = 0 → postorder[0] = 9 → 🪵 root = 9
inorderMap[9] = 0

🛑 No children

🌳 Final Constructed Tree:
       3
     /   \
    9     20
         /  \
       15    7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Final Output:
Inorder Traversal = 9 3 15 20 7

📈 Time Complexity: O(N)
→ Each node visited once, map lookup is O(1)

📦 Space Complexity: O(N)
→ Recursion stack + HashMap
*/
