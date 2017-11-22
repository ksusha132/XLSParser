import java.io.IOException;

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
        System.out.println(up.parse());
    }
}
