import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instancia;
    private Connection conexao;

    private ConexaoBD() {
        try {
            // Registra o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_obras", "usuario", "senha");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexaoBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }
}
