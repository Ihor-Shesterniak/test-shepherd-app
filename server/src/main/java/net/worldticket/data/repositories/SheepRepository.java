package net.worldticket.data.repositories;

import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SheepRepository extends JpaRepository<Sheep, Long> {

    List<Sheep> findAllByState(State state);

}
