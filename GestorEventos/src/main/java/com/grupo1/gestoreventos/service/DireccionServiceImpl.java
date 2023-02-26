package com.grupo1.gestoreventos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.DireccionDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.repository.dao.DireccionRepository;
import com.grupo1.gestoreventos.repository.entity.Direccion;

@Service
public class DireccionServiceImpl implements DireccionService {

	private static final Logger log = LoggerFactory.getLogger(DireccionServiceImpl.class);

	@Autowired
	private DireccionRepository direccionRepository;

	@Override
	public List<DireccionDTO> findAll() {
		log.info("DireccionServiceImpl - findAll: Lista de todas las Direcciones");

		List<DireccionDTO> listaDireccionesDTO = direccionRepository.findAll().stream()
				.map(p -> DireccionDTO.convertToDTO(p)).collect(Collectors.toList());

		return listaDireccionesDTO;
	}

	@Override
	public DireccionDTO findById(DireccionDTO direccionDTO) {
		log.info("DireccionServiceImpl - findById: Buscar Direccion por id: " + direccionDTO.getId());

		Optional<Direccion> direccion = direccionRepository.findById(direccionDTO.getId());
		if (direccion.isPresent()) {
			direccionDTO = DireccionDTO.convertToDTO(direccion.get());
			return direccionDTO;
		} else {
			return null;
		}
	}

	@Override
	public DireccionDTO save(DireccionDTO direccionDTO) {
		log.info("DireccionServiceImpl - save: Salvamos la Direccion: " + direccionDTO.toString());

		Direccion direccion = DireccionDTO.convertToEntity(direccionDTO);
		direccion = direccionRepository.save(direccion);
		direccionDTO = DireccionDTO.convertToDTO(direccion);
		
		return direccionDTO;
	}

	@Override
	public void delete(DireccionDTO direccionDTO) {
		log.info("DireccionServiceImpl - delete: Borramos la Direccion: " + direccionDTO.getId());

		direccionRepository.deleteById(direccionDTO.getId());
	}

	@Override
	public DireccionDTO findByUsuario(UsuarioDTO usuarioDTO) {
		log.info("DireccionServiceImpl - findByUsuario: Busca la Direccion del Usuario: " + usuarioDTO.getId());

		Optional<Direccion> direccion = direccionRepository.findByUsuario(usuarioDTO.getId());

		return DireccionDTO.convertToDTO(direccion.get());
	}

	@Override
	public DireccionDTO findByEmpresa(EmpresaDTO empresaDTO) {
		log.info("DireccionServiceImpl - findByEmpresa: Busca la Direccion de la Empresa: " + empresaDTO.getId());

		Optional<Direccion> direccion = direccionRepository.findByEmpresa(empresaDTO.getId());

		return DireccionDTO.convertToDTO(direccion.get());
	}

	@Override
	public DireccionDTO findByUbicacion(UbicacionDTO ubicacionDTO) {
		log.info("DireccionServiceImpl - findByUbicacion: Busca la Direccion de la Ubicacion: " + ubicacionDTO.getId());

		Optional<Direccion> direccion = direccionRepository.findByUbicacion(ubicacionDTO.getId());

		return DireccionDTO.convertToDTO(direccion.get());
	}
}
