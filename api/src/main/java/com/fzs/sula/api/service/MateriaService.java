package com.fzs.sula.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.sula.api.model.Materia;
import com.fzs.sula.api.repository.MateriaRepository;
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
public class MateriaService {
    private MateriaRepository materiaRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> getMaterias() throws JsonProcessingException {
        List<Materia> materiasList = materiaRepository.findAll();
        if (!materiasList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(materiasList), HttpStatus.OK);
        } else return new ResponseEntity<>("Não há materias cadastradas!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> getMateria(Long id) throws JsonProcessingException {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isPresent()){
            return new ResponseEntity<>(objectMapper.writeValueAsString(materia), HttpStatus.OK);
        }else return new ResponseEntity<>("Materia não encontrada!", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> createMateria(Materia model){
        if (!materiaRepository.existsMateriaByNome(model.getNome())) {
            Materia materia = new Materia();
            materia.setNome(model.getNome());
            materia.setSemestre(model.getSemestre());
            Materia materiaSalva = materiaRepository.save(materia);
            if (materiaRepository.findById(materiaSalva.getId()).isPresent())
                return new ResponseEntity<>("Materia cadastrada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Já existe uma materia com esse nome!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    public ResponseEntity<Object> updateMateria(Materia model, Long id){
        if (materiaRepository.findById(id).isPresent()){
            Materia materia = materiaRepository.findById(id).get();
            materia.setNome(model.getNome());
            materia.setSemestre(model.getSemestre());
            materia.setAtivo(model.getAtivo());
            materia.setUpdatedOn(Timestamp.from(Instant.now()));
            Materia materiaSalva = materiaRepository.save(materia);
            if (materiaRepository.findById(materiaSalva.getId()).isPresent())
                return new ResponseEntity<>("Materia atualizada com sucesso!", HttpStatus.OK);
            else return new ResponseEntity<>("Ocorreu um erro durante o processamento!", HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>("Materia não encontrada!", HttpStatus.NOT_FOUND);
    }
}
