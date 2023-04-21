package com.grupo1.gestoreventos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.CateringDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.OcioDTO;
import com.grupo1.gestoreventos.repository.dao.EmpresaRepository;
import com.grupo1.gestoreventos.repository.dao.OcioRepository;
import com.grupo1.gestoreventos.repository.entity.Catering;
import com.grupo1.gestoreventos.repository.entity.Ocio;

@Service

public class OcioServiceImpl implements OcioService{
	
	private static final Logger log = LoggerFactory.getLogger(OcioServiceImpl.class);
	
	//Objetos Autowired
	@Autowired
	private OcioRepository ocioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<OcioDTO> findAll() {
		// TODO Auto-generated method stub
		log.info("OcioServiceImpl - findAll: Mostramos todos los ocios disponibles de la empresa");
		List<Ocio> listaOcios = ocioRepository.findAll();
		List<OcioDTO> listaOciosDTO = new ArrayList<>();
		
		//Recorremos la lista que nos ha traido la bd convirtiendola en dto y poniendole el nombre de la empresa que es lo que nos interesa ver por pantalla
		for (Ocio ocio : listaOcios) {
			OcioDTO ocioDTO = OcioDTO.convertToDTO(ocio);
			ocioDTO.getEmpresaDTO().setNombre(ocio.getEmpresa().getNombre());
			listaOciosDTO.add(ocioDTO);			
		}

		return listaOciosDTO;
	}

	@Override
	public OcioDTO findById(OcioDTO ocioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(OcioDTO ocioDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OcioDTO ocioDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OcioDTO> findAllByEmpresa(EmpresaDTO empresaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
