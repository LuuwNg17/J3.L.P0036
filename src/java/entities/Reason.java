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
public class Reason {
    private int reason_id;
    private String reason_name;

    public Reason() {
    }

    public Reason(int reason_id, String reason_name) {
        this.reason_id = reason_id;
        this.reason_name = reason_name;
    }

    public int getReason_id() {
        return reason_id;
    }

    public void setReason_id(int reason_id) {
        this.reason_id = reason_id;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
    }
    
    
}
