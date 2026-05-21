package com.barbearia.backend.service;

import com.barbearia.backend.dto.request.ServicoRequest;
import com.barbearia.backend.dto.response.ServicoResponse;
import com.barbearia.backend.exception.ResourceNotFoundException;
import com.barbearia.backend.mapper.ServicoMapper;
import com.barbearia.backend.model.Servico;
import com.barbearia.backend.model.TipoServico;
import com.barbearia.backend.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    public ServicoResponse cadastrar(ServicoRequest request) {
        Servico servico = Servico.builder()
                .tipo(request.getTipo())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .duracaoMinutos(request.getDuracaoMinutos())
                .build();

        Servico salvo = servicoRepository.save(servicoMapper.toEntity(servico));
        return servicoMapper.toResponse(salvo);
    }

    public List<ServicoResponse> listarTodos() {
        return servicoRepository.findAll()
                .stream()
                .map(servicoMapper::toResponse)
                .toList();
    }

    public ServicoResponse buscarPorId(Long id) {
        Servico servico = buscarOuLancarExcecao(id);
        return servicoMapper.toResponse(servico);
    }

    private Servico buscarOuLancarExcecao(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Barbeiro não encontrado com id: " + id));
    }
    public List<ServicoResponse> listarPorTipo(TipoServico tipo) {
        return servicoRepository.findByTipo(tipo)
                .stream()
                .map(servicoMapper::toResponse)
                .toList();
    }

}
