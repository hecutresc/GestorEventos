package com.grupo1.gestoreventos.repository.entity;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "cateringubicacionevento")
public class CateringUbicacionEvento {
	//Atributos
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@Column(name = "fechahora")
		private Date fechahora;
		
		@ManyToOne
		@JoinColumn(name = "id_catering")
		@ToString.Exclude
		private Catering catering;
		
		@ManyToOne
		@JoinColumn(name = "id_ocio")
		@ToString.Exclude
		private Ocio ocio;
		
		@ManyToOne
		@JoinColumn(name = "id_ubicacion")
		@ToString.Exclude
		private Ubicacion ubicacion;
		
		@ManyToOne
		@JoinColumn(name = "id_evento")
		@ToString.Exclude
		private Evento evento;
		
		//HashCode && Equals
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CateringUbicacionEvento other = (CateringUbicacionEvento) obj;
			return Objects.equals(id, other.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		
		//Constructor
		public CateringUbicacionEvento() {
			this.catering = new Catering();
			this.ocio = new Ocio();
			this.evento = new Evento();
			this.ubicacion = new Ubicacion();
		}
}
