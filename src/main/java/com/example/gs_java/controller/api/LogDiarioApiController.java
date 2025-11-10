package com.example.gs_java.controller.api;

import com.example.gs_java.dtos.LogDiarioRequestDTO;
import com.example.gs_java.dtos.LogDiarioResponseDTO;
import com.example.gs_java.model.LogDiario;
import com.example.gs_java.model.Usuario;
import com.example.gs_java.service.LogDiarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/log")
public class LogDiarioApiController {


    @Autowired
    private LogDiarioService logDiarioService;


    @PostMapping("/salvar")
    public ResponseEntity<LogDiarioResponseDTO> adicionarLog(
            @RequestBody @Valid LogDiarioRequestDTO logDiarioRequestDTO,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        LogDiario logSalvo = logDiarioService.adicionarLog(logDiarioRequestDTO, usuarioLogado);

        LogDiarioResponseDTO responseDTO = new LogDiarioResponseDTO(logSalvo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(logSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }



    @GetMapping("/meusLogs")
    public ResponseEntity<Page<LogDiarioResponseDTO>> listarLogs(
            @AuthenticationPrincipal Usuario usuarioLogado,
            @PageableDefault(size = 10, sort = "data", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
        Page<LogDiario> logPage = logDiarioService.listarLogsPorUsuario(usuarioLogado, pageable);
        Page<LogDiarioResponseDTO> responsePage = logPage.map(LogDiarioResponseDTO::new);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/search/{email:.+}")
    public ResponseEntity<Page<LogDiarioResponseDTO>> listarLogsPorEmail(
            @PathVariable("email") String email,
            @PageableDefault(size = 10, sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<LogDiario> logPage = logDiarioService.listarLogsPorEmail(email, pageable);
        Page<LogDiarioResponseDTO> responsePage = logPage.map(LogDiarioResponseDTO::new);

        return ResponseEntity.ok(responsePage);
    }


    @GetMapping("/buscarPeloID/{id}")
    public ResponseEntity<LogDiarioResponseDTO> buscarLogPorId(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        LogDiario log = logDiarioService.buscarLogPorId(id, usuarioLogado);
        return ResponseEntity.ok(new LogDiarioResponseDTO(log));
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LogDiarioResponseDTO> atualizarLog(
            @PathVariable Long id,
            @RequestBody @Valid LogDiarioRequestDTO logDiarioRequestDTO,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        LogDiario logAtualizado = logDiarioService.atualizarLog(id, logDiarioRequestDTO, usuarioLogado);
        return ResponseEntity.ok(new LogDiarioResponseDTO(logAtualizado));
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarLog(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        logDiarioService.deletarLog(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }
}
