package com.sandbox.Episteme.business;

import com.sandbox.Episteme.model.Resource;
import org.springframework.stereotype.Service;
import com.sandbox.Episteme.repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceService {

    private ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository repo) {
        resourceRepository = repo;
    }

    public Resource createResource(String resourceName, String desc) {
        return resourceRepository.findByName(resourceName)
                .orElse(resourceRepository.save(new Resource(resourceName, desc)));
    }

    public List<Resource> lookupAll() {
        return resourceRepository.findAll();
    }


}
