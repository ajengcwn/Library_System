import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends FrameTemplate implements ActionListener
{
    private static int count = 0;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JPanel contentPanel = new JPanel();
    private JLabel logoLabel = new JLabel("Library System");

    public MainFrame()
    {
        new JFrame();

        setMenu();
        setLogoLabel();

        contentPanel.add(logoLabel);

        add(menuBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setMenu()
    {
        String menus[] = {"Borrow", "Return", "View History", "Log Out"};

        for(String item : menus)
        {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }

        menuBar.add(menu);
    }

    public void setLogoLabel()
    {
        ImageIcon image = new ImageIcon("images/logo.jpg");

        logoLabel.setFont(new FontUIResource("Arial", Font.PLAIN, 35));
        logoLabel.setIcon(image);
        logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        logoLabel.setIconTextGap(20);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public static void main(String[] args)
    {
        if(count == 0)
        {
            books.pushMid(23, new Book("Ajeng", 23, 3));
            books.pushMid(23, new Book("Alen", 23, 5));
            books.pushMid(43, new Book("Mirzha", 43, 1));
            books.pushMid(34, new Book("Yoh", 34, 7));
            count++;
            shelves = books.getKeys();
        }
    }

    JFrame frame = null;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JMenuItem menuItem)
        {
            this.dispose();
            String menu = menuItem.getText();

            if(frame != null)
            {
                return;
            }

            switch (menu)
            {
                case "Borrow":
                    frame = new BorrowFrame();
                break;

                case "Return":
                    frame = new ReturnFrame();
                break;

                case "View History":
                    frame = new ViewDataFrame();
                break;

                case "Log Out":
                    int log = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?");

                    //0 --> Yes
                    //1 --> No
                    //2 --> Cancel

                    if(log == 0)
                    {
                       this.dispose();
                       new LoginFrame();
                    } else
                    {
                        new MainFrame();
                    }

                break;
            }
        }
    }
}
