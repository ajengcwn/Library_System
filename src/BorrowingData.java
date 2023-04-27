import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BorrowingData
{
//    private String id;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate maxReturnDate;
    private LocalDate returnDate;
    private boolean status; //False -> not yet returned
                            //True  -> returned

    public BorrowingData(Book book, LocalDate borrowDate, LocalDate maxReturnDate)
    {
        this.book = book;
        this.borrowDate = borrowDate;
        this.maxReturnDate = maxReturnDate;
        this.status = false;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String getMaxReturnDate()
    {
        return String.format("%02d/%02d/%4d", this.maxReturnDate.getDayOfMonth(), this.maxReturnDate.getMonthValue(), this.maxReturnDate.getYear());
    }

    public LocalDate getMaxReturnDate(String identifier)
    {
        return this.maxReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus()
    {
        this.status = true;
    }

    public static String calculateMaxReturnDate(JFormattedTextField txtField, DateTimeFormatter dateFormat)
    {
        LocalDate date = LocalDate.parse(txtField.getText(), dateFormat);
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        date = date.plusDays(14);

        return String.format("%02d/%02d/%4d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    public int checkReturnDate(JFormattedTextField returnDateTxt, JFormattedTextField maxReturnDateTxt, DateTimeFormatter dateFormat)
    {
        LocalDate returnDate = getDate(returnDateTxt, dateFormat);
        LocalDate maxReturnDate = getDate(maxReturnDateTxt, dateFormat);

        if(returnDate.isAfter(maxReturnDate))
        {
            return 1;
        }

        if(returnDate.isBefore(this.borrowDate))
        {
            return 2;
        }

        return 0;
    }

    public static LocalDate getDate(JFormattedTextField txtField, DateTimeFormatter dateFormat)
    {
        LocalDate date = LocalDate.parse(txtField.getText(), dateFormat);

        return date;
    }

    public static BorrowingData getBorrowingData(JComboBox dropDown, ArrayList<BorrowingData> datas)
    {
        for(BorrowingData data : datas)
        {
            if(dropDown.getSelectedItem() == data.getBook().getTitle())
            {
                return data;
            }
        }

        return null;
    }

    public static boolean checkStatus(ArrayList<BorrowingData> datas)
    {
        for(BorrowingData data : datas)
        {
            if(!data.status) //False --> not yet returned
            {
                return true;
            }
        }

        return false;
    }
}
