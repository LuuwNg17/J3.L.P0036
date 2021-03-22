/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ThaiDuongNg
 */
public class Reply {
    private int reply_id;
    private String reply_content;
    private String author_name;
    private int comment_id;

    public Reply() {
    }

    public Reply(int reply_id, String reply_content, String author_name, int comment_id) {
        this.reply_id = reply_id;
        this.reply_content = reply_content;
        this.author_name = author_name;
        this.comment_id = comment_id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    
    
    
}
