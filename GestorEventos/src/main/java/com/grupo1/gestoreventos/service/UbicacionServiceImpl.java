package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.repository.dao.UbicacionRepository;
import com.grupo1.gestoreventos.repository.entity.Ubicacion;

@Service
public class UbicacionServiceImpl implements UbicacionService{

	private static final Logger log = LoggerFactory.getLogger(UbicacionServiceImpl.class);
	
	@Autowired
	private UbicacionRepository ubicacionRepository;
	
	@Override
	public List<UbicacionDTO> findAll() {
		// TODO Auto-generated method stub
		
		List<UbicacionDTO> listaUbicacionesDTO = ubicacionRepository.findAll().stream().map(u -> UbicacionDTO.convertToDTO(u)).collect(Collectors.toList()); 
		return listaUbicacionesDTO;
	}

	@Override
	public UbicacionDTO findById(UbicacionDTO ubicacionDTO) {
		// TODO Auto-generated method stub
		log.info("CateringServiceImpl - findById: Buscar Direccion por id: " + ubicacionDTO.getId());
		Optional<Ubicacion> ubicacion = ubicacionRepository.findById(ubicacionDTO.getId());
		
		if(ubicacion.isPresent()) {
			ubicacionDTO = UbicacionDTO.convertToDTO(ubicacion.get());
			return ubicacionDTO;
		}else {
			return null;
		}
	}

	@Override
	public void save(UbicacionDTO ubicacionDTO) {
		// TODO Auto-generated method stub
		log.info("UbicacionServiceImpl - save: Salvamos la Ubicacion: " + ubicacionDTO.toString());
		
		Ubicacion ubicacion = UbicacionDTO.convertToEntity(ubicacionDTO);
		ubicacionRepository.save(ubicacion);
		
	}

	@Override
	public void delete(UbicacionDTO ubicacionDTO) {
		// TODO Auto-generated method stub
		log.info("UbicacionServiceImpl - delete: Borramos la Ubicacion: " + ubicacionDTO.getId());
		
		ubicacionRepository.deleteById(ubicacionDTO.getId());
	}

}
