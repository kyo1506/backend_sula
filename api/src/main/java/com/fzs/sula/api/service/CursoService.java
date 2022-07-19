package com.fzs.sula.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.sula.api.model.Curso;
import com.fzs.sula.api.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoService {
    private CursoRepository cursoRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getCursos() throws JsonProcessingException {
        List<Curso> cursosList = cursoRepository.findAll();
        if (!cursosList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(cursosList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há cursos cadastrados!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getCurso(Long id) throws JsonProcessingException {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(curso), HttpStatus.OK);
        }else return new ResponseEntity<>("Curso não encontrado!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createCurso(Curso model){
        if (!cursoRepository.existsCursoByNome(model.getNome())) {
            Curso curso = new Curso();
            curso.setNome(model.getNome());
            curso.setMaterias(model.getMaterias());
            Curso cursoSalvo = cursoRepository.save(curso);
            if (cursoRepository.findById(cursoSalvo.getId()).isPresent())
                return new ResponseEntity<>("Curso cadastrado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe um curso com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateCurso(Curso model, Long id){
        if (cursoRepository.findById(id).isPresent()){
            Curso curso = cursoRepository.findById(id).get();
            curso.setNome(model.getNome());
            curso.setAtivo(model.getAtivo());
            curso.setMaterias(model.getMaterias());
            curso.setUpdatedOn(Timestamp.from(Instant.now()));
            Curso cursoSalvo = cursoRepository.save(curso);
            if (cursoRepository.findById(cursoSalvo.getId()).isPresent())
                return new ResponseEntity<>("Curso atualizado com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Curso não encontrado!", HttpStatus.NOT_FOUND);
    }
}
