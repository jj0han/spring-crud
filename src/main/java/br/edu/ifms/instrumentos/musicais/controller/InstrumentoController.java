package br.edu.ifms.instrumentos.musicais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.edu.ifms.instrumentos.musicais.model.Instrumentos;
import br.edu.ifms.instrumentos.musicais.service.InstrumentoService;
import jakarta.validation.Valid;

@Controller
public class InstrumentoController {
    @Autowired
    private InstrumentoService instrumentoService;

    @GetMapping("/")
    public String cadastrarInstrumentos(Model model) {
        Instrumentos instrumento = new Instrumentos();
        model.addAttribute("novoInstrumento", instrumento);
        return "/instrumentos-register";
    }

    @GetMapping("/listar")
    public String listarInstrumentos(Model model) {
        List<Instrumentos> instrumentos = instrumentoService.buscarTodosInstrumentos();
        model.addAttribute("listaInstrumentos", instrumentos);
        return "/instrumentos-list";
    }

    @PostMapping("/gravar")
    public String salvarInstrumento(@ModelAttribute("novoInstrumento") @Valid Instrumentos instrumento,
            BindingResult erros,
            RedirectAttributes attr) {
        if (erros.hasErrors()) {
            return "/";
        } else {
            instrumentoService.criarInstrumento(instrumento);
            attr.addFlashAttribute("mensagem", "Instrumento salvo com sucesso!");
            return "redirect:/listar";
        }
    }

    @GetMapping("/apagar/{id}")
    public String apagarInstrumento(@PathVariable("id") long id, RedirectAttributes attr) {
        instrumentoService.apagarInstrumento(id);
        attr.addFlashAttribute("mensagem", "Instrumento apagado com sucesso!");
        return "redirect:/listar";
    }

}
