package com.grupo1.gestoreventos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo1.gestoreventos.model.dto.DecoradoDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.OcioDTO;
import com.grupo1.gestoreventos.repository.dao.DecoradoRepository;
import com.grupo1.gestoreventos.repository.dao.EmpresaRepository;
import com.grupo1.gestoreventos.repository.dao.OcioRepository;
import com.grupo1.gestoreventos.repository.entity.Decorado;
import com.grupo1.gestoreventos.repository.entity.Empresa;
import com.grupo1.gestoreventos.repository.entity.Ocio;

@Service
public class DecoradoServiceImpl implements DecoradoService {

	// Log
	private static final Logger log = LoggerFactory.getLogger(DecoradoServiceImpl.class);
	// Objetos Autowired
	@Autowired
	private DecoradoRepository decoradoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<DecoradoDTO> findAll() {
		// TODO Auto-generated method stub
		log.info("DecoradoServiceImpl - findAll: Mostramos todos los decorados disponibles de la empresa");
		List<Decorado> listaDecorados = decoradoRepository.findAll();
		List<DecoradoDTO> listaDecoradosDTO = new ArrayList<>();

		// Recorremos la lista que nos ha traido la bd convirtiendola en dto y
		// poniendole el nombre de la empresa que es lo que nos interesa ver por
		// pantalla
		for (Decorado decorado : listaDecorados) {
			DecoradoDTO decoradoDTO = DecoradoDTO.convertToDTO(decorado);
			decoradoDTO.getEmpresaDTO().setNombre(decorado.getEmpresa().getNombre());
			listaDecoradosDTO.add(decoradoDTO);
		}

		return listaDecoradosDTO;
	}

	@Override
	public DecoradoDTO findById(DecoradoDTO decoradoDTO) {
		// TODO Auto-generated method stub
		log.info("DecoradoServiceImpl - findById: Buscar Decorado por id: " + decoradoDTO.getId());
		Optional<Decorado> decorado = decoradoRepository.findById(decoradoDTO.getId());
		if (decorado.isPresent()) {
			decoradoDTO = DecoradoDTO.convertToDTO(decorado.get());
			return decoradoDTO;
		} else {
			return null;
		}
	}

	@Override
	public void save(DecoradoDTO decoradoDTO) {
		// TODO Auto-generated method stub
		log.info("DecoradoServiceImpl - save: Salvamos el Decorado: " + decoradoDTO.toString());

		// Convertimos el cateringDTO a entidad
		Decorado decorado = DecoradoDTO.convertToEntity(decoradoDTO);
		// Le seteamos la empresa al catering
		Empresa empresa = new Empresa();
		empresa.setId(decoradoDTO.getEmpresaDTO().getId());
		decorado.setEmpresa(empresa);
		decoradoRepository.save(decorado);
	}

	@Override
	public void delete(DecoradoDTO decoradoDTO) {
		// TODO Auto-generated method stub
		log.info("DecoradoServiceImpl - delete: Borramos el decorado: " + decoradoDTO.getId());
		decoradoRepository.deleteById(decoradoDTO.getId());
	}

	@Override
	public List<DecoradoDTO> findAllByEmpresa(EmpresaDTO empresaDTO) {
		// TODO Auto-generated method stub
		log.info("DecoradoServiceImpl - findAllByEmpresa: Recogemos todos los decorados de la empresa : " + empresaDTO.getId());
		Optional<Empresa> empresa = empresaRepository.findById(empresaDTO.getId());
		List<Decorado> listaDecorados = decoradoRepository.findAllByEmpresa(empresaDTO.getId());
		List<DecoradoDTO> listaDecoradosDTO = new ArrayList<DecoradoDTO>();
		for (Decorado decorado : listaDecorados) {
			DecoradoDTO decoradoDTO = DecoradoDTO.convertToDTO(decorado);
			decoradoDTO.setEmpresaDTO(EmpresaDTO.convertToDTO((Empresa)empresa.get()));
			listaDecoradosDTO.add(decoradoDTO);
			
		}
		return listaDecoradosDTO;
	}

}
