package pl.karoll.spring.homebudget.model;

import javax.persistence.*;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long budgetId;

    private String message;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User reciver;

}
