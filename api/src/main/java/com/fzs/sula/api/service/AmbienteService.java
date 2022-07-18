package com.fzs.sula.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.sula.api.model.Ambiente;
import com.fzs.sula.api.repository.AmbienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AmbienteService {
    private AmbienteRepository ambienteRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getAmbientes() throws JsonProcessingException {
        List<Ambiente> ambientesList = ambienteRepository.findAll();
        if (!ambientesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambientesList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há ambientes cadastrados!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getAmbiente(Long id) throws JsonProcessingException {
        Optional<Ambiente> ambiente = ambienteRepository.findById(id);
        if (ambiente.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambiente), HttpStatus.OK);
        }else return new ResponseEntity<>("Ambiente não encontrado!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createAmbinte(Ambiente model){
        if (!ambienteRepository.existsAmbienteByNome(model.getNome())) {
            Ambiente ambiente = new Ambiente();
            ambiente.setNome(model.getNome());
            ambiente.setTipoAmbiente(model.getTipoAmbiente());
            ambiente.setCaracteristicas(model.getCaracteristicas());
            ambiente.setDisponibilidades(model.getDisponibilidades());
            Ambiente ambienteSalvo = ambienteRepository.save(ambiente);
            if (ambienteRepository.findById(ambienteSalvo.getId()).isPresent())
                return new ResponseEntity<>("Ambiente cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um ambiente com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateAmbiente(Ambiente model, Long id){
        if (ambienteRepository.findById(id).isPresent()){
            Ambiente ambiente = ambienteRepository.findById(id).get();
            ambiente.setNome(model.getNome());
            ambiente.setAtivo(model.getAtivo());
            ambiente.setTipoAmbiente(model.getTipoAmbiente());
            ambiente.setCaracteristicas(ambiente.getCaracteristicas());
            ambiente.setDisponibilidades(ambiente.getDisponibilidades());
            ambiente.setUpdatedOn(LocalDateTime.now());
            Ambiente ambienteSalvo = ambienteRepository.save(ambiente);
            if (ambienteRepository.findById(ambienteSalvo.getId()).isPresent())
                return new ResponseEntity<>("Ambiente atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Ambiente não encontrado!", HttpStatus.NOT_FOUND);
    }
}
