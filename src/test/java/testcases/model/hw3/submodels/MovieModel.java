package testcases.model.hw3.submodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import testcases.model.hw3.response.BrowseKeywordResponseModel;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieModel {

    @JsonProperty(value = "movie_id", required = true)
    String movie_id;
    @JsonProperty(value = "title", required = true)
    String title;
    @JsonProperty(value = "year", required = true)
    Integer year;
    @JsonProperty(value = "director", required = true)
    String director;
    @JsonProperty(value = "rating", required =  true)
    Float rating;
    @JsonProperty(value = "num_votes", required = true)
    Integer num_votes;
    @JsonProperty("budget")
    String budget;
    @JsonProperty("revenue")
    String revenue;
    @JsonProperty("overview")
    String overview;
    @JsonProperty("backdrop_path")
    String backdrop_path;

    @JsonProperty("poster_path")
    String poster_path;
    @JsonProperty("hidden")
    Boolean hidden;

    @JsonProperty(value ="genres", required=true)
    GenreModel[] genres;
    @JsonProperty(value ="people", required=true)
    PeopleMovieModel[] people;

    public MovieModel(){

    }

    public MovieModel(String movie_id,
                      String title,
                      Integer year,
                      String director,
                      Float rating,
                      Integer num_votes,
                      String budget,
                      String revenue,
                      String overview,
                      String backdrop_path,
                      String poster_path,
                      Boolean hidden,
                      GenreModel[] genres,
                      PeopleMovieModel[] people) {
        this.movie_id = movie_id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.num_votes = num_votes;
        this.budget = budget;
        this.revenue = revenue;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.hidden = hidden;
        this.genres = genres;
        this.people = people;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public Float getRating() {
        return rating;
    }

    public Integer getNum_votes() {
        return num_votes;
    }

    public String getBudget() {
        return budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public GenreModel[] getGenres() {
        return genres;
    }

    public PeopleMovieModel[] getPeople() {
        return people;
    }

    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MovieModel that = (MovieModel) o;

        if (!(Objects.equals(this.movie_id, that.movie_id)))
           return false;

        if (!Objects.equals(this.title, that.title))
            return false;

        if (!Objects.equals(this.year, that.year))
            return false;

        if (!Objects.equals(this.backdrop_path, that.backdrop_path))
            return false;

        //poster path not required, so if it's not nul then we can check. otherwise if it is null and the other one is not null then we hae an issue
        if (!Objects.equals(this.poster_path, that.poster_path))
            return false;
            // we good


        //All parameters were equal. We good.
        return true;
    }
}
