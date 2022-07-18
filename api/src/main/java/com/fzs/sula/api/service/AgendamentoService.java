package com.fzs.sula.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.sula.api.enums.Semestre;
import com.fzs.sula.api.model.Agendamento;
import com.fzs.sula.api.repository.AgendamentoRepository;
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
public class AgendamentoService {
    private AgendamentoRepository agendamentoRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getagendamentosCursoSemestre(Long id, Semestre semestre) throws JsonProcessingException {
        List<Agendamento> agendamentosList = agendamentoRepository.findAllByCurso_IdAndMateria_Semestre(id, semestre);
        if (!agendamentosList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(agendamentosList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há agendamentos cadastrados!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getAgendamentosUserId(Long id) throws JsonProcessingException {
        List<Agendamento> agendamentosList = agendamentoRepository.findAllByUser_Id(id);
        if (!agendamentosList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(agendamentosList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há agendamentos cadastrados!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getAgendamento(Long id) throws JsonProcessingException {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        if (agendamento.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(agendamento), HttpStatus.OK);
        }else return new ResponseEntity<>("Agendamento não encontrado!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createAgendamentoUnico(Agendamento model){
        if (agendamentoRepository.existsAgendamentoByAmbiente_IdAndDataAndHorarioInicioAndHorarioFim(
                model.getAmbiente().getId(),
                model.getData(),
                model.getHorarioInicio(),
                model.getHorarioFim())) {
            Agendamento agendamento = new Agendamento();
            agendamento.setAmbiente(model.getAmbiente());
            agendamento.setCurso(model.getCurso());
            agendamento.setMateria(model.getMateria());
            agendamento.setUser(model.getUser());
            agendamento.setData(model.getData());
            agendamento.setHorarioInicio(model.getHorarioInicio());
            agendamento.setHorarioFim(model.getHorarioFim());
            Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
            if (agendamentoRepository.findById(agendamentoSalvo.getId()).isPresent())
                return new ResponseEntity<>("Agendamento cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um agendamento com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> createAgendamentoRecorrente(List<Agendamento> listModel){
        for(Agendamento rowAgendamento : listModel) {
            if (agendamentoRepository.existsAgendamentoByAmbiente_IdAndDataAndHorarioInicioAndHorarioFim(
                    rowAgendamento.getAmbiente().getId(),
                    rowAgendamento.getData(),
                    rowAgendamento.getHorarioInicio(),
                    rowAgendamento.getHorarioFim())) {
                Agendamento agendamento = new Agendamento();
                agendamento.setAmbiente(rowAgendamento.getAmbiente());
                agendamento.setCurso(rowAgendamento.getCurso());
                agendamento.setMateria(rowAgendamento.getMateria());
                agendamento.setUser(rowAgendamento.getUser());
                agendamento.setData(rowAgendamento.getData());
                agendamento.setHorarioInicio(rowAgendamento.getHorarioInicio());
                agendamento.setHorarioFim(rowAgendamento.getHorarioFim());
                Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
                if (agendamentoRepository.findById(agendamentoSalvo.getId()).isEmpty())
                    return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
            } else
                return new ResponseEntity<>("Já existe um agendamento neste ambiente para o horário proposto!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("Agendamentos cadastrados com sucesso!", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateAgendamento(Agendamento model, Long id){
        if (agendamentoRepository.findById(id).isPresent()){
            Agendamento agendamento = agendamentoRepository.findById(id).get();
            agendamento.setAmbiente(model.getAmbiente());
            agendamento.setCurso(model.getCurso());
            agendamento.setMateria(model.getMateria());
            agendamento.setUser(model.getUser());
            agendamento.setData(model.getData());
            agendamento.setHorarioInicio(model.getHorarioInicio());
            agendamento.setHorarioFim(model.getHorarioFim());
            agendamento.setUpdatedOn(LocalDateTime.now());
            Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
            if (agendamentoRepository.findById(agendamentoSalvo.getId()).isPresent())
                return new ResponseEntity<>("Agendamento atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Agendamento não encontrado!", HttpStatus.NOT_FOUND);
    }
}
