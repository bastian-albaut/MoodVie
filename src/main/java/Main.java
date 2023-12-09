import business.UserFacade;
import persistance.dao.User;

public class Main {
    public static void main(String[] args) {

        UserFacade userFacade = UserFacade.getInstance();
        Boolean resRegister = userFacade.register("Doe","Test", "Test");
        Boolean resLogin = userFacade.login("Test", "Test");
        User user = userFacade.getUser();
        System.out.println("user : " + user);

        //userFacade.DeleteUser();
        System.out.println("resRegister : " + resRegister);
        System.out.println("resLogin : " + resLogin);


    }
}

