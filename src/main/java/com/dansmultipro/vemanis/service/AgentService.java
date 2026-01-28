package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.agent.AgentRes;
import com.dansmultipro.vemanis.dto.agent.CreateAgentReq;
import com.dansmultipro.vemanis.dto.agent.UpdateAgentReq;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface AgentService {
    List<AgentRes> getAll();

    AgentRes getById(String id);

    CreateResDTO createAgent(CreateAgentReq req);

    UpdateResDTO updateAgent(String id, UpdateAgentReq req);

    DeleteResDTO delete(String id);
}
