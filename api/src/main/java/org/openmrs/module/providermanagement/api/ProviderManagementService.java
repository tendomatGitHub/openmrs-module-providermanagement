/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.providermanagement.api;

import org.openmrs.Provider;
import org.openmrs.RelationshipType;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.providermanagement.ProviderRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Provider Management Service
 */
@Transactional
public interface ProviderManagementService extends OpenmrsService {

    // TODO: add permissions

	/*
	 * Basic methods for operating on provider roles
	 */

    /**
     * Gets all unretired provider roles
     * @return list of all unretired provider roles
     */
    @Transactional(readOnly = true)
    public List<ProviderRole> getAllProviderRoles();

    /**
     * Gets all Provider Roles in the database
     *
     * @param includeRetired whether or not to include retired providers
     * @return list of all provider roles in the system
     */
    @Transactional(readOnly = true)
    public List<ProviderRole> getAllProviderRoles(boolean includeRetired);

    /**
     * Gets the provider role referenced by the specified id
     *
     * @param id
     * @return providerRole
     */
    @Transactional(readOnly = true)
    public ProviderRole getProviderRole(Integer id);

    /**
     * Gets the provider role referenced by the specified uui
     *
     * @param uuid
     * @return providerRole
     */
    @Transactional(readOnly = true)
    public ProviderRole getProviderRoleByUuid(String uuid);

    /**
     * Returns all the provider roles that support the specified relationship type
     * (Excludes retired provider roles)
     *
     * @param relationshipType
     * @return the provider roles that support that relationship type
     * @should throw exception if relationshipType is null
     */
    @Transactional(readOnly = true)
    public List<ProviderRole> getProviderRolesByRelationshipType(RelationshipType relationshipType);

    /**
     * Returns all provider roles that are able to supervise the specified provider role
     * (Excluded retired provider roles)
     *
     * @param providerRole
     * @return the provider roles that can supervise the specified provider role
     * @should throw exception if providerRole is null
     */
    @Transactional(readOnly = true)
    public List<ProviderRole> getProviderRolesBySuperviseeProviderRole(ProviderRole providerRole);

    /**
     * Saves/updates a provider role
     *
     * @param role the provider role to save
     */
    @Transactional
    public void saveProviderRole(ProviderRole role);

    /**
     * Retires a provider role
     * @param role the role to retire
     * @param reason the reason the role is being retired
     */
    @Transactional
    public void retireProviderRole(ProviderRole role, String reason);

    /**
     * Unretires a provider role
     * @param role the role to unretire
     */
    @Transactional
    public void unretireProviderRole(ProviderRole role);
    
    /**
     * Deletes a provider role
     *
     * @param role the provider role to delete
     */
    @Transactional
    public void purgeProviderRole(ProviderRole role);

    /**
     * Basic methods for operating on providers using the new provider roles
     */

    /**
     * Assigns a provider role to a provider
     * Overwrites any existing role for that provider
     *
     * @param provider the provider whose role we wish to set
     * @param role the role to set
     */
    @Transactional
    public void setProviderRole(Provider provider, ProviderRole role);


    // TODO: would this method be better in a util class?

    /**
     * Gets the provider role associated with this provider
     *
     * @param provider the provider we are looking for the provider role for
     * @return the role associated with this provider
     */
    @Transactional(readOnly = true)
    public ProviderRole getProviderRole(Provider provider);


    /**
     * Gets all providers whose role is in the list of specified roles
     *
     * @param roles
     * @return all providers with one of the specified roles
     * @should throw APIException if roles are empty or null
     */
    public List<Provider> getProvidersByRoles(List<ProviderRole> roles);
    
    /**
     * Gets all providers with the specified role
     * (Excludes retired providers)
     *
     * @param role
     * @return list of providers with the specified role
     * @should throw APIException if role is null
     */
    @Transactional(readOnly = true)
    public List<Provider> getProvidersByRole(ProviderRole role);

    /**
     * Gets all providers that support the specified relationship type
     *
     * @param relationshipType
     * @return the list of providers that support the specified relationship type
     * @should throw API Exception if relationship type is null
     */
    @Transactional(readOnly = true)
    public List<Provider> getProvidersByRelationshipType(RelationshipType relationshipType);

    /**
     * Gets all providers that can supervise the specified provider role
     * 
     * @param role
     * @return the list of providers that can supervise the specific provider role
     * @should throw API Exception if the provider role is null
     */
    @Transactional(readOnly = true)
    public List<Provider> getProvidersBySuperviseeProviderRole(ProviderRole role);

}