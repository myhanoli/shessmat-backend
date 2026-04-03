package com.hanoli.demojwt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable, UserDetails{
	
	private static final long serialVersionUID = 4629780573695595838L;
	
	
	
	@Id
	//Configuracion para Oracle
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLI_SEQ")
	//@SequenceGenerator(name = "CLI_SEQ",sequenceName = "cliente_seq",initialValue=1, allocationSize = 1 )
	
	//Configuracion para MySQL
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Long id;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("apellidoPat")
	private String apellidoPat;
	@JsonProperty("apellidoMat")
	private String apellidoMat;
	@JsonProperty("direccion")
	private String direccion;
	@JsonProperty("telefono")
	private String telefono;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("roles")
	@Convert(converter = RolesConverter.class)
	private Set<String> roles;
	@JsonProperty("email")
	private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles != null ? roles.stream().map(SimpleGrantedAuthority::new).toList() : List.of();
    }
    
    
    
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }



	public Usuario() {
	
	}


	
	/*@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Folio> folios;*/
	
	
}
