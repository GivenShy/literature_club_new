package com.project.literature_club.repositories;


import com.project.literature_club.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {




    @Query("SELECT a FROM Article  a  where LOWER(a.title) like concat('%',:s,'%') ")
    List<Article> search(@Param("s") String str);

    List<Article> findByAuthor_Id(Long id);






}
