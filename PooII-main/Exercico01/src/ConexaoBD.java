import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static Connection conexao;

    private ConexaoBD() {}

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            String url = "jdbc:mysql://localhost:3306/sua_base_de_dados";
            String usuario = "seu_usuario";
            String senha = "sua_senha";
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        return conexao;
    }

    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
