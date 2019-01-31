package pl.karoll.spring.homebudget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karoll.spring.homebudget.model.Invitation;

import java.util.List;

public interface InvitationRepository extends JpaRepository <Invitation, Long> {

    List<Invitation> findAllByReciverId (Long id);


}
