import java.util.*;

public class Main {

    // 🔁 This function performs Vertical Order Traversal
    public static List<Integer> verticalOrder(Node root) {

        // 📌 If tree is empty, return empty list
        if (root == null) return new ArrayList<>();

        // 🗺️ TreeMap will store: HD -> list of node values at that HD
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        // Initially: map = { }  // (empty, as no node is processed yet)

        // 🧺 Queue used for BFS traversal (stores Pair<Node, HD>)
        Queue<Pair> q = new LinkedList<>();
        // Initially: q = [ (1, 0) ]  // Root node 1 is at horizontal distance 0


        // 🌱 Start from root with horizontal distance = 0
        q.add(new Pair(root, 0));

        // 🔄 Do level-order traversal (BFS)
        while (!q.isEmpty()) {
            Pair curr = q.poll();          // 🔄 Remove front element from queue
            Node node = curr.node;        // Current node
            int hd = curr.hd;             // Horizontal distance of current node

            // 💾 Add node value to the list for that horizontal distance
            map.putIfAbsent(hd, new ArrayList<>()); // If no list exists for hd, create one
            map.get(hd).add(node.data);             // Add node to appropriate list

            // 👈 If node has left child, add it with hd - 1
            if (node.left != null)
                q.add(new Pair(node.left, hd - 1));

            // 👉 If node has right child, add it with hd + 1
            if (node.right != null)
                q.add(new Pair(node.right, hd + 1));
        }

        // 📦 Now flatten the TreeMap into a single list in order
        List<Integer> result = new ArrayList<>();
        for (List<Integer> level : map.values()) {
            result.addAll(level);  // Add all elements of each vertical level into result
        }

        return result;  // ✅ Final vertical order list (flattened)
    }

    public static void main(String[] args) {

        /*
         Constructed Binary Tree:

                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
                   /
                  8

        Horizontal Distances (HD):
        HD = -2 → 4
        HD = -1 → 2
        HD =  0 → 1, 5, 6
        HD =  1 → 3, 8
        HD =  2 → 7

        So vertical order output will be:
        4 2 1 5 6 3 8 7
        */

        // 🌳 Create tree nodes
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);

        // 🚀 Run vertical order traversal
        List<Integer> output = verticalOrder(root);

        // 🖨️ Print the result
        System.out.println("Vertical Order Traversal (Flattened):");
        for (int val : output) {
            System.out.print(val + " ");  // Expected: 4 2 1 5 6 3 8 7
        }
    }

    // 🌿 Node class – Represents each node in the binary tree
    static class Node {
        int data;      // Value of node
        Node left;     // Left child
        Node right;    // Right child

        Node(int data) {
            this.data = data;
        }
    }

    // 📦 Pair class to keep node + horizontal distance (hd) together during traversal
    static class Pair {
        Node node;  // The current tree node
        int hd;     // Horizontal Distance from root

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
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
