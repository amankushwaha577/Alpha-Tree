import java.util.*;

public class Main {

    // 🌿 Node class
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
        }
    }

    // 📦 Pair class to hold Node and its horizontal distance (HD)
    static class Pair {
        Node node;
        int hd;
        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    // 🌐 Function to perform vertical order traversal
    public static void verticalOrder(Node root) {
        if (root == null) return;

        // 🔀 TreeMap to store nodes by horizontal distance (hd)
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(root, 0)); // 🌟 root is at horizontal distance 0

        while (!q.isEmpty()) {
            Pair curr = q.remove();
            Node node = curr.node;
            int hd = curr.hd;

            // 📥 Add node value to map at its hd
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.data);

            // ⬅️ Move to left child (hd - 1)
            if (node.left != null) {
                q.add(new Pair(node.left, hd - 1));
            }

            // ➡️ Move to right child (hd + 1)
            if (node.right != null) {
                q.add(new Pair(node.right, hd + 1));
            }
        }

        // 🖨️ Print vertical order traversal
        for (List<Integer> level : map.values()) {
            for (int val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*
               1
             /   \
            2     3
           / \   / \
          4   5 6   7
                   /
                  8
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);

        System.out.println("Vertical Order Traversal:");
        verticalOrder(root);
    }
}

/*
🧪 DRY RUN: Vertical Order Traversal (BFS with horizontal distance)

Example Tree:
             1
           /   \
         2       3
        / \     / \
       4   5   6   7
                  /
                 8

▶ Initial Setup:
- TreeMap: hd → List of nodes at that horizontal distance
- Queue: stores Pair(node, hd)

📦 Start with:
Queue = [(1, 0)]
Map = {}

🔁 Step-by-step Traversal:

1️⃣ Pop (1, 0)
   ➤ Add 1 to hd 0 → map = {0: [1]}
   ➤ Add (2, -1) and (3, 1) to queue
Queue = [(2, -1), (3, 1)]

2️⃣ Pop (2, -1)
   ➤ Add 2 to hd -1 → map = {-1: [2], 0: [1]}
   ➤ Add (4, -2) and (5, 0)
Queue = [(3, 1), (4, -2), (5, 0)]

3️⃣ Pop (3, 1)
   ➤ Add 3 to hd 1 → map = {-1: [2], 0: [1], 1: [3]}
   ➤ Add (6, 0) and (7, 2)
Queue = [(4, -2), (5, 0), (6, 0), (7, 2)]

4️⃣ Pop (4, -2)
   ➤ Add 4 to hd -2 → map = {-2: [4], -1: [2], 0: [1], 1: [3]}
Queue = [(5, 0), (6, 0), (7, 2)]

5️⃣ Pop (5, 0)
   ➤ Add 5 to hd 0 → map = {-2: [4], -1: [2], 0: [1, 5], 1: [3]}
Queue = [(6, 0), (7, 2)]

6️⃣ Pop (6, 0)
   ➤ Add 6 to hd 0 → map = {-2: [4], -1: [2], 0: [1, 5, 6], 1: [3]}
Queue = [(7, 2)]

7️⃣ Pop (7, 2)
   ➤ Add 7 to hd 2 → map = {-2: [4], -1: [2], 0: [1, 5, 6], 1: [3], 2: [7]}
   ➤ Add (8, 1)
Queue = [(8, 1)]

8️⃣ Pop (8, 1)
   ➤ Add 8 to hd 1 → map = {..., 1: [3, 8]}
Queue = []

📤 Final TreeMap (Vertical Order):
- hd -2 → [4]
- hd -1 → [2]
- hd  0 → [1, 5, 6]
- hd  1 → [3, 8]
- hd  2 → [7]

🎯 Final Output (Left → Right by HD):
4
2
1 5 6
3 8
7

*/


/*
🧠 SHORT NOTES: Vertical Order Traversal (BFS + Map)

📌 Goal:
Print nodes column-wise from left to right.

📘 Concept:
- Use BFS (level order) to ensure top-down order in each column
- Track horizontal distance (hd) of each node:
   ➤ Root has hd = 0
   ➤ Left child → hd - 1
   ➤ Right child → hd + 1
- Use TreeMap<hd, List> to group nodes by column (auto-sorted)

🛠️ Steps:
1. Use a Queue to store (Node, hd)
2. Start with root at hd = 0
3. While queue is not empty:
   - Pop node, add to TreeMap at current hd
   - Add left/right children with hd - 1 / hd + 1
4. Print map values in sorted hd order (left to right)

📦 Data Structures:
- TreeMap<Integer, List<Integer>> → for vertical grouping
- Queue<Pair<Node, hd>> → for BFS traversal

🕒 Time: O(N log N) → due to TreeMap operations
🧠 Space: O(N) → for Queue and TreeMap

🎯 Output: Nodes grouped by vertical column, top to bottom
*/
