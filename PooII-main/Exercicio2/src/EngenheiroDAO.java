import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    private Connection conexao;

    public EngenheiroDAO() {
        conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Engenheiro engenheiro) throws SQLException {
        String sql = "INSERT INTO engenheiro (nome_engenheiro, especialidade) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Engenheiro engenheiro) throws SQLException {
        String sql = "UPDATE engenheiro SET nome_engenheiro = ?, especialidade = ? WHERE id_engenheiro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idEngenheiro) throws SQLException {
        String sql = "DELETE FROM engenheiro WHERE id_engenheiro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();
        }
    }

    public List<Engenheiro> listarTodos() throws SQLException {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM engenheiro";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("id_engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("nome_engenheiro"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));
                engenheiros.add(engenheiro);
            }
        }
        return engenheiros;
    }

    public Engenheiro buscarPorId(int idEngenheiro) throws SQLException {
        String sql = "SELECT * FROM engenheiro WHERE id_engenheiro = ?";
        Engenheiro engenheiro = null;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    engenheiro = new Engenheiro();
                    engenheiro.setIdEngenheiro(rs.getInt("id_engenheiro"));
                    engenheiro.setNomeEngenheiro(rs.getString("nome_engenheiro"));
                    engenheiro.setEspecialidade(rs.getString("especialidade"));
                }
            }
        }
        return engenheiro;
    }
}
