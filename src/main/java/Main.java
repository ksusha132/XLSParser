import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ksusha on 21.11.2017.
 */

// задача: написать парсер ексель файла, в котором лежит пользователь
// у пользователя есть имя фамилия отчество возраст и пол
// нужно получить данные из файла и просетить их пользователю
// далее необходимо получить данные из пользователя

public class Main {
    public static void main(String[] args) throws IOException {
        UserParser up = new UserParser();

        //User user = up.parse().get(0);
        for (Iterator<User> it = up.parse().iterator(); it.hasNext(); ) {
            User user = it.next();
            System.out.println(user.getAge());
            System.out.println(user.getName());
            System.out.println(user.getSecName());
            System.out.println(user.getPatronimic());
        }
    }
}
