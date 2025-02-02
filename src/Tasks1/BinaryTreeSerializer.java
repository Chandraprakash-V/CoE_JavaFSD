package Tasks1;
public class BinaryTreeSerializer {

    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Serialisation function: Convert a binary tree to a string
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    // Helper function for pre-order traversal
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("X "); // 'X' represents null
            return;
        }

        sb.append(root.val).append(" "); // Append the node value
        serializeHelper(root.left, sb);  // Serialize left subtree
        serializeHelper(root.right, sb); // Serialize right subtree
    }

    // Deserialisation function: Convert a string back to a binary tree
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(" ");
        int[] index = {0}; // To track position during recursion
        return deserializeHelper(nodes, index);
    }

    // Helper function to deserialise the string back to a tree
    private TreeNode deserializeHelper(String[] nodes, int[] index) {
        if (index[0] >= nodes.length || nodes[index[0]].equals("X")) {
            index[0]++; // Skip 'X'
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodes[index[0]++]));
        node.left = deserializeHelper(nodes, index);  // Deserialize left subtree
        node.right = deserializeHelper(nodes, index); // Deserialize right subtree
        return node;
    }

    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Create an instance of BinaryTreeSerializer
        BinaryTreeSerializer serializer = new BinaryTreeSerializer();

        // Serialising the binary tree
        String serialized = serializer.serialize(root);
        System.out.println("Serialized Tree: " + serialized);

        // Deserialising the string back to a binary tree
        TreeNode deserializedRoot = serializer.deserialize(serialized);
        System.out.println("Deserialized Root Value: " + deserializedRoot.val);
    }
}
