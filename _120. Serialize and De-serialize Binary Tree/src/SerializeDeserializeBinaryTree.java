import java.util.*;

public class SerializeDeserializeBinaryTree {

    // 🌳 Node class representing each element in the Binary Tree
    static class Node {
        int val;          // value stored in node
        Node left, right; // left and right child pointers

        Node(int x) {
            val = x;
        }
    }

    // 🔐 SERIALIZE FUNCTION: Converts Binary Tree into a comma-separated String
    public static String serialize(Node root) {
        // 🛑 If tree is empty, return empty string
        if (root == null) return "";

        // 🌐 Use queue for Level Order Traversal (BFS)
        Queue<Node> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder(); // to build the result string

        // 🔁 Start from root
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // 🟢 Remove current node

            if (node == null) {
                sb.append("null,"); // ⛔ Mark null child explicitly
                continue;
            }

            // ✅ Append current node’s value
            sb.append(node.val).append(",");

            // 👈 Add left and 👉 right children (can be null)
            queue.add(node.left);
            queue.add(node.right);
        }

        // 🧹 Remove the trailing comma at the end
        sb.setLength(sb.length() - 1);

        // 🔁 Return final serialized string
        return sb.toString();
    }

    // 🔓 DESERIALIZE FUNCTION: Converts String back to Binary Tree
    public static Node deserialize(String data) {
        // 🛑 Edge case: if string is empty, return null
        if (data.isEmpty()) return null;

        // 🔍 Split the input string by commas to get each node value
        String[] values = data.split(",");

        // 🌱 The first value is always the root
        Node root = new Node(Integer.parseInt(values[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int i = 1; // 📌 Index for scanning through the values[]

        // 🔁 Level-order reconstruction
        while (!queue.isEmpty() && i < values.length) {
            Node curr = queue.poll(); // Current parent node

            // 👈 Create left child if not "null"
            if (!values[i].equals("null")) {
                curr.left = new Node(Integer.parseInt(values[i]));
                queue.add(curr.left); // Add left child to queue
            }
            i++; // Move to next value

            // 👉 Create right child if exists and not "null"
            if (i < values.length && !values[i].equals("null")) {
                curr.right = new Node(Integer.parseInt(values[i]));
                queue.add(curr.right); // Add right child to queue
            }
            i++; // Move to next value
        }

        // 🌳 Return root of reconstructed tree
        return root;
    }

    // 🖨️ Utility function to print tree in Inorder traversal for verification
    public static void printInorder(Node root) {
        if (root == null) return;

        printInorder(root.left);       // 🔽 Traverse left
        System.out.print(root.val + " "); // 🎯 Print node
        printInorder(root.right);      // 🔼 Traverse right
    }

    // 🧪 MAIN FUNCTION TO TEST
    public static void main(String[] args) {
        /*
               1
              / \
             2   3
                / \
               4   5
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        // 🔐 Convert tree to string
        String serialized = serialize(root);
        System.out.println("🔐 Serialized Tree: " + serialized);

        // 🔓 Convert string back to tree
        Node deserializedRoot = deserialize(serialized);

        // 🔍 Print inorder traversal of reconstructed tree
        System.out.print("📤 Inorder of Deserialized Tree: ");
        printInorder(deserializedRoot); // Should match original tree
    }
}

/*
🎯 Goal: Convert Binary Tree → String using Level Order (BFS)

Initial Tree:
        1
       / \
      2   3
         / \
        4   5

Queue: [1]
StringBuilder: ""

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll 1 → Append 1 to result
→ Add left = 2, right = 3 to queue

Queue: [2, 3]
Result: "1,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll 2 → Append 2 to result
→ Add left = null, right = null

Queue: [3, null, null]
Result: "1,2,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll 3 → Append 3 to result
→ Add left = 4, right = 5

Queue: [null, null, 4, 5]
Result: "1,2,3,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll null → Append "null"

Queue: [null, 4, 5]
Result: "1,2,3,null,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll null → Append "null"

Queue: [4, 5]
Result: "1,2,3,null,null,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll 4 → Append 4
→ Add left = null, right = null

Queue: [5, null, null]
Result: "1,2,3,null,null,4,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll 5 → Append 5
→ Add left = null, right = null

Queue: [null, null, null, null]
Result: "1,2,3,null,null,4,5,"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 Poll null → Append "null"
🔁 Poll null → Append "null"
🔁 Poll null → Append "null"
🔁 Poll null → Append "null"

Queue: []
Result: "1,2,3,null,null,4,5,null,null,null,null"

✅ Final Serialized Output:
"1,2,3,null,null,4,5,null,null,null,null"
*/


/*
🎯 Goal: Convert Serialized String → Tree (Level Order)

Input:
"1,2,3,null,null,4,5,null,null,null,null"

values = [1,2,3,null,null,4,5,null,null,null,null]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 1:
➤ Create root = Node(1)
➤ Queue = [1]
i = 1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 2:
➤ Poll 1 from queue
➤ values[1] = 2 → left = Node(2)
➤ values[2] = 3 → right = Node(3)
Queue = [2, 3]
i = 3

Tree so far:
        1
       / \
      2   3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 3:
➤ Poll 2 from queue
➤ values[3] = null → left = null
➤ values[4] = null → right = null
Queue = [3]
i = 5

Tree so far:
        1
       / \
      2   3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 4:
➤ Poll 3 from queue
➤ values[5] = 4 → left = Node(4)
➤ values[6] = 5 → right = Node(5)
Queue = [4, 5]
i = 7

Tree so far:
        1
       / \
      2   3
         / \
        4   5

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Step 5:
➤ Poll 4 → values[7,8] = null → both children = null
➤ Poll 5 → values[9,10] = null → both children = null

Queue: []
i = 11 (Done)

✅ Final Reconstructed Tree:
        1
       / \
      2   3
         / \
        4   5
*/


