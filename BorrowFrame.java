import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BorrowFrame extends FrameTemplate implements ActionListener, FocusListener, KeyListener
{
    private JPanel headerPanel = new JPanel();
    private JLabel borrowLabel = new JLabel("Borrow Books");
    private JPanel contentPanel = new JPanel(new GridLayout(4, 2));
    private ArrayList<JLabel> jLabels = new ArrayList<>();
    private JComboBox<Integer> shelfDropDown = new JComboBox<>();
    private JComboBox<String> titleDropDown = new JComboBox<>();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTxtTemplate borrowDateTxt = new DateTxtTemplate();
    private JFormattedTextField maxReturnDateTxt = new JFormattedTextField();
    private JPanel footerPanel = new JPanel();

    BorrowFrame()
    {
        setBorrowLabel();

        backBtn.addActionListener(this);

        setHeaderPanel();
        setFieldLabels();
        setShelfDropDown();
        setTextFields();
        setContentPanel();

        submitBtn.addActionListener(this);
        footerPanel.add(submitBtn);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setSize(500, 300);
        setVisible(true);
    }

    private void setBorrowLabel()
    {
        borrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        borrowLabel.setFont(new Font("Arial", Font.BOLD, 20));
        borrowLabel.setBorder(new EmptyBorder(0, 0, 0, 90));
    }

    private void setHeaderPanel()
    {
        headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setSize(500, 100);
        headerPanel.add(borrowLabel);
        headerPanel.add(backBtn);
    }

    private void setFieldLabels()
    {
        Font labelFont = new Font("Arial", Font.PLAIN, 15);

        String[] labels = {"Shelf Number", "Book Title", "Borrow Date", "Max Return Date"};
        for(String text : labels)
        {
            JLabel label = new JLabel(text);
            label.setFont(labelFont);
            label.setHorizontalAlignment(SwingConstants.LEFT);
            jLabels.add(new JLabel(text));
        }
    }

    private void setShelfDropDown()
    {
        for(int i : shelves)
        {
            shelfDropDown.addItem(i);
        }

        shelfDropDown.addActionListener(this);
        shelfDropDown.setSelectedItem(null);
    }

    private void setTextFields()
    {
        try {
            borrowDateTxt.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        borrowDateTxt.setText("23/12/2023");
        borrowDateTxt.addFocusListener(this);
        borrowDateTxt.addKeyListener(this);

        maxReturnDateTxt.setEditable(false);
    }

    private void setContentPanel()
    {
        contentPanel.add(jLabels.get(0));
        contentPanel.add(shelfDropDown);
        contentPanel.add(jLabels.get(1));
        contentPanel.add(titleDropDown);
        contentPanel.add(jLabels.get(2));
        contentPanel.add(borrowDateTxt);
        contentPanel.add(jLabels.get(3));
        contentPanel.add(maxReturnDateTxt);

        contentPanel.setSize(400, 100);
        contentPanel.setBorder(new EmptyBorder(20, 90, 50, 90));
    }

    public static void main(String[] args)
    {
        new BorrowFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitBtn)
        {
            boolean validShelf = this.checkComboBox(shelfDropDown);
            boolean validTitle = this.checkComboBox(titleDropDown);
            boolean validBorrowDate = this.checkTxt(borrowDateTxt);
            boolean validMaxReturnDate = this.checkTxt(maxReturnDateTxt);

            if(validShelf && validTitle && validBorrowDate && validMaxReturnDate)
            {
                user.borrowingData.add(new BorrowingData(books.get(titleDropDown.getSelectedIndex()),
                                           BorrowingData.getDate(borrowDateTxt, dateFormat),
                                           BorrowingData.getDate(maxReturnDateTxt, dateFormat)));

                JOptionPane.showMessageDialog(this, "Borrowing Data is successfully recorded!");

                this.dispose();

                new BorrowFrame();
            } else
            {
                JOptionPane.showMessageDialog(this, "Please fill out every field!");
            }
        } else if(e.getSource() == backBtn)
        {
            this.dispose();
            new MainFrame();
        } else if(e.getSource() == shelfDropDown)
        {
            setTitleDropDown();
        }
    }

    private void setTitleDropDown()
    {
        for(Book book : books)
        {
            if(book.getShelf() == shelfDropDown.getSelectedItem())
            {
                titleDropDown.addItem(book.getTitle());
            }
        }

        titleDropDown.setSelectedItem(null);
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        if(e.getSource() == borrowDateTxt)
        {
            if(borrowDateTxt.getText().equals("23/12/2023"))
            {
                borrowDateTxt.setText("");
                //Sets the component to "onfocus"
                borrowDateTxt.requestFocus();
                borrowDateTxt.removePlaceHolderStyle(borrowDateTxt);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if(e.getSource() == borrowDateTxt)
        {
            if(borrowDateTxt.getText().contains(" "))
            {
                borrowDateTxt.setText("23/12/2023");
                borrowDateTxt.addPlaceHolderStyle(borrowDateTxt);
            } else
            {
                maxReturnDateTxt.setText(BorrowingData.calculateMaxReturnDate(borrowDateTxt, dateFormat));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 10) //Enter
        {
            if(e.getSource() == borrowDateTxt)
            {
                if (!borrowDateTxt.getText().contains(" ")) {
                    maxReturnDateTxt.setText(BorrowingData.calculateMaxReturnDate(borrowDateTxt, dateFormat));
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
