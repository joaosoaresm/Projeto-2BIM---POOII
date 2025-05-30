import java.util.Scanner;

public class Hotel2bim {

    public static class Hospede {
        private String nome;
        private String email;

        public Hospede() {
        }

        public Hospede(String nome, String email) {
            this.nome = nome;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    private Hospede[] hospedes;

    public Hotel2bim(){
        hospedes = new Hospede[100]; 
    }

    public void reservar(int numero, String nome, String email) {
        if (numero < 1 || numero > 100) {
            System.out.println("Número de quarto inválido!\n");
        } else if (hospedes[numero - 1] != null) {
            System.out.println("Esse quarto já está ocupado!\n");
            System.out.print("Número do quarto (1-100): ");
            Scanner sc = new Scanner(System.in);
            int quarto = sc.nextInt();
            reservar(quarto, nome, email);
        } else{
            hospedes[numero - 1] = new Hospede(nome, email);
            System.out.println("Reserva efetuada com sucesso!\n");
        }
    }

    public void cancelar(int numero) {
        if (numero < 1 || numero > 100) {
            System.out.println("Número de quarto inválido!\n");
        } else if (hospedes[numero - 1] == null) {
            System.out.println("Este quarto já está livre.\n");
        } else {
            hospedes[numero - 1] = null;
            System.out.println("Reserva cancelada com sucesso!\n");
        }
    }

    public void listar() {
        boolean encontrou = false;
        for (int i = 0; i < hospedes.length; i++) {
            if (hospedes[i] != null) {
                System.out.println("Quarto " + (i + 1) + ": " + hospedes[i].getNome());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva feita ainda.\n");
        }
    }

    public void consultar(int numero) {
        if (numero < 1 || numero > 100) {
            System.out.println("Número de quarto inválido!\n");
        } else if (hospedes[numero - 1] == null) {
            System.out.println("Quarto vazio.\n");
        } else {
            Hospede h = hospedes[numero - 1];
            System.out.println("Nome: " + h.getNome());
            System.out.println("Email: " + h.getEmail());
        }
    }

    public void editar(int numero, String nome, String email) {
        if (numero < 1 || numero > 100) {
            System.out.println("Número de quarto inválido!\n");
        } else if (hospedes[numero - 1] == null) {
            System.out.println("Quarto vazio. Não é possível editar.\n");
        } else {
            Hospede h = hospedes[numero - 1];
            h.setNome(nome);
            h.setEmail(email);
            System.out.println("Dados atualizados com sucesso!\n");
        }
    }

    // AGORA o método main está dentro da classe
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel2bim hotel = new Hotel2bim();
        int opcao;

        do {
            System.out.println("\n\n\n--- MENU HOTEL ---");
            System.out.println("1 - Reservar quarto");
            System.out.println("2 - Consultar quarto");
            System.out.println("3 - Editar reserva");
            System.out.println("4 - Cancelar reserva");
            System.out.println("5 - Listar todos os quartos reservados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar o \n

            switch (opcao) {
                case 1 -> {
                    System.out.print("Número do quarto (1-100): ");
                    int numReserva = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome do hóspede: ");
                    String nome = sc.nextLine();
                    System.out.print("Email do hóspede: ");
                    String email = sc.nextLine();
                    hotel.reservar(numReserva, nome, email);
                }

                case 2 -> {
                    System.out.print("Número do quarto (1-100): ");
                    int numConsulta = sc.nextInt();
                    hotel.consultar(numConsulta);
                }

                case 3 -> {
                    System.out.print("Número do quarto (1-100): ");
                    int numEditar = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = sc.nextLine();
                    hotel.editar(numEditar, novoNome, novoEmail);
                }

                case 4 -> {
                    System.out.print("Número do quarto (1-100): ");
                    int numCancelar = sc.nextInt();
                    hotel.cancelar(numCancelar);
                }

                case 5 -> hotel.listar();

                case 0 -> System.out.println("Saindo... Até mais!");

                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}