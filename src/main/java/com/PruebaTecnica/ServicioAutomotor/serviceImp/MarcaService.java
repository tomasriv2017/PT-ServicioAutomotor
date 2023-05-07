package com.PruebaTecnica.ServicioAutomotor.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.ServicioAutomotor.interfaceService.IMarcaService;
import com.PruebaTecnica.ServicioAutomotor.models.Marca;
import com.PruebaTecnica.ServicioAutomotor.repositories.IMarcaRepository;

@Service
public class MarcaService implements IMarcaService{

	 @Autowired
	 private IMarcaRepository marcaRepository;
	
	@Override
	public List<Marca> listar() {
		// TODO Auto-generated method stub
		return marcaRepository.findAll();
	}

	@Override
	public Optional<Marca> traerById(int id) {
		// TODO Auto-generated method stub
		return marcaRepository.findById(id);
	}

	@Override
	public Optional<Marca> traerByMarca(String nombreMarca) {
		// TODO Auto-generated method stub
		return marcaRepository.findMarcaByNombreMarca(nombreMarca);
	}

	@Override
	public Marca saveOrUpdate(Marca marca) {
		// TODO Auto-generated method stub
        Optional<Marca> marcadb = marcaRepository.findById(marca.getIdMarca());
        if( !marcadb.isPresent() ) {
            return marcaRepository.save(marca);
        }else {
            map(marca, marcadb.get());
            return marcaRepository.save(marcadb.get());
       }
	}

	@Override
	public void delete(int idMarca) {
		// TODO Auto-generated method stub
		marcaRepository.deleteById(idMarca);
	}
	
	
	private void map(Marca modificado, Marca preModificado ){

        if(modificado.getNombreMarca()!=null) {
            preModificado.setNombreMarca(modificado.getNombreMarca());
        }
        
	}
}
