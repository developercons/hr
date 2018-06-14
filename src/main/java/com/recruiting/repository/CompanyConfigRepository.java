package com.recruiting.repository;

import com.recruiting.entity.CompanyConfig;

/**
 * @author Marta Ginosyan
 */

public interface CompanyConfigRepository extends BaseRepository<CompanyConfig> {
    CompanyConfig findFirstByIsActiveTrue();
}
