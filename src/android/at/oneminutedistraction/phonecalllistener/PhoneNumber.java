package at.oneminutedistraction.phonecalllistener;

import java.io.Serializable;

/**
 * Created by cmlee on 6/23/14.
 */
public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String phoneNumber;
    private String notes;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return (id);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return (phoneNumber);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getNotes() {
        return (notes);
    }
}
