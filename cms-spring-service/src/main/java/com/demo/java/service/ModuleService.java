package com.demo.java.service;

import java.util.List;

import com.demo.java.entity.Module;

public interface ModuleService {

    public void save(Module t);

    public List<Module> getList();

    public Module get(Long id);

    public int update(Module t);

    public int delete(Long id);
}