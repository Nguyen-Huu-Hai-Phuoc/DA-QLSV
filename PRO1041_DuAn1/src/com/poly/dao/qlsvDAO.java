/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import java.util.List;

/**
 *
 * @author Admin
 * @param <EntityType>
 * @param <KeyType>
 */
abstract public class qlsvDAO<EntityType, KeyType> {

    abstract public void insert(EntityType entity);

    abstract public void update(EntityType entity);

    abstract public void delete(KeyType key);

    abstract public List<EntityType> selectAll();

    abstract public EntityType selectById(KeyType key);

    abstract protected List<EntityType> selectBySql(String sql, Object... args);
}
