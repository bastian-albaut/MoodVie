package com.moodvie;

import com.moodvie.business.facade.TypeSubscribeFacade;
import com.moodvie.business.facade.UserFacade;
import com.moodvie.persistance.model.User;

public class Main {
    public static void main(String[] args) {

        // Must create type of subscribe on the init of the app
        TypeSubscribeFacade typeSubscribeFacade = TypeSubscribeFacade.getInstance();
        typeSubscribeFacade.createTypeOfSubscribe("Basique", 0, Integer.MAX_VALUE);
        typeSubscribeFacade.createTypeOfSubscribe("Mensuel", 4.99, 30);
        typeSubscribeFacade.createTypeOfSubscribe("Annuel", 29.99, 365);

        // Check if all types of subscribe have been created
        System.out.println("Type of subscribe list :");
        typeSubscribeFacade.getListTypeOfSubscribe();


        UserFacade userFacade = UserFacade.getInstance();
        Boolean resRegister = userFacade.register("Test", "Test", "Test", "Test", "Test", "Test");
        User user = userFacade.login("Test", "Test");
        System.out.println("user login : " + user);
        User user2 = userFacade.login("a", "a");
        System.out.println("user2 login : " + user2);

        //userFacade.DeleteUser();


    }
}