package com.sandbox.Episteme.business;

import com.sandbox.Episteme.model.Dimension;
import com.sandbox.Episteme.model.DimensionCategory;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import com.sandbox.Episteme.repository.DimensionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DimensionService {

    private DimensionRepository dimensionRepository;

    public DimensionService(DimensionRepository repo) {
        dimensionRepository = repo;
    }

    public Dimension createDimension(String dimensionName, DimensionCategory cat, String desc) {
        if (desc==null || Strings.isEmpty(desc))
            desc=dimensionName;
        try  {
            Optional<Dimension> dim = dimensionRepository.findByName(dimensionName);
            if (dim.isPresent())
                return dim.get();
        } catch(Exception ex) {
           // ex.printStackTrace();
        }
        Dimension dim = new Dimension(dimensionName, cat, desc);
        dimensionRepository.save(dim);
        return dim;
    }

    public void updateDimension(Dimension dim) {
        dimensionRepository.save(dim);
    }
    public List<Dimension> lookupAll() {
        return dimensionRepository.findAll();
    }


}
