import java.util.*;

public class Main {

    // Node class representing each element in the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

        /*
      🔁 Strategy: Zig-Zag (Spiral) Level Order Traversal
      1. Use a queue for standard level-order traversal.
      2. Keep a boolean flag `leftToRight` to track the printing direction.
      3. For each level:
         - Process all nodes currently in the queue (this is one level).
         - If `leftToRight` is true → append node values at the end of the list.
         - If false → insert node values at the beginning (reverses order).
         - Add left and right children to the queue for the next level.
      4. Toggle `leftToRight` after each level to switch direction.
      5. Continue until queue is empty (all levels processed).
      */
        public static List<List<Integer>> zigZagTraversal(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        // If tree is empty, return empty result
        if (root == null) return result;

        // Queue for standard level-order traversal
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        // Flag to track direction of traversal for each level
        boolean leftToRight = true;

        // Process nodes level by level
        while (!q.isEmpty()) {
            int size = q.size(); // Number of nodes at current level
            List<Integer> level = new ArrayList<>(size); // Holds nodes for this level

            // Process each node of the current level
            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                // Depending on the direction, add node data at end or at start
                if (leftToRight) {
                    level.add(node.data);
                } else {
                    level.add(0, node.data); // Insert at start for reverse order
                }

                // Add left and right children to queue for next level
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            // Store the processed level into the result list
            result.add(level);

            // Flip the direction for the next level
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static void main(String[] args) {
        // Constructing the binary tree:
        /*
                 1
               /   \
              2     3
             / \   /
            4   5 6
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);

        // Perform zig-zag traversal and print each level
        List<List<Integer>> ans = zigZagTraversal(root);
        for (List<Integer> level : ans) {
            System.out.println(level);
        }
    }
}

/*
------------------------------------------------------------
🧠 Time & Space Complexity:

🔸 Time Complexity: O(N) — Every node is visited exactly once.
🔸 Space Complexity: O(N) — Queue stores nodes of one level at most, plus output list.

------------------------------------------------------------
💡 Why not use two stacks?
- Two-stack approach works but is slightly more complex to manage.
- Queue + direction flag is cleaner and easier to maintain.

------------------------------------------------------------
📌 Key Points:
- Direction alternates after each level.
- `level.add(0, value)` ensures reverse order for alternate levels.
- Queue enables simple level-order traversal.

✅ Easy to understand
✅ No extra data structure beyond queue and level list

------------------------------------------------------------
📊 Dry Run Example:

Tree:
        1
      /   \
     2     3
    / \   /
   4   5 6

Step-by-step:
Level 1 (L→R): [1]
Level 2 (R→L): [3, 2]
Level 3 (L→R): [4, 5, 6]

Final Output: [[1], [3, 2], [4, 5, 6]]
------------------------------------------------------------
*/


 /*
    🧪 Dry Run Example:
    Tree:
             1
           /   \
          2     3
         / \   /
        4   5 6

    Initial:
    Queue = [1], leftToRight = true, result = []

    Level 0:
    - Process: 1 → add to level list → [1]
    - Enqueue children: 2, 3
    - result = [[1]]
    - Toggle direction → leftToRight = false

    Level 1:
    - Process: 2 → insert at start → [2]
      Process: 3 → insert at start → [3, 2]
    - Enqueue children: 4, 5, 6
    - result = [[1], [3, 2]]
    - Toggle direction → leftToRight = true

    Level 2:
    - Process: 4 → add at end → [4]
      Process: 5 → add at end → [4, 5]
      Process: 6 → add at end → [4, 5, 6]
    - No more children to enqueue
    - result = [[1], [3, 2], [4, 5, 6]]
    - Queue empty → stop

    ✅ Final Output:
    [[1], [3, 2], [4, 5, 6]]
    */