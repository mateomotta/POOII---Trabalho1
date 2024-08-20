import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    private Connection conexao;

    public OperarioDAO() {
        conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Operario operario) throws SQLException {
        String sql = "INSERT INTO operario (nome_operario, funcao) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Operario operario) throws SQLException {
        String sql = "UPDATE operario SET nome_operario = ?, funcao = ? WHERE id_operario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idOperario) throws SQLException {
        String sql = "DELETE FROM operario WHERE id_operario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            stmt.executeUpdate();
        }
    }

    public List<Operario> listarTodos() throws SQLException {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM operario";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("id_operario"));
                operario.setNomeOperario(rs.getString("nome_operario"));
                operario.setFuncao(rs.getString("funcao"));
                operarios.add(operario);
            }
        }
        return operarios;
    }

    public Operario buscarPorId(int idOperario) throws SQLException {
        String sql = "SELECT * FROM operario WHERE id_operario = ?";
        Operario operario = null;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    operario = new Operario();
                    operario.setIdOperario(rs.getInt("id_operario"));
                    operario.setNomeOperario(rs.getString("nome_operario"));
                    operario.setFuncao(rs.getString("funcao"));
                }
            }
        }
        return operario;
    }
}
