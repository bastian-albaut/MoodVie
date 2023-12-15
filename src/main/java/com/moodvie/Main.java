import com.moodvie.model.facade.UserFacade;
import com.moodvie.model.User;

public class Main {
    public static void main(String[] args) {

        UserFacade userFacade = UserFacade.getInstance();
        // Boolean resRegister = userFacade.register("Doe","Test", "Test");
        User user = userFacade.login("Test", "Test");
        System.out.println("user login : " + user);
        User user2 = userFacade.login("a", "a");
        System.out.println("user2 login : " + user2);

        //userFacade.DeleteUser();
    }
}

