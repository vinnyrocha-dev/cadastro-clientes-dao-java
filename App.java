package br.com.vini.mod14;

import br.com.vini.mod14.dao.ClienteMapDAO;
import br.com.vini.mod14.dao.IClienteDAO;
import br.com.vini.mod14.domain.Cliente;


import javax.swing.*;


public class App {
    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao =  JOptionPane.showInputDialog(null,
                "Digite 1 para Cadastro, 2 para Consultar, 3 para Exclusão, 4 para Alteração ou 5 para Sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);


        while (!isOpcaoValida(opcao)){
            if (opcao == null || "".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida! digite 1 para Cadastro, 2 para Consulta, 3 para Exclusão," +
                            " 4 para Alteração ou 5 para Sair", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)){
            if (isOpcaoSair(opcao)){
                sair();
            } else if (isCadastro(opcao)){
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme exemplo: " +
                                "Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsultar(opcao)){
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o Cpf: ",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if (isAlteracao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os novos dados separados por vírgula: " +
                                "Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Alteração", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para Cadastro, 2 para Consultar, 3 para Exclusão, 4 para Alteração ou 5 para Sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private static void excluir(String dados){
        Long cpf = Long.parseLong(dados.trim());
        Cliente cliente = iClienteDAO.consultar(cpf);
            if (cliente !=null){
                iClienteDAO.excluir(cpf);
                JOptionPane.showMessageDialog(null,
                        "Cliente excluído", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cliente não encontrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
            }
    }

    private  static void alterar (String dados){
        String[] dadosAlterados = dados.split(",");
        Cliente cliente = new Cliente(dadosAlterados[0],dadosAlterados[1], dadosAlterados[2],
        dadosAlterados[3], dadosAlterados[4], dadosAlterados[5], dadosAlterados[6]);
        Cliente clienteCadastrado = iClienteDAO.consultar(cliente.getCpf());
        if (clienteCadastrado != null){
            iClienteDAO.alterar(cliente);
            JOptionPane.showMessageDialog(null,
                    "Cliente alterado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null,
                    "Cliente não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private static void cadastrar(String dados){
        String[] dadosSeparados =  dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1],
                dadosSeparados[2], dadosSeparados[3], dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado){
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String dados){
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados.trim()));
        if (cliente !=null){
            JOptionPane.showMessageDialog(null,
                    "Cliente encontrado: " + cliente.toString(),
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Cliente não encontrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private static boolean isOpcaoValida(String opcao){
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao)
                || "4".equals(opcao) || "5".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao){
        if ("5".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isAlteracao(String opcao) {
        if ("4".equals(opcao)) {                                                          
            return true;                                                                 
        }                                                                                
        return false;                                                                     
    }

    private static boolean isExclusao(String opcao){
        if ("3".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isConsultar(String opcao){
        if ("2".equals(opcao)){
            return true;
        }
        return  false;
    }

    private static boolean isCadastro(String opcao){
        if ("1".equals(opcao)){
            return true;
        }
        return false;
    }

    private static void sair(){
        JOptionPane.showMessageDialog(null, "Até logo!",
                "Sair",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

