import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe abstrata para representar um evento
abstract class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    // Getters e setters para os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método abstrato para verificar se o evento está ocorrendo no momento
    public abstract boolean estaOcorrendoNoMomento();

    // Método abstrato para retornar uma String com as informações do evento
    public abstract String getInformacoes();
}

// Classe concreta para representar um evento
class EventoConcreto extends Evento {
    public EventoConcreto(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        super(nome, endereco, categoria, horario, descricao);
    }

    @Override
    public boolean estaOcorrendoNoMomento() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(getHorario()) && agora.isBefore(getHorario().plusHours(2)); // Exemplo: evento dura 2 horas
    }

    @Override
    public String getInformacoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "Nome: " + getNome() + "\n" +
               "Endereço: " + getEndereco() + "\n" +
               "Categoria: " + getCategoria() + "\n" +
               "Horário: " + getHorario().format(formatter) + "\n" +
               "Descrição: " + getDescricao();
    }
}

// Classe para representar um usuário
class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private List<Evento> eventosConfirmados;

    public Usuario(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.eventosConfirmados = new ArrayList<>();
    }

    // Getters e setters para os atributos
    // ...

    // Método para adicionar um evento aos eventos confirmados
    public void adicionarEventoConfirmado(Evento evento) {
        this.eventosConfirmados.add(evento);
    }

    // Método para remover um evento dos eventos confirmados
    public void removerEventoConfirmado(Evento evento) {
        this.eventosConfirmados.remove(evento);
    }

    // Método para retornar uma String com as informações do usuário e seus eventos confirmados
    public String getInformacoes() {
        StringBuilder informacoes = new StringBuilder();
        informacoes.append("Nome: ").append(this.nome).append("\n");
        informacoes.append("Email: ").append(this.email).append("\n");
        informacoes.append("Telefone: ").append(this.telefone).append("\n");
        informacoes.append("\nEventos Confirmados:\n");
        for (Evento evento : eventosConfirmados) {
            informacoes.append(evento.getInformacoes()).append("\n");
        }
        return informacoes.toString();
    }
}

// Classe principal para gerenciar o sistema de eventos
public class SistemaEventosPortoAlegre {

    private List<Evento> eventosCadastrados;
    private List<Usuario> usuarios;
    private Scanner scanner;

    public SistemaEventosPortoAlegre() {
        this.eventosCadastrados = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Método para carregar eventos do arquivo "events.data"
    public void carregarEventosDoArquivo() {
        System.out.println("Carregando eventos do arquivo...");
        // Adicione a lógica de leitura do arquivo e criação de eventos aqui
    }

    // Método para salvar eventos no arquivo "events.data"
    public void salvarEventosNoArquivo() {
        System.out.println("Salvando eventos no arquivo...");
        // Adicione a lógica de escrita no arquivo aqui
    }

    // Método para cadastrar um novo usuário
    public void cadastrarUsuario() {
        System.out.println("Cadastrando novo usuário...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        Usuario usuario = new Usuario(nome, email, telefone);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // Método para cadastrar um novo evento
    public void cadastrarEvento() {
        System.out.println("Cadastrando novo evento...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Horário (dd-MM-yyyy HH:mm): ");
        String horarioStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime horario = LocalDateTime.parse(horarioStr, formatter);
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        Evento evento = new EventoConcreto(nome, endereco, categoria, horario, descricao);
        eventosCadastrados.add(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }

    // Método para consultar eventos cadastrados
    public void consultarEventos() {
        System.out.println("Consultando eventos cadastrados...");
        if (eventosCadastrados.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            for (Evento evento : eventosCadastrados) {
                System.out.println(evento.getInformacoes());
            }
        }
    }

    // Método para confirmar presença em um evento
    // Método para confirmar presença em um evento
public void confirmarPresencaEmEvento() {
    System.out.println("Confirmando presença em evento...");

    // Exibir lista de usuários
    System.out.println("Escolha um usuário:");
    for (int i = 0; i < usuarios.size(); i++) {
        System.out.println((i + 1) + ". " + usuarios.get(i).getInformacoes());
    }
    System.out.print("Digite o número do usuário: ");
    int usuarioIndice = Integer.parseInt(scanner.nextLine()) - 1;
    
    // Verificar se o índice do usuário é válido
    if (usuarioIndice < 0 || usuarioIndice >= usuarios.size()) {
        System.out.println("Usuário inválido!");
        return;
    }

    Usuario usuario = usuarios.get(usuarioIndice);

    // Exibir lista de eventos cadastrados
    System.out.println("Escolha um evento:");
    for (int i = 0; i < eventosCadastrados.size(); i++) {
        System.out.println((i + 1) + ". " + eventosCadastrados.get(i).getNome());
    }
    System.out.print("Digite o número do evento: ");
    int eventoIndice = Integer.parseInt(scanner.nextLine()) - 1;

    // Verificar se o índice do evento é válido
    if (eventoIndice < 0 || eventoIndice >= eventosCadastrados.size()) {
        System.out.println("Evento inválido!");
        return;
    }

    Evento evento = eventosCadastrados.get(eventoIndice);

    // Confirmar presença adicionando o evento à lista de eventos confirmados do usuário
    usuario.adicionarEventoConfirmado(evento);
    System.out.println("Presença confirmada com sucesso!");
}


    // Método principal para execução do programa
    public static void main(String[] args) {
        SistemaEventosPortoAlegre sistema = new SistemaEventosPortoAlegre();
        sistema.executar();
    }

    // Método para executar o sistema
    public void executar() {
        System.out.println("Bem-vindo ao Sistema de Eventos de Porto Alegre!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        eventosCadastrados.add(new EventoConcreto("Evento 1", "Endereço 1", "Categoria 1", LocalDateTime.parse("26-06-2024 18:00", formatter), "Descrição do Evento 1"));
        eventosCadastrados.add(new EventoConcreto("Evento 2", "Endereço 2", "Categoria 2", LocalDateTime.parse("27-06-2024 19:00", formatter), "Descrição do Evento 2"));
        
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Carregar eventos do arquivo");
            System.out.println("2. Salvar eventos no arquivo");
            System.out.println("3. Cadastrar novo usuário");
            System.out.println("4. Cadastrar novo evento");
            System.out.println("5. Consultar eventos cadastrados");
            System.out.println("6. Confirmar presença em evento");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opçao: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    carregarEventosDoArquivo();
                    break;
                case 2:
                    salvarEventosNoArquivo();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    cadastrarEvento();
                    break;
                case 5:
                    consultarEventos();
                    break;
                case 6:
                    confirmarPresencaEmEvento();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        System.out.println("Obrigado por usar o Sistema de Eventos de Porto Alegre!");
    }
}
