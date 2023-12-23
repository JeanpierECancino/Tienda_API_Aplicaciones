package uss.edu.pe.PizarroDelgado.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uss.edu.pe.PizarroDelgado.entity.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	public Categoria findByNombre(String nombre);
	public List<Categoria> findByNombreContaining(String nombre,Pageable page);
}
