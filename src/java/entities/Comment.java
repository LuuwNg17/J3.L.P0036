/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author ThaiDuongNg
 */
public class Comment {

    private int comment_id;
    private String user_name;
    private int post_id;
    private String comment_content;
    private Date date_time;
    private boolean isApproved;
    private int reject_reason_id;
    private boolean status_alert;

    public Comment() {
    }

    public Comment(String user_name, int post_id, String comment_content, boolean isApproved, int reject_reason_id, boolean status_alert) {
        this.user_name = user_name;
        this.post_id = post_id;
        this.comment_content = comment_content;
        this.isApproved = isApproved;
        this.reject_reason_id = reject_reason_id;
        this.status_alert = status_alert;
    }

    public Comment(int comment_id, String user_name, int post_id, String comment_content, Date date_time, boolean isApproved, int reject_reason_id, boolean status_alert) {
        this.comment_id = comment_id;
        this.user_name = user_name;
        this.post_id = post_id;
        this.comment_content = comment_content;
        this.date_time = date_time;
        this.isApproved = isApproved;
        this.reject_reason_id = reject_reason_id;
        this.status_alert = status_alert;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public int getReject_reason_id() {
        return reject_reason_id;
    }

    public void setReject_reason_id(int reject_reason_id) {
        this.reject_reason_id = reject_reason_id;
    }

    public boolean isStatus_alert() {
        return status_alert;
    }

    public void setStatus_alert(boolean status_alert) {
        this.status_alert = status_alert;
    }

    public String getDateFormat() {
        return new SimpleDateFormat("MMMM dd yyy '-' HH:mmaaa")
                .format(this.date_time).toLowerCase();
    }

}
