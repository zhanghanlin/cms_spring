package com.demo.java.service;

import java.util.List;

import com.demo.java.entity.CustomPage;

public interface CustomPageService {

    public void save(CustomPage t);

    public List<CustomPage> getList();

    public CustomPage get(Long id);

    public int update(CustomPage t);

    public int delete(Long id);
}