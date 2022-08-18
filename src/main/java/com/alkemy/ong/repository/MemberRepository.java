package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.ong.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    
}
