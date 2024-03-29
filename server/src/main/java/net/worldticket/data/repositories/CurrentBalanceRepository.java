package net.worldticket.data.repositories;

import net.worldticket.data.CurrentBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentBalanceRepository extends JpaRepository<CurrentBalance, Long> {

    CurrentBalance findFirstByOrderByTimestampDesc();
}
