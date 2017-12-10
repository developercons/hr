package com.recruiting.service.company;

import com.recruiting.service.company.dto.CompanyConfigDTO;

/**
 * @author Marta Ginosyan
 */

public interface CompanyFormService {

    CompanyConfigDTO prepareCompany();
    CompanyConfigDTO updateCompany(CompanyConfigDTO company);

}
