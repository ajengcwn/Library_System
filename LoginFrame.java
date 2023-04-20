package LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends FrameTemplate{
    public static void main(String[] args) {
        new LoginFrame();
    }
    private Container container;
    private JLabel loginLabel = new JLabel("LOGIN");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel nimLabel = new JLabel("NIM");
    public static JLabel nameError = new JLabel();
    public static JLabel nimError = new JLabel();
    public static JTextField nameTxt = new JTextField();
    public static JTextField nimTxt = new JTextField();
    public LoginFrame(){
        container = getContentPane();
        container.setLayout(null);

        container.add(new loginLabel());

        nameLabel.setFont(new Font("calibri", Font.PLAIN, 18));
        nameLabel.setSize(150, 50);
        nameLabel.setLocation(120, 210);
        container.add(nameLabel);

        nimLabel.setFont(new Font("calibri", Font.PLAIN, 18));
        nimLabel.setSize(150, 50);
        nimLabel.setLocation(120, 260);
        container.add(nimLabel);

        nameTxt.setFont(new Font("calibri", Font.PLAIN, 14));
        nameTxt.setSize(180, 30);
        nameTxt.setLocation(200, 220);
        container.add(nameTxt);

        nameError.setFont(new Font("calibri", Font.PLAIN, 12));
        nameError.setSize(300, 50);
        nameError.setLocation(200, 235);
        nameError.setForeground(Color.RED);
        container.add(nameError);

        nimTxt.setFont(new Font("calibri", Font.PLAIN, 14));
        nimTxt.setSize(180, 30);
        nimTxt.setLocation(200, 270);
        container.add(nimTxt);

        nimError.setFont(new Font("calibri", Font.PLAIN, 12));
        nimError.setSize(300, 50);
        nimError.setLocation(200, 285);
        nimError.setForeground(Color.RED);
        container.add(nimError);

        container.add(new submitBtn("SUBMIT"));

        setVisible(true);
    }
}

class loginLabel extends JLabel{
    ImageIcon image = new ImageIcon("D:\\PPTI\\Cawu 2\\Object Oriented Programming\\NYoba\\Self Learning\\Self Learning\\LibrarySystem\\images\\user_logo.png");
    Image scaledLogo = image.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
    ImageIcon scaledImage = new ImageIcon(scaledLogo);
    public loginLabel(){
        setIcon(scaledImage);
        setText("LOGIN");
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setFont(new Font("calibri", Font.BOLD, 25));
        setSize(250, 150);
        setIconTextGap(0);
        setLocation(200, 60);
    }
}

class submitBtn extends JButton implements ActionListener {
    int temp = 3;
    public submitBtn(String text){
        super(text);
        setFont(new Font("calibri", Font.PLAIN, 20));
        setSize(120, 40);
        setLocation(195, 330);
        setBackground(Color.LIGHT_GRAY);
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        checkError();
        validation();
    }
    private void dispose() {
        this.dispose();

    }
    public void checkError(){
        for(int i=0; i<LoginFrame.users.size(); i++){
            if (!LoginFrame.nameTxt.getText().equals(LoginFrame.users.get(i).getName())) {
                LoginFrame.nameError.setText("Please fill in with the registered name!");
            }
            if (!LoginFrame.nimTxt.getText().equals(FrameTemplate.users.get(i).getNim())) {
                LoginFrame.nimError.setText("Please fill in with the registered NIM!");
            }
            if (LoginFrame.nameTxt.getText().length() == 0) {
                LoginFrame.nameError.setText("Name must be filled!");
            }
            if (LoginFrame.nimTxt.getText().length() == 0) {
                LoginFrame.nimError.setText("NIM must be filled!");
            }
            else {
                LoginFrame.nameError.setText(null);
                LoginFrame.nimError.setText(null);
            }
        }
    }
    public void validation(){
        boolean check = false;
        for (int i=0; i< FrameTemplate.users.size(); i++) {
            if (LoginFrame.nameTxt.getText().equals(LoginFrame.users.get(i).getName()) &&
                    LoginFrame.nimTxt.getText().equals(LoginFrame.users.get(i).getNim())) {
                check = true;
                JOptionPane.showMessageDialog(null, "Login Successful!", "Validation", JOptionPane.INFORMATION_MESSAGE);
                new ActivityFrame();
                this.disable();
                JOptionPane.
            }
        }
        if(check == false) {
            if (temp <= 0){
                temp = 3;
            }
            temp--;
            JOptionPane.showMessageDialog(null,"Login Failed! \nPlease retry again (" + temp + ")", "Validation", JOptionPane.WARNING_MESSAGE);
        }
        if(temp == 0) {
            JOptionPane.showMessageDialog(null, "Careful! 3 Times Failed Input \nPlease input corectly!", "ERROR", JOptionPane.ERROR_MESSAGE);
            //back to main page yg ada logonya library
            //this.dispose();
        }
    }
}
