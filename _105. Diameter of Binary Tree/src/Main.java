public class Main {

    // 🌿 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {

        static int diameter = 0; // 🌍 Store max diameter as global value

        // 🔍 Function to compute diameter
        public static int diameter(Node root) {
            height(root); // compute diameter during height calc
            return diameter;
        }

        // 📏 Helper: Compute height + update diameter simultaneously
        private static int height(Node root) {
            if (root == null) return 0;

            // 🔽 Left & right subtree height
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            // 🧮 Possible diameter at current node = longest path through root
            int localDiameter = leftHeight + rightHeight + 1;

            // 🆙 Update global diameter if current is greater
            diameter = Math.max(diameter, localDiameter);

            // 🔁 Return height of current node
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 🔬 Test
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(6); // Extending depth

        System.out.println("Diameter of Tree: " + BinaryTree.diameter(root)); // Output: 5
    }
}


/*
🧠 LOGIC: Diameter of Binary Tree (Longest Path Between Any 2 Nodes)

📘 Diameter = Max number of nodes on any path (NOT necessarily through root)

🔁 Strategy:
1. Traverse tree in Postorder (Left → Right → Node)
2. At each node:
   - Compute height of left & right subtrees
   - Compute path passing through this node: left + right + 1
   - Update global diameter
3. Return height of current node to parent (for further calculations)

✅ Key: Reuse height calculation to find max diameter efficiently

📌 Time: O(n)
📌 Space: O(h) → Recursion stack

🧪 DRY RUN:

Tree:

         1
        / \
       2   3
      / \
     4   5
    /
   6

Postorder visits:

- Node 6: height = 1
- Node 4: left = 1, right = 0 → localDiameter = 2 → update
- Node 5: height = 1
- Node 2: left = 2, right = 1 → localDiameter = 4 → update
- Node 3: height = 1
- Node 1: left = 3, right = 1 → localDiameter = 5 → update

✅ Final diameter = 5 (path: 6 → 4 → 2 → 1 → 3)
*/


/*
Question :
The second last line in both the codes... diameter = max(diameter, lh+rh+1);

+1 because Yes we have got the lh and rh of that node but we need to include that node itself also.
Then only a whole path will be made.

Answer :
@VishalGupta-xw2rp  I understand where you're coming from but one important piece of information you may have missed is that the path does not
necessarily have to pass through the root node.

So in that case, we don't need the +1.
diameter = max(diameter, lh+rh) will also fine if question is saying no need of diameter to go through Root.
 */
