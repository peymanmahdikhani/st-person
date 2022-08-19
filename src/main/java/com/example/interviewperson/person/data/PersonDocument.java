package com.example.interviewperson.person.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Created by Peyman Mahdikhani on 8/18/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PersonDocument {
    @Id
    private String personId;
    @NotBlank(message = "PersonDocument.name can't be empty")
    private String name;
    private @NotNull List<@NotBlank(message = "interest can't be empty") String> interests;
}
