public class BuildPreorderTree {

    // 🌿 Node class representing each element in the binary tree
    static class Node {
        int data;
        Node left;
        Node right;

        // 🧱 Constructor to initialize node data
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        // 🔢 Static index for tracking position in preorder array
        static int idx = -1;

        // 🏗️ Function to build tree from preorder array where -1 represents null
        public static Node buildTree(int[] nodes) {
            idx++;  // Move to next element

            // 🛑 If current value is -1, return null (empty node)
            if (nodes[idx] == -1) {
                return null;
            }

            // ✅ Create a new node
            Node newNode = new Node(nodes[idx]);

            // 🔽 Recursively build left subtree
            newNode.left = buildTree(nodes);

            // 🔼 Recursively build right subtree
            newNode.right = buildTree(nodes);

            return newNode;  // Return this node back to its parent
        }

        // 🔁 Preorder Traversal: Root → Left → Right
        public static void preorder(Node root) {
            if (root == null) {
                return;
            }

            System.out.print(root.data + " "); // Visit root
            preorder(root.left);               // Visit left
            preorder(root.right);              // Visit right
        }
    }

    public static void main(String[] args) {
        // 🌲 Preorder array (Root, Left, Right) with -1 as null
        int[] preorder = {
                1,
                2,
                4, -1, -1,
                5, -1, -1,
                3, -1,
                6, -1, -1
        };

        // 🛠️ Build the binary tree from the array
        Node root = BinaryTree.buildTree(preorder);

        // 🖨️ Print preorder traversal of the built tree
        System.out.print("Preorder Traversal: ");
        BinaryTree.preorder(root); // ➡️ Output: 1 2 4 5 3 6
    }
}


/*
👀 DRY RUN of buildTree() with preorder input:
   int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

🔄 Preorder format: Root → Left → Right
🔢 -1 means null (❌), otherwise node (🔵)

Step-by-step Recursive Construction:

1️⃣ idx = 0 → 1
Create 🔵1
    🔵1
   /   \
  ?     ?

2️⃣ idx = 1 → 2
Create 🔵2 as left child of 1
    🔵1
   /   \
 🔵2    ?

3️⃣ idx = 2 → 4
Create 🔵4 as left child of 2
    🔵1
   /   \
 🔵2    ?
 /  \
🔵4   ?

4️⃣ idx = 3 → -1 → ❌ (left of 4 is null)
5️⃣ idx = 4 → -1 → ❌ (right of 4 is null)

    🔵1
   /   \
 🔵2    ?
 /  \
🔵4   ?
/ \
❌ ❌

6️⃣ idx = 5 → 5
Create 🔵5 as right child of 2
    🔵1
   /   \
 🔵2    ?
 /  \
🔵4  🔵5
/ \  / \
❌❌ ?  ?

7️⃣ idx = 6 → -1 → ❌ (left of 5)
8️⃣ idx = 7 → -1 → ❌ (right of 5)

    🔵1
   /   \
 🔵2    ?
 /  \
🔵4  🔵5
/ \  / \
❌❌❌❌

9️⃣ idx = 8 → 3
Create 🔵3 as right child of 1
    🔵1
   /   \
 🔵2    🔵3
 /  \     \
🔵4  🔵5   ?

10️⃣ idx = 9 → -1 → ❌ (left of 3)
11️⃣ idx = 10 → 6
Create 🔵6 as right child of 3
    🔵1
   /   \
 🔵2    🔵3
 /  \     \
🔵4  🔵5   🔵6
/ \  / \   / \
❌❌❌❌ ❌ ?

12️⃣ idx = 11 → -1 → ❌ (left of 6)
13️⃣ idx = 12 → -1 → ❌ (right of 6)

✅ Final Tree:

            🔵1
           /    \
        🔵2      🔵3
       /   \       \
    🔵4    🔵5     🔵6
   / \    / \     / \
 ❌ ❌  ❌ ❌   ❌ ❌

Legend:
🔵 = Node
❌ = null (from -1)
?  = yet to be processed
*/
