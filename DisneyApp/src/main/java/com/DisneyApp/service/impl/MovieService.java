package com.DisneyApp.service.impl;

import com.DisneyApp.entity.CharacterEntity;
import com.DisneyApp.entity.GenreEntity;
import com.DisneyApp.entity.MovieEntity;
import com.DisneyApp.entity.dto.MovieBasicDTO;
import com.DisneyApp.entity.dto.MovieDTO;
import com.DisneyApp.entity.dto.MovieFiltersDTO;
import com.DisneyApp.mapper.MovieMapper;
import com.DisneyApp.repository.CharacterRepository;
import com.DisneyApp.repository.GenreRepository;
import com.DisneyApp.repository.MovieRepository;
import com.DisneyApp.repository.specification.MovieSpecification;
import com.DisneyApp.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService implements IMovieService {

    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final MovieMapper movieMapper;
    @Autowired
    private final MovieSpecification movieSpecification;
    @Autowired
    private final CharacterService characterService;
    @Autowired
    private final CharacterRepository characterRepository;
    @Autowired
    private final GenreService genreService;
    @Autowired
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, MovieSpecification movieSpecification, CharacterService characterService, CharacterRepository characterRepository, GenreService genreService, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieSpecification = movieSpecification;
        this.characterService = characterService;
        this.characterRepository = characterRepository;
        this.genreService = genreService;
        this.genreRepository = genreRepository;
    }

    //Create Movie w/asoc characters. (return complete w/char)
    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.MovieDTO2Entity(movieDTO);
        MovieEntity saved = movieRepository.save(movieEntity);
        MovieDTO movieResult = movieMapper.MovieEntity2DTO(saved, false);

        return movieResult;
    }

    @Override
    public List<MovieDTO> getAll() {
        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieDTO> moviesDto = movieMapper.EntityList2DTOList(movies, false);

        return moviesDto;
    }

    @Override
    public MovieDTO findMovieDTOById(Integer id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        boolean present = movieEntity.isPresent();
        MovieDTO movieDTOFound = null;
        if (present){
            MovieEntity movie = movieEntity.get();
            movieDTOFound = movieMapper.MovieEntity2DTO(movie, true);
        }
        return movieDTOFound;
    }

    //Edit Movie only movie data, no characters (return complete w/char)
    @Override
    public MovieDTO updateById(Integer id, MovieDTO movieDTO) {
        MovieDTO movieFound = this.findMovieDTOById(id);
        MovieEntity movieToBeUpdated = movieMapper.MovieDTO2Entity(movieFound);

        movieToBeUpdated.setImage(movieDTO.getImage());
        movieToBeUpdated.setTitle(movieDTO.getTitle());
        movieToBeUpdated.setReleaseDate(movieDTO.getRelaseDate());
        movieToBeUpdated.setRating(movieDTO.getRating());

        MovieEntity updated = movieRepository.save(movieToBeUpdated);
        MovieDTO result = movieMapper.MovieEntity2DTO(updated, false);
        return result;
    }

    //Delete Movie
    @Override
    public void deleteByid(Integer id) {
        movieRepository.deleteById(id);
    }

    //Add/remove characters to a movie by id (/movies/movieId/characters/characterId)
    @Override
    public void addCharacter(Integer movieId, Integer characterId) {
        //primero busca la movie y el character que quiere agregar.
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        MovieEntity movieFound = movie.get();
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        CharacterEntity characterFound = character.get();
        movieFound.getMoviesCharacters().size();
        movieFound.addCharacterToMovie(characterFound);
        movieRepository.save(movieFound);
    }

    public void removeCharacter(Integer movieId, Integer characterId) {
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        MovieEntity movieFound = movie.get();
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        CharacterEntity characterFound = character.get();
        movieFound.getMoviesCharacters().size();
        movieFound.removeCharacterToMovie (characterFound);
        movieRepository.save(movieFound);
    }

    @Override
    public void addGenre(Integer movieId, Integer genreId) {
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        MovieEntity movieFound = movie.get();
        Optional<GenreEntity> genre = genreRepository.findById(genreId);
        GenreEntity genreFound = genre.get();
        movieFound.getMoviesGenres().size();
        movieFound.addGenreToMovie(genreFound);
        movieRepository.save(movieFound);
        }


    //Filters by tiltle & genre, in asc or desc order-
    @Override
    public List<MovieBasicDTO> getMoviesByFilters(String title, List<Integer> genres, String order) {
        MovieFiltersDTO movieFiltersDTO = new MovieFiltersDTO(title, genres, order);
        List<MovieEntity> entityList = movieRepository.findAll(this.movieSpecification.getByFilters(movieFiltersDTO));
        List<MovieBasicDTO> resultBasicDTO = movieMapper.MovieEntityList2BasicDTOList(entityList);
        return resultBasicDTO;
    }

}
