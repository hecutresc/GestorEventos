package com.grupo1.gestoreventos.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.grupo1.gestoreventos.repository.entity.Empresa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data

public class EmpresaDTO {

	private Long id;
	
	@NotBlank(message = "¡El CIF es obligatorio!")
	@Pattern(regexp = "[A-HJNP-SUVW]{1}\\d{7}[0-9A-J]{1}", message = "¡El CIF no es válido!")
	private String cif;
	
	@NotBlank(message = "¡El nombre de la empresa es obligatorio!")
	private String nombre;
	
    @NotBlank(message = "¡El email de contacto es obligatorio!")
    @Email(message = "¡El email de contacto debe ser válido!")
	private String email_contacto;
    
    @Pattern(regexp = "\\d{9}", message = "¡El teléfono de contacto debe tener 9 dígitos!")
    @NotBlank(message = "¡El teléfono de contacto es obligatorio!")
	private String telefono_contacto;
    
    @NotBlank(message = "¡La web es obligatoria!")
    @URL(message = "¡La URL del sitio web debe ser válida!")
	private String web;
	
	@ToString.Exclude
	@Valid
	private DireccionDTO direccionDTO;
	@ToString.Exclude
	private List<CateringDTO> listaCateringsDTO;
	
	@ToString.Exclude
	private List<OcioDTO> listaOcioDTO;
	
	@ToString.Exclude
	private List<DecoradoDTO> listaDecoradosDTO;
	
	//Metodos de Conversión
	
	//ConvertToDTO
	public static EmpresaDTO convertToDTO(Empresa empresa) {
		//Creando los objetos y listas necesarios
		EmpresaDTO empresaDTO = new EmpresaDTO();
		
		//Set de los atributos
		empresaDTO.setId(empresa.getId());
		empresaDTO.setCif(empresa.getCif());
		empresaDTO.setNombre(empresa.getNombre());
		empresaDTO.setEmail_contacto(empresa.getEmail_contacto());
		empresaDTO.setTelefono_contacto(empresa.getTelefono_contacto());
		empresaDTO.setWeb(empresa.getWeb());
		empresaDTO.setDireccionDTO(DireccionDTO.convertToDTO(empresa.getDireccion()));
		return empresaDTO;
	}
	
	//ConvertToEntity
	public static Empresa convertToEntity(EmpresaDTO empresaDTO) {
		//Creando los objetos y listas necesarios
		Empresa empresa = new Empresa();
		//Set de los atributos
		empresa.setId(empresaDTO.getId());
		empresa.setCif(empresaDTO.getCif());
		empresa.setNombre(empresaDTO.getNombre());
		empresa.setEmail_contacto(empresaDTO.getEmail_contacto());
		empresa.setTelefono_contacto(empresaDTO.getTelefono_contacto());
		empresa.setWeb(empresaDTO.getWeb());
		empresa.setDireccion(DireccionDTO.convertToEntity(empresaDTO.getDireccionDTO()));
		return empresa;
	}
	
	
	//Constructor
	public EmpresaDTO() {
		this.direccionDTO = new DireccionDTO();
		this.listaCateringsDTO = new ArrayList<CateringDTO>();
		this.listaOcioDTO = new ArrayList<OcioDTO>();
		this.listaDecoradosDTO = new ArrayList<DecoradoDTO>();
	}
}