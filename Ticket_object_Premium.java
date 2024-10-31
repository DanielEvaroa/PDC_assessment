package MB;

public class Ticket_object_Premium extends Ticket_object{
    private String Drinks;
    private String Food;
    
    public Ticket_object_Premium(String name,String Email,Integer amount,String Drinks,String food)
    {
        super(name,Email,amount);
        this.setDrinks(Drinks);
        this.setFood(food);
    }
    
    public String getDrinks() {
        return Drinks;
    }
    public void setDrinks(String Drinks) {
        this.Drinks = Drinks;
    }

    public String getFood() {
        return Food;
    }
    public void setFood(String Food) {
        this.Food = Food;
    }
    
    public String return_imfo()
    {
        return " "+ this.getEmail()+" "+this.getName()+" "+this.getAmount()+" "+this.Drinks+" "+this.getFood();
    }
    
   
}
