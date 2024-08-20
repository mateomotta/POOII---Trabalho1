import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection conexao;

    public MaterialDAO() {
        conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Material material) throws SQLException {
        String sql = "INSERT INTO material (nome_material, quantidade) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Material material) throws SQLException {
        String sql = "UPDATE material SET nome_material = ?, quantidade = ? WHERE id_material = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idMaterial) throws SQLException {
        String sql = "DELETE FROM material WHERE id_material = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        }
    }

    public List<Material> listarTodos() throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM material";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setQuantidade(rs.getInt("quantidade"));
                materiais.add(material);
            }
        }
        return materiais;
    }

    public Material buscarPorId(int idMaterial) throws SQLException {
        String sql = "SELECT * FROM material WHERE id_material = ?";
        Material material = null;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    material = new Material();
                    material.setIdMaterial(rs.getInt("id_material"));
                    material.setNomeMaterial(rs.getString("nome_material"));
                    material.setQuantidade(rs.getInt("quantidade"));
                }
            }
        }
        return material;
    }
}
