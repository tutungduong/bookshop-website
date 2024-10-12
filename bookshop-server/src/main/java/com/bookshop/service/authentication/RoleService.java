package com.bookshop.service.authentication;

import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.authentication.RoleRequest;
import com.bookshop.dto.authentication.RoleResponse;
import com.bookshop.entity.authentication.Role;
import com.bookshop.repository.authentication.RoleRepository;
import com.bookshop.service.CrudService;
import com.bookshop.utils.SearchUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService implements CrudService<Long, RoleRequest, RoleResponse> {

    private final RoleRepository roleRepository;

    @Override
    public ListResponse<RoleResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Role> sortable = RSQLJPASupport.toSort(sort);
        Specification<Role> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Role> searchable = SearchUtils.parse(search, SearchFields.ROLE);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Role> entities = roleRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<RoleResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }

    @Override
    public RoleResponse findById(Long id) {
          return roleRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public RoleResponse save(RoleRequest request) {
        Role role = requestToEntity(request);
        role = roleRepository.save(role);
        return entityToResponse(role);
    }

    @Override
    public RoleResponse save(Long id, RoleRequest request) {
        return roleRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(roleRepository::save)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        roleRepository.deleteAllById(ids);
    }

    private Role requestToEntity(RoleRequest request) {
        Role role = new Role();
        role.setCode(request.getCode());
        role.setName(request.getName());
        role.setStatus(request.getStatus());
        return role;
    }

    private Role partialUpdate(Role role, RoleRequest request) {
        role.setCode(request.getCode());
        role.setName(request.getName());
        role.setStatus(request.getStatus());
        role.setUpdatedAt(Instant.now());
        return role;
    }

    private RoleResponse entityToResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setCreatedAt(role.getCreatedAt());
        response.setUpdatedAt(role.getUpdatedAt());
        response.setCode(role.getCode());
        response.setName(role.getName());
        response.setStatus(role.getStatus());
        return response;
    }
}
