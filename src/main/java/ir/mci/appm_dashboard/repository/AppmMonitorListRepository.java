package ir.mci.appm_dashboard.repository;

import ir.mci.appm_dashboard.entity.AppmMonitorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppmMonitorListRepository extends JpaRepository<AppmMonitorList , Integer> {
    List<AppmMonitorList> findAllByAppmId(Integer id);
//    <List>AppmMonitorList deleteAllByAppmId(Integer id);
}
