package com.rupeshshrestha.usermanagement.repository;

import com.rupeshshrestha.usermanagement.entity.IdentifiableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * The base interface for all repositories.
 *
 * @param <T>
 *          the entity type
 */
@NoRepositoryBean
@Repository
public interface BaseRepository<T extends IdentifiableEntity> extends JpaRepository<T, Long> {
}
