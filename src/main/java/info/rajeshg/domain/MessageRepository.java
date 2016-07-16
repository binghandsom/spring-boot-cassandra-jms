package info.rajeshg.domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CassandraRepository<Message>{
	
//	@Query("select * from message where valueDate = ?0")
//    Iterable findByFname(String valueDate);

}
