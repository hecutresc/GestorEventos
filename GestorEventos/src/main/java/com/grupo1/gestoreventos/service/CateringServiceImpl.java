package com.grupo1.gestoreventos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.CateringDTO;
import com.grupo1.gestoreventos.model.dto.DireccionDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.repository.dao.CateringRepository;
import com.grupo1.gestoreventos.repository.dao.EmpresaRepository;
import com.grupo1.gestoreventos.repository.entity.Catering;
import com.grupo1.gestoreventos.repository.entity.Direccion;
import com.grupo1.gestoreventos.repository.entity.Empresa;

@Service
public class CateringServiceImpl implements CateringService {

	private static final Logger log = LoggerFactory.getLogger(CateringServiceImpl.class);

	@Autowired
	private CateringRepository cateringRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<CateringDTO> findAll() {
		// TODO Auto-generated method stub
		log.info("CateringServiceImpl - findAll: Mostramos todos los caterings");
		List<Catering> listaCaterings = cateringRepository.findAll();
		List<CateringDTO> listaCateringsDTO = new ArrayList<>();
		
		//Recorremos la lista que nos ha traido la bd convirtiendola en dto y poniendole el nombre de la empresa que es lo que nos interesa ver por pantalla
		for (Catering catering : listaCaterings) {
			CateringDTO cateringDTO = CateringDTO.convertToDTO(catering);
			cateringDTO.getEmpresaDTO().setNombre(catering.getEmpresa().getNombre());
			listaCateringsDTO.add(cateringDTO);			
		}

		return listaCateringsDTO;
	}

	@Override
	public CateringDTO findById(CateringDTO cateringDTO) {
		// TODO Auto-generated method stub

		log.info("CateringServiceImpl - findById: Buscar Direccion por id: " + cateringDTO.getId());
		Optional<Catering> catering = cateringRepository.findById(cateringDTO.getId());
		if (catering.isPresent()) {
			cateringDTO = CateringDTO.convertToDTO(catering.get());
			return cateringDTO;
		} else {
			return null;
		}
	}

	@Override
	public void save(CateringDTO cateringDTO) {
		// TODO Auto-generated method stub
		log.info("CateringServiceImpl - save: Salvamos el Catering: " + cateringDTO.toString());
		
		//Convertimos el cateringDTO a entidad
		Catering catering = CateringDTO.convertToEntity(cateringDTO);
		//Le seteamos la empresa al catering
		Empresa empresa = new Empresa();
		empresa.setId(cateringDTO.getEmpresaDTO().getId());
		catering.setEmpresa(empresa);
		cateringRepository.save(catering);

	}

	@Override
	public void delete(CateringDTO cateringDTO) {
		// TODO Auto-generated method stub

		log.info("CateringServiceImpl - delete: Borramos la Direccion: " + cateringDTO.getId());
		cateringRepository.deleteById(cateringDTO.getId());

	}

	@Override
	public List<CateringDTO> findAllByEmpresa(EmpresaDTO empresaDTO) {
		// TODO Auto-generated method stub
		log.info("CateringServiceImpl - findAllByEmpresa: Recogemos todos los caterings de la empresa : " + empresaDTO.getId());
		Optional empresa = empresaRepository.findById(empresaDTO.getId());
		List<Catering> listaCaterings = cateringRepository.findAllByEmpresa(empresaDTO.getId());
		List<CateringDTO> listaCateringsDTO = new ArrayList<CateringDTO>();
		for (Catering catering : listaCaterings) {
			CateringDTO cateringDTO = CateringDTO.convertToDTO(catering);
			cateringDTO.setEmpresaDTO(EmpresaDTO.convertToDTO((Empresa)empresa.get()));
			listaCateringsDTO.add(cateringDTO);
			
		}
		return listaCateringsDTO;
	}

}
