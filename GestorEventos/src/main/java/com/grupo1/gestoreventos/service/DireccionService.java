package com.grupo1.gestoreventos.service;

import java.util.List;
import com.grupo1.gestoreventos.model.dto.DireccionDTO;
import com.grupo1.gestoreventos.model.dto.EmpresaDTO;
import com.grupo1.gestoreventos.model.dto.UbicacionDTO;
import com.grupo1.gestoreventos.model.dto.UsuarioDTO;

public interface DireccionService {

	public List<DireccionDTO> findAll();

	public DireccionDTO findById(DireccionDTO direccionDTO);

	public DireccionDTO save(DireccionDTO direccionDTO);

	public void delete(DireccionDTO direccionDTO);

	public DireccionDTO findByUsuario(UsuarioDTO usuarioDTO);

	public DireccionDTO findByEmpresa(EmpresaDTO empresaDTO);

	public DireccionDTO findByUbicacion(UbicacionDTO ubicacionDTO);

	

}
