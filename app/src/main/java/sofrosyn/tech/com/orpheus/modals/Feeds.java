package sofrosyn.tech.com.orpheus.modals;

public class Feeds {
   public  String subject,message,date;


    public Feeds(String subject, String message, String date) {
        this.subject = subject;
        this.message = message;
        this.date= date;

    }
    public Feeds(){

    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

}
