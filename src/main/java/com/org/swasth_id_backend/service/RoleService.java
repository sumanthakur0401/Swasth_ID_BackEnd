package com.org.swasth_id_backend.service;


import com.org.swasth_id_backend.dto.RoleDto;
import com.org.swasth_id_backend.entity.Role;
import com.org.swasth_id_backend.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {

    public List<RoleDto> getAllRoles();
    public Role getRoleById(Short id) throws ResourceNotFoundException;
    public void deleteRoleById(Short id) throws ResourceNotFoundException;
    public void createRole(Role role);

    public void assignRole(Short userId, Short roleId) throws ResourceNotFoundException;
    public void unAssignRole(Short userId, Short roleId) throws ResourceNotFoundException;

}
