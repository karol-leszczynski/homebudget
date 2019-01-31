package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.model.Invitation;
import pl.karoll.spring.homebudget.model.User;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.InvitationRepository;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class InvitationService {

    private InvitationRepository invitationRepository;
    private HttpSession session;
    private UserRepository userRepository;
    private BudgetRepository budgetRepository;

    public InvitationService(InvitationRepository invitationRepository, HttpSession session, UserRepository userRepository, BudgetRepository budgetRepository) {
        this.invitationRepository = invitationRepository;
        this.session = session;
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
    }

    public List<Invitation> invitationsList () {
        return invitationRepository
                .findAllByReciverId((Long) session.getAttribute("userid"));
    }

    public void deleteInvitation (Long invitationId){
        invitationRepository.deleteById(invitationId);
    }

    public void addUserToBudget (Long userId, Long budgetId){
        Budget updatedBudget = budgetRepository.getOne(budgetId);
        updatedBudget.getUsers().add(userRepository.getOne(userId));
        budgetRepository.save(updatedBudget);
    }

    public void saveInvitationWithReciverEmail (String reciverEmail){
        DateTimeFormatter formatterLong = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate budgetStartDate = (LocalDate) session.getAttribute("currentbudgetstartdate");
        LocalDate budgetEndDate = (LocalDate) session.getAttribute("currentbudgetenddate");
        String budgetStartDateText = budgetStartDate.format(formatterLong);
        String budgetEndDateText = budgetEndDate.format(formatterLong);
        User sender = userRepository.getOne((Long) session.getAttribute("userid"));
        String senderName = sender.getUserName();
        String message = "Użytkownik "+senderName+" zaprosił Cię do budżetu od "
                +budgetStartDateText+" do "+budgetEndDateText;
        Invitation invitation = new Invitation();
        invitation.setBudgetId((Long) session.getAttribute("currentbudgetid"));
        invitation.setSender(sender);
        invitation.setReciver(userRepository.findByEmail(reciverEmail));
        invitation.setMessage(message);
        invitationRepository.save(invitation);
    }

}
