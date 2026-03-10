package com.donation.api.config;

import com.donation.api.entity.User;
import com.donation.api.entity.Donation;
import com.donation.api.entity.Match;
import com.donation.api.entity.enums.FoodCategory;
import com.donation.api.entity.enums.UserRole;
import com.donation.api.repository.UserRepository;
import com.donation.api.repository.DonationRepository;
import com.donation.api.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DonationRepository donationRepository;
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar se já existem dados no banco
        if (userRepository.count() > 0) {
            System.out.println("=== Dados já existem no banco, pulando inicialização ===");
            return;
        }
        
        System.out.println("=== Iniciando população do banco de dados ===");
        
        // Criar usuários
        createUsers();
        
        // Criar doações
        createDonations();
        
        // Criar matches
        createMatches();
        
        System.out.println("=== População do banco concluída com sucesso ===");
    }
    
    private void createUsers() {
        System.out.println("Criando usuários...");

        String defaultPassword = passwordEncoder.encode("123456");

        // ── ADMIN ──────────────────────────────────────────────────────────────
        User admin = new User();
        admin.setName("Administrador Sistema");
        admin.setEmail("admin@doacoes.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setPhone("(11) 90000-0000");
        admin.setAddress("Av. Paulista, 1000");
        admin.setCity("São Paulo");
        admin.setState("SP");
        admin.setZipCode("01310-100");
        admin.setBio("Conta administrativa da plataforma de doações.");
        admin.setRole(UserRole.ADMIN);
        admin.setEnabled(true);
        admin.setCreatedAt(LocalDateTime.now().minusMonths(6));
        userRepository.save(admin);

        // ── DOADORES ───────────────────────────────────────────────────────────
        User joao = new User();
        joao.setName("João Silva Santos");
        joao.setEmail("joao.silva@email.com");
        joao.setPassword(defaultPassword);
        joao.setPhone("(11) 99876-5432");
        joao.setAddress("Rua das Flores, 123");
        joao.setCity("São Paulo");
        joao.setState("SP");
        joao.setZipCode("01234-567");
        joao.setBio("Supermercadista local que doa alimentos com prazo de validade próximo do vencimento mas ainda adequados ao consumo. Acredito que nenhum alimento deve ser desperdiçado!");
        joao.setRole(UserRole.DONOR);
        joao.setEnabled(true);
        joao.setCreatedAt(LocalDateTime.now().minusMonths(5));
        userRepository.save(joao);

        User maria = new User();
        maria.setName("Maria Fernanda Costa");
        maria.setEmail("maria.costa@email.com");
        maria.setPassword(defaultPassword);
        maria.setPhone("(21) 98765-4321");
        maria.setAddress("Av. Copacabana, 456");
        maria.setCity("Rio de Janeiro");
        maria.setState("RJ");
        maria.setZipCode("22070-000");
        maria.setBio("Professora aposentada e cozinheira amadora. Frequentemente doo marmitas prontas e alimentos excedentes das minhas receitas.");
        maria.setRole(UserRole.DONOR);
        maria.setEnabled(true);
        maria.setCreatedAt(LocalDateTime.now().minusMonths(4));
        userRepository.save(maria);

        User carlos = new User();
        carlos.setName("Carlos Eduardo Oliveira");
        carlos.setEmail("carlos.oliveira@email.com");
        carlos.setPassword(defaultPassword);
        carlos.setPhone("(31) 97654-3210");
        carlos.setAddress("Rua do Comércio, 789");
        carlos.setCity("Belo Horizonte");
        carlos.setState("MG");
        carlos.setZipCode("30120-000");
        carlos.setBio("Comerciante de hortifruti. Doo regularmente frutas, verduras e legumes frescos que não foram vendidos no dia.");
        carlos.setRole(UserRole.DONOR);
        carlos.setEnabled(true);
        carlos.setCreatedAt(LocalDateTime.now().minusMonths(4));
        userRepository.save(carlos);

        User ana = new User();
        ana.setName("Ana Paula Ribeiro");
        ana.setEmail("ana.ribeiro@email.com");
        ana.setPassword(defaultPassword);
        ana.setPhone("(47) 96543-2109");
        ana.setAddress("Rua Santa Catarina, 321");
        ana.setCity("Blumenau");
        ana.setState("SC");
        ana.setZipCode("89010-000");
        ana.setBio("Nutricionista voluntária. Doo cestas básicas e alimentos funcionais para famílias em situação de vulnerabilidade.");
        ana.setRole(UserRole.DONOR);
        ana.setEnabled(true);
        ana.setCreatedAt(LocalDateTime.now().minusMonths(3));
        userRepository.save(ana);

        User roberto = new User();
        roberto.setName("Roberto Mendes Silva");
        roberto.setEmail("roberto.mendes@email.com");
        roberto.setPassword(defaultPassword);
        roberto.setPhone("(85) 95432-1098");
        roberto.setAddress("Av. Beira Mar, 654");
        roberto.setCity("Fortaleza");
        roberto.setState("CE");
        roberto.setZipCode("60165-000");
        roberto.setBio("Restauranteiro aposentado. Doo alimentos não perecíveis e conservas que ainda estão dentro da validade.");
        roberto.setRole(UserRole.DONOR);
        roberto.setEnabled(true);
        roberto.setCreatedAt(LocalDateTime.now().minusMonths(3));
        userRepository.save(roberto);

        User fabio = new User();
        fabio.setName("Fábio Torres Neto");
        fabio.setEmail("fabio.torres@email.com");
        fabio.setPassword(defaultPassword);
        fabio.setPhone("(51) 94567-8901");
        fabio.setAddress("Rua dos Andradas, 500");
        fabio.setCity("Porto Alegre");
        fabio.setState("RS");
        fabio.setZipCode("90020-010");
        fabio.setBio("Padeiro há 20 anos. Todo fim de tarde doo pães, brioches e bolos que não foram vendidos na padaria.");
        fabio.setRole(UserRole.DONOR);
        fabio.setEnabled(true);
        fabio.setCreatedAt(LocalDateTime.now().minusMonths(2));
        userRepository.save(fabio);

        User camila = new User();
        camila.setName("Camila Souza Alves");
        camila.setEmail("camila.alves@email.com");
        camila.setPassword(defaultPassword);
        camila.setPhone("(71) 93456-7890");
        camila.setAddress("Estrada do Coco, 200");
        camila.setCity("Salvador");
        camila.setState("BA");
        camila.setZipCode("41700-000");
        camila.setBio("Produtora rural familiar. Cultivo frutas tropicais e legumes orgânicos. Doo excedentes da safra que não conseguem ser comercializados.");
        camila.setRole(UserRole.DONOR);
        camila.setEnabled(true);
        camila.setCreatedAt(LocalDateTime.now().minusMonths(2));
        userRepository.save(camila);

        User marcelo = new User();
        marcelo.setName("Marcelo Lima Costa");
        marcelo.setEmail("marcelo.lima@email.com");
        marcelo.setPassword(defaultPassword);
        marcelo.setPhone("(41) 92345-6789");
        marcelo.setAddress("Rua XV de Novembro, 800");
        marcelo.setCity("Curitiba");
        marcelo.setState("PR");
        marcelo.setZipCode("80020-310");
        marcelo.setBio("Chef de cozinha e proprietário de bufê. Doo refeições prontas excedentes de eventos e ingredientes não utilizados.");
        marcelo.setRole(UserRole.DONOR);
        marcelo.setEnabled(true);
        marcelo.setCreatedAt(LocalDateTime.now().minusMonths(1));
        userRepository.save(marcelo);

        // ── SOLICITANTES ───────────────────────────────────────────────────────
        User lucia = new User();
        lucia.setName("Lúcia Maria Santos");
        lucia.setEmail("lucia.santos@email.com");
        lucia.setPassword(defaultPassword);
        lucia.setPhone("(11) 94321-0987");
        lucia.setAddress("Rua da Esperança, 147");
        lucia.setCity("São Paulo");
        lucia.setState("SP");
        lucia.setZipCode("08100-000");
        lucia.setBio("Mãe solteira de dois filhos. Trabalho como diarista e preciso de ajuda com alimentos básicos para minha família.");
        lucia.setRole(UserRole.REQUESTER);
        lucia.setEnabled(true);
        lucia.setCreatedAt(LocalDateTime.now().minusMonths(4));
        userRepository.save(lucia);

        User pedro = new User();
        pedro.setName("Pedro Henrique Alves");
        pedro.setEmail("pedro.alves@email.com");
        pedro.setPassword(defaultPassword);
        pedro.setPhone("(21) 93210-9876");
        pedro.setAddress("Rua Nova Esperança, 258");
        pedro.setCity("Rio de Janeiro");
        pedro.setState("RJ");
        pedro.setZipCode("21040-000");
        pedro.setBio("Estudante universitário em situação de vulnerabilidade. Preciso de alimentos não perecíveis e às vezes de refeições prontas.");
        pedro.setRole(UserRole.REQUESTER);
        pedro.setEnabled(true);
        pedro.setCreatedAt(LocalDateTime.now().minusMonths(3));
        userRepository.save(pedro);

        User sandra = new User();
        sandra.setName("Sandra Regina Lima");
        sandra.setEmail("sandra.lima@email.com");
        sandra.setPassword(defaultPassword);
        sandra.setPhone("(31) 92109-8765");
        sandra.setAddress("Rua do Progresso, 369");
        sandra.setCity("Belo Horizonte");
        sandra.setState("MG");
        sandra.setZipCode("31030-000");
        sandra.setBio("Cuidadora de idosos. Mãe de três filhos, preciso de alimentos básicos e laticínios para as crianças.");
        sandra.setRole(UserRole.REQUESTER);
        sandra.setEnabled(true);
        sandra.setCreatedAt(LocalDateTime.now().minusMonths(3));
        userRepository.save(sandra);

        User jose = new User();
        jose.setName("José Carlos Ferreira");
        jose.setEmail("jose.ferreira@email.com");
        jose.setPassword(defaultPassword);
        jose.setPhone("(47) 91098-7654");
        jose.setAddress("Rua da Solidariedade, 741");
        jose.setCity("Blumenau");
        jose.setState("SC");
        jose.setZipCode("89020-000");
        jose.setBio("Desempregado há 6 meses. Pai de família precisando de cestas básicas para sustentar os filhos.");
        jose.setRole(UserRole.REQUESTER);
        jose.setEnabled(true);
        jose.setCreatedAt(LocalDateTime.now().minusMonths(2));
        userRepository.save(jose);

        User francisca = new User();
        francisca.setName("Francisca Silva Sousa");
        francisca.setEmail("francisca.sousa@email.com");
        francisca.setPassword(defaultPassword);
        francisca.setPhone("(85) 90987-6543");
        francisca.setAddress("Rua do Futuro, 852");
        francisca.setCity("Fortaleza");
        francisca.setState("CE");
        francisca.setZipCode("60180-000");
        francisca.setBio("Avó cuidando de 4 netos. Preciso de alimentos não perecíveis e leite para as crianças.");
        francisca.setRole(UserRole.REQUESTER);
        francisca.setEnabled(true);
        francisca.setCreatedAt(LocalDateTime.now().minusMonths(2));
        userRepository.save(francisca);

        User beatriz = new User();
        beatriz.setName("Beatriz Santos Moreira");
        beatriz.setEmail("beatriz.moreira@email.com");
        beatriz.setPassword(defaultPassword);
        beatriz.setPhone("(71) 89876-5432");
        beatriz.setAddress("Rua da Integração, 100");
        beatriz.setCity("Salvador");
        beatriz.setState("BA");
        beatriz.setZipCode("40270-000");
        beatriz.setBio("Família em situação de vulnerabilidade social. Mãe de quatro filhos pequenos, busco alimentos básicos e nutritivos.");
        beatriz.setRole(UserRole.REQUESTER);
        beatriz.setEnabled(true);
        beatriz.setCreatedAt(LocalDateTime.now().minusMonths(1));
        userRepository.save(beatriz);

        User daniel = new User();
        daniel.setName("Daniel Gomes Pinheiro");
        daniel.setEmail("daniel.pinheiro@email.com");
        daniel.setPassword(defaultPassword);
        daniel.setPhone("(51) 88765-4321");
        daniel.setAddress("Rua Voluntários da Pátria, 300");
        daniel.setCity("Porto Alegre");
        daniel.setState("RS");
        daniel.setZipCode("90040-000");
        daniel.setBio("Trabalhador informal. Sustento minha família com renda variável e aceito doações de alimentos não perecíveis e pães.");
        daniel.setRole(UserRole.REQUESTER);
        daniel.setEnabled(true);
        daniel.setCreatedAt(LocalDateTime.now().minusMonths(1));
        userRepository.save(daniel);

        User renata = new User();
        renata.setName("Renata Alves Mendonça");
        renata.setEmail("renata.mendonca@email.com");
        renata.setPassword(defaultPassword);
        renata.setPhone("(41) 87654-3210");
        renata.setAddress("Rua da Cidadania, 50");
        renata.setCity("Curitiba");
        renata.setState("PR");
        renata.setZipCode("81010-000");
        renata.setBio("Mãe de cinco filhos, marido em tratamento de saúde. Família numerosa em dificuldade financeira temporária.");
        renata.setRole(UserRole.REQUESTER);
        renata.setEnabled(true);
        renata.setCreatedAt(LocalDateTime.now().minusWeeks(3));
        userRepository.save(renata);

        System.out.println("Usuários criados: " + userRepository.count());
    }

    private void createDonations() {
        System.out.println("Criando doações...");

        List<User> donors = userRepository.findAll().stream()
            .filter(user -> user.getRole() == UserRole.DONOR)
            .toList();

        if (donors.isEmpty()) {
            System.out.println("Nenhum doador encontrado!");
            return;
        }

        User joao    = donors.get(0); // SP
        User maria   = donors.get(1); // RJ
        User carlos  = donors.get(2); // MG
        User ana     = donors.get(3); // SC
        User roberto = donors.get(4); // CE
        User fabio   = donors.get(5); // RS
        User camila  = donors.get(6); // BA
        User marcelo = donors.get(7); // PR

        // ── João (SP) ──────────────────────────────────────────────────────────
        Donation d1 = new Donation();
        d1.setTitle("Cesta Básica Completa");
        d1.setDescription("Cesta com arroz 5 kg, feijão 1 kg, óleo 2 L, macarrão 500 g, açúcar 1 kg, sal 1 kg e farinha de trigo 1 kg. Todos os produtos dentro do prazo de validade.");
        d1.setCategory(FoodCategory.GRAOS_CEREAIS);
        d1.setQuantity(5);
        d1.setPerishable(false);
        d1.setExpirationDate(LocalDate.now().plusMonths(6));
        d1.setStorageInstructions("Manter em local seco e arejado, longe de umidade.");
        d1.setLocation("Próximo ao Shopping Eldorado");
        d1.setCity("São Paulo");
        d1.setState("SP");
        d1.setZipCode("01234-567");
        d1.setStatus(Donation.DonationStatus.AVAILABLE);
        d1.setPickupInstructions("Disponível de segunda a sexta, das 18h às 21h. Favor entrar em contato antes.");
        d1.setExpiresAt(LocalDateTime.now().plusDays(30));
        d1.setCreatedAt(LocalDateTime.now().minusDays(10));
        d1.setDonor(joao);
        donationRepository.save(d1);

        Donation d2 = new Donation();
        d2.setTitle("Arroz Branco e Lentilhas");
        d2.setDescription("Pacotes de arroz branco tipo 1 (5 kg) e lentilhas (1 kg). Excedente do estoque do supermercado, ainda com boa validade.");
        d2.setCategory(FoodCategory.GRAOS_CEREAIS);
        d2.setQuantity(10);
        d2.setPerishable(false);
        d2.setExpirationDate(LocalDate.now().plusMonths(8));
        d2.setStorageInstructions("Armazenar em local fresco e seco.");
        d2.setLocation("Supermercado Bom Preço - Vila Madalena");
        d2.setCity("São Paulo");
        d2.setState("SP");
        d2.setZipCode("05435-000");
        d2.setStatus(Donation.DonationStatus.RESERVED);
        d2.setPickupInstructions("Combinar horário por telefone. Solicitar pelo nome João na entrada.");
        d2.setExpiresAt(LocalDateTime.now().plusDays(45));
        d2.setCreatedAt(LocalDateTime.now().minusDays(5));
        d2.setDonor(joao);
        donationRepository.save(d2);

        Donation d3 = new Donation();
        d3.setTitle("Frutas Variadas da Estação");
        d3.setDescription("Caixas com bananas-prata, maçãs Fuji, peras-williams e uvas rubi. Produção local colhida ontem.");
        d3.setCategory(FoodCategory.HORTIFRUTI);
        d3.setQuantity(4);
        d3.setPerishable(true);
        d3.setExpirationDate(LocalDate.now().plusDays(5));
        d3.setStorageInstructions("Conservar em local fresco. Não empilhar as caixas.");
        d3.setLocation("Feira Central - Bairro da Liberdade");
        d3.setCity("São Paulo");
        d3.setState("SP");
        d3.setZipCode("01501-000");
        d3.setStatus(Donation.DonationStatus.AVAILABLE);
        d3.setPickupInstructions("Retirada urgente — alimentos perecíveis. Contato via WhatsApp apenas.");
        d3.setExpiresAt(LocalDateTime.now().plusDays(3));
        d3.setCreatedAt(LocalDateTime.now().minusDays(1));
        d3.setDonor(joao);
        donationRepository.save(d3);

        // ── Maria (RJ) ─────────────────────────────────────────────────────────
        Donation d4 = new Donation();
        d4.setTitle("Leite Integral e Derivados");
        d4.setDescription("12 caixas de leite integral (1 L), iogurte natural (500 g) e queijo minas frescal (400 g). Produtos de qualidade, recém comprados.");
        d4.setCategory(FoodCategory.LATICINIOS);
        d4.setQuantity(12);
        d4.setPerishable(true);
        d4.setExpirationDate(LocalDate.now().plusDays(10));
        d4.setStorageInstructions("Manter refrigerado entre 2 °C e 8 °C. Consumir após aberto em até 3 dias.");
        d4.setLocation("Copacabana — próximo à praia");
        d4.setCity("Rio de Janeiro");
        d4.setState("RJ");
        d4.setZipCode("22070-000");
        d4.setStatus(Donation.DonationStatus.AVAILABLE);
        d4.setPickupInstructions("Retirada preferencialmente pela manhã. Moro a 100 m da estação do metrô.");
        d4.setExpiresAt(LocalDateTime.now().plusDays(7));
        d4.setCreatedAt(LocalDateTime.now().minusDays(2));
        d4.setDonor(maria);
        donationRepository.save(d4);

        Donation d5 = new Donation();
        d5.setTitle("Enlatados e Conservas Sortidos");
        d5.setDescription("Sardinhas em lata, atum sólido, milho verde, ervilhas, molho de tomate e extrato de tomate — todos com validade acima de 1 ano.");
        d5.setCategory(FoodCategory.ENLATADOS_CONSERVAS);
        d5.setQuantity(24);
        d5.setPerishable(false);
        d5.setExpirationDate(LocalDate.now().plusYears(1));
        d5.setStorageInstructions("Armazenar em local seco e fresco, longe de luz solar direta.");
        d5.setLocation("Copacabana — Zona Sul");
        d5.setCity("Rio de Janeiro");
        d5.setState("RJ");
        d5.setZipCode("22070-000");
        d5.setStatus(Donation.DonationStatus.COMPLETED);
        d5.setPickupInstructions("Já retirado. Doação concluída com sucesso.");
        d5.setExpiresAt(LocalDateTime.now().plusDays(60));
        d5.setCreatedAt(LocalDateTime.now().minusMonths(1));
        d5.setDonor(maria);
        donationRepository.save(d5);

        Donation d6 = new Donation();
        d6.setTitle("Marmitas Caseiras — Almoço Solidário");
        d6.setDescription("Marmitas com arroz integral, feijão carioca, frango grelhado ao limão e salada de folhas. Preparadas esta manhã com ingredientes frescos.");
        d6.setCategory(FoodCategory.REFEICAO_PRONTA);
        d6.setQuantity(10);
        d6.setPerishable(true);
        d6.setExpirationDate(LocalDate.now());
        d6.setStorageInstructions("Manter refrigerado. Aquecer antes de consumir. Consumir no mesmo dia.");
        d6.setLocation("Tijuca — próximo ao metrô Saens Peña");
        d6.setCity("Rio de Janeiro");
        d6.setState("RJ");
        d6.setZipCode("20530-000");
        d6.setStatus(Donation.DonationStatus.AVAILABLE);
        d6.setPickupInstructions("Retirar até as 15h de hoje. Após esse horário as marmitas não estarão mais disponíveis.");
        d6.setExpiresAt(LocalDateTime.now().plusHours(6));
        d6.setCreatedAt(LocalDateTime.now());
        d6.setDonor(maria);
        donationRepository.save(d6);

        // ── Carlos (MG) ────────────────────────────────────────────────────────
        Donation d7 = new Donation();
        d7.setTitle("Verduras Frescas — Couve, Espinafre e Alface");
        d7.setDescription("Maços de couve-manteiga, espinafre e alface crespa, colhidos esta manhã na horta. Ideal para consumo imediato.");
        d7.setCategory(FoodCategory.HORTIFRUTI);
        d7.setQuantity(15);
        d7.setPerishable(true);
        d7.setExpirationDate(LocalDate.now().plusDays(3));
        d7.setStorageInstructions("Refrigerar. Lavar somente antes de consumir.");
        d7.setLocation("Mercado Central de BH — Banca 42");
        d7.setCity("Belo Horizonte");
        d7.setState("MG");
        d7.setZipCode("30120-000");
        d7.setStatus(Donation.DonationStatus.AVAILABLE);
        d7.setPickupInstructions("Retirar pelo mercado, das 7h às 17h. Perguntar pelo Carlos.");
        d7.setExpiresAt(LocalDateTime.now().plusDays(2));
        d7.setCreatedAt(LocalDateTime.now().minusHours(3));
        d7.setDonor(carlos);
        donationRepository.save(d7);

        Donation d8 = new Donation();
        d8.setTitle("Proteínas — Frango e Carne Moída");
        d8.setDescription("Peças de frango inteiro congelado (1,5 kg cada) e pacotes de carne moída bovina (500 g). Devidamente embalados e rotulados.");
        d8.setCategory(FoodCategory.PROTEINAS);
        d8.setQuantity(6);
        d8.setPerishable(true);
        d8.setExpirationDate(LocalDate.now().plusDays(30));
        d8.setStorageInstructions("Manter congelado a -18 °C. Após descongelar, não recongelar. Consumir em até 24 h.");
        d8.setLocation("Savassi — BH");
        d8.setCity("Belo Horizonte");
        d8.setState("MG");
        d8.setZipCode("30130-010");
        d8.setStatus(Donation.DonationStatus.RESERVED);
        d8.setPickupInstructions("Solicitação aprovada. Retirar na quarta-feira de manhã.");
        d8.setExpiresAt(LocalDateTime.now().plusDays(25));
        d8.setCreatedAt(LocalDateTime.now().minusDays(3));
        d8.setDonor(carlos);
        donationRepository.save(d8);

        // ── Ana (SC) ───────────────────────────────────────────────────────────
        Donation d9 = new Donation();
        d9.setTitle("Temperos e Condimentos Naturais");
        d9.setDescription("Kit com orégano, canela, pimenta-do-reino, cúrcuma, cominho e ervas da Provence. Produtos naturais sem conservantes.");
        d9.setCategory(FoodCategory.TEMPEROS_CONDIMENTOS);
        d9.setQuantity(8);
        d9.setPerishable(false);
        d9.setExpirationDate(LocalDate.now().plusMonths(12));
        d9.setStorageInstructions("Armazenar em potes fechados, longe de luz e calor.");
        d9.setLocation("Centro — Blumenau");
        d9.setCity("Blumenau");
        d9.setState("SC");
        d9.setZipCode("89010-000");
        d9.setStatus(Donation.DonationStatus.AVAILABLE);
        d9.setPickupInstructions("Disponível aos fins de semana, das 9h às 12h.");
        d9.setExpiresAt(LocalDateTime.now().plusDays(60));
        d9.setCreatedAt(LocalDateTime.now().minusDays(7));
        d9.setDonor(ana);
        donationRepository.save(d9);

        Donation d10 = new Donation();
        d10.setTitle("Cesta Nutricional para Crianças");
        d10.setDescription("Cesta especial com leite em pó integral (400 g), achocolatado em pó, aveia em flocos, mel silvestre e biscoitos integrais. Ideal para crianças em fase de crescimento.");
        d10.setCategory(FoodCategory.OUTROS);
        d10.setQuantity(4);
        d10.setPerishable(false);
        d10.setExpirationDate(LocalDate.now().plusMonths(4));
        d10.setStorageInstructions("Manter em local seco e fresco.");
        d10.setLocation("Bairro Velha — Blumenau");
        d10.setCity("Blumenau");
        d10.setState("SC");
        d10.setZipCode("89036-000");
        d10.setStatus(Donation.DonationStatus.AVAILABLE);
        d10.setPickupInstructions("Combinar pelo telefone com antecedência de 1 dia.");
        d10.setExpiresAt(LocalDateTime.now().plusDays(40));
        d10.setCreatedAt(LocalDateTime.now().minusDays(4));
        d10.setDonor(ana);
        donationRepository.save(d10);

        // ── Roberto (CE) ───────────────────────────────────────────────────────
        Donation d11 = new Donation();
        d11.setTitle("Bebidas — Sucos Naturais e Água de Coco");
        d11.setDescription("Caixinhas de suco de caju, maracujá e manga (200 mL cada) e garrafinhas de água de coco. Todos dentro do prazo.");
        d11.setCategory(FoodCategory.BEBIDAS);
        d11.setQuantity(30);
        d11.setPerishable(false);
        d11.setExpirationDate(LocalDate.now().plusMonths(3));
        d11.setStorageInstructions("Refrigerar após aberto. Consumir em até 24 h após aberto.");
        d11.setLocation("Meireles — Fortaleza");
        d11.setCity("Fortaleza");
        d11.setState("CE");
        d11.setZipCode("60165-000");
        d11.setStatus(Donation.DonationStatus.AVAILABLE);
        d11.setPickupInstructions("Retirada a qualquer hora, das 8h às 20h. Ligar antes.");
        d11.setExpiresAt(LocalDateTime.now().plusDays(50));
        d11.setCreatedAt(LocalDateTime.now().minusDays(6));
        d11.setDonor(roberto);
        donationRepository.save(d11);

        Donation d12 = new Donation();
        d12.setTitle("Feijão Carioca e Grão-de-Bico");
        d12.setDescription("Sacos de feijão carioca tipo 1 (2 kg) e grão-de-bico (500 g). Excedente de estoque do antigo restaurante, ainda com validade.");
        d12.setCategory(FoodCategory.GRAOS_CEREAIS);
        d12.setQuantity(12);
        d12.setPerishable(false);
        d12.setExpirationDate(LocalDate.now().plusMonths(5));
        d12.setStorageInstructions("Guardar em pote fechado ou saco bem vedado, local seco.");
        d12.setLocation("Centro — Fortaleza");
        d12.setCity("Fortaleza");
        d12.setState("CE");
        d12.setZipCode("60040-000");
        d12.setStatus(Donation.DonationStatus.CANCELLED);
        d12.setPickupInstructions("Doação cancelada — itens já distribuídos diretamente.");
        d12.setExpiresAt(LocalDateTime.now().minusDays(1));
        d12.setCreatedAt(LocalDateTime.now().minusMonths(1).minusDays(5));
        d12.setDonor(roberto);
        donationRepository.save(d12);

        // ── Fábio (RS) ─────────────────────────────────────────────────────────
        Donation d13 = new Donation();
        d13.setTitle("Pães Artesanais do Dia");
        d13.setDescription("Pães franceses, de forma integral, de centeio e bisnaguinhas — todos assados hoje de manhã e embalados higienicamente.");
        d13.setCategory(FoodCategory.PADARIA_CONFEITARIA);
        d13.setQuantity(20);
        d13.setPerishable(true);
        d13.setExpirationDate(LocalDate.now().plusDays(2));
        d13.setStorageInstructions("Consumir em até 2 dias. Pode ser congelado para maior durabilidade.");
        d13.setLocation("Cidade Baixa — Porto Alegre");
        d13.setCity("Porto Alegre");
        d13.setState("RS");
        d13.setZipCode("90050-170");
        d13.setStatus(Donation.DonationStatus.AVAILABLE);
        d13.setPickupInstructions("Retirar na padaria após as 18h, de segunda a sábado.");
        d13.setExpiresAt(LocalDateTime.now().plusHours(30));
        d13.setCreatedAt(LocalDateTime.now().minusHours(2));
        d13.setDonor(fabio);
        donationRepository.save(d13);

        Donation d14 = new Donation();
        d14.setTitle("Bolos e Tortas Doces Excedentes");
        d14.setDescription("Fatias de bolo de cenoura com cobertura de chocolate, torta de limão e bolo de fubá. Preparados hoje para evento que foi cancelado.");
        d14.setCategory(FoodCategory.PADARIA_CONFEITARIA);
        d14.setQuantity(15);
        d14.setPerishable(true);
        d14.setExpirationDate(LocalDate.now().plusDays(1));
        d14.setStorageInstructions("Refrigerar. Consumir em até 24 h.");
        d14.setLocation("Moinhos de Vento — Porto Alegre");
        d14.setCity("Porto Alegre");
        d14.setState("RS");
        d14.setZipCode("90570-020");
        d14.setStatus(Donation.DonationStatus.AVAILABLE);
        d14.setPickupInstructions("Retirar hoje até as 21h. Trazer embalagem própria.");
        d14.setExpiresAt(LocalDateTime.now().plusHours(10));
        d14.setCreatedAt(LocalDateTime.now().minusHours(1));
        d14.setDonor(fabio);
        donationRepository.save(d14);

        // ── Camila (BA) ────────────────────────────────────────────────────────
        Donation d15 = new Donation();
        d15.setTitle("Frutas Tropicais Orgânicas");
        d15.setDescription("Caixas com mangas Palmer, mamões papaia, abacaxis pérola e bananas-da-terra. Colhidas ontem, sem agrotóxicos.");
        d15.setCategory(FoodCategory.HORTIFRUTI);
        d15.setQuantity(6);
        d15.setPerishable(true);
        d15.setExpirationDate(LocalDate.now().plusDays(7));
        d15.setStorageInstructions("Armazenar em local arejado. Não refrigerar mangas e bananas verdes.");
        d15.setLocation("Feira de São Joaquim — Salvador");
        d15.setCity("Salvador");
        d15.setState("BA");
        d15.setZipCode("40460-130");
        d15.setStatus(Donation.DonationStatus.AVAILABLE);
        d15.setPickupInstructions("Retirar na feira, banca no corredor B. Perguntar pela Camila.");
        d15.setExpiresAt(LocalDateTime.now().plusDays(5));
        d15.setCreatedAt(LocalDateTime.now().minusDays(1));
        d15.setDonor(camila);
        donationRepository.save(d15);

        Donation d16 = new Donation();
        d16.setTitle("Legumes Orgânicos — Abóbora e Mandioca");
        d16.setDescription("Abóboras japonesas inteiras (aprox. 1,5 kg cada) e raízes de mandioca, cultivados sem defensivos químicos.");
        d16.setCategory(FoodCategory.HORTIFRUTI);
        d16.setQuantity(10);
        d16.setPerishable(false);
        d16.setExpirationDate(LocalDate.now().plusDays(20));
        d16.setStorageInstructions("Manter em local seco e ventilado, fora da geladeira.");
        d16.setLocation("Lapinha — Salvador");
        d16.setCity("Salvador");
        d16.setState("BA");
        d16.setZipCode("40310-000");
        d16.setStatus(Donation.DonationStatus.COMPLETED);
        d16.setPickupInstructions("Já retirado. Doação concluída com sucesso.");
        d16.setExpiresAt(LocalDateTime.now().minusDays(2));
        d16.setCreatedAt(LocalDateTime.now().minusDays(15));
        d16.setDonor(camila);
        donationRepository.save(d16);

        // ── Marcelo (PR) ───────────────────────────────────────────────────────
        Donation d17 = new Donation();
        d17.setTitle("Refeições de Evento — Lasanha e Risoto");
        d17.setDescription("Porções de lasanha à bolonhesa e risoto de cogumelos excedentes de um casamento. Conservadas em recipientes fechados e refrigeradas.");
        d17.setCategory(FoodCategory.REFEICAO_PRONTA);
        d17.setQuantity(18);
        d17.setPerishable(true);
        d17.setExpirationDate(LocalDate.now().plusDays(1));
        d17.setStorageInstructions("Manter refrigerado. Aquecer a pelo menos 74 °C antes de servir.");
        d17.setLocation("Batel — Curitiba");
        d17.setCity("Curitiba");
        d17.setState("PR");
        d17.setZipCode("80250-070");
        d17.setStatus(Donation.DonationStatus.AVAILABLE);
        d17.setPickupInstructions("Retirar hoje entre 19h e 22h. Trazer caixas térmicas se possível.");
        d17.setExpiresAt(LocalDateTime.now().plusHours(8));
        d17.setCreatedAt(LocalDateTime.now().minusHours(4));
        d17.setDonor(marcelo);
        donationRepository.save(d17);

        Donation d18 = new Donation();
        d18.setTitle("Proteínas — Ovos Caipiras e Atum em Lata");
        d18.setDescription("Bandejas de ovos caipiras (12 unidades) e latas de atum sólido em água (170 g). Ovos coletados hoje, atum com validade longa.");
        d18.setCategory(FoodCategory.PROTEINAS);
        d18.setQuantity(7);
        d18.setPerishable(true);
        d18.setExpirationDate(LocalDate.now().plusDays(14));
        d18.setStorageInstructions("Ovos: refrigerar. Atum: manter em local seco até a abertura.");
        d18.setLocation("Água Verde — Curitiba");
        d18.setCity("Curitiba");
        d18.setState("PR");
        d18.setZipCode("80620-100");
        d18.setStatus(Donation.DonationStatus.AVAILABLE);
        d18.setPickupInstructions("Disponível de terça a sábado, das 10h às 18h.");
        d18.setExpiresAt(LocalDateTime.now().plusDays(12));
        d18.setCreatedAt(LocalDateTime.now().minusDays(2));
        d18.setDonor(marcelo);
        donationRepository.save(d18);

        Donation d19 = new Donation();
        d19.setTitle("Azeite, Vinagre e Molhos Especiais");
        d19.setDescription("Garrafas de azeite extra-virgem (500 mL), vinagre balsâmico, molho inglês e shoyu. Excedente de estoque do bufê.");
        d19.setCategory(FoodCategory.TEMPEROS_CONDIMENTOS);
        d19.setQuantity(9);
        d19.setPerishable(false);
        d19.setExpirationDate(LocalDate.now().plusMonths(10));
        d19.setStorageInstructions("Manter em local fresco, longe de luz direta.");
        d19.setLocation("Centro Cívico — Curitiba");
        d19.setCity("Curitiba");
        d19.setState("PR");
        d19.setZipCode("80530-000");
        d19.setStatus(Donation.DonationStatus.AVAILABLE);
        d19.setPickupInstructions("Combinar retirada com 1 dia de antecedência pelo telefone.");
        d19.setExpiresAt(LocalDateTime.now().plusDays(55));
        d19.setCreatedAt(LocalDateTime.now().minusDays(3));
        d19.setDonor(marcelo);
        donationRepository.save(d19);

        Donation d20 = new Donation();
        d20.setTitle("Bebidas para Eventos — Refrigerantes e Sucos");
        d20.setDescription("Garrafas de refrigerante 2 L (cola, guaraná) e caixinhas de suco de uva e de laranja. Lacrados, excedentes de evento.");
        d20.setCategory(FoodCategory.BEBIDAS);
        d20.setQuantity(25);
        d20.setPerishable(false);
        d20.setExpirationDate(LocalDate.now().plusMonths(4));
        d20.setStorageInstructions("Armazenar em local fresco. Refrigerar antes de servir.");
        d20.setLocation("Ecoville — Curitiba");
        d20.setCity("Curitiba");
        d20.setState("PR");
        d20.setZipCode("81200-240");
        d20.setStatus(Donation.DonationStatus.RESERVED);
        d20.setPickupInstructions("Solicitação aprovada. Retirar amanhã pela manhã.");
        d20.setExpiresAt(LocalDateTime.now().plusDays(35));
        d20.setCreatedAt(LocalDateTime.now().minusDays(1));
        d20.setDonor(marcelo);
        donationRepository.save(d20);

        System.out.println("Doações criadas: " + donationRepository.count());
    }

    private void createMatches() {
        System.out.println("Criando matches...");

        List<Donation> donations = donationRepository.findAll();
        List<User> requesters = userRepository.findAll().stream()
            .filter(user -> user.getRole() == UserRole.REQUESTER)
            .toList();

        if (donations.isEmpty() || requesters.isEmpty()) {
            System.out.println("Não há doações ou solicitantes suficientes para criar matches.");
            return;
        }

        // Índices de doações para facilitar referência
        // d1=0  d2=1  d3=2  d4=3  d5=4  d6=5  d7=6  d8=7  d9=8
        // d10=9 d11=10 d12=11 d13=12 d14=13 d15=14 d16=15 d17=16 d18=17 d19=18 d20=19

        // req0=lucia  req1=pedro  req2=sandra  req3=jose  req4=francisca
        // req5=beatriz req6=daniel req7=renata

        // ── PENDING ────────────────────────────────────────────────────────────
        Match m1 = new Match();
        m1.setMessage("Olá! Sou mãe solteira de dois filhos e precisamos muito de alimentos. Posso buscar no horário que for melhor para você. Muito obrigada!");
        m1.setStatus(Match.MatchStatus.PENDING);
        m1.setRequestedAt(LocalDateTime.now().minusHours(3));
        m1.setCreatedAt(LocalDateTime.now().minusHours(3));
        m1.setDonation(donations.get(0));    // Cesta Básica Completa
        m1.setRequester(requesters.get(0));   // lucia
        matchRepository.save(m1);

        Match m2 = new Match();
        m2.setMessage("Boa tarde! Sou estudante universitário com dificuldades financeiras. Os alimentos seriam de enorme ajuda para fechar o mês. Posso retirar no fim de semana.");
        m2.setStatus(Match.MatchStatus.PENDING);
        m2.setRequestedAt(LocalDateTime.now().minusHours(1));
        m2.setCreatedAt(LocalDateTime.now().minusHours(1));
        m2.setDonation(donations.get(11));   // Feijão Carioca e Grão-de-Bico
        m2.setRequester(requesters.get(1));   // pedro
        matchRepository.save(m2);

        Match m3 = new Match();
        m3.setMessage("Oi, tenho 4 netos para cuidar e estou precisando de sucos e bebidas para as crianças. Posso retirar amanhã cedo.");
        m3.setStatus(Match.MatchStatus.PENDING);
        m3.setRequestedAt(LocalDateTime.now().minusMinutes(45));
        m3.setCreatedAt(LocalDateTime.now().minusMinutes(45));
        m3.setDonation(donations.get(10));   // Bebidas — Sucos Naturais
        m3.setRequester(requesters.get(4));   // francisca
        matchRepository.save(m3);

        // ── APPROVED ───────────────────────────────────────────────────────────
        Match m4 = new Match();
        m4.setMessage("Olá! Tenho três filhos pequenos que precisam muito de laticínios. Seria uma bênção receber essa doação. Posso buscar quando precisar.");
        m4.setStatus(Match.MatchStatus.APPROVED);
        m4.setRequestedAt(LocalDateTime.now().minusDays(2));
        m4.setRespondedAt(LocalDateTime.now().minusDays(1));
        m4.setDonorNotes("Aprovado. Retirar na quinta-feira pela manhã, das 8h às 10h.");
        m4.setCreatedAt(LocalDateTime.now().minusDays(2));
        m4.setDonation(donations.get(3));    // Leite Integral e Derivados
        m4.setRequester(requesters.get(2));   // sandra
        matchRepository.save(m4);

        Match m5 = new Match();
        m5.setMessage("Bom dia! Desempregado há 6 meses, com dois filhos em casa. Os pães fariam muita diferença para o café da manhã das crianças. Busco hoje mesmo.");
        m5.setStatus(Match.MatchStatus.APPROVED);
        m5.setRequestedAt(LocalDateTime.now().minusHours(5));
        m5.setRespondedAt(LocalDateTime.now().minusHours(2));
        m5.setDonorNotes("Aprovado para retirada hoje após as 18h na padaria.");
        m5.setCreatedAt(LocalDateTime.now().minusHours(5));
        m5.setDonation(donations.get(12));   // Pães Artesanais do Dia
        m5.setRequester(requesters.get(3));   // jose
        matchRepository.save(m5);

        Match m6 = new Match();
        m6.setMessage("Olá! Família grande, marido doente. As refeições prontas seriam uma ajuda enorme hoje. Posso retirar à tarde.");
        m6.setStatus(Match.MatchStatus.APPROVED);
        m6.setRequestedAt(LocalDateTime.now().minusHours(4));
        m6.setRespondedAt(LocalDateTime.now().minusHours(1));
        m6.setDonorNotes("Aprovado. Retirar entre 17h e 19h hoje.");
        m6.setCreatedAt(LocalDateTime.now().minusHours(4));
        m6.setDonation(donations.get(16));   // Refeições de Evento
        m6.setRequester(requesters.get(7));   // renata
        matchRepository.save(m6);

        // ── IN_PROGRESS ────────────────────────────────────────────────────────
        Match m7 = new Match();
        m7.setMessage("Oi! Sou trabalhador informal e os temperos naturais ajudariam muito a tornar as refeições melhores. Posso retirar no fim de semana.");
        m7.setStatus(Match.MatchStatus.IN_PROGRESS);
        m7.setRequestedAt(LocalDateTime.now().minusDays(3));
        m7.setRespondedAt(LocalDateTime.now().minusDays(2));
        m7.setPickupDate(LocalDateTime.now().plusDays(1));
        m7.setPickupNotes("Retirada agendada para sábado às 10h.");
        m7.setCreatedAt(LocalDateTime.now().minusDays(3));
        m7.setDonation(donations.get(8));    // Temperos e Condimentos Naturais
        m7.setRequester(requesters.get(6));   // daniel
        matchRepository.save(m7);

        Match m8 = new Match();
        m8.setMessage("Olá! Preciso muito de proteínas para minha família. Tenho crianças e o frango seria essencial. Posso retirar na quarta-feira cedo.");
        m8.setStatus(Match.MatchStatus.IN_PROGRESS);
        m8.setRequestedAt(LocalDateTime.now().minusDays(4));
        m8.setRespondedAt(LocalDateTime.now().minusDays(3));
        m8.setPickupDate(LocalDateTime.now().plusDays(2));
        m8.setPickupNotes("Retirada confirmada para quarta às 8h na Savassi.");
        m8.setCreatedAt(LocalDateTime.now().minusDays(4));
        m8.setDonation(donations.get(7));    // Proteínas — Frango e Carne Moída
        m8.setRequester(requesters.get(5));   // beatriz
        matchRepository.save(m8);

        // ── COMPLETED ──────────────────────────────────────────────────────────
        Match m9 = new Match();
        m9.setMessage("Olá! Mãe de quatro filhos. As frutas tropicais seriam fantásticas para as crianças. Posso retirar qualquer dia da semana.");
        m9.setStatus(Match.MatchStatus.COMPLETED);
        m9.setRequestedAt(LocalDateTime.now().minusDays(16));
        m9.setRespondedAt(LocalDateTime.now().minusDays(15));
        m9.setPickupDate(LocalDateTime.now().minusDays(14));
        m9.setCompletedAt(LocalDateTime.now().minusDays(14));
        m9.setPickupNotes("Retirada realizada na feira, banca B.");
        m9.setDonorNotes("Doação entregue com sucesso. Família muito agradecida.");
        m9.setRequesterRating(5);
        m9.setDonorRating(5);
        m9.setCreatedAt(LocalDateTime.now().minusDays(16));
        m9.setDonation(donations.get(15));   // Legumes Orgânicos (COMPLETED)
        m9.setRequester(requesters.get(5));   // beatriz
        matchRepository.save(m9);

        Match m10 = new Match();
        m10.setMessage("Boa tarde! Estudante precisando de enlatados para ter estoque em casa. Seria muito útil. Posso retirar no próximo fim de semana.");
        m10.setStatus(Match.MatchStatus.COMPLETED);
        m10.setRequestedAt(LocalDateTime.now().minusMonths(1).minusDays(3));
        m10.setRespondedAt(LocalDateTime.now().minusMonths(1).minusDays(2));
        m10.setPickupDate(LocalDateTime.now().minusMonths(1));
        m10.setCompletedAt(LocalDateTime.now().minusMonths(1));
        m10.setPickupNotes("Retirado no domingo às 11h.");
        m10.setDonorNotes("Entrega feita com sucesso. Pedro foi pontual.");
        m10.setRequesterRating(5);
        m10.setDonorRating(4);
        m10.setCreatedAt(LocalDateTime.now().minusMonths(1).minusDays(3));
        m10.setDonation(donations.get(4));   // Enlatados e Conservas (COMPLETED)
        m10.setRequester(requesters.get(1));  // pedro
        matchRepository.save(m10);

        Match m11 = new Match();
        m11.setMessage("Olá! Avó de 4 netos. As frutas e verduras são fundamentais para a alimentação das crianças. Muito grata por essa iniciativa!");
        m11.setStatus(Match.MatchStatus.COMPLETED);
        m11.setRequestedAt(LocalDateTime.now().minusDays(20));
        m11.setRespondedAt(LocalDateTime.now().minusDays(19));
        m11.setPickupDate(LocalDateTime.now().minusDays(18));
        m11.setCompletedAt(LocalDateTime.now().minusDays(18));
        m11.setPickupNotes("Dona Francisca retirou pessoalmente, satisfeita com a qualidade.");
        m11.setDonorNotes("Família muito carente e grata. Entrega realizada sem problemas.");
        m11.setRequesterRating(5);
        m11.setDonorRating(5);
        m11.setCreatedAt(LocalDateTime.now().minusDays(20));
        m11.setDonation(donations.get(6));   // Verduras Frescas
        m11.setRequester(requesters.get(4));  // francisca
        matchRepository.save(m11);

        // ── REJECTED ───────────────────────────────────────────────────────────
        Match m12 = new Match();
        m12.setMessage("Olá! Preciso de arroz e lentilhas para minha família. Posso retirar durante a semana.");
        m12.setStatus(Match.MatchStatus.REJECTED);
        m12.setRequestedAt(LocalDateTime.now().minusDays(6));
        m12.setRespondedAt(LocalDateTime.now().minusDays(5));
        m12.setDonorNotes("Desculpe, a doação já foi reservada para outra família em maior necessidade.");
        m12.setCreatedAt(LocalDateTime.now().minusDays(6));
        m12.setDonation(donations.get(1));   // Arroz Branco e Lentilhas (RESERVED)
        m12.setRequester(requesters.get(2));  // sandra
        matchRepository.save(m12);

        // ── CANCELLED ──────────────────────────────────────────────────────────
        Match m13 = new Match();
        m13.setMessage("Olá! Minha família precisa de bebidas para as crianças. Posso retirar amanhã cedo.");
        m13.setStatus(Match.MatchStatus.CANCELLED);
        m13.setRequestedAt(LocalDateTime.now().minusDays(8));
        m13.setRespondedAt(LocalDateTime.now().minusDays(7));
        m13.setDonorNotes("A solicitação ficou sem retorno por 48 h e foi cancelada automaticamente.");
        m13.setCreatedAt(LocalDateTime.now().minusDays(8));
        m13.setDonation(donations.get(19));  // Bebidas para Eventos (RESERVED)
        m13.setRequester(requesters.get(0)); // lucia
        matchRepository.save(m13);

        System.out.println("Matches criados: " + matchRepository.count());
    }
}
