package business;

import com.moodvie.persistance.model.User;
import com.moodvie.persistance.dao.UserDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.moodvie.business.facade.UserFacade;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoFactory.class })
public class UserFacadeTest {
    
    @Test
    public void testLogin() {
        // Mock the AbstractDaoFactory class
        AbstractDaoFactory abstractDaoFactoryMock = mock(AbstractDaoFactory.class);
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(abstractDaoFactoryMock);

        // Mock the UserDao class
        UserDao userDaoMock = mock(UserDao.class);
        when(abstractDaoFactoryMock.getUserDao()).thenReturn(userDaoMock);

        // Mock the getUser method
        String pseudo= "Doe";
        String firstname = "John";
        String lastname = "Doe";
        String email = "Test";
        String password = "Test";
        String birthdate = "01/01/2000";


        User expectedUser = new User(pseudo,firstname,lastname,birthdate,email,password);
        when(userDaoMock.get(eq(email))).thenReturn(expectedUser);

        // Test the login method
        UserFacade userFacade = UserFacade.getInstance();
        User userAfterLogin = userFacade.login(email, password);

        // Assertions
        assertNotNull(userAfterLogin);
        assertEquals(expectedUser.getId(), userAfterLogin.getId());
        assertEquals(expectedUser.getPseudo(), userAfterLogin.getPseudo());
        assertEquals(expectedUser.getFirstname(), userAfterLogin.getFirstname());
        assertEquals(expectedUser.getLastname(), userAfterLogin.getLastname());
        assertEquals(expectedUser.getBirthday(), userAfterLogin.getBirthday());
        assertEquals(expectedUser.getEmail(), userAfterLogin.getEmail());
        assertEquals(expectedUser.getPassword(), userAfterLogin.getPassword());
    }

    @Test
    public void testRegister() {
        // Mock the AbstractDaoFactory class
        AbstractDaoFactory abstractDaoFactoryMock = mock(AbstractDaoFactory.class);
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(abstractDaoFactoryMock);

        // Mock the UserDao class
        UserDao userDaoMock = mock(UserDao.class);
        when(abstractDaoFactoryMock.getUserDao()).thenReturn(userDaoMock);

        // Mock the getUser method
        String pseudo = "Doe";
        String firstname = "John";
        String lastname = "Doe";
        String email = "Test";
        String password = "Test";
        String birthdate = "01/01/2000";

        User expectedUser = new User(pseudo, firstname, lastname, birthdate, email, password);
        when(userDaoMock.get(eq(email))).thenReturn(expectedUser);

        // Test the register method
        UserFacade userFacade = UserFacade.getInstance();
        Boolean userAfterRegister = userFacade.register(pseudo, firstname, lastname, birthdate, email, password);

        // Assertions
        assertNotNull(userAfterRegister);
        assertEquals(false, userAfterRegister);
    }
    @Test
    public void testLogout() {
        UserFacade userFacade = UserFacade.getInstance();
        userFacade.logout();
        assertNull("User should be null after logout", userFacade.getUser());
    }

}
