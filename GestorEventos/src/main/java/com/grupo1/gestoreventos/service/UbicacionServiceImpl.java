package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.EventoDTO;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.repository.dao.DireccionRepository;
import com.grupo1.gestoreventos.repository.dao.UbicacionRepository;
import com.grupo1.gestoreventos.repository.entity.Direccion;
import com.grupo1.gestoreventos.repository.entity.Empresa;
import com.grupo1.gestoreventos.repository.entity.Ubicacion;

@Service
public class UbicacionServiceImpl implements UbicacionService {

	private static final Logger log = LoggerFactory.getLogger(UbicacionServiceImpl.class);

	@Autowired
	private UbicacionRepository ubicacionRepository;

	@Autowired
	private DireccionRepository direccionRepository;

	@Override
	public List<UbicacionDTO> findAll() {
		// TODO Auto-generated method stub

		List<UbicacionDTO> listaUbicacionesDTO = ubicacionRepository.findAll().stream()
				.map(u -> UbicacionDTO.convertToDTO(u)).collect(Collectors.toList());
		return listaUbicacionesDTO;
	}

	@Override
	public UbicacionDTO findById(UbicacionDTO ubicacionDTO) {
		// TODO Auto-generated method stub
		log.info("CateringServiceImpl - findById: Buscar Direccion por id: " + ubicacionDTO.getId());
		Optional<Ubicacion> ubicacion = ubicacionRepository.findById(ubicacionDTO.getId());

		if (ubicacion.isPresent()) {
			ubicacionDTO = UbicacionDTO.convertToDTO(ubicacion.get());
			return ubicacionDTO;
		} else {
			return null;
		}
	}

	@Override
	public void save(UbicacionDTO ubicacionDTO) {
		// TODO Auto-generated method stub
		log.info("UbicacionServiceImpl - save: Salvamos la Ubicacion: " + ubicacionDTO.toString());
		try {
			if (ubicacionDTO.getId() == null) {
				Ubicacion ubicacion = UbicacionDTO.convertToEntity(ubicacionDTO);
				// Tendremos que guardar primero la direccion, luego ponersela a la empresa y
				// por último guardar la empresa
				Direccion direccion = direccionRepository.save(ubicacion.getDireccion());
				ubicacion.setDireccion(direccion);
				ubicacionRepository.save(ubicacion);
			} else {
				Ubicacion ubicacion = UbicacionDTO.convertToEntity(ubicacionDTO);
				Optional<Direccion> direccion = direccionRepository.findByUbicacion(ubicacion.getId());
				ubicacion.setDireccion(direccion.get());
				ubicacionRepository.save(ubicacion);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Error de guardado " + e);
		}

	}

	@Override
	public void delete(UbicacionDTO ubicacionDTO) {
		// TODO Auto-generated method stub
		log.info("UbicacionServiceImpl - delete: Borramos la Ubicacion: " + ubicacionDTO.getId());

		ubicacionRepository.deleteById(ubicacionDTO.getId());
	}


}
