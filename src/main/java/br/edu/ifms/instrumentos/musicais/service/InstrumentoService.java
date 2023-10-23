package br.edu.ifms.instrumentos.musicais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.instrumentos.musicais.model.Instrumentos;
import br.edu.ifms.instrumentos.musicais.repository.InstrumentoRepository;

@Service
public class InstrumentoService {

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public Instrumentos criarInstrumento(Instrumentos instrumento) {
        return instrumentoRepository.save(instrumento);
    }

    public List<Instrumentos> buscarTodosInstrumentos() {
        return instrumentoRepository.findAll();
    }

    public Instrumentos buscarInstrumentoPorId(Long id) {
        Optional<Instrumentos> opt = instrumentoRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public void apagarInstrumento(Long id) {
        Instrumentos instrumento = buscarInstrumentoPorId(id);
        instrumentoRepository.delete(instrumento);
    }

}
