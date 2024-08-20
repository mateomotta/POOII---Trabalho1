import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void inserir(Autor autor) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "INSERT INTO Autor (nome, nacionalidade) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.setString(2, autor.getNacionalidade());
        stmt.executeUpdate();
        stmt.close();
    }

    public void atualizar(Autor autor) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "UPDATE Autor SET nome = ?, nacionalidade = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.setString(2, autor.getNacionalidade());
        stmt.setInt(3, autor.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "DELETE FROM Autor WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Autor> listar() throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "SELECT * FROM Autor";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Autor> autores = new ArrayList<>();
        while (rs.next()) {
            Autor autor = new Autor();
            autor.setId(rs.getInt("id"));
            autor.setNome(rs.getString("nome"));
            autor.setNacionalidade(rs.getString("nacionalidade"));
            autores.add(autor);
        }
        rs.close();
        stmt.close();
        return autores;
    }
}
