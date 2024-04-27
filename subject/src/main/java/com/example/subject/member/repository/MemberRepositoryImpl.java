package com.example.subject.member.repository;

import com.example.subject.member.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {

        em.persist(member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long memberId) {

        Member member = em.find(Member.class, memberId);

        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {

        List<Member> memberList = em.createQuery("select m from Member as m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();

        return memberList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }
}
