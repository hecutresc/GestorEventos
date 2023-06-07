package com.grupo1.gestoreventos.service;

import java.util.List;

import com.grupo1.gestoreventos.model.dto.DecoradoDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;

public interface DecoradoService {

	public List<DecoradoDTO> findAll();
	public DecoradoDTO findById(DecoradoDTO decoradoDTO);
	public void save(DecoradoDTO decoradoDTO);
	public void delete(DecoradoDTO decoradoDTO);
	public List<DecoradoDTO> findAllByEmpresa(EmpresaDTO empresaDTO);
}
