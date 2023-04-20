package LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityFrame extends FrameTemplate implements ActionListener {
    private Container container;
    private JButton borrowBtn = new JButton("Borrow Book");
    private JButton returnBtn = new JButton("Borrowing Book Data");
    private JButton borrowingDataBtn = new JButton("Return Book");

//    public static void main(String[] args) {
//        new ActivityFrame();
//    }

    public ActivityFrame() {
        container = getContentPane();
        container.setLayout(null);

        container.add(new message());

        borrowBtn.setFont(new Font("calibri", Font.PLAIN, 19));
        borrowBtn.setSize(200, 35);
        borrowBtn.setLocation(150, 230);
        borrowBtn.setBackground(Color.LIGHT_GRAY);
        borrowBtn.addActionListener(this);
        container.add(borrowBtn);

        borrowingDataBtn.setFont(new Font("calibri", Font.PLAIN, 19));
        borrowingDataBtn.setSize(200, 35);
        borrowingDataBtn.setLocation(150, 280);
        borrowingDataBtn.setBackground(Color.LIGHT_GRAY);
        borrowingDataBtn.addActionListener(this);
        container.add(borrowingDataBtn);

        returnBtn.setFont(new Font("calibri", Font.PLAIN, 18));
        returnBtn.setSize(200, 35);
        returnBtn.setLocation(150, 330);
        returnBtn.setBackground(Color.LIGHT_GRAY);
        returnBtn.addActionListener(this);
        container.add(returnBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == borrowBtn) {
            //new borrowFrame();
        } else if (e.getSource() == returnBtn) {
            //new returnFrame();
        } else if (e.getSource() == borrowingDataBtn) {
            //new viewBorrowingFrame();
        }
    }
}

class message extends JLabel{
    ImageIcon image = new ImageIcon("D:\\PPTI\\Cawu 2\\Object Oriented Programming\\NYoba\\Self Learning\\Self Learning\\LibrarySystem\\images\\welcome_logo.png");
    Image scaledLogo = image.getImage().getScaledInstance(95,95, Image.SCALE_SMOOTH);
    ImageIcon scaledImage = new ImageIcon(scaledLogo);
    public message(){
        setIcon(scaledImage);
        setText("<html>Choose which library activities" +
                "<br><center>you want to do!<html>");
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setFont(new Font("calibri", Font.PLAIN, 18));
        setSize(250, 160);
        setIconTextGap(0);
        setLocation(140, 60);
    }
}


