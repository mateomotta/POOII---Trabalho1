import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Exemplo de código que pode lançar SQLException
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sua_base_de_dados", "usuario", "senha");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
