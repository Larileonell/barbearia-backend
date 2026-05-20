package com.barbearia.backend.service;

import com.barbearia.backend.dto.request.ClienteRequest;
import com.barbearia.backend.dto.response.ClienteResponse;
import com.barbearia.backend.exception.RegraNegocioException;
import com.barbearia.backend.exception.ResourceNotFoundException;
import com.barbearia.backend.mapper.ClienteMapper;
import com.barbearia.backend.model.Cliente;
import com.barbearia.backend.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteResponse cadastrar(ClienteRequest request) {
        validarEmailDuplicado(request.getEmail());
        validarCpfDuplicado(request.getCpf());

        Cliente salvo = clienteRepository.save(
                clienteMapper.toEntity(request));
        return clienteMapper.toResponse(salvo);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    public ClienteResponse buscarPorId(Long id) {

        return clienteMapper.toResponse(buscarOuLancarExcecao(id));
    }

    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = buscarOuLancarExcecao(id);

        if (!cliente.getEmail().equals(request.getEmail())) {
            validarEmailDuplicado(request.getEmail());
        }

        if (!cliente.getCpf().equals(request.getCpf())) {
            validarCpfDuplicado(request.getCpf());
        }

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setCpf(request.getCpf());
        cliente.setEndereco(request.getEndereco());

        return clienteMapper.toResponse(
                clienteRepository.save(cliente));
    }

    private Cliente buscarOuLancarExcecao(Long id) {
        return clienteRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente não encontrado com id: " + id));
    }

    private void validarEmailDuplicado(String email) {
        if (clienteRepository.existsByEmail(email)) {
            throw new RegraNegocioException(
                    "Já existe um cliente cadastrado com o email: " + email);
        }
    }

    private void validarCpfDuplicado(String cpf) {
        if (clienteRepository.existsByCpf(cpf)) {
            throw new RegraNegocioException(
                    "Já existe um cliente cadastrado com o CPF: " + cpf);
        }
    }

}
