package com.kilpi.finayo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilpi.finayo.Domain.Comment;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

    @Query(value = "select c from Comment c where c.loan.id=?1")
    Comment getCommentByLoan(Long id);

}
