package MB;

public class Ticket_object_Standard extends Ticket_object {
    

    public Ticket_object_Standard(String name,String Email,Integer amount)
    {
        super(name,Email,amount);
    }
    
    //Tester function
    public String return_imfo()
    {
        return " "+ this.getEmail()+" "+this.getName()+" "+this.getAmount();
    }

}

