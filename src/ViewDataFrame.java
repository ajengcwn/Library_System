import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDataFrame extends FrameTemplate implements ActionListener
{
    private JPanel headerPanel = new JPanel();
    private JLabel viewLabel = new JLabel("Borrowing History");
    private JScrollPane scrollPane = new JScrollPane();
    private JTable table;
    private Object[] row = new Object[6];
    private String[] fieldName = {"No.", "Book Title", "Borrow Date", "Return Date", "Max Return Date", "Status"};
    private DefaultTableModel tableModel = new DefaultTableModel(fieldName, 0);

    public ViewDataFrame()
    {
        setViewLabel();

        backBtn.addActionListener(this);

        setHeaderPanel();
        setTable();

        scrollPane.add(table);
        scrollPane.setViewportView(table);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(600, 300);
        setVisible(true);
    }

    private void setViewLabel()
    {
        viewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        viewLabel.setBorder(new EmptyBorder(0, 0, 0, 90));
    }

    private void setHeaderPanel()
    {
        headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setSize(500, 100);
        headerPanel.add(viewLabel);
        headerPanel.add(backBtn);
    }

    private void setTable()
    {
        if(user.borrowingData.size() == 0)
        {
            row[1] = "No Data";

            tableModel.addRow(row);
        } else {
            int i = 0;
            String status;
            for (BorrowingData data : user.borrowingData) {
                row[0] = ++i;
                row[1] = data.getBook().getTitle();
                row[2] = data.getBorrowDate();

                if (data.getReturnDate() == null) {
                    row[3] = "-";
                } else {
                    row[3] = data.getReturnDate();
                }

                row[4] = data.getMaxReturnDate();

                if (data.getReturnDate() != null && data.getReturnDate().isAfter(data.getMaxReturnDate("localDate"))) {
                    status = "Overdue";
                } else if (data.getStatus()) {
                    status = "Returned";
                } else {
                    status = "Not yet returned";
                }

                row[5] = status;

                tableModel.addRow(row);
            }
        }
        table = new JTable(tableModel);
    }

    public static void main(String[] args) {
        new ViewDataFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn)
        {
            this.dispose();
            new MainFrame();
        }
    }
}
