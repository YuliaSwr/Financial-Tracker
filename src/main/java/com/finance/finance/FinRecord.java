package com.finance.finance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance.user.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class FinRecord {

    @Id
    @SequenceGenerator(
            name = "finance_sequence",
            sequenceName = "finance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "finance_sequence"
    )
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FinanceType type;

   /* @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;*/

    private String category;

    @NotNull
    @Min(0)
    private Long sum;

    @Email
    private String userEmail;

   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private AppUser user;
}
