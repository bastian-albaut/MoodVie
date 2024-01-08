package business;

import com.moodvie.business.facade.LogFacade;
import com.moodvie.persistance.dao.LogDao;
import com.moodvie.persistance.model.Log;
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

public class LogFacadeTest {
     @Test
    public void addLog() {
         // Mock the AbstractDaoFactory class
         AbstractDaoFactory abstractDaoFactoryMock = mock(AbstractDaoFactory.class);
         PowerMockito.mockStatic(AbstractDaoFactory.class);
         when(AbstractDaoFactory.getFactory()).thenReturn(abstractDaoFactoryMock);

         // Mock the UserDao class
         LogDao logDaoMock = mock(LogDao.class);
         when(abstractDaoFactoryMock.getLogDao()).thenReturn(logDaoMock);

         //Mock the addLog method
         Log log = new Log("test", "test", "test");
         //add(log) retourne rien
         doNothing().when(logDaoMock).add(log);

         // Test the addLog method
         LogFacade logFacade = LogFacade.getInstance();
         logFacade.add(log);

         // Assertions
         verify(logDaoMock, times(1)).add(log);
     }
}
