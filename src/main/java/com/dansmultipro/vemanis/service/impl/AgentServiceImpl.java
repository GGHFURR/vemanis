package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.agent.AgentRes;
import com.dansmultipro.vemanis.dto.agent.CreateAgentReq;
import com.dansmultipro.vemanis.dto.agent.UpdateAgentReq;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.model.Agent;
import com.dansmultipro.vemanis.repository.AgentRepo;
import com.dansmultipro.vemanis.service.AgentService;
import com.dansmultipro.vemanis.service.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgentServiceImpl extends BaseService implements AgentService {

    private AgentRepo agentRepo;

    public AgentServiceImpl(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }

    @Override
    public List<AgentRes> getAll(){
        return agentRepo.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public AgentRes getById(UUID id){
        return agentRepo.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new NotFoundException("Agent Tidak Ditemukan"));
    }

    @Transactional
    @Override
    public CreateResDTO createAgent(CreateAgentReq req){
        if(agentRepo.existsByName(req.getName())){
            throw new BadRequestException("Please Input Another Name");
        }

        Agent agent = new Agent();
        agent.setName(req.getName());
        createBase(agent);

        return new CreateResDTO(agent.getId(), "Agent Created");
    }

    @Transactional
    @Override
    public UpdateResDTO updateAgent(String id, UpdateAgentReq req){
        var agent = agentRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Data Agent Tidak Ditemukan"));

        if(!agent.getName().equals(req.getName())){
            Optional<Agent> existingAgent = agentRepo.findByName(req.getName());
            if(existingAgent.isPresent()){
                throw new DuplicateResourceException("Data Is Not Unique");
            }
        }

        if(!agent.getVersion().equals(req.getVersion())){
            throw new BadRequestException("Please Refresh The Page");
        }

        agent.setName(req.getName());
        updateBase(agent);

        agentRepo.saveAndFlush(agent);

        return new UpdateResDTO(agent.getVersion(),"Updated");
    }

    @Override
    public DeleteResDTO delete(String id){
        agentRepo.deleteById(UUID.fromString(id));
        return new DeleteResDTO("Deleted");
    }

    private AgentRes mapToResponse(Agent agent){
        return new AgentRes(
                agent.getId(), agent.getName());
    }
}

