package com.moodvie.persistance.dao;
import com.moodvie.persistance.model.OMDbApiResponse;
import com.moodvie.persistance.model.Film;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import com.google.gson.Gson;

import java.util.List;

public class OMDbApiFilmDao extends FilmDao{
    private static final String API_KEY = "6c9ae0c2"; // Remplacez par votre clé API
    private static final String BASE_URL = "http://www.omdbapi.com/";

    @Override
    public void add(Film film) {

    }


    @Override
    public Film get(String filmId) {
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String finalUrl = BASE_URL + "?apikey=" + API_KEY + "&i=" + filmId;
            HttpGet request = new HttpGet(finalUrl);
            HttpResponse response = httpClient.execute(request);
            String jsonResult = EntityUtils.toString(response.getEntity());

            System.out.println(jsonResult);

            Gson gson = new Gson();
            Film film = gson.fromJson(jsonResult, Film.class);

            System.out.println(film.toString());

            return film;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Film film) {

    }

    public void delete(String filmId) {

    }
    public List<Film> searchFilm(String title) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String finalUrl = BASE_URL + "?apikey=" + API_KEY + "&s=" + title.replace(" ", "+");
            HttpGet request = new HttpGet(finalUrl);
            HttpResponse response = httpClient.execute(request);
            String jsonResult = EntityUtils.toString(response.getEntity());

            System.out.println(jsonResult);



            // Désérialisation en utilisant la nouvelle classe de conteneur
            Gson gson = new Gson();
            OMDbApiResponse apiResponse = gson.fromJson(jsonResult, OMDbApiResponse.class);

            System.out.println(apiResponse.toString());

            if (apiResponse.getResponse().equals("True")) {
                List<Film> films = apiResponse.getSearch();
                for (Film film : films) {
                    System.out.println(film.getTitle());
                }
                return films;
            } else {
                // Gérer le cas où la réponse est "False"
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
