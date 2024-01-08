package business;

import com.moodvie.business.facade.UserMoodFacade;
import com.moodvie.persistance.dao.UserMoodDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.UserMood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoFactory.class })
public class UserMoodFacadeTest {

    @Test
    public void testAddUserMood() {
        // Mock the AbstractDaoFactory class using PowerMockito
        AbstractDaoFactory abstractDaoFactoryMock = PowerMockito.mock(AbstractDaoFactory.class);
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(abstractDaoFactoryMock);

        // Mock the UserMoodDao class
        UserMoodDao userMoodDaoMock = mock(UserMoodDao.class);
        when(abstractDaoFactoryMock.getUserMoodDao()).thenReturn(userMoodDaoMock);

        // Call the method to be tested
        UserMoodFacade userMoodFacade = UserMoodFacade.getInstance();

        // Create a UserMood object for testing
        UserMood testUserMood = new UserMood();
        testUserMood.setMoodID(1);
        testUserMood.setMoodDescription("Happy");

        // Call the method to be tested
        userMoodFacade.addUserMood(testUserMood);

        // Use Mockito to capture the argument passed to the add method of UserMoodDao
        ArgumentCaptor<UserMood> userMoodArgumentCaptor = ArgumentCaptor.forClass(UserMood.class);
        verify(userMoodDaoMock).add(userMoodArgumentCaptor.capture());

        // Assert that the argument passed to the add method is the same as the testUserMood
        assertEquals(testUserMood, userMoodArgumentCaptor.getValue());
    }
}
