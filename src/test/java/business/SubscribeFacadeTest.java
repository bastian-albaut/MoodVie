package business;

import com.moodvie.persistance.model.Subscribe;
import com.moodvie.persistance.dao.SubscribeDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.moodvie.business.facade.SubscribeFacade;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoFactory.class })
public class SubscribeFacadeTest {
    
    @Test
    public void testCreateSubscribe() {
        // Mock the AbstractDaoFactory class
        AbstractDaoFactory abstractDaoFactoryMock = mock(AbstractDaoFactory.class);
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(abstractDaoFactoryMock);

        // Mock the SubscribeDao class
        SubscribeDao subscribeDaoMock = mock(SubscribeDao.class);
        when(abstractDaoFactoryMock.getSubscribeDao()).thenReturn(subscribeDaoMock);

        // Test the createSubscribe method
        SubscribeFacade subscribeFacade = SubscribeFacade.getInstance();

        int userId = 1;
        Timestamp startDate = new Timestamp(0);
        boolean isActive = false;
        int typeOfSubscribeId = 1;
        boolean isSubscribeCreate = subscribeFacade.createSubscribe(startDate, isActive, typeOfSubscribeId, userId);

        // Assertions
        assertEquals(true, isSubscribeCreate);
    }
}
