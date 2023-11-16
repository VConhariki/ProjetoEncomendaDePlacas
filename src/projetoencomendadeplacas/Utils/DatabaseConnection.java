package projetoencomendadeplacas.Utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseConnection {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/";
    private static final String USER = "postgres";
    private static final String SENHA = "admin";
    private static final String DATABASE = "encomendaplacadb";
    private static Statement SESSAO = null;
    private static final String DELIMITER = ";;";
    private static final String BASEPATH = "src/projetoencomendadeplacas/Utils/Scripts/";

    public DatabaseConnection(){
        SESSAO = CreateStatement();
    }

    public void CreateDb(){
        try{
            Scanner scanner = ObterScript(BASEPATH + "CreateDatabase.sql");
            assert scanner != null;
            RodarScript(scanner);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void CreateClienteSchema(){
        try{
            Scanner scanner = ObterScript(BASEPATH + "CreateClienteSchema.sql");
            assert scanner != null;
            RodarScript(scanner);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void CreateEncomendaSchema(){
        try{
            Scanner scanner = ObterScript(BASEPATH + "CreateEncomendaSchema.sql");
            assert scanner != null;
            RodarScript(scanner);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //region Private Methods
    private Statement CreateStatement(){
        try{
            Class.forName(DRIVER);
            Connection conexao = DriverManager.getConnection(URL+DATABASE,USER, SENHA);
            return conexao.createStatement();
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    private Scanner ObterScript(String path) {
        try{
            File file = new File(path);
            Scanner scanner;
            scanner = new Scanner(file).useDelimiter(DELIMITER);
            return scanner;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private void RodarScript(Scanner scanner){
        while(scanner.hasNext()) {
            String rawStatement = scanner.next() + DELIMITER;
            System.out.println(rawStatement);
            try {
                SESSAO.execute(rawStatement);
            } catch (SQLException e) {
                System.out.println("Erro de sql: " + e.getMessage());
            }
        }
        scanner.close();
    }
    //endregion
}
