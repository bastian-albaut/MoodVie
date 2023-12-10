package business;

import model.User;
import persistance.dao.UserDao;
import persistance.factory.AbstractDaoFactory;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoFactory.class })
public class UserFacadeTest {
    
    @Test
    public void testLogin() {
        // Mock UserDao
        UserDao mockedUserDao = mock(UserDao.class);

        // Mock AbstractDaoFactory
        AbstractDaoFactory mockedDaoFactory = mock(AbstractDaoFactory.class);
        when(mockedDaoFactory.getUserDao()).thenReturn(mockedUserDao);

        // Mock the static method getFactory() in AbstractDaoFactory
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(mockedDaoFactory);

        String name = "Test";
        String email = "test@example.com";
        String password = "password123";

        // Setup the expected behavior of the login method
        User expectedUser = new User(name, email, password);
        when(mockedUserDao.login(eq(email), eq(password))).thenReturn(expectedUser);

        // Test the login method
        UserFacade userFacade = UserFacade.getInstance();
        User userAfterLogin = userFacade.login(email, password);

        // Assertions
        assertNotNull(userAfterLogin);
        assertEquals(expectedUser.getName(), userAfterLogin.getName());
        assertEquals(expectedUser.getEmail(), userAfterLogin.getEmail());
        assertEquals(expectedUser.getPassword(), userAfterLogin.getPassword());
    }
}
