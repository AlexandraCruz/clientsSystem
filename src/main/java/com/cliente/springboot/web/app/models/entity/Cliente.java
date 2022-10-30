package com.cliente.springboot.web.app.models.entity;
import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
/*
 * @Data
All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, 
and @Setter on all non-final fields, and @RequiredArgsConstructor!
 * */
@Entity
@Table(name = "clientes") /* table name in lower case and plural */
public class Cliente implements Serializable {
	/*
	 * Always implement Serializable interface for Entity classes. Serialization The
	 * process of converting an object to a sequence of bytes, to saving or transmit
	 * them to memory or db or JSON or XML or when we are working with http sesion
	 * serialization is required
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty  /* @NotEmpty tag is only for string data type 
	attributes, and @NotNull tag is  for other data types attributes.*/
	private String name;
	
	@NotEmpty
	@Column
	private String lastname;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;

	private String picture;

	private static final long serialVersionUID = 1L;
	/* class name in Camel case and singular */

	public Cliente(String name, String lastname, String email, Date createAt )
	{
		this.name = name;
		this.lastname =	lastname;
		this.email=	email;
		this.createAt=	createAt;
	}

	public Cliente(String name, String lastname, String email, Date createAt, String picture )
	{
		this.name = name;
		this.lastname =	lastname;
		this.email=	email;
		this.createAt=	createAt;
		this.picture = picture;
	}

	public Cliente( )
	{
		
	}
}
