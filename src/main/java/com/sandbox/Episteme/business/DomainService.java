package com.sandbox.Episteme.business;

import com.sandbox.Episteme.model.Dimension;
import com.sandbox.Episteme.model.Domain;
import com.sandbox.Episteme.model.DomainCategory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import com.sandbox.Episteme.repository.DomainRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DomainService {

    private DomainRepository domainRepository;

    public DomainService(DomainRepository repo) {
        domainRepository = repo;
    }

    public Domain createDomain(String domainName, DomainCategory categ, String desc) {
        Optional<Domain> opt = domainRepository.findByName(domainName);
        if (opt.isPresent())
            return opt.get();
        if (desc==null || Strings.isEmpty(desc))
            desc=domainName;
        Domain dom = new Domain(domainName, categ, desc);
        domainRepository.save(dom);
        return dom;
    }
    public void updateDomain(Domain dom) {
        domainRepository.save(dom);
    }

    public List<Domain> lookupAll() {
        return domainRepository.findAll();
    }


}
