package com.felipegcx.filmsDBMS.repositories;

import java.util.List;
import java.util.Set;
import com.felipegcx.filmsDBMS.models.Films;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

//*|||||||||||||||||*\\
//* FilmRepository *\\
//*|||||||||||||||||*\\

public interface FilmRepository extends MongoRepository<Films, Integer> {
    @Query(value = "{year : {$gt: 0}}", sort = "{year: -1}, {id: -1}")
    Page<Films> findAllSortByYear(Pageable pageable);

    // Query find by title with regex start with
    // /^abc/
    // @Query(value = "{title : {$regex: ?0, $options: 'i'}}")
    @Query(value = "{$or:[{title:{$regex:?0,$options:'i'}},{titleOG:{$regex:?0,$options:'i'}}]}")
    List<Films> findByTitle(String title);

    @Query(value = "{year : {$lt: ?0}}}", sort = "{year: -1}")
    Page<Films> findByYearDESC(Integer year, Pageable pageable);

    @Query(value = "{year : {$lt: ?0}}}", sort = "{year: 1}")
    Page<Films> findByYearASC(Integer year, Pageable pageable);

    @Query(value = "{note : {$lt: ?0}}}", sort = "{note: -1}")
    Page<Films> findByNoteDESC(Double note, Pageable pageable);

    @Query(value = "{note : {$lt: ?0}}}", sort = "{note: 1}")
    Page<Films> findByNoteASC(Double note, Pageable pageable);

    @Query(value = "{category:?0}", sort = "{year: -1}")
    Page<Films> findByCategory(Integer category, Pageable pageable);

    @Query(value = "{language:?0}", sort = "{year: -1}")
    Page<Films> findByLanguage(Boolean language, Pageable pageable);

    @Query(value = "{favorite:?0}", sort = "{year: -1}")
    Page<Films> findByFavorite(Boolean favorite, Pageable pageable);

    @Query(value = "{type : ?0}")
    Page<Films> findByType(String type, Pageable pageable);

    @Query(value = "{type : ?0,year : {$lt: ?1}}", sort = "{year:-1,note:-1}")
    Page<Films> findByTypeFullDESC(String type, Integer year, Pageable pageable);

    @Query(value = "{type : ?0,year : {$lt: ?1}}", sort = "{year:-1,note:1}")
    Page<Films> findByTypeFullASC(String type, Integer year, Pageable pageable);

    @Query(value = "{type : ?0,year : {$lt: ?1}}", sort = "{year:-1}")
    Page<Films> findByTypeFullNoNote(
            String type,
            Integer year,
            Pageable pageable);

    @Query(value = "{year:{$lt:?0}}", sort = "{year:-1,note:-1}")
    Page<Films> findAllFullDESC(Integer year, Pageable pageable);

    @Query(value = "{year:{$lt:?0}}", sort = "{year:-1,note:1}")
    Page<Films> findAllFullASC(Integer year, Pageable pageable);

    @Query(value = "{year:{$lt:?0}}", sort = "{year:-1}")
    Page<Films> findAllFullNoNote(Integer year, Pageable pageable);

    @Query(value = "{type : ?0, year : {$lt: ?1}}", sort = "{year : -1}")
    Page<Films> findByTypeAndYearDESC(
            String type,
            Integer year,
            Pageable pageable);

    @Query(value = "{type : ?0, year : {$lt: ?1}}", sort = "{year : 1}")
    Page<Films> findByTypeAndYearASC(
            String type,
            Integer year,
            Pageable pageable);

    @Query(value = "{type : ?0, note : {$lt: ?1}}", sort = "{note : -1}")
    Page<Films> findByTypeAndNoteDESC(
            String type,
            Double note,
            Pageable pageable);

    @Query(value = "{type : ?0, note : {$lt: ?1}}", sort = "{note : 1}")
    Page<Films> findByTypeAndNoteASC(String type, Double note, Pageable pageable);

    @Query(value = "{type : ?0, category:?1}", sort = "{year: -1}")
    Page<Films> findByTypeAndCategory(
            String type,
            Integer category,
            Pageable pageable);

    @Query(value = "{type : ?0, language:?1}", sort = "{year: -1}")
    Page<Films> findByTypeAndLanguage(
            String type,
            Boolean language,
            Pageable pageable);

    @Query(value = "{type : ?0, favorite:?1}", sort = "{year: -1}")
    Page<Films> findByTypeAndFavorite(
            String type,
            Boolean favorite,
            Pageable pageable);

    @Query(value = "{saga: ?0}", sort = "{year: 1}")
    Page<Films> findBySaga(Integer saga, Pageable pageable);

    @Query(value = "{}", sort = "{id: -1}")
    List<Films> findLast();

    @Aggregation(pipeline = {
            "{ $group: { _id: '$type' } }",
            "{ $project: { _id: 0, type: '$_id' } }"
    })
    Set<String> findUniqueTypes();

    @Aggregation(pipeline = {
            "{$match: { 'type': ?0 }}",
            "{$count:'type'}"
    })
    long countByType(String type);
}
