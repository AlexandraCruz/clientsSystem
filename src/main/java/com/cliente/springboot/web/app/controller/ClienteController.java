package com.cliente.springboot.web.app.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cliente.springboot.web.app.models.entity.Cliente;
import com.cliente.springboot.web.app.models.service.IClienteService;
import com.cliente.springboot.web.app.util.paginator.PageRender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	//@Qualifier("clienteDaoJPA")
	private IClienteService iClienteService;
			
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue = "0") int page,  Model model) {
		
		Pageable pageable =  PageRequest.of(page, 5 /*5 records per page*/);
		
		Page<Cliente> clientes_pages =  iClienteService.findAll(pageable);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes_pages);	
		
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes_pages);
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
		
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar (@PathVariable(value="id") Long id,  Map<String,Object> model, RedirectAttributes redirectAttributes) throws InterruptedException {
		
		Cliente cliente = null;
		
		if( id > 0) {
			cliente = iClienteService.findOne(id);
			
			if (cliente == null) {
				
				redirectAttributes.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
			
		} else {
			redirectAttributes.addFlashAttribute("error", "El ID del cliente debe ser mayor a cero!");
			return "redirect:/listar";
		} 
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile picture, RedirectAttributes redirectAttributes , SessionStatus sessionStatus )
		{ 
		if (bindingResult.hasErrors())
		{
			model.addAttribute("titulo", "Formulario de Clientes");
			return "/form";
		}

		if(!picture.isEmpty()){
			Path ResourcesDirectory = Paths.get( "src//main//resources//static/uploads " );
			String rootPath = ResourcesDirectory.toFile().getAbsolutePath();
			try {
				byte[] bytes = picture.getBytes();
				Path path = Paths.get((rootPath.concat("//").concat(picture.getOriginalFilename())));
				Files.write(path,bytes);
				redirectAttributes.addFlashAttribute("info",picture.getOriginalFilename().concat("have been successfully uploaded")) ;
				cliente.setPicture(picture.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String mensajeFlash = ( cliente.getId() != null)? "Cliente editado con éxito!" : "Cliente creado con éxito!";
		
		iClienteService.save(cliente);
		sessionStatus.setComplete(); /*this method delete client object from web session*/
		redirectAttributes.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes) {
		
		if(id > 0) {
			iClienteService.delete(id);
			redirectAttributes.addFlashAttribute("success", "Cliente eliminado con éxito!");
		}
		return "redirect:/listar";
	}
}