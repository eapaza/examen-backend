package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.model.Signo;
import com.mitocode.service.ISignoService;

@RestController
@RequestMapping("/signos")
public class SignoController {
	
	@Autowired
	private ISignoService service;
	
	@PreAuthorize("@restAuthService.hasAccess('signos')")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Signo>> Listar() 
	{
		List<Signo> lstSignos = service.listar(); //new ArrayList<>();
		//lstSignos = service.listar();
		return new ResponseEntity<List<Signo>> (lstSignos,HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Signo> listarPorId(@PathVariable("id") Integer id) {
		Signo sig = service.listarId(id);
		if (sig == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		return new ResponseEntity<Signo> (sig,HttpStatus.OK);
	}
	
	@PreAuthorize("@restAuthService.hasAccess('signos')")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Signo> registrar(@RequestBody Signo signo)
	{
		Signo sig = service.registrar(signo);
		return new ResponseEntity<Signo> (sig, HttpStatus.CREATED);
	}
	
	@PutMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> modificar(@RequestBody Signo signo) {
		service.modificar(signo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Signo sig = service.listarId(id);
		if (sig == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}
	}
	
	
}
