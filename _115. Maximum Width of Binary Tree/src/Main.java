import java.util.*;

public class Main {

    // 🌿 Node structure
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    // 🌐 Helper class to keep track of node and its position/index
    static class Pair {
        Node node;
        int index;

        Pair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    // 🚀 Function to calculate maximum width
    public static int widthOfBinaryTree(Node root) {
        if (root == null) return 0;

        int maxWidth = 0;

        // 🧺 Use Queue for level-order traversal (BFS)
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));  // 🌟 root has index 0

        while (!q.isEmpty()) {
            int size = q.size();
            int minIndex = q.peek().index; // 🟢 index of leftmost node at this level

            int first = 0, last = 0;  // To store index of first and last node at current level

            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();
                int curIndex = curr.index - minIndex; // Normalize to prevent overflow
                Node node = curr.node;

                if (i == 0) first = curIndex;  // 🔢 first node index
                if (i == size - 1) last = curIndex; // 🔚 last node index

                // 📥 Left child index = 2 * index
                if (node.left != null)
                    q.offer(new Pair(node.left, 2 * curIndex + 1));

                // 📤 Right child index = 2 * index + 1
                if (node.right != null)
                    q.offer(new Pair(node.right, 2 * curIndex + 2));
            }

            int width = last - first + 1;  // 📏 width at current level
            maxWidth = Math.max(maxWidth, width);  // ✅ update maxWidth
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        /*
                   1
                 /   \
               2       3
              /         \
             4           5

        Widths:
        Level 0:        1 → width = 1
        Level 1:     2     3 → width = 2
        Level 2:   4         5 → width = 4 (index 0 to 3)

        Expected Output: 4
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.right = new Node(5);

        System.out.println("Maximum Width of Binary Tree: " + widthOfBinaryTree(root)); // Output: 4
    }
}

/*
🧠 DRY RUN: On Tree

            1
          /   \
        2       3
       /         \
      4           5

📌 Indexing:
- Root has index 0
- For any node at index i:
    → left child = 2*i + 1
    → right child = 2*i + 2

Level 0:
  Queue = [(1, 0)]
  Width = 0 - 0 + 1 = 1

Level 1:
  Queue = [(2, 1), (3, 2)]
  Width = 2 - 1 + 1 = 2

Level 2:
  Queue = [(4, 3), (5, 6)]
  Width = 6 - 3 + 1 = 4 ✅ (max)

🎯 Final Output = 4

-----------------------------
📌 NOTES: Maximum Width of Binary Tree

▶ Approach:
- Use BFS level-order traversal.
- Track node **index** at each level.
- Width = last index - first index + 1 at that level

🕒 Time Complexity: O(N)
→ Each node visited once.

🧠 Space Complexity: O(N)
→ In worst case, queue stores all nodes at one level.

Why normalize index (curIndex = index - minIndex)?
→ Prevents integer overflow for deep trees.
*/
