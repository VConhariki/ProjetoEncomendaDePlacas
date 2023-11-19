package projetoencomendadeplacas.Utils.Databases;

public class Database extends DatabaseConnection{
    public void createDatabase(){
        createDb();
        createClienteSchema();
        createEncomendaSchema();
        createClienteEncomendaSchema();
    }
}
