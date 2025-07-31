import java.util.*;

public class Main {

    // 🌿 Node class for binary tree
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
        }
    }

    // 📌 Function to create parent map and find the target node
    static Node mapParents(Node root, Map<Node, Node> parentMap, int target, Node[] targetNode) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            // If current node matches the target value
            if (curr.data == target) {
                targetNode[0] = curr;
            }

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }
        return targetNode[0];
    }

    // 🔥 Function to calculate minimum time to burn the tree from target node
    static int burnTree(Node root, int target) {
        Map<Node, Node> parentMap = new HashMap<>(); // child -> parent
        Node[] targetNode = new Node[1]; // to store target node reference

        // 📦 Step 1: Map parents and get the target node
        Node targetRoot = mapParents(root, parentMap, target, targetNode);

        // 📦 Step 2: Start BFS from target node
        Queue<Node> q = new LinkedList<>();
        Map<Node, Boolean> visited = new HashMap<>();

        q.add(targetRoot);
        visited.put(targetRoot, true);
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean burnedAny = false;

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                // 🔥 Try to burn left child
                if (curr.left != null && !visited.containsKey(curr.left)) {
                    burnedAny = true;
                    visited.put(curr.left, true);
                    q.add(curr.left);
                }

                // 🔥 Try to burn right child
                if (curr.right != null && !visited.containsKey(curr.right)) {
                    burnedAny = true;
                    visited.put(curr.right, true);
                    q.add(curr.right);
                }

                // 🔥 Try to burn parent
                if (parentMap.containsKey(curr) && !visited.containsKey(parentMap.get(curr))) {
                    burnedAny = true;
                    visited.put(parentMap.get(curr), true);
                    q.add(parentMap.get(curr));
                }
            }

            if (burnedAny) time++; // ⏱️ Increment time only if fire spreads
        }

        return time;
    }

    public static void main(String[] args) {
        /*
                  1
                /   \
               2     3
              / \     \
             4   5     7
                        \
                         8

        Suppose target = 5
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);

        int target = 5;
        int time = burnTree(root, target);
        System.out.println("🔥 Minimum time to burn the tree: " + time);
    }
}

/*
🧪 DRY RUN: Burn Binary Tree from Node 5

Binary Tree:
              1
            /   \
           2     3
          / \     \
         4   5     7
                    \
                     8

🎯 Start burning from Node 5

Step 1️⃣: Build parent mapping using BFS
  parentMap:
    2 -> 1
    3 -> 1
    4 -> 2
    5 -> 2
    7 -> 3
    8 -> 7
  targetNode = Node with value 5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 2️⃣: Start BFS from targetNode (5)
We'll simulate fire spread by levels (time units):

⏱️ time = 0
queue: [5]
visited: {5}

📍 At time = 0:
- Burn node 5
  → left = null
  → right = null
  → parent = 2 → Add to queue ✅
newQueue = [2]
visited = {5, 2}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 1
queue: [2]
visited: {5, 2}

📍 At time = 1:
- Burn node 2
  → left = 4 → Add to queue ✅
  → right = 5 (already visited)
  → parent = 1 → Add to queue ✅
newQueue = [4, 1]
visited = {5, 2, 4, 1}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 2
queue: [4, 1]
visited: {5, 2, 4, 1}

📍 At time = 2:
- Burn node 4 → no children, skip
- Burn node 1
  → left = 2 (already visited)
  → right = 3 → Add to queue ✅
newQueue = [3]
visited = {5, 2, 4, 1, 3}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 3
queue: [3]
visited: {5, 2, 4, 1, 3}

📍 At time = 3:
- Burn node 3
  → left = null
  → right = 7 → Add to queue ✅
  → parent = 1 (already visited)
newQueue = [7]
visited = {5, 2, 4, 1, 3, 7}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 4
queue: [7]
visited: {5, 2, 4, 1, 3, 7}

📍 At time = 4:
- Burn node 7
  → right = 8 → Add to queue ✅
  → left = null
  → parent = 3 (already visited)
newQueue = [8]
visited = {5, 2, 4, 1, 3, 7, 8}
burnedAny = true → time++

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⏱️ time = 5
queue: [8]
visited: {5, 2, 4, 1, 3, 7, 8}

📍 At time = 5:
- Burn node 8 → no children
  → parent = 7 (already visited)
newQueue = []
No nodes left to burn → fire stops

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Final Answer:
🔥 Minimum time to burn tree from node 5 = **5 units**

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📈 Time Complexity = O(N) → every node is visited once
📦 Space Complexity = O(N) → parent map + visited map + queue
*/
