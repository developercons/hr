package com.recruiting.repository;

import com.recruiting.domain.CompanyConfig;

/**
 * @author Marta Ginosyan
 */

public interface CompanyConfigRepository extends BaseRepository<CompanyConfig> {
    CompanyConfig findFirstByIsActiveTrue();
}
