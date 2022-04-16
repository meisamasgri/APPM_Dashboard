package ir.mci.appm_dashboard.repository.sync;

import ir.mci.appm_dashboard.entity.sync.SyncResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyncResultRepository extends JpaRepository<SyncResult,Integer> {
//    List<SyncResult> findByHOSTIPAndTYPESHORTNAME(String hostIp, String TYPESHORTNAME);
    Optional<SyncResult> findById(Integer id);
}
