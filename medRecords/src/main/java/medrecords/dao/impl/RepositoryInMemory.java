package medrecords.dao.impl;

import medrecords.dao.IdGenerator;
import medrecords.dao.Repository;
import medrecords.model.Identifiable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositoryInMemory<K,V extends Identifiable<K>> implements Repository<K,V> {

    private Map<K,V> entities= new HashMap<>();
    private IdGenerator<K> idGenerator;

    public RepositoryInMemory(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public List<V> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getnNextId());
        entities.put(entity.getId(), entity);
       return entity;
    }

    @Override
    public Optional<V> update(V entity) {
        var old = findById(entity.getId());
        if(old.isEmpty()){
            return old;
        }
        entities.put(entity.getId(), entity);
        return  Optional.of(entity);
    }

    public Optional<V> deleteById(K id) {
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public long count() {
        return entities.size();
    }
}
