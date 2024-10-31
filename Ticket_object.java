package MB;

public class Ticket_object {
    private String name ;
    private String Email ;
    private Integer amount;
  
    public Ticket_object(String name,String Email,Integer amount)
    {
        this.setEmail(Email);
        this.setAmount(amount);
        this.setName(name);
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String return_imfo()
    {
        return " "+ this.getEmail()+" "+this.getName()+" "+this.getAmount();
    }
    
}
