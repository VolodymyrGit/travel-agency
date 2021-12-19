package vml.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vml.travelagency.model.Role;
import vml.travelagency.repository.RoleRepo;
import vml.travelagency.service.RoleService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Role readById(long id) {
        return roleRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role with id " + id + " not found"));
    }
}
