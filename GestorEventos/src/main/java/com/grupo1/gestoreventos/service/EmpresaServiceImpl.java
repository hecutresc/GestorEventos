package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.repository.dao.EmpresaRepository;
import com.grupo1.gestoreventos.repository.entity.Empresa;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Override
	public List<EmpresaDTO> findAll() {
		// TODO Auto-generated method stub
		log.info("EmpresaServiceImpl - findAll: Mostramos todas las empresas");
		List<EmpresaDTO> listaEmpresaDTO = empresaRepository.findAll().stream().map(e -> EmpresaDTO.convertToDTO(e)).collect(Collectors.toList());
		return listaEmpresaDTO;
	}

	@Override
	public EmpresaDTO findById(EmpresaDTO empresaDTO) {
		// TODO Auto-generated method stub
		log.info("EmpresaServiceImpl - findById: Buscar Empresa por id: " + empresaDTO.getId());
		Optional<Empresa> empresa = empresaRepository.findById(empresaDTO.getId());
		
		if(empresa.isPresent()) {
			empresaDTO = EmpresaDTO.convertToDTO(empresa.get());
			return empresaDTO;
		}else {
			return null;
		}
	}

	@Override
	public void save(EmpresaDTO empresaDTO) {
		// TODO Auto-generated method stub
		log.info("EmpresaServiceImpl - save: Salvamos la Empresa: " + empresaDTO.toString());
		
		Empresa empresa = EmpresaDTO.convertToEntity(empresaDTO);
		empresaRepository.save(empresa);
	}

	@Override
	public void delete(EmpresaDTO empresaDTO) {
		// TODO Auto-generated method stub
		log.info("EmpresaServiceImpl - delete: Borramos la Empresa: " + empresaDTO.getId());
		
		empresaRepository.deleteById(empresaDTO.getId());
		
	}

}