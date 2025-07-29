public class BuildPreorderTree {

    // Node class to represent each node in the tree
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1; // 🔢 To keep track of current position in preorder array

        // 📦 Build Tree from preorder array where -1 means null node
        public static Node buildTree(int[] nodes) {
            idx++;  // Move to next index

            // 🛑 Base case: if current node is -1, return null
            if (nodes[idx] == -1) {
                return null;
            }

            // ✅ Create new node with current value
            Node newNode = new Node(nodes[idx]);

            // 🌿 Recursively build left and right subtrees
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode; // 🔙 Return the node to be attached to its parent
        }

        // 🔍 Inorder traversal for checking tree structure (Left → Root → Right)
        public static void inorder(Node root) {
            if (root == null) return;
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        // 🌳 Preorder array: root, left, right (-1 means null)
        int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        // 🛠 Build the tree
        Node root = BinaryTree.buildTree(preorder);

        // 🖨 Print inorder traversal of the tree
        System.out.print("Inorder Traversal: ");
        BinaryTree.inorder(root);  // Output should be: 4 2 5 1 3 6
    }
}


/*
👀 DRY RUN (Graphical Representation) for:
    int[] preorder = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

Step-by-step tree build using preorder (Root → Left → Right):

➡️  idx = 0 → 1
         🔵1
        /   \
       ?     ?

➡️  idx = 1 → 2
         🔵1
        /   \
     🔵2     ?

➡️  idx = 2 → 4
         🔵1
        /   \
     🔵2     ?
     /  \
  🔵4    ?

➡️  idx = 3 → -1 (left of 4 is null)
➡️  idx = 4 → -1 (right of 4 is null)

         🔵1
        /   \
     🔵2     ?
     /  \
  🔵4   ❌
 /   \
❌   ❌

➡️  idx = 5 → 5 (right child of 2)
         🔵1
        /   \
     🔵2     ?
     /  \
  🔵4   🔵5
 / \   / \
❌ ❌ ❌ ❌

➡️  idx = 8 → 3 (right child of 1)
         🔵1
        /   \
     🔵2     🔵3
     /  \      \
  🔵4   🔵5     🔵6
 / \   / \     / \
❌ ❌ ❌ ❌   ❌ ❌

✅ Final Tree:
            🔵1
           /   \
        🔵2     🔵3
       /  \       \
    🔵4   🔵5     🔵6

Legend:
🔵 = Node with value
❌ = null (represented by -1)
*/
