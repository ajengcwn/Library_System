import javax.swing.*;
import java.util.ArrayList;

public class FrameTemplate extends JFrame
{
    static protected ArrayList<User> users = new ArrayList<>();
    ImageIcon image = new ImageIcon("images/logo.jpg");
    public FrameTemplate()
    {
        setTitle("Library System");
        setIconImage(image.getImage());
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
