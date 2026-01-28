package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.agent.AgentRes;
import com.dansmultipro.vemanis.dto.agent.CreateAgentReq;
import com.dansmultipro.vemanis.dto.agent.UpdateAgentReq;
import com.dansmultipro.vemanis.service.AgentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public ResponseEntity<List<AgentRes>> getAll(){
        var res = agentService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentRes> getById(@PathVariable String id){
        var res = agentService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(@Valid @RequestBody CreateAgentReq req){
        var res = agentService.createAgent(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDTO> update(@PathVariable String id, @Valid @RequestBody UpdateAgentReq req){
        var res = agentService.updateAgent(id, req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDTO> delete(@PathVariable String id){
        var res = agentService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
