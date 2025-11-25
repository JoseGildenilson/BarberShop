package barbershop.model.abstratas;

public abstract class ItemComercializavel{
    protected String nome;
    protected double valorBase;

    public ItemComercializavel(String nome, double valorBase){
        this.nome = nome;
        this.valorBase = valorBase;
    }

    public abstract double calcularPrecoFinal();

    public String gerNome(){
        return nome;
    }
    public double gerValorBase(){
        return valorBase;
    }
}