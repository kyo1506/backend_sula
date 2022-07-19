package com.fzs.sula.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.sula.api.model.ManutencaoAmbiente;
import com.fzs.sula.api.repository.ManutencaoAmbienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ManutencaoAmbienteService {
    private ManutencaoAmbienteRepository manutencaoAmbienteRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getManutencoesAmbiente() throws JsonProcessingException {
        List<ManutencaoAmbiente> manutencoesAmbienteList = manutencaoAmbienteRepository.findAll();
        if (!manutencoesAmbienteList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(manutencoesAmbienteList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há manutenções agendadas no sistema!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getManutencoesAmbienteAmbienteId(Long id) throws JsonProcessingException {
        List<ManutencaoAmbiente> manutencoesAmbienteList = manutencaoAmbienteRepository.findAllByAmbiente_Id(id);
        if (!manutencoesAmbienteList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(manutencoesAmbienteList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há manutenções agendadas para esse ambiente!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getManutencaoAmbiente(Long id) throws JsonProcessingException {
        Optional<ManutencaoAmbiente> manutencaoAmbiente = manutencaoAmbienteRepository.findById(id);
        if (manutencaoAmbiente.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(manutencaoAmbiente), HttpStatus.OK);
        }else return new ResponseEntity<>("Manutenção não encontrada!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createManutencaoAmbiente(ManutencaoAmbiente model){
        Optional<ManutencaoAmbiente> existsManutencao = manutencaoAmbienteRepository.findManutencaoAmbienteByAmbiente_IdAndDtInicioAndDtFim(
                model.getAmbiente().getId(),
                model.getDtInicio(),
                model.getDtFim());

        if(existsManutencao.isEmpty() || !existsManutencao.get().getConcluido()){
            ManutencaoAmbiente manutencaoAmbiente = new ManutencaoAmbiente();
            manutencaoAmbiente.setAmbiente(model.getAmbiente());
            manutencaoAmbiente.setDtInicio(model.getDtInicio());
            manutencaoAmbiente.setDtFim(model.getDtFim());
            ManutencaoAmbiente manutencaoAmbienteSalva = manutencaoAmbienteRepository.save(manutencaoAmbiente);
            if (manutencaoAmbienteRepository.findById(manutencaoAmbienteSalva.getId()).isPresent())
                return new ResponseEntity<>("Manutenção agendada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe uma manutenção agendada para essa sala neste horário!", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateManutencaoAmbiente(ManutencaoAmbiente model, Long id){
        if (manutencaoAmbienteRepository.findById(id).isPresent()){
            ManutencaoAmbiente manutencaoAmbiente = manutencaoAmbienteRepository.findById(id).get();
            manutencaoAmbiente.setAmbiente(model.getAmbiente());
            manutencaoAmbiente.setDtInicio(model.getDtInicio());
            manutencaoAmbiente.setDtFim(model.getDtFim());
            manutencaoAmbiente.setConcluido(model.getConcluido());
            manutencaoAmbiente.setUpdatedOn(Timestamp.from(Instant.now()));
            ManutencaoAmbiente manutencaoAmbienteSalva = manutencaoAmbienteRepository.save(manutencaoAmbiente);
            if (manutencaoAmbienteRepository.findById(manutencaoAmbienteSalva.getId()).isPresent())
                return new ResponseEntity<>("Manutenção atualizada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Manutenção não encontrada!", HttpStatus.NOT_FOUND);
    }
}
