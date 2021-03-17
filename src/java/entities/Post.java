package entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ThaiDuongNg
 */
public class Post {

    private int post_id;
    private String author_name;
    private Date date_time;
    private String content;

    public Post() {
    }

    public Post(int post_id, String author_name, Date date_time, String content) {
        this.post_id = post_id;
        this.author_name = author_name;
        this.date_time = date_time;
        this.content = content;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateFormat() {
        return new SimpleDateFormat("MMMM dd yyy '-' HH:mmaaa")
                .format(this.date_time).toLowerCase();
    }

}
