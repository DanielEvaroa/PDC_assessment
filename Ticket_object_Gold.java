package MB;

public class Ticket_object_Gold extends Ticket_object{
    
    private String Drinks;
    
    public Ticket_object_Gold(String name,String Email,Integer amount,String Drinks)
    {
        super(name,Email,amount);
        this.setDrinks(Drinks);
    }

    public String getDrinks() {
        return Drinks;
    }
    public void setDrinks(String Drinks) {
        this.Drinks = Drinks;
    }
    
    public String return_imfo()
    {
        return " "+ this.getEmail()+" "+this.getName()+" "+this.getAmount()+" "+this.Drinks;
    }
    
     
}
