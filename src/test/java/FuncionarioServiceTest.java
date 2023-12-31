import ifsuldeminas.bcc.PrimeiroProjeto.Model.Entity.*;
import ifsuldeminas.bcc.PrimeiroProjeto.Model.Repository.FuncionarioRepository;
import ifsuldeminas.bcc.PrimeiroProjeto.Model.Repository.ClienteRepository;
import ifsuldeminas.bcc.PrimeiroProjeto.Model.Repository.CarroRepository;
import ifsuldeminas.bcc.PrimeiroProjeto.Model.Services.FuncionarioService;
import ifsuldeminas.bcc.PrimeiroProjeto.Model.Services.ClienteService;
import ifsuldeminas.bcc.PrimeiroProjeto.Model.Services.CarroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrudServicesTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CarroRepository carroRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @InjectMocks
    private ClienteService clienteService;

    @InjectMocks
    private CarroService carroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Métodos CRUD para Funcionario

    @Test
    void getAllFuncionarios() {
        List<Funcionario> mockFuncionarios = new ArrayList<>();
        when(funcionarioRepository.findAll()).thenReturn(mockFuncionarios);

        List<Funcionario> result = funcionarioService.getAllFuncionarios();

        assertEquals(mockFuncionarios, result);
    }

    @Test
    void getFuncionarioById() {
        Long id = 1L;
        Funcionario mockFuncionario = criarMockFuncionario(id);
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(mockFuncionario));

        Funcionario result = funcionarioService.getFuncionarioById(id);

        assertEquals(mockFuncionario, result);
    }

    @Test
    void getFuncionarioByIdNotFound() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> funcionarioService.getFuncionarioById(id));
    }

    @Test
    void createFuncionario() {
        Long id = 1L;
        Funcionario mockFuncionario = criarMockFuncionario(id);
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(mockFuncionario);

        Funcionario result = funcionarioService.createFuncionario(mockFuncionario);

        assertEquals(mockFuncionario, result);
    }

    @Test
    void updateFuncionario() {
        Long id = 1L;
        Funcionario existingFuncionario = criarMockFuncionario(id);
        Funcionario newFuncionario = criarMockFuncionarioAtualizado(id);

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(existingFuncionario));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(newFuncionario);

        Funcionario result = funcionarioService.updateFuncionario(id, newFuncionario);

        assertEquals(newFuncionario.getNome(), result.getNome());
        // Verificar outros atributos, se necessário
    }

    @Test
    void deleteFuncionario() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new Funcionario(id, "Nome", new ArrayList<OrdemServico>(), new DesempenhoFuncionario(), "456-456-456-57", "35997379360", "05/08/2001", "Rua teste")));

        funcionarioService.deleteFuncionario(id);

        verify(funcionarioRepository).deleteById(id);
    }

    // Métodos CRUD para Cliente

    @Test
    void getAllClientes() {
        List<Cliente> mockClientes = new ArrayList<>();
        when(clienteRepository.findAll()).thenReturn(mockClientes);

        List<Cliente> result = clienteService.getAllClientes();

        assertEquals(mockClientes, result);
    }

    @Test
    void getClienteById() {
        Long id = 1L;
        Cliente mockCliente = criarMockCliente(id);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(mockCliente));

        Cliente result = clienteService.getClienteById(id);

        assertEquals(mockCliente, result);
    }

    @Test
    void getClienteByIdNotFound() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> clienteService.getClienteById(id));
    }

    @Test
    void createCliente() {
        Long id = 1L;
        Cliente mockCliente = criarMockCliente(id);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(mockCliente);

        Cliente result = clienteService.createCliente(mockCliente);

        assertEquals(mockCliente, result);
    }

    @Test
    void updateCliente() {
        Long id = 1L;
        Cliente existingCliente = criarMockCliente(id);
        Cliente newCliente = criarMockClienteAtualizado(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(newCliente);

        Cliente result = clienteService.updateCliente(id, newCliente);

        assertEquals(newCliente.getNome(), result.getNome());
        // Verificar outros atributos, se necessário
    }

    @Test
    void deleteCliente() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.of(new Cliente()));

        clienteService.deleteCliente(id);

        verify(clienteRepository).deleteById(id);
    }

    // Métodos CRUD para Carro

    @Test
    void getAllCarros() {
        List<Carro> mockCarros = new ArrayList<>();
        when(carroRepository.findAll()).thenReturn(mockCarros);

        List<Carro> result = carroService.getAllCarros();

        assertEquals(mockCarros, result);
    }

    @Test
    void getCarroById() {
        Long id = 1L;
        Carro mockCarro = criarMockCarro(id);
        when(carroRepository.findById(id)).thenReturn(Optional.of(mockCarro));

        Carro result = carroService.getCarroById(id);

        assertEquals(mockCarro, result);
    }

    @Test
    void getCarroByIdNotFound() {
        Long id = 1L;
        when(carroRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> carroService.getCarroById(id));
    }

    @Test
    void createCarro() {
        Long id = 1L;
        Carro mockCarro = criarMockCarro(id);
        when(carroRepository.save(any(Carro.class))).thenReturn(mockCarro);

        Carro result = carroService.createCarro(mockCarro);

        assertEquals(mockCarro, result);
    }

    @Test
    void updateCarro() {
        Long id = 1L;
        Carro existingCarro = criarMockCarro(id);
        Carro newCarro = criarMockCarroAtualizado(id);

        when(carroRepository.findById(id)).thenReturn(Optional.of(existingCarro));
        when(carroRepository.save(any(Carro.class))).thenReturn(newCarro);

        Carro result = carroService.updateCarro(id, newCarro);

        assertEquals(newCarro.getMarca(), result.getMarca());
        // Verificar outros atributos, se necessário
    }

    @Test
    void deleteCarro() {
        Long id = 1L;
        when(carroRepository.findById(id)).thenReturn(Optional.of(new Carro()));

        carroService.deleteCarro(id);

        verify(carroRepository).deleteById(id);
    }

    // Funções auxiliares para criar mocks
    private Funcionario criarMockFuncionario(Long id) {
        return new Funcionario(id, "Nome", new ArrayList<OrdemServico>(), new DesempenhoFuncionario(), "456-456-456-57", "35997379360", "05/08/2001", "Rua teste");
    }

    private Funcionario criarMockFuncionarioAtualizado(Long id) {
        return new Funcionario(id, "Nome Atualizado", new ArrayList<OrdemServico>(), new DesempenhoFuncionario(), "456-456-456-57", "35997379360", "05/08/2001", "Rua teste atualizada");
    }

    private Cliente criarMockCliente(Long id) {
        return new Cliente(id, "Nome", "123-456-789-01", "35991234567", "01/01/1990", "Rua Cliente");
    }

    private Cliente criarMockClienteAtualizado(Long id) {
        return new Cliente(id, "Nome Atualizado", "123-456-789-01", "35991234567", "01/01/1990", "Rua Cliente Atualizada");
    }

    private Carro criarMockCarro(Long id) {
        return new Carro(id, "Marca", "Modelo", "ABC-1234", "Vermelho", "2020", 10000.0, new Cliente());
    }

    private Carro criarMockCarroAtualizado(Long id) {
        return new Carro(id, "Marca Atualizada", "Modelo Atualizado", "XYZ-9876", "Azul", "2022", 15000.0, new Cliente());
    }
}
