package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.dto.StudentOrder;

public interface StudentOrderRepo extends JpaRepository<StudentOrder, Long> {

	public StudentOrder findByRazorPayOrderId(String razorPayOrderId);

}
