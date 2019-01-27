package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.ISignoDAO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signo;
import com.mitocode.service.ISignoService;

@Service
public class SignoServiceImpl implements ISignoService{

	@Autowired
	private ISignoDAO dao;
	
	@Override
	public Signo registrar(Signo t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Signo modificar(Signo t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Signo listarId(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public List<Signo> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
