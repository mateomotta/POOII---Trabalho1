import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    public ProjetoDAO() {
        conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO projeto (nome_projeto, local, data_inicio, data_termino) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Projeto projeto) throws SQLException {
        String sql = "UPDATE projeto SET nome_projeto = ?, local = ?, data_inicio = ?, data_termino = ? WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idProjeto) throws SQLException {
        String sql = "DELETE FROM projeto WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        }
    }

    public List<Projeto> listarTodos() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("id_projeto"));
                projeto.setNomeProjeto(rs.getString("nome_projeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getString("data_inicio"));
                projeto.setDataTermino(rs.getString("data_termino"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }

    public Projeto buscarPorId(int idProjeto) throws SQLException {
        String sql = "SELECT * FROM projeto WHERE id_projeto = ?";
        Projeto projeto = null;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    projeto = new Projeto();
                    projeto.setIdProjeto(rs.getInt("id_projeto"));
                    projeto.setNomeProjeto(rs.getString("nome_projeto"));
                    projeto.setLocal(rs.getString("local"));
                    projeto.setDataInicio(rs.getString("data_inicio"));
                    projeto.setDataTermino(rs.getString("data_termino"));
                }
            }
        }
        return projeto;
    }
}
