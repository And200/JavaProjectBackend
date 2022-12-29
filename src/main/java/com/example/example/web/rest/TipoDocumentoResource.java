package com.example.example.web.rest;

import com.example.example.domain.TipoDocumento;
import com.example.example.repository.TipoDocumentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TipoDocumentoResource {

    private TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoResource(TipoDocumentoRepository tipoDocumentoRepository){
        this.tipoDocumentoRepository=tipoDocumentoRepository;
    }
    @GetMapping("/tipo-documentos")
    public ResponseEntity<List<TipoDocumento>>getAllTipoDocumentos(){
        List<TipoDocumento>query=tipoDocumentoRepository.findAll();
        return ResponseEntity.ok().body(query);
    }
    @PostMapping("/tipo-documentos")
    public ResponseEntity<TipoDocumento>createTipoDocumento(@Valid @RequestBody TipoDocumento tipoDocumento)throws URISyntaxException{
        if (tipoDocumento.getId()!=null){
            return ResponseEntity.badRequest().build();
        }else {
            TipoDocumento result=tipoDocumentoRepository.save(tipoDocumento);
            return  ResponseEntity.created(new URI("/api/tipo-documentos/"+result.getId())).body(result);
        }


    }

    @GetMapping("/tipo-documentos/{id}")
    public ResponseEntity<TipoDocumento>getIdTipoDocumento(@PathVariable Integer id){
        Optional<TipoDocumento>query=tipoDocumentoRepository.findById(id);
        if (query.isPresent()){
            return ResponseEntity.ok().body(query.isPresent()?query.get():null);
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/tipo-documentos")
    public ResponseEntity<TipoDocumento>updateTipoDocumento(@Valid @RequestBody TipoDocumento tipoDocumento){
        if (tipoDocumento.getId()==null){
            return ResponseEntity.badRequest().build();
        }
        Optional<TipoDocumento>consulta=tipoDocumentoRepository.findById(tipoDocumento.getId());
        TipoDocumento result=null;
        if (consulta.isPresent()){
            result=tipoDocumentoRepository.save(tipoDocumento);
        }else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/tipo-documentos/{id}")
    public ResponseEntity<Void> deleteTipoDocumento(@PathVariable Integer id){
        tipoDocumentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
