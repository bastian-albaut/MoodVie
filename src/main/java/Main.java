import business.UserFacade;
import model.User;

public class Main {
    public static void main(String[] args) {

        UserFacade userFacade = UserFacade.getInstance();
        // Boolean resRegister = userFacade.register("Doe","Test", "Test");
        User user = userFacade.login("Test", "Test");
        System.out.println("user login : " + user);

        //userFacade.DeleteUser();
    }
}

