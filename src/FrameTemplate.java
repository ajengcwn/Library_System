import javax.swing.*;
import java.util.ArrayList;

public class FrameTemplate extends JFrame
{
    static protected BST_StringKey<User> users = new BST_StringKey<>();
    static protected BST_IntKey<Book> books = new BST_IntKey<>();
    static protected ArrayList<Integer> shelves;
    static protected User user;
    ImageIcon image = new ImageIcon("images/logo.jpg");
    JButton backBtn = new JButton("Back");
    JButton submitBtn = new JButton("Submit");
    public FrameTemplate()
    {
        setTitle("Library System");
        setIconImage(image.getImage());
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public boolean checkComboBox(JComboBox comboBox)
    {
        if(comboBox.getSelectedItem() == null)
        {
            return false;
        }

        return true;
    }

    public boolean checkTxt(JFormattedTextField txtField)
    {
        if(txtField.getText().equals(""))
        {
            return false;
        }

        return true;
    }

    public boolean checkTxt(JTextField txtField)
    {
        if(txtField.getText().equals(""))
        {
            return false;
        }

        return true;
    }
}
