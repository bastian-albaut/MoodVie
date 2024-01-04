package com.moodvie.persistance.model;

import com.moodvie.persistance.model.Film;

import java.util.List;

public class OMDbApiResponse {

    private List<Film> Search;

    private String totalResults;

    private String Response;

    public OMDbApiResponse() {
    }

    //Methodes toString
    @Override
    public String toString() {
        return "OMDbApiResponse{" +
                "Search=" + Search +
                ", totalResults='" + totalResults + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }

    // Getters et Setters
    public List<Film> getSearch() {
        for (Film film : Search) {
            System.out.println(film);
        }
        return Search;
    }

    public void setSearch(List<Film> search) {
        this.Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        this.Response = response;
    }
}
