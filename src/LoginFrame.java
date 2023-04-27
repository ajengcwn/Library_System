import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends FrameTemplate implements ActionListener{
    private Container container;
    private JLabel loginLabel = new JLabel("LOGIN");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel nimLabel = new JLabel("NIM");
    public static JLabel nameError = new JLabel();
    public static JLabel nimError = new JLabel();
    public static JTextField nameTxt = new JTextField();
    public static JTextField nimTxt = new JTextField();
    private int temp;

    public LoginFrame(){
        container = getContentPane();
        container.setLayout(null);

        setLabels();
        setTextFields();
        setErrorLabels();

        container.add(loginLabel);
        container.add(nameLabel);
        container.add(nimLabel);
        container.add(nameTxt);
        container.add(nameError);
        container.add(nimTxt);
        container.add(nimError);

        submitBtn.setSize(120, 40);
        submitBtn.setLocation(195, 330);
        submitBtn.addActionListener(this);
        container.add(submitBtn);

        temp = 3;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setLabels()
    {
        ImageIcon image = new ImageIcon("D:\\PPTI\\Cawu 2\\Object Oriented Programming\\NYoba\\Self Learning\\Self Learning\\LibrarySystem\\images\\user_logo.png");
        Image scaledLogo = image.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(scaledLogo);

        loginLabel.setIcon(scaledImage);
        loginLabel.setHorizontalTextPosition(JLabel.CENTER);
        loginLabel.setVerticalTextPosition(JLabel.BOTTOM);
        loginLabel.setFont(new Font("calibri", Font.BOLD, 25));
        loginLabel.setSize(250, 150);
        loginLabel.setIconTextGap(0);
        loginLabel.setLocation(200, 60);

        nimLabel.setFont(new Font("calibri", Font.PLAIN, 18));
        nimLabel.setSize(150, 50);
        nimLabel.setLocation(120, 210);

        nameLabel.setFont(new Font("calibri", Font.PLAIN, 18));
        nameLabel.setSize(150, 50);
        nameLabel.setLocation(120, 260);
    }

    private void setTextFields()
    {
        nimTxt.setFont(new Font("calibri", Font.PLAIN, 14));
        nimTxt.setSize(180, 30);
        nimTxt.setLocation(200, 220);

        nameTxt.setFont(new Font("calibri", Font.PLAIN, 14));
        nameTxt.setSize(180, 30);
        nameTxt.setLocation(200, 270);
    }

    private void setErrorLabels()
    {
        nimError.setFont(new Font("calibri", Font.PLAIN, 12));
        nimError.setSize(300, 50);
        nimError.setLocation(200, 235);
        nimError.setForeground(Color.RED);

        nameError.setFont(new Font("calibri", Font.PLAIN, 12));
        nameError.setSize(300, 50);
        nameError.setLocation(200, 285);
        nameError.setForeground(Color.RED);
    }

    public static void main(String[] args)
    {
        users.pushMid("123456", new User("user1", "123456"));
        users.pushMid("678910", new User("user2", "678910"));

        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        validation();
    }

    public boolean checkError()
    {
        user = users.search(nimTxt.getText());

        if(user == null)
        {
            nimError.setText("Please fill in with the registered NIM!");
            return false;
        } else
        {
            nimError.setText(null);

            if(!nameTxt.getText().equals(user.getName()))
            {
                LoginFrame.nameError.setText("Please fill in with the registered name!");
                return false;
            } else
            {
                nameError.setText(null);
            }
        }

        return true;
    }
    public void validation()
    {
        boolean validNIM = checkTxt(nimTxt);
        boolean validName = checkTxt(nameTxt);

        if(validNIM & validName)
        {
            boolean log = checkError();

            if (log == true) {

                JOptionPane.showMessageDialog(null, "Login Successful!", "Validation", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new MainFrame();
                MainFrame.main(new String[]{});
            } else
            {
                if (temp <= 0){
                    temp = 3;
                }
                temp--;
                JOptionPane.showMessageDialog(null,"Login Failed! \nPlease retry again (" + temp + ")", "Validation", JOptionPane.WARNING_MESSAGE);

                if(temp == 0) {
                    JOptionPane.showMessageDialog(null, "Careful! 3 Times Failed Input \nPlease input corectly!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "Please fill out every field!");
        }
    }
}