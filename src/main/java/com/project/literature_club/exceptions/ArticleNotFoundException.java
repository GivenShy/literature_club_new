package com.project.literature_club.exceptions;

public class ArticleNotFoundException extends NotFoundException {
    public ArticleNotFoundException(){
        super("Article is not found");
    }
}
