package com.kilpi.finayo.Service.Impl;

import java.time.LocalDateTime;

import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.Repository.LoanRepository;
import com.kilpi.finayo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kilpi.finayo.Domain.Comment;
import com.kilpi.finayo.Domain.UserEntity;
import com.kilpi.finayo.Repository.CommentRepository;
import com.kilpi.finayo.Service.CommentService;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.VO.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	LoanRepository loanRepository;

	@Override
	public CommentVO createComment(CommentVO commentVO,int lid) {
		UserEntity user = userService.getUser();
		LoanEntity loanEntity =null;
		loanEntity =loanRepository.getById(lid);
		if(loanEntity ==null){
			throw new NotFoundException("No loan for this id");
		}
		Comment comment = new Comment();
		comment.setComment(commentVO.getComment());
		comment.setCreatedBy(user.getUsername());
		comment.setCreatedDate(LocalDateTime.now());
		comment.setLoan(loanEntity);
		comment.setRole(userService.getRoleFromAuth());
		return commentRepository.save(comment).toVo();
	}

	@Override
	public CommentVO updateComment(CommentVO commentVO,Long loanId) {
		UserEntity user = userService.getUser();
		Comment comment = null;
		comment = commentRepository.getCommentByLoan(loanId);
		if(comment==null){
			throw new NotFoundException("No comment found for this loan");
		}
		comment.setComment(commentVO.getComment());
		comment.setCreatedBy(user.getUsername());
		comment.setCreatedDate(LocalDateTime.now());
//		comment.setLoanId(commentVO.getLoanId());
		comment.setRole(userService.getRoleFromAuth());
//		comment.setLoanId(loanId);
		return commentRepository.save(comment).toVo();

	}

	@Override
	public CommentVO getCommByLoanId(Long loanId) {
		Comment comment = null;
		comment = commentRepository.getCommentByLoan(loanId);
		if(comment==null){
			throw new NotFoundException("No comment found for this loan");
		}
		return comment.toVo();
		}


}
