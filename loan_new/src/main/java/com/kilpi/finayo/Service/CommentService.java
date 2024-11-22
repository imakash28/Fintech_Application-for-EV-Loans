package com.kilpi.finayo.Service;

import com.kilpi.finayo.VO.CommentVO;

public interface CommentService {
	
	CommentVO createComment(CommentVO comment, int lid);

	CommentVO updateComment(CommentVO comment,Long loanId);
	
	CommentVO getCommByLoanId(Long loanId);

}
