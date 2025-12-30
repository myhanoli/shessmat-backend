package com.hanoli.demojwt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Equipo;
import com.hanoli.demojwt.repository.EquipoRepository;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> buscarPorNombre(String query) {
        return equipoRepository.findByNombreContainingIgnoreCase(query);
    }
}