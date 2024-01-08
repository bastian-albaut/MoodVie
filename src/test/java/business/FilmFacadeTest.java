package business;

import com.moodvie.business.facade.FilmFacade;
import com.moodvie.persistance.dao.FilmDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Film;
import com.moodvie.persistance.model.OMDbApiResponse;

import com.moodvie.persistance.model.OMDbApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoFactory.class })
public class FilmFacadeTest {


    @Test
    public void testGetFilm() {
        // Mock the AbstractDaoFactory and FilmDao
        AbstractDaoFactory factoryMock = mock(AbstractDaoFactory.class);
        FilmDao filmDaoMock = mock(FilmDao.class);
        PowerMockito.mockStatic(AbstractDaoFactory.class);
        when(AbstractDaoFactory.getFactory()).thenReturn(factoryMock);
        when(factoryMock.getFilmDao()).thenReturn(filmDaoMock);

        // Prepare test data
        String filmId = "tt0076759";
        Film expectedFilm = new Film();
        expectedFilm.setTitle("Star Wars: Episode IV - A New Hope");
        // Adjust as per your Film class
        when(filmDaoMock.get(anyString())).thenReturn(expectedFilm);

        // Test the getFilm method
        FilmFacade filmFacade = FilmFacade.getInstance();
        Film result = filmFacade.getFilm(filmId);

        // Assertions
        assertNotNull(result);
        assertEquals(expectedFilm.getTitle(), result.getTitle());
    }
}
