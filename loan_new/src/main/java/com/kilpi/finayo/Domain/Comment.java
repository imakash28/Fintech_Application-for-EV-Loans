package com.kilpi.finayo.Domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.kilpi.finayo.VO.CommentVO;

import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {
	
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "comment")
	    private String comment;

	    @Column(name = "role")
	    private String role;
	    
	    @Column(name = "createdby")
	    private String createdBy;
	    
	    @Column(name = "created_date")
	    private LocalDateTime createdDate;
	    

		@OneToOne(fetch = FetchType.LAZY,optional = false)
		@JoinColumn(
				name = "id",
				referencedColumnName = "id"
		)
	    private LoanEntity loan;
	    
	    public CommentVO toVo() {
	        return CommentVO.builder()
	                .comment(comment)
	                .commentId(id)
	                .createdBy(createdBy)
	                .createdDate(createdDate)
	                .role(role)
					.build();
	    }

	}
