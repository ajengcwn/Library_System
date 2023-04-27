import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.spec.RSAOtherPrimeInfo;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReturnFrame extends FrameTemplate implements ActionListener, FocusListener
{
    private JPanel headerPanel = new JPanel();
    private JLabel returnLabel = new JLabel("Return Books");
    private JPanel contentPanel = new JPanel(new GridLayout(3, 2));
    private ArrayList<JLabel> jLabels = new ArrayList<>();
    private JComboBox<String> borrowedBooksDropDown = new JComboBox();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTxtTemplate returnDateTxt = new DateTxtTemplate();
    private JFormattedTextField maxReturnDateTxt = new JFormattedTextField();
    private JPanel footerPanel = new JPanel();

    ReturnFrame()
    {
        boolean log = checkNecessity();

        if(!log)
        {
            return;
        }

        setReturnLabel();

        backBtn.addActionListener(this);

        setHeaderPanel();
        setFieldLabels();
        setBorrowedBooksDropDown();
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

    //If the borrowing data is empty or no books to be returned, return
    private boolean checkNecessity()
    {
        if(user.borrowingData.size() == 0 || !BorrowingData.checkStatus(user.borrowingData))
        {
            JOptionPane.showMessageDialog(this, "No books to return!");
            this.dispose();

            new MainFrame();

            return false;
        }

        return true;
    }

    private void setReturnLabel()
    {
        returnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        returnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        returnLabel.setBorder(new EmptyBorder(0, 0, 0, 90));
    }

    private void setHeaderPanel()
    {
        headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setSize(500, 100);
        headerPanel.add(returnLabel);
        headerPanel.add(backBtn);
    }

    private void setFieldLabels()
    {
        Font labelFont = new Font("Arial", Font.PLAIN, 15);

        String[] labels = {"Borrowed Books", "Return Date", "Max Return Date"};
        for(String text : labels)
        {
            JLabel label = new JLabel(text);
            label.setFont(labelFont);
            label.setHorizontalAlignment(SwingConstants.LEFT);
            jLabels.add(new JLabel(text));
        }
    }

    private void setBorrowedBooksDropDown()
    {
        for(BorrowingData data : user.borrowingData)
        {
            if(!data.getStatus())
            {
                borrowedBooksDropDown.addItem(data.getBook().getTitle());
            }
        }

        borrowedBooksDropDown.addActionListener(this);
        borrowedBooksDropDown.setSelectedItem(null);
    }

    private void setTextFields()
    {
        returnDateTxt.setText("23/12/2023");
        returnDateTxt.addFocusListener(this);
        maxReturnDateTxt.setEditable(false);
    }

    private void setContentPanel()
    {
        contentPanel.add(jLabels.get(0));
        contentPanel.add(borrowedBooksDropDown);
        contentPanel.add(jLabels.get(1));
        contentPanel.add(returnDateTxt);
        contentPanel.add(jLabels.get(2));
        contentPanel.add(maxReturnDateTxt);

        contentPanel.setSize(400, 70);
        contentPanel.setBorder(new EmptyBorder(30, 90, 65, 90));
    }

    public static void main(String[] args)
    {
        new ReturnFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submitBtn)
        {
            boolean validBook = this.checkComboBox(borrowedBooksDropDown);
            boolean validMaxReturnDate = this.checkTxt(maxReturnDateTxt);
            boolean validReturnDate = this.checkTxt(returnDateTxt);

            if(validBook && validReturnDate && validMaxReturnDate)
            {
                BorrowingData selectedData = BorrowingData.getBorrowingData(borrowedBooksDropDown, user.borrowingData);
                Book selectedBook = Book.getBook(selectedData.getBook().getShelf(), borrowedBooksDropDown, books);

                int log = selectedData.checkReturnDate(returnDateTxt, maxReturnDateTxt, dateFormat);

                if(log == 1)
                {
                    JOptionPane.showMessageDialog(this, "You have exceeded the max return date!\n Please contact the librarian for further assistance!");
                } else if(log == 2)
                {
                    JOptionPane.showMessageDialog(this, "Return date must be after borrow date!\n You borrowed " + selectedBook.getTitle() + " book on " + selectedData.getBorrowDate());
                } else if(log == 0)
                {
                    selectedBook.setTotalBooks("+");
                    selectedData.setReturnDate(BorrowingData.getDate(returnDateTxt, dateFormat));
                    selectedData.setStatus();

                    JOptionPane.showMessageDialog(this, "You have successfully returned the book!");

                    this.dispose();

                    new ReturnFrame();
                }
            } else
            {
                JOptionPane.showMessageDialog(this, "Please fill out every field!");
            }
        } else if(e.getSource() == backBtn)
        {
            this.dispose();
            new MainFrame();
        } else if(e.getSource() == borrowedBooksDropDown)
        {
            if(borrowedBooksDropDown.getSelectedItem() != null)
            {
                maxReturnDateTxt.setText(BorrowingData.getBorrowingData(borrowedBooksDropDown, user.borrowingData).getMaxReturnDate());
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        if(e.getSource() == returnDateTxt)
        {
            if(returnDateTxt.getText().equals("23/12/2023"))
            {
                returnDateTxt.setText("");
                //Sets the component to "onfocus"
                returnDateTxt.requestFocus();
                returnDateTxt.removePlaceHolderStyle(returnDateTxt);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if(e.getSource() == returnDateTxt)
        {
            if(returnDateTxt.getText().contains(" "))
            {
                returnDateTxt.setText("23/12/2023");
                returnDateTxt.addPlaceHolderStyle(returnDateTxt);
            }
        }
    }
}
