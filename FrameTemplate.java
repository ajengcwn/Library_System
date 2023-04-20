import javax.swing.*;
import java.util.ArrayList;

public class FrameTemplate extends JFrame
{
    static protected ArrayList<User> users = new ArrayList<>();
    static protected ArrayList<Book> books = new ArrayList<>();
    static protected ArrayList<Integer> shelves = new ArrayList<>();
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
