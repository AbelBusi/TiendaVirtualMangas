package com.example.wbm.services;



import com.example.wbm.model.entity.Recomendacion;

import java.util.List;

public interface RecomendacionService {

    List<Recomendacion> listar();

    Recomendacion guardar(Recomendacion r);

    Recomendacion buscarPorId(Integer id);

    void eliminar(Integer id);
}
