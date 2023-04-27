import java.util.ArrayList;

public class User
{
    String name;
    String nim;
    ArrayList<BorrowingData> borrowingData = new ArrayList<>();

    User(String name, String nim)
    {
        this.nim = nim;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }
}
