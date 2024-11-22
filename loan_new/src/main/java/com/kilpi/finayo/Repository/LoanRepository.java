package com.kilpi.finayo.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.kilpi.finayo.Domain.Loan.ProfileEntity;
import com.kilpi.finayo.Domain.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Domain.BankEntity;

@Repository("loanRepository")
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

	@Query(value = "SELECT l FROM LoanEntity l where l.id = ?1")
	LoanEntity getById(long id);
	
	@Query(value = "SELECT sum(l.loanAmount) FROM LoanEntity l")
    public Long sumTotalLoan();

	@Query(value = "SELECT sum(l.loanAmount) FROM LoanEntity l where l.executive.branch.dsa.id = ?1")
	public Long sumTotalLoanByDealer(Integer dealerId);

	@Query(value = "SELECT sum(l.loanAmount) FROM LoanEntity l WHERE l.approval.lender = ?1")
	public Long sumTotalLoanByBank(BankEntity bank);

	@Query(value = "SELECT sum(l.loanAmount) FROM LoanEntity as l where l.status in (:status) AND l.approval.lender = (:bank)")
	public Long getSummaryByStatusAndBank(@Param("status")List<LoanStatus> statusList, @Param("bank")BankEntity bank);
	
	 @Query(value = "SELECT sum(l.loanAmount) FROM LoanEntity as l where l.status in (:status)")
	 public Long getSummaryByStatus(@Param("status")List<LoanStatus> statusList);


	@Query(value = "SELECT sum(l.loanAmount) FROM LoanEntity as l where l.status in (:status) AND l.executive.branch.dsa.id = :dealer")
	public Long getSummaryByStatusAndDealer(@Param("status")List<LoanStatus> statusList, @Param("dealer")Integer dealerId);

	
	 @Query(value = "SELECT count(*) FROM LoanEntity as l where l.status in (:status)")
	 public Long totalCountByStatus(@Param("status")List<LoanStatus> statusList);
	
	@Query(value = "SELECT distinct count(l.id) FROM LoanEntity as l where l.status in (:status) and l.updatedDate BETWEEN :startDate AND :endDate")
    public Long getLoanReportByStatusDate(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status")List<LoanStatus> statusList);

	@Query(value = "SELECT distinct count(l.id) FROM LoanEntity as l where l.status in (:status) and l.updatedDate BETWEEN :startDate AND :endDate AND l.executive.id = (:executiveId)")
    public Long getLoanReportByStatusDateAndExecutive(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status")List<LoanStatus> statusList,@Param("executiveId")Integer executiveId);

	@Query(value = "SELECT distinct count(l.id) FROM LoanEntity as l where l.status in (:status) and l.updatedDate BETWEEN :startDate AND :endDate AND l.approval.lender = (:bank)")
	public Long getLoanReportByStatusDateAndBank(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status")List<LoanStatus> statusList, @Param("bank")BankEntity bank);

	@Query(value="select  distinct  count(loan_details.id)"
			+ "from loan_details "
			+ "LEFT JOIN bank_entity_loan ON loan_details.id =bank_entity_loan.loan_id "
			+ "where bank_entity_loan.status = :status and loan_details.updatedDate BETWEEN :startDate AND :endDate "
			+ "AND bank_entity_loan.bank_entity_id=:id", nativeQuery=true)
	public Long getLoanReportByStatusDateAndBankReject(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status")String statusList, @Param("id")Integer id);

	@Query(value="select  distinct  count(l.id),b.name \r\n"
			+ "from LoanEntity l \r\n"
			+ "join l.executive e\r\n"
			+ "join e.branch b \r\n"
			+ "where l.status = :status GROUP BY b.name")
    public List<Object[]> getStatusByBranch(@Param("status") LoanStatus status);
    
	@Query(value="select  distinct  count(l.id),b.name \r\n"
			+ "from LoanEntity l \r\n"
			+ "join l.executive e\r\n"
			+ "join e.branch b \r\n"
			+ "join l.banks q ON (q.loan.id=l.id)\r\n"
			+ "where l.status = 'NEW' and size(l.banks)>0 and q.status= :status  GROUP BY b.name")
    public List<Object[]> getApproveStatusByBranch(@Param("status") String status);
    
	@Query(value="select  distinct  count(l.id),b.name \r\n"
			+ "from LoanEntity l \r\n"
			+ "join l.executive e\r\n"
			+ "join e.branch b \r\n"
			+ "where l.status = :status and l.updatedDate BETWEEN :startDate AND :endDate GROUP BY b.name")
    public List<Object[]> getDailyStatusByBranch(@Param("status") LoanStatus status ,
    		@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
    
	@Query(value="select  distinct  count(l.id),b.name \r\n"
			+ "from LoanEntity l \r\n"
			+ "join l.executive e\r\n"
			+ "join e.branch b \r\n"
			+ "join l.banks q ON (q.loan.id=l.id)\r\n"
			+ "where l.status = 'NEW' and size(l.banks)>0 and q.status= :status and l.updatedDate BETWEEN :startDate AND :endDate GROUP BY b.name")
    public List<Object[]> getDailyAPPROVEStatusByBranch(@Param("status") String status ,
    		@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
    
//	@Query(value="select  distinct  count(loan_details.id),branch.first_name \r\n"
//			+ "from profile_details \r\n"
//			+ "JOIN loan_details ON loan_details.profile_id =profile_details.pid\r\n"
//			+ "join executive  on profile_details.executive_id = executive.id \r\n"
//			+ "join branch on branch.id = executive.branch_id\r\n"
//			+ "where loan_details.status = :status and loan_details.updatedby BETWEEN :startDate AND :endDate"
//			+ " GROUP BY branch.first_name", nativeQuery=true)
//    public List<Object[]> getDailyStatusByBranch(@Param("status") String status ,
//    		@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
    
    
	@Query(value="select SUM(CASE WHEN loan_details.status='APPLY' THEN 1 ELSE 0 END) as applied,\r\n"
			+ "SUM(CASE WHEN loan_details.status='HOLD' THEN 1 ELSE 0 END) as hold,\r\n"
			+ "SUM(CASE WHEN loan_details.status='REJECT' THEN 1 ELSE 0 END) as rejected,\r\n"
			+ "SUM(CASE WHEN loan_details.status='DISBURSE' THEN 1 ELSE 0 END) as disbursh\r\n "
			+ "JOIN executive  on loan_details.executive_id = executive.id", nativeQuery=true)
    public List<Object[]> getCountAllExecutive();

	@Query(value="select SUM(CASE WHEN loan_details.status='APPLY' THEN 1 ELSE 0 END) as applied,\r\n"
			+ "SUM(CASE WHEN loan_details.status='HOLD' THEN 1 ELSE 0 END) as hold,\r\n"
			+ "SUM(CASE WHEN loan_details.status='REJECT' THEN 1 ELSE 0 END) as rejected,\r\n"
			+ "SUM(CASE WHEN loan_details.status='DISBURSE' THEN 1 ELSE 0 END) as disbursh\r\n "
			+ "JOIN loan_approval_details la  on loan_details.id = la.lender_id\r\n "
			+ "from loan_details where la.lender_id=?1", nativeQuery=true)
	public List<Object[]> getCountAllByBank(Integer id);
    
	@Query(value="select SUM(CASE WHEN loan_details.status='APPLY' THEN 1 ELSE 0 END) as applied,\r\n"
			+ "SUM(CASE WHEN loan_details.status='HOLD' THEN 1 ELSE 0 END) as hold,\r\n"
			+ "SUM(CASE WHEN loan_details.status='REJECT' THEN 1 ELSE 0 END) as rejected,\r\n"
			+ "SUM(CASE WHEN loan_details.status='DISBURSE' THEN 1 ELSE 0 END) as disbursh\r\n"
			+ "from loan_details \r\n"
			+ "join executive  on loan_details.executive_id = executive.id \r\n"
			+ "where executive.first_name= :name GROUP BY executive.first_name", nativeQuery=true)
    public List<Object[]> getCountByExecutiveName(@Param("name")String name);
    
	@Query(value="select  distinct  count(loan_details.id)\r\n"
			+ "from loan_details \r\n"
			+ "join executive  on loan_details.executive_id = executive.id \r\n"
			+ "join branch  on branch.id = executive.branch_id\r\n"
			+ "where loan_details.status in (:status) and branch.first_name=:name ", nativeQuery=true)
    public List<Object[]> getCountByBranch(@Param("name")String name,@Param("status") List<String> status);
    
	@Query(value="select SUM(CASE WHEN loan_details.status='APPLY' THEN 1 ELSE 0 END) as applied,\r\n"
			+ "SUM(CASE WHEN loan_details.status='HOLD' THEN 1 ELSE 0 END) as hold,\r\n"
			+ "SUM(CASE WHEN loan_details.status='REJECT' THEN 1 ELSE 0 END) as rejected,\r\n"
			+ "SUM(CASE WHEN loan_details.status='DISBURSE' THEN 1 ELSE 0 END) as disbursh\r\n"
			+ "from loan_details \r\n"
			+ "where loan_details.executive_id= :id GROUP BY loan_details.executive_id", nativeQuery=true)
    public List<Object[]> getCountByExecutiveId(@Param("id")Integer id);
    
	@Query(value="select  distinct  count(loan_details.id)\r\n"
			+ "from loan_details \r\n"
			+ "join executive  on loan_details.executive_id = executive.id \r\n"
			+ "join branch  on branch.id = executive.branch_id\r\n"
			+ "where loan_details.status in (:status) and branch.id=:id ", nativeQuery=true)
    public List<Object[]> getCountByBranchId(@Param("id")Integer name,@Param("status") List<String> status);
    
	@Query(value="select SUM(CASE WHEN loan_details.status='APPLY' THEN 1 ELSE 0 END) as applied,\r\n"
			+ "SUM(CASE WHEN loan_details.status='HOLD' THEN 1 ELSE 0 END) as hold,\r\n"
			+ "SUM(CASE WHEN loan_details.status='REJECT' THEN 1 ELSE 0 END) as rejected,\r\n"
			+ "SUM(CASE WHEN loan_details.status='DISBURSE' THEN 1 ELSE 0 END) as disbursh,\r\n"
			+ "SUM(CASE WHEN loan_details.status='NEW' AND bel.status='APPROVE' THEN 1 ELSE 0 END) as approve,\r\n"
			+ "SUM(CASE WHEN loan_details.status='INPROGRESS' THEN 1 ELSE 0 END) as inprogress\r\n"
			+ "from loan_details \r\n"
			+ "JOIN bank_entity_loan bel ON loan_details.id=bel.loan_id\r\n"
			+ "where loan_details.executive_id= :id and loan_details.updatedDate BETWEEN :startDate AND :endDate", nativeQuery=true)
    public List<Object[]> getCountByExecutiveDate(@Param("id")Integer id,
    		@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
    
	@Query(value="select SUM(CASE WHEN loan_details.status='APPLY' THEN 1 ELSE 0 END) as applied,\r\n"
			+ "SUM(CASE WHEN loan_details.status='HOLD' THEN 1 ELSE 0 END) as hold,\r\n"
			+ "SUM(CASE WHEN loan_details.status='REJECT' THEN 1 ELSE 0 END) as rejected,\r\n"
			+ "SUM(CASE WHEN loan_details.status='DISBURSE' THEN 1 ELSE 0 END) as disbursh\r\n"
			+ "from loan_details where loan_details.updatedDate BETWEEN :startDate AND :endDate", nativeQuery=true)
    public List<Object[]> getCountByDate(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);


	@Query("SELECT l FROM LoanEntity l WHERE l.approval.lender = ?1")
	List<LoanEntity> findByBank(BankEntity bankEntity);


	@Query("SELECT l FROM LoanEntity l WHERE l.approval.lender.id = ?1")
	List<LoanEntity> findByBankId(Integer id);


	@Query("SELECT l FROM LoanEntity l WHERE l.executive.id = ?1 ORDER BY l.id DESC")
    List<LoanEntity> findByExecutiveId(Integer id);

	@Query("SELECT l FROM LoanEntity l WHERE l.status = ?1")
	List<LoanEntity> findByStatus(LoanStatus status);



	@Query("SELECT l FROM LoanEntity l WHERE l.executive.id = ?1 AND l.status = ?2")
	List<LoanEntity> findByExecutiveIdAndStatus(Integer id, LoanStatus loanStatus);
	
	//Admin Query
//	@Query(value="select  distinct  count(loan_details.id)"
//			+ "from profile_details "
//			+ "LEFT JOIN loan_details ON loan_details.profile_id =profile_details.pid "
//			+ "LEFT JOIN executive  on profile_details.executive_id = executive.id "
//			+ "LEFT JOIN branch on branch.id = executive.branch_id "
//			+ "LEFT JOIN dsa on branch.business_type_id = dsa.id "
//			+ "where loan_details.status = :status and loan_details.updatedby BETWEEN :startDate AND :endDate "
//			+ "AND dsa.id=:id", nativeQuery=true)
//    public Long getLoanReportByStatusDateAndAdmin(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") String statusList,@Param("id")Integer id);
//	
	
	//Admin Query
	@Query(value="select  distinct  count(l.id)"
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN DSAEntity d on (b.dsa.id = d.id) "
			+ "where l.status = :status and l.updatedDate BETWEEN :startDate AND :endDate "
			+ "AND d.id=:id")
    public Long getLoanReportByStatusDateAndAdmin(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("status") LoanStatus statusList,@Param("id")Integer id);

	@Query("SELECT l FROM LoanEntity l WHERE l.approval.lender = ?1 AND l.status = ?2")
	List<LoanEntity> findByBankAndStatus(BankEntity exe, LoanStatus status);

	@Query("SELECT l FROM LoanEntity l WHERE l.status = ?1")
	List<LoanEntity> findByBankAndStatusNEW(LoanStatus loanStatus);

	@Query(value = "SELECT distinct count(l.id) FROM LoanEntity as l where l.status in (:status) and l.updatedDate BETWEEN :startDate AND :endDate")
	Long getLoanReportByStatusDateNew(LocalDateTime startDate, LocalDateTime endDate, List<LoanStatus> status);
	
	@Query(value = "SELECT l FROM LoanEntity as l where l.updatedDate BETWEEN :startDate AND :endDate AND l.executive.id = :executiveId")
    List<LoanEntity> getLoanByStatusDateAndExecutive(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("executiveId")Integer executiveId);
	
	@Query(value = "SELECT l FROM LoanEntity as l where l.updatedDate BETWEEN :startDate AND :endDate")
    List<LoanEntity> getLoanByStatusDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN b.dsa d "
			+ "where l.status = :status "
			+ "AND d.id=:id")
	List<LoanEntity> getLoanByStatusAdmin(@Param("status") LoanStatus status, @Param("id") Integer id);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN b.dsa d "
			+ "where b.id = :bId "
			+ "AND d.id=:id")
	List<LoanEntity> getLoanByBranchAdmin(@Param("bId") Integer bId, @Param("id") Integer id);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN b.dsa d "
			+ "where e.id = :eId "
			+ "AND d.id=:id")
	List<LoanEntity> getLoanByExecutiveAdmin(@Param("eId") Integer eId, @Param("id") Integer id);

    @Query(value="SELECT l FROM LoanEntity as l where l.updatedDate BETWEEN :startDate AND :endDate AND l.executive.branch.id = :id ")
    List<LoanEntity> getCountByBranchDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("id")Integer id);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "where e.id = :eId AND l.status = :status ")
	List<LoanEntity> getLoanByExecutive(@Param("eId") Integer eId, @Param("status") LoanStatus status);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN b.dsa d "
			+ "where l.updatedDate BETWEEN :startDate AND :endDate "
			+ "AND d.id=:id")
	List<LoanEntity> getLoanByDateAdmin(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("id") Integer id);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "where e.id = :eId AND "
			+ "l.updatedDate BETWEEN :startDate AND :endDate")
	List<LoanEntity> getLoanByExecutiveDate(@Param("eId") Integer eId
			, @Param("startDate") LocalDateTime startDate
			, @Param("endDate") LocalDateTime endDate);

	@Query(value = "SELECT distinct l FROM LoanEntity as l where l.updatedDate BETWEEN :startDate AND :endDate AND l.approval.lender = (:bank)")
	public List<LoanEntity> getLoanByDateAndBank(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("bank")BankEntity bank);

	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN b.dsa d "
			+ "where l.updatedDate BETWEEN :startDate AND :endDate ")
	List<LoanEntity> getLoanByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "where l.updatedDate BETWEEN :startDate AND :endDate and l.executive.branch.dsa.id = :dealer")
	List<LoanEntity> getLoanByDateByDsa(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("dealer") Integer dealerId);


	@Query(value="select  distinct l "
			+ "from LoanEntity l "
			+ "JOIN l.executive e "
			+ "JOIN e.branch b "
			+ "JOIN b.dsa d "
			+ "where d.id=:id  ")
	List<LoanEntity> getLoanByDealer(@Param("id") Integer id);

}
