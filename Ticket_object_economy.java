package MB;

public class Ticket_object_economy extends Ticket_object{
    private String steated;
    
    public Ticket_object_economy(String name,String Email,Integer amount,String SEATED)
    {
        super(name,Email,amount);
        this.setSteated(SEATED);
        
    }
    
     public String getSteated() {
        return steated;
    }

    // Setter method for steated
    public void setSteated(String steated) {
        this.steated = steated;
    }
}
