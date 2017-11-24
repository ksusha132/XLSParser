import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<String, Object> map = new HashMap<String, Object>();

        //User user = up.parse().get(0);
        for (User user : up.parse()) {
            System.out.println(user.getAge());
            System.out.println(user.getName());
            System.out.println(user.getSecName());
            System.out.println(user.getPatronimic());
            map.put(user.getName(), user); // I need this to transmit this map into somewhere else... In cache
        }
    }
}
