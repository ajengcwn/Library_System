import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class DateTxtTemplate extends JFormattedTextField
{
    public DateTxtTemplate()
    {
        try {
            setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        addPlaceHolderStyle(this);
    }

    public void addPlaceHolderStyle(JFormattedTextField txtField)
    {
        Font font = txtField.getFont();
        font = font.deriveFont(Font.ITALIC);
        txtField.setFont(font);
        //Font Color
        txtField.setForeground(Color.GRAY);
    }

    public void removePlaceHolderStyle(JFormattedTextField txtField)
    {
        Font font = txtField.getFont();
        font = font.deriveFont(Font.PLAIN);
        txtField.setFont(font);
        //Font Color
        txtField.setForeground(Color.BLACK);
    }
}
