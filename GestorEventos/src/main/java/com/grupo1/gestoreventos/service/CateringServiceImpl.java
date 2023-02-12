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
import com.grupo1.gestoreventos.repository.dao.CateringRepository;
import com.grupo1.gestoreventos.repository.entity.Catering;
import com.grupo1.gestoreventos.repository.entity.Direccion;

@Service
public class CateringServiceImpl implements CateringService {

	private static final Logger log = LoggerFactory.getLogger(CateringServiceImpl.class);

	@Autowired
	private CateringRepository cateringRepository;

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

		Catering catering = CateringDTO.convertToEntity(cateringDTO);
		cateringRepository.save(catering);

	}

	@Override
	public void delete(CateringDTO cateringDTO) {
		// TODO Auto-generated method stub

		log.info("DireccionServiceImpl - delete: Borramos la Direccion: " + cateringDTO.getId());
		cateringRepository.deleteById(cateringDTO.getId());

	}

}
