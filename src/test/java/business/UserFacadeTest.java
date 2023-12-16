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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        String name= "Doe";
        String email = "Test";
        String password = "Test";

        User expectedUser = new User(name, email, password);
        when(userDaoMock.getUser(eq(email))).thenReturn(expectedUser);

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
