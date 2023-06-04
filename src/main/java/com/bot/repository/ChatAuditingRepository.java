package com.bot.repository;

import com.bot.entity.ChatAuditing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatAuditingRepository extends JpaRepository<ChatAuditing, Long> {
}
