package com.example.nowfeed.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rusili on 11/9/16.
 */

public class BestSellersPOJO {

    String status;
    String copyright;
    int num_results;
    List<results> results = new ArrayList<>();

    public String getStatus(){
        return this.status;
    }

    public List<results> getResults(){
        return this.results;
    }

    public class results{
        String title;
        String description;
        String contributor;
        String author;
        double price;
        String age_group;
        String publisher;
        List<isbns> isbns = new ArrayList<>();
        List<ranks_history> rankshistory = new ArrayList<>();
        List<reviews> reviews = new ArrayList<>();

        public String getTitle(){
            return this.title;
        }

        public String getAuthor(){
            return this.author;
        }

        public String getDescription(){
            return this.description;
        }

        public class isbns{
            String isbn10;
            String isbn13;
        }

        public class ranks_history{
            String primary_isbn10;
            String primary_isbn13;
            int rank;
            String list_name;
            String display_name;
            String bestsellers_date;
            int weeks_on_list;
            int ranks_last_week;
            int asterisk;
            int dagger;
        }

        public class reviews{
            String book_review_link;
            String first_chapter_link;
            String sunday_review_link;
            String article_chapter_link;
        }

    }
}
