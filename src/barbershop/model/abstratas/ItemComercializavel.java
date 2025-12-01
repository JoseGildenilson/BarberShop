package barbershop.model.abstratas;

public abstract class ItemComercializavel{
    protected String nome;
    protected double valorBase;

    public ItemComercializavel(String nome, double valorBase){
        this.nome = nome;
        this.valorBase = valorBase;
    }

    public abstract double getPreco();

    // getters
    public String getNome(){
        return nome;
    }
    public double getValorBase(){
        return valorBase;
    }

    // setters
    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
}