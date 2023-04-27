import java.util.ArrayList;

public class BST_StringKey<dataType>
{
    class Node
    {
        String key;
        dataType data;
        Node left;
        Node right;

        public Node(String key, dataType data)
        {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    int count;

    public BST_StringKey()
    {
        this.root = null;
        this.count = 0;
    }

    private Node pushMid(Node curr, String key, dataType data)
    {
        if(curr == null)
        {
            curr = new Node(key, data);
            count++;
        } else if(!key.equals(curr.key))
        {
            if(key.compareTo(curr.key) < 0)
            {
                curr.left = pushMid(curr.left, key, data);
            } else if(key.compareTo(curr.key) > 0)
            {
                curr.right = pushMid(curr.right, key, data);
            }
        }

        return curr;
    }

    public void pushMid(String key, dataType data)
    {
        root = pushMid(root, key, data);
    }

    private Node search(Node curr, String key)
    {
        if(curr == null)
        {
            //DO SOMETHING
        } else if(key.compareTo(curr.key) < 0)
        {
            return search(curr.left, key);
        } else if(key.compareTo(curr.key) > 0)
        {
            return search(curr.right, key);
        }

        return curr;
    }

    public dataType search(String key)
    {
        Node search = search(root, key);

        return (search != null) ? search.data : null;
    }
}