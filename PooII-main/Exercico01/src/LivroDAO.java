import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "INSERT INTO Livro (titulo, ano_publicacao, id_autor) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setInt(2, livro.getAnoPublicacao());
        stmt.setInt(3, livro.getAutorId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void atualizar(Livro livro) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "UPDATE Livro SET titulo = ?, ano_publicacao = ?, id_autor = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setInt(2, livro.getAnoPublicacao());
        stmt.setInt(3, livro.getAutorId());
        stmt.setInt(4, livro.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "DELETE FROM Livro WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Livro> listar() throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "SELECT * FROM Livro";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Livro> livros = new ArrayList<>();
        while (rs.next()) {
            Livro livro = new Livro();
            livro.setId(rs.getInt("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
            livro.setAutorId(rs.getInt("id_autor"));
            livros.add(livro);
        }
        rs.close();
        stmt.close();
        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int autorId) throws SQLException {
        Connection conn = ConexaoBD.getConexao();
        String sql = "SELECT * FROM Livro WHERE id_autor = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, autorId);
        ResultSet rs = stmt.executeQuery();

        List<Livro> livros = new ArrayList<>();
        while (rs.next()) {
            Livro livro = new Livro();
            livro.setId(rs.getInt("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
            livro.setAutorId(rs.getInt("id_autor"));
            livros.add(livro);
        }
        rs.close();
        stmt.close();
        return livros;
    }
}
