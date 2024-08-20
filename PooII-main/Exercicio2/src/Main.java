import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Criando instâncias dos DAOs
            ProjetoDAO projetoDAO = new ProjetoDAO();
            EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
            OperarioDAO operarioDAO = new OperarioDAO();
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            MaterialDAO materialDAO = new MaterialDAO();

            // Inserindo um novo Projeto
            Projeto projeto = new Projeto();
            projeto.setNomeProjeto("Construção do Prédio A");
            projeto.setLocal("São Paulo");
            projeto.setDataInicio("2024-01-01");
            projeto.setDataTermino("2024-12-31");
            projetoDAO.inserir(projeto);

            // Inserindo um novo Engenheiro
            Engenheiro engenheiro = new Engenheiro();
            engenheiro.setNomeEngenheiro("Carlos Silva");
            engenheiro.setEspecialidade("Civil");
            engenheiroDAO.inserir(engenheiro);

            // Inserindo um novo Operário
            Operario operario = new Operario();
            operario.setNomeOperario("José Santos");
            operario.setFuncao("Pedreiro");
            operarioDAO.inserir(operario);

            // Inserindo um novo Equipamento
            Equipamento equipamento = new Equipamento();
            equipamento.setNomeEquipamento("Escavadeira");
            equipamento.setTipo("Terra");
            equipamentoDAO.inserir(equipamento);

            // Inserindo um novo Material
            Material material = new Material();
            material.setNomeMaterial("Cimento");
            material.setQuantidade(1000);
            materialDAO.inserir(material);

            // Atualizando um Projeto
            projeto.setNomeProjeto("Construção do Prédio B");
            projetoDAO.atualizar(projeto);

            // Atualizando um Engenheiro
            engenheiro.setEspecialidade("Estrutural");
            engenheiroDAO.atualizar(engenheiro);

            // Atualizando um Operário
            operario.setFuncao("Encanador");
            operarioDAO.atualizar(operario);

            // Atualizando um Equipamento
            equipamento.setTipo("Escavação");
            equipamentoDAO.atualizar(equipamento);

            // Atualizando um Material
            material.setQuantidade(1200);
            materialDAO.atualizar(material);

            // Listando todos Projetos
            List<Projeto> projetos = projetoDAO.listarTodos();
            System.out.println("Projetos:");
            for (Projeto p : projetos) {
                System.out.println(p.getIdProjeto() + ": " + p.getNomeProjeto());
            }

            // Listando todos Engenheiros
            List<Engenheiro> engenheiros = engenheiroDAO.listarTodos();
            System.out.println("\nEngenheiros:");
            for (Engenheiro e : engenheiros) {
                System.out.println(e.getIdEngenheiro() + ": " + e.getNomeEngenheiro());
            }

            // Listando todos Operários
            List<Operario> operarios = operarioDAO.listarTodos();
            System.out.println("\nOperários:");
            for (Operario o : operarios) {
                System.out.println(o.getIdOperario() + ": " + o.getNomeOperario());
            }

            // Listando todos Equipamentos
            List<Equipamento> equipamentos = equipamentoDAO.listarTodos();
            System.out.println("\nEquipamentos:");
            for (Equipamento eq : equipamentos) {
                System.out.println(eq.getIdEquipamento() + ": " + eq.getNomeEquipamento());
            }

            // Listando todos Materiais
            List<Material> materiais = materialDAO.listarTodos();
            System.out.println("\nMateriais:");
            for (Material m : materiais) {
                System.out.println(m.getIdMaterial() + ": " + m.getNomeMaterial());
            }

            // Excluindo um Projeto (Exemplo: Excluir o projeto com ID 1)
            projetoDAO.excluir(1);

            // Excluindo um Engenheiro (Exemplo: Excluir o engenheiro com ID 1)
            engenheiroDAO.excluir(1);

            // Excluindo um Operário (Exemplo: Excluir o operário com ID 1)
            operarioDAO.excluir(1);

            // Excluindo um Equipamento (Exemplo: Excluir o equipamento com ID 1)
            equipamentoDAO.excluir(1);

            // Excluindo um Material (Exemplo: Excluir o material com ID 1)
            materialDAO.excluir(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
