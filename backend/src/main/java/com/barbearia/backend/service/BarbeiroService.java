package com.barbearia.backend.service;

import com.barbearia.backend.dto.request.BarbeiroRequest;
import com.barbearia.backend.dto.response.BarbeiroResponse;
import com.barbearia.backend.exception.RegraNegocioException;
import com.barbearia.backend.exception.ResourceNotFoundException;
import com.barbearia.backend.mapper.BarbeiroMapper;
import com.barbearia.backend.model.Barbeiro;
import com.barbearia.backend.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final BarbeiroMapper barbeiroMapper;

    private void validarEmailDuplicado(String email) {
        if (barbeiroRepository.existsByEmail(email)) {
            throw new RegraNegocioException(
                    "Já existe um barbeiro cadastrado com o email: " + email);
        }
    }
    private Barbeiro buscarOuLancarExcecao(Long id) {
        return barbeiroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Barbeiro não encontrado com id: " + id));
    }
    public BarbeiroResponse cadastrar(BarbeiroRequest request) {
        validarEmailDuplicado(request.getEmail());
        Barbeiro salvo = barbeiroRepository.save(
                barbeiroMapper.toEntity(request));
        return barbeiroMapper.toResponse(salvo);
    }
    public List<BarbeiroResponse> listarAtivos() {
        return barbeiroRepository.findByAtivoTrue()
                .stream()
                .map(barbeiroMapper::toResponse)
                .toList();
    }

    public BarbeiroResponse buscarPorId(Long id) {
        return barbeiroMapper.toResponse(buscarOuLancarExcecao(id));
    }

    public BarbeiroResponse atualizar(Long id, BarbeiroRequest request) {
        Barbeiro barbeiro = buscarOuLancarExcecao(id);

        if (!barbeiro.getEmail().equals(request.getEmail())) {
            validarEmailDuplicado(request.getEmail());
        }

        barbeiro.setNome(request.getNome());
        barbeiro.setEmail(request.getEmail());
        barbeiro.setTelefone(request.getTelefone());
        barbeiro.setEspecialidade(request.getEspecialidade());

        return barbeiroMapper.toResponse(
                barbeiroRepository.save(barbeiro));
    }

    public void inativar(Long id) {
        Barbeiro barbeiro = buscarOuLancarExcecao(id);
        barbeiro.setAtivo(false);
        barbeiroRepository.save(barbeiro);
    }


}
