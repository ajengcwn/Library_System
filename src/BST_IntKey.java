import java.util.ArrayList;

public class BST_IntKey<dataType>
{
    class Node
    {
        Integer key;
        ArrayList<dataType> datas = new ArrayList<>();
        Node left;
        Node right;

        public Node(Integer key, dataType data)
        {
            this.key = key;
            this.datas.add(data);
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    int count;

    public BST_IntKey()
    {
        this.root = null;
        this.count = 0;
    }

    private Node pushMid(Node curr, Integer key, dataType data)
    {
        if(curr == null)
        {
            curr = new Node(key, data);
            count++;
        } else if(key != curr.key)
        {
            if(key < curr.key)
            {
                curr.left = pushMid(curr.left, key, data);
            } else if(key > curr.key)
            {
                curr.right = pushMid(curr.right, key, data);
            }
        } else
        {
            curr.datas.add(data);
        }

        return curr;
    }

    public void pushMid(Integer key, dataType data)
    {
        root = pushMid(root, key, data);
    }

    private Node search(Node curr, Integer key)
    {
        if(curr == null)
        {
            //DO SOMETHING
        } else if(key < curr.key)
        {
            return search(curr.left, key);
        } else if(key > curr.key)
        {
            return search(curr.right, key);
        }

        return curr;
    }

    public ArrayList<dataType> search(Integer key)
    {
        Node search = search(root, key);

        return (search != null) ? search.datas : null;
    }

    public int getCount()
    {
        return this.count;
    }

    private ArrayList<Integer> getKeys(Node curr, ArrayList<Integer> keys)
    {
        if(curr != null)
        {
            getKeys(curr.left, keys);
            keys.add(curr.key);
            getKeys(curr.right, keys);
        }

        return keys;
    }

    public ArrayList<Integer> getKeys()
    {
        return getKeys(root, new ArrayList<>());
    }
}