# 📇 Cadastro de Clientes com Padrão DAO

Sistema de cadastro de clientes desenvolvido em **Java**, aplicando o padrão de projeto **DAO (Data Access Object)** e separação em camadas. A aplicação permite realizar as quatro operações de um **CRUD** por meio de caixas de diálogo gráficas.

Projeto iniciado durante o curso **Profissão: Especialista Backend Java** da **EBAC**. A partir da base apresentada nas aulas, o restante das funcionalidades foi implementado de forma independente.

---

## ✅ Funcionalidades

| Opção | Operação | Descrição |
|:---:|---|---|
| **1** | Cadastrar | Registra um novo cliente. Impede cadastro duplicado pelo CPF |
| **2** | Consultar | Busca um cliente pelo CPF e exibe seus dados |
| **3** | Excluir | Remove um cliente cadastrado a partir do CPF |
| **4** | Alterar | Atualiza nome, telefone, endereço, número, cidade e estado |
| **5** | Sair | Encerra a aplicação |

Também há validação do menu: qualquer opção diferente de 1 a 5 exibe uma mensagem de "Opção inválida".

---

## 🛠 Tecnologias utilizadas

| Tecnologia | Uso no projeto |
|---|---|
| **Java** | Linguagem principal da aplicação |
| **Java Swing (`JOptionPane`)** | Interface gráfica com caixas de entrada e mensagens |
| **Java Collections (`Map` / `HashMap`)** | Armazenamento dos clientes em memória, usando o CPF como chave |
| **`java.util.Collection`** | Retorno da listagem de todos os clientes |
| **`java.util.Objects`** | Implementação segura de `equals` e `hashCode` |
| **IntelliJ IDEA** | IDE utilizada no desenvolvimento |
| **Git e GitHub** | Versionamento e hospedagem do código |

---

## 🧠 Conceitos aplicados

### Padrão DAO (Data Access Object)
Toda a lógica de acesso aos dados fica isolada na camada `dao`. O restante da aplicação não sabe *como* os dados são guardados, apenas *o que* pode ser feito com eles.

### Programação orientada a interfaces
A classe `App` depende da interface `IClienteDAO`, e não da implementação `ClienteMapDAO`:

```java
private static IClienteDAO iClienteDAO;
iClienteDAO = new ClienteMapDAO();
```

Isso permite trocar o armazenamento em memória por um banco de dados no futuro criando apenas uma nova implementação da interface, sem alterar a lógica da aplicação.

### Programação Orientada a Objetos (POO)
- **Encapsulamento:** atributos `private` com acesso por getters e setters
- **Abstração:** a interface define o contrato das operações
- **Polimorfismo:** a variável do tipo `IClienteDAO` recebe um objeto `ClienteMapDAO`
- **Sobrescrita de métodos:** `equals`, `hashCode` e `toString` com `@Override`

### Identidade de objetos com `equals` e `hashCode`
Dois clientes são considerados iguais quando possuem o mesmo CPF, o que garante consistência ao trabalhar com coleções.

### Separação em camadas
Cada pacote tem uma responsabilidade única: domínio, acesso a dados e interação com o usuário.

### Outros recursos da linguagem
- Conversão de tipos com `Long.valueOf`, `Long.parseLong` e `Integer.valueOf`
- Tratamento de texto com `split(",")` e `trim()`
- Wrapper classes (`Long`, `Integer`, `Boolean`)
- Métodos auxiliares `private static` para deixar o fluxo principal legível
- Estruturas de repetição (`while`) e decisão (`if / else if`)

---

## 🏗 Arquitetura do projeto

```
┌──────────────────────────┐
│          App             │  Interface com o usuário (JOptionPane)
│   (menu e fluxo geral)   │
└────────────┬─────────────┘
             │ depende da interface
             ▼
┌──────────────────────────┐
│       IClienteDAO        │  Contrato das operações do CRUD
└────────────┬─────────────┘
             │ implementada por
             ▼
┌──────────────────────────┐
│      ClienteMapDAO       │  Armazenamento em memória com HashMap
└────────────┬─────────────┘
             │ manipula
             ▼
┌──────────────────────────┐
│         Cliente          │  Entidade de domínio
└──────────────────────────┘
```

---

## 📁 Estrutura de pastas

```
cadastro-clientes-dao-java/
├── src/
│   └── br/com/vini/mod14/
│       ├── App.java                  # Menu e fluxo da aplicação
│       ├── dao/
│       │   ├── IClienteDAO.java      # Interface com as operações do CRUD
│       │   └── ClienteMapDAO.java    # Implementação com HashMap
│       └── domain/
│           └── Cliente.java          # Entidade Cliente
└── README.md
```

---

## 💡 Como usar

1. Ao iniciar, escolha uma opção de **1 a 5** no menu.
2. Para **cadastrar** ou **alterar**, informe os dados separados por vírgula, nesta ordem:

```
   Nome, CPF, Telefone, Endereço, Número, Cidade, Estado
```

   Exemplo:

```
   João Silva, 12345678900, 21999999999, Rua das Flores, 100, Rio de Janeiro, RJ
```

3. Para **consultar** ou **excluir**, informe apenas o CPF (somente números).
4. Escolha **5** para sair.

> ⚠ Os dados ficam armazenados apenas em memória e são perdidos ao encerrar a aplicação.

---

## 📚 Aprendizados

- Entender na prática o valor de **programar para uma interface e não para uma implementação**
- Aplicar o **padrão DAO** para isolar o acesso a dados
- Usar o **HashMap** para buscas rápidas e para evitar registros duplicados
- Implementar corretamente **`equals` e `hashCode`**
- Organizar um projeto Java em **pacotes e camadas**
- Pesquisar, testar e resolver problemas de forma independente

---

## 👨‍💻 Autor

**Vinicius**

Desenvolvedor Full Stack em formação, estudante de Análise e Desenvolvimento de Sistemas.

