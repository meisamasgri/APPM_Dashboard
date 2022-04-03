package ir.mci.appm_dashboard.repository;


import ir.mci.appm_dashboard.entity.AppmApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppmApiKeyRepository extends JpaRepository<AppmApiKey , Integer> {
}
