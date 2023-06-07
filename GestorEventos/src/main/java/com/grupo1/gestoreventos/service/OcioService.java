package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.OcioDTO;

public interface OcioService {
	
	public List<OcioDTO> findAll();
	public OcioDTO findById(OcioDTO ocioDTO);
	public void save(OcioDTO ocioDTO);
	public void delete(OcioDTO ocioDTO);
	public List<OcioDTO> findAllByEmpresa(EmpresaDTO empresaDTO);
}
