import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    private Connection conexao;

    public EquipamentoDAO() {
        conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO equipamento (nome_equipamento, tipo) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE equipamento SET nome_equipamento = ?, tipo = ? WHERE id_equipamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idEquipamento) throws SQLException {
        String sql = "DELETE FROM equipamento WHERE id_equipamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();
        }
    }

    public List<Equipamento> listarTodos() throws SQLException {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM equipamento";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("id_equipamento"));
                equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                equipamento.setTipo(rs.getString("tipo"));
                equipamentos.add(equipamento);
            }
        }
        return equipamentos;
    }

    public Equipamento buscarPorId(int idEquipamento) throws SQLException {
        String sql = "SELECT * FROM equipamento WHERE id_equipamento = ?";
        Equipamento equipamento = null;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    equipamento = new Equipamento();
                    equipamento.setIdEquipamento(rs.getInt("id_equipamento"));
                    equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                    equipamento.setTipo(rs.getString("tipo"));
                }
            }
        }
        return equipamento;
    }
}
