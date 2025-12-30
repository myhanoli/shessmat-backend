package com.hanoli.demojwt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Marca;
import com.hanoli.demojwt.repository.MarcaRepository;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> buscarPorNombre(String query) {
        return marcaRepository.findByNombreContainingIgnoreCase(query);
    }
}