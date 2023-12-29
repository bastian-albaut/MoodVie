package business;

import com.moodvie.persistance.dao.TypeOfSubscribeDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.moodvie.business.facade.TypeSubscribeFacade;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoFactory.class })
public class TypeSubscribeFacadeTest {
    
    @Test
    public void testCreateTypeOfSubscribe() {
        // Mock the AbstractDaoFactory class
        AbstractDaoFactory abstractDaoFactoryMock = mock(AbstractDaoFactory.class);
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(abstractDaoFactoryMock);

        // Mock the TypeOfSubscribeDao class
        TypeOfSubscribeDao typeOfSubscribeDaoMock = mock(TypeOfSubscribeDao.class);
        when(abstractDaoFactoryMock.getTypeOfSubscribeDao()).thenReturn(typeOfSubscribeDaoMock);

        // Test the createTypeOfSubscribe method
        TypeSubscribeFacade typeSubscribeFacade = TypeSubscribeFacade.getInstance();
        
        String label = "Basique";
        double price = 10;
        int numberOfDays = 30;
        ArrayList<String> features = new ArrayList<>(List.of("Feature 1", "Feature 2"));
        boolean isTypeOfSubscribeCreate = typeSubscribeFacade.createTypeOfSubscribe(label, price, numberOfDays, features);

        // Assertions
        assertEquals(true, isTypeOfSubscribeCreate);
    }
}
