package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Movie movie1 = new Movie(1l,"Matrix");
//        Movie movie2 = new Movie(2l,"Avenger");
        Repository<Movie> repository = new RepositoryImpl();

        repository.update(movie1);
        Movie movie = (Movie) repository.getById(Movie.class,2l);
        System.out.println(movie.getMovieName());
        repository.delete(movie1);

    }
}
