package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.agent.AgentRes;
import com.dansmultipro.vemanis.dto.agent.CreateAgentReq;
import com.dansmultipro.vemanis.dto.agent.UpdateAgentReq;
import com.dansmultipro.vemanis.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/agent")
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
        var res = agentService.getById(UUID.fromString(id));

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(@RequestBody CreateAgentReq req){
        var res = agentService.createAgent(req);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDTO> update(@PathVariable String id, @RequestBody UpdateAgentReq req){
        var res = agentService.updateAgent(id, req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDTO> delete(@PathVariable String id){
        var res = agentService.delete(id);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
