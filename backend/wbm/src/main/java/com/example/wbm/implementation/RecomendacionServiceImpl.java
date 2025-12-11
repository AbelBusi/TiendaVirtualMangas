package com.example.wbm.implementation;


import com.example.wbm.model.entity.Recomendacion;
import com.example.wbm.repository.RecomendacionRepository;
import com.example.wbm.services.RecomendacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecomendacionServiceImpl implements RecomendacionService {

    private final RecomendacionRepository repo;

    @Override
    public List<Recomendacion> listar() {
        return repo.findAll();
    }

    @Override
    public Recomendacion guardar(Recomendacion r) {
        return repo.save(r);
    }

    @Override
    public Recomendacion buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
